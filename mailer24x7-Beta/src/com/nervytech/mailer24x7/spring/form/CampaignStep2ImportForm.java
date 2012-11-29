/**
 * 
 */
package com.nervytech.mailer24x7.spring.form;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @author ADMIN
 * 
 */
@Repository("campaignStep2ImportForm")
public class CampaignStep2ImportForm {

	private CommonsMultipartFile htmlFileData;
	private CommonsMultipartFile zipFileData;
	private String nextAction;
	private long campaignId;
	private String toPage;

	/**
	 * @return the htmlFileData
	 */
	public CommonsMultipartFile getHtmlFileData() {
		return htmlFileData;
	}

	/**
	 * @param htmlFileData
	 *            the htmlFileData to set
	 */
	public void setHtmlFileData(CommonsMultipartFile htmlFileData) {
		this.htmlFileData = htmlFileData;
	}

	/**
	 * @return the zipFileData
	 */
	public CommonsMultipartFile getZipFileData() {
		return zipFileData;
	}

	/**
	 * @param zipFileData
	 *            the zipFileData to set
	 */
	public void setZipFileData(CommonsMultipartFile zipFileData) {
		this.zipFileData = zipFileData;
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
	 * @return the toPage
	 */
	public String getToPage() {
		return toPage;
	}

	/**
	 * @param toPage
	 *            the toPage to set
	 */
	public void setToPage(String toPage) {
		this.toPage = toPage;
	}

}