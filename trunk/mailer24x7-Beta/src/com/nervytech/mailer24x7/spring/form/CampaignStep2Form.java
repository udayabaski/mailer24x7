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
@Repository("campaignStep2Form")
public class CampaignStep2Form {

	private CommonsMultipartFile htmlFileData;
	private CommonsMultipartFile zipFileData;
	private String textData;
	private String htmlData;
	private long campaignId;
	private int contentType;
	private String nextAction;

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
	 * @return the contentType
	 */
	public int getContentType() {
		return contentType;
	}

	/**
	 * @param contentType
	 *            the contentType to set
	 */
	public void setContentType(int contentType) {
		this.contentType = contentType;
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
	 * @return the textData
	 */
	public String getTextData() {
		return textData;
	}

	/**
	 * @param textData
	 *            the textData to set
	 */
	public void setTextData(String textData) {
		this.textData = textData;
	}

	/**
	 * @return the htmlData
	 */
	public String getHtmlData() {
		return htmlData;
	}

	/**
	 * @param htmlData
	 *            the htmlData to set
	 */
	public void setHtmlData(String htmlData) {
		this.htmlData = htmlData;
	}

}
