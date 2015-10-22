<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="/WEB-INF/tlds/functions.tld" %>
<h1>Tài khoản</h1>
<div class="panel">
    <div class="panel-heading">
        <h2 class="login-title">Lấy lại mật khẩu</h2>
    </div>
    <div class="panel-body">
        <p>Mật khẩu sẽ được tự động khởi tạo lại và gửi về hòm thư của bạn</p>
        <div class="row">
            <form class="form-insert" method="post" novalidate action="<c:url value='/Customer/ResetPassword'/>">
                <div class="col-md-4">
                    <input type="text" class="form-control" name="userName" required placeholder="Tên đăng nhập hoặc tên đăng nhập nhanh">
                </div>
                <div class="col-md-4">                    
                    <input type="text" name="email" required pattern="^[\w\d_-]+@[\w\d-_]+(.[\w\d-_]+)+$" data-original-title-pattern="Định dạng email không hợp lệ" class="form-control" placeholder="Email đã dăng ký">
                </div>
                <div class="col-md-4">
                    <button class="btn-default btn" type="submit">Reset mật khẩu</button>
                </div>
            </form>     
        </div>   
        <div class="row">
            <div class=" col-md-12">
                <p class="help-block"><i>Chỉ áp dụng cho các tài khoản đã đăng ký email.</i></p>
            </div>
        </div>
    </div>
</div>
