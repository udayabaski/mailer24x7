/**
 * 
 */
package com.nervytech.mailer24x7.spring.bean;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nervytech.mailer24x7.domain.model.User;

/**
 * @author ADMIN
 * 
 */
@Repository("adminBean")
public class AdminBean {

	private List<User> usersList;

	/**
	 * @return the usersList
	 */
	public List<User> getUsersList() {
		return usersList;
	}

	/**
	 * @param usersList
	 *            the usersList to set
	 */
	public void setUsersList(List<User> usersList) {
		this.usersList = usersList;
	}

}
