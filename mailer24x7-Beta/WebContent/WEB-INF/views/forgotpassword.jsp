
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Mailer247 - Create Campaign Step1</title>

<link href="${pageContext.request.contextPath}/styles/style.css"
	rel="stylesheet" type="text/css" />
<!--[if lt IE 9]><script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/script/excanvas.js"></script><![endif]-->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/script/jquery-1.8.3.min.js"></script>

</head>

<body>


	<form:form action="${pageContext.request.contextPath}/pub/pwd/reset"
		commandName="registrationForm" method="POST" id="registrationForm">

		<form:hidden path="action" />

		<table cellpadding="0" cellspacing="0" width="100%"
			class="content-padding">
			<tr>
				<td width="10%"></td>
				<td width="80%">
					<table cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td width="100%" class="contentLayer">
								<table width="98%" cellpadding="0" cellspacing="0"
									align="center">
									<tr>
										<td class="content-heading" colspan="4"><img
											src="${pageContext.request.contextPath}/images/password.png"
											class="top-icon-padd" />Reset Password</td>
									</tr>

									<c:if test="${not empty registrationForm.message}">
										<div class="success">
											<c:out value="${registrationForm.message}" />
										</div>
									</c:if>

									<tr>
										<td class="empty" colspan="4"></td>
									</tr>

									<tr>
										<td></td>
										<td><span class="formtext">Email Id</span></td>
										<td><form:input path="emailId" id="emailId" /> <span
											class="errortxt"><form:errors path="emailId" /></span></td>
										<td></td>
									</tr>


									<tr>
										<td colspan="4" class="empty"></td>
									</tr>


									<tr>
										<td></td>
										<td></td>
										<td align="left"><a
											onclick="document.forms[0].submit();return false;"
											class="button green" href="#">Reset</a> <a
											class="button green" href="#">Cancel</a></td>
										<td></td>
									</tr>




								</table>
							</td>
						</tr>
					</table>
				</td>
				<td width="10%"></td>
			</tr>
		</table>


	</form:form>

</body>
</html>
