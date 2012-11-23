/**
 * 
 */
package com.nervytech.mailer24x7.common.util;

import java.text.SimpleDateFormat;
import java.util.Properties;

/**
 * @author bsikkaya
 *
 */
public class MailerUtil {
  
	public static SimpleDateFormat FORMATTER_WITH_TIME = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss");	
	public static SimpleDateFormat FORMATTER_WITHOUT_TIME = new SimpleDateFormat("dd-MMM-yyyy");
	
	public static long maxFileSize = 102400; // 100 MB
	public static final String subscriberSeparator = ",";
	public static final String CONFIRMATION_MAIL_FROM = "baskar.sks@gmail.com";
	public static final String CONFIRMATION_MAIL_SUBJECT = "Welcome to Mailer24x7 - Confirmation";
	public static final String RESET_MAIL_SUBJECT = "Mailer24x7 - Instructions to reset your password";
	public static final String RESET_MAIL_URL = "http://localhost:8080/mailer24x7/comn/regform.form?action=reset&id=RESET_ID";
	public static final String CONFIRMATION_MAIL_URL = "http://localhost:18080/mailer24x7/reg/join/confirm/email/EMAIL_ID/id/CONFIRM_ID";
	public static final String HTML_DIRECTORY = "D:\\\\nervymail\\\\html\\\\";
	public static final String ACCOUNT_ACTIVATION_MESSAGE = "Your account is activated. You can login now.";
	public static final String RESET_MAIL_SENT_MESSAGE = "The mail with reset password instructions has been sent to this email. Please check your mail and Follow the instructions !!!";
	public static final String PASSWORD_RESET_MESSAGE = "Your password is reset. You can login now with your new password !!!";
	
	public static final  int ROW_FETCH_SIZE = 100; 
	public static final int THREADS_COUNT = 6; // 100 MB
	public static final long EXECUTOR_WAIT_TIME = 60000; // 100 MB
	public static final long LATEST_SUBSCRIBER_UPDATE_COUNT_INTERVAL = 100; // 100 MB

	public static final String MAILGUN_API_KEY = "key-1k6eo97h--jja9t8safne-f72in8vi11";
	public static final String MAILGUN_RESOURCE = "https://api.mailgun.net/v2/sungod.mailgun.org/campaigns";
	
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
		
		if(mailProps == null) {
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

}
