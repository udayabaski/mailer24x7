
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/jquery-ui-1.9.1.custom.min.css" />


<script type="text/javascript">
$(function(){
  $.datepicker.setDefaults(
    $.extend($.datepicker.regional[""])
  );
  $("#datepicker").datepicker({
    minDate: "0", //The minimal date that can be selected, i.e. 30 days from the "now"
    //maxDate: " +1m +1w +3d" //The maximal date that can be selected, i.e. + 1 month, 1 week, and 3 days from "now"
});
  
  
});
function changeit(txt){

			

					if(txt == "L"){

						document.getElementById("sch").style.display="block" ;
					}
					if(txt == "N"){

						document.getElementById("sch").style.display="none" ;



					}
				}

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


<div class="content">
 <div class="bodyOfSpan">
 <table cellpadding="0" cellspacing="0" width="100%">
 <tr>
 <td class="contentLayer">

<table width="98%" cellspacing="0" cellpadding="0" align="center">
<tr>
<td  colspan="2" class="content-heading"><div class="wizard-menu">
<div class="completed-wiz"><a href="#" style="text-decoration:none;"><span>1</span> Campaign Settings</a></div>
<div  class="wizard-menu-new" ><a href="#" style="text-decoration:none;"><span>2</span> Create my campaign</a></div>
<div class="completed-wiz"><a href="#" style="text-decoration:none;"><span>3</span> Subscriber List</a></div>
<div class="completed-wiz"><a href="#" style="text-decoration:none;"><span>4</span> Delivery</a></div>
</div></td>

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
<td width="10"></td>
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
<td><span ><form:radiobutton path="deliveryType" value="now" onclick="javascript:changeit('N')" checked="checked"/><p class="radio-text">Send Now</p></span></td>
<td><span class="radiospan"><form:radiobutton path="deliveryType" value="later" onclick="javascript:changeit('L')"/><p class="radio-text">Send later</p></span></td>
</tr>
</tbody></table>
</td>

</tr>

<tr>
<td></td>
<td></td>
<td><div class="form-divtext"  id="sch">Date : <input id="datepicker" type="text" /> </div></td>

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
 
</div>
<script>

document.getElementById("sch").style.display="none" ;
</script>

