package com.nervytech.mailer24x7.spring.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nervytech.mailer24x7.common.enums.CampaignStatusEnum;
import com.nervytech.mailer24x7.common.enums.SubscriberCampaignStatusEnum;
import com.nervytech.mailer24x7.model.service.api.ICampaignService;
import com.nervytech.mailer24x7.model.service.api.ISubscriberReportsService;
import com.nervytech.mailer24x7.reports.bean.BarChartReportsBean;
import com.nervytech.mailer24x7.spring.bean.CampaignBean;
import com.nervytech.mailer24x7.spring.bean.CampaignReportsBean;
import com.nervytech.mailer24x7.spring.bean.CampaignsHomeBean;
import com.nervytech.mailer24x7.spring.security.auth.user.SessionUser;
import com.nervytech.mailer24x7.spring.security.auth.user.UserDetailsServiceImpl;

@Controller
@RequestMapping("/usr/reports")
public class ReportsController {

	private static final Logger logger = LoggerFactory
			.getLogger(ReportsController.class);

	@Autowired
	private ICampaignService campaignService;

	@Autowired
	private ISubscriberReportsService reportsService;

	@RequestMapping(value = "/view/all", method = RequestMethod.GET)
	public String showCampaigns(Map model, HttpServletRequest request) {

		SessionUser userDetails = UserDetailsServiceImpl.currentUserDetails();
		long orgId = userDetails.getOrgId();

		CampaignsHomeBean homeBean = new CampaignsHomeBean();

		int status = CampaignStatusEnum.COMPLETED.getStatus();
		List<CampaignBean> completedCampaigns = campaignService.getCampaigns(
				orgId, status);

		homeBean.setCompletedCampaigns(completedCampaigns);

		model.put("campaignsHomeBean", homeBean);

		return "reportsHome";
	}

	@RequestMapping(value = "/view/campaign/id/{campaignId}", method = RequestMethod.GET)
	public String campaignReport(@PathVariable String campaignId, Map model,
			HttpServletRequest request) {

		SessionUser userDetails = UserDetailsServiceImpl.currentUserDetails();
		long orgId = userDetails.getOrgId();

		CampaignReportsBean bean = campaignService.getCampaignBean(campaignId);

		int totalSent = bean.getTotalEmailsSent();

		bean.setOpenedPercentage(totalSent == 0 ? 0.0f : ((float) bean
				.getOpened() / (float) totalSent) * 100);
		bean.setClickedPercentage(totalSent == 0 ? 0.0f : ((float) bean
				.getClicked() / (float) totalSent) * 100);
		bean.setBouncedPercentage(totalSent == 0 ? 0.0f : ((float) bean
				.getBounced() / (float) totalSent) * 100);
		bean.setDroppedPercentage(totalSent == 0 ? 0.0f : ((float) bean
				.getDropped() / (float) totalSent) * 100);
		bean.setUnsubscribedPercentage(totalSent == 0 ? 0.0f : ((float) bean
				.getUnsubscribed() / (float) totalSent) * 100);

		model.put("campaignReportsBean", bean);

		return "viewcampaignreport";
	}

