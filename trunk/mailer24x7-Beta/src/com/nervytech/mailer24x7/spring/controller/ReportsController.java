package com.nervytech.mailer24x7.spring.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nervytech.mailer24x7.common.enums.CampaignStatusEnum;
import com.nervytech.mailer24x7.common.enums.SubscriberCampaignStatusEnum;
import com.nervytech.mailer24x7.model.service.api.ICampaignService;
import com.nervytech.mailer24x7.model.service.api.ISubscriberIdStatusService;
import com.nervytech.mailer24x7.model.service.api.ISubscriberReportsService;
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
	public String campaignReport(@PathVariable String campaignId, Map model, HttpServletRequest request) {

		SessionUser userDetails = UserDetailsServiceImpl.currentUserDetails();
		long orgId = userDetails.getOrgId();

		CampaignReportsBean bean = campaignService.getCampaignBean(campaignId);
		
		int totalSent = bean.getTotalEmailsSent();
		
		bean.setOpenedPercentage(totalSent == 0?0.0f:(bean.getOpened()/totalSent)*100);
		bean.setClickedPercentage(totalSent == 0?0.0f:(bean.getClicked()/totalSent)*100);
		bean.setBouncedPercentage(totalSent == 0?0.0f:(bean.getBounced()/totalSent)*100);
		bean.setDroppedPercentage(totalSent == 0?0.0f:(bean.getDropped()/totalSent)*100);
		bean.setUnsubscribedPercentage(totalSent == 0?0.0f:(bean.getUnsubscribed()/totalSent)*100);

		
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
		

		Map<Integer, List<Long>> statusMap = reportsService.getSubscriberReport(
				campaignIdLong, SubscriberCampaignStatusEnum.OPENED.getStatus(),
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

	
	@RequestMapping(value = "/view/campaign/id/{campaignId}/type/region", method = RequestMethod.GET)
	public String campaignReportsByRegion(@PathVariable String campaignId, Map model, HttpServletRequest request) {
		
		return "campaignRegionReports";
	}


}
