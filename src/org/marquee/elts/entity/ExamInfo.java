package org.marquee.elts.entity;

import java.io.Serializable;

import org.marquee.exam.service.User;

/**
 * ������Ϣֵ����
 * ��ֵ����
 * @author Administrator
 *
 */
//TODO 7-3
public class ExamInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 10025L;
	
	/** ���Կ�Ŀ*/
	private String title;
	/** ���� */
	private User user;
	/**
	 * ����Ϊ��λ
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
		//"��������־ΰ"
		//		+ "	��ţ�1001	����ʱ�䣺4����	��Ŀ���ھ��	    ��Ŀ������20"
		return "������" + user + ", ����ʱ��:" + timeLimit + ", ��Ŀ������" + questionCount;
	}
}
