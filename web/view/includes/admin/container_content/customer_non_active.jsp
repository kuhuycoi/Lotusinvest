<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="/WEB-INF/tlds/functions.tld" %>
<c:set var="PAGINATION" value="${sessionScope['NON_ACTIVE_PAGINATION']}"/>
<input type="hidden" id="reloadController" value="<c:url value='${PAGINATION.ROOT_CONTROLLER}${PAGINATION.childrenController}${PAGINATION.grandController}'/>">
<div class="content-title">
    <h3 class="text-center">${PAGINATION.viewTitle}</h3>
</div>
<div class="panel panel-default">
    <div class="panel-heading">
        <nav class="navbar navbar-default" role="navigation">
            <div class="container-fluid">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>                       
                    <a type="button" class="navbar-brand btn-reload-content" data-toggle="tooltip" data-placement="bottom" data-title="Tải lại nội dung hiển thị">
                        <i class="fa fa-refresh"></i>
                    </a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <form class="form-search navbar-form navbar-left" novalidate method="post" action='<c:url value="${PAGINATION.ROOT_CONTROLLER}${PAGINATION.childrenController}${PAGINATION.grandController}${PAGINATION.SEARCH}"/>'>
                        <div class="input-group">
                            <div class="input-group-btn" data-toggle="tooltip" data-placement="left" data-title="Lựa chọn các trường tìm kiếm">
                                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" tabindex="-1">
                                    <span class="caret"></span>
                                    <span class="sr-only">Toggle Dropdown</span>
                                </button>
                                <ul class="dropdown-menu dropdown-select dropdown-menu-left">
                                    <li><a><label><input type="checkbox" value="this_.id" />ID</label></a></li>
                                    <li><a><label><input type="checkbox" value="this_.firstName" />Họ Khách Hàng</label></a></li>
                                    <li><a><label><input type="checkbox" value="this_.lastName" />Tên Khách Hàng</label></a></li>
                                    <li><a><label><input type="checkbox" value="this_.userName"  />Tên Đăng Nhập</label></a></li>
                                    <li><a><label><input type="checkbox" value="this_.title"  />Tên Đăng Nhập Nhanh</label></a></li>
                                    <li class="divider"></li>
                                    <li><a><label><input class="select-all" type="checkbox" />Tất Cả</label></a></li>
                                </ul>
                            </div>
                            <input type="text" class="form-control" name="searchString" placeholder="Nhập từ khóa ..." />
                            <div class="input-group-btn">
                                <button type="submit" class="btn btn-default">                                
                                    <i class="fa fa-search"></i>
                                </button>
                            </div>
                        </div>
                    </form>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a class="btn-open-modal btn-sm btn btn-default" controller="<c:url value='${PAGINATION.ROOT_CONTROLLER}${PAGINATION.childrenController}${PAGINATION.grandController}${PAGINATION.VIEW_INSERT}'/>">Thêm mới <i class="fa fa-user-plus"></i></a></li>
                    </ul>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>
    </div><!-- end panel heading -->
    <div class="ajax-content panel-body">    
        <c:import url="${PAGINATION.ROOT_CONTROLLER}${PAGINATION.childrenController}${PAGINATION.grandController}${PAGINATION.GO_TO}/1"/>
    </div>
</div><!-- end panel-->