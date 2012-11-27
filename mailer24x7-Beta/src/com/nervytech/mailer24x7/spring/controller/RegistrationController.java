package com.nervytech.mailer24x7.spring.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nervytech.mailer24x7.common.enums.MessageTypeEnum;
import com.nervytech.mailer24x7.common.enums.OrgStatusEnum;
import com.nervytech.mailer24x7.common.enums.UserRoleEnum;
import com.nervytech.mailer24x7.common.enums.UserStatusEnum;
import com.nervytech.mailer24x7.common.enums.UuidTypeEnum;
import com.nervytech.mailer24x7.common.util.MailerUtil;
import com.nervytech.mailer24x7.common.util.UUIDUtil;
import com.nervytech.mailer24x7.domain.model.Organization;
import com.nervytech.mailer24x7.domain.model.User;
import com.nervytech.mailer24x7.domain.model.UserUuid;
import com.nervytech.mailer24x7.mailgun.MailgunSendUtil;
import com.nervytech.mailer24x7.model.service.api.IOrganizationService;
import com.nervytech.mailer24x7.model.service.api.IUserService;
import com.nervytech.mailer24x7.model.service.api.IUserUuidService;
import com.nervytech.mailer24x7.spring.form.LoginForm;
import com.nervytech.mailer24x7.spring.form.RegistrationForm;
import com.nervytech.mailer24x7.spring.security.auth.user.SessionUser;
import com.nervytech.mailer24x7.spring.validator.RegistrationValidator;

@Controller
@RequestMapping("/reg/join")
public class RegistrationController {

	private static final Logger logger = LoggerFactory
			.getLogger(RegistrationController.class);

	@Autowired
	private RegistrationValidator registrationValidation;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private SaltSource saltSource;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private IUserService usrService;

	@Autowired
	private IUserUuidService usrUuidService;

	@Autowired
	private IOrganizationService orgService;

	@Resource(name = "countryDropDown")
	private Map<String, String> countryMap;

