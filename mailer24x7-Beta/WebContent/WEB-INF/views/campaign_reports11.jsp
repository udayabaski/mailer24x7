


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Mailer24x7 : Reports</title>

<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugin/jquery.jqplot.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugin/jqplot.json2.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/script/plugin/jqplot.highlighter.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugin/jqplot.cursor.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/script/plugin/jqplot.dateAxisRenderer.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/script/plugin/jqplot.pieRenderer.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/script/plugin/jqplot.donutRenderer.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/script/plugin/jqplot.logAxisRenderer.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/script/plugin/jqplot.canvasTextRenderer.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/script/plugin/jqplot.canvasAxisTicketRenderer.js"></script>

<script>
	// When the document loads do everything inside here ...

	$(document).ready(
			function() {
			
			
	var campaignId = ${campaignReportsBean.campaignId};
	
	//alert("CAMMMMMMMMMMMMM "+campaignId);

		$.ajax({
			//type: "POST",
			//async: false,
			url : "${pageContext.request.contextPath}/usr/reports/view/campaign/id/"+campaignId+"/type/campaignopens/time",
			//contentType: "application/json;",
			//data: "{'groupBy': '" + group + "'}",
			//dataType: 'json',
			success : function(data) {				
				plotLineChart(data);
			}
		});

	});
	
	/*$(document).ready(
			function() {
			
			
	var campaignId = ${campaignReportsBean.campaignId};
	
	//alert("CAMMMMMMMMMMMMM "+campaignId);

		$.ajax({
			//type: "POST",
			//async: false,
			url : "${pageContext.request.contextPath}/usr/reports/view/campaign/id/"+campaignId+"/type/campaignopens/region",
			//contentType: "application/json;",
			//data: "{'groupBy': '" + group + "'}",
			//dataType: 'json',
			success : function(data) {				
				plotPieChart(data);
			}
		});

	});*/

	function plotLineChart(dataIn) {
	    $.jqplot._noToImageButton = true;
	
		var openDataSlices = [];
		var clickDataSlices = [];
	    
		//alert(dataIn);
		
		var dataResult = dataIn.split("##");
		
		var openResult = dataResult[0].replace("{","").replace("}","").split(",");
		var clickResult = dataResult[1].replace("{","").replace("}","").split(",");
	
		for(i = 0; i < openResult.length; i++){
		    var dateCount = openResult[i].split("="); 
			date = dateCount[0];
			count = dateCount[1];
			
			openDataSlices.push([ date,count ]);
			openDataSlices.push([ '26-Jan-2013',10 ]);
			openDataSlices.push([ '29-Jan-2013',6  ]);
			openDataSlices.push([ '10-Feb-2013',8  ]);
		}
		
		for(i = 0; i < clickResult.length; i++){
		    var dateCount = clickResult[i].split("="); 
			date = dateCount[0];
			count = dateCount[1];
			
			//clickDataSlices.push([ date,count ]);
		}
		
		    clickDataSlices.push([ '27-Jan-2013',1 ]);
			clickDataSlices.push([ '29-Jan-2013',10 ]);
			clickDataSlices.push([ '10-Feb-2013',6  ]);
			clickDataSlices.push([ '20-Feb-2013',8  ]);
		
						
	var plot1 = $.jqplot("lineChart1", [ openDataSlices, clickDataSlices], {
			seriesColors : [ "rgba(78, 135, 194, 0.7)", "rgb(211, 235, 59)" ],
			title : 'Opens over time',
			highlighter : {
				show : true,
				sizeAdjust : 1,
				tooltipOffset : 5
			},
			grid : {
				background : 'rgba(57,57,57,0.0)',
				drawBorder : false,
				shadow : false,
				gridLineColor : '#666666',
				gridLineWidth : 0.15
			},
			legend : {
				show : true,
				placement : 'inside'
			},
			seriesDefaults : {
				rendererOptions : {
					smooth : false,
					animation : {
						show : true
					}
				},
				showMarker : true
			},
			series : [ {
				fill : false,
				label : 'Opens',
				lineWidth:2, markerOptions:{style:'square'}
			}, {
			    fill : false,
				label : 'Clicks',
				lineWidth:2, markerOptions:{style:'circle'}
			} ],
			axesDefaults : {
				rendererOptions : {
					baselineWidth : 0,
					baselineColor : '#444444',
					drawBaseline : false
				}
			},
			axes : {
				xaxis : {
					renderer : $.jqplot.DateAxisRenderer,
					
					//tickRenderer : $.jqplot.CanvasAxisTickRenderer,
					
					sdrawMajorGridlines : false
				},
				
			},
			//series:[{lineWidth:2, markerOptions:{style:'square'}},{lineWidth:2, markerOptions:{style:'circle'}}]				
		});
		
		var plot1 = $.jqplot("lineChart2", [ clickDataSlices], {
			seriesColors : [ "rgb(211, 235, 59)" ],
			title : 'Clicks over time',
			highlighter : {
				show : true,
				sizeAdjust : 1,
				tooltipOffset : 5
			},
			grid : {
				background : 'rgba(57,57,57,0.0)',
				drawBorder : false,
				shadow : false,
				gridLineColor : '#666666',
				gridLineWidth : 0.15
			},
			seriesDefaults : {
				rendererOptions : {
					smooth : false,
					animation : {
						show : true
					}
				},
				showMarker : true
			},
			axesDefaults : {
				rendererOptions : {
					baselineWidth : 0,
					baselineColor : '#444444',
					drawBaseline : false
				}
			},
			axes : {
				xaxis : {
					renderer : $.jqplot.DateAxisRenderer,
					
					//tickRenderer : $.jqplot.CanvasAxisTickRenderer,
					
					sdrawMajorGridlines : false
				},
				
			},
			series:[{lineWidth:2, markerOptions:{style:'square'}}]				
		});
		
		$('.jqplot-highlighter-tooltip').addClass('ui-corner-all')
	}

	$(document)
			.ready(
					function() {

						var dataSlices = [];

						dataSlices.push([ "opened",
								parseInt("${campaignReportsBean.opened}") ]);
						dataSlices.push([ "clicked",
								parseInt("${campaignReportsBean.clicked}") ]);
						dataSlices.push([ "unsubscribed",
								parseInt("${campaignReportsBean.unsubscribed}") ]);
						dataSlices.push([ "bounced",
								parseInt("${campaignReportsBean.bounced}") ]);

						$.jqplot('pieChart', [ dataSlices ], {
							seriesDefaults : {
								shadow : false,
								fontSize : 20,
								renderer : jQuery.jqplot.PieRenderer,
								rendererOptions : {

									startAngle : 180,
									sliceMargin : 4,
									showDataLabels : true,
									dataLabels : 'value',
								}
							},
							legend : {
								fontSize : 15,
								show : true,
								location : 'e'
							}
						});
						$('#pieChart').bind('jqplotDataMouseOver', 
							function (ev, seriesIndex, pointIndex, data) {
								/* To open in a NEW window use: */
								/* window.open(data[2]); */
								/* To open in the same window use: */
								alert(data);
								//window.open(data[1])
							}
        );

					});
