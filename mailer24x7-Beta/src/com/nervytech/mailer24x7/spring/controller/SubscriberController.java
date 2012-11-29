package com.nervytech.mailer24x7.spring.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.nervytech.mailer24x7.client.exception.MailerException;
import com.nervytech.mailer24x7.common.enums.SubscriberStatusEnum;
import com.nervytech.mailer24x7.common.util.MailerUtil;
import com.nervytech.mailer24x7.domain.model.SubscriberIdStatus;
import com.nervytech.mailer24x7.domain.model.SubscriberList;
import com.nervytech.mailer24x7.model.service.api.ICampaignStatusService;
import com.nervytech.mailer24x7.model.service.api.ISubscriberIdStatusService;
import com.nervytech.mailer24x7.model.service.api.ISubscriberListService;
import com.nervytech.mailer24x7.spring.bean.SubscriberHomeBean;
import com.nervytech.mailer24x7.spring.form.CampaignStep3Form;
import com.nervytech.mailer24x7.spring.form.SubscriberForm;
import com.nervytech.mailer24x7.spring.security.auth.user.SessionUser;
import com.nervytech.mailer24x7.spring.security.auth.user.UserDetailsServiceImpl;

@Controller
@RequestMapping("/usr/subscriber")
public class SubscriberController {

	private static final Logger logger = LoggerFactory
			.getLogger(SubscriberController.class);

	@Autowired
	private ISubscriberListService subscriberListService;

	@Autowired
	private ICampaignStatusService campaignStatusService;

	@Autowired
	private ISubscriberIdStatusService subscriberIdStatusService;

	private Map<Long, String> subscriberGroupMap;

	@ModelAttribute("subscriberGroupMap")
	public Map<Long, String> populateSubscriberGroupMap() {
		// if (this.subscriberGroupMap == null) {

		SessionUser userDetails = UserDetailsServiceImpl.currentUserDetails();
		String orgId = userDetails.getOrgId() + "";

		subscriberGroupMap = new LinkedHashMap<Long, String>();
		subscriberGroupMap.put(-1l, "Select Subscriber");
		List<SubscriberList> subGroups = subscriberListService
				.getSubscriberGroups(orgId);
		for (SubscriberList subList : subGroups) {
			subscriberGroupMap.put(subList.getSubscriberListId(),
					subList.getSubscriberListName());
		}
		// }
		return this.subscriberGroupMap;
	}

	@RequestMapping(value = "/new/group", method = RequestMethod.GET)
	public String newGroup(Map model) {

		SubscriberForm subForm = new SubscriberForm();

		subForm.setAddOption("CSV");
		subForm.setToPage("groups");

		model.put("subscriberForm", subForm);

		return "subscribergroupnew";

	}

	@RequestMapping(value = "/view/step3/id/{campaignId}", method = RequestMethod.GET)
	public String newSubscriberGroup(@PathVariable String campaignId, Map model) {

		SessionUser userDetails = UserDetailsServiceImpl.currentUserDetails();
		long orgId = userDetails.getOrgId();

		if (subscriberGroupMap != null && subscriberGroupMap.size() == 1) {

			SubscriberForm subForm = new SubscriberForm();

			subForm.setCampaignId(Long.parseLong(campaignId));
			subForm.setAddOption("CSV");
			subForm.setToPage("step3");

			model.put("subscriberForm", subForm);

			return "subscribergroupnew";

		}

		CampaignStep3Form campForm = new CampaignStep3Form();

		campForm.setCampaignId(Long.parseLong(campaignId));

		model.put("campaignStep3Form", campForm);

		return "campaignStep3";
	}

	@RequestMapping(value = "/save/step3", method = RequestMethod.POST)
	public String saveStep3(CampaignStep3Form step3Form, BindingResult result,
			Map model) throws MailerException {

		campaignStatusService.updateSubscriberListId(step3Form.getCampaignId(),
				step3Form.getSubscriberGroup());

		return "redirect:/usr/campaign/view/snapshot/id/"
				+ step3Form.getCampaignId();

	}


