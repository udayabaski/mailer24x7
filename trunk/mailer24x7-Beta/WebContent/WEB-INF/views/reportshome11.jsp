
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Mailer24x7 : home</title>
<link href="${pageContext.request.contextPath}/styles/style.css" rel="stylesheet" type="text/css" />
<!--[if lt IE 9]><script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/excanvas.js"></script><![endif]-->
<link href="${pageContext.request.contextPath}/styles/jquery-ui-1.9.1.custom.min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery-ui-1.9.1.custom.min.js"></script>

	<script>
    	$(function() {
          $( "#tabs" ).tabs();
        });
    </script>

</head>

<body>

<form:form action="view" method="POST" commandName="campaignsHomeBean">


	<!--content main controller table starts!-->
	<table cellpadding="0" cellspacing="0" width="100%"
		class="content-padding">
		<tr>
			<td width="10%"></td>
			<td width="80%" valign="top">
				<table cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td width="100%" class="contentLayer">
							<div id="tabbed_box_1" class="tabbed_box">

								<div id="tabs" class="tabbed_area">

									<ul class="tabs">
										<li><a href="#tabs-1" title="content_1" class="tab active">Sent Campaigns
										</a></li>
									</ul>
									<div class="right-link">
										<a href="${pageContext.request.contextPath}/usr/campaign/new" class="button grey-btn mar1" ><img src="${pageContext.request.contextPath}/images/add.png" width="16" height="15" alt="" />New Campaign</a>
										<a href="${pageContext.request.contextPath}/usr/campaign/new" class="button grey-btn mar1" ><img src="${pageContext.request.contextPath}/images/add.png" width="16" height="15" alt="" />New A/B Campaign</a>
									</div>

									<div id="tabs-1" class="content">
										<table cellpadding="0" cellspacing="0" width="100%">
											
												<c:set var="completedCampaignsCount" value="${fn:length(campaignsHomeBean.completedCampaigns)}"></c:set>
												
											<c:choose>
        									<c:when test="${completedCampaignsCount == 0}">
       											<tr>
														<td colspan="4" class="">No Campaigns are sent.</td>
													</tr>
       										</c:when>
       										<c:otherwise>
													
													<tr>
														<td class="table-heading">Name</td>
														<td class="table-heading">Created By</td>
														<td class="table-heading">Last Updated</td>
														<td class="table-heading">Opened</td>
														<td class="table-heading">Clicked</td>
														<td class="table-heading">Bounced</td>
														<td class="table-heading" width="100">Actions</td>
													</tr>
													
								<c:forEach items="${campaignsHomeBean.completedCampaigns}" var="campaign" varStatus="reStatus">
								
														<tr onmouseout="this.className='emailcampheader odd'"
															onmouseover="this.className='emailcampHeaderHover'"
															class="emailcampheader odd">

															<td class="tbl-btm-bdr"><a
																href="${pageContext.request.contextPath}/usr/reports/view/campaign/id/${campaign.campaignId}"><c:out value="${campaign.campaignName}" /></a></td>
															<td class="tbl-btm-bdr"><c:out value="${campaign.createdBy}" /></td>
															<td class="tbl-btm-bdr"><c:out value="${campaign.lastModifiedTime}" /></td>
															<td class="tbl-btm-bdr"><c:out value="${campaign.opened}" /></td>
															<td class="tbl-btm-bdr"><c:out value="${campaign.clicked}" /></td>
															<td class="tbl-btm-bdr"><c:out value="${campaign.bounced}" /></td>
															<td class="tbl-btm-bdr" width="100">
															  <a href="view/detail/id/<c:out value="${campaign.campaignId}" />" class="add" title="view">View</a> 
															  <a href="edit/id/<c:out value="${campaign.campaignId}" />" class="edit" title="edit">Edit</a> 
															  <a href="clone/id/<c:out value="${campaign.campaignId}" />" class="clone" title="Clone">Clone</a> 
															  <a href="delete/id/<c:out value="${campaign.campaignId}" />" class="delete" class="Delete">Delete</a></td>
														</tr>


								</c:forEach>
													
													</c:otherwise>
											</c:choose>	
											

										</table>
									</div>

								</div>

							</div>
						</td>
					</tr>
				</table>
			</td>
			<td width="10%"></td>
		</tr>
	</table>
	<!--content main controller table ends!-->

</form:form>





<!--footer part starts!-->

<!--footer part ends!-->
</body>
</html>
