<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="/WEB-INF/tlds/functions.tld" %>
<div class="container">
    <div class="row">
        <div class="col-md-6">
            <ul class="menu-top menu-left clearfix">
                <c:if test="${sessionScope['CUSTOMER_ID']!=null}">
                    <c:set value="${f:findDistributorById(sessionScope['CUSTOMER_ID'])}" var="cus" />
                    <li>
                        <a href="">Xin chào, ${cus.lastName}!</a>
                    </li>
                    <li>
                        <a href="<c:url value='/Customer/Logout'/>">Thoát</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope['CUSTOMER_ID']==null}">
                    <li>
                        <a class="btn-change-content" controller="<c:url value='/Customer/Login'/>">Đăng nhập</a>
                    </li>
                    <li>
                        <a class="btn-change-content" controller="<c:url value='/Customer/Register'/>">Đăng ký</a>
                    </li>
                </c:if>
            </ul>
        </div>
        <div class="col-md-6 text-right">
            <ul class="menu-top menu-right clearfix">
                <li class="top-menu-lang">
                    <a><img src="/resources/img/lang/Flag_of_Vietnam.png"/>Vietnamese (VI)&nbsp;<span class="fa fa-angle-down"></span></a>
                    <ul class="hidden">
                        <li><a><img src="/resources/img/lang/Flag_of_Vietnam.png"/>Vietnamese (VI)</a></li>
                        <li><a><img src="/resources/img/lang/Flag_of_American.png"/>English (US)</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</div>
