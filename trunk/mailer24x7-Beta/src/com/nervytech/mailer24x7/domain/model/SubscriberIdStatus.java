/**
 * 
 */
package com.nervytech.mailer24x7.domain.model;

/**
 * @author bsikkaya
 * 
 */
public class SubscriberIdStatus {

	private long statusId;
	private long subscriberListId;
	private String emailId;
	private int status;

	/**
	 * @return the statusId
	 */
	public long getStatusId() {
		return statusId;
	}

	/**
	 * @param statusId
	 *            the statusId to set
	 */
	public void setStatusId(long statusId) {
		this.statusId = statusId;
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
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId
	 *            the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SubscriberIdStatus [statusId=");
		builder.append(statusId);
		builder.append(", subscriberListId=");
		builder.append(subscriberListId);
		builder.append(", emailId=");
		builder.append(emailId);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}

}
