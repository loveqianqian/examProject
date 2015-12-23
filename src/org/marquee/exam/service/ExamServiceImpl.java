package org.marquee.exam.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Random;

import org.marquee.elts.entity.EntityContext;
import org.marquee.elts.entity.ExamInfo;
import org.marquee.elts.entity.Question;
import org.marquee.elts.entity.QuestionInfo;
import org.marquee.util.Config;
import org.marquee.util.Md5Utils;


//TODO 5-1
public class ExamServiceImpl implements ExamService {
	
	//TODO 10.1
	private boolean finish =false;
	
	/**
	 * 考卷是试题信息的集合
	 */
//	private Map<Integer, QuestionInfo> paper= new HashMap<Integer, QuestionInfo>();
	private List<QuestionInfo> paper=new ArrayList<QuestionInfo>();
	private EntityContext context;
	private Config config;
	private User loginUser;
	private int score;
	
	
	public ExamServiceImpl(EntityContext context,Config config){
		this.context=context;
		this.config=config;
	}

	public ExamServiceImpl(EntityContext context){
		this.context=context;
	}
	public User login(int id, String Pwd) throws NameorPasswordException {
		User u=context.findUserByID(id);
		if (u==null) {
			throw new NameorPasswordException("无此用户");
		}
		if (u.getPassword().equals(Md5Utils.md5(Pwd))) {
			loginUser=u;
			return u;
		}
		throw new NameorPasswordException("密码错误");
	}
	//TODO 7-2.2.a
	public ExamInfo start() throws ExamOverException {
		//TODO 10.1
		if (finish) {
			throw new ExamOverException("考试已经结束!");
		}
		//随机组织考卷
		buildPaper();
		//创建ExamInfo 实例
		ExamInfo info=new ExamInfo();
		info.setQuestionCount(paper.size());
		info.setTimeLimit(config.getInt("TimeLimit"));
		info.setTitle(config.getString("paperTitle"));
		info.setUser(loginUser);
		return info;
	}
	/**
	 * 组织考卷，考卷是QuestionInfo的集合，
	 * 组织规则：
	 * 1、一个10个level，每个level随机抽取两道题
	 */
	private void buildPaper(){
		int i=0;
		Random random=new Random();
		for (int level = Question.LEVEL1; level <= Question.LEVEL10; level++) {
			List<Question> list=context.getQuestions(level);
			Question q1=list.remove(random.nextInt(list.size()));
			Question q2=list.remove(random.nextInt(list.size()));
//			QuestionInfo info=new QuestionInfo(i++,q1);
//			paper.put(info.getQuestionIndex(), info);
//			info=new QuestionInfo(i++,q1);
//			paper.put(info.getQuestionIndex(), info);
			paper.add(new QuestionInfo(i++,q1));
			paper.add(new QuestionInfo(i++,q2));
		}
	}
	//TODO 7-2.2.b
	/**
	 * 通过序号获取考题
	 */
	public QuestionInfo getQuestion(int index) throws ExamOverException {
		//TODO 10.1
				if (finish) {
					throw new ExamOverException("考试已经结束!");
				}
		return paper.get(index);
	}
	//TODO 7-2.2.c
	/**
	 * 将用户答案保存在试卷中
	 */
	public void sendUserAnswers(int questionIndex, List<Integer> answers) {
		QuestionInfo q=paper.get(questionIndex);
		q.setUserAnswers(new ArrayList<Integer>(answers));
	}
	//TODO 7-2.2.d
	public int commit() {
		for (QuestionInfo q : paper) {
			List<Integer> userAnswers=q.getUserAnswers();
			if (userAnswers.equals(q.getQuestion().getAnswer())) {
				score+=q.getQuestion().getScore();
			}
		}
		finish=true;
		return score;
	}
	//TODO 7-2.2.e
	public int result() throws ExamNotFinishException {
		//TODO 10.1
		if (!finish) {
			throw new ExamNotFinishException("考试还没结束！");
		}
		return score;
	}
	//test
//	public static void main(String[] args) {
//		try{
//			Config config = new Config("client.properties");
//			EntityContext entity=new EntityContext(config);
//			ExamServiceImpl service=new ExamServiceImpl(entity,config);
//			ExamInfo examinfo=service.start();
//			System.out.println(service.paper);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		
//	}

}
