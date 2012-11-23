package com.nervytech.mailer24x7.spring.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nervytech.mailer24x7.model.service.api.ICampaignService;
import com.nervytech.mailer24x7.model.service.api.IHistoryService;
import com.nervytech.mailer24x7.spring.bean.ActivityBean;
import com.nervytech.mailer24x7.spring.bean.CampaignBean;
import com.nervytech.mailer24x7.spring.bean.HomeBean;
import com.nervytech.mailer24x7.spring.security.auth.user.SessionUser;
import com.nervytech.mailer24x7.spring.security.auth.user.UserDetailsServiceImpl;

@Controller
@RequestMapping("/usr/home")
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	@Autowired
	private ICampaignService campaignService;
	
	@Autowired
	private IHistoryService historyService;

	@RequestMapping(method = RequestMethod.GET)
	public String showCampaigns(Map model, HttpServletRequest request) {

		SessionUser userDetails = UserDetailsServiceImpl.currentUserDetails();
		long orgId = userDetails.getOrgId();

		HomeBean homeBean = new HomeBean();

		List<CampaignBean> campaignsList = campaignService.getLatestCampaigns(
				10, orgId);
		
		List<ActivityBean> activitiesList = historyService.getLatestActivities(10,orgId);

		homeBean.setOrgId(orgId + "");
		homeBean.setCampaignsList(campaignsList);
		homeBean.setActivitiesList(activitiesList);

		model.put("homeBean", homeBean);

		return "home";
	}

}
