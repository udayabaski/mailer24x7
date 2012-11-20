/**
 * 
 */
package com.nervytech.mailer24x7.spring.security.auth.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nervytech.mailer24x7.common.enums.UserRoleEnum;
import com.nervytech.mailer24x7.common.enums.UserStatusEnum;
import com.nervytech.mailer24x7.domain.model.User;
import com.nervytech.mailer24x7.model.dao.interfaces.IUserDAO;
import com.nervytech.mailer24x7.model.service.api.IUserService;

/**
 * @author ADMIN
 * 
 */
@Service("userDetailsService")
@SessionAttributes("user")
public class UserDetailsServiceImpl implements UserDetailsService {

	private static final Logger logger = LoggerFactory
			.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	private IUserService usrService;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {

		SessionUser springUser = null;
		User user = null;
		try {
			user = usrService.getUserByEmailId(username);
			if (user == null) {
				
				logger.info("User "+username+" not found in the system.");
				
				throw new UsernameNotFoundException(
						"Oops!!! Invalid username.");
				
			}

			Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			/*
			 * for (UserRoles role : user.getUserRoleses()) {
			 * authorities.add(new GrantedAuthorityImpl(role.getRoleName())); }
			 */
			authorities.add(new GrantedAuthorityImpl(UserRoleEnum.values()[user
					.getRole()].toString()));

			// User springUser = new User(userName, user.getPassword(), true,
			// true, true, true, authorities);

			boolean enabled = false;
			if (user.getStatus() == UserStatusEnum.ENABLED.getStatus()) {
				enabled = true;
			}

			springUser = new SessionUser(username, user.getPassword(),
					authorities, user.getOrgId(), user.getUserId(), enabled);

		} catch (Exception e) {
			
			logger.error("Error while checking if user exist : ", e);
			throw new UsernameNotFoundException("Error while checking if user exist !!!");
		}
		
		return springUser;
	}

	public static SessionUser currentUserDetails() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		if (authentication != null) {
			Object principal = authentication.getPrincipal();
			return principal instanceof UserDetails ? (SessionUser) principal
					: null;
		}
		return null;
	}

}