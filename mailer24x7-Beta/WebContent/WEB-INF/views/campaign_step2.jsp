
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
	  document.forms[0].getElementById("nextAction").value="prev";
	  document.forms[0].submit();
	}
	
	function submitNext(){
	  document.forms[0].getElementById("nextAction").value="next";
	  document.forms[0].submit();
	}
	
	function submitExit(){
	  document.forms[0].getElementById("nextAction").value="exit";
	  document.forms[0].submit();
	}
	
</script>


</head>

<body>

<form:form id="campaignStep2" commandName="campaignStep2Form" action="${pageContext.request.contextPath}/usr/campaign/save/step2" method="POST">
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
	
	<input id="actionId" name="action" type="hidden" value="saveorupdate"/>
	<input id="campaignId" name="campaignId" type="hidden" value="-1"/>
	<input id="filename" name="filename" type="hidden" value=""/>
        <fieldset>

            

		<table width="98%" cellpadding="0" cellspacing="0" align="center" class="campaign-page">
	    <tr valign="top">
			<td width="20"><form:radiobutton path="contentType" value="0"/> </td>
			<td class="txthd">
				<h4>Import my own design</h4>
				<p class="greytxt">Import a HTML email design from your computer or the web and add a plain text version for your recipients with older or mobile email clients. We provide detailed reporting on the results.</p>
			</td>
		</tr>
	    <tr valign="top">
			<td width="20"><form:radiobutton path="contentType" value="1"/></td>
			<td class="txthd">
				<h4>Plain text only</h4>
				<p class="greytxt">Plain text ensures that your message will be viewable by all re3cipients but means you can't add any formatting or style to your email. Only basic reporting will be available.</p>
			</td>
		</tr>
	    <tr valign="top">
			<td width="20"><form:radiobutton path="contentType" value="2"/></td>
			<td class="txthd">
				<h4>HTML Editor</h4>
				<p class="greytxt">Design a HTML email using our HTML editor.</p>
			</td>
		</tr>
	    <tr valign="top">
			<td width="20"><form:radiobutton path="contentType" value="3"/></td>
			<td class="txthd">
				<h4>New Templates</h4>
				<p class="greytxt">Design a HTML email using our template layouts.</p>
			</td>
		</tr>
	    <tr valign="top">
			<td width="20"><form:radiobutton path="contentType" value="4"/></td>
			<td class="txthd">
				<h4>Use one of my predefined Templates</h4>
				<p class="greytxt">Add content to one of your pre-designed email templates.</p>
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
