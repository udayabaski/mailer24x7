package com.nervytech.mailer24x7.mailgun;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.json.JSONException;

import com.nervytech.mailer24x7.domain.model.SubscriberIdStatus;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.sun.jersey.multipart.impl.MultiPartWriter;

public class MailgunSubscriberUtil 
{

	public static WebResource getCommonListWebresource()
	{
		ClientConfig cc = new DefaultClientConfig();
		cc.getClasses().add(MultiPartWriter.class);
		Client client = Client.create(cc);
		client.addFilter(new HTTPBasicAuthFilter("api",
				"key-1k6eo97h--jja9t8safne-f72in8vi11"));
		WebResource webResource =
				client.resource("https://api.mailgun.net/v2/lists");
		return webResource;
	}


	public static WebResource getListMemberWebresource(String listName)
	{
		ClientConfig cc = new DefaultClientConfig();
		cc.getClasses().add(MultiPartWriter.class);
		Client client = Client.create(cc);
		client.addFilter(new HTTPBasicAuthFilter("api",
				"key-1k6eo97h--jja9t8safne-f72in8vi11"));
		WebResource webResource = null;
		if(listName != null && !listName.isEmpty()) {
			webResource = client.resource("https://api.mailgun.net/v2/lists/"+listName+"/members");
		}else {
			webResource = client.resource("https://api.mailgun.net/v2/lists");
		}
		return webResource;
	}

	public static ClientResponse createMailingList(String listId, String domain, String listName, String description) {
		WebResource webResource = getCommonListWebresource();
		MultivaluedMapImpl formData = new MultivaluedMapImpl();
		//formData.add("address", "list@sungod.mailgun.org");
		formData.add("address", listId+"@"+domain);
		formData.add("name", listName);
		formData.add("description", description);
		return webResource.type(MediaType.APPLICATION_FORM_URLENCODED).
				post(ClientResponse.class, formData);

	}

	public static ClientResponse addNewMember(String listId, String emailId, String name, boolean overwrite) {
		WebResource webResource = getListMemberWebresource(listId);
		MultivaluedMapImpl formData = new MultivaluedMapImpl();
		formData.add("address", emailId);
		formData.add("subscribed", true);
		formData.add("name", name);
		formData.add("upsert",""+overwrite);
		formData.add("description", "Developer");
		formData.add("vars", "{\"age\": 27}");
		return webResource.type(MediaType.APPLICATION_FORM_URLENCODED).
				post(ClientResponse.class, formData);

	}

	public static WebResource getMultipleListMemberWebresource(String listName)
	{
		ClientConfig cc = new DefaultClientConfig();
		cc.getClasses().add(MultiPartWriter.class);
		Client client = Client.create(cc);
		client.addFilter(new HTTPBasicAuthFilter("api",
				"key-1k6eo97h--jja9t8safne-f72in8vi11"));
		WebResource webResource = null;
		if(listName != null && !listName.isEmpty()) {
			webResource = client.resource("https://api.mailgun.net/v2/lists/"+listName+"/members.json");
		}else {
			webResource = client.resource("https://api.mailgun.net/v2/lists");
		}
		return webResource;
	}
	
	public static ClientResponse addMutipleMembers(String listId, List<SubscriberIdStatus> subscriberList, boolean overwrite) throws JSONException {
		WebResource webResource = getMultipleListMemberWebresource(listId);
		MultivaluedMapImpl formData = new MultivaluedMapImpl();
		formData.add("subscribed", true);
		formData.add("upsert", true);
		//TODO. parse the result
		formData.add("members", JSONUtil.getListMembersAsJSONArray(subscriberList).toString());
		return webResource.type(MediaType.APPLICATION_FORM_URLENCODED).
				post(ClientResponse.class, formData);
	}

}
