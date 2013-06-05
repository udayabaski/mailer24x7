<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Mailer247 - Registration</title>
	
<link href="${pageContext.request.contextPath}/styles/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/styles/signup-style.css" rel="stylesheet" type="text/css" />
<!--[if lt IE 9]><script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/script/excanvas.js"></script><![endif]-->
<link href="${pageContext.request.contextPath}/styles/jquery-ui-1.9.1.custom.min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery-ui-1.9.1.custom.min.js"></script>

	<script>
    	$(function() {
          $( "#tabs" ).tabs();
        });
    </script>

</head>

<body>

<form:form action="admin" method="POST" commandName="adminBean">


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
										<li><a href="#tabs-1" title="content_1" class="tab active">Settings</a></li>
										<li><a href="#tabs-2" title="content_2" class="tab">Users</a></li>
										<li><a href="#tabs-3" title="content_3" class="tab">Privileges</a></li>
											<li>

											</li>
									</ul>
									<div class="right-link">
										<a href="${pageContext.request.contextPath}/usr/admin/new/user" class="button grey-btn mar1" ><img src="${pageContext.request.contextPath}/images/add.png" width="16" height="15" alt="" />Add User</a>
									</div>

									<div id="tabs-1" class="content">
										<table width="100%" cellspacing="0" cellpadding="0" border="0">
										  <tbody><tr>
										  <td valign="top" height="300">
										  <div class="setting-content">
									  
											<!-- <div class="settings-block">
												<div class="seticon1 sections left"></div>
												<div class="content-block">
													<div class="titletxt"> Subscription </div>
													<div class="greytxt">Upgrade your plan, reach a higher volume of subscribers. The more the merrier.</div>
												</div>
											</div>  -->

											<div class="settings-block">
												<div class="seticon2 sections left"></div>
												<div class="content-block">
													<div class="titletxt"><a href="${pageContext.request.contextPath}/usr/admin/edit/org">Company Profile</a></div>
													<div class="greytxt">Configure your organization profile and include an image of your brand logo.</div>
												</div>
											</div>  
											<div class="settings-block last">
												<div class="seticon3 sections left"></div>
												<div class="content-block">
													<div class="titletxt"><a href="${pageContext.request.contextPath}/usr/admin/edit/user">User Profile</a></div>
													<div class="greytxt">Update your location details. Local subscribers will be listed based on this information.</div>
												</div>
											</div>
											<div class="settings-block">
												<div class="seticon2 sections left"></div>
												<div class="content-block">
													<div class="titletxt"><a href="${pageContext.request.contextPath}/usr/pwd/change">Company Profile</a></div>
													<div class="greytxt">Configure your organization profile and include an image of your brand logo.</div>
												</div>
											</div>   
											<!-- <div class="settings-block">
												<div class="seticon4 sections left"></div>
												<div class="content-block">
													<div class="titletxt">Merge Tags</div>
													<div class="greytxt">Personalize campaigns by addressing your subscribers with their first name and more.</div>
												</div>
											</div>

											<div class="settings-block">
												<div class="seticon5 sections left"></div>
												<div class="content-block">
													<div class="titletxt">Custom Fields</div>
													<div class="greytxt">Add your own fields to your contact details apart from basic default fields we provide.  </div>
												</div>
											</div>
										 
											<div class="settings-block last">
												<div class="seticon6 sections left"></div>
												<div class="content-block">
													<div class="titletxt">Campaign Settings</div>
													<div class="greytxt">Display links in your campaign. Be it web view, subscription, forwarding or more.</div>
												</div>
											</div>
										   
											<div class="settings-block">
												<div class="seticon7 sections left"></div>
												<div class="content-block">
													<div class="titletxt">Sender Address</div>
													<div class="greytxt">Use respective sender addresses to match your email campaigns and increase reach.</div>
												</div>
											</div>
											
											<div class="settings-block">
												<div class="seticon8 sections left"></div>
												<div class="content-block">
													<div class="titletxt">Integrations </div>
													<div class="greytxt">Integrate and share email campaigns on social accounts. Reach a wider audience.</div>
												</div>
											</div>
											
											<div class="settings-block last">
												<div class="seticon9 sections left"></div>
												<div class="content-block">
													<div class="titletxt">API &amp; Call Back</div>
													<div class="greytxt">Manage campaigns and mailing lists without logging in. Track subscriber activity too.</div>
												</div>
											</div>
											
											<div class="settings-block">
												<div class="seticon10 sections left"></div>
												<div class="content-block">
													<div class="titletxt">Sign Up Form Themes</div>
													<div class="greytxt">Design themes as you like to match your websites and associate them to your lists.</div>
												</div>
											</div>  -->
									</div>
													  
									  </td>
								  </tr>
								  </tbody></table>
									</div>
									<div id="tabs-2" class="content">
										<ul class="list">
										
            							<tr>
            								<td class="table-heading">Email ID</td>
            								<td class="table-heading">Full Name</td>
            								<td class="table-heading">Role</td>
            							</tr>
            
              							<c:forEach items="${adminBean.usersList}" var="pageItem" varStatus="reqStatus">
            
             							<tr onmouseout="this.className='emailcampheader'" onmouseover="this.className='emailcampHeaderHover'" class="emailcampheader">
              								<td class="tbl-btm-bdr"><a href="profile.form?action=edit&object=<c:out value="${pageItem.userId}"/>"><c:out value="${pageItem.emailId}"></c:out></a></td>
              								<td class="tbl-btm-bdr"><a href="profile.form?action=delete&object=<c:out value="${pageItem.userId}"/>"><c:out value="${pageItem.fullName}"></c:out></a></td>
              								<td class="tbl-btm-bdr"><c:out value="${pageItem.roleName}"></c:out></td>
             							</tr>
            
            						    </c:forEach>
            
											
										</ul>
									</div>
									<div id="tabs-3" class="content">
										<h3>Mailing List Privileges</h3>
										<table width="98%" cellpadding="0" cellspacing="0" align="center" class="campaign-page">
											<tr valign="top">
												<td width="20"><input type="checkbox" /></td>
												<td class="txthd">
													<p class="greytxt"> Only mailing list owners can access the respective list(s). </p>
												</td>
											</tr>
											<tr valign="top">
												<td width="20"><input type="checkbox" /></td>
												<td class="txthd">
													<p class="greytxt"> Admin only can access other user's mailing list including the export option. </p>
												</td>
											</tr>
											<tr valign="top">
												<td width="20"><input type="checkbox" /></td>
												<td class="txthd">
													<p class="greytxt"> All users can access other user's mailing list, but admin only allowed to export it. </p>
												</td>
											</tr>
											<tr valign="top">
												<td width="20"><input type="checkbox" /></td>
												<td class="txthd">
													<p class="greytxt"> All users can access other user's mailing list and also export the same. </p>
												</td>
											</tr>
										</table>
										<h3>Campaign Privilege</h3>
										<p class="hdtxt"><b>Allow for Edit, Delete and Send Campaigns</b></p>
										<table width="98%" cellpadding="0" cellspacing="0" align="center" class="campaign-page">
											<tr valign="top">
												<td width="20"><input type="checkbox" /></td>
												<td class="txthd">
													<p class="greytxt"> Only Campaign owners can view, edit, delete and send their respective campaigns </p>
												</td>
											</tr>
											<tr valign="top">
												<td width="20"><input type="checkbox" /></td>
												<td class="txthd">
													<p class="greytxt"> Admin can view, edit, delete and send other users campaigns </p>
												</td>
											</tr>
											<tr valign="top">
												<td width="20"><input type="checkbox" /></td>
												<td class="txthd">
													<p class="greytxt"> Users can view, edit, delete and send other users campaigns </p>
												</td>
											</tr>
										</table>
										<p class="hdtxt"><b>Allow for Campaign Reports and its Analysis</b></p>
										<table width="98%" cellpadding="0" cellspacing="0" align="center" class="campaign-page">
											<tr valign="top">
												<td width="20"><input type="checkbox" /></td>
												<td class="txthd">
													<p class="greytxt"> Only campaign owners can view respective campaign reports. </p>
												</td>
											</tr>
											<tr valign="top">
												<td width="20"><input type="checkbox" /></td>
												<td class="txthd">
													<p class="greytxt"> Admin can view campaign reports of all users in this organization. </p>
												</td>
											</tr>
											<tr valign="top">
												<td width="20"><input type="checkbox" /></td>
												<td class="txthd">
													<p class="greytxt"> Users can view campaign reports of other users in this organization. </p>
												</td>
											</tr>
										</table>
										<h3>Sender/Reply-To Address Privilege</h3>
										<table width="98%" cellpadding="0" cellspacing="0" align="center" class="campaign-page">
											<tr valign="top">
												<td width="20"><input type="checkbox" /></td>
												<td class="txthd">
													<p class="greytxt"> Only admins of our organization can use sender addresses added by other users </p>
												</td>
											</tr>
											<tr valign="top">
												<td width="20"><input type="checkbox" /></td>
												<td class="txthd">
													<p class="greytxt"> All users of our organization can use sender addresses added by other users </p>
												</td>
											</tr>	
										</table>
										<h3>Template Library Privilege</h3>
										<table width="98%" cellpadding="0" cellspacing="0" align="center" class="campaign-page">
											<tr valign="top">
												<td width="20"><input type="checkbox" /></td>
												<td class="txthd">
													<p class="greytxt"> Only template owners can access respective templates. </p>
												</td>
											</tr>
											<tr valign="top">
												<td width="20"><input type="checkbox" /></td>
												<td class="txthd">
													<p class="greytxt"> Administrator can access all the templates. </p>
												</td>
											</tr>
											<tr valign="top">
												<td width="20"><input type="checkbox" /></td>
												<td class="txthd">
													<p class="greytxt"> Users can access all the templates. </p>
												</td>
											</tr>
										</table>
										<h3>Post Campaign Privilege</h3>
										<table width="98%" cellpadding="0" cellspacing="0" align="center" class="campaign-page">
											<tr valign="top">
												<td width="20"><input type="checkbox" /></td>
												<td class="txthd">
													<p class="greytxt"> Only post campaign owners can access respective post campaigns. </p>
												</td>
											</tr>
											<tr valign="top">
												<td width="20"><input type="checkbox" /></td>
												<td class="txthd">
													<p class="greytxt"> Administrator can access all post campaigns. </p>
												</td>
											</tr>
											<tr valign="top">
												<td width="20"><input type="checkbox" /></td>
												<td class="txthd">
													<p class="greytxt"> Users can access all post campaigns. </p>
												</td>
											</tr>
											<tr valign="top">
												<td colspan="2" align="center">
													<a href="#" class="button green" onclick="document.forms[0].submit();return false;">Save</a>
												</td>
											</tr>
										</table>
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

</form:form>





<!--footer part starts!-->

<!--footer part ends!-->
</body>
</html>

