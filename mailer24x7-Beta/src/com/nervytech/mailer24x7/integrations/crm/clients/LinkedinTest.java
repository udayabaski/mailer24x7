/**
 * 
 */
package com.nervytech.mailer24x7.integrations.crm.clients;

import java.util.Scanner;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import com.nervytech.mailer24x7.common.util.MailerUtil;
import com.nervytech.mailer24x7.integrations.crm.salesforce.ForceDotComApi;

/**
 * @author bsikkaya
 *
 */
public class LinkedinTest {
	
	private static final String NETWORK_NAME = "Salesforce";
	private static final String PROTECTED_RESOURCE_URL = "http://api.linkedin.com/v1/people/~/connections:(id,last-name)";
	private static final Token EMPTY_TOKEN = null;
	  
	  public static void main(String[] args)
	  {
	    OAuthService service = new ServiceBuilder()
	                                .provider(ForceDotComApi.class)
	                                .apiKey(MailerUtil.SALESFORCE_API_TOKEN)
	                                .apiSecret(MailerUtil.SALESFORCE_API_SECRET)
	                                .scope("full")
	                                .callback("http://localhost:18080/mailer24x7/usr/integrations/salesforce/fetch/contacts/step2")
	                                .build();
	    
	    Scanner in = new Scanner(System.in);

	    System.out.println("=== " + NETWORK_NAME + "'s OAuth Workflow ===");
	    System.out.println();

	    // Obtain the Authorization URL
	    System.out.println("Fetching the Authorization URL...");
	    String authorizationUrl = service.getAuthorizationUrl(EMPTY_TOKEN);
	    System.out.println("Got the Authorization URL!");
	    System.out.println("Now go and authorize Scribe here:");
	    System.out.println(authorizationUrl);
	    System.out.println("And paste the authorization code here");
	    System.out.print(">>");
	    Verifier verifier = new Verifier(in.nextLine());
	    System.out.println();
	    
	 // Trade the Request Token and Verifier for the Access Token
	    System.out.println("Trading the Request Token for an Access Token...");
	    Token accessToken = service.getAccessToken(EMPTY_TOKEN, verifier);
	    System.out.println("Got the Access Token!");
	    System.out.println("(if your curious it looks like this: " + accessToken + " )");
	    System.out.println();

	   }


}
