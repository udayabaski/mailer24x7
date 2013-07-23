/**
 * 
 */
package com.nervytech.mailer24x7.reports.bean;

/**
 * @author bsikkaya
 * 
 */
public class BarChartReportsBean {

	private int opened;
	private int clicked;
	private int unsubscribed;
	private int bounced;
	private String country;

	/**
	 * @return the opened
	 */
	public int getOpened() {
		return opened;
	}

	/**
	 * @param opened
	 *            the opened to set
	 */
	public void setOpened(int opened) {
		this.opened = opened;
	}

	/**
	 * @return the clicked
	 */
	public int getClicked() {
		return clicked;
	}

	/**
	 * @param clicked
	 *            the clicked to set
	 */
	public void setClicked(int clicked) {
		this.clicked = clicked;
	}

	/**
	 * @return the unsubscribed
	 */
	public int getUnsubscribed() {
		return unsubscribed;
	}

	/**
	 * @param unsubscribed
	 *            the unsubscribed to set
	 */
	public void setUnsubscribed(int unsubscribed) {
		this.unsubscribed = unsubscribed;
	}

	/**
	 * @return the bounced
	 */
	public int getBounced() {
		return bounced;
	}

	/**
	 * @param bounced
	 *            the bounced to set
	 */
	public void setBounced(int bounced) {
		this.bounced = bounced;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BarChartReportsBean other = (BarChartReportsBean) obj;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		return true;
	}
	
}
