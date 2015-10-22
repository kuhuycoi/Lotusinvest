<%@page import="java.net.URLEncoder"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="/WEB-INF/tlds/functions.tld" %>
<c:set var="PAGINATION" value="${sessionScope['LIST_ADMIN_PAGINATION']}"/>
<c:if test="${f:size(PAGINATION.displayList)==0}">
    <div class="alert alert-danger">
        Không có kết quả nào được hiển thị
    </div>
</c:if>
<c:if test="${f:size(PAGINATION.displayList)!=0}">
    <table class="table table-condensed table-hover table-valign-midle table-grid-view table-align-center">
        <thead>
            <tr controller="<c:url value='${PAGINATION.ROOT_CONTROLLER}${PAGINATION.childrenController}${PAGINATION.grandController}${PAGINATION.ORDER_DATA}/'/>">
                <th class="th-checkbox external"><input class="select-all" type="checkbox" /></th>
                <th class="th-id" column="id">ID <span class="${PAGINATION.orderColmn=='id'?(PAGINATION.asc?'fa fa-caret-up':'fa fa-caret-down'):''}"></span></th>
                <th class="col-md-2" column="lastName">Tên <span class="${PAGINATION.orderColmn=='lastName'?(PAGINATION.asc?'fa fa-caret-up':'fa fa-caret-down'):''}"></span></th>
                <th class="col-md-2" column="userName">Username <span class="${PAGINATION.orderColmn=='userName'?(PAGINATION.asc?'fa fa-caret-up':'fa fa-caret-down'):''}"></span></th>
                <th class="col-md-2" column="mobile">Số ĐT <span class="${PAGINATION.orderColmn=='mobile'?(PAGINATION.asc?'fa fa-caret-up':'fa fa-caret-down'):''}"></span></th>
                <th class="col-md-2" column="email">Email <span class="${PAGINATION.orderColmn=='email'?(PAGINATION.asc?'fa fa-caret-up':'fa fa-caret-down'):''}"></span></th>
                <th class="col-md-2" column="createdOnUtc">Thời gian tạo <span class="${PAGINATION.orderColmn=='createdOnUtc'?(PAGINATION.asc?'fa fa-caret-up':'fa fa-caret-down'):''}"></span></th>
                <th class="col-md-2" column="isActive">Trạng thái <span class="${PAGINATION.orderColmn=='isActive'?(PAGINATION.asc?'fa fa-caret-up':'fa fa-caret-down'):''}"></span></th>
                <th class="th-action external">   
                    <div class="btn-group btn-group-sm">             
                        <a type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"><i class="ti-settings"></i></a>
                        <ul class="dropdown-menu dropdown-menu-right dropdown-menu-action" role="menu">
                            <li><a class="external">Sửa</a></li>
                            <li><a controller="<c:url value='/Admin/Block/${admin.id}'/>">Khóa</a></li>
                            <li class="divider external"></li>
                            <li><a controller="<c:url value='/Admin/Delete/${admin.id}'/>">Xóa</a></li>
                        </ul>
                    </div>
                </th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${PAGINATION.displayList}" var="admin">
                <tr class="${admin.isActive?'':'alert alert-danger'}">
                    <td class="text-center"><input type="checkbox" /></td>
                    <td class="td-align-center">${admin.id}</td>
                    <td>${admin.firstName} ${admin.lastName}</td>
                    <td>${admin.userName}</td>
                    <td>${admin.mobile}</td>
                    <td>${admin.email}</td>
                    <td>${f:formatTime(admin.createdOnUtc)}</td>
                    <td>${admin.isActive?'Không khóa':'Khóa'}</td>
                    <td>
                        <div class="btn-group btn-group-sm">
                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"><i class="ti-settings"></i></button>
                            <ul class="dropdown-menu dropdown-menu-right dropdown-menu-action" role="menu">
                                <li><a class="external">Sửa</a></li>
                                <li><a controller="<c:url value='/Admin/Block/${admin.id}/${!admin.isActive}'/>">${admin.isActive?'Khóa':'Mở khóa'}</a></li>
                                <li class="divider external"></li>
                                <li><a controller="<c:url value='/Admin/Delete/${admin.id}'/>">Xóa</a></li>
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
        </div>
    </c:if>
</c:if>