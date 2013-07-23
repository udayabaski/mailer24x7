

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<script type="text/javascript">
	
	function submitUpdate(){
	  document.forms[0].submit();
	}
	
</script>



<body>

<form:form action="${pageContext.request.contextPath}/usr/admin/show/user"  commandName="userForm" method="POST" id="userForm">

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
<td class="empty" colspan="4"></td>
</tr>


<tr>
<td>

<div id="main">

        <fieldset>

            <legend>User Details</legend>

           
	   <table width="98%" cellpadding="0" cellspacing="0" align="center">
	   
	   <tr>
	   <td width="2%"></td>
	   <td width="20%"><span class="formtext">Full Name:</span></td>
	   <td width="38%">
           <form:input path="fullName" id="fullName" />
           <span class="errortxt"><form:errors path="fullName" /></span>
	    </td>
	   <td width="44%"></td>
	   </tr>
	   
	   
	   <tr>
	   <td></td>
	   <td><span class="formtext">EmailId:</span></td>
	   <td>
	     
	     <form:input path="emailId" id="emailId" />
         <span class="errortxt"><form:errors path="emailId" /></span>
	    </td>
	   <td></td>
	   </tr>
	   
	   <tr>
	   <td></td>
	   <td><span class="formtext">Display Name:</span></td>
	   <td>
	     <form:input path="displayName" id="displayName" />
         <span class="errortxt"><form:errors path="displayName" /></span>
	    </td>
	   <td></td>
	   </tr>
	   
	   <tr>
	   <td></td>
	   <td><span class="formtext">Contact Number: </span></td>
	   <td>
	     <form:input path="contactNumber" id="contactNumber" />
         <span class="errortxt"><form:errors path="contactNumber" /></span>
	    </td>
	   <td></td>
	   </tr>
	   
	   
	   <tr>
	   <td></td>
	   <td><span class="formtext">Language</span></td>
	   <td>
	     <form:input path="language" id="language" />
         <span class="errortxt"><form:errors path="language" /></span>
	  </td>
	   <td></td>
	   </tr>
	   
	   <tr>
	   <td></td>
	   <td><span class="formtext">Timezone</span></td>
	   <td>
	     <form:input path="timeZone" id="timeZone" />
         <span class="errortxt"><form:errors path="timeZone" /></span>
	  </td>
	   <td></td>
	   </tr>
	   
	   </table>
	   
		
				<tr>
				    
					<td align="center" ><a href="#" class="btn btn-success" onclick="javascript:submitUpdate()">Save</a></td>
					<td align="left" ><a href="#" class="btn btn-success" onclick="javascript:window.back()">Cancel</a></td>
				</tr>
			</table>
			
		

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
