<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>



 <!--Side navigation tiles Starts-->   
        
<aside class="side-left">
    <ul class="sidebar">
         <li class="first"> <!--always define class .first for first-child of li element sidebar left-->
            
                <div class="helper-font-24">
                    <i class="icon-user user-icon-size"></i>
                </div>
                <span class="sidebar-text"> <a href="${pageContext.request.contextPath}/usr/campaign/view/all">Email</a></span>
            
        </li>
        
    
        <li class="first"> <!--always define class .first for first-child of li element sidebar left-->
            
                <div class="helper-font-24">
                    <i class="icon-user user-icon-size"></i>
                </div>
                <span class="sidebar-text"><a href="${pageContext.request.contextPath}/usr/subscriber/view/all/groups">Subscriber</a></span>
            
        </li>
        <li class="first"> <!--always define class .first for first-child of li element sidebar left-->
            
                <div class="helper-font-24">
                    <i class="icon-table"></i>
                </div>
                <span class="sidebar-text"><a href="${pageContext.request.contextPath}/usr/reports/view/all">Reports</a></span>
            
        </li>
        <li class="first"> <!--always define class .first for first-child of li element sidebar left-->
            
                <div class="helper-font-24">
                    <i class="icon-cog"></i>
                </div>
                <span class="sidebar-text"><a href="${pageContext.request.contextPath}/usr/admin">Settings</a></span>
            
        </li>
        
    </ul>
</aside>

<!--Side navigation tiles Ends-->   


 



