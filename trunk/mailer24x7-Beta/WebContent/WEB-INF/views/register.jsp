<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Mailer247 - Registration</title>
	
<link href="${pageContext.request.contextPath}/styles/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/styles/signup-style.css" rel="stylesheet" type="text/css" />
<!--[if lt IE 9]><script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/script/excanvas.js"></script><![endif]-->
<link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/styles/stylez.css" />
<link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/styles/signup.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/font-awesome.css" />
<link href="//fonts.googleapis.com/css?family=Open+Sans:400,300" rel="stylesheet" type="text/css" />





</head>

<body>


 <!--Top header tiles starts-->
<div class="client-banner cf">
    <div class="container">
        <div class="row">
            <div class="inner-wrapper cf span12" style="position:relative; left:15px;">
                <div class="left">
                    <a href="#" style="text-decoration:none">
                        <div class="panel-sitename">
                            <h4 style="color:#fff; padding-top:2px; font-size:25px;"><span class="helper-font-24">
                    <i class="icon-envelope-alt" style="font-size:36px; color:#fff"></i>
                </span> <span style="position:relative; bottom:2px; font-weight:normal; left:5px; ">Mail Engage Sign in below</span></h4>
                        </div>
                    </a>

                </div>
                <b class="txt">Already a member?</b> <a href="${pageContext.request.contextPath}/auth/login" class="button green mar1">Login</a>
            </div>
        </div>
    </div>
</div>
<!--Top header tiles Ends-->

 <!--Content area tiles Starts-->  
 <form:form action="join"  commandName="registrationForm" method="POST" id="registration">
      <form:hidden path="uuId" />
<div >
 <div class="bodyOfSpan">
 
		   <c:set var="uuId" value="${registrationForm.uuId}"></c:set>
            <c:if test="${not empty uuId}">
            <tr><td colspan="3" height="20" class="${registrationForm.messageType}">Thanks for registration. An activation mail has been sent to your email. Please follow the mail. 
          Click the link below to send the mail again if you have not got the mail. <a href="${pageContext.request.contextPath}/reg/join/confirm/resend/usr/<c:out value="${registrationForm.uuId}"/>">Send now.</a></td></tr>
            <tr><td colspan="3" height="20"></td></tr>
             </c:if>
 
 <div class="signup-wrapper cf">
    <div class="form-wrapper left">
        <div class="email-white bottom20px">
            <div class="padding20px form">
                <form accept-charset="utf-8" method="post">
                <div class="cf small-inputs">
                    <div class="left">
                        <label>First Name</label>
                        <form:input path="fullName" id="fullName" />
              			 <span class="errortxt"><form:errors path="fullName" /></span>
                                            
                        </div>

           
                </div>

                <label style="float:left">Company or organisation</label>
                <input type="text" value="" name="company_name" />
                <label>Email address</label><form:input path="emailId" id="emailId" />
              <span class="errortxt"><form:errors path="emailId" /></span>
           
                <label>Password</label><form:password path="password" id="password" />
              <span class="errortxt"><form:errors path="password" /></span>
              
               <label>Retype password</label> <form:password path="confirmPassword" id="confirmPassword" />
              <span class="errortxt"><form:errors path="confirmPassword" /></span>
            
        		
        		
        		
                              <div class="cf">
                    <div class="left">
                        <span class="free-trial">30 day free trial, no credit card required.</span>
                    </div>
                    <div class="right">
                        <input type="submit" value="Get started" class="btn btn-success btn-large"  onclick="document.forms[0].submit();return false;"  href="#"/>
                    </div>
                </div>
                </form>         <br>       <span class="by-clicking">By clicking "Get Started" you agree to <a href="#">Terms of Use</a> and <a href="#">Privacy Policy</a>.</span>
            </div>
        </div>
    </div>
    <div class="perks right">
        <p class="up-and-running">Benefits for signing up</p>
        <p class="included">Included in your account:</p>
        <ul class="perks-list">
            <li>24/7 support from our team</li>
            <li>Another great benefit</li>
            <li>100GB Storage</li>
            <li>We're in the cloud, so accessing your data will be 10x faster</li>
            <li>We use the latest technology on the market today</li>
        </ul>
        <p class="joining">
           Online email marketing solution to manage contacts,<br> send emails and track results Offers plug-ins for other programs.
        </p>
    </div>

</div>
 
 </div>

</div>

</form:form>



<!--Content area tiles Ends-->  
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
<script type="text/javascript"  src="js/bootstrap.js"></script>
</body>
</html>







