package com.nervytech.mailer24x7.spring.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nervytech.mailer24x7.common.enums.MessageTypeEnum;
import com.nervytech.mailer24x7.common.util.MailerUtil;
import com.nervytech.mailer24x7.domain.model.Organization;
import com.nervytech.mailer24x7.domain.model.User;
import com.nervytech.mailer24x7.model.service.api.IOrganizationService;
import com.nervytech.mailer24x7.model.service.api.IUserService;
import com.nervytech.mailer24x7.spring.bean.AdminBean;
import com.nervytech.mailer24x7.spring.form.OrganizationForm;
import com.nervytech.mailer24x7.spring.form.UserForm;
import com.nervytech.mailer24x7.spring.security.auth.user.SessionUser;
import com.nervytech.mailer24x7.spring.security.auth.user.UserDetailsServiceImpl;

@Controller
@RequestMapping("/usr/admin")
public class AdminController {

	private static final Logger logger = LoggerFactory
			.getLogger(AdminController.class);

	@Autowired
	private IOrganizationService orgService;

	@Autowired
	private IUserService usrService;

	@RequestMapping(method = RequestMethod.GET)
	public String showAdminPage(Map model, HttpServletRequest request) {

		SessionUser usr = UserDetailsServiceImpl.currentUserDetails();
		
		AdminBean bean = new AdminBean();
		
		List<User> usersList = usrService.getUsers(usr.getUserId()+"");
		
		bean.setUsersList(usersList);
		
		model.put("adminBean", bean);

		return "admin";
	}

	@RequestMapping(value = "/edit/org", method = RequestMethod.GET)
	public String showOrganization(Map model, HttpServletRequest request) {

		SessionUser usr = UserDetailsServiceImpl.currentUserDetails();

		Organization org = orgService.getOrganization(usr.getOrgId());

		OrganizationForm form = new OrganizationForm();
		form.setAddress(org.getAddress());
		form.setContactEmail(org.getContactEmail());
		form.setContactNo(org.getContactNo());
		form.setCountry(org.getCountry());
		form.setDisplayName(org.getDisplayName());
		form.setOrgName(org.getOrgName());
		form.setSenderEmail(org.getSenderEmail());
		form.setTimeZone(org.getTimeZone());
		form.setWebsite(org.getWebsite());

		model.put("organizationForm", form);

		return "organization";
	}

	@RequestMapping(value = "/update/org", method = RequestMethod.POST)
	public String updateOrganization(OrganizationForm orgForm,
			BindingResult result, Map model, HttpServletRequest request) {

		SessionUser usr = UserDetailsServiceImpl.currentUserDetails();

		Organization form = new Organization();
		form.setAddress(orgForm.getAddress());
		form.setContactEmail(orgForm.getContactEmail());
		form.setContactNo(orgForm.getContactNo());
		form.setCountry(orgForm.getCountry());
		form.setDisplayName(orgForm.getDisplayName());
		form.setOrgName(orgForm.getOrgName());
		form.setSenderEmail(orgForm.getSenderEmail());
		form.setTimeZone(orgForm.getTimeZone());
		form.setWebsite(orgForm.getWebsite());
		form.setUpdatedEmailId(usr.getUsername());
		form.setUpdatedTime(MailerUtil.FORMATTER_WITH_TIME.format(new Date()));
		form.setOrgId(usr.getOrgId());

		orgService.updateOrganization(form);

		orgForm.setMessage("Success");
		orgForm.setMessagType(MessageTypeEnum.SUCCESS.name());

		model.put("organizationForm", orgForm);

		return "organization";
	}

	@RequestMapping(value = "/edit/user", method = RequestMethod.GET)
	public String showUser(Map model, HttpServletRequest request) {

		SessionUser usr = UserDetailsServiceImpl.currentUserDetails();

		User user = usrService.getUserByUserId(usr.getUserId());

		UserForm form = new UserForm();
		form.setContactNumber(user.getContactNo());
		form.setDisplayName(user.getDisplayName());
		form.setFullName(user.getFullName());
		form.setLanguage(user.getLanguage());
		form.setEmailId(user.getEmailId());
		form.setTimeZone(user.getTimeZone());
		form.setOrgId(usr.getOrgId() + "");

		model.put("userForm", form);
		
		System.out.println("NNNNNNNNNNNNNNNNNNNNNNNNEEEEEEEEEEEEEEEEEWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW");

		return "newuser";
	}

	@RequestMapping(value = "/update/user", method = RequestMethod.POST)
	public String updateUser(UserForm usrForm, BindingResult result, Map model,
			HttpServletRequest request) {

		SessionUser usr = UserDetailsServiceImpl.currentUserDetails();

		User form = new User();
		form.setContactNo(usrForm.getContactNumber());
		form.setDisplayName(usrForm.getDisplayName());
		form.setFullName(usrForm.getFullName());
		form.setLanguage(usrForm.getLanguage());
		form.setEmailId(usrForm.getEmailId());
		form.setTimeZone(usrForm.getTimeZone());
		form.setUpdatedTime(MailerUtil.FORMATTER_WITH_TIME.format(new Date()));
		form.setUserId(usr.getUserId());

		usrService.updateUser(form);

		usrForm.setMessage("Success");
		usrForm.setMessageType(MessageTypeEnum.SUCCESS.name());

		model.put("userForm", usrForm);

		return "newuser";
	}
	
	@RequestMapping(value = "/new/user", method = RequestMethod.GET)
	public String newUser(Map model, HttpServletRequest request) {
		UserForm form = new UserForm();
		model.put("userForm", form);

		return "newuser";
	}

}
