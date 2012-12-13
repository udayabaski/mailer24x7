<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<script>
(function($){
$.fn.fixedMenu=function(){
return this.each(function(){
var linkClicked= false;
var menu= $(this);
$('body').bind('click',function(){

if(menu.find('.active-menu').size()>0 && !linkClicked)
{
menu.find('.active-menu').removeClass('active-menu');
}
else
{
linkClicked = false; 
}
});

menu.find('ul li > a').bind('click',function(){
linkClicked = true;
if ($(this).parent().hasClass('active-menu')){
$(this).parent().removeClass('active-menu');
}
else{
$(this).parent().parent().find('.active-menu').removeClass('active-menu');
$(this).parent().addClass('active-menu');
}
})
});
}
})(jQuery);
</script>
<script>
        $('document').ready(function(){
            $('.menu').fixedMenu();
        });
        </script>

<table width="100%" cellpadding="0" cellspacing="0">
<tr>
<!--logo part starts!-->
<td width="100%">
<!--logo main controller table starts!-->
<table cellpadding="0" cellspacing="0" width="100%">


<tr>
<td width="10%"></td>
<td width="80%" >
<table cellpadding="0" cellspacing="0" width="100%">
<tr><td colspan="2" ></td></tr>

<tr>
<td  width="10%" style="height:60px;">
<img src="${pageContext.request.contextPath}/images/mailer-logo.png" /> 
</td>
<td align="right" valign="top">
<div class="menu">
        <ul>
            <li>
                <a href="${pageContext.request.contextPath}/j_spring_security_logout">Logout<span class="arrow"></span></a>
                
                <ul>

                    <li><a href="${pageContext.request.contextPath}/j_spring_security_logout">Logout</a></li>
                    <li><a href="#">Arunkumar</a></li>
                   <li><a href="#">Appadurai</a></li>
                    <li><a href="#">Arunkumar</a></li>
                </ul>
            </li>
            <li>

                <a href="#">Settings<span class="arrow"></span></a>
                <ul>
                    <li><a href="#">Galleries</a></li>
                    <li><a href="#">DropDown Menus</a></li>
                    <li><a href="#">Content Slider</a></li>
                    <li><a href="#">LightBox</a></li>

                </ul>
            </li>
            <li>
                <a href="#">Contact Us</a>
                
            </li>
            <li>
                <a href="#">About Us</a>
                
            </li>
            
            <li>
               Welcome <a href="#" class="menulink"><security:authentication property="principal.fullName"/></a>                
            </li>
        </ul>

    </div>
</td>

</tr>
</table>
</td>
<td width="10%"></td>
</tr>
</table>
<!--logo main controller table ends!-->
</td>
<!--logo part ends!-->
</tr>

</table>

<table width="100%" cellpadding="0" cellspacing="0">
<tr>
<!--header part starts!-->
<td width="100%">
<!--header main controller table starts!-->
<table cellpadding="0" cellspacing="0" width="100%">
<tr>
<td width="10%"></td>
<td width="80%" >
<table cellpadding="0" cellspacing="0" width="100%">
<tr>
<td width="100%">
<div id="menu_wrapper" class="grey">
<div class="left"></div>
<ul id="menu">
         
<li><a href="${pageContext.request.contextPath}/usr/home">Home</a></li>  
<li><a href="${pageContext.request.contextPath}/usr/campaign/view/all">Email</a></li>
<li><a href="${pageContext.request.contextPath}/usr/subscriber/view/all/groups">Subscribers</a></li>
<li><a href="${pageContext.request.contextPath}/usr/reports/view/all">Reports</a></li>
<li class="active"><a href="${pageContext.request.contextPath}/usr/admin">Admin</a></li>


</ul>
</div>
</td>
</tr>
</table>
</td>
<td width="10%"></td>
</tr>
</table>
<!--header main controller table ends!-->
</td>
<!--header part ends!-->
</tr>

</table>
