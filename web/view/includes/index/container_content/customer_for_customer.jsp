<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="/WEB-INF/tlds/functions.tld" %>
<c:set var="PAGINATION" value="${sessionScope['INDEX_CUSTOMER_FOR_CUSTOMER_PAGINATION']}"/>
<input type="hidden" id="reloadController" value="<c:url value='${PAGINATION.ROOT_CONTROLLER}${PAGINATION.childrenController}${PAGINATION.grandController}'/>">
<h1>Thông tin hệ thống</h1>
<div class="panel">
    <div class="panel-heading">
        <h2>Danh sách đã giới thiệu</h2>
    </div><!-- end panel heading -->
    <div class="ajax-content panel-body">
        <c:import url="${PAGINATION.ROOT_CONTROLLER}${PAGINATION.childrenController}${PAGINATION.grandController}${PAGINATION.GO_TO}/1"/>
    </div>
</div><!-- end panel-->