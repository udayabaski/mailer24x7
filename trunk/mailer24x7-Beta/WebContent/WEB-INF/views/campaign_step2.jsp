


<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<script src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>


<script type="text/javascript">

	function submitPrev(){
	  document.getElementById("nextAction").value="prev";
	  document.forms[0].submit();
	}
	
	function submitNext(){
	  document.getElementById("nextAction").value="next";
	  document.forms[0].submit();
	}
	
	function submitExit(){
	  document.getElementById("nextAction").value="exit";
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
	
	function changeit(txt){
	    		
				
    				if(txt == "0"){
	    						
							document.getElementById("fhtml").style.display="block" ;
							document.getElementById("ckeditor").style.display="none" ;
						}
    				if(txt == "2"){
							    						
							document.getElementById("fhtml").style.display="none" ;
							document.getElementById("ckeditor").style.display="block" ;
													
							    						
    						}
    					}
    					
	
</script>


<div class="content">
 <div class="bodyOfSpan">
 <table cellpadding="0" cellspacing="0" width="100%">
 <tr>
 <td class="contentLayer">

<table width="98%" cellspacing="0" cellpadding="0" align="center">
<tr>
<td  colspan="2" class="content-heading"><div class="wizard-menu">
<div class="completed-wiz"><a href="#" style="text-decoration:none;"><span>1</span> Campaign Settings</a></div>
<div  class="wizard-menu-new" ><a href="#" style="text-decoration:none;"><span>2</span> Create my campaign</a></div>
<div class="completed-wiz"><a href="#" style="text-decoration:none;"><span>3</span> Subscriber List</a></div>
<div class="completed-wiz"><a href="#" style="text-decoration:none;"><span>4</span> Delivery</a></div>
</div></td>

</tr>


<tr>
<td class="empty" colspan="2"></td>
</tr>

<tr>
<td colspan="2">

<form:form id="campaignStep2" commandName="campaignStep2Form" action="${pageContext.request.contextPath}/usr/campaign/save/step2" method="POST" enctype="multipart/form-data">
<form:hidden path="nextAction" id="nextAction" />
<form:hidden path="campaignId" />
<form:hidden path="htmlData" id="htmlData"/>

<fieldset>


<table cellpadding="0" cellspacing="0" width="100%">
<tr>
<td colspan="2"><span class="radiospan"><form:radiobutton path="contentType" value="0" checked="checked" onclick="javascript:changeit('0')" /></span><p class="radio-text">Import My design</p>
<span class="radiospan"><form:radiobutton path="contentType" value="2" onclick="javascript:changeit('2')"/></span><p class="radio-text">Html Plain Text</p></td>
<!--<span class="radiospan"><form:radiobutton path="contentType" value="0"/><p class="radio-text">Template</p></span></td>-->
</tr>

<tr>
<td>&nbsp; <td>
<td><div id="fhtml">
 <table width="98%" cellpadding="0" cellspacing="0" align="center" class="campaign-page">
	    <tr valign="top">
			<td width="50">&nbsp;</td>
			<td class="txthd">
				<h4>HTML File</h4>
				<p class="greytxt">Browse and select a HTML file that you created for this campaign.</p>
				<p><form:input path="htmlFileData" type="file" id="htmlFileDataId"/></p>
			</td>
		</tr>
	    <tr valign="top">
			<td height="40" colspan="2">&nbsp;</td>
		</tr>
	    <tr valign="top">
			<td width="50">&nbsp;</td>
			<td class="txthd">
				<h4>Image files (optional)</h4>
				<p class="greytxt">Zip additional files(if any), that were created for this campaign and upload them here. This may include your logo, banner, CSS files. For more details on this topic, please refer to our help section.</p>
				<p><form:input path="zipFileData" type="file" id="htmlFileDataId"/></p>
			</td>
		</tr>
 </table></div></td>
</tr>

<tr>
<td>&nbsp; <td>
<td><div id="ckeditor">
<table width="99%" cellpadding="0" cellspacing="0" class="campaign-page">
	    <tr valign="top">
			<td>
			 
			 
            <textarea name='editorContent' > </textarea>
            <script>
                CKEDITOR.replace( 'editorContent' );
            </script>
			</td>
		</tr>
		</table>
		</div></td>
</tr>

<tr>

	<td width="100"><span class="previous"><a href="#" onclick="javascript:submitPrev()">Previous</a></span></td>
	<td align="center"><a href="#" class="btn btn-success" onclick="javascript:submitExit()">Save &amp; Exit</a></td>
	<td align="center"><a href="#" class="btn btn-success" onclick="javascript:submitPreview()">Preview</a></td>
	<td width="100"><span class="next1"><a href="#" onclick="javascript:submitNext()">Next</a></span></td> 
  
 </tr>
<!--Import My design ends-->
</table>
</fieldset>
</form:form>
</td>
</tr>
</table>
</td>
 </tr>
 </table>
 </div>
 </div>
<script>
document.getElementById("ckeditor").style.display="none" ;
document.getElementById("fhtml").style.display="block" ;
</script>