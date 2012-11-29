/**
 * 
 */
package com.nervytech.mailer24x7.model.dao.interfaces;

import java.util.List;

import com.nervytech.mailer24x7.domain.model.User;

/**
 * @author bsikkaya
 * 
 */
public interface IUserDAO {

	public List<User> getUserByEmailId(String emailId);

	public List<User> getUserByUserId(long userId);
	
	public long saveUser(User user);
	
	public void updateEncodedPassword(User user);
	
	public void enableUser(String userId);

	public void updateUser(User usr);
	
	public List<User> getUsers(String orgId);

}
