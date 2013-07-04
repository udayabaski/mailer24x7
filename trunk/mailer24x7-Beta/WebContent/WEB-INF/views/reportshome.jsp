
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<form:form action="view" method="POST" commandName="campaignsHomeBean">
<div class="content">


<div id="email-list">
  
    <div class="bodyOfSpan">


	<!--content main controller table starts!-->
	<div id="sublist" class="content">
	<table class="table table-striped" style="white-space:nowrap; border:1px solid #ebebeb; font-size: 13px; box-shadow: 0 0px 0px #CDCDCD;
		    border-radius: 5px;">
	    

							

								

									
										
											
										<c:set var="completedCampaignsCount" value="${fn:length(campaignsHomeBean.completedCampaigns)}"></c:set>
												
											<c:choose>
        									<c:when test="${completedCampaignsCount == 0}">
       											<tr>
														<td colspan="4" class="">No Campaigns are sent.</td>
													</tr>
       										</c:when>
       										<c:otherwise>
													<tr>
													
														<th>Name</th>
														<th>Created By</th>
														<th>Last Updated</th>
														<th>Opened</th>
														<th>Clicked</th>
														<th>Bounced</th>
														<th>Actions</th>
													</tr>
													
								<c:forEach items="${campaignsHomeBean.completedCampaigns}" var="campaign" varStatus="reStatus">
								
														<tr onmouseout="this.className='emailcampheader odd'"
															onmouseover="this.className='emailcampHeaderHover'"
															class="emailcampheader odd">

															<td class="tbl-btm-bdr"><a
																

href="${pageContext.request.contextPath}/usr/reports/view/campaign/id/${campaign.campaignId}"><c:out value="${campaign.campaignName}" /></a></td>
															<td class="tbl-btm-bdr"><c:out value="${campaign.createdBy}" 

/></td>
															<td class="tbl-btm-bdr"><c:out 

value="${campaign.lastModifiedTime}" /></td>
															<td class="tbl-btm-bdr"><c:out value="${campaign.opened}" 

/></td>
															<td class="tbl-btm-bdr"><c:out value="${campaign.clicked}" 

/></td>
															<td class="tbl-btm-bdr"><c:out value="${campaign.bounced}" 

/></td>
															<td class="tbl-btm-bdr" width="100">
															  <a href="view/detail/id/<c:out 

value="${campaign.campaignId}" />" class="add" title="view">View</a> 
															  <a href="edit/id/<c:out value="${campaign.campaignId}" />" 

class="edit" title="edit">Edit</a> 
															  <a href="clone/id/<c:out value="${campaign.campaignId}" />" 

class="clone" title="Clone">Clone</a> 
															  <a href="delete/id/<c:out value="${campaign.campaignId}" />" 

class="delete" class="Delete">Delete</a></td>
														</tr>


								</c:forEach>
													
													</c:otherwise>
											</c:choose>	
											

		
	</table>
	<!--content main controller table ends!-->
</div></div></div></div>
</form:form>





