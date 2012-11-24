/**
 * 
 */
package com.nervytech.mailer24x7.spring.bean;

import java.util.List;

import com.nervytech.mailer24x7.domain.model.User;

/**
 * @author bsikkaya
 *
 */
public class UserBean {
   
	private List<User> users;

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
}
