

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>



<script type="text/javascript">
	
	function submitUpdate(){
	  document.forms[0].submit();
	}
	
</script>



<body>
<form:form action="view" method="POST" commandName="userBean">

<div class="content">
<div id="email-list">
<div class="bodyOfSpan">
<!--content main controller table starts!-->
<div style="float:right;"> <a href="${pageContext.request.contextPath}/usr/admin/new/user" class="btn btn-success">+ New User</a></div>
<div id="sublist" class="content">
<table class="table table-striped" style="white-space:nowrap; border:1px solid #ebebeb; font-size: 13px; box-shadow: 0 0px 0px #CDCDCD;    border-radius: 5px;"> 

<c:set var="userCount" value="${fn:length(userBean.users)}"></c:set>
<c:choose>
<c:when test="${userCount == 0}">
<tr>
<td colspan="4" class="">No User Created.</td>
</tr>
</c:when>
<c:otherwise>
<tr>
<th>UserName</th>
<th>Email ID</th>
<th>Role</th>
<th>Status</th>
<th>Actions</th>
</tr>
<c:forEach items="${userBean.users}" var="user" varStatus="reStatus">
<tr onmouseout="this.className='emailcampheader odd'" 										onmouseover="this.className='emailcampHeaderHover'" 											class="emailcampheader odd">
<td class="tbl-btm-bdr">
<c:out value="${user.displayName}" /></td>
<td class="tbl-btm-bdr"><c:out value="${user.emailId}" /></td>
<td class="tbl-btm-bdr"><c:out value="${user.role}"/></td>
<td class="tbl-btm-bdr"><c:out value="${user.status}"/></td>
															
<td class="tbl-btm-bdr" width="100">

<a href="${pageContext.request.contextPath}/usr/admin/edit/id/<c:out value="${user.userId}" />" class="edit" title="edit">Edit</a> 
<a href="${pageContext.request.contextPath}/usr/admin/delete/id/<c:out value="${user.userId}" />" class="delete" class="Delete">Delete</a></td>
</tr>
</c:forEach>
</c:otherwise>
</c:choose>	
											

		
	</table>
	<!--content main controller table ends!-->
</div></div></div></div>
</form:form>


</body>
