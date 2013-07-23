
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	


	



<body>

<form:form action="admin" method="POST" commandName="adminBean">

<div class="content">
 <div class="bodyOfSpan">
 <table cellpadding="0" cellspacing="0" width="100%">
 <tr>
 <td class="contentLayer">
 
 
 
<table width="98%" cellspacing="0" cellpadding="0" align="center">
<tr>
<td  colspan="2" class="content-heading"><img class="top-icon-padd" src="${pageContext.request.contextPath}/images/rss.png"> Settings </td>

</tr>


<tr>
<td class="empty" colspan="3"></td>
</tr>


<tr>
<td  class="pad1">
<img src="${pageContext.request.contextPath}/images/user.png" width="75" height="72" alt="" />
<a href="${pageContext.request.contextPath}/usr/admin/view/all/users">All Users</a></td>

<td  class="pad1">
<img src="${pageContext.request.contextPath}/images/icon-admin.png" width="75" height="72" alt="" />
<a href="${pageContext.request.contextPath}/usr/admin/edit/org">Company Settings</a></td>

<td  class="pad1">
<img src="${pageContext.request.contextPath}/images/group_gear.png" width="75" height="72" alt="" />
<a href="${pageContext.request.contextPath}/usr/pwd/change">Change Password</a></td>
</tr>




</table>
</td>
</tr>
</table>
</div>
</div>



	<!--content main controller table ends!-->

</form:form>




</body>


