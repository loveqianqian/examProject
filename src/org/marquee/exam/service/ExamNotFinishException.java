package org.marquee.exam.service;

//TODO 7-2.2
public class ExamNotFinishException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 10215L;

	public ExamNotFinishException() {
		super();
		code=consts.Err_Exam_Not_Finish;
	}

	public ExamNotFinishException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		code=consts.Err_Exam_Not_Finish;
	}

	public ExamNotFinishException(String message, Throwable cause) {
		super(message, cause);
		code=consts.Err_Exam_Not_Finish;
	}

	public ExamNotFinishException(String message) {
		super(message);
		code=consts.Err_Exam_Not_Finish;
	}

	public ExamNotFinishException(Throwable cause) {
		super(cause);
		code=consts.Err_Exam_Not_Finish;
	}
	
	

}
