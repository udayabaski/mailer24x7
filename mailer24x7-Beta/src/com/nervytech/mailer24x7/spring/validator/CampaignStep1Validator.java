/**
 * 
 */
package com.nervytech.mailer24x7.spring.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.nervytech.mailer24x7.spring.form.CampaignStep1Form;

/**
 * @author ADMIN
 *
 */

@Component("campaignStep1Validator")
public class CampaignStep1Validator {
	
	public boolean supports(Class<?> klass) {
	    return CampaignStep1Form.class.isAssignableFrom(klass);
	}
	
	public void validate(Object target, Errors errors) {
	    
		CampaignStep1Form registrationForm = (CampaignStep1Form) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "campaignName", "NotEmpty.campaignStep1Form.campaignName","Campaign name must not be empty.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "subject", "NotEmpty.campaignStep1Form.subject","Subject must not be empty.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "senderName", "NotEmpty.campaignStep1Form.senderName","Sender name must not be empty.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "senderEmail", "NotEmpty.campaignStep1Form.senderEmail","Sender Email must not be empty.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "replyToAddress", "NotEmpty.campaignStep1Form.replyToAddress","ReplyTo Address must not be empty.");
		
	  }
}
