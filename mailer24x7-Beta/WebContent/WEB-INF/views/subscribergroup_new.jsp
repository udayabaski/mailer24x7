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

<form:form action="${pageContext.request.contextPath}/usr/subscribers/save/group"  commandName="subscriberForm" method="POST" id="subGroup" enctype="multipart/form-data">

<form:hidden path="campaignId" />
<form:hidden path="subscriberListId" />
<form:hidden path="action" />
<form:hidden path="toPage" />


<c:if test="${not empty subscriberForm.toPage}">
    <div class="info">There must be at least one subscriber to create a Campaign.</div>
</c:if>

<!--content main controller table starts!-->
<table cellpadding="0" cellspacing="0" width="100%" class="content-padding">
<tr>
<td width="10%"></td>
<td width="80%" >
<table cellpadding="0" cellspacing="0" width="100%">
<tr>
<td width="100%" class="contentLayer">
<table width="98%" cellpadding="0" cellspacing="0" align="center" >
<tr>
<td class="content-heading" colspan="4"><img src="${pageContext.request.contextPath}/images/group_gear.png" class="top-icon-padd" />Add New Subscriber</td>
</tr>

<tr>
<td class="empty" colspan="4"></td>
</tr>
<tr>
<td width="2%"></td>
<td width="20%"><span class="formtext">Name</span></td>
<td width="30%">
  <form:input path="subscriberName" id="subscriberName" cssClass="inputwidth" />
  <span class="errortxt"><form:errors path="subscriberName" /></span>
 </td>
<td width="52%"></td>
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

<tr><td colspan="4" class="empty"></td></tr>


<tr>
<td></td>
<td></td>
<td  align="left"><a onclick="document.forms[0].submit();return false;" class="button green" href="#">Submit</a> <a class="button green" href="#">Cancel</a></td>
<td></td>
</tr>




</table>
</td>
</tr>
</table>
</td>
<td width="10%"></td>
</tr>
</table>
<!--content main controller table ends!-->

</form:form>





<!--footer part starts!-->
<table cellpadding="0" cellspacing="0"  width="100%">
<tr>

<td width="10%"></td>
<td width="80%" align="center" style="font-size:12px; color:#595959;">Copyright@companyname.com</td>
<td width="10%"></td>

</tr>
</table>
<!--footer part ends!-->
</body>
</html>
