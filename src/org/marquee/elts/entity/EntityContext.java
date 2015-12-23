package org.marquee.elts.entity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.marquee.exam.service.User;
import org.marquee.util.Config;

//TODO 4-1-entitycontext
public class EntityContext {

	private Config config;
	private Map<Integer, User> users = new HashMap<Integer, User>();
	// TODO 6-2.2
	private List<Question> questions = new ArrayList<Question>();
	/** 按照分类对题目进行处理 */
	private Map<Integer, List<Question>> levels = new HashMap<Integer, List<Question>>();

	public EntityContext(Config config) throws IOException {
		this.config = config;
		loadUsers();
		// TODO 6-2
		loadQuestions();
	}

	// TODO 6-2.1
	private void loadQuestions() throws IOException {
		String questionAndAnswer = config.getString("QuestionFile");
		BufferedReader in = new BufferedReader(
				new FileReader(questionAndAnswer));
		String str;
		while ((str = in.readLine()) != null) {
			if (str.trim().equals(" "))
				continue;
			Question q = parseField(str);
			readTitle(q, in);
			readOptions(q, in);
			questions.add(q);
			addByLevel(q);
//			System.out.println(q.toString());
		}
	}

	// TODO 6-2.2
	/**  @option=4,answer=0,score=5,level=3
	 * @	option=4	,	answer=0	,	score=5	,	level=3 
	 * @throws IOException */
	private Question parseField(String str) throws IOException {
		String reg="^@option=4,answer=[0-3](/[0-3]){0,3},score=\\d{1,2},level=([1-9]|10)$";
		if (!str.matches(reg)) {
			throw new IOException("文件格式有问题");
		}
		//切割所需的部分
		String[] data=str.split("[@,]");
		Question q=new Question();
		q.setOptionNumbers(Integer.parseInt(data[1].split("=")[1]));
		q.setAnswer(parseAnswer(data[2].split("=")[1]));
		q.setScore(Integer.parseInt(data[3].split("=")[1]));
		q.setLevel(Integer.parseInt(data[4].split("=")[1]));
		return q;
	}
	// TODO 6-2.2.1
	private List<Integer> parseAnswer(String str) {
		List<Integer> answer=new ArrayList<Integer>();
		String[] data=str.split("/");
		for (String s : data) {
//			answer.add(Integer.parseInt(s));
			answer.add(new Integer(s));
		}
		return answer;
	}

	// TODO 6-2.2
	private void readOptions(Question q, BufferedReader in) throws IOException {
		List<String> opts=new ArrayList<String>();
		for (int i = 0; i < 4; i++) {
			opts.add(in.readLine());
		}
		q.setOptions(opts);
	}

	// TODO 6-2.2
	private void readTitle(Question q, BufferedReader in) throws IOException {
		q.setTitle(in.readLine());
	}

	// TODO 6-2.2
	private void addByLevel(Question q) {
		List<Question> list = levels.get(q.getLevel());
		if (list == null) {
			list = new ArrayList<Question>();
			levels.put(q.getLevel(), list);
		}
		list.add(q);
	}

	private void loadUsers() throws IOException {
		String filename = config.getString("UserFile");
		BufferedReader in = new BufferedReader(new FileReader(filename));
		String str;
		while ((str = in.readLine()) != null) {
			if (str.trim().equals(" ")) {
				continue;
			}
			User u = parseUser(str);
			users.put(u.getId(), u);
		}
	}

	/**
	 * 1000:13955235245B2497:志伟： 5c269363c8f72719f607d27aec0ea67：
	 * 18722268416：diaozhiwei2k@163.com
	 * 
	 * @param str
	 * @return
	 */
	private User parseUser(String str) {
		String[] buf = str.split(":");
		User u = new User();
		// System.out.println(buf[0]);
		int a = Integer.parseInt(buf[0]);
		u.setId(a);
		u.setCertifyName(buf[1]);
		u.setName(buf[2]);
		u.setPassword(buf[3]);
		u.setPhoneNumber(buf[4]);
		u.setEmail(buf[5]);
		return u;
	}

	public User findUserByID(int id) {
		return users.get(id);
	}
	//通过id查找
	public Question getQuestion(int id){
		for (Question q : questions) {
			if (q.getId()==id) {
				return q;
			}
		}
		return null;
	}
	//创建副本
	public List<Question> getQuestions(int level){
		return new ArrayList<Question>(levels.get(level));
	}
	/**
	 * TEST
	 * @param args
	 */
//	 public static void main(String[] args) {
//	 try {
//	 Config context=new Config("client.properties");
//	 EntityContext entity=new EntityContext(context);
//	 System.out.println(entity.findUserByID(1004));
//	 System.out.println(entity.levels);
//	 } catch (Exception e) {
//	 e.printStackTrace();
//	 }
//	 }
}
