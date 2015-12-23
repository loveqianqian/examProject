package org.marquee.exam.server;

import java.io.Serializable;

import org.marquee.exam.service.BaseException;

/**
 * ����������Կͻ����õķ���
 * ��������ȷʱ�򷵻صķ���ֵ
 * ���쳣���ص��쳣��Ϣ�Ѿ��쳣Code
 * @author Administrator
 *
 */

//TODO 11.2
public class Response implements Serializable{

	private static final long serialVersionUID = 873850631L;
	
	private String sessionID;
	
	/** �����ķ���ֵ*/
	private Object value;
	
	/** ����״̬�� 200������501...*/
	private int state=200;
	
	private String message="Success";

	public Response() {
	}
	/** �����쳣�ķ���*/
	public Response(String sid,Exception e) {
		state=500;
		this.sessionID=sid;
		message=e.getMessage();
	}
	/** ���������ķ���*/
	public Response(String sid,Object val) {
		state=200;
		this.sessionID=sid;
		value=val;
	}
	/** �쳣�����ķ���*/
	public Response(String sid,BaseException e) {
		state=e.getCode();
		this.sessionID=sid;
		message=e.getMessage();
	}

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSuccess(){
		return state==200;
	}

	@Override
	public String toString() {
		return "sessionID=" + sessionID + ", value=" + value+ ", state=" + state  ;
	}
	
}
