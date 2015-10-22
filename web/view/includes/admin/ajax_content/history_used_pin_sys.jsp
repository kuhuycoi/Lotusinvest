<%@page import="java.net.URLEncoder"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="/WEB-INF/tlds/functions.tld" %>
<c:set var="PAGINATION" value="${sessionScope['USED_PIN_SYS_PAGINATION']}"/>
<c:if test="${f:size(PAGINATION.displayList)==0}">
    <div class="alert alert-danger">
        Không có kết quả nào được hiển thị
    </div>
</c:if>
<c:if test="${f:size(PAGINATION.displayList)!=0}">
    <table class="table table-condensed table-hover table-valign-midle table-grid-view table-align-center">
        <thead>
            <tr controller="<c:url value='${PAGINATION.ROOT_CONTROLLER}${PAGINATION.childrenController}${PAGINATION.grandController}${PAGINATION.ORDER_DATA}/'/>">
                <th class="th-id" column="id">ID <span class="${PAGINATION.orderColmn=='id'?(PAGINATION.asc?'fa fa-caret-up':'fa fa-caret-down'):''}"></span></th>
                <th class="col-md-1" column="usedCustomer">NPP <span class="${PAGINATION.orderColmn=='usedCustomer'?(PAGINATION.asc?'fa fa-caret-up':'fa fa-caret-down'):''}"></span></th>
                <th class="col-md-2" column="usedDate">Ngày nạp <span class="${PAGINATION.orderColmn=='usedDate'?(PAGINATION.asc?'fa fa-caret-up':'fa fa-caret-down'):''}"></span></th>
                <th class="col-md-2" column="pinNumber">Mã PIN <span class="${PAGINATION.orderColmn=='pinNumber'?(PAGINATION.asc?'fa fa-caret-up':'fa fa-caret-down'):''}"></span></th>
                <th class="col-md-2" column="pinType">Loại PIN <span class="${PAGINATION.orderColmn=='pinType'?(PAGINATION.asc?'fa fa-caret-up':'fa fa-caret-down'):''}"></span></th>
                <th class="col-md-2" column="pricePv">Mệnh giá <span class="${PAGINATION.orderColmn=='pricePv'?(PAGINATION.asc?'fa fa-caret-up':'fa fa-caret-down'):''}"></span></th>
                <th class="col-md-1" column="createdAdm">Người tạo <span class="${PAGINATION.orderColmn=='createdAdm'?(PAGINATION.asc?'fa fa-caret-up':'fa fa-caret-down'):''}"></span></th>
                <th class="col-md-2" column="createdDate">Ngày tạo <span class="${PAGINATION.orderColmn=='createdDate'?(PAGINATION.asc?'fa fa-caret-up':'fa fa-caret-down'):''}"></span></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${PAGINATION.displayList}" var="p">
                <tr>
                    <td>${p.id}</td>
                    <td>${p.usedCustomer}</td>
                    <td>${f:formatTime(p.usedDate)}</td>
                    <td>${p.pinNumber}</td>
                    <td>${p.pinType}</td>
                    <td>${f:formatCurrency(p.pricePv)}</td>
                    <td>${p.createdAdm}</td>
                    <td>${f:formatTime(p.createdDate)}</td>
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