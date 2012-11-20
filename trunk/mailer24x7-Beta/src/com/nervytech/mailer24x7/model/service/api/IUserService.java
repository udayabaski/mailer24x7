/**
 * 
 */
package com.nervytech.mailer24x7.model.service.api;

import com.nervytech.mailer24x7.domain.model.User;

/**
 * @author bsikkaya
 *
 */
public interface IUserService {
	
	public User getUserByEmailId(String emailId) ;

	public User getUserByUserId(long userId) ;
	
	public long saveUser(User user) ;
	
	public void updateEncodedPassword(User user) ;
	
	public void enableUser(String userId) ;

}
