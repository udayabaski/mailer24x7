

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




<form:form action="subscriber" method="POST" commandName="subscriberCampaignReportsBean">
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
            <th>Campaign Name</th>
            <th>Sent Time</th>           
            </tr>
           
           
              <c:forEach items="${subscriberCampaignReportsBean.campaignsList}" var="pageItem" varStatus="reqStatus">
            
             <tr onmouseout="this.className='emailcampheader'" onmouseover="this.className='emailcampHeaderHover'" class="emailcampheader">
             
              <td class="tbl-btm-bdr"><a href="${pageContext.request.contextPath}/usr/subscriber/view/campaigns/${pageItem.campaignId}"><c:out value="${pageItem.campaignName}"></c:out></a></td>
              <td class="tbl-btm-bdr"><c:out value="${pageItem.campaignDate}"></c:out></td>
              
             </tr>
            
            </c:forEach>
           
           <tr>
             <td class="tbl-btm-bdr">Group Name : <c:out value="${subscriberCampaignReportsBean.groupName}"></c:out></td>
             <td class="tbl-btm-bdr">Email Id : <c:out value="${subscriberCampaignReportsBean.emailId}"></c:out></td>
			 <td class="tbl-btm-bdr">Emails Sent : <c:out value="${subscriberCampaignReportsBean.emailsSent}"></c:out></td>			 
             <td class="tbl-btm-bdr">Opened : <c:out value="${subscriberCampaignReportsBean.opened}"></c:out></td>
             <td class="tbl-btm-bdr">Clicked : <c:out value="${subscriberCampaignReportsBean.clicked}"></c:out></td>
           </tr>
            
            </table>

<!--content main controller table ends!-->

</div></div></div></div>
</form:form>



