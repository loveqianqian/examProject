package org.marquee.exam.service;

import java.util.List;

import org.marquee.elts.entity.ExamInfo;
import org.marquee.elts.entity.QuestionInfo;

/**
 * 软件核心业务的抽象
 * @author Administrator
 *
 */
//TODO 2-1
public interface ExamService {
	//TODO 2-1.1
	public User login(int id ,String Pwd)throws NameorPasswordException;
	
	/**
	 * 开始考试
	 * @return
	 * @throws ExamOverException
	 */
	//TODO 7-2.1
	ExamInfo start() throws ExamOverException;
	/**
	 * 获取某个考题信息
	 * @param index
	 * @return
	 * @throws ExamOverException
	 */
	//TODO 7-2.2
	QuestionInfo getQuestion(int index)throws ExamOverException;
	//TODO 7-2.3
	//发送当前题目的答案到服务器
	void sendUserAnswers(int questionIndex,List<Integer>answers);
	/**
	 * 交卷
	 */
	//TODO 7-2.4
	int commit();
	//TODO 7-2.5
	int result()throws ExamNotFinishException;
}
