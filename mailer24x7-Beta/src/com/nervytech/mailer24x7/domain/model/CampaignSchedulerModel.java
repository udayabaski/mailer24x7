/**
 * 
 */
package com.nervytech.mailer24x7.domain.model;

/**
 * @author bsikkaya
 * 
 */
public class CampaignSchedulerModel {

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
	private long subscriberListId;
	private long latestSubscriberId;
	private String unsubscribeLink;
	private String unsubscribeSubject;
	private String s3Path;
	private String senderEmailId;
	private int campaignType;
	private long orgId;
	private int syncStatus;
	private String confirmationMailId;

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
	 * @return the s3Path
	 */
	public String getS3Path() {
		return s3Path;
	}

	/**
	 * @return the confirmationMailId
	 */
	public String getConfirmationMailId() {
		return confirmationMailId;
	}

	/**
	 * @param confirmationMailId the confirmationMailId to set
	 */
	public void setConfirmationMailId(String confirmationMailId) {
		this.confirmationMailId = confirmationMailId;
	}

	/**
	 * @param s3Path
	 *            the s3Path to set
	 */
	public void setS3Path(String s3Path) {
		this.s3Path = s3Path;
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
	public int getCampaignType() {
		return campaignType;
	}

	/**
	 * @param campaignType
	 *            the campaignType to set
	 */
	public void setCampaignType(int campaignType) {
		this.campaignType = campaignType;
	}

	/**
	 * @return the orgId
	 */
	public long getOrgId() {
		return orgId;
	}

	/**
	 * @param orgId
	 *            the orgId to set
	 */
	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}

	/**
	 * @return the syncStatus
	 */
	public int getSyncStatus() {
		return syncStatus;
	}

	/**
	 * @param syncStatus
	 *            the syncStatus to set
	 */
	public void setSyncStatus(int syncStatus) {
		this.syncStatus = syncStatus;
	}
	
	

}
