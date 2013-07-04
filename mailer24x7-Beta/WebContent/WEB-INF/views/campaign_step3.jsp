
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



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


<form:form id="campaignStep3" commandName="campaignStep3Form" action="${pageContext.request.contextPath}/usr/subscriber/save/step3" method="POST">
<form:hidden path="nextAction" id="nextAction" />
<form:hidden path="campaignId" />


<!--content main controller table starts!-->

<div class="content">
 <div class="bodyOfSpan">
 <table cellpadding="0" cellspacing="0" width="100%">
 <tr>
 <td class="contentLayer">

<table width="98%" cellspacing="0" cellpadding="0" align="center">
<tr>
<td  colspan="2" class="content-heading"><img class="top-icon-padd" src="${pageContext.request.contextPath}/images/rss.png">New Subscriber</td>

</tr>


<tr>
<td class="empty" colspan="2"></td>
</tr>

<tr>
<td colspan="2">

<form>
<fieldset>




<table>
<tr>
<td class="formtext">Select Subscriber</td>
<td width="20"></td>
<td><form:select path="subscriberGroup" cssClass="selbox2" id="sub_group">
    <form:options items="${subscriberGroupMap}" />
   </form:select> 
   <span class="errortxt"><form:errors path="subscriberGroup" /></span></td>
</tr>
<!--<tr>
<td class="formtext">View List</td>
<td></td>
<td>
<textarea cols="70" rows="5" class="textarea-item"></textarea>
</td>

</tr>-->
</table>

<div class="button-padding" align="center">

<button class="btn btn-success">
<a href="#">Previous</a></button>
<a href="#" class="button green" onclick="javascript:submitExit()">Save &amp; Exit</a>
<button class="btn btn-success"><a onclick="javascript:submitNext()" href="#">Next</a></button>
			


</div>

</fieldset>
</form>
</td>
</tr>
</table>
</td>
 </tr>
 </table>

<!--content main controller table ends!-->

</form:form>

