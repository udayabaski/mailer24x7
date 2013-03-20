/**
 * 
 */
package com.nervytech.mailer24x7.integrations.crm.clients;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
import org.scribe.utils.OAuthEncoder;
import org.springframework.stereotype.Service;

import com.nervytech.mailer24x7.integrations.crm.salesforce.ForceDotComApi;
import com.nervytech.mailer24x7.integrations.crm.salesforce.ForceDotComApi.ForceDotComToken;

/**
 * @author bsikkaya
 * 
 */
@Service
public class SalesforceService {

	/*
	 * scope	A space separated list of scope values. The scope parameter allows you to fine-tune what the client application can access. Supported values are:
api - Allows access to the current, logged-in user’s account over the APIs, such as the REST API or Bulk API.
chatter_api - Allows access to only the Chatter API URLs.
full - Allows access to all data accessible by the current, logged-in user.
id - Allows access only to the Identity Service.
refresh_token - Allows a refresh token to be returned if you are eligible to receive one.
visualforce - Allows access to Visualforce pages.
web - Allows the ability to use the access_token on the Web.
If you do not supply a scope parameter, it will default to id api refresh_token.
	 */
	
	/*
	 * "urls":{
        "enterprise":"https://na1.salesforce.com/services/Soap/c/{version}/00D50000000IZ3Z",
        "metadata":"https://na1.salesforce.com/services/Soap/m/{version}/00D50000000IZ3Z",
        "partner":"https://na1.salesforce.com/services/Soap/u/{version}/00D50000000IZ3Z",
        "rest":"https://na1.salesforce.com/services/data/v{version}/",
        "sobjects":"https://na1.salesforce.com/services/data/v{version}/sobjects/",
        "search":"https://na1.salesforce.com/services/data/v{version}/search/",
        "query":"https://na1.salesforce.com/services/data/v{version}/query/",
        "recent":"https://na1.salesforce.com/services/data/v{version}/recent/",
        "profile":"https://na1.salesforce.com/00550000001fg5OAAQ"
    },

	 */
	
	// Ref : https://developers.facebook.com/docs/reference/api/
	// http://developers.facebook.com/docs/reference/login/extended-permissions/
	// http://developers.facebook.com/docs/reference/dialogs/feed/
	private static final String NETWORK_NAME = "Salesforce";
	private static String BASIC_DATA_PROTECTED_RESOURCE_URL = "/services/data/v20.0/query/";
	private static final Token EMPTY_TOKEN = null;

	public String getAuthorizationURL(String apiKey,
			String apiSecret, String callbackUrl) {
		OAuthService service = new ServiceBuilder().provider(ForceDotComApi.class)
				.apiKey(apiKey).apiSecret(apiSecret)
				//.scope("refresh_token")
				.callback(callbackUrl).build();
		
		return service.getAuthorizationUrl(EMPTY_TOKEN);
	}

	public Map<String, String> getAccessTokenMap(String apiKey,
			String apiSecret, String verifier,String callbackUrl) {
		OAuthService service = new ServiceBuilder().provider(ForceDotComApi.class)
				.apiKey(apiKey).apiSecret(apiSecret).callback(callbackUrl).build();

		Verifier verifierObj = new Verifier(verifier);

		ForceDotComToken accessToken = (ForceDotComToken)service.getAccessToken(EMPTY_TOKEN, verifierObj);

		Map<String, String> map = new HashMap<String, String>();
		map.put("ACCESS_TOKEN", accessToken.getToken());
		map.put("ACCESS_SECRET", accessToken.getSecret());
		map.put("REFRESH_TOKEN", accessToken.getRefreshToken());
		map.put("INSTANCE_URL", accessToken.getInstanceUrl());

		return map;
	}

	public String getDataFromAccess(String apiKey, String apiSecret,
			String token, String secret, String insatnceUrl) {
		OAuthService service = new ServiceBuilder().provider(ForceDotComApi.class)
				.apiKey(apiKey).apiSecret(apiSecret).build();
		Token accessToken = new Token(token, secret);
		String query = OAuthEncoder.encode("SELECT Contact.Firstname,Contact.Lastname,Contact.Title,Contact.Phone,Contact.Email FROM Contact");
		OAuthRequest request = new OAuthRequest(Verb.GET,
				insatnceUrl+BASIC_DATA_PROTECTED_RESOURCE_URL+"?q="+query);
		service.signRequest(accessToken, request);
		
		Response response = request.send();
		if (response.isSuccessful()) {

		}
		System.out.println("RRRRRRRRRRRRRRRRRRR "+response.getBody());
		return response.getBody();
	}
	
	public static void main(String[] args) {
		// Replace these with your own api key and secret
		String apiKey = "your_app_id";
		String apiSecret = "your_api_secret";
		OAuthService service = new ServiceBuilder().provider(ForceDotComApi.class)
				.apiKey(apiKey).apiSecret(apiSecret)
				.callback("http://www.example.com/oauth_callback/").build();
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

		// Trade the Request Token and Verfier for the Access Token
		System.out.println("Trading the Request Token for an Access Token...");
		Token accessToken = service.getAccessToken(EMPTY_TOKEN, verifier);
		System.out.println("Got the Access Token!");
		System.out.println("(if your curious it looks like this: "
				+ accessToken + " )");
		System.out.println();

		// Now let's go and ask for a protected resource!
		System.out.println("Now we're going to access a protected resource...");
		OAuthRequest request = new OAuthRequest(Verb.GET,
				BASIC_DATA_PROTECTED_RESOURCE_URL);
		service.signRequest(accessToken, request);
		Response response = request.send();
		System.out.println("Got it! Lets see what we found...");
		System.out.println();
		System.out.println(response.getCode());
		System.out.println(response.getBody());

		System.out.println();
		System.out
				.println("Thats it man! Go and build something awesome with Scribe! :)");

	}

}
