<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" type="text/css"  hrf="${pageContext.request.contextPath}/styles/jquery.jqplot.css" />


<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugin/jquery.jqplot.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugin/jqplot.json2.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/script/plugin/jqplot.highlighter.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugin/jqplot.cursor.js"></script>
<script type="text/javascript" 	src="${pageContext.request.contextPath}/script/plugin/jqplot.dateAxisRenderer.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugin/jqplot.barRenderer.js"></script>

<script type="text/javascript" 	src="${pageContext.request.contextPath}/script/plugin/jqplot.pieRenderer.js"></script>
<script type="text/javascript" 	src="${pageContext.request.contextPath}/script/plugin/jqplot.donutRenderer.js"></script>
<script type="text/javascript" 	src="${pageContext.request.contextPath}/script/plugin/jqplot.logAxisRenderer.js"></script>
<script type="text/javascript" 	src="${pageContext.request.contextPath}/script/plugin/jqplot.canvasTextRenderer.js"></script>
<script type="text/javascript" 	src="${pageContext.request.contextPath}/script/plugin/jqplot.canvasAxisTicketRenderer.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugin/jqplot.categoryAxisRenderer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugin/jqplot.pointLabels.js"></script>


<script>
	// When the document loads do everything inside here ...
	
	$(document).ready(
			function() {
			
			
	var campaignId = ${campaignReportsBean.campaignId};
	
		$.ajax({
			//type: "POST",
			//async: false,
			url : "${pageContext.request.contextPath}/usr/reports/view/campaign/id/"+campaignId+"/type/campaignopens/time",
			//contentType: "application/json;",
			//data: "{'groupBy': '" + group + "'}",
			//dataType: 'json',
			success : function(data) {	
				//alert("Data is "+data);
				plotLineChart(data);
			}
		});

	});
	
	$(document).ready(
			function() {
						
	var campaignId = ${campaignReportsBean.campaignId};
	
		$.ajax({
			//type: "POST",
			//async: false,
			url : "${pageContext.request.contextPath}/usr/reports/view/campaign/id/"+campaignId+"/type/region",
			//contentType: "application/json;",
			//data: "{'groupBy': '" + group + "'}",
			//dataType: 'json',
			success : function(data) {
				alert("REGIONNNNNNN "+data);
				plotRegionBarChart(data);
			}
		});

	});

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
			/*openDataSlices.push([ '26-Jan-2013',10 ]);
			openDataSlices.push([ '29-Jan-2013',6  ]);
			openDataSlices.push([ '10-Feb-2013',8  ]);*/
		}
		
		for(i = 0; i < clickResult.length; i++){
		    var dateCount = clickResult[i].split("="); 
			date = dateCount[0];
			count = dateCount[1];
			
			clickDataSlices.push([ date,count ]);
		}
		
		    /*clickDataSlices.push([ '27-Jan-2013',1 ]);
			clickDataSlices.push([ '29-Jan-2013',10 ]);
			clickDataSlices.push([ '10-Feb-2013',6  ]);
			clickDataSlices.push([ '20-Feb-2013',8  ]);*/
		
						
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
				placement : 'outside'
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
					baselineWidth : 1,
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
						
		$('.jqplot-highlighter-tooltip').addClass('ui-corner-all')
	}
	
	
	function plotRegionBarChart(dataIn){
	
	  var dataResult = dataIn.split("##");
		
	  var openResult = dataResult[0].replace("{","").replace("}","").split(",");
	  var clickResult = dataResult[1].replace("{","").replace("}","").split(",");
	  var unsubscribeResult = dataResult[2].replace("{","").replace("}","").split(",");
	  var bounceResult = dataResult[3].replace("{","").replace("}","").split(",");
	  var complainResult = dataResult[4].replace("{","").replace("}","").split(",");
	  
	  var openCounts = new Array(openResult.length);
	  var openTicks = new Array(openResult.length);
	  
	  for(i = 0; i < openResult.length; i++){
		    var dateCount = openResult[i].split("="); 
			region = dateCount[0];
			count = dateCount[1];
			openCounts[i] = count;
			openTicks[i] = region;			
	  }
	  
	  //alert("OPPPPPPPPPPPPPPP "+openCounts+" TTTTTTT "+openTicks);	
		
	  $.jqplot.config.enablePlugins = true;
        var s1 = [2, 6, 7, 10];
        var ticks = ['a', 'b', 'c', 'd'];
         
        plot1 = $.jqplot('barChart1', [openCounts], {
            // Only animate if we're not using excanvas (not in IE 7 or IE 8)..
            animate: !$.jqplot.use_excanvas,
            seriesDefaults:{
                renderer:$.jqplot.BarRenderer,
                pointLabels: { show: true },
				shadowAngle: 135,
                rendererOptions: {
                    barDirection: 'horizontal'}
            },
            axes: {
                yaxis: {
                    renderer: $.jqplot.CategoryAxisRenderer,
                    ticks: openTicks
                }
            },
            highlighter: { show: false }
        });
     
        $('#chart1').bind('jqplotDataClick',
            function (ev, seriesIndex, pointIndex, data) {
                $('#info1').html('series: '+seriesIndex+', point: '+pointIndex+', data: '+data);
            }
        );
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
<div class="content">
 <div class="bodyOfSpan">

 <table width="100%" cellspacing="0" cellpadding="0">
<tbody><tr>
<td width="100%" class="contentLayer">
<table width="98%" cellspacing="0" cellpadding="0" align="center">
<tbody><tr>
<td colspan="4" class="content-heading"><img class="top-icon-padd" src="${pageContext.request.contextPath}/images/report.png">Campign Response Time Report</td>
</tr>


<tr>
<td colspan="4" class="empty"></td>
</tr>

<tr>

<td colspan="2"><b class="normal-text">Name</b> : <span class="normal-text"><c:out value="${campaignReportsBean.campaignName}" /></span> &nbsp; <span class="normal-text">|</span> &nbsp;  <b class="normal-text">Sent at </b> : <span class="normal-text"><c:out value="${campaignReportsBean.sentTime}" /></span> &nbsp; <span class="normal-text">|</span> &nbsp;   <b class="normal-text">From</b> : <span class="normal-text"><c:out value="${campaignReportsBean.senderName}" /> <  <c:out value="${campaignReportsBean.senderEmailId}" /> ></span><span class="normal-text">|</span> &nbsp; <b class="normal-text">Sent To</b> : <span class="normal-text"><a href="${pageContext.request.contextPath}/usr/subscriber/view/group/subscribers/id/${campaignReportsBean.subscriberListId}"><c:out value="${campaignReportsBean.subscriberListName}" /></a></span> &nbsp; </td>
<td></td>
<td align="right"><button class="btn btn-success" type="button">View</button> <button class="btn btn-success" type="button">Share</button></td>
</tr>
<tr>
<td colspan="4" class="empty content-heading"></td>
</tr>





<tr class="reportcampheader" onmouseover="this.className='reporcampHeaderHover'" onmouseout="this.className='reportcampheader'">
<td colspan="4">
<table width="100%" cellspacing="0" cellpadding="0" align="center" class="report-bg">
<tbody><tr>
<td width="25%" height="80" align="center" class="report-bdr-left"><div>Total Email Sent </div><div class="report-green-txt"><a href="#"><c:out value="${campaignReportsBean.totalEmailsSent}" /></a></div></td>

<td width="25%" align="center" class="report-bdr-left"><div align="center">Opened Emails</div><div class="report-green-txt" align="center"><c:out value="${campaignReportsBean.openedPercentage}" />% &nbsp; <a href="#"><c:out value="${campaignReportsBean.opened}" /> </a></span></div></td>

<td width="25%" align="center" class="report-bdr-left">Clicked<div class="report-blue-txt"><c:out value="${campaignReportsBean.clickedPercentage}" />% &nbsp;<a href="#"><c:out value="${campaignReportsBean.clicked}" /> </a></div></td>

</tr>


<tr>
<td height="80" align="center" class="report-bdr-left"><div>Bounced</div><div class="report-bounced"><c:out value="${campaignReportsBean.bouncedPercentage}" />% &nbsp;<a href="#"><c:out value="${campaignReportsBean.bounced}" /></a></div></td>

<td align="center" class="report-bdr-left">Unsubscribed<div class="report-bounced"><c:out value="${campaignReportsBean.unsubscribedPercentage}" />% &nbsp; <a href="#"><c:out value="${campaignReportsBean.unsubscribed}" /></a></div></td>

<td align="center" class="report-bdr-left">Dropped<div class="report-bounced"><c:out value="${campaignReportsBean.droppedPercentage}" />% &nbsp; <a href="#"><c:out value="${campaignReportsBean.dropped}" /> </a></div></td>

</tr>

</tbody></table>
</td>
</tr>




<tr>
<td colspan="4" class="content-heading">Open and Circles Overtime</td>
</tr>



<tr>

<td>

<div id="lineChart1"
					style="margin-top: 20px; margin-left: 20px; width: 900px; height: 250px;"></div>
					
					
	
		<div id="pieChart"
					style="margin-top: 20px; margin-left: 20px; width: 400px; height: 300px;">
		</div>
		
		<div id="barChart1"
					style="margin-top: 20px; margin-left: 20px; width: 600px; height: 300px;">
		</div>

</td>

</tr>


<tr>
<td colspan="4" class="empty "></td>
</tr>

<tr>
<td colspan="4" class="empty content-heading"></td>
</tr>

<tr>
<td colspan="4" class="content-heading">Link Activity</td>
</tr>


<tr>
<td width="40%" colspan="4">
<table width="100%" cellspacing="0" cellpadding="0">
<tbody><tr>
<td class="table-heading-bg">Links</td>
<td class="table-heading-bg">Uniqe Circles</td>
<td class="table-heading-bg">Total Circles</td>
</tr>
<tr class="emailcampheader" onmouseover="this.className='emailcampHeaderHover'" onmouseout="this.className='emailcampheader'">
<td class="tbl-btm-bdr">arunkumar</td>
<td class="tbl-btm-bdr"><a href="#">arunkumararunkumar</a></td>
<td class="tbl-btm-bdr">12-5-2012</td>
</tr>
<tr class="emailcampheader" onmouseover="this.className='emailcampHeaderHover'" onmouseout="this.className='emailcampheader'">
<td class="tbl-btm-bdr">arunkumar</td>
<td class="tbl-btm-bdr"><a href="#">arunkumararunkumar</a></td>
<td class="tbl-btm-bdr">12-5-2012</td>
</tr>
<tr class="emailcampheader" onmouseover="this.className='emailcampHeaderHover'" onmouseout="this.className='emailcampheader'">
<td class="tbl-btm-bdr">arunkumar</td>
<td class="tbl-btm-bdr"><a href="#">arunkumararunkumar</a></td>
<td class="tbl-btm-bdr">12-5-2012</td>
</tr>
<tr class="emailcampheader" onmouseover="this.className='emailcampHeaderHover'" onmouseout="this.className='emailcampheader'">
<td class="tbl-btm-bdr">arunkumar</td>
<td class="tbl-btm-bdr"><a href="#">arunkumararunkumar</a></td>
<td class="tbl-btm-bdr">12-5-2012</td>
</tr>
</tbody></table>
</td>
</tr>
<tr>
<td colspan="4" class="empty "></td>
</tr>






<tr>
<td colspan="4">
<table width="100%" cellspacing="0" cellpadding="0">
<tbody><tr>
<td width="40%" align="center"><img src="${pageContext.request.contextPath}/images/pie.gif"></td>
<td width="2%"></td>
<td width="28%">
<table width="100%" cellspacing="0" cellpadding="0">
<tbody><tr class="emailcampheader" onmouseover="this.className='emailcampHeaderHover'" onmouseout="this.className='emailcampheader'">
<td class="tbl-btm-bdr">3 emails sent</td>
</tr>
<tr class="emailcampheader" onmouseover="this.className='emailcampHeaderHover'" onmouseout="this.className='emailcampheader'">
<td class="tbl-btm-bdr">3  Opened</td>
</tr>
<tr class="emailcampheader" onmouseover="this.className='emailcampHeaderHover'" onmouseout="this.className='emailcampheader'">
<td class="tbl-btm-bdr">3 Bounced</td>
</tr>

<tr>
<td class="tbl-btm-bdr">3 Not Opened</td>
</tr>
</tbody></table>
</td>

<td width="2%"></td>
<td width="28%">

<table width="100%" cellspacing="0" cellpadding="0">
<tbody><tr class="emailcampheader" onmouseover="this.className='emailcampHeaderHover'" onmouseout="this.className='emailcampheader'">
<td class="tbl-btm-bdr"><b>0.00 (3)</b> Opened</td>
</tr>
<tr class="emailcampheader" onmouseover="this.className='emailcampHeaderHover'" onmouseout="this.className='emailcampheader'">
<td class="tbl-btm-bdr"><b>0.00 (3)</b> Opened</td>
</tr>
<tr class="emailcampheader" onmouseover="this.className='emailcampHeaderHover'" onmouseout="this.className='emailcampheader'">
<td class="tbl-btm-bdr"><b>0.00 (3)</b> Bounced</td>
</tr>

<tr>
<td class="tbl-btm-bdr"><b>0.00 (3)</b> Not Opened</td>
</tr>
</tbody></table>
</td>

</tr>
</tbody></table>
</td>
</tr>

</tbody></table>
</td>
</tr>
</tbody></table>


 </div>

</div>


</form:form>


