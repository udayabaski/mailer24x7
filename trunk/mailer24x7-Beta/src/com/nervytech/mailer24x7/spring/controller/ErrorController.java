/**
 * 
 */
package com.nervytech.mailer24x7.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author bsikkaya
 * 
 */
@Controller
public class ErrorController {

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accessDenied(ModelMap model) {
		model.addAttribute("msg",
				"You don't have privileges to view this page!!!");
		return "403";

	}
	
	@RequestMapping(value = "/404", method = RequestMethod.GET)
	public String unknownRequest(ModelMap model) {
		model.addAttribute("msg",
				"You don't have privileges to view this page!!!");
		return "404";

	}


}
