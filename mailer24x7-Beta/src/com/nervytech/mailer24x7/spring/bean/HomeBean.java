/**
 * 
 */
package com.nervytech.mailer24x7.spring.bean;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * @author bsikkaya
 * 
 */
@Repository("homeBean")
public class HomeBean {

	private List<CampaignBean> campaignsList;
	private List<ActivityBean> activitiesList;
	private String orgId;

	/**
	 * @return the campaignsList
	 */
	public List<CampaignBean> getCampaignsList() {
		return campaignsList;
	}

	/**
	 * @param campaignsList
	 *            the campaignsList to set
	 */
	public void setCampaignsList(List<CampaignBean> campaignsList) {
		this.campaignsList = campaignsList;
	}

	/**
	 * @return the activitiesList
	 */
	public List<ActivityBean> getActivitiesList() {
		return activitiesList;
	}

	/**
	 * @param activitiesList
	 *            the activitiesList to set
	 */
	public void setActivitiesList(List<ActivityBean> activitiesList) {
		this.activitiesList = activitiesList;
	}

	/**
	 * @return the orgId
	 */
	public String getOrgId() {
		return orgId;
	}

	/**
	 * @param orgId
	 *            the orgId to set
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

}