	@RequestMapping(value = "/view/campaign/id/{campaignId}/type/campaignopens/time", method = RequestMethod.GET)
	public @ResponseBody
	String viewCampaignOpens(@PathVariable String campaignId, Map model,
			HttpServletRequest request) {

		long campaignIdLong = -1;

		try {
			campaignIdLong = Long.parseLong(campaignId);
		} catch (Exception e) {
			logger.info("Invalid campaign Id : " + campaignId
					+ ". Hence redirecting to home page. ");

			// return "invalidrequest";
		}

		Map<Integer, List<Long>> statusMap = reportsService
				.getSubscriberReport(campaignIdLong,
						SubscriberCampaignStatusEnum.OPENED.getStatus(),
						SubscriberCampaignStatusEnum.CLICKED.getStatus());

		Map<String, Integer> openedMap = new HashMap<String, Integer>();
		Map<String, Integer> clickedMap = new HashMap<String, Integer>();

		DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");

		Date date = new Date();

		Iterator itr = statusMap.entrySet().iterator();

		while (itr.hasNext()) {
			Map.Entry<Integer, List<Long>> entrySet = (Map.Entry<Integer, List<Long>>) itr
					.next();
			int status = entrySet.getKey();
			List<Long> timesList = entrySet.getValue();

			if (status == SubscriberCampaignStatusEnum.OPENED.getStatus()) {

				for (Long time : timesList) {
					date.setTime(time);
					String dateStr = formatter.format(date);
					if (openedMap.get(dateStr) == null) {
						openedMap.put(dateStr, 1);
					} else {
						int count = openedMap.get(dateStr);
						openedMap.put(dateStr, (count += 1));
					}
				}
			} else if (status == SubscriberCampaignStatusEnum.CLICKED
					.getStatus()) {

				for (Long time : timesList) {
					date.setTime(time);
					String dateStr = formatter.format(date);
					if (clickedMap.get(dateStr) == null) {
						clickedMap.put(dateStr, 1);
					} else {
						int count = clickedMap.get(dateStr);
						clickedMap.put(dateStr, (count += 1));
					}
				}
			}
		}

		return openedMap.toString() + "##" + clickedMap.toString();
	}

	@RequestMapping(value = "/view/campaign/id/{campaignId}/type/country", method = RequestMethod.GET)
	public @ResponseBody
	String campaignReportsByRegion(@PathVariable String campaignId, Map model,
			HttpServletRequest request) {

		long campaignIdLong = -1;

		try {
			campaignIdLong = Long.parseLong(campaignId);
		} catch (Exception e) {
			logger.info("Invalid campaign Id : " + campaignId
					+ ". Hence redirecting to home page. ");

			// return "invalidrequest";
		}

		Map<String, BarChartReportsBean> countryStatusMap = reportsService
				.getSubscriberRegionReport(campaignIdLong,
						SubscriberCampaignStatusEnum.OPENED.getStatus(),
						SubscriberCampaignStatusEnum.CLICKED.getStatus(),
						SubscriberCampaignStatusEnum.BOUNCED.getStatus(),
						SubscriberCampaignStatusEnum.UNSUBSCRIBED.getStatus());

		List openedClickedTickList = new ArrayList();
		List bouncedUnSubscribedTickList = new ArrayList();
		List openedList = new ArrayList();
		List clickedList = new ArrayList();
		List bouncedList = new ArrayList();
		List unsubscribedList = new ArrayList();

		String toReturn = null;

		Iterator itr = countryStatusMap.entrySet().iterator();

		while (itr.hasNext()) {
			Map.Entry<String, BarChartReportsBean> entrySet = (Map.Entry<String, BarChartReportsBean>) itr
					.next();
			String country = entrySet.getKey();
			BarChartReportsBean bean = entrySet.getValue();

			if (!(bean.getOpened() == 0 && bean.getClicked() == 0)) {
				openedClickedTickList.add(country);
				openedList.add(bean.getOpened());
				clickedList.add(bean.getClicked());
			}

			if (!(bean.getBounced() == 0 && bean.getUnsubscribed() == 0)) {
				bouncedUnSubscribedTickList.add(country);
				bouncedList.add(bean.getBounced());
				unsubscribedList.add(bean.getUnsubscribed());
			}
		}

		StringBuilder builder = new StringBuilder();
		if (openedClickedTickList.size() > 0) {
			builder.append(openedClickedTickList.toString());
			builder.append("##");
			builder.append(openedList.toString());
			builder.append("##");
			builder.append(clickedList.toString());
			builder.append("###");
		} else {
			builder.append("No Date for Opened And Clicked");
			builder.append("###");
		}

		if (openedClickedTickList.size() > 0) {
			builder.append(bouncedUnSubscribedTickList.toString());
			builder.append("##");
			builder.append(bouncedList.toString());
			builder.append("##");
			builder.append(unsubscribedList.toString());
		} else {
			builder.append("No Date for Bounced and UnSubscribed");
			builder.append("###");
		}

		// return toReturn == null ? "" : toReturn;
		return builder.toString();

	}

	public static void main(String args[]) {
		int opened = 2;
		int totalSent = 4;
		System.out.println(((float) opened / (float) totalSent) * 100);
	}

}
