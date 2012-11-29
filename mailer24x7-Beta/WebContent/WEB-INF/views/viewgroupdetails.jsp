
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<title>Mailer247 - Create Campaign Step1</title>

<link href="${pageContext.request.contextPath}/styles/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/styles/jquery-ui-1.9.1.custom.min.css" rel="stylesheet" type="text/css" />
<link class="include" rel="stylesheet" type="text/css"	href="${pageContext.request.contextPath}/styles/jquery.jqplot.css" />
<!--[if lt IE 9]><script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/script/excanvas.js"></script><![endif]-->
<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery-ui-1.9.1.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugin/jquery.jqplot.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugin/jqplot.json2.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/script/plugin/jqplot.pieRenderer.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/script/plugin/jqplot.donutRenderer.js"></script>

<script>
    	$(function() {
          $( "#tabs" ).tabs();
        });
</script>

<script>
    	function move(from,to){
    	   document.getElementById("subscriberType").value=from;
    	   document.getElementById("moveTo").value=to;
    	   var ctx = "${pageContext.request.contextPath}";
	       document.forms[0].action=ctx+"/usr/subscriber?action=moveSubscribers";
	  	   document.forms[0].submit();
    	}
    	
    	function deleteSubscribers(type){
    	  document.getElementById("subscriberType").value=type;
    	  var ctx = "${pageContext.request.contextPath}";
	      document.forms[0].action=ctx+"/usr/subscriber?action=deleteSubscribers";
	  	  document.forms[0].submit();
    	}
</script>

    
<script>
	
	$(document).ready(function () {
        //var group = 'test';
        $.ajax({
            //type: "POST",
            async: false,
            url: "subscriber.form?action=getSubListPieChart&subListId=1",
            //contentType: "application/json;",
            //data: "{'groupBy': '" + group + "'}",
            //dataType: 'json',
            success: function (data) {               
                Plot(data);
            }
        });
    });

    function Plot(dataIn) {                        
					
		var activeArray = [];
		var activeCount = 0;
		activeArray = "${subscriberHomeBean.activeSubscribers}".split(",");
		for( element in activeArray){
		  if(activeArray[element].length > 2) {
            activeCount = activeCount+1;
		  }
       }
	   
	   var bouncedArray = [];
		var bouncedCount = 0;
		bouncedArray = "${subscriberHomeBean.bouncedSubscribers}".split(",");
		for( element in bouncedArray){
		   if(bouncedArray[element].length > 2) {
            bouncedCount = bouncedCount+1;
		  }
       }
	   
	   var unsubscribedArray = [];
		var unsubscribedCount = 0;
		unsubscribedArray = "${subscriberHomeBean.unsubscribedSubscribers}".split(",");
		for( element in unsubscribedArray){
		  if(unsubscribedArray[element].length > 2) {
            unsubscribedCount = unsubscribedCount+1;
		  }
       }
		
        var dataSlices = [];
        dataSlices.push(["Active", activeCount]);
		dataSlices.push(["Unsubscribed", bouncedCount]);
        dataSlices.push(["Bounced", unsubscribedCount]);
		        
        $.jqplot('pieChart', [dataSlices],
        {
            seriesDefaults: {
                shadow: true,
                renderer: $.jqplot.PieRenderer,
                rendererOptions: {
				    fill: false,
                    sliceMargin: 4,
                    showDataLabels: true
                }
            },
            legend: { show: true, location: 'e' }
        }
            );
    }

	
	/*jQuery(document).ready(function () {
	
    urlDataJSON = 'subscriber.form?action=getSubListPieChart&subListId=1';

    $.getJSON(urlDataJSON, "", function (data) {
        var dataSlices = [];
	
	    alert("Data "+data);
        $.each(data, function (entryindex, entry) {
            alert("TITLEEEEEE "+entry['title']);
            dataSlices.push([entry['title'], entry['Counts']]);
        });
        options = {
            legend: { show: true },
            title: 'Poll Results',
            seriesDefaults: {
                // Make this a pie chart.
                renderer: jQuery.jqplot.PieRenderer,
                rendererOptions: {
                    // Put data labels on the pie slices.
                    // By default, labels show the percentage of the slice.
                    showDataLabels: true
                }
            }
        }
        var plot = $.jqplot('pieChart', [dataSlices], options);
    });
});   */           

	
</script>

