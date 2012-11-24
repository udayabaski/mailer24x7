/**
 * 
 */
package com.nervytech.mailer24x7.spring.form;

import org.springframework.stereotype.Repository;

/**
 * @author ADMIN
 * 
 */
@Repository("campaignStep3Form")
public class CampaignStep3Form {

	private String subscriberGroup;
	private String sendingOption;
	private String scheduledDate;
	private String testMailId;
	private String message;
	private String messageType;
	private String campaignId;

	/**
	 * @return the subscriberGroup
	 */
	public String getSubscriberGroup() {
		return subscriberGroup;
	}

	/**
	 * @param subscriberGroup
	 *            the subscriberGroup to set
	 */
	public void setSubscriberGroup(String subscriberGroup) {
		this.subscriberGroup = subscriberGroup;
	}

	/**
	 * @return the sendingOption
	 */
	public String getSendingOption() {
		return sendingOption;
	}

	/**
	 * @param sendingOption
	 *            the sendingOption to set
	 */
	public void setSendingOption(String sendingOption) {
		this.sendingOption = sendingOption;
	}

	/**
	 * @return the scheduledDate
	 */
	public String getScheduledDate() {
		return scheduledDate;
	}

	/**
	 * @param scheduledDate
	 *            the scheduledDate to set
	 */
	public void setScheduledDate(String scheduledDate) {
		this.scheduledDate = scheduledDate;
	}

	/**
	 * @return the testMailId
	 */
	public String getTestMailId() {
		return testMailId;
	}

	/**
	 * @param testMailId
	 *            the testMailId to set
	 */
	public void setTestMailId(String testMailId) {
		this.testMailId = testMailId;
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

	/**
	 * @return the campaignId
	 */
	public String getCampaignId() {
		return campaignId;
	}

	/**
	 * @param campaignId
	 *            the campaignId to set
	 */
	public void setCampaignId(String campaignId) {
		this.campaignId = campaignId;
	}

}
