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
	
	function submitExit(){
	  document.getElementById("nextAction").value="exit";
	  document.forms[0].submit();
	}

    function submitPreview(){
      if(document.getElementById("contentTypeInt").value == 1){
        writeTextConsole(document.getElementById("content").value);
      } else {
        writeHtmlConsole(document.getElementById("content").value);
      }      
	}
	
	function writeHtmlConsole(content) {
      top.consoleRef=window.open('','Preview',
       'width=600,height=650'
        +',menubar=0'
   		+',toolbar=1'
   		+',status=0'
   		+',scrollbars=1'
   		+',resizable=1')
 	 top.consoleRef.document.writeln(
  		'<html><head><title>Preview</title></head>'
   			+'<body bgcolor=white onLoad="self.focus()">'
   					+content
   		+'</body></html>'
 		)
 		top.consoleRef.document.close()
	}
	
	function writeTextConsole(content) {
      top.consoleRef=window.open('','Preview',
       'width=600,height=650'
        +',menubar=0'
   		+',toolbar=1'
   		+',status=0'
   		+',scrollbars=1'
   		+',resizable=1')
 	  top.consoleRef.document.writeln(content)
 	  top.consoleRef.document.close()
	}
	
</script>


 <!--Content area tiles Starts-->  
<div class="content">
 <div class="bodyOfSpan">
 <table cellpadding="0" cellspacing="0" width="100%">
 <tr>
 <td class="contentLayer">

<table width="98%" cellspacing="0" cellpadding="0" align="center">
<tr>
<td  colspan="2" class="content-heading"><img class="top-icon-padd" src="img/rss.png">Campaign and sender<button class="btn btn-success" type="button" style="float:right"> Campaign and sender</button></td>

</tr>


<tr>
<td class="empty" colspan="2"></td>
</tr>

<tr>
<td colspan="2">

<form:form id="campaign" commandName="campaignSnapshotBean" action="${pageContext.request.contextPath}/usr/campaign/deliver/schedule" method="POST">
<form:hidden path="content" id="content"/>
<form:hidden path="contentTypeInt" id="contentTypeInt"/>
<form:hidden path="nextAction" id="nextAction" />
<form:hidden path="campaignId" />

<fieldset>


<table cellpadding="6" cellspacing="0" width="98%">

<tr><td colspan="3" class="newwizard-heading"><h5>Campaign and sender</h5></td></tr>

<tr>
<td width="25%" class="formtext">Name</td>
<td width="10"></td>
<td class="formtext"><c:out value="${campaignSnapshotBean.campaignName}" /></td>
</tr>
<tr>
<td width="25%" class="formtext">Subject</td>
<td width="10"></td>
<td class="formtext"><c:out value="${campaignSnapshotBean.subject}" /></td>
</tr>
<tr>
<td class="formtext">Sender</td>
<td width="10" class="formtext"></td>
<td class="formtext"><c:out value="${campaignSnapshotBean.senderName}" /></td>
</tr>
<tr>
<td class="formtext">From</td>
<td width="10"></td>
<td class="formtext"><c:out value="${campaignSnapshotBean.senderEmailId}" /></td>
</tr>


<tr><td colspan="3" class="newwizard-heading"><h5>Content</h5></td></tr>

<c:set var="campaignType" value="${campaignSnapshotBean.campaignType}"></c:set>



<tr>
<td width="25%" class="formtext">Campaign Type</td>
<td width="10"></td>
<td class="formtext"><c:out value="${campaignSnapshotBean.campaignType}" /></td>
</tr>

<c:if test="${not empty campaignType}">
<tr>
<td class="formtext">Content</td>
<td width="10" class="formtext"></td>
<td class="formtext"><a href="#" onclick="javascript:submitPreview()" class="newwizard-link">Preview</a>| <a href="${pageContext.request.contextPath}/usr/campaign/edit/type/${campaignSnapshotBean.contentTypeInt}/id/${campaignSnapshotBean.campaignId}" class="newwizard-link1">Edit</a></td>
</tr>
</c:if>


<tr><td colspan="3" class="newwizard-heading"><h5>Recipients</h5></td></tr>

<tr>
<td width="25%" class="formtext">Subscriber List</td>
111222<td width="10"></td>
<td class="formtext"><c:out value="${campaignSnapshotBean.subscriberListName}" /></td>
</tr>
<tr>
<td class="formtext">Total List</td>
<td width="10" class="formtext"></td>
<td class="formtext"><c:out value="${campaignSnapshotBean.subscribersCount}" /></td>
</tr>

<tr>

<td class="formtext">Sending Options</td>
<td></td>
<td>
<table cellspacing="0" cellpadding="0">
<tbody><tr>
<td><span ><form:radiobutton path="deliveryType" value="later"/><p class="radio-text">Send Now</p></span></td>
<td><span class="radiospan"><form:radiobutton path="deliveryType" value="later"/><p class="radio-text">Send later</p></span></td>
</tr>
</tbody></table>
</td>

</tr>

<tr>
<td></td>
<td></td>
<td><div class="form-divtext" ><input type="time" style="position: relative; left: 10px; width: 120px; height: 19px; -moz-border-radius:0;
	-khtml-border-radius:0; height:21px;" class="calendarReportImg " value="From"> <input type="time" style="position: relative; left: 20px; width: 120px; height: 19px; -moz-border-radius:0;
	-khtml-border-radius:0; height:21px;" class="calendarReportImg " value="To"></div></td>

</tr>
</table>


<div class="button-padding" align="center"><button class="btn btn-success">Submit</button></div>

</fieldset>
</form:form>
</td>
</tr>



</table>





</td>
 </tr>
 </table>














 
 
 </div>
 <!-- #footer -->
</div>

