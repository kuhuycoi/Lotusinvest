<%@page import="java.net.URLEncoder"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="/WEB-INF/tlds/functions.tld" %>
<c:set var="PAGINATION" value="${sessionScope['INDEX_CUSTOMER_FOR_CUSTOMER_PAGINATION']}"/>
<c:if test="${f:size(PAGINATION.displayList)==0}">
    <div class="alert alert-danger">
        Không có kết quả nào được hiển thị
    </div>
</c:if>
<c:if test="${f:size(PAGINATION.displayList)!=0}">
    <table class="table table-condensed table-bordered table-hover table-valign-midle table-grid-view table-align-center">
        <thead>
            <tr controller="<c:url value='${PAGINATION.ROOT_CONTROLLER}${PAGINATION.childrenController}${PAGINATION.grandController}${PAGINATION.ORDER_DATA}/'/>">
                <th class="th-id" column="id">ID <span class="${PAGINATION.orderColmn=='id'?(PAGINATION.asc?'fa fa-caret-up':'fa fa-caret-down'):''}"></span></th>
                <th class="col-md-2" column="lastName">Tên NPP <span class="${PAGINATION.orderColmn=='lastName'?(PAGINATION.asc?'fa fa-caret-up':'fa fa-caret-down'):''}"></span></th>
                <th class="col-md-2" column="userName">Username NPP <span class="${PAGINATION.orderColmn=='userName'?(PAGINATION.asc?'fa fa-caret-up':'fa fa-caret-down'):''}"></span></th>
                <th class="col-md-2" column="email">Email <span class="${PAGINATION.orderColmn=='email'?(PAGINATION.asc?'fa fa-caret-up':'fa fa-caret-down'):''}"></span></th>
                <th class="col-md-3" column="createdOnUtc">Ngày tạo <span class="${PAGINATION.orderColmn=='createdOnUtc'?(PAGINATION.asc?'fa fa-caret-up':'fa fa-caret-down'):''}"></span></th>
                <th class="col-md-3" column="isActive">Trạng thái <span class="${PAGINATION.orderColmn=='isActive'?(PAGINATION.asc?'fa fa-caret-up':'fa fa-caret-down'):''}"></span></th>
                <th class="external"></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${PAGINATION.displayList}" var="cus">
                <tr class='${cus.isActive?"":"danger"}'>
                    <td>${cus.id}</td>
                    <td>${cus.firstName} ${cus.lastName}</td>
                    <td>${cus.userName}</td>
                    <td>${cus.email}</td>
                    <td>${f:formatTime(cus.createdOnUtc)}</td>
                    <td>${cus.isActive?'Đã kích hoạt':'Chưa kích hoạt'}</td>
                    <td>
                        <button class="btn btn-sm ${!cus.isActive?'btn-send-ajax btn-success':'btn-warning'}" ${cus.isActive?'disabled':''} confirm='true' controller='<c:url value="/Customer/ActiveCustomer/${cus.id}"/>' data-toggle="tooltip" data-placement="bottom" data-title="Kích hoạt tài khoản này.">
                            <i class="ti-check"></i>
                        </button>
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
        </div>
    </c:if>
    <div class="buttonBar"></div>
</c:if>