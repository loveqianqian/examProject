package org.marquee.exam.service;

import java.util.List;

import org.marquee.elts.entity.ExamInfo;
import org.marquee.elts.entity.QuestionInfo;

/**
 * �������ҵ��ĳ���
 * @author Administrator
 *
 */
//TODO 2-1
public interface ExamService {
	//TODO 2-1.1
	public User login(int id ,String Pwd)throws NameorPasswordException;
	
	/**
	 * ��ʼ����
	 * @return
	 * @throws ExamOverException
	 */
	//TODO 7-2.1
	ExamInfo start() throws ExamOverException;
	/**
	 * ��ȡĳ��������Ϣ
	 * @param index
	 * @return
	 * @throws ExamOverException
	 */
	//TODO 7-2.2
	QuestionInfo getQuestion(int index)throws ExamOverException;
	//TODO 7-2.3
	//���͵�ǰ��Ŀ�Ĵ𰸵�������
	void sendUserAnswers(int questionIndex,List<Integer>answers);
	/**
	 * ����
	 */
	//TODO 7-2.4
	int commit();
	//TODO 7-2.5
	int result()throws ExamNotFinishException;
}