</script>




<form:form action="reports" method="POST" commandName="campaignReportsBean">

<!--content main controller table starts!-->
<table cellpadding="0" cellspacing="0" border="0" width="100%" class="content-padding">
<tr>
<td width="10%"></td>
<td width="80%" >
<table cellpadding="0" cellspacing="0" width="100%">
<tr>
<td width="100%" class="contentLayer">




<table width="98%" cellpadding="0" cellspacing="0" align="center" >
<tr>
<td class="content-heading" colspan="4"><img src="${pageContext.request.contextPath}/images/report.png" class="top-icon-padd" />Reports</td>
</tr>

<tr>
<td class="empty" colspan="4"></td>
</tr>

<tr>
<td>

<div id="main">

        <fieldset>

        <div class="hd">
			<h2><c:out value="${campaignReportsBean.campaignName}" /></h2>
			
			<span  class="options"><a href="${pageContext.request.contextPath}/usr/reports/view/all">Go to Reports Home</a></span>
			
			<span  class="options"><a href="${pageContext.request.contextPath}/usr/campaign/id/${campaignReportsBean.campaignId}/type/region">Reports By Region</a></span>
			
            <span  class="options"><a href="">Share on Facebook</a></span>
            
            <span  class="options"><a href="">Share on Twitter</a></span>		</div>
		<table width="100%" cellpadding="5" cellspacing="0" class="campaign-page">
	    <tr valign="top" class="odd">
			<td width="30%">From</td>
			<td><c:out value="${campaignReportsBean.senderName}" /> < <c:out value="${campaignReportsBean.senderEmailId}" /> ></td>
		</tr>
	    <tr valign="top" class="even">
			<td width="30%">Sent at</td>
			<td><c:out value="${campaignReportsBean.sentTime}" /></td>
		</tr>
	    <tr valign="top" class="odd">
			<td width="30%">Sent to</td>
			<td><a href="${pageContext.request.contextPath}/usr/subscriber/view/group/subscribers/id/${campaignReportsBean.subscriberListId}"><c:out value="${campaignReportsBean.subscriberListName}" /></a></td>
		</tr>
		</table>
      
		<table width="100%" cellpadding="5" cellspacing="0" class="reportcampheader" >
	    <tr valign="top" class="odd">
			<td width="30%" height="67"><h4>Total emails sent<br />
			    <br /> 
			    <span class="report-green-txt"><c:out value="${campaignReportsBean.totalEmailsSent}" /></span></h4></td>
			<td width="36%"><h4>Opened<br /></h4>
			<div>
              <div class="count"><span class="report-green-txt"><c:out value="${campaignReportsBean.openedPercentage}" />%</span></div>
                <div class="countDetails"><img src="${pageContext.request.contextPath}/images/user.jpg" style="height:25;width:25px; margin-right:5px; margin-top:5px" /><a href="#"><c:out value="${campaignReportsBean.opened}" /></a></div>
              </div></td>
			<td width="34%"><h4>Clicked</h4>
            <div>
              <div class="count"><span class="report-blue-txt"><c:out value="${campaignReportsBean.clickedPercentage}" />%</span></div>
                <div class="countDetails"><img src="${pageContext.request.contextPath}/images/user.jpg" style="height:25;width:25px; margin-right:5px; margin-top:5px" /><a href="#"><c:out value="${campaignReportsBean.clicked}" /></a></div>
              </div>            </td>
		</tr>
	    <tr valign="top" class="even">
			<td width="30%" height="69"><h4>Bounced</h4> 
            <div>
              <div class="count"><span class="report-bounced"><c:out value="${campaignReportsBean.bouncedPercentage}" />%</span></div>
                <div class="countDetails"><img src="${pageContext.request.contextPath}/images/user.jpg" style="height:25;width:25px; margin-right:5px; margin-top:5px" /><a href="#"><c:out value="${campaignReportsBean.bounced}" /></a></div>
              </div></td>
			<td><h4>Unsubscribed</h4> 
            <div>
              <div class="count"><span class="report-bounced"><c:out value="${campaignReportsBean.unsubscribedPercentage}" />%</span></div>
                <div class="countDetails"><img src="${pageContext.request.contextPath}/images/user.jpg" style="height:25;width:25px; margin-right:5px; margin-top:5px" /><a href="#"><c:out value="${campaignReportsBean.unsubscribed}" /></a></div>
              </div></td>
			<td><h4>Dropped</h4> 
            <div>
              <div class="count"><span class="report-bounced"><c:out value="${campaignReportsBean.droppedPercentage}" />%</span></div>
                <div class="countDetails"><img src="${pageContext.request.contextPath}/images/user.jpg" style="height:25;width:25px; margin-right:5px; margin-top:5px" /><a href="#"><c:out value="${campaignReportsBean.dropped}" /></a></div>
              </div></td>
		</tr>
		</table>
		<div class="hd" style="margin-top:15px;">
			<h2>Pie Chart</h2>
		</div>
		<div id="pieChart"
					style="margin-top: 20px; margin-left: 20px; width: 500px; height: 300px;">
		</div>
        <div class="hd" style="margin-top:15px;">
			<h2>Open &amp; Clicks over time</h2>
		</div>
		<div id="lineChart1"
					style="margin-top: 20px; margin-left: 20px; width: 1000px; height: 300px;"></div>
	
		<div id="pieChart1"
					style="margin-top: 20px; margin-left: 20px; width: 500px; height: 400px;">
		</div>
        
        
        <div class="hd" style="margin-top:15px;">
			<h2>Link Activity</h2>
		</div>
        
		  <table width="100%" cellpadding="5" cellspacing="0" class="clicks-table">
            <tr valign="top" class="odd">
              <th width="59%" height="25"><strong>Link</strong></th>
              <th width="21%"><strong>Unique Clicks</strong></th>
              <th width="20%"><strong>Total Clicks</strong></th>
            </tr>
            <tr valign="top" class="even">
              <td><a rel="external" href="http://udayabaski.mailerlite.com/f9e0s2/">http://udayabaski.mailerlite.com/f9e0s2/</a></td>
              <td>0</td>
              <td>0</td>
            </tr>
            <tr valign="top" class="odd">
               <td><a rel="external" href="http://udayabaski.mailerlite.com/f9e0s2/">http://udayabaski.mailerlite.com/f9e0s2/</a></td>
              <td>0</td>
              <td>0</td>
            </tr>
          </table>
          <div>
          	<div style="width:40%; float:left">
          		<div class="hd" style="margin-top:15px;">
					<h2>Reading Environment</h2>
				</div>
                <div><img src="${pageContext.request.contextPath}/images/graph-pie.jpg" /></div>
            </div>
            <div style="width:50%; float:right">
            	<div class="hd" style="margin-top:15px;">
					<h2>Webclient</h2>
				</div>
                <table width="100%" cellpadding="5" cellspacing="0" class="clicks-table">
                  <tr valign="top" class="odd">
                    <th width="59%" height="25"><strong>Email Client</strong></th>
                    <th width="20%"><strong>Subscribers</strong></th>
                  </tr>
                  <tr valign="top" class="even">
                    <td>Web version/ Firefox</td>
                    <td>100%</td>
                  </tr>
                </table>
            </div>    
		 
        </fieldset>
        </form>
    </div>    </td></tr></table>
    
    
</td></tr></table>


<td width="10%"></td>
</tr>
</table>
<!--content main controller table ends!-->

</form:form>


