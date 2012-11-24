
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

<form:form id="campaignStep3" commandName="campaignStep3Form" action="${pageContext.request.contextPath}/usr/campaign/save/step3" method="POST">
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
<td><span class="formtext">Subscriber Group</span></td>
<td>
    <form:select path="subscriberGroup" cssClass="selbox2" id="sub_group">
									<form:options items="${subscriberGroupMap}" />
   </form:select> 
   <span class="errortxt"><form:errors path="subscriberGroup" /></span>
 </td>
<td></td>
</tr>

<tr>
<td></td>
<td><span class="formtext">Sending Options</span></td>
<td>
<table cellpadding="0" cellspacing="0">

<tr>
<td>
  
  <form:radiobutton path="sendingOption" id="sendingOption" cssClass="inputradio" value="NOW" style="position:relative; top:3px;"/><span class="chk-box-paddingfont">Send Now</span>
 </td>
 <td> 
  <form:radiobutton path="sendingOption" id="sendingOption" cssClass="inputradio" value="LATER" style="position:relative; top:3px;"/><span class="chk-box-paddingfont">Send Later</span>
 </td>
</tr>

</table>
</td>
<td></td>
</tr>


<!-- <tr>
<td></td>
<td></td>
<td><div class="form-divtext"><input value="From" type="time" class="calendarReportImg " style="position: relative; left: 40px; width: 120px; height: 19px; -moz-border-radius:0;
	-khtml-border-radius:0; height:17px;" > <input value="To" type="time" class="calendarReportImg " style="position: relative; left: 40px; width: 120px; height: 19px; -moz-border-radius:0;
	-khtml-border-radius:0; height:17px;" ></div></td>
<td></td>
</tr>-->


<tr>
<td></td>
<td></td>
<td>
  <div class="form-divtext">
      Send Test Mail ID : <form:input id="testMailId" path="testMailId" cssClass="inputwidth" type="text" value=""/> 
      <span class="errortxt"></span>
      <a onclick="sendTestMail()" class="button green" href="#">Send</a>
  </div>
 </td>
<td></td>
</tr>


</table>
          	   <div class="btn">
			<table>
				<tr>
					<td width="100"><span class="previous"><a href="CreateCampaignStep2.html">Previous</a></span></td>
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
