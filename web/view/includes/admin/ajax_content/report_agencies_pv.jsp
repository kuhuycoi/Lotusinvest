<%@page import="java.net.URLEncoder"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="/WEB-INF/tlds/functions.tld" %>
<table class="table table-condensed table-bordered table-hover table-valign-midle table-grid-view">
    <thead>
        <tr controller="<c:url value='${PAGINATION.ROOT_CONTROLLER}${PAGINATION.childrenController}${PAGINATION.grandController}${PAGINATION.ORDER_DATA}/'/>" class="alert-success">
            <th class="th-checkbox external"><input class="select-all" type="checkbox" /></th>
            <th class="th-id" column="id">Mã đại lý <span class="${PAGINATION.orderColmn=='id'?(PAGINATION.asc?'fa fa-caret-up':'fa fa-caret-down'):''}"></span></th>
            <th class="col-md-2" column="lastName">Tên đại lý <span class="${PAGINATION.orderColmn=='lastName'?(PAGINATION.asc?'fa fa-caret-up':'fa fa-caret-down'):''}"></span></th>
            <th class="col-md-1" column="userName">Tổng PV <span class="${PAGINATION.orderColmn=='userName'?(PAGINATION.asc?'fa fa-caret-up':'fa fa-caret-down'):''}"></span></th>
            <th class="" column="customerName">Tháng <span class="${PAGINATION.orderColmn=='customerName'?(PAGINATION.asc?'fa fa-caret-up':'fa fa-caret-down'):''}"></span></th>
            <th class="" column="customerName">Năm <span class="${PAGINATION.orderColmn=='customerName'?(PAGINATION.asc?'fa fa-caret-up':'fa fa-caret-down'):''}"></span></th>            
            <th class="th-action external"></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${PAGINATION.displayList}" var="customerRank">
            <tr>
                <td class="text-center"><input type="checkbox" /></td>
                <td class="td-align-center">${customerRank.id}</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td>
                    <div class="btn-group btn-group-sm">
                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">Tương tác <span class="caret"></span></button>
                        <ul class="dropdown-menu dropdown-menu-right dropdown-menu-action" role="menu">
                            <li><a class="" controller="<c:url value='${PAGINATION.ROOT_CONTROLLER}${PAGINATION.childrenController}/Active/${customer.id}'/>">Kích hoạt</a></li>
                            <li><a class="external" controller="#">Ẩn</a></li>
                            <li><a class="external" controller="#">Hiện</a></li>
                            <li class="divider external"></li>
                            <li><a class="external" controller="#">Xóa</a></li>
                        </ul>
                    </div>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table><!-- end table -->


<c:if test="${PAGINATION.totalResult>0}">
    <div class="row">
        <div class="col-md-4">
            <c:if test="${PAGINATION.totalResult>5}">
                <select class="form-control input-sm display-per-page" controller="<c:url value='${PAGINATION.ROOT_CONTROLLER}${PAGINATION.childrenController}${PAGINATION.grandController}${PAGINATION.DISPLAY_PER_PAGE}/'/>">
                    <c:forEach begin="5" step="5" end="50" var="numb">
                        <option ${numb==PAGINATION.displayPerPage?'selected':''}>${numb}</option>
                    </c:forEach>
                </select>
                <label class="control-label">/ <i>${PAGINATION.totalResult}</i> Kết quả</label>
            </c:if>
        </div>
        <div class="col-md-4 text-center">                    
            <c:if test="${PAGINATION.totalResult>PAGINATION.displayPerPage}">
                <form class="pagination" action="<c:url value='${PAGINATION.ROOT_CONTROLLER}${PAGINATION.childrenController}${PAGINATION.grandController}${PAGINATION.GO_TO}/'/>">
                    <div class="input-group input-group-sm">
                        <div class="input-group-btn">
                            <button type="button" page="1" class="btn btn-default first ${PAGINATION.currentPage==1?'disabled':''}"><span aria-hidden="true" class="glyphicon glyphicon-step-backward"></span></button>
                            <button type="button" page="${PAGINATION.currentPage-1}" class="btn btn-default prev ${PAGINATION.currentPage==1?'disabled':''}"><span aria-hidden="true" class="glyphicon glyphicon-play"></span></button>
                        </div>
                        <input type="number" class="form-control" value="${PAGINATION.currentPage}" max="${PAGINATION.totalPage}" min="1" />
                        <span class="input-group-addon">/<span>${PAGINATION.totalPage}</span></span> 

                        <div class="input-group-btn">
                            <button type="button" page="${PAGINATION.currentPage+1}" class="btn btn-default next ${PAGINATION.currentPage==PAGINATION.totalPage?'disabled':''}"><span aria-hidden="true" class="glyphicon glyphicon-play"></span></button>
                            <button type="button" page="${PAGINATION.totalPage}" class="btn btn-default last ${PAGINATION.currentPage==PAGINATION.totalPage?'disabled':''}"><span aria-hidden="true" class="glyphicon glyphicon-step-forward"></span></button>
                        </div>
                    </div>
                </form>
            </c:if>
        </div>
        <div class="col-md-4 text-right">
            <label>Đã chọn: </label>
            <button type="button" class="btn btn-default btn-sm" href="">Xóa</button>&nbsp;
            <c:if test="${PAGINATION.exportable}">
                <button type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown">Xuất ra <span class="caret"></span></button>
                <ul class="dropdown-menu dropdown-menu-right">
                    <li><a href="#">Excel</a></li>
                    <li><a href="#">Text</a></li>
                </ul>
            </c:if>
        </div>
    </div>
</c:if>