<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="/WEB-INF/tlds/functions.tld" %>
<c:set value="${f:findDistributorById(sessionScope['CUSTOMER_ID'])}" var="CUSTOMER" />
<h1>Thông tin hệ thống</h1>
<div class="panel">
    <div class="panel-heading">
        <h2 class="login-title">Thay đổi mật khẩu</h2>
    </div>
    <div class="panel-body">
        <form class="form-insert" novalidate method="post" action='<c:url value="/Customer/ChangePassword"/>'>
            <div class="row">
                <div class="col-md-3">
                    <label>Mật khẩu cũ</label>
                </div>
                <div class="col-md-9">
                    <input type="password" required pattern="^[\w\d]+$" data-original-title-pattern="Vui lòng các ký tự A-Z, a-z, 0-9" name="oldPassword" class="form-control">
                </div>
            </div> 
            <div class="row">
                <div class="col-md-3">
                    <label>Mật khẩu mới</label>
                </div>
                <div class="col-md-9">
                    <input type="password" required pattern="^[\w\d]+$" id="newPassword" data-original-title-pattern="Vui lòng các ký tự A-Z, a-z, 0-9" name="newPassword" class="form-control">
                    <p class="help-block"><i>Chỉ chấp nhận các ký tự bảng chữ cái và ký tự số.</i></p>
                </div>
            </div> 
            <div class="row">
                <div class="col-md-3">
                    <label>Nhập lại mật khẩu</label>
                </div>
                <div class="col-md-9">
                    <input class="form-control" required type="password" match="#newPassword">
                    <p class="help-block"><i>Yêu cầu khớp với mật khẩu mới.</i></p>
                </div>
            </div> 
            <div class="buttonBar">
                <button type="submit" class="btn btn-default">
                    Cập nhật mật khẩu
                </button>
                <button type="reset" class="btn btn-default">
                    Nhập lại
                </button>
            </div>
        </form>
    </div>
</div>