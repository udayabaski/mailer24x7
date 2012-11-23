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
@Repository("campaignsHomeBean")
public class CampaignsHomeBean {

	private List<CampaignBean> draftCampaigns;
	private List<CampaignBean> completedCampaigns;
	private List<CampaignBean> scheduledCampaigns;
	private String orgId;

	/**
	 * @return the draftCampaigns
	 */
	public List<CampaignBean> getDraftCampaigns() {
		return draftCampaigns;
	}

	/**
	 * @param draftCampaigns
	 *            the draftCampaigns to set
	 */
	public void setDraftCampaigns(List<CampaignBean> draftCampaigns) {
		this.draftCampaigns = draftCampaigns;
	}

	/**
	 * @return the completedCampaigns
	 */
	public List<CampaignBean> getCompletedCampaigns() {
		return completedCampaigns;
	}

	/**
	 * @param completedCampaigns
	 *            the completedCampaigns to set
	 */
	public void setCompletedCampaigns(List<CampaignBean> completedCampaigns) {
		this.completedCampaigns = completedCampaigns;
	}

	/**
	 * @return the scheduledCampaigns
	 */
	public List<CampaignBean> getScheduledCampaigns() {
		return scheduledCampaigns;
	}

	/**
	 * @param scheduledCampaigns
	 *            the scheduledCampaigns to set
	 */
	public void setScheduledCampaigns(List<CampaignBean> scheduledCampaigns) {
		this.scheduledCampaigns = scheduledCampaigns;
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
