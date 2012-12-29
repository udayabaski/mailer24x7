package com.nervytech.mailer24x7.mailgun;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.nervytech.mailer24x7.client.exception.MailerException;
import com.nervytech.mailer24x7.common.enums.CampaignJSONKeyEnum;
import com.nervytech.mailer24x7.common.enums.MailgunSyncStatusEnum;
import com.nervytech.mailer24x7.common.enums.SubscriberCampaignStatusEnum;
import com.nervytech.mailer24x7.common.enums.SubscriberStatusEnum;
import com.nervytech.mailer24x7.common.util.MailerUtil;
import com.nervytech.mailer24x7.domain.model.CampaignSchedulerModel;
import com.nervytech.mailer24x7.domain.model.SubscriberIdStatus;
import com.nervytech.mailer24x7.model.service.api.ICampaignStatusService;
import com.nervytech.mailer24x7.model.service.api.ISubscriberIdStatusService;
import com.nervytech.mailer24x7.model.service.api.ISubscriberListService;
import com.nervytech.mailer24x7.model.service.api.ISubscriberReportsService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;

@Resource(mappedName = "mailgunCampignSyncer")
public class MailgunCampignSyncer {

	private static final Logger logger = LoggerFactory
			.getLogger(MailgunCampignSyncer.class);

	@Autowired
	private ICampaignStatusService cmpnStatusService;
	
	@Autowired
	private ISubscriberIdStatusService subscriberIdStatusService;
	
	@Autowired
	private ISubscriberListService subscriberListService;
	
	@Autowired
	private ISubscriberReportsService subscriberReportsService;
	
	public final SimpleDateFormat sdft = new SimpleDateFormat(
			"EEE, dd MMM yyyy HH:mm:ss z");

	public ClientResponse deleteCampaign(String campaignId)
			throws MailerException {
		if (campaignId == null || campaignId.isEmpty()) {
			throw new MailerException(
					"Input params can not be null or Blank. Please check the input params.");
		}
		Client client = Client.create();
		client.addFilter(new HTTPBasicAuthFilter("api",
				MailerUtil.MAILGUN_API_KEY));
		WebResource webResource = client.resource(MailerUtil.MAILGUN_RESOURCE+"/"
				+ campaignId);
		MultivaluedMapImpl formData = new MultivaluedMapImpl();
		formData.add("id", campaignId);
		return webResource.type(MediaType.APPLICATION_FORM_URLENCODED).delete(
				ClientResponse.class, formData);
	}

