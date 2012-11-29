/**
 * 
 */
package com.nervytech.mailer24x7.spring.bean;

import org.springframework.stereotype.Repository;

/**
 * @author bsikkaya
 * 
 */
@Repository("linkBean")
public class LinkBean {

	private String linkName;
	private int uniqueLinks;
	private int totalClicks;

	/**
	 * @return the linkName
	 */
	public String getLinkName() {
		return linkName;
	}

	/**
	 * @param linkName
	 *            the linkName to set
	 */
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	/**
	 * @return the uniqueLinks
	 */
	public int getUniqueLinks() {
		return uniqueLinks;
	}

	/**
	 * @param uniqueLinks
	 *            the uniqueLinks to set
	 */
	public void setUniqueLinks(int uniqueLinks) {
		this.uniqueLinks = uniqueLinks;
	}

	/**
	 * @return the totalClicks
	 */
	public int getTotalClicks() {
		return totalClicks;
	}

	/**
	 * @param totalClicks
	 *            the totalClicks to set
	 */
	public void setTotalClicks(int totalClicks) {
		this.totalClicks = totalClicks;
	}

}