/**
 * 
 */
package com.nervytech.mailer24x7.spring.form;

import org.springframework.stereotype.Repository;

/**
 * @author ADMIN
 * 
 */
@Repository("campaignDeliveryForm")
public class CampaignDeliveryForm {

	private String campaignId;
	private String confirmationMailIdNow;
	private String confirmationMailIdLater;
	private String nextAction;
	private String date;
	private String timeHour;
	private String timeMinute;
	private String timezone;
	private String deliveryType;

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

	/**
	 * @return the confirmationMailIdNow
	 */
	public String getConfirmationMailIdNow() {
		return confirmationMailIdNow;
	}

	/**
	 * @param confirmationMailIdNow
	 *            the confirmationMailIdNow to set
	 */
	public void setConfirmationMailIdNow(String confirmationMailIdNow) {
		this.confirmationMailIdNow = confirmationMailIdNow;
	}

	/**
	 * @return the confirmationMailIdLater
	 */
	public String getConfirmationMailIdLater() {
		return confirmationMailIdLater;
	}

	/**
	 * @param confirmationMailIdLater
	 *            the confirmationMailIdLater to set
	 */
	public void setConfirmationMailIdLater(String confirmationMailIdLater) {
		this.confirmationMailIdLater = confirmationMailIdLater;
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

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the timeHour
	 */
	public String getTimeHour() {
		return timeHour;
	}

	/**
	 * @param timeHour
	 *            the timeHour to set
	 */
	public void setTimeHour(String timeHour) {
		this.timeHour = timeHour;
	}

	/**
	 * @return the timeMinute
	 */
	public String getTimeMinute() {
		return timeMinute;
	}

	/**
	 * @param timeMinute
	 *            the timeMinute to set
	 */
	public void setTimeMinute(String timeMinute) {
		this.timeMinute = timeMinute;
	}

	/**
	 * @return the timezone
	 */
	public String getTimezone() {
		return timezone;
	}

	/**
	 * @param timezone
	 *            the timezone to set
	 */
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	/**
	 * @return the deliveryType
	 */
	public String getDeliveryType() {
		return deliveryType;
	}

	/**
	 * @param deliveryType
	 *            the deliveryType to set
	 */
	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

}