	@RequestMapping(value = {"/step3/save/group","/save/group"}, method = RequestMethod.POST)
	public String saveGroup(SubscriberForm subForm, BindingResult result,
			Map model) throws MailerException {


		long subListId = 0;

		if (subForm.getSubscriberListId() == 0) {
			if (subForm.getSubscriberName() == null
					|| subForm.getSubscriberName().trim().length() == 0) {
				result.rejectValue("subscriberName",
						"NotEmpty.subscriberForm.subscriberName",
						"Subscriber Name must not be empty.");

				return "subscribergroupnew";
			}
		}

		SessionUser userDetails = UserDetailsServiceImpl.currentUserDetails();
		long orgId = userDetails.getOrgId();
		long userId = userDetails.getUserId();

		if (subForm.getSubscriberListId() == 0) {
			try {
				subscriberListService.getSubscriberGroupByListName(
						subForm.getSubscriberName(), orgId);

				result.rejectValue(
						"subscriberName",
						"NotEmpty.registrationForm.emailId",
						"This Subscriber group Name is already exist in your organization. Please give a unique name.");
				return "subscribergroupnew";
			} catch (Exception e) {
				// This name is not taken and it is good to go.
			}
		} else {
			subListId = subForm.getSubscriberListId();
		}

		InputStream inputStream = null;
		String[] subscibersArray = null;
		if (subForm.getAddOption().equals("CSV")) {
			try {
				CommonsMultipartFile file = subForm.getFileData();

				if (file.getOriginalFilename() == null
						|| file.getOriginalFilename().trim().length() == 0) {
					result.rejectValue("fileData",
							"NotEmpty.subscriberForm.fileData",
							"File must be uploaded.");

					return "subscribergroupnew";
				}
				if (file.getOriginalFilename().toLowerCase()
						.lastIndexOf(".csv") == -1
						&& file.getOriginalFilename().toLowerCase()
								.lastIndexOf(".txt") == -1) {
					result.rejectValue("fileData",
							"NotEmpty.subscriberForm.fileData",
							"Unsupported file type."); // TODO : Arun - Need to
														// mention supported
														// file types in GUI
														// ....

					return "subscribergroupnew";
				}
				if (file.getSize() > 0) {
					inputStream = file.getInputStream();

					logger.debug("Subscribers List file size : "
							+ file.getSize());

					if (file.getSize() > MailerUtil.maxFileSize) {
						System.out.println("File Size:::" + file.getSize());
						result.rejectValue(
								"fileData",
								"NotEmpty.subscriberForm.fileData",
								"File size can not be more than "
										+ file.getSize() + " Bytes.");

						return "subscribergroupnew";
					}
					String subscribers = IOUtil.toString(inputStream);
					subscibersArray = subscribers
							.split(MailerUtil.subscriberSeparator);

					logger.debug("Total Subscribers to be added : "
							+ subscibersArray.length);

				} else {
					result.rejectValue("fileData",
							"NotEmpty.subscriberForm.fileData",
							"File data is empty.");

					return "subscribergroupnew";
				}

			} catch (Exception e) {
				logger.error("Error while importing Subscribers list file ", e);
				throw new MailerException(
						"Error while importing Subscribers list file ");
			} finally {
				if (inputStream != null) {
					try {
						inputStream.close();
					} catch (IOException e) {
						logger.error("Error while closing the inputstream", e);
					}
				}
			}
		} else if (subForm.getAddOption().equals("Manual")) {
			String subscribers = subForm.getSubscribers();
			if (subscribers == null || subscribers.trim().length() == 0) {
				result.rejectValue("subscribers",
						"NotEmpty.subscriberForm.subscribers",
						"Subscribers must not be empty.");

				return "subscribergroupnew";
			}
			subscibersArray = subscribers.split(MailerUtil.subscriberSeparator);

			logger.debug("Total Subscribers to be added : "
					+ subscibersArray.length);
		}

		if (subListId == 0) {
			SubscriberList subList = new SubscriberList();
			subList.setCreatedTime(MailerUtil.FORMATTER_WITH_TIME
					.format(new Date()));
			subList.setLastModifiedTime(MailerUtil.FORMATTER_WITH_TIME
					.format(new Date()));
			subList.setOrgId(orgId);
			subList.setSubscriberListName(subForm.getSubscriberName());
			subList.setUserId(userId);

			subList.setActiveCount(subscibersArray.length);

			subListId = subscriberListService.addSubGroup(subList);
		} else {
			subscriberListService.addActiveCount(subListId,
					subscibersArray.length);
		}

		if (subscibersArray.length > 0) {
			int status = SubscriberStatusEnum.ACTIVE.getStatus();
			subscriberIdStatusService.addBatchSubscriber(subListId, userId,
					orgId, status, subscibersArray);

			logger.info("Total Subscribers added : " + subscibersArray.length
					+ " Subscriber List Id : " + subListId);
		}

		campaignStatusService.updateSubscriberListId(subForm.getCampaignId(),
				subListId + "");

		if (subForm.getToPage() != null && subForm.getToPage().equals("groups")) {
			return "redirect:/usr/subscriber/view/all/groups";
		} else {
			return "redirect:/usr/campaign/view/snapshot/id/"
					+ subForm.getCampaignId();
		}

	}

