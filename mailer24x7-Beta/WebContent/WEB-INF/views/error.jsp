<html>
<head>
 
 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
 
</head>
<body>
<h1>Oops !!! - Some exception occured!</h1>
<h3>Message :<font color="Red">${exception}</font></h3>

<c:forEach items="${exception.stackTrace}" var="element">
    <c:out value="${element}" />
</c:forEach>
 
</body>
</html>