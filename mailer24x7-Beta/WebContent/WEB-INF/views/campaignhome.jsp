
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Mailer24x7 : home</title>

	<script>
    	$(document).ready(
    		function(){
			document.getElementById("draftlist").style.display="none" ;
			document.getElementById("sentlist").style.display="block" ;
			document.getElementById("scheduledlist").style.display="none" ;
    						
    			$("#Ctype").change(
    				function(){
    					var txt = $("#Ctype option:selected").val();
    					
    					if(txt == "Draft"){
    						
						document.getElementById("draftlist").style.display="block" ;
						document.getElementById("sentlist").style.display="none" ;
						document.getElementById("scheduledlist").style.display="none" ;
    						
    					}
					if(txt == "Scheduled"){
    						
						document.getElementById("draftlist").style.display="none" ;
						document.getElementById("sentlist").style.display="none" ;
						document.getElementById("scheduledlist").style.display="block" ;
    						
    					}
					if(txt == "Sent"){
    						
						document.getElementById("draftlist").style.display="none" ;
						document.getElementById("sentlist").style.display="block" ;
						document.getElementById("scheduledlist").style.display="none" ;
    						
    					}
    					
    					
    				}	
    			
    			
    			);
    			
    		}	
    	
    	);
    </script>


<form:form action="view" method="POST" commandName="campaignsHomeBean">


	<!--content main controller table starts!-->
	<table cellpadding="0" cellspacing="0" width="100%"
		class="content-padding">
		<tr>
			<td width="10%"></td>
			<td width="80%" valign="top">
				<table cellpadding="0" cellspacing="0" width="100%">
				<tr>
				<td>
				<select id="Ctype">
				
				<option value="Sent">Sent</option>
				<option value="Scheduled">Scheduled</option>
				<option value= "Draft">Draft</option>
				</select>
				</td>
				</tr>
				
					<tr>

			
 			
					

<td width="100%" class="contentLayer" >


	<div id="tabs" class="tabbed_area">


									
									<div class="right-link">
										<a href="${pageContext.request.contextPath}/usr/campaign/new" class="button grey-btn mar1" ><img 

src="${pageContext.request.contextPath}/images/add.png" width="16" height="15" alt="" />New Campaign</a>
										<a href="${pageContext.request.contextPath}/usr/campaign/new" class="button grey-btn mar1" ><img 

src="${pageContext.request.contextPath}/images/add.png" width="16" height="15" alt="" />New A/B Campaign</a>
									</div>

									<div id="draftlist" class="content">
										<table class="table table-striped" style="white-space:nowrap; border:1px solid #ebebeb; font-size: 13px; box-

shadow: 0 0px 0px #CDCDCD;    border-radius: 5px;">
											

					   <c:set var="draftCampaignsCount" value="${fn:length(campaignsHomeBean.draftCampaigns)}"></c:set>
       
        				<c:choose>
        				<c:when test="${draftCampaignsCount == 0}">
       						<tr valign="top" >
								<td class="pad1"><img src="${pageContext.request.contextPath}/images/img-campaign.png" width="75" height="72" alt="" /></td>
								<td><h1>Design your campaign</h1>
								<p>Get started by creating your first email campaign. We'll walk through the entire process, and you can choose how you'd like to pay 

before you send.</p>
								<p><a href="${pageContext.request.contextPath}/usr/campaign/new" class="button green mar1 bg" >Create Campaign<img 

src="${pageContext.request.contextPath}/images/arrows.png" width="20" height="20" alt="" class="arrows" /></a></p></td>
							</tr>
       					</c:when>
       					<c:otherwise>

													  <tr>
    <th>Campaign Name</th>
    <th>Last Modified</th>
    <th>Actions</th>
  </tr>
													
								<c:forEach items="${campaignsHomeBean.draftCampaigns}" var="campaign" varStatus="reStatus">
								
														<tr onmouseout="this.className='emailcampheader odd'"
															onmouseover="this.className='emailcampHeaderHover'"
															class="emailcampheader odd">

															<td class="tbl-btm-bdr"><a
																

href="${pageContext.request.contextPath}/usr/campaign/view/snapshot/id/${campaign.campaignId}" ><c:out value="${campaign.campaignName}" /></a>
,by <c:out value="${campaign.createdBy}" /> </td>
															
															
															<td class="tbl-btm-bdr">
<img src="${pageContext.request.contextPath}/images/icon-shedule.png" class="email-list-icons" style="position:relative; top:2px;"><c:out value="${campaign.lastModifiedTime}" /></td>
															<td class="tbl-btm-bdr" width="100">
															  <a 

href="${pageContext.request.contextPath}/usr/campaign/view/snapshot/id/${campaign.campaignId}" class="add" title="view">View</a> 
															  <a 

href="${pageContext.request.contextPath}/usr/campaign/view/snapshot/id/${campaign.campaignId}" class="edit" title="edit">Edit</a> 
															  <a href="clone/id/<c:out value="${campaign.campaignId}" />" 

class="clone" title="Clone">Clone</a> 
															  <a 

href="${pageContext.request.contextPath}/usr/campaign/delete/id/${campaign.campaignId}" class="delete" title="Delete">Delete</a></td>
														</tr>


								</c:forEach>
													
										 </c:otherwise>
										</c:choose>
										</table>
										<div class="pagination">
											<ul>
												
											</ul>
										</div>
									</div>
									<div id="scheduledlist" class="content">
										<table class="table table-striped" style="white-space:nowrap; border:1px solid #ebebeb; font-size: 13px; box-

