
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Mailer247 - Create Campaign Step1</title>

<link href="${pageContext.request.contextPath}/styles/style.css" rel="stylesheet" type="text/css" />
<!--[if lt IE 9]><script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/script/excanvas.js"></script><![endif]-->
<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery-1.8.3.min.js"></script>

<script type="text/javascript">
	
	function submitUpdate(){
	  document.forms[0].submit();
	}
	
</script>

</head>

<body>

<form:form action="${pageContext.request.contextPath}/usr/admin/update/user"  commandName="userForm" method="POST" id="userForm">

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

            <legend>Campaign Headers</legend>

           
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
	     <span class="formtext"><b><c:out value="${userForm.emailId}" /></b></span> 
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
	   
		<div class="btn">
			<table>
				<tr>
				    <td width="100">&nbsp;</td>
					<td align="center"><a href="#" class="button green" onclick="javascript:submitUpdate()">Update</a></td>
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
