package org.marquee.elts.entity;

import java.io.Serializable;

import org.marquee.exam.service.User;

/**
 * 考试信息值对象
 * 是值对象
 * @author Administrator
 *
 */
//TODO 7-3
public class ExamInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 10025L;
	
	/** 考试科目*/
	private String title;
	/** 考生 */
	private User user;
	/**
	 * 分钟为单位
	 */
	private int timeLimit;
	
	private int questionCount;
	
	public ExamInfo(){
	}
	
	public ExamInfo(String title,User user, int timeLimit,int questionCount){
		super();
		this.title=title;
		this.user=user;
		this.timeLimit=timeLimit;
		this.questionCount=questionCount;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
	}

	public int getQuestionCount() {
		return questionCount;
	}

	public void setQuestionCount(int questionCount) {
		this.questionCount = questionCount;
	}

	@Override
	public String toString() {
		//"姓名：刁志伟"
		//		+ "	编号：1001	考试时间：4分钟	科目：挖掘机	    题目数量：20"
		return "姓名：" + user + ", 考试时间:" + timeLimit + ", 题目数量：" + questionCount;
	}
}
