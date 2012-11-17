package com.nervytech.mailer24x7.mailgun;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nervytech.mailer24x7.domain.model.SubscriberIdStatus;

public class JSONUtil {
	
	public static JSONArray getListMembersAsJSONArray(String[] emailIds) throws JSONException
	{
		JSONArray jsonArray = new JSONArray();
		if(emailIds != null) {
			for(String emailId : emailIds) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("address", emailId);
				//System.out.println("JSON Object string "+jsonObject);
				jsonArray.put(jsonObject);
			}
		};
		//System.out.println("JSON Array print :"+jsonArray.join(","));
		System.out.println("JSON Array print :"+jsonArray.toString());
		return jsonArray;
	}
	
	
	public static JSONArray getListMembersAsJSONArray(List<SubscriberIdStatus> subscriberList) throws JSONException
	{
		JSONArray jsonArray = new JSONArray();
		if(subscriberList != null) {
			for(SubscriberIdStatus subscriber : subscriberList) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("address", subscriber.getEmailId());
				//System.out.println("JSON Object string "+jsonObject);
				jsonArray.put(jsonObject);
			}
		};
		//System.out.println("JSON Array print :"+jsonArray.join(","));
		System.out.println("JSON Array print :"+jsonArray.toString());
		return jsonArray;
	}
	
	
	public static void main(String[] args)
	{
		try {
			//getListMembersAsJSONArray(new String[] {"Peter <petertestiphone@zoho.com>","Gayathri <gayathri.paschal@gmail.com>", "Gayathri NG <gayathri.ng@gmail.com>"});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
