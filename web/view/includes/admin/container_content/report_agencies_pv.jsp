<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="/WEB-INF/tlds/functions.tld" %>
<div class="content-title">
    <h3 class="text-center">${PAGINATION.viewTitle}</h3>
</div>
<div class="panel panel-default">
    <div class="panel-heading">
        <div class="row">
            <div class="col-md-6">
                <div class="input-group input-group-sm send-back">
                    <label class="input-group-addon">Tìm kiếm</label>
                    <input type="text" class="form-control" placeholder="Nhập từ khóa ..." />
                    <div class="input-group-btn">
                        <button type="button" class="btn btn-default" tabindex="-1"><i class="glyphicon glyphicon-search"></i></button>
                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" tabindex="-1">
                            <span class="caret"></span>
                            <span class="sr-only">Toggle Dropdown</span>
                        </button>
                        <ul class="dropdown-menu dropdown-select dropdown-menu-right">
                            <li><a><label><input type="checkbox" />ID</label></a></li>
                            <li><a><label><input type="checkbox" />Tên Khách Hàng</label></a></li>
                            <li><a><label><input type="checkbox" />Tên Đăng Nhập</label></a></li>
                            <li class="divider"></li>
                            <li><a><label><input class="select-all" type="checkbox" />Tất Cả</label></a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-md-6 text-right">
                <div class="btn-group btn-group-sm">
                    <c:if test="${PAGINATION.insertable}">                        
                        <button type="button" class="btn btn-success btn-open-modal" controller="<c:url value='${PAGINATION.ROOT_CONTROLLER}${PAGINATION.childrenController}${PAGINATION.VIEW_INSERT}'/>">Thêm mới</button>
                    </c:if>
                    <c:if test="${PAGINATION.exportable}">
                        <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown">Xuất ra <span class="caret"></span></button>
                        <ul class="dropdown-menu dropdown-menu-right">
                            <li><a href="#">Excel</a></li>
                            <li><a href="#">Text</a></li>
                        </ul>
                    </c:if>
                </div>
            </div>
        </div>
    </div><!-- end panel heading -->
    <div class="ajax-content panel-body">
        <c:import url="${PAGINATION.ROOT_CONTROLLER}${PAGINATION.childrenController}${PAGINATION.grandController}${PAGINATION.GO_TO}/1"/>
    </div>
</div><!-- end panel-->