	@RequestMapping(value = "/view/all/groups", method = RequestMethod.GET)
	public String viewSubGroups(Map model, HttpServletRequest request) {

		SessionUser userDetails = UserDetailsServiceImpl.currentUserDetails();
		long orgId = userDetails.getOrgId();

		SubscriberHomeBean subscriberHomeBean = new SubscriberHomeBean();

		List<SubscriberList> subscriberGroups = subscriberListService
				.getSubscriberGroups(orgId + "");

		subscriberHomeBean.setSubscriberList(subscriberGroups);

		if (subscriberGroups != null && subscriberGroups.size() > 0) {
			subscriberHomeBean.setSubscriberListNotEmpty(true);
		}

		model.put("subscriberHomeBean", subscriberHomeBean);

		return "viewsubgroups";

	}

	@RequestMapping(value = "/edit/group/id/{groupId}", method = RequestMethod.GET)
	public String editSubGroups(@PathVariable String groupId, Map model,
			HttpServletRequest request) {

		long subGroupId = -1;

		try {
			subGroupId = Long.parseLong(groupId);
		} catch (Exception e) {
			logger.info("Invalid Subscriber Group Id : " + groupId
					+ ". Hence redirecting to home page. ");

			return "invalidrequest";
		}

		List<SubscriberList> subLists = subscriberListService
				.getSubscriberGroup(subGroupId);
		SubscriberList subList = subLists.get(0);

		SubscriberForm subForm = new SubscriberForm();
		subForm.setSubscriberListId(subGroupId);
		subForm.setSubscriberName(subList.getSubscriberListName());

		subForm.setAddOption("CSV");
		subForm.setToPage("groups");

		model.put("subscriberForm", subForm);

		return "subscribergroupnew";
	}

	@RequestMapping(value = "/delete/group/id/{groupId}", method = RequestMethod.GET)
	public String deleteSubGroups(@PathVariable String groupId, Map model,
			HttpServletRequest request) {

		long subGroupId = -1;

		try {
			subGroupId = Long.parseLong(groupId);
		} catch (Exception e) {
			logger.info("Invalid Subscriber Group Id : " + groupId
					+ ". Hence redirecting to home page. ");

			return "invalidrequest";
		}

		// TODO : Need to check if it is already asssociated with any Campaign
		// ....

		subscriberIdStatusService.deleteSubGroup(subGroupId);

		return "redirect:/usr/subscriber/view/all/groups";
	}

	@RequestMapping(value = "/view/group/subscribers/id/{groupId}", method = RequestMethod.GET)
	public String showGroup(@PathVariable String groupId, Map model,
			HttpServletRequest request) {

		long subGroupId = -1;

		try {
			subGroupId = Long.parseLong(groupId);
		} catch (Exception e) {
			logger.info("Invalid Subscriber Group Id : " + groupId
					+ ". Hence redirecting to home page. ");

			return "invalidrequest";
		}

		SubscriberHomeBean subscriberHomeBean = new SubscriberHomeBean();

		List<SubscriberList> subscriberGroups = subscriberListService
				.getSubscriberGroup(subGroupId);

		List<List<SubscriberIdStatus>> allSubscribers = subscriberIdStatusService
				.getAllSubscribers(subGroupId);

		subscriberHomeBean.setSubscriberGroup(subscriberGroups.get(0));

		subscriberHomeBean.setActiveSubscribers(allSubscribers.get(0));
		subscriberHomeBean.setUnsubscribedSubscribers(allSubscribers.get(1));
		subscriberHomeBean.setBouncedSubscribers(allSubscribers.get(2));
		
		subscriberHomeBean.setSubscriberListId(subGroupId);

		model.put("subscriberHomeBean", subscriberHomeBean);

		return "viewgroupdetails";
	}

