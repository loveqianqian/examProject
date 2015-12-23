package org.marquee.exam.service;

//TODO 7-2.2
public class ExamOverException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 156545L;
	
	public ExamOverException(){
		code=consts.Err_Exam_Over;
	}

	public ExamOverException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		code=consts.Err_Exam_Over;
	}

	public ExamOverException(String message, Throwable cause) {
		super(message, cause);
		code=consts.Err_Exam_Over;
	}

	public ExamOverException(String message) {
		super(message);
		code=consts.Err_Exam_Over;
	}

	public ExamOverException(Throwable cause) {
		super(cause);
		code=consts.Err_Exam_Over;
	}
	
}
