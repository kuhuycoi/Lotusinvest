<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="/WEB-INF/tlds/functions.tld" %>
<c:set var="ADMIN" value="${f:findAdminById(sessionScope['ADMIN_ID'])}"/>
<div class="pull-left header-btns">
    <ul class="clearfix">
        <li><a id="hide-sidebar"><span class="glyphicon glyphicon-pushpin"></span></a></li>
        <li><a id="btn-expand-sidebar"><span class="glyphicon glyphicon-align-justify"></span></a></li>
        <li><a id="fullscreen-toggle"><span class="glyphicon glyphicon-fullscreen"></span></a></li>
    </ul>
</div>
<div class="pull-right header-btns">
    <ul class="clearfix">
        <li class="user-menu dropdown">
            <a data-toggle="dropdown" class="external">
                Xin chào, <label>${ADMIN.lastName}!</label>
            </a>
            <ul class="dropdown-menu checkbox-persist wow flipInY center animated dropdown-menu-right" role="menu">
                <li class="menu-arrow">
                    <div class="menu-arrow-up"></div>
                </li>
                <li class="dropdown-header">
                    ${ADMIN.userName} <span class="pull-right glyphicon glyphicon-user"></span>
                </li>
                <li>
                    <ul class="dropdown-items">
                        <li>
                            <div class="item-icon"><i class="fa fa-envelope-o"></i> </div>
                            <a class="item-message">Messages</a> </li>
                        <li>
                            <div class="item-icon"><i class="fa fa-calendar"></i> </div>
                            <a class="item-message">Calendar</a> </li>
                        <li class="border-bottom-none">
                            <div class="item-icon"><i class="fa fa-cog"></i> </div>
                            <a class="item-message">Settings</a> 
                        </li>
                        <li class="padding-none clearfix">
                            <div class="pull-left dropdown-edit"><i class="fa fa-edit"></i> <a class="open-modal" href="">Edit Profile</a></div>
                            <div class="pull-right dropdown-signout"><i class="fa fa-sign-out"></i> <a href='<c:url value="/Admin/Logout.html"/>'>Sign Out</a></div>
                        </li>
                    </ul>
                </li>
            </ul>
        </li>
    </ul>
</div>