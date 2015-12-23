package org.marquee.exam.service;

/**
 * 异常的基础，exam所有的异常都从这里扩展出来
 * @author Administrator
 *
 */
//TODO 2-2
public class BaseException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1000001L;
	
	protected int code;
	
	public int getCode(){
		return code;
	}

	public BaseException() {
		super();
	}
	public BaseException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseException(String message) {
		super(message);
	}

	public BaseException(Throwable cause) {
		super(cause);
	}

	public String toString() {
		return getCode()+":"+super.toString();
	}
	
	
	
}
