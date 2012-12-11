
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Mailer247 - Create Campaign Step2</title>

<link href="${pageContext.request.contextPath}/styles/style.css" rel="stylesheet" type="text/css" />
<!--[if lt IE 9]><script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/script/excanvas.js"></script><![endif]-->

<script src="${pageContext.request.contextPath}/script/jquery-1.8.3.min.js"></script>
<script src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>


<script type="text/javascript">

	function submitPrev(){
	  document.getElementById("nextAction").value="prev";
	  document.forms[0].submit();
	}
	
	function submitNext(){
	   document.getElementById("htmlData").value=CKEDITOR.instances.editorContent.getData();
	  document.getElementById("nextAction").value="next";
	  document.forms[0].submit();
	}
	
	function submitExit(){
	  document.getElementById("htmlData").value=CKEDITOR.instances.editorContent.getData();
	  document.getElementById("nextAction").value="exit";
	  document.forms[0].submit();
	}
	
	function submitUpdate(){
	  document.forms[0].action = "${pageContext.request.contextPath}/usr/campaign/update/html";
	  document.getElementById("nextAction").value="update";
	  document.forms[0].submit();
	}
	
	function submitPreview(){	  
	  writeConsole(CKEDITOR.instances.editorContent.getData());
	}
	
	function writeConsole(content) {
      top.consoleRef=window.open('','Preview',
       'width=600,height=650'
        +',menubar=0'
   		+',toolbar=1'
   		+',status=0'
   		+',scrollbars=1'
   		+',resizable=1')
 	 top.consoleRef.document.writeln(
  		'<html><head><title>Preview</title></head>'
   			+'<body bgcolor=white onLoad="self.focus()">'
   					+content
   		+'</body></html>'
 		)
 		top.consoleRef.document.close()
	}
	
</script>

</head>

<body>

<form:form id="campaignEditor" commandName="campaignStep2EditorForm" action="${pageContext.request.contextPath}/usr/campaign/save/html" method="POST">
<form:hidden path="nextAction" id="nextAction" />
<form:hidden path="campaignId" />
<form:hidden path="editorType" />
<form:hidden path="toPage" />
<form:hidden path="htmlData" id="htmlData"/>

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
<td class="content-heading" colspan="4"><img src="${pageContext.request.contextPath}/images/email_add.png" class="top-icon-padd" />Email Content</td>
</tr>

<tr>
<td class="empty" colspan="4"></td>
</tr>


<tr>
<td>

<div id="main">

	<ul id="steps">
		<li id="stepDesc0" class="current"> <span class="no">1</span><span>Campaign</span>&nbsp;</li>
		<li id="stepDesc1" class="current"><span class="no">2</span><span>Email Template</span>&nbsp;</li>
		<li id="stepDesc2" class=""><span class="no">3</span><span>Subscriber Group</span>&nbsp;</li>
	</ul>
	
        <fieldset>

            

		<div class="btn" style="padding-top:0px;margin:0px;padding-bottom:20px;" >
			<table>
				<tr>
					<!-- <td width="100"><span class="previous"><a href="#" onclick="javascript:submitPrev()">Previous</a></span></td> -->
					<td align="center"><a href="#" class="button green" onclick="javascript:submitPreview()">Preview</a></td>
					<td width="100"><span class="next1"><a href="#" onclick="javascript:submitNext()">Next</a></span></td>
				</tr>
			</table>
			
		</div>
		<table width="99%" cellpadding="0" cellspacing="0" class="campaign-page">
	    <tr valign="top">
			<td>
			 
			  <textarea rows="100" cols="100" name="editorContent"></textarea>
            <script>
                CKEDITOR.replace( 'editorContent' );
            </script>
			</td>
		</tr>
		</table>
		<div class="btn">
			<table>
				<tr>
				 <c:set var="toPage" value="${campaignStep1Form.toPage}"></c:set>
				  <c:choose>
				   <c:when test="${not empty toPage}">
				    <td width="100">&nbsp;</td>
				    <td align="center"><a href="#" class="button green" onclick="javascript:submitPreview()">Preview</a></td>
					<td align="center"><a href="#" class="button green" onclick="javascript:submitUpdate()">Update</a></td>
				   </c:when>
				   <c:otherwise>
					<td width="100"><span class="previous"><a href="#" onclick="javascript:submitPrev()">Previous</a></span></td>
					<td width="100"><span class="previous"><a href="#" onclick="javascript:submitExit()">Save & Exit</a></span></td>  
					<td align="center"><a href="#" class="button green" onclick="javascript:submitPreview()">Preview</a></td>
					<td width="100"><span class="next1"><a href="#" onclick="javascript:submitNext()">Next</a></span></td>
				   </c:otherwise>
				   </c:choose>
					
				</tr>
			</table>
		</div>
        </fieldset>

        </form>

    </div>
    
    </td></tr></table>
    
    
</td></tr></table>


<td width="10%"></td>
</tr>
</table>
<!--content main controller table ends!-->

</form:form>


</body>


</body>
</html>
