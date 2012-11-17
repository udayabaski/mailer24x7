package com.nervytech.mailer24x7.mailgun;

import java.io.File;
import java.util.List;

import javax.ws.rs.core.MediaType;

import com.nervytech.mailer24x7.mail.util.HTMLParser;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.file.FileDataBodyPart;
import com.sun.jersey.multipart.impl.MultiPartWriter;

public class MailgunSendUtil {

	// TODO. Add tag based unsubscribe url after study.
	private static WebResource getMessageWebresource() {
		ClientConfig cc = new DefaultClientConfig();
		cc.getClasses().add(MultiPartWriter.class);
		Client client = Client.create(cc);
		client.addFilter(new HTTPBasicAuthFilter("api",
				"key-1k6eo97h--jja9t8safne-f72in8vi11"));
		WebResource webResource = client
				.resource("https://api.mailgun.net/v2/sungod.mailgun.org"
						+ "/messages");
		webResource.accept(MediaType.APPLICATION_JSON);
		return webResource;
	}

	public static ClientResponse sendCampaignTestMail(Long campaingId,
			Long orgId, String fromAddress, String toMailIds, String subject,
			String textContent, String htmlContent, List<AttachInfo> attachList) // throws
																					// sendmail
																					// exception
	{
		WebResource webResource = getMessageWebresource();
		// TODO. May be give ClientResponse with error code.
		if (fromAddress == null || fromAddress.isEmpty() || toMailIds == null
				|| toMailIds.isEmpty()) {
			return null;
		}
		FormDataMultiPart form = new FormDataMultiPart();
		form.field("from", fromAddress);
		// Comma separated email address here.
		form.field("to", toMailIds);
		// Assigning no subject here in case of empty subject.
		if (subject == null || subject.isEmpty()) {
			subject = "<no subject>";
		}
		form.field("subject", subject);
		if (textContent == null && htmlContent != null) {
			textContent = HTMLParser.extractText(htmlContent);
		} else {
			form.field("text", textContent);
		}
		form.field("html", htmlContent);
		form.field("o:campaign", "campaign_" + campaingId);
		form.field("o:tag", "org_" + orgId);
		form.field("o:tracking", "yes");
		form.field("o:tracking-clicks", "yes");
		form.field("o:tracking-opens", "yes");
		if (attachList != null && !attachList.isEmpty()) {
			for (AttachInfo attachInfo : attachList) {
				File txtFile = new File(attachInfo.getFileName());
				form.bodyPart(new FileDataBodyPart("attachment", txtFile,
						attachInfo.getMediaType()));
			}
		}
		return webResource.type(MediaType.MULTIPART_FORM_DATA_TYPE).post(
				ClientResponse.class, form);
	}

	public static ClientResponse sendMail(Long campaingId, String fromAddress,
			Long subListId, Long orgId, String subject, String textContent,
			String htmlContent, List<AttachInfo> inlineList,
			List<AttachInfo> attachList) // throws sendmail exception
	{
		WebResource webResource = getMessageWebresource();
		// TODO. May be give ClientResponse with error code.
		if (fromAddress == null || fromAddress.isEmpty() || subListId == null
				|| subListId <= 0L || campaingId == null || campaingId <= 0) {
			return null;
		}
		FormDataMultiPart form = new FormDataMultiPart();
		form.field("from", fromAddress);
		// TODO. Hardcoding the subsriber list based on subsriber list id..
		form.field("to", "sl_" + subListId + "@sungod.mailgun.org");
		// Assigning no subject here in case of empty subject.
		if (subject == null || subject.isEmpty()) {
			subject = "<no subject>";
		}
		form.field("subject", subject);
		if (textContent == null && htmlContent != null) {
			textContent = HTMLParser.extractText(htmlContent);
		} else {
			form.field("text", textContent);
		}
		form.field("html", htmlContent);
		form.field("o:campaign", "" + campaingId);
		form.field("o:tag", "org_" + orgId);
		form.field("o:tag", "sl_" + subListId);
		form.field("o:tracking", "yes");
		form.field("o:tracking-clicks", "yes");
		form.field("o:tracking-opens", "yes");
		if (inlineList != null && !inlineList.isEmpty()) {
			for (AttachInfo attachInfo : inlineList) {
				File txtFile = new File(attachInfo.getFileName());
				form.bodyPart(new FileDataBodyPart("inline", txtFile,
						attachInfo.getMediaType()));
			}
		}
		if (attachList != null && !attachList.isEmpty()) {
			for (AttachInfo attachInfo : attachList) {
				File txtFile = new File(attachInfo.getFileName());
				form.bodyPart(new FileDataBodyPart("attachment", txtFile,
						attachInfo.getMediaType()));
			}
		}
		return webResource.type(MediaType.MULTIPART_FORM_DATA_TYPE).post(
				ClientResponse.class, form);
	}
	/*
	 * public static ClientResponse sendMail(String fromAddress, String
	 * toMailIds, String subject, String textContent, String htmlContent,
	 * List<AttachInfo> attachList) //throws sendmail exception { WebResource
	 * webResource = getMessageWebresource(); //TODO. May be give ClientResponse
	 * with error code. if(fromAddress == null || fromAddress.isEmpty() ||
	 * toMailIds == null || toMailIds.isEmpty()) { return null; }
	 * FormDataMultiPart form = new FormDataMultiPart(); form.field("from",
	 * fromAddress); //Comma separated email address here. form.field("to",
	 * toMailIds); //Assigning no subject here in case of empty subject.
	 * if(subject == null || subject.isEmpty()) { subject = "<no subject>"; }
	 * form.field("subject", subject); if(textContent == null && htmlContent !=
	 * null) { textContent = HTMLParser.extractText(htmlContent); }else {
	 * form.field("text", textContent); } form.field("html", htmlContent);
	 * if(attachList != null && !attachList.isEmpty()) { for(AttachInfo
	 * attachInfo : attachList){ File txtFile = new
	 * File(attachInfo.getFileName()); form.bodyPart(new
	 * FileDataBodyPart("attachment", txtFile, attachInfo.getMediaType())); } }
	 * return
	 * webResource.type(MediaType.MULTIPART_FORM_DATA_TYPE).post(ClientResponse
	 * .class, form); }
	 */

}
