
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

<form:form id="campaignImport" commandName="campaignStep2ImportForm" action="${pageContext.request.contextPath}/usr/campaign/save/import" method="POST" enctype="multipart/form-data">
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
			<td width="50">&nbsp;</td>
			<td class="txthd">
				<h4>HTML File</h4>
				<p class="greytxt">Browse and select a HTML file that you created for this campaign.</p>
				<p><form:input path="htmlFileData" type="file" id="htmlFileDataId"/></p>
			</td>
		</tr>
	    <tr valign="top">
			<td height="40" colspan="2">&nbsp;</td>
		</tr>
	    <tr valign="top">
			<td width="50">&nbsp;</td>
			<td class="txthd">
				<h4>Image files (optional)</h4>
				<p class="greytxt">Zip additional files(if any), that were created for this campaign and upload them here. This may include your logo, banner, CSS files. For more details on this topic, please refer to our help section.</p>
				<p><form:input path="zipFileData" type="file" id="htmlFileDataId"/></p>
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
