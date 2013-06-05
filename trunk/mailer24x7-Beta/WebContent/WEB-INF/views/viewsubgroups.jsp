

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<title>Mailer247 - Create Campaign Step1</title>

<link href="${pageContext.request.contextPath}/styles/style.css" rel="stylesheet" type="text/css" />
<!--[if lt IE 9]><script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/script/excanvas.js"></script><![endif]-->
<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery-1.8.3.min.js"></script>


<script type="text/javascript">

	             /* ---------------- Show settings and close icon in rows on mouseover ---------*/   
            $("div.tableData table tr").mouseover(function() {
               $(this).children().find("img.setting").css("display","block");
            });
            $("div.tableData table tr").mouseout(function() {
               $(this).children().find("img.setting").css("display","none");
            });
            
	
</script>




<form:form action="subscriber" method="POST" commandName="subscriberHomeBean">


<!--content main controller table starts!-->
<table cellpadding="0" cellspacing="0" width="100%" class="content-padding">
<tr>
<td width="10%"></td>
<td width="80%" valign="top">


<a href="${pageContext.request.contextPath}/usr/subscriber/new/group" class="button grey-btn mar1" ><img src="${pageContext.request.contextPath}/images/add.png" width="16" height="15" alt="" />New Subscribers Group</a>

<table cellpadding="0" cellspacing="0" width="100%">

<tr>


<td width="100%" class="contentLayer">

	

		


	
    
       
        	<table cellpadding="0" cellspacing="0" width="100%" class="content-padding">
            <tr>
            <th>Group Name</th>
            <th>Active</th>
           <th>Unsubscribed</th>
            <th>Bounced</th>
           <th>Last Modified</th>
           <th>Actions</th>
            </tr>
            <c:choose>
            
            <c:when test="${fn:length(subscriberHomeBean.subscriberList) > 0}">
              <c:forEach items="${subscriberHomeBean.subscriberList}" var="pageItem" varStatus="reqStatus">
            
             <tr onmouseout="this.className='emailcampheader'" onmouseover="this.className='emailcampHeaderHover'" class="emailcampheader">
             
              <td class="tbl-btm-bdr"><a href="${pageContext.request.contextPath}/usr/subscriber/view/group/subscribers/id/${pageItem.subscriberListId}"><c:out value="${pageItem.subscriberListName}"></c:out></a></td>
              <td class="tbl-btm-bdr"><a href="subscriber.form?action=deleteGroup&object=<c:out value="${pageItem.subscriberListId}"/>"><c:out value="${pageItem.activeCount}"></c:out></a></td>
              <td class="tbl-btm-bdr"><c:out value="${pageItem.unsubscriberCount}"></c:out></td>
              <td class="tbl-btm-bdr"><c:out value="${pageItem.bouncedCount}"></c:out></td>
              <td class="tbl-btm-bdr setting_column"><c:out value="${pageItem.lastModifiedTime}"></c:out></td>
              <td class="tbl-btm-bdr">
				<a href="${pageContext.request.contextPath}/usr/subscriber/edit/group/id/${pageItem.subscriberListId}" class="edit" title="Edit">Edit</a> 
				<a href="${pageContext.request.contextPath}/usr/subscriber/delete/group/id/${pageItem.subscriberListId}" class="delete" title="Delete">Delete</a>
			  </td>
             </tr>
            
            </c:forEach>
            </c:when>
            
            <c:otherwise>
            <tr>
              <td colspan="4" class="tbl-btm-bdr">No Groups are created. <a href="${pageContext.request.contextPath}/usr/subscriber/new/group">Create</a> a Group.</td>
             </tr>
            </c:otherwise>
            </c:choose>
            
            </table>
        
</td>
</tr>
</table>
</td>
<td width="10%"></td>
</tr>
</table>

<!--content main controller table ends!-->


</form:form>



