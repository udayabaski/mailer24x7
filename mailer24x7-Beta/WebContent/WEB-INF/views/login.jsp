<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>


 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Mailer24x7 : Login</title>
<link href="${pageContext.request.contextPath}/styles/style.css" rel="stylesheet" type="text/css" />
<!--[if lt IE 9]><script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/excanvas.js"></script><![endif]-->

</head>

<body>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html lang="en">
<head>
    
	<title>Mailer247 - Registration</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />	
	<link href="${pageContext.request.contextPath}//styles/signup-style.css" rel="stylesheet" type="text/css" />
    
</head>

<body>


 <div id="pageContainer" class="clearfix mar2">
	
		
		<ul id="tabs" class="clearfix">
			<li class="activeTab" id="signInTab">
				<div class="signInTabContent">
					<!-- <a href="../auth/loginform.form"><span><b>Already a member?</b></span></a> -->
					<h1><b>Sign in below</b></h1>
				</div>
				
				<span class="activeTabArrow"><!-- --></span>
			</li>
			<li class="hd-right"><b class="txt">Don't have an account?</b> <a href="${pageContext.request.contextPath}/reg/join" class="button green mar1">Sign Up</a></li>
		</ul>
	
	  <form:form action="${pageContext.request.contextPath}/j_spring_security_check" name="loginForm" id="frm_login" method="POST">
	
		<div id="signUp" class="clearfix toggleTab">
			
            <table width="100%" cellpadding="0" cellspacing="0" border="0">
            
            <c:set var="message" value="${loginForm.message}"></c:set>
    		<c:if test="${not empty message}">
    	     <tr><td colspan="3" height="20" class="${loginForm.messageType}"><c:out value="${loginForm.message}"/></td></tr>
             <tr><td colspan="3" height="20"></td></tr>
		 	</c:if>
            
            <tr>
            <td valign="top" width="70%">
            <table width="70%" cellpadding="0" cellspacing="0" style="margin-left:15%;">
			
             <tr>
            <td>Email</td>
            <td width="3%"></td>
            <td><input type="text" name="j_username" class="loginbox" id="j_username" /></td>
            </tr>
            
            <tr><td colspan="3" height="20"></td></tr>
             <tr>
            <td>Password</td>
            <td width="3%"></td>
            <td><input type="password" name="j_password" class="loginbox" id="j_password"/></td>
            </tr>
            
            <tr><td colspan="3" height="5"></td></tr>
             <tr>
            <td>&nbsp;</td>
            <td width="3%"></td>
            <td><input type="checkbox" /> Remember Me </td>
            </tr><!-- 
             <tr><td colspan="3" height="20"></td></tr>
             <tr><td></td> <td colspan="2" height="20"><p style="padding-left:25px;">New to Mailer24x7? <a href="../comn/regform.form" class="new-report-link"><b>Join Today</b></a></p></td></tr> -->
           <tr>    
            <td></td>        
            <td colspan="2"  align="left"  style="padding-left:20px;"> <a onclick="document.forms[0].submit();return false;" class="button green" href="#">Login</a> 
			<a href="" class="forgot">Forgot Password</a>
			</td>            
            </tr>
            
            </table>
            </td>
            
            <td width="3%"></td>
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