	public void updateCampainStats(long campainId, String campaignName,
			String[] groupbyParams) {
		
		// TODO. At present we are not doing any group by param(domain ||
		// daily_hour)
		// We will take it up later
		System.out.println("Called Updated Campaign Stats "+campainId);
		
		ClientResponse cr = getCampainsStats(campainId, null);
		
		System.out.println("Status IS : "+cr.getClientResponseStatus());
		
		if (cr.getClientResponseStatus() == ClientResponse.Status.OK) {
			String output = cr.getEntity(String.class);
			System.out.println("Output is ===>>>> "+output);
			// TODO. at present we are not doing any group by params
			if (!output.startsWith("[")) {
				try {
					JSONObject json = new JSONObject(output);
					JSONObject totalJSONObject = json
							.getJSONObject(CampaignJSONKeyEnum.total.name());
					CampaignStatsVO campainStatsVO = new CampaignStatsVO(
							campainId, campaignName);
					boolean updateDb = false;
					if (totalJSONObject != null) {
						updateDb = true;
						campainStatsVO.setDelivered(totalJSONObject
								.getInt(CampaignJSONKeyEnum.delivered.name()));
						campainStatsVO.setOpens(totalJSONObject
								.getInt(CampaignJSONKeyEnum.opened.name()));
						campainStatsVO.setClicks(totalJSONObject
								.getInt(CampaignJSONKeyEnum.clicked.name()));
						campainStatsVO.setBounced(totalJSONObject
								.getInt(CampaignJSONKeyEnum.bounced.name()));
						campainStatsVO
								.setUnsubscribed(totalJSONObject
										.getInt(CampaignJSONKeyEnum.unsubscribed
												.name()));
						campainStatsVO.setDropped(totalJSONObject
								.getInt(CampaignJSONKeyEnum.dropped.name()));
					}
					// TODO: As of now we are overriding the unique values with
					// total values
					// as we are having only one variable to represent.
					JSONObject uniqueJSONObject = json
							.getJSONObject(CampaignJSONKeyEnum.unique.name());
					if (uniqueJSONObject != null) {
						JSONObject clickedJSONObject = uniqueJSONObject
								.getJSONObject(CampaignJSONKeyEnum.clicked
										.name());
						if (clickedJSONObject != null) {
							campainStatsVO.setClicks(clickedJSONObject
									.getInt(CampaignJSONKeyEnum.recipient
											.name()));
						}
						JSONObject openedJSONObject = uniqueJSONObject
								.getJSONObject(CampaignJSONKeyEnum.opened
										.name());
						if (openedJSONObject != null) {
							campainStatsVO.setOpens(openedJSONObject
									.getInt(CampaignJSONKeyEnum.recipient
											.name()));
						}
					}
					if (updateDb) {
						cmpnStatusService.updateCampaignStatus(campainStatsVO);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}
		
		System.out.println("Update Campaign Stats Completed : ");
	}

	public void updateCampaignInMailgun(CampaignSchedulerModel cmpn) throws JSONException,
			MailerException {
		
		System.out.println("Update Campaign in Mailgun Started : "+cmpn.getCampaignId());

		logger.info("Synching Campaign in Mailgun Started : "
				+ cmpn.getCampaignId());

		MailgunSyncStatusEnum status = getEnumStatus(cmpn.getSyncStatus());
		System.out.println("STATUSSSSSSSSS "+status);

		switch (status) {
		case NONE:
			createCampaign("campaign_" + cmpn.getCampaignId(),
					"" + cmpn.getCampaignId());
			System.out.println("Created Campaign "+cmpn.getCampaignId());
			cmpnStatusService.updateCampaignSyncStatus(cmpn.getCampaignId(),
					MailgunSyncStatusEnum.CAMPAIGN_CREATED);
			// TODO. Domain we are hardcoding here
			MailgunSubscriberUtil.createMailingList(
					"sl_" + cmpn.getSubscriberListId(), MailerUtil.MAILGUN_DOMAIN_NAME,
					"Subscriber List - " + cmpn.getSubscriberListId(), "sl_"
							+ cmpn.getSubscriberListId()
							+ "@"+MailerUtil.MAILGUN_DOMAIN_NAME);
			cmpnStatusService.updateCampaignSyncStatus(cmpn.getCampaignId(),
					MailgunSyncStatusEnum.SUBSCRIBER_UPDATE_IN_PROGRESS);

			syncSubscribers(cmpn.getCampaignId(),cmpn.getSubscriberListId());

			cmpnStatusService.updateCampaignSyncStatus(cmpn.getCampaignId(),
					MailgunSyncStatusEnum.SUBSCRIBER_UPDATE_COMPLETED);
			break;
		case CAMPAIGN_CREATED:
			MailgunSubscriberUtil.createMailingList(
					"sl_" + cmpn.getSubscriberListId(), MailerUtil.MAILGUN_DOMAIN_NAME,
					"Subscriber List - " + cmpn.getSubscriberListId(), "sl_"
							+ cmpn.getSubscriberListId()
							+ "@"+MailerUtil.MAILGUN_DOMAIN_NAME);
			cmpnStatusService.updateCampaignSyncStatus(cmpn.getCampaignId(),
					MailgunSyncStatusEnum.SUBSCRIBER_UPDATE_IN_PROGRESS);

			syncSubscribers(cmpn.getCampaignId(),cmpn.getSubscriberListId());

			cmpnStatusService.updateCampaignSyncStatus(cmpn.getCampaignId(),
					MailgunSyncStatusEnum.SUBSCRIBER_UPDATE_COMPLETED);
			break;
		case SUBSCRIBER_LIST_CREATED:
			cmpnStatusService.updateCampaignSyncStatus(cmpn.getCampaignId(),
					MailgunSyncStatusEnum.SUBSCRIBER_UPDATE_IN_PROGRESS);

			syncSubscribers(cmpn.getCampaignId(),cmpn.getSubscriberListId());

			cmpnStatusService.updateCampaignSyncStatus(cmpn.getCampaignId(),
					MailgunSyncStatusEnum.SUBSCRIBER_UPDATE_COMPLETED);
			break;
		case SUBSCRIBER_UPDATE_IN_PROGRESS:

			syncSubscribers(cmpn.getCampaignId(),cmpn.getSubscriberListId());

			cmpnStatusService.updateCampaignSyncStatus(cmpn.getCampaignId(),
					MailgunSyncStatusEnum.SUBSCRIBER_UPDATE_COMPLETED);
			break;
		case SUBSCRIBER_UPDATE_COMPLETED:
			logger.info("Nothing to sync for CampaignId : "
					+ cmpn.getCampaignId());
			break;
		default:
			System.out
					.println("Unknow sync status in campaign...Check!!!!!!!!!");

		}

		logger.info("Synching Campaign in Mailgun Finished : "
				+ cmpn.getCampaignId());
		
		System.out.println("Synching completed for ID : "+cmpn.getCampaignId());
	}
	
	public void updateEventsFromMailgun(long campaignId) throws JSONException {
		
		int mailgunEventCount = getCampaignEventCount("" + campaignId);

		int deliveredCount = getCampaignDeliveredCount("" + campaignId);
		
		System.out.println("Updating EventsFrom mailgun "+campaignId+" DeliveredCount is "+deliveredCount);
		// TODO. No need to repeat every time. Have to change later.
		cmpnStatusService.updateDeliveredCount(campaignId, deliveredCount);

		int ourEventCount = cmpnStatusService.getCampaignEventFetchCount(campaignId);

		System.out.println("OurCOunt is ====>>>>> "+ourEventCount+" MailgunEventCount =====>>>> "+mailgunEventCount);
		
		if (ourEventCount < mailgunEventCount) {

			int diff = mailgunEventCount - ourEventCount;

			int pageNo = diff / 100;
			int remain = diff % 100;
			if (remain > 0) {
				ClientResponse cr = getCampaignEvents("" + campaignId,
						pageNo + 1, 0, remain);
				if (cr.getClientResponseStatus() == ClientResponse.Status.OK) {
					processAndUpdateEvents(campaignId,
							cr.getEntity(String.class));
				}
			}
			if (pageNo > 0) {
				int count = 0;
				// At present we are synching a maximum of 1000 events at a
				// time.
				while (pageNo > 0 & count < 10) {
					ClientResponse cr = getCampaignEvents("" + campaignId,
							pageNo, 0, 100);
					if (cr.getClientResponseStatus() == ClientResponse.Status.OK) {
						processAndUpdateEvents(campaignId,
								cr.getEntity(String.class));
					}
					pageNo--;
					count++;
				}
			}
		}
	}

	private WebResource getCampaignWebresource() {
		Client client = Client.create();
		client.addFilter(new HTTPBasicAuthFilter("api",
				MailerUtil.MAILGUN_API_KEY));
		WebResource webResource = client.resource(MailerUtil.MAILGUN_RESOURCE);
		return webResource;
	}

	private ClientResponse createCampaign(String campaignName, String campaignId)
			throws MailerException {
		if (campaignName == null || campaignName.isEmpty()
				|| campaignId == null || campaignId.isEmpty()) {
			throw new MailerException(
					"Input params can not be null or Blank. Please check the input params.");
		}
		WebResource webResource = getCampaignWebresource();
		MultivaluedMapImpl formData = new MultivaluedMapImpl();
		formData.add("name", campaignName);
		formData.add("id", campaignId);
		return webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(
				ClientResponse.class, formData);
	}

	private WebResource getCampaignStatsWebresource(long campaignId) {
		Client client = Client.create();
		client.addFilter(new HTTPBasicAuthFilter("api",
				MailerUtil.MAILGUN_API_KEY));
		WebResource webResource = client.resource(MailerUtil.MAILGUN_RESOURCE+"/"
				+ campaignId + "/stats");
		webResource.accept(MediaType.APPLICATION_JSON);
		return webResource;
	}

	private ClientResponse getCampainsStats(long campaignId,
			String[] groupbyParams) {
		// WebResource webResource =
		// getCampaignStatsWebresource("campaign_"+campaignID);
		WebResource webResource = getCampaignStatsWebresource(campaignId);
		MultivaluedMapImpl queryParams = new MultivaluedMapImpl();
		if (groupbyParams != null && groupbyParams.length > 0) {
			for (String gbParam : groupbyParams) {
				queryParams.add("groupby", gbParam);
			}
		}
		ClientResponse cr = webResource.queryParams(queryParams).get(
				ClientResponse.class);
		return cr;
	}

	private WebResource getCampaignEventsWebresource(String campaignId) {
		Client client = Client.create();
		client.addFilter(new HTTPBasicAuthFilter("api",
				MailerUtil.MAILGUN_API_KEY));
		WebResource webResource = client.resource(MailerUtil.MAILGUN_RESOURCE+"/"
				+ campaignId + "/events");
		webResource.accept(MediaType.APPLICATION_JSON);
		return webResource;
	}

	private ClientResponse getCampaignEvents(String campaignId, int pageNo,
			int skip, int limit) {
		WebResource webResource = getCampaignEventsWebresource(campaignId);
		MultivaluedMapImpl queryParams = new MultivaluedMapImpl();
		queryParams.add("event", CampaignJSONKeyEnum.clicked.name());
		queryParams.add("event", CampaignJSONKeyEnum.opened.name());
		queryParams.add("event", CampaignJSONKeyEnum.unsubscribed.name());
		queryParams.add("event", CampaignJSONKeyEnum.bounced.name());
		queryParams.add("event", CampaignJSONKeyEnum.dropped.name());
		if (pageNo > 0) {
			queryParams.add("page", "" + pageNo);
		}
		if (skip > 0) {
			queryParams.add("skip", "" + skip);
		}
		if (limit > 0 && limit < 100) {
			queryParams.add("limit", "" + limit);
		}
		ClientResponse cr = webResource.queryParams(queryParams).get(
				ClientResponse.class);
		return cr;
	}

	private int getCampaignDeliveredCount(String campaignId)
			throws JSONException {
		WebResource webResource = getCampaignEventsWebresource(campaignId);
		MultivaluedMapImpl queryParams = new MultivaluedMapImpl();
		queryParams.add("event", CampaignJSONKeyEnum.delivered.name());
		queryParams.add("count", CampaignJSONKeyEnum.count.name());
		ClientResponse cr = webResource.queryParams(queryParams).get(
				ClientResponse.class);
		if (cr != null
				&& cr.getClientResponseStatus() == ClientResponse.Status.OK) {
			String output = cr.getEntity(String.class);
			JSONObject json = new JSONObject(output);
			return json.getInt(CampaignJSONKeyEnum.count.name());
		} else {
			// TODO. Throw Exception here.
		}
		return -1;
	}

	private int getCampaignEventCount(String campaignId) throws JSONException {
		WebResource webResource = getCampaignEventsWebresource(campaignId);
		MultivaluedMapImpl queryParams = new MultivaluedMapImpl();
		queryParams.add("event", CampaignJSONKeyEnum.clicked.name());
		queryParams.add("event", CampaignJSONKeyEnum.opened.name());
		queryParams.add("event", CampaignJSONKeyEnum.unsubscribed.name());
		queryParams.add("event", CampaignJSONKeyEnum.bounced.name());
		queryParams.add("event", CampaignJSONKeyEnum.dropped.name());
		queryParams.add("count", CampaignJSONKeyEnum.count.name());
		ClientResponse cr = webResource.queryParams(queryParams).get(
				ClientResponse.class);
		if (cr != null
				&& cr.getClientResponseStatus() == ClientResponse.Status.OK) {
			String output = cr.getEntity(String.class);
			JSONObject json = new JSONObject(output);
			return json.getInt(CampaignJSONKeyEnum.count.name());
		} else {
			// TODO. Throw Exception here.
		}
		return -1;
	}

	private MailgunSyncStatusEnum getEnumStatus(int status) {
		if (status == 0) {
			return MailgunSyncStatusEnum.NONE;
		} else if (status == 1) {
			return MailgunSyncStatusEnum.CAMPAIGN_CREATED;
		} else if (status == 2) {
			return MailgunSyncStatusEnum.SUBSCRIBER_LIST_CREATED;
		} else if (status == 3) {
			return MailgunSyncStatusEnum.SUBSCRIBER_UPDATE_IN_PROGRESS;
		} else if (status == 4) {
			return MailgunSyncStatusEnum.SUBSCRIBER_UPDATE_COMPLETED;
		} else {
			// TODO. Throw an exception here.
			return MailgunSyncStatusEnum.NONE;
		}
	}

	private void syncSubscribers(long campaignId,long subscriberListId) throws JSONException {
		
		boolean finishFlag = false;
		while (!finishFlag) {
			List<SubscriberIdStatus> subscriberList = subscriberIdStatusService.getNextSubscribers(subscriberListId, 0L,
							SubscriberStatusEnum.ACTIVE.getStatus(), 1000);
			if (subscriberList != null && !subscriberList.isEmpty()) {
				MailgunSubscriberUtil.addMutipleMembers(
						"sl_" + subscriberListId
								+ "@"+MailerUtil.MAILGUN_DOMAIN_NAME, subscriberList, true);
				cmpnStatusService.updateLatestCampaignSubscriberId(campaignId,
						subscriberList.get(subscriberList.size() - 1).getStatusId());
			} else {
				finishFlag = true;
			}
			if (subscriberList.size() < 1000) {
				finishFlag = true;
			}
		}
	}

	private int getCamapignEventStatus(String eventString) {
		// TODO. Build it in the enum itself.
		if (eventString.equals(CampaignJSONKeyEnum.opened)) {
			return SubscriberCampaignStatusEnum.OPENED.getStatus();
		} else if (eventString.equals(CampaignJSONKeyEnum.clicked.name())) {
			return SubscriberCampaignStatusEnum.CLICKED.getStatus();
		} else if (eventString.equals(CampaignJSONKeyEnum.bounced.name())) {
			return SubscriberCampaignStatusEnum.BOUNCED.getStatus();
		} else if (eventString.equals(CampaignJSONKeyEnum.unsubscribed.name())) {
			return SubscriberCampaignStatusEnum.UNSUBSCRIBED.getStatus();
		} else if (eventString.equals(CampaignJSONKeyEnum.dropped.name())) {
			return SubscriberCampaignStatusEnum.DROPPED.getStatus();
		} else if (eventString.equals(CampaignJSONKeyEnum.complained.name())) {
			return SubscriberCampaignStatusEnum.COMPLAINED.getStatus();
		} else if (eventString.equals(CampaignJSONKeyEnum.delivered.name())) {
			return SubscriberCampaignStatusEnum.SENT.getStatus();
		} else {
			// TODO. Returning default value here. is it correct?
			return SubscriberCampaignStatusEnum.SENT.getStatus();
		}
	}

	private long getSubscriberListId(JSONArray jsonArray) throws JSONException {
		for (int i = 0; i < jsonArray.length(); i++) {
			Object obj = jsonArray.get(i);
			if (obj instanceof String) {
				String str = (String) obj;
				if (str.indexOf("sl_") != -1) {
					return Long.parseLong(str.substring(3));
				}
			}
		}
		return -1;
	}

	private void addInvalidSubsciber(CampaignStatsVO campaignStatusVO,
			JSONArray tagArray, int eventStatus, String emailId) {
		if (tagArray != null && tagArray.length() > 0) {
			long subscriberListId = 0;
			try {
				subscriberListId = getSubscriberListId(tagArray);
			} catch (JSONException e) {
				return;
			}
			if (subscriberListId > -1) {
				if (eventStatus == SubscriberCampaignStatusEnum.BOUNCED
						.getStatus()) {
					campaignStatusVO.addToBounceList(subscriberListId, emailId);
				} else if (eventStatus == SubscriberCampaignStatusEnum.UNSUBSCRIBED
						.getStatus()) {
					campaignStatusVO.addToUnsubscribeList(subscriberListId,
							emailId);
				}
			}
		}
	}

	private void processAndUpdateEvents(long campaignId, String jsonString)
			throws JSONException {
		
		System.out.println("UPDATE EVENETSSSSSSSSSSs "+jsonString);
		
		JSONArray jsonArray = new JSONArray(jsonString);
		if (jsonArray == null || jsonArray.length() == 0) {
			return;
		}

		List<CampaignEvent> campaignEventList = new ArrayList<CampaignEvent>();
		CampaignStatsVO campaignStatusVO = new CampaignStatsVO(campaignId,
				"campaign_" + campaignId);

		for (int i = 0; i < jsonArray.length(); i++) {
			Object obj = jsonArray.get(i);
			if (obj instanceof JSONObject) {
				JSONObject json = (JSONObject) obj;
				// TODO. Use enum here.
				long eventTime;
				try {
					eventTime = sdft
							.parse(json.getString(CampaignJSONKeyEnum.timestamp
									.name())).getTime();
				} catch (ParseException e) {
					eventTime = System.currentTimeMillis();
				}
				int eventStatus = getCamapignEventStatus(json
						.getString(CampaignJSONKeyEnum.event.name()));
				String emailId = json.getString(CampaignJSONKeyEnum.recipient
						.name());
				String city = json.getString(CampaignJSONKeyEnum.city
						.name());
				String region = json.getString(CampaignJSONKeyEnum.region
						.name());
				String country = json.getString(CampaignJSONKeyEnum.country
						.name());
				String ip = json.getString(CampaignJSONKeyEnum.ip
						.name());
				
				JSONArray tagArray = json.getJSONArray(CampaignJSONKeyEnum.tags.name());
				long subscriberListId = 0;
				
				if (tagArray != null && tagArray.length() > 0) {
					try {
						subscriberListId = getSubscriberListId(tagArray);
					} catch (JSONException e) {
						return;
					}
				}
				
				campaignEventList.add(new CampaignEvent(subscriberListId,eventStatus, emailId,
						eventTime,city,region,ip,country));
				campaignStatusVO.incrementEventCount(eventStatus);

				// TODO. If you want to get the subscriber id from event process
				// the tags for each event.
				if (eventStatus == SubscriberCampaignStatusEnum.BOUNCED
						.getStatus()
						|| eventStatus == SubscriberCampaignStatusEnum.UNSUBSCRIBED
								.getStatus()) {
					addInvalidSubsciber(campaignStatusVO,
							json.getJSONArray(CampaignJSONKeyEnum.tags.name()),
							eventStatus, emailId);
				}
			}
		}
		// Updating the subscriber_reports table here.
		subscriberReportsService.addCampaignEvents(campaignId, campaignEventList);

		campaignStatusVO.setEventCount(campaignEventList.size());
		// Updating the campaign opened, clicked, bounced, unsubscribe and
		// dropped count
		cmpnStatusService.updateCampaignEventsStats(campaignStatusVO);

		// Updating the subscriberid_status table for unsubscribe here.
		Map<Long, List<String>> unsubscribeMap = campaignStatusVO
				.getUnsubscribeSubMap();
		if (unsubscribeMap.size() > 0) {
			Iterator<Long> itr = unsubscribeMap.keySet().iterator();
			while (itr.hasNext()) {
				Long subListId = itr.next();
				subscriberIdStatusService.updateSubscriberStatus(subListId,
						unsubscribeMap.get(subListId),
						SubscriberStatusEnum.UNSUBSCRIBED.getStatus());
				
				subscriberListService.updateUnSubscriberCounts(subListId, unsubscribeMap.get(subListId));
			}
		}

		// Updating the subscriberid_status table for bounce here.
		Map<Long, List<String>> bouncedMap = campaignStatusVO.getBounceSubMap();
		if (bouncedMap.size() > 0) {
			Iterator<Long> itr = bouncedMap.keySet().iterator();
			while (itr.hasNext()) {
				Long subListId = itr.next();
				subscriberIdStatusService.updateSubscriberStatus(subListId,
						bouncedMap.get(subListId),
						SubscriberStatusEnum.BOUNCED.getStatus());
				
				subscriberListService.updateBounceCounts(subListId, unsubscribeMap.get(subListId));
			}
		}
	}

}
