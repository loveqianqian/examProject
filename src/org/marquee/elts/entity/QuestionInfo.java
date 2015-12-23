package org.marquee.elts.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * ������û��𰸵�ֵ���󣬱�ʾ�����ϵ�һ����Ͷ�Ӧ���û��Ĵ�
 * ��ֵ����
 * @author zhiwei
 *
 */
//TODO 7-1
public class QuestionInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 100145214L;
	private Question question;
	/**  ���Ծ�(paper)�е���� 0,1,2*/
	private int questionIndex;
	/**  �û���*/
	private List<Integer> userAnswers=new ArrayList<Integer>();
	
	public QuestionInfo(){
	}
	public QuestionInfo(int questionIndex, Question question){
		super();
		this.question=question;
		this.questionIndex=questionIndex;
	}
	public QuestionInfo(int questionIndex, Question question,List<Integer> userAnswers){
		super();
		this.question=question;
		this.questionIndex=questionIndex;
		this.userAnswers=userAnswers;
	}
	public Question getQuestion(){
		return question;
	}
	public int getQuestionIndex() {
		return questionIndex;
	}
	public void setQuestionIndex(int questionIndex) {
		this.questionIndex = questionIndex;
	}
	public List<Integer> getUserAnswers() {
		return userAnswers;
	}
	public void setUserAnswers(List<Integer> userAnswers) {
		this.userAnswers = userAnswers;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	@Override
	public String toString() {
		return "QuestionInfo [question=" + question + ", questionIndex="
				+ questionIndex + ", userAnswers=" + userAnswers + "]";
	}
	
	

}
