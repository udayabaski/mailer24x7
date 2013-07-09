<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
	
	function submitSave(){
	  var ctx = "${pageContext.request.contextPath}";
	  document.forms[0].action=ctx+"/usr/subscriber/save/group";
	  document.getElementById("nextAction").value="save";
	  document.forms[0].submit();
	}
	
	function show(f){

	if(f=='csv'){

	document.getElementById('csv').style.visibility = 'visible';
	document.getElementById('man').style.visibility = 'hidden';

	}else{
	document.getElementById('csv').style.visibility = 'hidden';
	document.getElementById('man').style.visibility = 'visible';
	}
	}
	function onLoad(){
	show('csv');
	}
</script>


<body onload="javascript:onLoad()">

<form:form id="subscriberForm" commandName="subscriberForm" action="${pageContext.request.contextPath}/usr/subscriber/step3/save/group" method="POST" enctype="multipart/form-data">
<form:hidden path="nextAction" id="nextAction" />
<form:hidden path="campaignId" />
<form:hidden path="toPage" />
<form:hidden path="subscriberListId" />

















 <c:set var="toPage" value="${subscriberForm.toPage}"></c:set>
 <c:set var="subListId" value="${subscriberForm.subscriberListId}"></c:set>
 <c:if test="${toPage != 'groups'}">
 	<ul id="steps">
 		<li id="stepDesc0" class="current"> <span class="no">1</span><span>Campaign</span>&nbsp;</li>
 		<li id="stepDesc1" class="current"><span class="no">2</span><span>Email Template</span>&nbsp;</li>
 		<li id="stepDesc2" class="current"><span class="no">3</span><span>Subscriber Group</span>&nbsp;</li>
 	</ul>
	</c:if>
	
<div class="content">
 <div class="bodyOfSpan">

 
 <table cellpadding="0" cellspacing="0" width="100%">
 <tr>
 <td class="contentLayer">

<table width="98%" cellspacing="0" cellpadding="0" align="center">

<tr>
<td  colspan="2" class="content-heading"><img class="top-icon-padd" src="${pageContext.request.contextPath}/images/rss.png">New Subscriber </td>

</tr>

<tr>
<td class="empty" colspan="2"></td>
</tr>

<tr>
   <td><label class="control-label" for="inputEmail">List Name</label></td>
  <td> <c:choose>
   				   <c:when test="${subListId != 0}">
   				    <span class="input-xxlarge"><b><c:out value="${subscriberForm.subscriberName}" /></b></span>
   				   </c:when>
   				   <c:otherwise>
   				    <form:input path="subscriberName" id="subscriberName" cssClass="inputwidth" />
     					<span class="input-xxlarge"><form:errors path="subscriberName" /></span>
   				   </c:otherwise>
				</c:choose></td>
   
  </tr>
  <tr>
<td class="empty" colspan="2"></td>
</tr>

  <tr>
<td><label class="control-label" for="inputEmail">Add using</label></td>

<td>
<span ><form:radiobutton path="addOption" id="addOption" cssClass="inputradio" value="Manual" onclick="javascript:show('man')" /><p class="radio-text-padding">Manual</p></span>
		
<span class="radiospan"><form:radiobutton path="addOption" id="addOption" cssClass="inputradio" value="CSV" onclick="javascript:show('csv')"/><p class="radio-text-padding">CSV</p></span>
</td>


</tr>
  
  <tr>
  <td ></td>
  
  <td width="300" align="left"  colspan="2">

<div id="csv">
		  <div class="custom_file_upload" ><form:input path="fileData" type="file"/></div>
		  <span class="errortxt" ><form:errors path="fileData" /></span></div>
		  
		  
		  
		  <div id="man">
		  		  <div class="form-divtext"><form:textarea style="width:300px; height:150px;" path="subscribers" id="subscribers" onfocus="if(this.value == 'emailids') {this.value=''}" onblur="if(this.value == ''){this.value ='emailids'}" value="arun"/></div>
		  <span class="errortxt"><form:errors path="subscribers" /></span></div>
		  
		  


</td>
  </tr>


<tr><td colspan="2" style="height:20px;"></td></tr>

</table>

<table>
			
				<tr><td colspan="2">
				 
				 <c:set var="toPage" value="${subscriberForm.toPage}"></c:set>
  				
				<c:choose>
				   <c:when test="${toPage eq 'groups'}">
				    <td width="190">&nbsp;</td>
					<td align="center"><a href="#" class="btn btn-success" onclick="javascript:submitSave()">Save</a> &nbsp; </td>
						<td align="center"> <a class="btn" onclick="javacript:window.history.back()" href="#">Cancel</a></td>
				   </c:when>
				   <c:otherwise>
					<!-- <td width="190"><span class="previous"><a href="CreateCampaignStep2.html">Previous</a></span></td>  
					<td align="center"><a href="#" class="button green" onclick="document.forms[0].submit();return false;">Save &amp; Exit</a></td> -->
					<td width="190"><span class="next1"><a href="#" onclick="javascript:submitNext()">Next</a></span></td>
				   </c:otherwise>
				</c:choose>
				
			  </td> </tr>
			
	</table>	


</td>
 </tr>
 </table>

</div>




</form:form>

</body>

