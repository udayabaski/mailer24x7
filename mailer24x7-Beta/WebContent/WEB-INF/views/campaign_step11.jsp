
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Mailer247 - Create Campaign Step1</title>

<link href="${pageContext.request.contextPath}/styles/style.css" rel="stylesheet" type="text/css" />
<!--[if lt IE 9]><script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/script/excanvas.js"></script><![endif]-->
<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery-1.8.3.min.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	$("#ad-search-btn").click(function() {
		if($("#advance-search").css('display')=='none') {
			$("#advance-search").slideDown('slow');
		} else {
			$("#advance-search").slideUp('slow');
		}
	});
});
</script>

<script type="text/javascript">
	
	function submitNext(){
	  document.getElementById("nextAction").value="next";
	  document.forms[0].submit();
	}
	
	function submitExit(){
	  document.getElementById("nextAction").value="exit";
	  document.forms[0].submit();
	}
	
	function submitUpdate(){
	  document.forms[0].action = "${pageContext.request.contextPath}/usr/campaign/update/step1";
	  document.getElementById("nextAction").value="update";
	  document.forms[0].submit();
	}
	
</script>

</head>

<body>

<form:form action="${pageContext.request.contextPath}/usr/campaign/save/step1"  commandName="campaignStep1Form" method="POST" id="campaignStep1Form">
<form:hidden path="nextAction" id="nextAction" />
<form:hidden path="campaignId" />
<form:hidden path="toPage" />

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
<td class="content-heading" colspan="4"><img src="${pageContext.request.contextPath}/images/email_add.png" class="top-icon-padd" />Add New email Campaign</td>
</tr>

<tr>
<td class="empty" colspan="4"></td>
</tr>


<tr>
<td>

<div id="main">

	<ul id="steps">
		<li id="stepDesc0" class="current"> <span class="no">1</span><span>Campaign</span>&nbsp;</li>
		<li id="stepDesc1" class=""><span class="no">2</span><span>Email Template</span>&nbsp;</li>
		<li id="stepDesc2" class=""><span class="no">3</span><span>Subscriber Group</span>&nbsp;</li>
	</ul>
	
        <fieldset>

            <legend>Campaign Headers</legend>

           
	   <table width="98%" cellpadding="0" cellspacing="0" align="center">
	   
	   <tr>
	   <td width="2%"></td>
	   <td width="20%"><span class="formtext">Campaign Name:</span></td>
	   <td width="38%">
           <form:input path="campaignName" id="campaignName" />
           <span class="errortxt"><form:errors path="campaignName" /></span>
	    </td>
	   <td width="44%"></td>
	   </tr>
	   
	   
	   <tr>
	   <td></td>
	   <td><span class="formtext">Sender Name:</span></td>
	   <td>
	     <form:input path="senderName" id="senderName" />
         <span class="errortxt"><form:errors path="senderName" /></span>
	    </td>
	   <td></td>
	   </tr>
	   
	   <tr>
	   <td></td>
	   <td><span class="formtext">Sender Email:</span></td>
	   <td>
	     <form:input path="senderEmail" id="senderEmail" />
         <span class="errortxt"><form:errors path="senderEmail" /></span>
	    </td>
	   <td></td>
	   </tr>
	   
	   <tr>
	   <td></td>
	   <td><span class="formtext">Reply to Address: </span></td>
	   <td>
	     <form:input path="replyToAddress" id="replyToAddress" />
         <span class="errortxt"><form:errors path="replyToAddress" /></span>
	    </td>
	   <td></td>
	   </tr>
	   
	   
	   <tr>
	   <td></td>
	   <td><span class="formtext">Subject</span></td>
	   <td>
	     <form:input path="subject" id="subject" />
         <span class="errortxt"><form:errors path="subject" /></span>
	  </td>
	   <td></td>
	   </tr>
	   
	   <tr>
	   <td></td>
	   <td colspan="2"><span class="formtext"><a href="javascript:;" id="ad-search-btn">Advanced Options</a></span></td>
	   <td></td>
	   </tr>
	   
	   </table>
	   
		<table width="98%" cellpadding="0" cellspacing="0" align="center" id="advance-search">
		<tr>
		<td width="10"></td>
		<td width="30">
		  <form:checkbox path="addGoogleAnalytics" />
		</td>
		<td><span class="formtext"> Add Google Analytics to track URLs in your Campaign </span></td>
		</tr>
		<tr>
		<td width="10"></td>
		<td width="30"></td>
		<td class="greytxt">Monitor traffic of your website through Campaigns (Only if you've configured your website in Google Analytics) </td>
		</tr>
		<tr>
		<td width="10"></td>
		<td width="30"><form:checkbox path="personalizeToAddress" /></td>
		<td><span class="formtext">  Personalize your "To" address  </span></td>
		</tr>
		<tr>
		<td width="10"></td>
		<td width="30"></td>
		<td class="greytxt">Personalize your email campaign by showing importance to your subscribers with their first name and last name.</td>
		</tr>
		</table>
		<div class="btn">
			<table>
				<tr>
					
  				<c:set var="toPage" value="${campaignStep1Form.toPage}"></c:set>
  				
				<c:choose>
				   <c:when test="${not empty toPage}">
				    <td width="100">&nbsp;</td>
					<td align="center"><a href="#" class="button green" onclick="javascript:submitUpdate()">Update</a></td>
				   </c:when>
				   <c:otherwise>
				    <td width="100">&nbsp;</td>
					<td align="center"><a href="#" class="button green" onclick="javascript:submitExit()">Save &amp; Exit</a></td>
					<td width="100"><span class="next1"><a href="#" onclick="javascript:submitNext()">Next</a></span></td> 
				   </c:otherwise>
				</c:choose>
					
				</tr>
			</table>
			
		</div>

        </fieldset>

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
