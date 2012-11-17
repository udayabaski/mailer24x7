/**
 * 
 */
package com.nervytech.mailer24x7.domain.model;

/**
 * @author bsikkaya
 * 
 */
public class SubscriberList {

	private long subscriberListId;
	private long orgId;
	private String subscriberListName;
	private String userId;
	private String createdTime;
	private String lastModifiedTime;
	private int activeCount;
	private int bounceCount;
	private int unsubscriberCount;

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
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the createdTime
	 */
	public String getCreatedTime() {
		return createdTime;
	}

	/**
	 * @param createdTime
	 *            the createdTime to set
	 */
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
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
	 * @return the activeCount
	 */
	public int getActiveCount() {
		return activeCount;
	}

	/**
	 * @param activeCount
	 *            the activeCount to set
	 */
	public void setActiveCount(int activeCount) {
		this.activeCount = activeCount;
	}

	/**
	 * @return the bounceCount
	 */
	public int getBounceCount() {
		return bounceCount;
	}

	/**
	 * @param bounceCount
	 *            the bounceCount to set
	 */
	public void setBounceCount(int bounceCount) {
		this.bounceCount = bounceCount;
	}

	/**
	 * @return the unsubscriberCount
	 */
	public int getUnsubscriberCount() {
		return unsubscriberCount;
	}

	/**
	 * @param unsubscriberCount
	 *            the unsubscriberCount to set
	 */
	public void setUnsubscriberCount(int unsubscriberCount) {
		this.unsubscriberCount = unsubscriberCount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SubscriberList [subscriberListId=");
		builder.append(subscriberListId);
		builder.append(", orgId=");
		builder.append(orgId);
		builder.append(", subscriberListName=");
		builder.append(subscriberListName);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", createdTime=");
		builder.append(createdTime);
		builder.append(", lastModifiedTime=");
		builder.append(lastModifiedTime);
		builder.append(", activeCount=");
		builder.append(activeCount);
		builder.append(", bounceCount=");
		builder.append(bounceCount);
		builder.append(", unsubscriberCount=");
		builder.append(unsubscriberCount);
		builder.append("]");
		return builder.toString();
	}

}
