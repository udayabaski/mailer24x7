
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

<form:form id="subscriberForm" commandName="subscriberForm" action="${pageContext.request.contextPath}/usr/subscriber/step3/save/group" method="POST" enctype="multipart/form-data">
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
		<li id="stepDesc1" class="current"><span class="no">2</span><span>Email Template</span>&nbsp;</li>
		<li id="stepDesc2" class="current"><span class="no">3</span><span>Subscriber Group</span>&nbsp;</li>
	</ul>
	
        <fieldset>

            <legend>Subscriber Group</legend>
            <table width="98%" cellpadding="0" cellspacing="0" align="center" >

				<tr>
				<td></td>
				<td><span class="formtext">List Name</span></td>
				<td>
				    <form:input path="subscriberName" id="subscriberName" cssClass="inputwidth" />
  					<span class="errortxt"><form:errors path="subscriberName" /></span>
				 </td>
				<td></td>
				</tr>
				
		<tr>
		<td></td>
		<td><span class="formtext">Add Using</span></td>
		<td>
		<table cellpadding="0" cellspacing="0">
		<tr>
		<td><form:radiobutton path="addOption" id="addOption" cssClass="inputradio" value="CSV"/><span class="chk-box-paddingfont">CSV</span></td>
		<td><form:radiobutton path="addOption" id="addOption" cssClass="inputradio" value="Manual"/><span class="chk-box-paddingfont">Manual</span></td>
		</tr>
		</table>
		</td>
		<td></td>
		</tr>
		


		<tr>
		<td></td>
		<td></td>
		<td>
		  <div class="form-divtext">Import HTML : <form:input path="fileData" type="file"/></div>
		  <span class="errortxt"><form:errors path="fileData" /></span>
		</td>
		<td></td>
		</tr>

		<tr>
		<td></td>
		<td></td>
		<td>
		  <div class="form-divtext"><form:textarea path="subscribers" id="subscribers" /></div>
		  <span class="errortxt"><form:errors path="subscribers" /></span>
		 </td>
		<td></td>
		</tr>
						

			</table>
          	   <div class="btn">
			<table>
				<tr>
					<!-- <td width="100"><span class="previous"><a href="CreateCampaignStep2.html">Previous</a></span></td>  
					<td align="center"><a href="#" class="button green" onclick="document.forms[0].submit();return false;">Save &amp; Exit</a></td> -->
					<td width="100"><span class="next1"><a href="#" onclick="javascript:submitNext()">Next</a></span></td>
				</tr>
			</table>
			
		</div>
 

        </fieldset>

    </div>
    
    </td></tr></table>
    
    
</td></tr></table>


</tr>
</table>
<!--content main controller table ends!-->

</form:form>


</body>

</html>
