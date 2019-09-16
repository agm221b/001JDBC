/**
 * 
 */
package com.cg.jdbc.ems.exception;

/**
 * @author rvikash
 *
 */
public class MyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9036276925262103108L;

	/**
	 * 
	 */
	public MyException() {
		super();
	}

	/**
	 * @param message
	 */
	public MyException(String message) {
		super(message);

	}

	/**
	 * @param cause
	 */
	public MyException(Throwable cause) {
		super(cause);

	}

	/**
	 * @param message
	 * @param cause
	 */
	public MyException(String message, Throwable cause) {
		super(message, cause);

	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public MyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

}