shadow: 0 0px 0px #CDCDCD;    border-radius: 5px;">
											<c:set var="scheduledCampaignsCount" value="${fn:length

(campaignsHomeBean.scheduledCampaigns)}"></c:set>
												
											<c:choose>
        									<c:when test="${scheduledCampaignsCount == 0}">
       											<tr>
														<td colspan="4" class="">No Campaigns are
															scheduled. <a 

href="${pageContext.request.contextPath}/usr/campaign/new"><b>Schedule</b></a>
															a campaign.
														</td>
													</tr>
       										</c:when>
       										<c:otherwise>
       												
       												  <tr>
    <th>Campaign Name</th>
    <th>Scheduled On</th>
    <th>Actions</th>
  </tr>
								<c:forEach items="${campaignsHomeBean.scheduledCampaigns}" var="campaign" varStatus="reStatus">
								
														<tr onmouseout="this.className='emailcampheader odd'"
															onmouseover="this.className='emailcampHeaderHover'"
															class="emailcampheader odd">

															<td class="tbl-btm-bdr"><a
																

href="${pageContext.request.contextPath}/usr/campaign/view/snapshot/id/${campaign.campaignId}"><c:out value="${campaign.campaignName}" /></a>
,by  <c:out value="${campaign.createdBy}" /> </td>
															
															<td class="tbl-btm-bdr"><img 

src="${pageContext.request.contextPath}/images/icon-shedule.png" class="email-list-icons" style="position:relative; top:2px;"><c:out value="${campaign.scheduledOn}" /></td>
															<td class="tbl-btm-bdr" width="100">
															  <a 

href="${pageContext.request.contextPath}/usr/campaign/view/snapshot/id/${campaign.campaignId}" class="add" title="view">View</a> 
															  <a 

href="${pageContext.request.contextPath}/usr/campaign/view/snapshot/id/${campaign.campaignId}" class="edit" title="edit">Edit</a> 
															  <a href="clone/id/<c:out value="${campaign.campaignId}" />" 

class="clone" title="Clone">Clone</a> 
															  <a 

href="${pageContext.request.contextPath}/usr/campaign/delete/id/${campaign.campaignId}" class="delete" title="Delete">Delete</a></td>
														</tr>


								</c:forEach>
       											       										

	       										   
											</c:otherwise>
											</c:choose>	

										</table>
									</div>
									<div id="sentlist" class="content">
										  <table class="table table-striped" style="white-space:nowrap; border:1px solid #ebebeb; font-size: 13px; box-

shadow: 0 0px 0px #CDCDCD;border-radius: 5px;">
											
												<c:set var="completedCampaignsCount" value="${fn:length

(campaignsHomeBean.completedCampaigns)}"></c:set>
												
											<c:choose>
        									<c:when test="${completedCampaignsCount == 0}">
       											<tr>
														<td colspan="4" class="">No Campaigns are sent.</td>
													</tr>
       										</c:when>
       										<c:otherwise>
													
													  <tr>
    														<th>Campaign Name</th>
    														<th>Status</th>
    														<th>Actions</th>
 													 </tr>
													
								<c:forEach items="${campaignsHomeBean.completedCampaigns}" var="campaign" varStatus="reStatus">
								
														<tr onmouseout="this.className='emailcampheader odd'"
															onmouseover="this.className='emailcampHeaderHover'"
															class="emailcampheader odd">

															<td class="tbl-btm-bdr"><a
																

href="${pageContext.request.contextPath}/usr/campaign/view/snapshot/id/${campaign.campaignId}"><c:out value="${campaign.campaignName}" /></a>
																by <c:out value="${campaign.createdBy}" /> , on 

<c:out value="${campaign.lastModifiedTime}" />
																
																</td>
															
															<td class="tbl-btm-bdr">
<img src="${pageContext.request.contextPath}/images/icon-email.png" class="email-list-icons" style="position:relative; top:2px;" title= "<c:out value="${campaign.opened}" />" />&nbsp;
<img src="${pageContext.request.contextPath}/images/icon-email.png" class="email-list-icons" style="position:relative; top:2px;" title= "<c:out value="${campaign.clicked}" />" >&nbsp;<img 

src="${pageContext.request.contextPath}/images/icon-bounce.png" class="email-list-icons" style="position:relative; top:2px;" title ="<c:out value="${campaign.bounced}" />" /></td>
															<td class="tbl-btm-bdr" width="100">
															  <a 

href="${pageContext.request.contextPath}/usr/campaign/view/snapshot/id/${campaign.campaignId}" class="add" title="view">View</a> 
															  <a 

href="${pageContext.request.contextPath}/usr/campaign/view/snapshot/id/${campaign.campaignId}" class="edit" title="edit">Edit</a> 
															  <a href="clone/id/<c:out value="${campaign.campaignId}" />" 

class="clone" title="Clone">Clone</a> 
															  <a 

href="${pageContext.request.contextPath}/usr/campaign/delete/id/${campaign.campaignId}" class="delete" class="Delete">Delete</a></td>
														</tr>


								</c:forEach>
													
													</c:otherwise>
											</c:choose>	
											

										</table>
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





