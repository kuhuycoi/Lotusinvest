<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="/WEB-INF/tlds/functions.tld" %>
<c:set var="PAGINATION" value="${sessionScope['INDEX_CUSTOMER_RANK_CUSTOMER_PAGINATION']}"/>
<h1>Thống kê hoa hồng</h1>
<div class="panel">
    <div class="panel-heading">
        <h2>Lịch sử nạp PV</h2>
    </div><!-- end panel heading -->
    <div class="ajax-content panel-body">
        <c:import url="${PAGINATION.ROOT_CONTROLLER}${PAGINATION.childrenController}${PAGINATION.grandController}${PAGINATION.GO_TO}/1"/>
    </div>
</div><!-- end panel-->