	@ModelAttribute("countryMap")
	public Map<String, String> populateCountryMap() {
		return this.countryMap;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String showForm(Map model, HttpServletRequest request) {
		// String userCountry =
		// getCountryFromIpAddress(request.getRemoteAddr());
		RegistrationForm regForm = new RegistrationForm();
		// regForm.setCountry(userCountry);

		model.put("registrationForm", regForm);

		return "register";
	}
	
	@RequestMapping(value = "/confirm/email/{emailId}/id/{uuId}",method = RequestMethod.GET)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public String confirmUser(@PathVariable String emailId,@PathVariable String uuId,
			Map model) {
		String userId = null;
		try {
			userId = usrUuidService.getUserId(uuId);
		} catch (Exception e) {

			// The confirmation URL is not a valid one ..... Could be a hacking
			// !!!
			logger.warn("Invalid confimration id was sent !!!");

			LoginForm loginForm = new LoginForm();
			loginForm.setMessage("Invalid request.");
			loginForm.setMessageType(MessageTypeEnum.ERROR.name());
			model.put("loginForm", loginForm);

			return "login";
		}

		usrService.enableUser(userId);

		usrUuidService.deleteUuid(userId);

		logger.info("Account is activated for the user : ID : " + userId);

		LoginForm loginForm = new LoginForm();
		loginForm.setMessage(MailerUtil.ACCOUNT_ACTIVATION_MESSAGE);
		loginForm.setMessageType(MessageTypeEnum.SUCCESS.name());
		model.put("loginForm", loginForm);
		return "login";

	}
	
	@RequestMapping(value = "/confirm/resend/usr/{uuId}", method = RequestMethod.GET)
	public String reSendConfirmationMail(@PathVariable String uuId, Map model,
			HttpServletRequest request) {

		String userId = null;
		
		try {
			userId = usrUuidService.getUserId(uuId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(userId == null){
			RegistrationForm regForm = new RegistrationForm();
			
			regForm.setMessage("Invalid confirmation Id.");
			regForm.setMessageType(MessageTypeEnum.ERROR.name());
			model.put("registrationForm", regForm);

			return "register";

		}
		
		User usr = usrService.getUserByUserId(Long.parseLong(userId));

		String confirmationUrl = MailerUtil.CONFIRMATION_MAIL_URL;
		confirmationUrl = confirmationUrl.replaceAll("EMAIL_ID",
				usr.getEmailId());
		confirmationUrl = confirmationUrl.replaceAll("CONFIRM_ID",
				uuId);

		String content = "Hi "
				+ usr.getFullName()
				+ "\n Please click the following link for activating your account.\n "
				+ confirmationUrl;

		logger.debug("Sending registration confirmation mail !!!");

		MailgunSendUtil.sendCampaignTestMail(-1l, -1l,
				MailerUtil.CONFIRMATION_MAIL_FROM, usr.getEmailId(),
				MailerUtil.CONFIRMATION_MAIL_SUBJECT, null, content, null);

		RegistrationForm regForm = new RegistrationForm();

		regForm.setUuId(uuId);
		
		regForm.setMessageType(MessageTypeEnum.SUCCESS.name());

		model.put("registrationForm", regForm);

		return "register";
	}


	@RequestMapping(method = RequestMethod.POST)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public String registerUser(RegistrationForm regForm, BindingResult result,
			Map model) {

		registrationValidation.validate(regForm, result);

		if (result.hasErrors()) {
			return "register";
		}

		Organization checkOrg = orgService.getOrganization(
				regForm.getCompany(), regForm.getCountry());
		if (checkOrg != null) {
			result.rejectValue("company",
					"NotEmpty.registrationForm.company",
					"This company with the selected country is already registered.");
			return "register";
		}

		User user = usrService.getUserByEmailId(regForm.getEmailId());
		if (user != null) {
			result.rejectValue("emailId", "NotEmpty.registrationForm.emailId",
					"This User is already exist.");
			return "register";
		}

		Date currentDate = new Date();

		Organization org = new Organization();

		org.setCountry(regForm.getCountry());
		org.setDisplayName(regForm.getCompany());
		org.setOrgName(regForm.getCompany());
		org.setSenderEmail(regForm.getEmailId()); // TODO : Arun - The same
													// Created Email id is set
													// to sender Email.

		// As user is disabled while registering an org, we can enable it by
		// default ...
		org.setStatus(OrgStatusEnum.ENABLED.getStatus());
		// org.setTimeZone(regForm.getTimeZone()); // TODO : Arun - We are not
		// getting timezone from the user. Should we calculate based on the
		// location or should we change the UI.
		org.setWebsite(regForm.getWebsite()); // TODO : Arun - Do we need to
												// check if the website entered
												// is a valid one.
		org.setCreatedEmailId(regForm.getEmailId());
		org.setContactEmail(regForm.getEmailId()); // TODO : Arun - The same
													// Created Email id is set
													// to Contact Email.
		org.setCreatedTime(MailerUtil.FORMATTER_WITH_TIME.format(currentDate));

		long orgId = orgService.saveOrganization(org);

		user = new User();

		user.setOrgId(orgId);
		user.setDisplayName(regForm.getFullName());
		user.setEmailId(regForm.getEmailId());
		user.setFullName(regForm.getFullName());
		user.setPassword(regForm.getPassword());
		user.setRole(UserRoleEnum.ADMIN.getRole());
		user.setStatus(UserStatusEnum.DISABLED.getStatus());
		user.setCreatedTime(MailerUtil.FORMATTER_WITH_TIME.format(currentDate));

		usrService.saveUser(user);

		UserDetails userDetails = userDetailsService.loadUserByUsername(regForm
				.getEmailId());

		long userId = ((SessionUser) userDetails).getUserId();

		user.setPassword(passwordEncoder.encodePassword(regForm.getPassword(),
				saltSource.getSalt(userDetails)));

		user.setUserId(userId);

		usrService.updateEncodedPassword(user);

		UserUuid uuid = new UserUuid();

		uuid.setUserId(userId);
		uuid.setUuid(UUIDUtil.getTimeUUID().toString());
		uuid.setCreatedTime(MailerUtil.FORMATTER_WITH_TIME.format(currentDate));
		uuid.setType(UuidTypeEnum.CONFIRM.getStatus());

		usrUuidService.saveUuid(uuid);

		String confirmationUrl = MailerUtil.CONFIRMATION_MAIL_URL;
		confirmationUrl = confirmationUrl.replaceAll("EMAIL_ID",
				user.getEmailId());
		confirmationUrl = confirmationUrl.replaceAll("CONFIRM_ID",
				uuid.getUuid());

		logger.info("Confirmation ID : " + uuid.getUuid() + " User ID "
				+ regForm.getEmailId());

		// TODO : Arun - Content needs to be modified. It can be a HTML mail as
		// well ...
		String content = "Hi "
				+ regForm.getFullName()
				+ ","
				+ "\n Please click the following lilnk for activating your account.\n "
				+ confirmationUrl;

		logger.debug("Sending registration confirmation mail : user "
				+ regForm.getEmailId() + " organization "
				+ regForm.getCompany());

		try {
			MailgunSendUtil.sendCampaignTestMail(-1l, -1l,
					MailerUtil.CONFIRMATION_MAIL_FROM, regForm.getEmailId(),
					MailerUtil.CONFIRMATION_MAIL_SUBJECT, null, content, null);

		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * autoLogin(regForm.getEmailId(),regForm.getPassword(),user);
		 * 
		 * return "redirect:/usr/home.form";
		 */
		regForm = new RegistrationForm();

		regForm.setUuId(uuid.getUuid() + "");
		
		regForm.setMessageType(MessageTypeEnum.SUCCESS.name());

		model.put("registrationForm", regForm);

		return "register";
	}

	/**
	 * Automatic login after successful registration.
	 * 
	 * @param password
	 * @param user
	 * 
	 * @param username
	 * @param users
	 */
	// @Transactional(propagation = Propagation.REQUIRES_NEW)
	private boolean autoLogin(String username, String password, User user) {
		try {
			Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new GrantedAuthorityImpl("ADMIN"));

			// org.springframework.security.core.userdetails.User springUser =
			// new org.springframework.security.core.userdetails.User(username,
			// password, true, true, true, true, authorities);

			SessionUser springUser = new SessionUser(username,
					user.getPassword(), authorities, user.getOrgId(),
					user.getUserId(), true, user.getFullName());

			Authentication authentication = new UsernamePasswordAuthenticationToken(
					springUser, springUser.getPassword(),
					springUser.getAuthorities());

			// Place the new Authentication object in the security context.
			SecurityContextHolder.getContext()
					.setAuthentication(authentication);
		} catch (Exception e) {
			e.printStackTrace();
			SecurityContextHolder.getContext().setAuthentication(null);
			// logger.error("Exception", e);
			return false;
		}
		return true;
	}

}
