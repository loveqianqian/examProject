package org.marquee.exam.server;

import java.io.Serializable;

import org.marquee.exam.service.BaseException;

/**
 * 代表服务器对客户调用的反馈
 * 包含：正确时候返回的方法值
 * 和异常返回的异常消息已经异常Code
 * @author Administrator
 *
 */

//TODO 11.2
public class Response implements Serializable{

	private static final long serialVersionUID = 873850631L;
	
	private String sessionID;
	
	/** 方法的返回值*/
	private Object value;
	
	/** 返回状态， 200正常，501...*/
	private int state=200;
	
	private String message="Success";

	public Response() {
	}
	/** 任意异常的返回*/
	public Response(String sid,Exception e) {
		state=500;
		this.sessionID=sid;
		message=e.getMessage();
	}
	/** 正常方法的返回*/
	public Response(String sid,Object val) {
		state=200;
		this.sessionID=sid;
		value=val;
	}
	/** 异常方法的返回*/
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
