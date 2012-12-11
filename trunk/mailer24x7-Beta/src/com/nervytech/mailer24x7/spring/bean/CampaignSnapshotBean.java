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
	private int subscribersCount;
	private String senderId;
	private String senderName;
	private String senderEmailId;
	private String campaignType;
	private int contentTypeInt;
	private String content;
	private String s3Path;
	private String nextAction;

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
	public int getSubscribersCount() {
		return subscribersCount;
	}

	/**
	 * @param subscribersCount
	 *            the subscribersCount to set
	 */
	public void setSubscribersCount(int subscribersCount) {
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

	/**
	 * @return the campaignType
	 */
	public String getCampaignType() {
		return campaignType;
	}

	/**
	 * @param campaignType
	 *            the campaignType to set
	 */
	public void setCampaignType(String campaignType) {
		this.campaignType = campaignType;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the s3Path
	 */
	public String getS3Path() {
		return s3Path;
	}

	/**
	 * @param s3Path
	 *            the s3Path to set
	 */
	public void setS3Path(String s3Path) {
		this.s3Path = s3Path;
	}

	/**
	 * @return the contentTypeInt
	 */
	public int getContentTypeInt() {
		return contentTypeInt;
	}

	/**
	 * @param contentTypeInt
	 *            the contentTypeInt to set
	 */
	public void setContentTypeInt(int contentTypeInt) {
		this.contentTypeInt = contentTypeInt;
	}

	/**
	 * @return the nextAction
	 */
	public String getNextAction() {
		return nextAction;
	}

	/**
	 * @param nextAction
	 *            the nextAction to set
	 */
	public void setNextAction(String nextAction) {
		this.nextAction = nextAction;
	}

}
