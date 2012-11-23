
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

<form:form id="campaign" commandName="campaignSnapshot" action="snapshot" method="POST">

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
<td class="content-heading" colspan="4"><img src="../images/email_add.png" class="top-icon-padd" />Snapshot for <c:out value="${campaignSnapshot.campaignName}" /></td>
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
			<span  class="options"><a href="">Edit</a></span>
		</div>
		<table width="100%" cellpadding="5" cellspacing="0" class="campaign-page">
	    <tr valign="top" class="odd">
			<td width="30%">Campaign Name</td>
			<td><c:out value="${campaignSnapshot.campaignName}" /></td>
		</tr>
	    <tr valign="top" class="even">
			<td width="30%">Subject</td>
			<td><c:out value="${campaignSnapshot.subject}" /></td>
		</tr>
	    <tr valign="top" class="odd">
			<td width="30%">From</td>
			<td><c:out value="${campaignSnapshot.from}" /></td>
		</tr>
		</table>
        <div class="hd">
			<h2>Content</h2>
			<span  class="options"><a href="">Edit</a></span>
		</div>
		<table width="100%" cellpadding="5" cellspacing="0" class="campaign-page">
	    <tr valign="top" class="odd">
			<td width="30%">HTML Version</td>
			<td>Using the test template (<a href="">View a preview</a> or <a href="">change template</a>)</td>
		</tr>
	    <tr valign="top" class="even">
			<td width="30%">Auto-generated plain text version</td>
			<td><a href="">Preview</a> or <a href="">edit</a></td>
		</tr>
		</table>
        <div class="hd">
			<h2>Recipients</h2>
			<span  class="options"><a href="">Add more recipients</a></span>
		</div>
		<table width="100%" cellpadding="5" cellspacing="0" class="campaign-page">
	    <tr valign="top" class="odd">
			<td width="30%">Subscriber List</td>
			<td><c:out value="${campaignSnapshot.subscriberListName}" /></td>
		</tr>
	    <tr valign="top" class="even">
			<td width="30%">Total</td>
			<td><c:out value="${campaignSnapshot.subscribersCount}" /></td>
		</tr>
		</table>
		<div class="btn">
			<table>
				<tr>
					<td width="100"><span class="previous"><a href="campaign_step2.jsp">Previous</a></span></td>
					<td align="center"><a href="#" class="button green" onclick="document.forms[0].submit();return false;">Save &amp; Exit</a></td>
					<td width="100"><span class="next1"><a href="CreateCampaignStep3.html">Next</a></span></td>
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
