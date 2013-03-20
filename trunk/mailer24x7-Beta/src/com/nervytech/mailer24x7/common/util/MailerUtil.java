/**
 * 
 */
package com.nervytech.mailer24x7.common.util;

import java.text.SimpleDateFormat;
import java.util.Properties;

import com.nervytech.mailer24x7.common.enums.CampaignTypeEnum;

/**
 * @author bsikkaya
 * 
 */
public class MailerUtil {

	public static SimpleDateFormat FORMATTER_WITH_TIME = new SimpleDateFormat(
			"yyyy-MM-dd hh:mm:ss");
	public static SimpleDateFormat FORMATTER_WITHOUT_TIME = new SimpleDateFormat(
			"yyyy-MM-dd");

	public static long maxFileSize = 102400; // 100 MB
	public static final String subscriberSeparator = ",";
	public static final String CONFIRMATION_MAIL_FROM = "baskar.sks@gmail.com";
	public static final String CONFIRMATION_MAIL_SUBJECT = "Welcome to Mailer24x7 - Confirmation";
	public static final String RESET_MAIL_SUBJECT = "Mailer24x7 - Instructions to reset your password";
	//public static final String RESET_MAIL_URL = "http://localhost:18080/mailer24x7/pub/pwd/reset/confirm/email/EMAIL_ID/id/RESET_ID";
	//public static final String CONFIRMATION_MAIL_URL = "http://localhost:18080/mailer24x7/reg/join/confirm/email/EMAIL_ID/id/CONFIRM_ID";
	public static final String HTML_DIRECTORY = "D:\\\\nervymail\\\\html\\\\";
	public static final String ACCOUNT_ACTIVATION_MESSAGE = "Your account is activated. You can login now.";
	public static final String RESET_MAIL_SENT_MESSAGE = "The mail with reset password instructions has been sent to this email. Please check your mail and Follow the instructions !!!";
	public static final String PASSWORD_RESET_MESSAGE = "Your password is reset. You can login now with your new password !!!";

	public static final int ROW_FETCH_SIZE = 100;
	public static final int THREADS_COUNT = 6; // 100 MB
	public static final long EXECUTOR_WAIT_TIME = 60000; // 100 MB
	public static final long LATEST_SUBSCRIBER_UPDATE_COUNT_INTERVAL = 100; // 100
																			// MB

	public static final String MAILGUN_API_KEY = "key-9enhqpnvi96dfzikfpbn46bnxpnngto0";
	public static final String MAILGUN_DOMAIN_NAME = "mailer24x7.mailgun.org";
	
	public static final String MAILGUN_RESOURCE = "https://api.mailgun.net/v2/"+MAILGUN_DOMAIN_NAME+"/campaigns";
	public static final String MAILGUN_MESSAGE_RESOURCE = "https://api.mailgun.net/v2/"+MAILGUN_DOMAIN_NAME+"/messages";
	
	public static final String CAMPAIGN_SENT_CONFIRMATION_MAIL_SUBJECT = "Mailer24x7 - Campaign Sent Successsfully";
	
	public static final String SALESFORCE_API_TOKEN = "3MVG9Y6d_Btp4xp6NhORDtcrlHgyV8bML07Yqv3t0iSmbXjsJINloMsbneTFoxVmrvkMrTVMp4x9FNHtc9wNy";
	public static final String SALESFORCE_API_SECRET = "6990886758311161066";

	public static Properties mailProps = null;

	static {

		mailProps = new Properties();
		mailProps.put("mail.smtp.host", "smtp.gmail.com");
		mailProps.put("mail.smtp.socketFactory.port", "465");
		mailProps.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		mailProps.put("mail.smtp.auth", "true");
		mailProps.put("mail.smtp.port", "465");
	}

	public static Properties getMailProps() {

		if (mailProps == null) {
			mailProps = new Properties();
			mailProps.put("mail.smtp.host", "smtp.gmail.com");
			mailProps.put("mail.smtp.socketFactory.port", "465");
			mailProps.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			mailProps.put("mail.smtp.auth", "true");
			mailProps.put("mail.smtp.port", "465");
		}
		return mailProps;
	}

	public static String getCampaignType(int type) {
		
		String toReturn = null;
		
		if (type == CampaignTypeEnum.HTMLEDITOR.getType()) {
			toReturn="HTML Editor";
		} else if (type == CampaignTypeEnum.IMPORT.getType()) {
			toReturn="Imported HTML";
		} else if (type == CampaignTypeEnum.NEWTEMPLATE.getType()) {
			toReturn="HTML Template";
		} else if (type == CampaignTypeEnum.PLAINTEXT.getType()) {
			toReturn="Plain Text";
		} else if (type == CampaignTypeEnum.PREDEFINEDTEMPLATE.getType()) {
			toReturn="HTML Template";
		}
		
		return toReturn;
	}

}
