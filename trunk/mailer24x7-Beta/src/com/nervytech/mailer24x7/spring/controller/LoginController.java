package com.nervytech.mailer24x7.spring.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nervytech.mailer24x7.common.enums.MessageTypeEnum;
import com.nervytech.mailer24x7.spring.form.LoginForm;

@Controller
@RequestMapping("/auth/login")
public class LoginController {

	@RequestMapping(method = RequestMethod.GET)
	public String showHome(Map model,HttpServletRequest request) {
		
		LoginForm loginForm = new LoginForm();
		
		if(request.getParameter("message") !=null && request.getParameter("message").equalsIgnoreCase("failed")) {
		   loginForm.setMessage("Invalid Email Address or Password");
		   loginForm.setMessageType(MessageTypeEnum.ERROR.name());
		}
		
		model.put("loginForm", loginForm);
		
		return "login";
	}

}
