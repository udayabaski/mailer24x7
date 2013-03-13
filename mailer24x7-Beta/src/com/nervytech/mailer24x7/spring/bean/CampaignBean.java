/**
 * 
 */
package com.nervytech.mailer24x7.spring.bean;

import org.springframework.stereotype.Repository;

/**
 * @author bsikkaya
 * 
 */
@Repository("campaignBean")
public class CampaignBean {

	private String campaignName;
	private long campaignId;
	private String campaignStatus;
	private String createdDate;
	private String createdBy;
	private int opened;
	private int bounced;
	private int clicked;
	private String subject;
	private String lastModifiedTime;
	private String scheduledOn;
	private int unsubscribed;
	private long subscriberListId;
	private String subscriberListName;
	private int totalEmailsSent;
	private long latestSubscriberId;
	private String unsubscribeLink;
	private String unsubscribeSubject;
	private String sentTime;
	private String isPoweredBy;
	private String imageLoc;
	private int campaignType;
	private String replyToMailId;
	private long senderId;
	private String s3Path;

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
	 * @return the createdDate
	 */
	public String getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate
	 *            the createdDate to set
	 */
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy
	 *            the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the opened
	 */
	public int getOpened() {
		return opened;
	}

	/**
	 * @param opened
	 *            the opened to set
	 */
	public void setOpened(int opened) {
		this.opened = opened;
	}

	/**
	 * @return the bounced
	 */
	public int getBounced() {
		return bounced;
	}

	/**
	 * @param bounced
	 *            the bounced to set
	 */
	public void setBounced(int bounced) {
		this.bounced = bounced;
	}

	/**
	 * @return the clicked
	 */
	public int getClicked() {
		return clicked;
	}

	/**
	 * @param clicked
	 *            the clicked to set
	 */
	public void setClicked(int clicked) {
		this.clicked = clicked;
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
	 * @return the lastModifiedTime
	 */
	public String getLastModifiedTime() {
		return lastModifiedTime;
	}

	/**
	 * @param lastModifiedTime
	 *            the lastModifiedTime to set
	 */
	public void setLastModifiedTime(String lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	/**
	 * @return the scheduledOn
	 */
	public String getScheduledOn() {
		return scheduledOn;
	}

	/**
	 * @param scheduledOn
	 *            the scheduledOn to set
	 */
	public void setScheduledOn(String scheduledOn) {
		this.scheduledOn = scheduledOn;
	}

	/**
	 * @return the subscriberListId
	 */
	public long getSubscriberListId() {
		return subscriberListId;
	}

	/**
	 * @param subscriberListId
	 *            the subscriberListId to set
	 */
	public void setSubscriberListId(long subscriberListId) {
		this.subscriberListId = subscriberListId;
	}

	/**
	 * @return the latestSubscriberId
	 */
	public long getLatestSubscriberId() {
		return latestSubscriberId;
	}

	/**
	 * @param latestSubscriberId
	 *            the latestSubscriberId to set
	 */
	public void setLatestSubscriberId(long latestSubscriberId) {
		this.latestSubscriberId = latestSubscriberId;
	}

	/**
	 * @return the unsubscribeLink
	 */
	public String getUnsubscribeLink() {
		return unsubscribeLink;
	}

	/**
	 * @param unsubscribeLink
	 *            the unsubscribeLink to set
	 */
	public void setUnsubscribeLink(String unsubscribeLink) {
		this.unsubscribeLink = unsubscribeLink;
	}

	/**
	 * @return the unsubscribeSubject
	 */
	public String getUnsubscribeSubject() {
		return unsubscribeSubject;
	}

	/**
	 * @param unsubscribeSubject
	 *            the unsubscribeSubject to set
	 */
	public void setUnsubscribeSubject(String unsubscribeSubject) {
		this.unsubscribeSubject = unsubscribeSubject;
	}

	/**
	 * @return the unsubscribed
	 */
	public int getUnsubscribed() {
		return unsubscribed;
	}

	/**
	 * @param unsubscribed
	 *            the unsubscribed to set
	 */
	public void setUnsubscribed(int unsubscribed) {
		this.unsubscribed = unsubscribed;
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
	 * @return the totalEmailsSent
	 */
	public int getTotalEmailsSent() {
		return totalEmailsSent;
	}

	/**
	 * @param totalEmailsSent
	 *            the totalEmailsSent to set
	 */
	public void setTotalEmailsSent(int totalEmailsSent) {
		this.totalEmailsSent = totalEmailsSent;
	}

	/**
	 * @return the sentTime
	 */
	public String getSentTime() {
		return sentTime;
	}

	/**
	 * @param sentTime
	 *            the sentTime to set
	 */
	public void setSentTime(String sentTime) {
		this.sentTime = sentTime;
	}

	/**
	 * @return the isPoweredBy
	 */
	public String getIsPoweredBy() {
		return isPoweredBy;
	}

	/**
	 * @param isPoweredBy the isPoweredBy to set
	 */
	public void setIsPoweredBy(String isPoweredBy) {
		this.isPoweredBy = isPoweredBy;
	}

	/**
	 * @return the imageLoc
	 */
	public String getImageLoc() {
		return imageLoc;
	}

	/**
	 * @param imageLoc the imageLoc to set
	 */
	public void setImageLoc(String imageLoc) {
		this.imageLoc = imageLoc;
	}

	/**
	 * @return the campaignType
	 */
	public int getCampaignType() {
		return campaignType;
	}

	/**
	 * @param campaignType the campaignType to set
	 */
	public void setCampaignType(int campaignType) {
		this.campaignType = campaignType;
	}

	/**
	 * @return the replyToMailId
	 */
	public String getReplyToMailId() {
		return replyToMailId;
	}

	/**
	 * @param replyToMailId the replyToMailId to set
	 */
	public void setReplyToMailId(String replyToMailId) {
		this.replyToMailId = replyToMailId;
	}

	/**
	 * @return the senderId
	 */
	public long getSenderId() {
		return senderId;
	}

	/**
	 * @param senderId the senderId to set
	 */
	public void setSenderId(long senderId) {
		this.senderId = senderId;
	}

	/**
	 * @return the s3Path
	 */
	public String getS3Path() {
		return s3Path;
	}

	/**
	 * @param s3Path the s3Path to set
	 */
	public void setS3Path(String s3Path) {
		this.s3Path = s3Path;
	}
	
}
