/**
 * 
 */
package com.nervytech.mailer24x7.spring.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nervytech.mailer24x7.common.util.MailerUtil;
import com.nervytech.mailer24x7.integrations.crm.clients.SalesforceService;

/**
 * @author bsikkaya
 * 
 */

@Controller
@RequestMapping(value = { "/usr/integrations" })
public class CRMController {

	private static final Logger logger = LoggerFactory
			.getLogger(CRMController.class);

	@Autowired
	private SalesforceService salesforceService;

	@Autowired
	@Qualifier("org.springframework.security.authenticationManager")
	protected AuthenticationManager authenticationManager;

	@RequestMapping(value = "/salesforce/fetch/contacts/step2", method = RequestMethod.GET)
	public String signupFacebook(Map model, HttpServletRequest request,
			HttpServletResponse response) {
		
		String verifierStr = request.getParameter("code");

		Map<String, String> map = salesforceService.getAccessTokenMap(
				MailerUtil.SALESFORCE_API_TOKEN, MailerUtil.SALESFORCE_API_SECRET, 
				 verifierStr,"http://localhost:18080/mailer24x7/usr/integrations/salesforce/fetch/contacts/step2");

		String accessToken = map.get("ACCESS_TOKEN");
		String accessSecret = map.get("ACCESS_SECRET");
		String instanceUrl = map.get("INSTANCE_URL");

		String data = salesforceService.getDataFromAccess(MailerUtil.SALESFORCE_API_TOKEN,
				MailerUtil.SALESFORCE_API_SECRET, accessToken, accessSecret, instanceUrl);

		/*try {
			Map<String, String> responseMap = null;// SocialReposnseParser.getFacebookDataMap(data);

			String emailId = responseMap.get("EMAILID");
			String firstName = responseMap.get("FIRSTNAME");
			String lastName = responseMap.get("LASTNAME");

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "redirect:/auth/login?message=failed";
		}*/

		return "redirect:http://localhost:18080/mailer24x7";

	}

	

	@RequestMapping(value = "/salesforce/fetch/contacts/step1", method = RequestMethod.GET)
	public String getSalesforceAuthorizationURL(Map model, HttpServletRequest request,
			HttpServletResponse response) {

		String authUrl = salesforceService
				.getAuthorizationURL(MailerUtil.SALESFORCE_API_TOKEN,
						MailerUtil.SALESFORCE_API_SECRET,
						"http://localhost:18080/mailer24x7/usr/integrations/salesforce/fetch/contacts/step2");
		
		System.out.println("Authorization URLLLLLL "+authUrl);

		return "redirect:" + authUrl;

	}

	private String[] getTokensFromCookies(String service, HttpServletRequest request, HttpServletResponse response) {

		String[] toReturn = new String[2];
		String reqCookieName = null;
		String secretCookieName = null;
		
		if (service.equalsIgnoreCase("SALESFORCE")) {			
				reqCookieName = "SALESFORCE_FETCH_REQTOKEN";
				secretCookieName = "SALESFORCE_FETCH_REQSECRET";
		} 

		Cookie cookie = null;
		Cookie[] cookies = null;
		// Get an array of Cookies associated with this domain
		cookies = request.getCookies();

		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];

				if (cookie.getName().equals(reqCookieName)) {
					toReturn[0] = cookie.getValue();
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				} else if (cookie.getName().equals(secretCookieName)) {
					toReturn[1] = cookie.getValue();
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
		}

		return toReturn;
	}

}
