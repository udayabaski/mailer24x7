package com.nervytech.mailer24x7.spring.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.nervytech.mailer24x7.aws.s3.client.MailerS3Client;
import com.nervytech.mailer24x7.client.exception.MailerException;
import com.nervytech.mailer24x7.common.enums.CampaignSenderStatusEnum;
import com.nervytech.mailer24x7.common.enums.CampaignStatusEnum;
import com.nervytech.mailer24x7.common.enums.CampaignTypeEnum;
import com.nervytech.mailer24x7.common.util.MailerUtil;
import com.nervytech.mailer24x7.domain.model.Campaign;
import com.nervytech.mailer24x7.domain.model.CampaignSender;
import com.nervytech.mailer24x7.domain.model.CampaignStatus;
import com.nervytech.mailer24x7.mailgun.MailgunCampignSyncer;
import com.nervytech.mailer24x7.model.service.api.ICampaignSenderService;
import com.nervytech.mailer24x7.model.service.api.ICampaignService;
import com.nervytech.mailer24x7.model.service.api.ICampaignStatusService;
import com.nervytech.mailer24x7.model.service.api.ISubscriberIdStatusService;
import com.nervytech.mailer24x7.spring.bean.CampaignBean;
import com.nervytech.mailer24x7.spring.bean.CampaignSnapshotBean;
import com.nervytech.mailer24x7.spring.bean.CampaignsHomeBean;
import com.nervytech.mailer24x7.spring.form.CampaignStep1Form;
import com.nervytech.mailer24x7.spring.form.CampaignStep2EditorForm;
import com.nervytech.mailer24x7.spring.form.CampaignStep2Form;
import com.nervytech.mailer24x7.spring.form.CampaignStep2ImportForm;
import com.nervytech.mailer24x7.spring.form.CampaignTestMailForm;
import com.nervytech.mailer24x7.spring.security.auth.user.SessionUser;
import com.nervytech.mailer24x7.spring.security.auth.user.UserDetailsServiceImpl;

@Controller
@RequestMapping("/usr/campaign")
public class CampaignController {

	private static final Logger logger = LoggerFactory
			.getLogger(CampaignController.class);

	@Autowired
	private ICampaignService campaignService;

	@Autowired
	private ICampaignStatusService campaignStatusService;

	@Autowired
	private ICampaignSenderService campaignSenderService;

	@Autowired
	private ISubscriberIdStatusService subscriberIdStatusService;

	@Autowired
	private MailgunCampignSyncer mailgunCampaignSyncer;

