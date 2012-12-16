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
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nervytech.mailer24x7.common.enums.MessageTypeEnum;
import com.nervytech.mailer24x7.common.enums.UserStatusEnum;
import com.nervytech.mailer24x7.common.enums.UuidTypeEnum;
import com.nervytech.mailer24x7.common.util.MailerUtil;
import com.nervytech.mailer24x7.common.util.UUIDUtil;
import com.nervytech.mailer24x7.domain.model.User;
import com.nervytech.mailer24x7.domain.model.UserUuid;
import com.nervytech.mailer24x7.mailgun.MailgunSendUtil;
import com.nervytech.mailer24x7.model.service.api.IOrganizationService;
import com.nervytech.mailer24x7.model.service.api.IUserService;
import com.nervytech.mailer24x7.model.service.api.IUserUuidService;
import com.nervytech.mailer24x7.spring.form.LoginForm;
import com.nervytech.mailer24x7.spring.form.RegistrationForm;
import com.nervytech.mailer24x7.spring.form.UserForm;
import com.nervytech.mailer24x7.spring.security.auth.user.SessionUser;

@Controller
@RequestMapping(value = { "/pub/pwd", "/usr/pwd" })
public class PasswordController {

	private static final Logger logger = LoggerFactory
			.getLogger(PasswordController.class);

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private SaltSource saltSource;

	@Autowired
	private UserDetailsService userDetailsService;

	@Resource(name = "countryDropDown")
	private Map<String, String> countryMap;

	@Autowired
	private IUserService usrService;

	@Autowired
	private IUserUuidService usrUuidService;

	@Autowired
	private IOrganizationService orgService;

	@ModelAttribute("countryMap")
	public Map<String, String> populateCountryMap() {
		return this.countryMap;
	}

	@RequestMapping(value = "/forgot", method = RequestMethod.GET)
	public String forgotPassword(Map model, HttpServletRequest request) {

		RegistrationForm regForm = new RegistrationForm();
		regForm.setAction("resetmail");

		model.put("registrationForm", regForm);

		return "forgotpwd";
	}

	@RequestMapping(value = "/change", method = RequestMethod.GET)
	public String changePassword(Map model, HttpServletRequest request) {

		UserForm form = new UserForm();

		model.put("userForm", form);

		return "changepwd";
	}

	@RequestMapping(value = "/update/new", method = RequestMethod.POST)
	public String updateNewPwd(UserForm userForm, BindingResult result,
			Map model, HttpServletRequest request) {

		ValidationUtils.rejectIfEmptyOrWhitespace(result, "oldPassword",
				"NotEmpty.profileForm.oldPassword",
				"Old Password must not be empty.");
		ValidationUtils.rejectIfEmptyOrWhitespace(result, "password",
				"NotEmpty.profileForm.password", "Password must not be empty.");
		ValidationUtils.rejectIfEmptyOrWhitespace(result, "confirmPassword",
				"NotEmpty.profileForm.confirmPassword",
				"Confirm password must not be empty.");

		if (!(userForm.getPassword()).equals(userForm.getConfirmPassword())) {
			result.rejectValue("confirmPassword",
					"matchingPassword.profileForm.password",
					"Password and Confirm Password does not match.");
		}

		if (result.hasErrors()) {
			return "resetpwd";
		}

		String userId = userForm.getUserId();

		User user = usrService.getUserByUserId(Long.parseLong(userId));

		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		SessionUser userDetails = new SessionUser(user.getEmailId(),
				user.getPassword(), authorities, user.getOrgId(),
				user.getUserId(), true, user.getFullName());

		String oldEncodedPassword = passwordEncoder.encodePassword(
				userForm.getOldPassword(), saltSource.getSalt(userDetails));
		String actualPassword = user.getPassword();

		if (!oldEncodedPassword.equals(actualPassword)) {
			result.rejectValue("oldPassword",
					"matchingPassword.profileForm.oldPassword",
					"Old Password does not match.");
		}

		user.setPassword(passwordEncoder.encodePassword(userForm.getPassword(),
				saltSource.getSalt(userDetails)));

		usrService.updateEncodedPassword(user);

		UserForm form = new UserForm();
		form.setMessage("Password updated successfully");
		form.setMessageType(MessageTypeEnum.SUCCESS.name());

		model.put("userForm", form);

		return "changepwd";

	}

	@RequestMapping(value = "/reset", method = RequestMethod.POST)
	public String sendResetMail(RegistrationForm regForm, BindingResult result,
			Map model, HttpServletRequest request) {

		ValidationUtils.rejectIfEmptyOrWhitespace(result, "emailId",
				"NotEmpty.profileForm.emailId", "Email must not be empty.");

		if (result.hasErrors()) {
			return "forgotpwd";
		}

		User user = usrService.getUserByEmailId(regForm.getEmailId());
		if (user == null) {
			result.rejectValue("emailId",
					"matchingPassword.profileForm.emailId",
					"Oops !!!, The user does not exist in our system.");
			return "forgotpwd";
		} else if (user.getStatus() == UserStatusEnum.DISABLED.getStatus()) {
			result.rejectValue("emailId",
					"matchingPassword.profileForm.emailId",
					"Oops !!!, The user is not yet Activated.");
			return "forgotpwd";
		}

		UserUuid uuid = new UserUuid();
		uuid.setUserId(user.getUserId());
		uuid.setUuid(UUIDUtil.getTimeUUID().toString() + "-"
				+ System.currentTimeMillis());
		uuid.setCreatedTime(MailerUtil.FORMATTER_WITH_TIME.format(new Date()));
		uuid.setType(UuidTypeEnum.RESET.getStatus());

		usrUuidService.saveUuid(uuid);

		logger.info("Password Reset mail UUID : " + uuid.getUuid() + " User : "
				+ user.getEmailId());

		String resetUrl = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + "/"
				+ request.getContextPath() + "/"
				+ "pub/pwd/reset/confirm/email/EMAIL_ID/id/RESET_ID";
		resetUrl = resetUrl.replaceAll("EMAIL_ID", regForm.getEmailId());
		resetUrl = resetUrl.replaceAll("RESET_ID", uuid.getUuid());

		String content = "Hi "
				+ user.getFullName()
				+ "\n Please click the following link for resetting your password.\n "
				+ resetUrl;

		logger.debug("Sendig mail for password reset : " + user.getEmailId());

		MailgunSendUtil.sendCampaignTestMail(-1l, -1l,
				MailerUtil.CONFIRMATION_MAIL_FROM, regForm.getEmailId(),
				MailerUtil.RESET_MAIL_SUBJECT, null, content, null);

		regForm.setMessage(MailerUtil.RESET_MAIL_SENT_MESSAGE);

		return "forgotpwd";
	}

