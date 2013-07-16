
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>





<script type="text/javascript">
	
	function submitNext(){
	  document.getElementById("nextAction").value="next";
	  document.forms[0].submit();
	}
	
	function submitExit(){
	  document.getElementById("nextAction").value="exit";
	  document.forms[0].submit();
	}
	
	function submitUpdate(){
	  document.forms[0].action = "${pageContext.request.contextPath}/usr/campaign/update/step1";
	  document.getElementById("nextAction").value="update";
	  document.forms[0].submit();
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
</div>



</td>

</tr>


<tr>
<td class="empty" colspan="2"></td>
</tr>

<tr>
<td colspan="2">
	
<form:form action="${pageContext.request.contextPath}/usr/campaign/save/step1"  commandName="campaignStep1Form" method="POST" id="campaignStep1Form">
<form:hidden path="nextAction" id="nextAction" />
<form:hidden path="campaignId" />
<form:hidden path="toPage" />

<fieldset>

	<div class="control-group">
	<label class="control-label" for="inputEmail">Campaign Name</label>
	<form:input cssClass="input-xxlarge" path="campaignName" id="campaignName" />
	<span class="errortxt"><form:errors path="campaignName" /></span>	   
	
	</div>
		
	
	
    <div class="control-group">
	   <label class="control-label" for="senderName">Sender Name</label>
	   <form:input cssClass="input-xxlarge" path="senderName" id="senderName" />
	   <span class="errortxt"><form:errors path="senderName" /></span>	   
	</div>
    
    <div class="control-group">
	   <label class="control-label" for="senderName">Sender's Email</label>
	   <form:input cssClass="input-xxlarge" path="senderEmail" id="senderEmail" />
	   <span class="errortxt"><form:errors path="senderEmail" /></span>	  
	</div>	
	  
    <div class="control-group">
	   <label class="control-label" for="replyToAddress">Reply to Address</label>
	   <form:input cssClass="input-xxlarge" path="replyToAddress" id="replyToAddress" />
	   <span class="errortxt"><form:errors path="replyToAddress" /></span>	   
	</div> 
	  
	<div class="control-group">
	   <label class="control-label" for="subject">Subject</label>
	   <form:input cssClass="input-xxlarge" path="subject" id="subject" />
	   <span class="errortxt"><form:errors path="subject" /></span>	   
	</div>
	<div id="accordion2" class="accordion">
	                <div class="advanced-options<hr>">
	                  <div class="advanced-options-heading">
	                    <a href="#collapseOne" data-parent="#accordion2" data-toggle="collapse" class="advanced-options-toggle collapsed">
	                      Show Advanced options <span class="caret" style="position:relative; top:8px;"></span>
	                    </a>
	                  </div>
	                  <div class="accordion-body collapse" id="collapseOne" style="height: 0px;">
	                    <div class="accordion-inner">
	                      Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
	                    </div>
	                  </div>
	                </div>
	
	
	              </div>
	
	
	
	</fieldset>
	
</form:form>
	</td>
	</tr>
	<tr>
		 <div align="center">
		 	
		 	<c:set var="toPage" value="${campaignStep1Form.toPage}"></c:set>
		 		  <c:choose>
		 			<c:when test="${not empty toPage}">
		 			  
		 			   <td align="center"><a href="#" class="btn btn-success" onclick="javascript:submitUpdate()">Update</a></td>
		 			</c:when>
		 			<c:otherwise>
		 			  
		 			   <td>
		 			  <div align="center"> <a href="#" class="btn btn-success" onclick="javascript:submitExit()">Save &amp; Exit</a>
		 			   <span class="next"><a href="#" onclick="javascript:submitNext()">Next</a></span></div.</td> 
		 			</c:otherwise>
		 	  </c:choose>	
		 	
		</div>
	 </tr>
	</table>
	</td>
	 </tr>
	 
	 </table>
	</div>
	
	
	
	
	
	