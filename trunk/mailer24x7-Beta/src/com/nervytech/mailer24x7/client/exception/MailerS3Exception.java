/**
 * 
 */
package com.nervytech.mailer24x7.client.exception;

/**
 * @author bsikkaya
 *
 */
public class MailerS3Exception extends Exception {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7439542851619721060L;

	/**
	 * 
	 */
	public MailerS3Exception() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public MailerS3Exception(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public MailerS3Exception(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public MailerS3Exception(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