	@RequestMapping(value = "/view/all", method = RequestMethod.GET)
	public String showCampaigns(Map model, HttpServletRequest request) {

		SessionUser userDetails = UserDetailsServiceImpl.currentUserDetails();
		long orgId = userDetails.getOrgId();

		CampaignsHomeBean homeBean = new CampaignsHomeBean();

		int status = CampaignStatusEnum.DRAFT.getStatus();
		List<CampaignBean> draftCampaigns = campaignService.getCampaigns(orgId,
				status);
		status = CampaignStatusEnum.COMPLETED.getStatus();
		List<CampaignBean> completedCampaigns = campaignService.getCampaigns(
				orgId, status);
		status = CampaignStatusEnum.SCHEDULED.getStatus();
		List<CampaignBean> scheduledCampaigns = campaignService.getCampaigns(
				orgId, status);

		homeBean.setDraftCampaigns(draftCampaigns);
		homeBean.setScheduledCampaigns(scheduledCampaigns);
		homeBean.setCompletedCampaigns(completedCampaigns);

		model.put("campaignsHomeBean", homeBean);

		return "campaignHome";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newCampaign(Map model) {

		CampaignStep1Form cmpnStep1Form = new CampaignStep1Form();

		model.put("campaignStep1Form", cmpnStep1Form);

		return "campaignStep1";

	}

	@RequestMapping(value = "/view/step1/id/{campaignId}", method = RequestMethod.GET)
	public String showStep1Campaign(@PathVariable String campaignId, Map model,
			HttpServletRequest req) {

		SessionUser userDetails = UserDetailsServiceImpl.currentUserDetails();
		long orgId = userDetails.getOrgId();

		if (!isValidCampaignId(campaignId, orgId)) {
			return "redirect:/usr/home";
		}

		CampaignSnapshotBean snapshotBean = campaignService.getCampaign(Long
				.parseLong(campaignId));

		CampaignStep1Form cmpnStep1Form = new CampaignStep1Form();
		cmpnStep1Form.setCampaignId(campaignId);
		cmpnStep1Form.setCampaignName(snapshotBean.getCampaignName());
		cmpnStep1Form.setSenderEmail(snapshotBean.getSenderEmailId());
		cmpnStep1Form.setSenderName(snapshotBean.getSenderName());
		cmpnStep1Form.setSubject(snapshotBean.getSubject());
		// cmpnStep1Form.setReplyToAddress(replyToAddress);

		if (req.getParameter("p") != null
				&& req.getParameter("p").equals("snapshot")) {
			cmpnStep1Form.setToPage("snapshot");
		}

		model.put("campaignStep1Form", cmpnStep1Form);

		return "campaignStep1";
	}

	@RequestMapping(value = "/view/step2/id/{campaignId}", method = RequestMethod.GET)
	public String showStep2Campaign(@PathVariable String campaignId, Map model) {

		SessionUser userDetails = UserDetailsServiceImpl.currentUserDetails();
		long orgId = userDetails.getOrgId();

		if (!isValidCampaignId(campaignId, orgId)) {
			return "redirect:/usr/home";
		}

		int campaignType = campaignService.getCampaignType(Long
				.parseLong(campaignId));

		CampaignStep2Form cmpnStep2Form = new CampaignStep2Form();
		cmpnStep2Form.setCampaignId(Long.parseLong(campaignId));
		cmpnStep2Form.setContentType(campaignType);

		model.put("campaignStep2Form", cmpnStep2Form);

		return "campaignStep2";
	}

	@RequestMapping(value = "/save/step2", method = RequestMethod.POST)
	public String saveCampaignStep2(CampaignStep2Form cmpnForm, Map model) {
		SessionUser userDetails = UserDetailsServiceImpl.currentUserDetails();
		long orgId = userDetails.getOrgId();

		if (cmpnForm.getNextAction().equalsIgnoreCase("next")) {

			campaignService.updateContentType(cmpnForm.getCampaignId(),
					cmpnForm.getContentType());
			if (cmpnForm.getContentType() == CampaignTypeEnum.IMPORT.getType()) {

				CampaignStep2ImportForm form = new CampaignStep2ImportForm();
				form.setCampaignId(cmpnForm.getCampaignId());

				model.put("campaignStep2ImportForm", form);

				return "importHtml";
			} else if (cmpnForm.getContentType() == CampaignTypeEnum.PLAINTEXT
					.getType()) {

				CampaignStep2EditorForm form = new CampaignStep2EditorForm();
				form.setCampaignId(cmpnForm.getCampaignId());
				form.setEditorType(CampaignTypeEnum.PLAINTEXT.name());

				model.put("campaignStep2EditorForm", form);

				return "textEditor";
			} else if (cmpnForm.getContentType() == CampaignTypeEnum.HTMLEDITOR
					.getType()) {
				CampaignStep2EditorForm form = new CampaignStep2EditorForm();
				form.setCampaignId(cmpnForm.getCampaignId());
				form.setEditorType(CampaignTypeEnum.HTMLEDITOR.name());

				model.put("campaignStep2EditorForm", form);

				return "htmlEditor";
			} else if (cmpnForm.getContentType() == CampaignTypeEnum.NEWTEMPLATE
					.getType()) {

			} else if (cmpnForm.getContentType() == CampaignTypeEnum.PREDEFINEDTEMPLATE
					.getType()) {

			}
		} else if (cmpnForm.getNextAction().equalsIgnoreCase("exit")) {
			campaignService.updateContentType(cmpnForm.getCampaignId(),
					cmpnForm.getContentType());

			return "redirect:/usr/campaign/view/snapshot/id/"
					+ cmpnForm.getCampaignId();
		} else if (cmpnForm.getNextAction().equalsIgnoreCase("prev")) {
			return "redirect:/usr/campaign/view/step1/id/"
					+ cmpnForm.getCampaignId();
		}

		return null;

	}

	@RequestMapping(value = "/update/import", method = RequestMethod.POST)
	public String updateCampaignImport(CampaignStep2ImportForm cmpnForm,
			BindingResult result, Map model) {

		SessionUser userDetails = UserDetailsServiceImpl.currentUserDetails();
		long orgId = userDetails.getOrgId();

		String htmlContent = null;
		try {
			htmlContent = getHtmlContent(cmpnForm, result);
		} catch (Exception e) {
			return "importHtml";
		}

		try {
			putZipData(cmpnForm, result, userDetails);
		} catch (Exception e) {
			return "importHtml";
		}

		String s3Path = MailerS3Client.putHtmlObject(orgId,
				userDetails.getUserId(), cmpnForm.getCampaignId(), htmlContent,
				true);

		campaignStatusService.updateS3Path(s3Path, cmpnForm.getCampaignId(),
				MailerUtil.FORMATTER_WITH_TIME.format(new Date()));

		return "redirect:/usr/campaign/view/snapshot/id/"
				+ cmpnForm.getCampaignId();

	}

	@RequestMapping(value = "/save/import", method = RequestMethod.POST)
	public String saveCampaignImport(CampaignStep2ImportForm cmpnForm,
			BindingResult result, Map model) {
		SessionUser userDetails = UserDetailsServiceImpl.currentUserDetails();
		long orgId = userDetails.getOrgId();

		if (cmpnForm.getNextAction().equalsIgnoreCase("prev")) {
			return "redirect:/usr/campaign/view/step2/id/"
					+ cmpnForm.getCampaignId();
		}

		String htmlContent = null;
		try {
			htmlContent = getHtmlContent(cmpnForm, result);
		} catch (Exception e) {
			return "importHtml";
		}

		try {
			putZipData(cmpnForm, result, userDetails);
		} catch (Exception e) {
			return "importHtml";
		}

		String s3Path = MailerS3Client.putHtmlObject(orgId,
				userDetails.getUserId(), cmpnForm.getCampaignId(), htmlContent,
				true);

		campaignStatusService.updateS3Path(s3Path, cmpnForm.getCampaignId(),
				MailerUtil.FORMATTER_WITH_TIME.format(new Date()));

		if (cmpnForm.getNextAction().equalsIgnoreCase("exit")) {
			return "redirect:/usr/campaign/view/snapshot/id/"
					+ cmpnForm.getCampaignId();
		} else if (cmpnForm.getNextAction().equalsIgnoreCase("next")) {

			return "redirect:/usr/subscriber/view/step3/id/"
					+ cmpnForm.getCampaignId();
		}

		return null;
	}

	@RequestMapping(value = "/update/text", method = RequestMethod.POST)
	public String updateCampaignText(CampaignStep2EditorForm cmpnForm, Map model) {

		SessionUser userDetails = UserDetailsServiceImpl.currentUserDetails();
		long orgId = userDetails.getOrgId();

		String s3Path = MailerS3Client.putTxtObject(orgId,
				userDetails.getUserId(), cmpnForm.getCampaignId(),
				cmpnForm.getTextData(), true);

		campaignStatusService.updateS3Path(s3Path, cmpnForm.getCampaignId(),
				MailerUtil.FORMATTER_WITH_TIME.format(new Date()));

		return "redirect:/usr/campaign/view/snapshot/id/"
				+ cmpnForm.getCampaignId();

	}
	
	@RequestMapping(value = "/deliver/test", method = RequestMethod.POST)
	public String testCampaign(CampaignTestMailForm testForm, Map model) {
		SessionUser userDetails = UserDetailsServiceImpl.currentUserDetails();
		long orgId = userDetails.getOrgId();

		if (testForm.getNextAction().equals("next")) {
			CampaignTestMailForm form = new CampaignTestMailForm();
			form.setTestMailId(snapshotBean.getSenderEmailId());
			form.setCampaignId(snapshotBean.getCampaignId()+"");
			
			return "testcampaign";
		} else {
			return "redirect:/usr/campaign/view/all";
		}

		// return null;
	}

	@RequestMapping(value = "/save/text", method = RequestMethod.POST)
	public String saveSnapshot(CampaignSnapshotBean snapshotBean, Map model) {
		SessionUser userDetails = UserDetailsServiceImpl.currentUserDetails();
		long orgId = userDetails.getOrgId();

		if (snapshotBean.getNextAction().equals("next")) {
			CampaignTestMailForm form = new CampaignTestMailForm();
			form.setTestMailId(snapshotBean.getSenderEmailId());
			form.setCampaignId(snapshotBean.getCampaignId()+"");
			
			return "testcampaign";
		} else {
			return "redirect:/usr/campaign/view/all";
		}

		// return null;
	}

	@RequestMapping(value = "/save/text", method = RequestMethod.POST)
	public String saveCampaignText(CampaignStep2EditorForm cmpnForm, Map model) {
		SessionUser userDetails = UserDetailsServiceImpl.currentUserDetails();
		long orgId = userDetails.getOrgId();

		if (cmpnForm.getNextAction().equalsIgnoreCase("prev")) {
			return "redirect:/usr/campaign/view/step2/id/"
					+ cmpnForm.getCampaignId();
		}

		String s3Path = MailerS3Client.putTxtObject(orgId,
				userDetails.getUserId(), cmpnForm.getCampaignId(),
				cmpnForm.getTextData(), true);

		campaignStatusService.updateS3Path(s3Path, cmpnForm.getCampaignId(),
				MailerUtil.FORMATTER_WITH_TIME.format(new Date()));

		if (cmpnForm.getNextAction().equalsIgnoreCase("exit")) {
			return "redirect:/usr/campaign/view/snapshot/id/"
					+ cmpnForm.getCampaignId();
		} else if (cmpnForm.getNextAction().equalsIgnoreCase("next")) {

			return "redirect:/usr/subscriber/view/step3/id/"
					+ cmpnForm.getCampaignId();
		}

		return null;

	}

	@RequestMapping(value = "/update/html", method = RequestMethod.POST)
	public String updateCampaignHtml(CampaignStep2EditorForm cmpnForm, Map model) {
		SessionUser userDetails = UserDetailsServiceImpl.currentUserDetails();
		long orgId = userDetails.getOrgId();

		String content = "<html><body>" + cmpnForm.getHtmlData()
				+ "<html><body>";

		String s3Path = MailerS3Client.putTxtObject(orgId,
				userDetails.getUserId(), cmpnForm.getCampaignId(), content,
				true);

		campaignStatusService.updateS3Path(s3Path, cmpnForm.getCampaignId(),
				MailerUtil.FORMATTER_WITH_TIME.format(new Date()));

		return "redirect:/usr/campaign/view/snapshot/id/"
				+ cmpnForm.getCampaignId();
	}

	@RequestMapping(value = "/save/html", method = RequestMethod.POST)
	public String saveCampaignHtml(CampaignStep2EditorForm cmpnForm, Map model) {
		SessionUser userDetails = UserDetailsServiceImpl.currentUserDetails();
		long orgId = userDetails.getOrgId();

		if (cmpnForm.getNextAction().equalsIgnoreCase("prev")) {
			return "redirect:/usr/campaign/view/step2/id/"
					+ cmpnForm.getCampaignId();
		}

		String content = "<html><body>" + cmpnForm.getHtmlData()
				+ "<html><body>";

		String s3Path = MailerS3Client.putTxtObject(orgId,
				userDetails.getUserId(), cmpnForm.getCampaignId(), content,
				true);

		campaignStatusService.updateS3Path(s3Path, cmpnForm.getCampaignId(),
				MailerUtil.FORMATTER_WITH_TIME.format(new Date()));

		if (cmpnForm.getNextAction().equalsIgnoreCase("exit")) {
			return "redirect:/usr/campaign/view/snapshot/id/"
					+ cmpnForm.getCampaignId();
		} else if (cmpnForm.getNextAction().equalsIgnoreCase("next")) {

			return "redirect:/usr/subscriber/view/step3/id/"
					+ cmpnForm.getCampaignId();
		}

		return null;
	}

	@RequestMapping(value = "/update/step1", method = RequestMethod.POST)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public String updateCampaignStep1(CampaignStep1Form cmpnForm, Map model) {
		SessionUser userDetails = UserDetailsServiceImpl.currentUserDetails();
		long orgId = userDetails.getOrgId();

		String campaignIdStr = cmpnForm.getCampaignId();

		System.out.println("CampaignStr .... " + campaignIdStr);

		CampaignSender cmpnSender = new CampaignSender();
		cmpnSender.setOrgId(orgId);
		cmpnSender.setDisplayName(cmpnForm.getSenderName());
		cmpnSender.setEmailId(cmpnForm.getSenderEmail());
		cmpnSender.setStatus(CampaignSenderStatusEnum.VERIFIED.getStatus());
		long campaignSenderId = -1;
		try {
			campaignSenderId = campaignSenderService
					.getCampaignSenderId(cmpnSender);
		} catch (Exception e) {
			// Exception will be thrown if the record is not found ....
			campaignSenderId = campaignSenderService
					.saveCampaignSender(cmpnSender);
		}

		Date currentTime = new Date();

		Campaign cmpn = new Campaign();
		cmpn.setCampaignName(cmpnForm.getCampaignName());
		cmpn.setSubject(cmpnForm.getSubject());
		cmpn.setCreatedEmailId(userDetails.getUsername());
		cmpn.setLastModifiedTime(MailerUtil.FORMATTER_WITH_TIME
				.format(currentTime));
		cmpn.setCampaignId(Long.parseLong(campaignIdStr));
		campaignService.updateCampaign(cmpn);

		CampaignStatus cmpnStatus = new CampaignStatus();
		cmpnStatus.setSenderId(campaignSenderId);
		cmpnStatus.setLastUpdatedTime(MailerUtil.FORMATTER_WITH_TIME
				.format(currentTime));
		cmpnStatus.setCampaignId(Long.parseLong(campaignIdStr));

		campaignStatusService.updateCampaignStatusSender(cmpnStatus);

		return "redirect:/usr/campaign/view/snapshot/id/" + campaignIdStr;

	}

	@RequestMapping(value = "/save/step1", method = RequestMethod.POST)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public String saveCampaignStep1(CampaignStep1Form cmpnForm, Map model) {

		SessionUser userDetails = UserDetailsServiceImpl.currentUserDetails();
		long orgId = userDetails.getOrgId();

		String campaignIdStr = cmpnForm.getCampaignId();

		System.out.println("CampaignStr .... " + campaignIdStr);

		CampaignSender cmpnSender = new CampaignSender();
		cmpnSender.setOrgId(orgId);
		cmpnSender.setDisplayName(cmpnForm.getSenderName());
		cmpnSender.setEmailId(cmpnForm.getSenderEmail());
		cmpnSender.setStatus(CampaignSenderStatusEnum.VERIFIED.getStatus()); // TODO
																				// :
																				// Arun
																				// -
																				// Need
																				// to
																				// set
																				// correct
																				// value
																				// ...

		long campaignSenderId = -1;
		try {
			campaignSenderId = campaignSenderService
					.getCampaignSenderId(cmpnSender);
		} catch (Exception e) {
			// Exception will be thrown if the record is not found ....
			campaignSenderId = campaignSenderService
					.saveCampaignSender(cmpnSender);
		}

		Date currentTime = new Date();

		Campaign cmpn = new Campaign();
		cmpn.setCampaignName(cmpnForm.getCampaignName());
		cmpn.setSubject(cmpnForm.getSubject());
		cmpn.setOrgId(orgId);
		cmpn.setCreatedEmailId(userDetails.getUsername());
		cmpn.setCreatedTime(MailerUtil.FORMATTER_WITH_TIME.format(currentTime));
		cmpn.setLastModifiedTime(MailerUtil.FORMATTER_WITH_TIME
				.format(currentTime));

		long campaignId = -1;

		if (campaignIdStr != null && campaignIdStr.trim().length() > 0) {
			campaignId = Long.parseLong(campaignIdStr);
			campaignService.updateCampaign(cmpn);
		} else {
			campaignId = campaignService.saveCampaign(cmpn);
		}

		CampaignStatus cmpnStatus = new CampaignStatus();
		cmpnStatus.setCampaignId(campaignId);
		cmpnStatus.setOrgId(orgId);
		cmpnStatus.setUserId(userDetails.getUserId());
		cmpnStatus.setSenderId(campaignSenderId);
		cmpnStatus.setStatus(CampaignStatusEnum.DRAFT.getStatus());
		cmpnStatus.setLastUpdatedTime(MailerUtil.FORMATTER_WITH_TIME
				.format(currentTime));

		if (campaignIdStr != null && campaignIdStr.trim().length() > 0) {
			campaignStatusService.updateCampaignStatusSender(cmpnStatus);
		} else {
			campaignStatusService.saveCampaignStatus(cmpnStatus);
		}

		if (cmpnForm.getNextAction().equalsIgnoreCase("next")) {
			CampaignStep2Form steps2Form = new CampaignStep2Form();
			steps2Form.setCampaignId(campaignId);

			if (campaignIdStr != null && campaignIdStr.trim().length() > 0) {
				int campaignType = campaignService.getCampaignType(campaignId);
				steps2Form.setContentType(campaignType);
			}

			model.put("campaignStep2Form", steps2Form);

			return "campaignStep2";
		} else if (cmpnForm.getNextAction().equalsIgnoreCase("exit")) {
			return "redirect:/usr/campaign/view/snapshot/id/" + campaignId;
		}

		return null;
	}

	@RequestMapping(value = "/edit/content/type/{type}/id/{campaignId}", method = RequestMethod.GET)
	public String editContent(@PathVariable String type,
			@PathVariable String campaignId, Map model,
			HttpServletRequest request) {

		int typeInt = Integer.parseInt(type);

		String s3Path = campaignStatusService.getS3Path(campaignId);

		if (typeInt == CampaignTypeEnum.IMPORT.getType()) {

			MailerS3Client.deleteObject(s3Path);
			// TODO : Need to delete zip as well .......

			CampaignStep2ImportForm form = new CampaignStep2ImportForm();
			form.setCampaignId(Long.parseLong(s3Path));
			form.setToPage("snapshot");

			model.put("campaignStep2ImportForm", form);

			return "importHtml";
		} else if (typeInt == CampaignTypeEnum.PLAINTEXT.getType()) {

			String content = MailerS3Client.getHTMLObject(s3Path);

			CampaignStep2EditorForm form = new CampaignStep2EditorForm();
			form.setCampaignId(Long.parseLong(s3Path));
			form.setTextData(content);
			form.setEditorType(CampaignTypeEnum.PLAINTEXT.name());
			form.setToPage("snapshot");

			model.put("campaignStep2EditorForm", form);
			return "textEditor";

		} else if (typeInt == CampaignTypeEnum.HTMLEDITOR.getType()) {

			String content = MailerS3Client.getHTMLObject(s3Path);

			CampaignStep2EditorForm form = new CampaignStep2EditorForm();
			form.setCampaignId(Long.parseLong(s3Path));
			form.setTextData(content);
			form.setEditorType(CampaignTypeEnum.HTMLEDITOR.name());
			form.setToPage("snapshot");

			model.put("campaignStep2EditorForm", form);

			return "htmlEditor";

		} else if (typeInt == CampaignTypeEnum.NEWTEMPLATE.getType()) {

		} else if (typeInt == CampaignTypeEnum.PREDEFINEDTEMPLATE.getType()) {

		}

		return null;
	}

	@RequestMapping(value = "/view/snapshot/id/{campaignId}", method = RequestMethod.GET)
	public String viewSnapshot(@PathVariable String campaignId, Map model,
			HttpServletRequest request) {

		SessionUser userDetails = UserDetailsServiceImpl.currentUserDetails();
		long orgId = userDetails.getOrgId();

		if (!isValidCampaignId(campaignId, orgId)) {
			return "redirect:/usr/home";
		}

		CampaignSnapshotBean snapshotBean = campaignService.getCampaign(Long
				.parseLong(campaignId));

		if (snapshotBean.getS3Path() != null) {
			snapshotBean.setContent(MailerS3Client.getHTMLObject(snapshotBean
					.getS3Path()));
		}

		System.out.println("SNAPSHOTBEAN " + snapshotBean);

		if (snapshotBean.getSubscriberListId() != null) {
			int subscribersCount = subscriberIdStatusService
					.getSubscribersCount(snapshotBean.getSubscriberListId());
			snapshotBean.setSubscribersCount(subscribersCount);
		}

		model.put("campaignSnapshotBean", snapshotBean);

		return "snapshot";
	}

	@RequestMapping(value = "/view/detail/id/{campaignId}", method = RequestMethod.GET)
	public String viewCampaign(@PathVariable String campaignId, Map model,
			HttpServletRequest request) {

		SessionUser userDetails = UserDetailsServiceImpl.currentUserDetails();
		long orgId = userDetails.getOrgId();

		if (!isValidCampaignId(campaignId, orgId)) {
			return "redirect:/usr/home";
		}

		CampaignsHomeBean homeBean = new CampaignsHomeBean();

		int status = CampaignStatusEnum.DRAFT.getStatus();
		List<CampaignBean> draftCampaigns = campaignService.getCampaigns(orgId,
				status);
		status = CampaignStatusEnum.COMPLETED.getStatus();
		List<CampaignBean> completedCampaigns = campaignService.getCampaigns(
				orgId, status);
		status = CampaignStatusEnum.SCHEDULED.getStatus();
		List<CampaignBean> scheduledCampaigns = campaignService.getCampaigns(
				orgId, status);

		homeBean.setDraftCampaigns(draftCampaigns);
		homeBean.setScheduledCampaigns(scheduledCampaigns);
		homeBean.setCompletedCampaigns(completedCampaigns);

		model.put("campaignsHomeBean", homeBean);

		return "campaignHome";
	}

	@RequestMapping(value = "/edit/id/{campaignId}", method = RequestMethod.GET)
	public String editCampaign(@PathVariable String campaignId, Map model) {

		return "redirect:/usr/campaign/view/snapshot/id/" + campaignId;
	}

	@RequestMapping(value = "/clone/id/{campaignId}", method = RequestMethod.GET)
	public String cloneCampaign(@PathVariable String campaignId, Map model) {

		SessionUser userDetails = UserDetailsServiceImpl.currentUserDetails();
		long orgId = userDetails.getOrgId();

		if (!isValidCampaignId(campaignId, orgId)) {
			return "redirect:/usr/home";
		}

		CampaignsHomeBean homeBean = new CampaignsHomeBean();

		int status = CampaignStatusEnum.DRAFT.getStatus();
		List<CampaignBean> draftCampaigns = campaignService.getCampaigns(orgId,
				status);
		status = CampaignStatusEnum.COMPLETED.getStatus();
		List<CampaignBean> completedCampaigns = campaignService.getCampaigns(
				orgId, status);
		status = CampaignStatusEnum.SCHEDULED.getStatus();
		List<CampaignBean> scheduledCampaigns = campaignService.getCampaigns(
				orgId, status);

		homeBean.setDraftCampaigns(draftCampaigns);
		homeBean.setScheduledCampaigns(scheduledCampaigns);
		homeBean.setCompletedCampaigns(completedCampaigns);

		model.put("campaignsHomeBean", homeBean);

		return "campaignHome";
	}

	@RequestMapping(value = "/delete/id/{campaignId}", method = RequestMethod.GET)
	public String deleteCampaign(@PathVariable String campaignId, Map model) {

		SessionUser userDetails = UserDetailsServiceImpl.currentUserDetails();
		long orgId = userDetails.getOrgId();

		if (!isValidCampaignId(campaignId, orgId)) {
			return "redirect:/usr/home";
		}

		campaignService.deleteCampaign(Long.parseLong(campaignId));

		try {
			mailgunCampaignSyncer.deleteCampaign(campaignId + "");
		} catch (MailerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// model.put("homeBean", homeBean);

		return "redirect:/usr/campaign/view/all";

	}

	private boolean isValidCampaignId(String campaignId, long orgId) {
		long campaignIdLong = -1;
		boolean toReturn = true;

		try {
			campaignIdLong = Long.parseLong(campaignId);
		} catch (Exception e) {
			logger.info("Invalid campaign Id : " + campaignId
					+ ". Hence redirecting to home page. ");

			toReturn = false;
		}

		long campaignOrgId = -1;
		try {
			campaignOrgId = campaignService.getOrgId(campaignIdLong);
		} catch (Exception e) {
			logger.info("Invalid campaign Id : " + campaignId
					+ ". Hence redirecting to home page. ");

			toReturn = false;
		}

		if (campaignOrgId != orgId) {
			logger.info("Invalid campaign Id : " + campaignId
					+ ". Hence redirecting to home page. ");

			toReturn = false;
		}

		return toReturn;
	}

	private void putZipData(CampaignStep2ImportForm cmpnForm,
			BindingResult result, SessionUser userDetails) {
		if (cmpnForm.getZipFileData() != null) {
			InputStream inputStream = null;
			try {
				CommonsMultipartFile file = cmpnForm.getZipFileData();
				if (file.getOriginalFilename() != null
						&& file.getOriginalFilename().trim().length() > 0) {

					if (file.getSize() > 0) {
						if (file.getSize() > MailerUtil.maxFileSize) {
							result.rejectValue("zipFileData",
									"NotEmpty.campaignForm.zipFileData",
									"Zip File size can not be more than "
											+ file.getSize() + " Bytes.");

							throw new RuntimeException();
						}

						logger.info("File size : " + file.getSize());

						MailerS3Client.processZipFile(file,
								userDetails.getOrgId(),
								userDetails.getUserId(),
								cmpnForm.getCampaignId());

					}
				}

			} catch (Exception e) {
				throw new RuntimeException();
			} finally {
				if (inputStream != null) {
					try {
						inputStream.close();
					} catch (IOException e) {
						logger.error("Error while closing the input stream ", e);
					}
				}
			}
		}

	}

	private String getHtmlContent(CampaignStep2ImportForm cmpnForm,
			BindingResult result) {

		String htmlContent = null;

		InputStream inputStream = null;
		try {
			CommonsMultipartFile file = cmpnForm.getHtmlFileData();
			if (file.getOriginalFilename() == null
					|| file.getOriginalFilename().trim().length() == 0) {
				result.rejectValue("htmlFileData",
						"NotEmpty.campaignForm.htmlFileData",
						"File must be uploaded.");

				throw new RuntimeException();
			}
			if (file.getOriginalFilename().toLowerCase().lastIndexOf(".html") == -1) {
				result.rejectValue("htmlFileData",
						"NotEmpty.campaignForm.htmlFileData",
						"Unsupported file type.");

				throw new RuntimeException();
			}
			if (file.getSize() > 0) {
				inputStream = file.getInputStream();
				if (file.getSize() > MailerUtil.maxFileSize) {
					result.rejectValue("htmlFileData",
							"NotEmpty.campaignForm.htmlFileData",
							"File size can not be more than " + file.getSize()
									+ " Bytes.");

					throw new RuntimeException();
				}

				logger.info("File size : " + file.getSize());

				BufferedReader br = new BufferedReader(new InputStreamReader(
						inputStream));

				StringBuilder sb = new StringBuilder();

				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}

				htmlContent = sb.toString();

			} else {
				result.rejectValue("htmlFileData",
						"NotEmpty.campaignForm.htmlFileData",
						"File data is empty.");

				throw new RuntimeException();
			}

		} catch (Exception e) {
			throw new RuntimeException();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					logger.error("Error while closing the input stream ", e);
				}
			}
		}

		return htmlContent;

	}

}
