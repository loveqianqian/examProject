package org.marquee.exam.server;

import java.io.Serializable;
import java.util.Arrays;

//TODO 11.2
public class Request implements Serializable{

	private static final long serialVersionUID = 125498L;
	
	/** 使用uuid算法实现*/
	private String sessionID;
	
	/** 方法名*/
	private String method;//login
	/** 参数类型列表*/
	private Class[] argsTypes;//int ,string
	/** 方法 参数*/
	private Object[] args;//1001 "dzw123"
	
	public Request(){
	}
	public Request(String method,Class...argsTypes){
		this.method=method;
		this.argsTypes=argsTypes;
	}
	//TODO 10.2
	public Request(String sessionID, String method, Class[] argsTypes,
			Object[] args) {
		this.sessionID = sessionID;
		this.method = method;
		this.argsTypes = argsTypes;
		this.args = args;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Class[] getArgsTypes() {
		return argsTypes;
	}
	public void setArgsTypes(Class[] argsTypes) {
		this.argsTypes = argsTypes;
	}
	public Object[] getArgs() {
		return args;
	}
	public void setArgs(Object[] args) {
		this.args = args;
	}
	public String getSessionID() {
		return sessionID;
	}
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}
	@Override
	public String toString() {
		return sessionID+":"+method+":"+Arrays.toString(args);
	}
	
	
}