	@RequestMapping(params = "action=deleteSubscribers", method = RequestMethod.POST)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public String deleteSubscribers(SubscriberHomeBean subForm,
			BindingResult result, Map model, HttpServletRequest request) {

		String[] subscribersSelected = null;
		long subscriberListId = subForm.getSubscriberListId();

		if (subForm.getSubscriberType() == SubscriberStatusEnum.ACTIVE
				.getStatus()) {
			subscribersSelected = subForm.getActiveSubscribersSelected();
			subscriberListService.updateActiveCount(
					subForm.getSubscriberListId(),
					subForm.getActiveSubscribersSelected().length);
		} else if (subForm.getSubscriberType() == SubscriberStatusEnum.BOUNCED
				.getStatus()) {
			subscribersSelected = subForm.getBouncedSubscribersSelected();
			subscriberListService.updateBouncedCount(
					subForm.getSubscriberListId(),
					subForm.getBouncedSubscribersSelected().length);
		} else if (subForm.getSubscriberType() == SubscriberStatusEnum.UNSUBSCRIBED
				.getStatus()) {
			subscribersSelected = subForm.getUnsubscribedSubscribersSelected();
			subscriberListService.updateUnsubscriberCount(
					subForm.getSubscriberListId(),
					subForm.getUnsubscribedSubscribersSelected().length);
		}
		
		System.out.println(" SSSSSSSSSSSSSSSSSSSSSSSS "+subscriberListId);

		subscriberIdStatusService
				.deleteSubscriber(getCommaSeparatedSubscribersList(subscribersSelected));

		// TODO : Arun - Need to update subscriber_reports table ...
		
		return "redirect:/usr/subscriber/view/group/subscribers/id/"+subscriberListId;
	}

	private String getCommaSeparatedSubscribersList(String[] subscribersSelected) {
		StringBuilder subBuilder = new StringBuilder();
		int selectedCount = 0;

		for (String subscriber : subscribersSelected) {
			if (selectedCount != 0) {
				subBuilder.append(",");
			}
			subBuilder.append(subscriber);
			selectedCount = selectedCount + 1;
		}

		return subBuilder.toString();
	}

	@RequestMapping(params = "action=moveSubscribers",  method = RequestMethod.POST)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public String moveSubscribers(SubscriberHomeBean subForm, BindingResult result,
			Map model, HttpServletRequest request) {

		String[] subscribersSelected = null;
		long subscriberListId = subForm.getSubscriberListId();

		if (subForm.getSubscriberType() == SubscriberStatusEnum.ACTIVE
				.getStatus()) {
			subscribersSelected = subForm.getActiveSubscribersSelected();
		} else if (subForm.getSubscriberType() == SubscriberStatusEnum.BOUNCED
				.getStatus()) {
			subscribersSelected = subForm.getBouncedSubscribersSelected();
		} else if (subForm.getSubscriberType() == SubscriberStatusEnum.UNSUBSCRIBED
				.getStatus()) {
			subscribersSelected = subForm.getUnsubscribedSubscribersSelected();
		}

		subscriberListService.moveSubscribers(subForm.getSubscriberListId(),
				subscribersSelected.length, subForm.getSubscriberType(),
				subForm.getMoveTo());

		subscriberIdStatusService.updateSubscribersStatus(
				getCommaSeparatedSubscribersList(subscribersSelected),
				subForm.getMoveTo());
		
		return "redirect:/usr/subscriber/view/group/subscribers/id/"+subscriberListId;
	}

	// This is for AJAX ..........
	@RequestMapping(params = "action=getSubListPieChart", method = RequestMethod.GET)
	public @ResponseBody
	String getSubscriberListPieChartData(@RequestParam String subListId) {
		System.out.println("Received REQUESTTTTTTT ");
		List<SubscriberList> subLists = subscriberListService
				.getSubscriberGroup(Long.parseLong(subListId));
		SubscriberList subList = subLists.get(0);
		System.out.println("BEFORE RETTTTTTTTTTTTT");
		return "[{title: Active,Counts:" + subList.getActiveCount() + "},"
				+ "{title: Bounced,Counts:" + subList.getBouncedCount() + "},"
				+ "{title: Unsubscribed,Counts:"
				+ subList.getUnsubscriberCount() + "}]";
	}

}
