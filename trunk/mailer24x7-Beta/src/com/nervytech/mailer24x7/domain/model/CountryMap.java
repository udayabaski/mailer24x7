/**
 * 
 */
package com.nervytech.mailer24x7.domain.model;

/**
 * @author bsikkaya
 * 
 */
public class CountryMap {

	private String country;
	private String city;
	private int region;
	private int xCoordinate;
	private int yCoordinate;

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

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the region
	 */
	public int getRegion() {
		return region;
	}

	/**
	 * @param region
	 *            the region to set
	 */
	public void setRegion(int region) {
		this.region = region;
	}

	/**
	 * @return the xCoordinate
	 */
	public int getxCoordinate() {
		return xCoordinate;
	}

	/**
	 * @param xCoordinate
	 *            the xCoordinate to set
	 */
	public void setxCoordinate(int xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	/**
	 * @return the yCoordinate
	 */
	public int getyCoordinate() {
		return yCoordinate;
	}

	/**
	 * @param yCoordinate
	 *            the yCoordinate to set
	 */
	public void setyCoordinate(int yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CountryMap [country=");
		builder.append(country);
		builder.append(", city=");
		builder.append(city);
		builder.append(", region=");
		builder.append(region);
		builder.append(", xCoordinate=");
		builder.append(xCoordinate);
		builder.append(", yCoordinate=");
		builder.append(yCoordinate);
		builder.append("]");
		return builder.toString();
	}

}
