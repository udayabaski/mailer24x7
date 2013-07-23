

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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




<form:form action="subscriber" method="POST" commandName="subscriberStatusReportsBean">
<div class="content">


<div id="email-list">
  
    <div class="bodyOfSpan">

 <div class="btn-group" style="float:right;">
   <a href="${pageContext.request.contextPath}/usr/subscriber/new/group" class="btn btn-success" >New Subscribers Group</a>
  </div>

<!--content main controller table starts!-->
<div id="sublist" class="content">
<table class="table table-striped" style="white-space:nowrap; border:1px solid #ebebeb; font-size: 13px; box-shadow: 0 0px 0px #CDCDCD;
	    border-radius: 5px; margin:10px 0px 0px 0px; float:left;">
    
<tr>
            <th>Subscriber Name</th>
            <th>Count</th>           
            </tr>
           
           
              <c:forEach items="${subscriberStatusReportsBean.subscriberList}" var="pageItem" varStatus="reqStatus">
            
             <tr onmouseout="this.className='emailcampheader'" onmouseover="this.className='emailcampHeaderHover'" class="emailcampheader">
             
              <td class="tbl-btm-bdr"><a href="${pageContext.request.contextPath}/usr/subscriber/reports/campaigns/${pageItem.subscriberId}"><c:out value="${pageItem.emailId}"></c:out></a></td>
              <td class="tbl-btm-bdr"><c:out value="${pageItem.count}"></c:out></td>
              
             </tr>
            
            </c:forEach>
           
            
            </table>

<!--content main controller table ends!-->

</div></div></div></div>
</form:form>



