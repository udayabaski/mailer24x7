
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Mailer247 - Campaign Snapshot</title>
	
<link href="${pageContext.request.contextPath}/styles/style.css" rel="stylesheet" type="text/css" />
<!--[if lt IE 9]><script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/script/excanvas.js"></script><![endif]-->

</head>

<body>

<form:form id="campaign" commandName="campaignSnapshotBean" action="snapshot" method="POST">

<!--content main controller table starts!-->
<table cellpadding="0" cellspacing="0" border="0" width="100%" class="content-padding">
<tr>
<td width="10%"></td>
<td width="80%" >
<table cellpadding="0" cellspacing="0" width="100%">
<tr>
<td width="100%" class="contentLayer">




<table width="98%" cellpadding="0" cellspacing="0" align="center" >
<tr>
<td class="content-heading" colspan="4"><img src="${pageContext.request.contextPath}/images/email_add.png" class="top-icon-padd" />Snapshot for <c:out value="${campaignSnapshot.campaignName}" /></td>
</tr>

<tr>
<td class="empty" colspan="4"></td>
</tr>


<tr>
<td>

<div id="main">

	<ul id="steps">
		<li id="stepDesc0" class="current"> <span class="no">1</span><span>Campaign</span>&nbsp;</li>
		<li id="stepDesc1" class="current"><span class="no">2</span><span>Email Template</span>&nbsp;</li>
		<li id="stepDesc2" class="current"><span class="no">3</span><span>Subscriber Group</span>&nbsp;</li>
	</ul>
	
	<input id="actionId" name="action" type="hidden" value="saveorupdate"/>
	<input id="campaignId" name="campaignId" type="hidden" value="-1"/>
	<input id="filename" name="filename" type="hidden" value=""/>
        <fieldset>

        <div class="hd">
			<h2>Campaign and sender</h2>
			<span  class="options"><a href="${pageContext.request.contextPath}/usr/campaign/view/step1/id/${campaignSnapshot.campaignId}">Edit</a></span>
		</div>
		<table width="100%" cellpadding="5" cellspacing="0" class="campaign-page">
	    <tr valign="top" class="odd">
			<td width="30%">Campaign Name</td>
			<td><c:out value="${campaignSnapshotBean.campaignName}" /></td>
		</tr>
	    <tr valign="top" class="even">
			<td width="30%">Subject</td>
			<td><c:out value="${campaignSnapshotBean.subject}" /></td>
		</tr>
	    <tr valign="top" class="odd">
			<td width="30%">From</td>
			<td><c:out value="${campaignSnapshotBean.senderName}" /> < <c:out value="${campaignSnapshotBean.senderEmailId}" /> ></td>
		</tr>
		</table>
		
		<c:set var="campaignType" value="${campaignSnapshotBean.campaignType}"></c:set>
		
        <div class="hd">
			<h2>Content</h2>
			<c:if test="${not empty campaignType}">
			  <span  class="options"><a href="">Edit</a></span>
			</c:if>
		</div>
		<table width="100%" cellpadding="5" cellspacing="0" class="campaign-page">
		<c:choose>
		 <c:when test="${not empty campaignType}">
	       <tr valign="top" class="odd">
			<td width="30%">Campaign Type</td>
			<td><c:out value="${campaignSnapshotBean.campaignType}" /></td>
	 	   </tr>
	       <tr valign="top" class="even">
			<td width="30%">Content</td>
			<td><a href="">Preview</a> or <a href="">edit</a></td>
		   </tr>
		  </c:when>
		  <c:otherwise>
		   <tr valign="top" class="odd">
			<td>No content found in this campaign, please click below to add/import content </td>			
	 	   </tr>
	 	   <tr valign="top" class="odd">
			<td><span  class="options"><a href="${pageContext.request.contextPath}/usr/campaign/view/step2/id/${campaignSnapshotBean.campaignId}">Add Content</a></span></td>			
	 	   </tr>
		  </c:otherwise>
		</c:choose>
		</table>
		<c:set var="listName" value="${campaignSnapshotBean.subscriberListName}"></c:set>
        <div class="hd">
			<h2>Recipients</h2>
			<span  class="options">
             <c:if test="${not empty listName}">
			  <a href="${pageContext.request.contextPath}/usr/subscriber/view/step3/id/${campaignSnapshotBean.campaignId}">Add more recipients</a>
			 </c:if>
			</span>
		</div>
		<table width="100%" cellpadding="5" cellspacing="0" class="campaign-page">
		<c:choose>
		<c:when test="${not empty listName}">
	    <tr valign="top" class="odd">
			<td width="30%">Subscriber List</td>
			<td><c:out value="${campaignSnapshotBean.subscriberListName}" /></td>
		</tr>
	    <tr valign="top" class="even">
			<td width="30%">Total</td>
			<td><c:out value="${campaignSnapshotBean.subscribersCount}" /></td>
		</tr>
		</c:when>
		<c:otherwise>
		  <tr valign="top" class="odd">
			<td>No recipients selected for this campaign, please click below to add recipients</td>			
	 	   </tr>
	 	   <tr valign="top" class="odd">
			<td><span  class="options"><a href="${pageContext.request.contextPath}/usr/subscriber/view/step3/id/${campaignSnapshotBean.campaignId}">Add Recipients</a></span></td>			
	 	   </tr>
		</c:otherwise>
		</c:choose>
		</table>
		<div class="btn">
			<table>
				<tr>
					<!-- <td width="100"><span class="previous"><a href="campaign_step2.jsp">Previous</a></span></td> -->
					<td align="center"><a href="#" class="button green" onclick="document.forms[0].submit();return false;">Save &amp; Exit</a></td>
					<td width="100"><span class="next1"><a href="campaign_step3.jsp">Next</a></span></td>
				</tr>
			</table>
		</div>
        </fieldset>

        </form>

    </div>
    
    </td></tr></table>
    
    
</td></tr></table>


<td width="10%"></td>
</tr>
</table>
<!--content main controller table ends!-->

</form:form>


</body>

</body>
</html>
