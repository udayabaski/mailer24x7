<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<title>Mailer24x7 : Home</title>
<link href="${pageContext.request.contextPath}/styles/style.css" rel="stylesheet" type="text/css" />
<!--[if lt IE 9]><script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/excanvas.js"></script><![endif]-->
<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery-min.js"></script>

</head>

<body>

<form:form action="home" method="POST" commandName="homeBean">

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

								<div class="tabbed_area">
							
						<c:set var="campaignsCount" value="${fn:length(homeBean.campaignsList)}"></c:set>
       
        				<c:choose>
        				<c:when test="${campaignsCount == 0}">
       
									<div id="content_1" class="content">
										<table cellpadding="0" cellspacing="0" width="100%">
													<tr>
														<td class="table-left" width="50%">
															<table cellpadding="10" cellspacing="0">
																<tr valign="top">
																	<td class="pad1"><img src="${pageContext.request.contextPath}/images/img-campaign.png" width="75" height="72" alt="" /></td>
																	<td><h1>Design your first campaign</h1>
																	<p>Get started by creating your first email campaign. We'll walk through the entire process, and you can choose how you'd like to pay before you send.</p>
																	<p><a href="${pageContext.request.contextPath}/usr/campaign/new" class="button green mar1 bg" >Get Started<img src="${pageContext.request.contextPath}/images/arrows.png" width="20" height="20" alt="" class="arrows" /></a></p></td>
																</tr>
															</table>
														</td>
														<td width="50%" class="table-right">
															<h3>Already have a list?</h3>
															<p>Fantastic! Import your existing list of subscribers with a few clicks.</p>
															<p><a href="${pageContext.request.contextPath}/usr/campaign/new" class="button green mar1" >Import your subscribers</a></p>
														</td>
													</tr>
										</table>
									</div>
									<br/>
									
						</c:when>
						<c:otherwise>
									
									<div id="content_2" class="content">
										<div class="recent-campaign clearfix">
											<table cellpadding="0" cellspacing="0" width="100%">
														<tr valign="top">
															<td width="78%">
																<h3>Recent Campaign</h3>
																<ul class="list">
																    <c:forEach items="${homeBean.campaignsList}" var="campaign" varStatus="reStatus">
																		<li class="odd">
																			<h4><a href="${pageContext.request.contextPath}/usr/campaign/view/snapshot/id/${campaign.campaignId}"><c:out value="${campaign.campaignName}" /></a></h4>
																			<p class="clearfix">is <c:out value="${campaign.campaignStatus}" />; Created on <c:out value="${campaign.createdDate}" /> ; by <c:out value="${campaign.createdBy}" /></p>
																		</li>
																    </c:forEach>
																</ul>
															</td>
															<td width="200" class='quick-links'>
																<h3>Quick Links</h3>
																<ul>
																	<li>Add Contacts</li>
																	<li><a href="${pageContext.request.contextPath}/usr/campaign/new">Create Email Campaign</a></li>
																</ul>
															</td>
														</tr>
											</table>
										</div>
										<div class="recent-activities clearfix">
											<h3>Recent Activities</h3>
											<table cellpadding="0" cellspacing="0" width="100%">
												<tr>
													<td class="table-heading">Subject</td>
													<td class="table-heading">Created By</td>
													<td class="table-heading">Last Modified</td>
												</tr>
											   <c:forEach items="${homeBean.activitiesList}" var="activity" varStatus="reStatus">												
												<tr class="emailcampheader odd">
													<td class="tbl-btm-bdr"><c:out value="${activity.subject}" /></td>
													<td class="tbl-btm-bdr">by <c:out value="${activity.createdBy}" /></td>
													<td class="tbl-btm-bdr"><c:out value="${activity.createdDate}" /></td>
												</tr>
												</c:forEach>
											</table>
										</div>
									</div>
							</c:otherwise>
							</c:choose>

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

<table cellpadding="0" cellspacing="0"  width="100%">
<tr>

<td width="10%"></td>
<td width="80%" align="center" style="font-size:12px; color:#595959; padding:20px 0px 0px 0px">Copyright@companyname.com</td>
<td width="10%"></td>

</tr>
</table>


</body>
</html>