	@RequestMapping(value = "/reset/confirm/email/{emailId}/id/{uuId}", method = RequestMethod.GET)
	public String resetPassword(@PathVariable String emailId,
			@PathVariable String uuId, Map model) {

		String userId = null;

		try {
			userId = usrUuidService.getUserId(uuId);
		} catch (Exception e) {

			logger.warn("Invalid reset id was sent !!!");

			// The reset URL is not valid. Could be a URL hacking !!!
			LoginForm loginForm = new LoginForm();
			loginForm.setMessage("Invalid request.");
			loginForm.setMessageType(MessageTypeEnum.ERROR.name());
			model.put("loginForm", loginForm);
			return "login";
		}

		RegistrationForm form = new RegistrationForm();
		form.setUserId(userId);

		model.put("registrationForm", form);

		return "resetpwd";
	}

	@RequestMapping(value = "/update/pwd", method = RequestMethod.POST)
	public String updatePassword(RegistrationForm regForm,
			BindingResult result, Map model) {

		ValidationUtils.rejectIfEmptyOrWhitespace(result, "password",
				"NotEmpty.profileForm.password", "Password must not be empty.");
		ValidationUtils.rejectIfEmptyOrWhitespace(result, "confirmPassword",
				"NotEmpty.profileForm.confirmPassword",
				"Confirm password must not be empty.");

		if (!(regForm.getPassword()).equals(regForm.getConfirmPassword())) {
			result.rejectValue("confirmPassword",
					"matchingPassword.profileForm.password",
					"Password and Confirm Password does not match.");
		}

		if (result.hasErrors()) {
			return "resetpwd";
		}

		String userId = regForm.getUserId();

		User user = usrService.getUserByUserId(Long.parseLong(userId));

		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		SessionUser userDetails = new SessionUser(user.getEmailId(),
				user.getPassword(), authorities, user.getOrgId(),
				user.getUserId(), true, user.getFullName());

		user.setPassword(passwordEncoder.encodePassword(regForm.getPassword(),
				saltSource.getSalt(userDetails)));

		usrService.updateEncodedPassword(user);

		usrUuidService.deleteUuid(userId);

		LoginForm loginForm = new LoginForm();
		loginForm.setMessage(MailerUtil.PASSWORD_RESET_MESSAGE);
		loginForm.setMessageType(MessageTypeEnum.SUCCESS.name());
		model.put("loginForm", loginForm);
		return "login";
	}

	/*
	 * @RequestMapping(params = "action=updatepwd", method = RequestMethod.POST)
	 * 
	 * @Transactional(propagation = Propagation.REQUIRES_NEW) public String
	 * updatePwd(UserForm profileForm, BindingResult result, Map model,
	 * HttpServletRequest request) {
	 * 
	 * ValidationUtils.rejectIfEmptyOrWhitespace(result, "password",
	 * "NotEmpty.profileForm.password", "Password must not be empty.");
	 * ValidationUtils.rejectIfEmptyOrWhitespace(result, "confirmPassword",
	 * "NotEmpty.profileForm.confirmPassword",
	 * "Confirm password must not be empty.");
	 * 
	 * if (!(profileForm.getPassword()).equals(profileForm
	 * .getConfirmPassword())) { result.rejectValue("confirmPassword",
	 * "matchingPassword.profileForm.password",
	 * "Password and Confirm Password does not match."); }
	 * 
	 * if (result.hasErrors()) { return "resetpwd"; }
	 * 
	 * String userId = profileForm.getUserId();
	 * 
	 * User user = usrService.getUserByUserId(Long.parseLong(userId));
	 * 
	 * Collection<GrantedAuthority> authorities = new
	 * ArrayList<GrantedAuthority>(); SessionUser userDetails = new
	 * SessionUser(user.getEmailId(), user.getPassword(), authorities,
	 * user.getOrgId(), user.getUserId(), true);
	 * 
	 * user.setPassword(passwordEncoder.encodePassword(
	 * profileForm.getPassword(), saltSource.getSalt(userDetails)));
	 * 
	 * usrService.updateEncodedPassword(user);
	 * 
	 * usrUuidService.deleteUuid(userId);
	 * 
	 * logger.info("Password is reset : User ID : " + userId);
	 * 
	 * LoginForm loginForm = new LoginForm();
	 * loginForm.setMessage(MailerUtil.PASSWORD_RESET_MESSAGE);
	 * loginForm.setMessageType(MessageTypeEnum.ERROR.name());
	 * model.put("loginForm", loginForm); return "login"; }
	 */

}
