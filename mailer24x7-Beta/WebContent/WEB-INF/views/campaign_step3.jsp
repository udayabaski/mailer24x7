
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


<a href="#"  onclick="javascript:submitPrev()"  class="btn btn-success">Previous</a>
<a href="#" class="btn btn-success" onclick="javascript:submitExit()">Save &amp; Exit</a>
<a onclick="javascript:submitNext()" href="#" class="btn btn-success">Next</a>
			


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

