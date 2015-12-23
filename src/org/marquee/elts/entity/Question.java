package org.marquee.elts.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Question代表一个试题，包含1个问题，4个选项，以及正确答案
 * @author Administrator
 *
 */
//TODO 6-question
public class Question implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 357191L;
	
	public static final int LEVEL1=1;
	public static final int LEVEL2=2;
	public static final int LEVEL3=3;
	public static final int LEVEL4=4;
	public static final int LEVEL5=5;
	public static final int LEVEL6=6;
	public static final int LEVEL7=7;
	public static final int LEVEL8=8;
	public static final int LEVEL9=9;
	public static final int LEVEL10=10;
	
	public static final int SINGLE_SELETION=0;
	public static final int MULTI_SELETION=1;
	
	private int id;
	private String title;
	private int optionNumbers;
	private List<Integer> answer;
	private List<String> options;
	private int score;
	private int level;
	private int type;
	
	public Question(){
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOptionNumbers() {
		return optionNumbers;
	}

	public void setOptionNumbers(int optionNumbers) {
		this.optionNumbers = optionNumbers;
	}

	public List<Integer> getAnswer() {
		return answer;
	}

	public void setAnswer(List<Integer> answer) {
		this.answer = answer;
	}

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj==null)
			return false;
		if(this==obj)
			return true;
		if (obj instanceof Question) {
			Question other=(Question)obj;
			return other.id==this.id;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public String toString() {
		StringBuffer sb=new StringBuffer(title+"\n");
		for (int i = 0; i < optionNumbers; i++) {
			sb.append((char)('A'+i)+"."+options.get(i)+"\n");
		}
		sb.append("\n");
		return sb.toString();
	};
	
	
}
