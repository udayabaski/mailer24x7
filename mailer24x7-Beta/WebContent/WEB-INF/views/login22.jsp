<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>


 <%@ page language="java" contentType="text/html; charset=ISO-8859-1" 	pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Mailer24x7 : Login</title>
<link href="${pageContext.request.contextPath}/styles/style.css" rel="stylesheet" type="text/css" />

<!--[if lt IE 9]><script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/excanvas.js"></script><![endif]-->


<link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/styles/style.css" />
<link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/styles/style.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/style.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/style.css" />
<link href="//fonts.googleapis.com/css?family=Open+Sans:400,300" rel="stylesheet" type="text/css" />

    
	
    
</head>

<body>
<div class="client-banner cf">
    <div class="container">
        <div class="row">
            <div class="inner-wrapper cf span12" style="position:relative; left:15px;">
                <div class="left">
                    <a href="#" style="text-decoration:none">
                        <div class="panel-sitename">
                            <h4 style="color:#fff; padding-top:2px; font-size:25px;"><span class="helper-font-24">
                    <i class="icon-envelope-alt" style="font-size:36px; color:#fff"></i>
                </span> <span style="position:relative; bottom:2px; font-weight:normal; left:5px; ">Mail Engage </span></h4>
                        </div>
                    </a>

                </div>
                
            </div>
        </div>
    </div>
</div>
 <form:form action="${pageContext.request.contextPath}/j_spring_security_check" name="loginForm" id="frm_login" method="POST">
 <c:set var="message" value="${loginForm.message}"></c:set>
    		<c:if test="${not empty message}">
    	     <tr><td colspan="3" height="20" class="${loginForm.messageType}"><c:out value="${loginForm.message}"/></td></tr>
             <tr><td colspan="3" height="20"></td></tr>
		 	</c:if>
<!--Content area tiles Starts-->  
<div >
 <div class="bodyOfSpan">
 
 <div class="signup-wrapper cf">
    <div class="form-wrapper left">
        <div class="email-white bottom20px">
        <div class="page-header">
            <h1>Log in</h1>
        </div>

        <div class="padding20px form">
            
            <input  autofocus placeholder="Email address" type="text" name="j_username"  id="j_username">
            <input type="password" name="j_password"  id="j_password" placeholder="Password" >
            <label class="checkbox">
                <input type="checkbox" value="Y" name="remember"> Remember me
            </label>

            <div class="cf">
                <div class="left">
                    <input type="submit" value="Login" onclick="document.forms[0].submit();return false;" class="btn btn-success btn-large">
                </div>
                <div class="right">
                <a class="link-option" href="${pageContext.request.contextPath}/pub/pwd/forgot" style="font-size:12px">Forgot Password</a>
                    
                </div>
            </div>

           
            </div>
    </div>
    </div>
    <div class="perks right">
        <p class="up-and-running">Benefits for Login</p>
        <p class="included">Included in your account:</p>
        <ul class="perks-list">
            <li>24/7 support from our team</li>
            <li>Another great benefit</li>
            <li>100GB Storage</li>
            <li>We're in the cloud, so accessing your data will be 10x faster</li>
            <li>We use the latest technology on the market today</li>
        </ul>
        <p class="joining">
           Online email marketing solution to manage contacts,
           <br> send emails and track results Offers plug-ins for other programs.
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


