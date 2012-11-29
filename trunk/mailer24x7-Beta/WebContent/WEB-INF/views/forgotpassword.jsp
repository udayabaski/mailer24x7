
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

</head>

<body>


 <div id="pageContainer">
	
		
      <form:form action="${pageContext.request.contextPath}/pub/pwd/reset"  commandName="registrationForm" method="POST" id="registrationForm">
      
      <c:if test="${not empty registrationForm.message}">
          <div class="success"><c:out value="${registrationForm.message}"/></div>
        </c:if>
      
      <form:hidden path="action" />
      
		<div id="signUp" class="clearfix toggleTab">
		
            <table width="100%" cellpadding="0" cellspacing="0">
            <tr>
            <td valign="top" width="70%">
            <table width="80%" cellpadding="0" cellspacing="0">
           
            <tr><td colspan="3" ><img src="${pageContext.request.contextPath}/images/mailer-logo.png" class="top-icon-padd" /></td></tr>
            <tr><td colspan="3" style="height:40px;"></td></tr>
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
            <td></td>        
            <td colspan="2"  align="left"> <a onclick="document.forms[0].submit();return false;" class="button green" href="#">Reset</a> <a class="button green" href="#">Cancel</a></td>            
            </tr>
            
            </table>
            </td>
            
            <td width="3%"></td>
            <td valign="top" width="27%">
            
            </td>
            </tr>
            </table>
		</div>
		</form:form> 
	</div>
	</body>
</html>
