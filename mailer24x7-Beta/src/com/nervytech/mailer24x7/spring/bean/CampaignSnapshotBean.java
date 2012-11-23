/**
 * 
 */
package com.nervytech.mailer24x7.spring.bean;

import org.springframework.stereotype.Repository;

/**
 * @author bsikkaya
 * 
 */
@Repository("campaignSnapshotBean")
public class CampaignSnapshotBean {

	private String campaignName;
	private long campaignId;
	private String subject;
	private String campaignStatus;
	private String subscriberListName;
	private String subscriberListId;
	private String subscribersCount;
	private String senderId;
	private String senderName;
	private String senderEmailId;

	/**
	 * @return the campaignName
	 */
	public String getCampaignName() {
		return campaignName;
	}

	/**
	 * @param campaignName
	 *            the campaignName to set
	 */
	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

	/**
	 * @return the campaignId
	 */
	public long getCampaignId() {
		return campaignId;
	}

	/**
	 * @param campaignId
	 *            the campaignId to set
	 */
	public void setCampaignId(long campaignId) {
		this.campaignId = campaignId;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject
	 *            the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the subscriberListName
	 */
	public String getSubscriberListName() {
		return subscriberListName;
	}

	/**
	 * @param subscriberListName
	 *            the subscriberListName to set
	 */
	public void setSubscriberListName(String subscriberListName) {
		this.subscriberListName = subscriberListName;
	}

	/**
	 * @return the subscriberListId
	 */
	public String getSubscriberListId() {
		return subscriberListId;
	}

	/**
	 * @param subscriberListId
	 *            the subscriberListId to set
	 */
	public void setSubscriberListId(String subscriberListId) {
		this.subscriberListId = subscriberListId;
	}

	/**
	 * @return the subscribersCount
	 */
	public String getSubscribersCount() {
		return subscribersCount;
	}

	/**
	 * @param subscribersCount
	 *            the subscribersCount to set
	 */
	public void setSubscribersCount(String subscribersCount) {
		this.subscribersCount = subscribersCount;
	}

	/**
	 * @return the campaignStatus
	 */
	public String getCampaignStatus() {
		return campaignStatus;
	}

	/**
	 * @param campaignStatus
	 *            the campaignStatus to set
	 */
	public void setCampaignStatus(String campaignStatus) {
		this.campaignStatus = campaignStatus;
	}

	/**
	 * @return the senderId
	 */
	public String getSenderId() {
		return senderId;
	}

	/**
	 * @param senderId
	 *            the senderId to set
	 */
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	/**
	 * @return the senderName
	 */
	public String getSenderName() {
		return senderName;
	}

	/**
	 * @param senderName
	 *            the senderName to set
	 */
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	/**
	 * @return the senderEmailId
	 */
	public String getSenderEmailId() {
		return senderEmailId;
	}

	/**
	 * @param senderEmailId
	 *            the senderEmailId to set
	 */
	public void setSenderEmailId(String senderEmailId) {
		this.senderEmailId = senderEmailId;
	}

}
