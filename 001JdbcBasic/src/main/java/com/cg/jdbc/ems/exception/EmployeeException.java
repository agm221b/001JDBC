/**
 * 
 */
package com.cg.jdbc.ems.exception;

/**
 * @author smitkuma
 *
 */
public class EmployeeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3571719954478869568L;

	/**
	 * 
	 */
	public EmployeeException() {
		super();
	}

	/**
	 * @param message
	 */
	public EmployeeException(String message) {
		super(message);

	}

	/**
	 * @param cause
	 */
	public EmployeeException(Throwable cause) {
		super(cause);

	}

	/**
	 * @param message
	 * @param cause
	 */
	public EmployeeException(String message, Throwable cause) {
		super(message, cause);

	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public EmployeeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

}
