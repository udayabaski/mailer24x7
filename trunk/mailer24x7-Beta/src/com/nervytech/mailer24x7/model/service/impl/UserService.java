/**
 * 
 */
package com.nervytech.mailer24x7.model.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nervytech.mailer24x7.domain.model.User;
import com.nervytech.mailer24x7.model.dao.impl.UserDAO;
import com.nervytech.mailer24x7.model.dao.interfaces.IUserDAO;
import com.nervytech.mailer24x7.model.service.api.IUserService;

/**
 * @author bsikkaya
 * 
 */
@Service
public class UserService implements IUserService {

	private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);

	@Autowired
	private IUserDAO userDAO;

	@Transactional
	public User getUserByEmailId(String emailId) {
		User user = null;
		try {
			List<User> users = userDAO.getUserByEmailId(emailId);
			if (users != null && users.size() > 0)
				user = users.get(0);
		} catch (Exception e) {
			logger.error("Error while retreiving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}

		return user;
	}

	@Transactional
	public User getUserByUserId(long userId) {
		User user = null;
		try {
			List<User> users = userDAO.getUserByUserId(userId);
			if (users != null && users.size() > 0)
				user = users.get(0);
		} catch (Exception e) {
			logger.error("Error while retreiving user detail. ", e);
			throw new RuntimeException(e.getMessage());
		}

		return user;
	}

	@Transactional
	public long saveUser(User user) {
		long userId = -1;
		try {
			userId = userDAO.saveUser(user);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
		return userId;
	}

	@Transactional
	public void updateEncodedPassword(User user) {
		try {
			userDAO.updateEncodedPassword(user);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
	}

	@Transactional
	public void enableUser(String userId) throws RuntimeException {
		try {
			userDAO.enableUser(userId);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public void updateUser(User usr) {
		try {
			userDAO.updateUser(usr);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public List<User> getUsers(String orgId) {
		List<User> usersList = null;
		try {
			usersList = userDAO.getUsers(orgId);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
		return usersList;
	}

}
