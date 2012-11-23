/**
 * 
 */
package com.nervytech.mailer24x7.spring.form;

import org.springframework.stereotype.Repository;

/**
 * @author ADMIN
 * 
 */
@Repository("campaignStep2EditorForm")
public class CampaignStep2EditorForm {

	private String textData;
	private String htmlData;
	private String nextAction;
	private long campaignId;
	private String editorType;

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
	 * @return the editorType
	 */
	public String getEditorType() {
		return editorType;
	}

	/**
	 * @param editorType
	 *            the editorType to set
	 */
	public void setEditorType(String editorType) {
		this.editorType = editorType;
	}

}