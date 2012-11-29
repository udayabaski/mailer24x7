/**
 * 
 */
package com.nervytech.mailer24x7.spring.bean;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nervytech.mailer24x7.domain.model.SubscriberIdStatus;
import com.nervytech.mailer24x7.domain.model.SubscriberList;

/**
 * @author ADMIN
 * 
 */
@Repository("subscriberHomeBean")
public class SubscriberHomeBean {

	private List<SubscriberList> subscriberList;

	private SubscriberList subscriberGroup;

	private List<SubscriberIdStatus> activeSubscribers;

	private List<SubscriberIdStatus> unsubscribedSubscribers;

	private List<SubscriberIdStatus> bouncedSubscribers;

	private int subscriberType;

	private String[] activeSubscribersSelected;

	private String[] bouncedSubscribersSelected;

	private String[] unsubscribedSubscribersSelected;

	private long subscriberListId;

	private int moveTo;

	private boolean subscriberListNotEmpty;

	public boolean isSubscriberListNotEmpty() {
		return subscriberListNotEmpty;
	}

	public void setSubscriberListNotEmpty(boolean subscriberListNotEmpty) {
		this.subscriberListNotEmpty = subscriberListNotEmpty;
	}

	public List<SubscriberList> getSubscriberList() {
		return subscriberList;
	}

	public void setSubscriberList(List<SubscriberList> subscriberList) {
		this.subscriberList = subscriberList;
	}

	public SubscriberList getSubscriberGroup() {
		return subscriberGroup;
	}

	public void setSubscriberGroup(SubscriberList subscriberGroup) {
		this.subscriberGroup = subscriberGroup;
	}

	public List<SubscriberIdStatus> getActiveSubscribers() {
		return activeSubscribers;
	}

	public void setActiveSubscribers(List<SubscriberIdStatus> activeSubscribers) {
		this.activeSubscribers = activeSubscribers;
	}

	public List<SubscriberIdStatus> getUnsubscribedSubscribers() {
		return unsubscribedSubscribers;
	}

	public void setUnsubscribedSubscribers(
			List<SubscriberIdStatus> unsubscribedSubscribers) {
		this.unsubscribedSubscribers = unsubscribedSubscribers;
	}

	public List<SubscriberIdStatus> getBouncedSubscribers() {
		return bouncedSubscribers;
	}

	public void setBouncedSubscribers(
			List<SubscriberIdStatus> bouncedSubscribers) {
		this.bouncedSubscribers = bouncedSubscribers;
	}

	/**
	 * @return the subscriberType
	 */
	public int getSubscriberType() {
		return subscriberType;
	}

	/**
	 * @param subscriberType
	 *            the subscriberType to set
	 */
	public void setSubscriberType(int subscriberType) {
		this.subscriberType = subscriberType;
	}

	/**
	 * @return the activeSubscribersSelected
	 */
	public String[] getActiveSubscribersSelected() {
		return activeSubscribersSelected;
	}

	/**
	 * @param activeSubscribersSelected
	 *            the activeSubscribersSelected to set
	 */
	public void setActiveSubscribersSelected(String[] activeSubscribersSelected) {
		this.activeSubscribersSelected = activeSubscribersSelected;
	}

	/**
	 * @return the bouncedSubscribersSelected
	 */
	public String[] getBouncedSubscribersSelected() {
		return bouncedSubscribersSelected;
	}

	/**
	 * @param bouncedSubscribersSelected
	 *            the bouncedSubscribersSelected to set
	 */
	public void setBouncedSubscribersSelected(
			String[] bouncedSubscribersSelected) {
		this.bouncedSubscribersSelected = bouncedSubscribersSelected;
	}

	/**
	 * @return the unsubscribedSubscribersSelected
	 */
	public String[] getUnsubscribedSubscribersSelected() {
		return unsubscribedSubscribersSelected;
	}

	/**
	 * @param unsubscribedSubscribersSelected
	 *            the unsubscribedSubscribersSelected to set
	 */
	public void setUnsubscribedSubscribersSelected(
			String[] unsubscribedSubscribersSelected) {
		this.unsubscribedSubscribersSelected = unsubscribedSubscribersSelected;
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
	 * @return the moveTo
	 */
	public int getMoveTo() {
		return moveTo;
	}

	/**
	 * @param moveTo
	 *            the moveTo to set
	 */
	public void setMoveTo(int moveTo) {
		this.moveTo = moveTo;
	}

}
