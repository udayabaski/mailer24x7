/**
 * 
 */
package com.nervytech.mailer24x7.spring.form;

import org.springframework.stereotype.Repository;

/**
 * @author ADMIN
 * 
 */
@Repository("campaignStep2Form")
public class CampaignStep1Form {

	private String emailContentType;
	private String message;
	private String messageType;

	/**
	 * @return the emailContentType
	 */
	public String getEmailContentType() {
		return emailContentType;
	}

	/**
	 * @param emailContentType
	 *            the emailContentType to set
	 */
	public void setEmailContentType(String emailContentType) {
		this.emailContentType = emailContentType;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the messageType
	 */
	public String getMessageType() {
		return messageType;
	}

	/**
	 * @param messageType
	 *            the messageType to set
	 */
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

}
