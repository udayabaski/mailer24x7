/**
 * 
 */
package com.nervytech.mailer24x7.model.service.api;

import com.nervytech.mailer24x7.domain.model.UserUuid;

/**
 * @author bsikkaya
 *
 */
public interface IUserUuidService {
	
	public void saveUuid(UserUuid reg);
	
	public String getUserId(String confirmationId);
	
	public void deleteUuid(String userId) ;
	
	public String getUuid(String userId) ;
}
