
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Mailer247 - Create Campaign Step2</title>

<link href="${pageContext.request.contextPath}/styles/style.css" rel="stylesheet" type="text/css" />
<!--[if lt IE 9]><script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/script/excanvas.js"></script><![endif]-->
<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery-1.8.3.min.js"></script>

<script type="text/javascript">

	function submitPrev(){
	  document.getElementById("nextAction").value="prev";
	  document.forms[0].submit();
	}
	
	function submitNext(){
	  document.getElementById("nextAction").value="next";
	  document.forms[0].submit();
	}
	
	function submitExit(){
	  document.getElementById("nextAction").value="exit";
	  document.forms[0].submit();
	}
	
</script>


</head>

<body>

<form:form id="campaignDeliveryForm" commandName="campaignDeliveryForm" action="${pageContext.request.contextPath}/usr/campaign/deliver/schedule" method="POST">
<form:hidden path="nextAction" id="nextAction" />
<form:hidden path="campaignId" />

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
<td class="content-heading" colspan="4"><img src="${pageContext.request.contextPath}/images/email_add.png" class="top-icon-padd" />Email Content</td>
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
		<li id="stepDesc2" class=""><span class="no">3</span><span>Subscriber Group</span>&nbsp;</li>
	</ul>
	
        <fieldset>

            

		<table width="98%" cellpadding="0" cellspacing="0" align="center" class="campaign-page">
	    <tr valign="top">
			<td width="20"><form:radiobutton path="deliveryType" value="now"/> </td>
			<td class="txthd">
				<h4>Deliver the campaign now</h4>
				<p class="greytxt">Your campaign will be queued on our delivery servers and sent to your recipients immediately.</p>
			</td>
		</tr>
		<tr valign="top">
			<td width="20">&nbsp;</td>
			<td class="txthd">
				<h6>Send confirmation mail to</h6>&nbsp;&nbsp;&nbsp;<form:input path="confirmationMailIdNow" />&nbsp;&nbsp;&nbsp;when the campaign has been sent.
			</td>
		</tr>
	    <tr valign="top">
			<td width="20"><form:radiobutton path="deliveryType" value="later"/></td>
			<td class="txthd">
				<h4>Deliver at the following time</h4>
				<p class="greytxt">You can always change the scheduled delivery time before the campaign is sent.</p>
			</td>
		</tr>
		<tr valign="top">
			<td width="20">&nbsp;</td>
			<td class="txthd">
				<h6>Date</h6>&nbsp;&nbsp;&nbsp;<form:input path="date" />
			</td>
		</tr>
		<tr valign="top">
			<td width="20">&nbsp;</td>
			<td class="txthd">
				<h6>Time</h6>&nbsp;&nbsp;&nbsp;<form:select path="timeHour" cssClass="selbox2">
				  <form:options items="${hoursMap}" />
   			   </form:select>
   			   <form:select path="timeMinute" cssClass="selbox2">
				  <form:options items="${minutesMap}" />
   			   </form:select>
			</td>
		</tr>
		<tr valign="top">
			<td width="20">&nbsp;</td>
			<td class="txthd">
				<h6>Timezone</h6>&nbsp;&nbsp;&nbsp;<form:select path="timezone" cssClass="selbox2">
				  <form:options items="${timezoneMap}" />
   			   </form:select>
			</td>
		</tr>
		<tr valign="top">
			<td width="20">&nbsp;</td>
			<td class="txthd">
				<h6>Send confirmation mail to</h6>&nbsp;&nbsp;&nbsp;<form:input path="confirmationMailIdLater" />&nbsp;&nbsp;&nbsp;when the campaign has been sent.
			</td>
		</tr>
	    </table>
		<div class="btn">
			<table>
				<tr>
					<td width="100"><span class="previous"><a href="#" onclick="javascript:submitPrev()">Previous</a></span></td>
					<td align="center"><a href="#" class="button green" onclick="javascript:submitExit()">Save &amp; Exit</a></td>
					<td width="100"><span class="next1"><a href="#" onclick="javascript:submitNext()">Next</a></span></td>
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
</html>