</head>
<body>

<form:form action="subscriber" method="POST"
	commandName="subscriberHomeBean">
	
	<form:hidden path="subscriberType" id="subscriberType" />
	<form:hidden path="moveTo" id="moveTo" />
	<form:hidden path="subscriberListId" id="subscriberListId" />
	

	<!--content main controller table starts!-->
	<table cellpadding="0" cellspacing="0" width="100%"
		class="content-padding">
		<tr>
			<td width="10%"></td>
			<td width="80%" valign="top">
				<table cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td width="100%" class="contentLayer">
							<div id="tabbed_box_1" class="tabbed_box">

								<div id="tabs" class="tabbed_area">


									<ul class="tabs">
										<li><a href="#tabs-1" title="content_1" class="tab active">Active
										</a></li>
										<li><a href="#tabs-2" title="content_2" class="tab">Unsusbscribed
										</a></li>
										<li><a href="#tabs-3" title="content_3" class="tab">Bounced
										</a></li>
									</ul>
									
									<div class="right-link">
										<a href="${pageContext.request.contextPath}/usr/subscriber/view/all/groups" class="button grey-btn mar1" >View All Groups</a>
									</div>
									

									<div id="tabs-1" class="content">
										<table cellpadding="0" cellspacing="0" width="100%">
											<tr>
											    <td class="table-heading" width="5%"></td>
												<td class="table-heading">Email</td>
												<td class="table-heading">Status</td>
											</tr>

											<c:choose>

												<c:when
													test="${fn:length(subscriberHomeBean.activeSubscribers) > 0}">
													<c:forEach items="${subscriberHomeBean.activeSubscribers}"
														var="pageItem" varStatus="reqStatus">
	
														<tr onmouseout="this.className='emailcampheader'"
															onmouseover="this.className='emailcampHeaderHover'"
															class="emailcampheader">
															
															<td>
															  <form:checkbox path="activeSubscribersSelected" value="${pageItem.statusId}" />
															</td>
															
															<td class="tbl-btm-bdr"><a
																href="${pageContext.request.contextPath}/usr/subscriber/view/subscriber/id/${pageItem.statusId}/group/${subscriberHomeBean.subscriberGroup.subscriberListId}"><c:out
																		value="${pageItem.emailId}"></c:out></a></td>
															<td class="tbl-btm-bdr"><a
																href="#"><c:out
																		value="${pageItem.statusId}"></c:out></a></td>
														</tr>

													</c:forEach>
												</c:when>

												<c:otherwise>
													<tr>
														<td colspan="4" class="tbl-btm-bdr">
															No user is Active.
														</td>
													</tr>
												</c:otherwise>
											</c:choose>
											
										</table>
										<c:if test="${fn:length(subscriberHomeBean.activeSubscribers) > 0}">
										<table cellpadding="0" cellspacing="0" width="100%">
											<tr>
											    <td><a href="#" onclick="javascript:move('0','2')" class="edit" title="Move to UnSubscribed">Move to UnSubscribed</a></td> 
											    <td><a href="#" onclick="javascript:move('0','1')" class="edit" title="Move to Bounced">Move to Bounced</a></td>
											    <td><a href="#" onclick="javascript:deleteSubscribers('0')" class="delete" title="Delete">Delete</a></td>
											</tr>
										</table>
										</c:if>
									</div>
									<div id="tabs-2" class="content">
										<table cellpadding="0" cellspacing="0" width="100%">
											<tr>
											    <td class="table-heading" width="5%"></td>
												<td class="table-heading">Email</td>
												<td class="table-heading">Status</td>
											</tr>

											<c:choose>
												<c:when
													test="${fn:length(subscriberHomeBean.unsubscribedSubscribers) > 0}">
													<c:forEach
														items="${subscriberHomeBean.unsubscribedSubscribers}"
														var="pageItem" varStatus="reqStatus">

														<tr onmouseout="this.className='emailcampheader'"
															onmouseover="this.className='emailcampHeaderHover'"
															class="emailcampheader">
															
															<td>
															  <form:checkbox path="unsubscribedSubscribersSelected" value="${pageItem.statusId}" />
															</td>
															
															<td class="tbl-btm-bdr"><a
																href="${pageContext.request.contextPath}/usr/subscriber/view/subscriber/id/${pageItem.statusId}/group/${subscriberHomeBean.subscriberGroup.subscriberListId}"><c:out
																		value="${pageItem.emailId}"></c:out></a></td>
															<td class="tbl-btm-bdr"><a
																href="#"><c:out
																		value="${pageItem.statusId}"></c:out></a></td>
														</tr>

													</c:forEach>
												</c:when>

												<c:otherwise>
													<tr>
														<td colspan="4" class="tbl-btm-bdr">No user is
															unsubscribed.</td>
													</tr>
												</c:otherwise>
											</c:choose>
											
											
										

										</table>
										<c:if test="${fn:length(subscriberHomeBean.unsubscribedSubscribers) > 0}">
										<table cellpadding="0" cellspacing="0" width="100%">
											<tr>
											  <td><a href="#" onclick="javascript:move('2','0')" class="edit" title="Move to Subscribed">Move to Subscribed</a></td> 
											  <td><a href="#" onclick="javascript:move('2','1')" class="edit" title="Move to Bounced">Move to Bounced</a></td>
											  <td><a href="#" onclick="javascript:deleteSubscribers('2')" class="delete" title="Delete">Delete</a></td>
											</tr>
										</table>
										</c:if>
									</div>
									<div id="tabs-3" class="content">
										<table cellpadding="0" cellspacing="0" width="100%">
											<tr>
											    <td class="table-heading" width="5%"></td>
												<td class="table-heading">Email</td>
												<td class="table-heading">Status</td>
											</tr>

											<c:choose>
												<c:when
													test="${fn:length(subscriberHomeBean.bouncedSubscribers) > 0}">

													<c:forEach items="${subscriberHomeBean.bouncedSubscribers}"
														var="pageItem" varStatus="reqStatus">

														<tr onmouseout="this.className='emailcampheader'"
															onmouseover="this.className='emailcampHeaderHover'"
															class="emailcampheader">
															
															<td>
															  <form:checkbox path="bouncedSubscribersSelected" value="${pageItem.statusId}" />
															</td>
															
															
															<td class="tbl-btm-bdr"><a
																href="${pageContext.request.contextPath}/usr/subscriber/view/subscriber/id/${pageItem.statusId}/group/${subscriberHomeBean.subscriberGroup.subscriberListId}"><c:out
																		value="${pageItem.emailId}"></c:out></a></td>
															<td class="tbl-btm-bdr"><a
																href="#"><c:out
																		value="${pageItem.statusId}"></c:out></a></td>
														</tr>

													</c:forEach>
												</c:when>

												<c:otherwise>
													<tr>
														<td colspan="4" class="tbl-btm-bdr">There is no
															bounced subscriber.</td>
													</tr>
												</c:otherwise>
											</c:choose>

										</table>
							
										<c:if test="${fn:length(subscriberHomeBean.bouncedSubscribers) > 0}">
										<table cellpadding="0" cellspacing="0" width="100%">
											<tr>
											  <td><a href="#" onclick="javascript:move('1','0')" class="edit" title="Move to UnSubscribed">Move to Subscribed</a></td> 
											<td><a href="#" onclick="javascript:move('1','2')" class="edit" title="Move to Bounced">Move to UnSubscribed</a></td>
											<td><a href="#" onclick="javascript:deleteSubscribers('1')" class="delete" title="Delete">Delete</a></td>
											</tr>
										</table>
										</c:if>
							
									</div>

								</div>

							</div>
						</td>
					</tr>
				</table>
			</td>
			<td width="10%"></td>
		</tr>
	</table>

	<!--content main controller table ends!-->

	<table>
		<tr>
			<td>
				<div id="pieChart"
					style="margin-top: 20px; margin-left: 20px; width: 400px; height: 240px;"></div>
			</td>
		</tr>
	</table>

</form:form>

</body>
</html>
