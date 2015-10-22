<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="/WEB-INF/tlds/functions.tld" %>
<div class="logo">
    <a href="<c:url value="/Admin"/>">
        <span class="wow fadeInLeft" data-wow-delay="0.1s">L</span><span class="wow fadeInRightBig" data-wow-delay="10ms">otusinvest</span>
    </a>
</div>
<div class="scroll-bar">
    <ul class="nav sidebar-nav">
        <c:set var="PARENTLIST" value="${SIDEBAR_PAGINATION.displayList}" />
        <c:forEach items="${f:filterModuleList(PARENTLIST, null)}" var="nodeModule">
            <li class="tittle"><span>${nodeModule.name}</span></li>
                    <c:forEach items="${f:filterModuleList(PARENTLIST, nodeModule)}" var="childrenModuleLv1">
                <li>
                    <a class="external">
                        <span class="fa ${childrenModuleLv1.icon} item-icon"></span>
                        <span class="sidebar-title">${childrenModuleLv1.name}</span>
                        <c:if test="${f:size(f:filterModuleList(PARENTLIST, childrenModuleLv1))>0}">
                            <span class="fa fa-angle-down item-expand"></span>
                        </c:if>
                    </a> 

                    <c:if test="${f:size(f:filterModuleList(PARENTLIST, childrenModuleLv1))>0}">
                        <ul>
                            <c:forEach items="${f:filterModuleList(PARENTLIST, childrenModuleLv1)}" var="childrenModuleLv2">
                                <li>
                                    <a class="${childrenModuleLv2.cssClass}" controller='<c:url value="${SIDEBAR_PAGINATION.ROOT_CONTROLLER}${childrenModuleLv1.controller}${childrenModuleLv2.controller}"/>'>${childrenModuleLv2.name}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </c:if>
                </li>
            </c:forEach>
        </c:forEach>
    </ul>
</div>
<!--<div class="contact-me">
    Thiết kế bởi <a href="http://adcvietnam.net/">ADCVietnam</a>
</div>-->