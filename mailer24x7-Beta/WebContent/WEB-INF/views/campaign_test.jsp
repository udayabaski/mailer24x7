
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

<script type="text/javascript">

	function submitNext(){
	  document.getElementById("nextAction").value="next";
	  document.forms[0].submit();
	}
	
	function submitPrev(){
	  document.getElementById("nextAction").value="prev";
	  document.forms[0].submit();
	}
	
	function testCampaign(){
	  document.getElementById("nextAction").value="prev";
	  document.forms[0].submit();
	}
	
</script>

</head>

<body>

<form:form id="campaignTestMailForm" commandName="campaignTestMailForm" action="${pageContext.request.contextPath}/usr/campaign/deliver/test" method="POST">
<form:hidden path="nextAction" id="nextAction"/>
<form:hidden path="campaignId"/>

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
<td class="content-heading" colspan="4"><img src="${pageContext.request.contextPath}/images/email_add.png" class="top-icon-padd" />Test your Campaign - <c:out value="${campaignSnapshot.campaignName}" /></td>
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
	
        <fieldset>

       
		<table width="100%" cellpadding="5" cellspacing="0" class="campaign-page">
	    <tr valign="top" class="odd">
			<td width="30%">Test Email Id</td>
			<td><form:input path="testMailId" />
			<span class="errortxt"><form:errors path="testMailId" /></span>
			</td>
		</tr>
	    
	     <tr>
			 <td align="center"><a href="#" class="button green" onclick="javascript:testCampaign()">Send the Test Email</a></td>
		 </tr>
		</table>
		
		<div class="btn">
			<table>
				<tr>
					<td width="100"><span class="previous"><a onclick="javascript:submitPrev()" href="#">Previous</a></span></td> 
					<!-- <td align="center"><a href="#" class="button green" onclick="document.forms[0].submit();return false;">Skip Test</a></td>-->
					<td width="100"><span class="next1"><a onclick="javascript:submitNext()" href="#">Skip Test</a></span></td>
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
