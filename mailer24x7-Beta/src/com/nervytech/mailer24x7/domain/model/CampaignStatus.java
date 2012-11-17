/**
 * 
 */
package com.nervytech.mailer24x7.domain.model;

/**
 * @author bsikkaya
 * 
 */
public class CampaignStatus {

	private long campaignId;
	private long orgId;
	private long userId;
	private long subscriberListId;
	private long senderId;
	private String scheduledOn;
	private int status;
	private long latestSubscriberCount;
	private int syncStatus;
	private long latestUpdatedSubscriberId;
	private int totalEventFetched;
	private int opened;
	private int clicked;
	private int bounced;
	private int dropped;
	private int unsubscribed;
	private int totalSubscriberSent;
	private String sentTime;
	private String lastUpdatedTime;
	private String s3Path;

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
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
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
	 * @return the senderId
	 */
	public long getSenderId() {
		return senderId;
	}

	/**
	 * @param senderId
	 *            the senderId to set
	 */
	public void setSenderId(long senderId) {
		this.senderId = senderId;
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
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the latestSubscriberCount
	 */
	public long getLatestSubscriberCount() {
		return latestSubscriberCount;
	}

	/**
	 * @param latestSubscriberCount
	 *            the latestSubscriberCount to set
	 */
	public void setLatestSubscriberCount(long latestSubscriberCount) {
		this.latestSubscriberCount = latestSubscriberCount;
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

	/**
	 * @return the latestUpdatedSubscriberId
	 */
	public long getLatestUpdatedSubscriberId() {
		return latestUpdatedSubscriberId;
	}

	/**
	 * @param latestUpdatedSubscriberId
	 *            the latestUpdatedSubscriberId to set
	 */
	public void setLatestUpdatedSubscriberId(long latestUpdatedSubscriberId) {
		this.latestUpdatedSubscriberId = latestUpdatedSubscriberId;
	}

	/**
	 * @return the totalEventFetched
	 */
	public int getTotalEventFetched() {
		return totalEventFetched;
	}

	/**
	 * @param totalEventFetched
	 *            the totalEventFetched to set
	 */
	public void setTotalEventFetched(int totalEventFetched) {
		this.totalEventFetched = totalEventFetched;
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
	 * @return the dropped
	 */
	public int getDropped() {
		return dropped;
	}

	/**
	 * @param dropped
	 *            the dropped to set
	 */
	public void setDropped(int dropped) {
		this.dropped = dropped;
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
	 * @return the totalSubscriberSent
	 */
	public int getTotalSubscriberSent() {
		return totalSubscriberSent;
	}

	/**
	 * @param totalSubscriberSent
	 *            the totalSubscriberSent to set
	 */
	public void setTotalSubscriberSent(int totalSubscriberSent) {
		this.totalSubscriberSent = totalSubscriberSent;
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
	 * @return the lastUpdatedTime
	 */
	public String getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	/**
	 * @param lastUpdatedTime
	 *            the lastUpdatedTime to set
	 */
	public void setLastUpdatedTime(String lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CampaignStatus [campaignId=");
		builder.append(campaignId);
		builder.append(", orgId=");
		builder.append(orgId);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", subscriberListId=");
		builder.append(subscriberListId);
		builder.append(", senderId=");
		builder.append(senderId);
		builder.append(", scheduledOn=");
		builder.append(scheduledOn);
		builder.append(", status=");
		builder.append(status);
		builder.append(", latestSubscriberCount=");
		builder.append(latestSubscriberCount);
		builder.append(", syncStatus=");
		builder.append(syncStatus);
		builder.append(", latestUpdatedSubscriberId=");
		builder.append(latestUpdatedSubscriberId);
		builder.append(", totalEventFetched=");
		builder.append(totalEventFetched);
		builder.append(", opened=");
		builder.append(opened);
		builder.append(", clicked=");
		builder.append(clicked);
		builder.append(", bounced=");
		builder.append(bounced);
		builder.append(", dropped=");
		builder.append(dropped);
		builder.append(", unsubscribed=");
		builder.append(unsubscribed);
		builder.append(", totalSubscriberSent=");
		builder.append(totalSubscriberSent);
		builder.append(", sentTime=");
		builder.append(sentTime);
		builder.append(", lastUpdatedTime=");
		builder.append(lastUpdatedTime);
		builder.append(", s3Path=");
		builder.append(s3Path);
		builder.append("]");
		return builder.toString();
	}

}
