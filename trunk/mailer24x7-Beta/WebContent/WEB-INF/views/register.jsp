<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Mailer247 - Registration</title>
	
<link href="${pageContext.request.contextPath}/styles/style.css" rel="stylesheet" type="text/css" />
<!--[if lt IE 9]><script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/script/excanvas.js"></script><![endif]-->

</head>

<body>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html lang="en">
<head>
	<title>Mailer247 - Registration</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />	
    <link href="${pageContext.request.contextPath}/styles/signup-style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript">
    
  </script>
</head>

<body>


 <div id="pageContainer">
	
		
		<ul id="tabs" class="clearfix">
			<li class="activeTab" id="signInTab">
				<div class="signInTabContent">
					<h1><b>Get Started with a Free Account</b></h1>
				</div>
				<span class="activeTabArrow"><!-- --></span>
			</li>
			<li class="hd-right"><b class="txt">Already a member?</b> <a href="${pageContext.request.contextPath}/auth/login" class="button green mar1">Login</a></li>
		</ul>

      <form:form action="join"  commandName="registrationForm" method="POST" id="registration">
      <form:hidden path="uuId" />
      	
      	
		<div id="signUp" class="clearfix toggleTab">
		
			
            <table width="100%" cellpadding="0" cellspacing="0">
            
            
      	
         <div class="${registrationForm.messageType}">
         </div>
            
            <tr>
            <td valign="top" width="70%">
            <table width="80%" cellpadding="0" cellspacing="0">
            
            <c:set var="uuId" value="${registrationForm.uuId}"></c:set>
            <c:if test="${not empty uuId}">
            <tr><td colspan="3" height="20" class="${registrationForm.messageType}">Thanks for registration. An activation mail has been sent to your email. Please follow the mail. 
          Click the link below to send the mail again if you have not got the mail. <a href="${pageContext.request.contextPath}/reg/join/confirm/resend/usr/<c:out value="${registrationForm.uuId}"/>">Send now.</a></td></tr>
            <tr><td colspan="3" height="20"></td></tr>
             </c:if>
            
            <tr>
            <td width="45%">Full Name</td>
            <td width="3%"></td>
             <td>
               <form:input path="fullName" id="fullName" />
               <span class="errortxt"><form:errors path="fullName" /></span>
             </td>
            </tr>
            <tr><td colspan="3" height="20"></td></tr>
            
             <tr>
            <td>Company</td>
            <td width="3%"></td>
             <td>
              <form:input path="company" id="company" />
              <span class="errortxt"><form:errors path="company" /></span>
            </td>
           </tr>
            
             <tr><td colspan="3" height="20"></td></tr>
             <tr>
            <td>Website</td>
            <td width="3%"></td>
            <td>
              <form:input path="website" id="website" />
              <span class="errortxt"><form:errors path="website" /></span>
             </td>
            </tr>
            
            <tr><td colspan="3" height="20"></td></tr>
             <tr>
            <td>Country</td>
            <td width="3%"></td>
             <td>
               <form:select path="country" cssClass="selbox2" id="sub_group">
				  <form:options items="${countryMap}" />
   			   </form:select>
   			   <span class="errortxt"><form:errors path="country" /></span>
            </td>
            </tr>
            
            <tr><td colspan="3" height="20"></td></tr>
             <tr>
            <td>Email</td>
            <td width="3%"></td>
             <td>
              <form:input path="emailId" id="emailId" />
              <span class="errortxt"><form:errors path="emailId" /></span>
            </td>
           </tr>
            
            <tr><td colspan="3" height="20"></td></tr>
             <tr>
            <td>Password</td>
            <td width="3%"></td>
            <td>
              <form:password path="password" id="password" />
              <span class="errortxt"><form:errors path="password" /></span>
             </td>
            </tr>
            
            <tr><td colspan="3" height="20"></td></tr>
             <tr>
            <td>Retype password</td>
            <td width="3%"></td>
            <td>
              <form:password path="confirmPassword" id="confirmPassword" />
              <span class="errortxt"><form:errors path="confirmPassword" /></span>
            </td>
            </tr>
            
             <tr><td colspan="3" height="20"></td></tr>
            <tr>    
            <td></td>        
            <td colspan="2"  align="center"> <a onclick="document.forms[0].submit();return false;" class="button green" href="#">Create My Account</a> <!-- <a class="button green" href="#">Cancel</a> --></td>            
            </tr>
            
             <tr><td colspan="3" height="10"></td></tr>
            <tr>          
            <td colspan="3"  align="left" class="cond">"By signing up, you agree to our <a href="javascript:void(0);" class="conditions">Terms of Use</a>, <a href="javascript:void(0);" class="conditions">Privacy Policy</a>, and <a href="javascript:void(0);" class="conditions">Anti-spam Policy</a>"
</td>            
            </tr>
            
            </table>
            </td>
            
            <td width="3%"></td>
            <td valign="top" width="27%">
            <table cellpadding="0" cellspacing="0">
            <tr>
            <td>
            <div id="sidebar">
				<h3>Benefits for signing up</h3>
				
				<ul>
					<li>24/7 support from our team</li>
					<li>Another great benefit</li>
					<li>We're in the cloud, so accessing your data will be 10x faster</li>
					<li>We use the latest technology on the market today</li>
				</ul>
				
			</div>
            </td>
            </tr>
            </table>
            
            </td>
            </tr>
            </table>
		</div>
		</form:form> 
	</div>
	</body>
</html>

<table cellpadding="0" cellspacing="0"  width="100%">
<tr>

<td width="10%"></td>
<td width="80%" align="center" style="font-size:12px; color:#595959; padding:20px 0px 0px 0px">Copyright@companyname.com</td>
<td width="10%"></td>

</tr>
</table>


</body>
</html>
