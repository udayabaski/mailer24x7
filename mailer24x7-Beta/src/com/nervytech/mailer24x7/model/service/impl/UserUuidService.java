/**
 * 
 */
package com.nervytech.mailer24x7.model.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.nervytech.mailer24x7.domain.model.UserUuid;
import com.nervytech.mailer24x7.model.dao.interfaces.IUserUuidDAO;
import com.nervytech.mailer24x7.model.service.api.IUserUuidService;

/**
 * @author bsikkaya
 * 
 */
public class UserUuidService implements IUserUuidService {

	private static final Logger logger = LoggerFactory
			.getLogger(UserUuidService.class);

	@Autowired
	private IUserUuidDAO usrUuidDAO;

	public void saveUuid(UserUuid usrUuid)  {
		try {
			usrUuidDAO.saveUuid(usrUuid);
		} catch (Exception e) {
			logger.error("Error while retreiving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public String getUserId(String confirmationId) {
		String userId = null;
		try {
			userId = usrUuidDAO.getUserId(confirmationId);
		} catch (Exception e) {
			logger.error("Error while retreiving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
		return userId;
	}
	
	public void deleteUuid(String userId) {
		try {
			usrUuidDAO.deleteUuid(userId);
		} catch (Exception e) {
			logger.error("Error while retreiving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public String getUuid(String userId) {
		String uuid = null;
		try {
			userId = usrUuidDAO.getUuid(uuid);
		} catch (Exception e) {
			logger.error("Error while retreiving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
		return uuid;
	}
}
