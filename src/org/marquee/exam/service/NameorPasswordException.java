package org.marquee.exam.service;

//TODO 2-3
public class NameorPasswordException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 10001L;

	public NameorPasswordException() {
		super();
		code=consts.Err_Name_Or_Password;
	}

	public NameorPasswordException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		code=consts.Err_Name_Or_Password;
	}

	public NameorPasswordException(String message, Throwable cause) {
		super(message, cause);
		code=consts.Err_Name_Or_Password;
	}

	public NameorPasswordException(String message) {
		super(message);
		code=consts.Err_Name_Or_Password;
	}

	public NameorPasswordException(Throwable cause) {
		super(cause);
		code=consts.Err_Name_Or_Password;
	}

	
}
