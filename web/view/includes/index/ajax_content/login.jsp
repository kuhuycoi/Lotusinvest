<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="/WEB-INF/tlds/functions.tld" %>
<h1>Tài khoản</h1>
<div class="panel">
    <div class="panel-heading">
        <h2 class="login-title">Đăng nhập</h2>
    </div>
    <div class="panel-body">
        <p>Nếu bạn đã có tài khoản, vui lòng đăng nhập bằng form dưới đây:</p>
        <div class="row">
            <form class="form-insert" method="post" novalidate action="<c:url value='/Customer/Login'/>">
                <div class="col-md-4">
                    <input type="text" class="form-control" name="userName" required placeholder="Tên đăng nhập">
                </div>
                <div class="col-md-4">
                    <input type="password" class="form-control" name="password" required placeholder="•••••••••••">
                </div>
                <div class="col-md-4">
                    <button class="btn-default btn" type="submit">Đăng nhập</button>
                </div>
            </form>     
        </div>   
        <div class="row">
            <div class=" col-md-4">
                <a class="btn-change-content" controller="<c:url value='/Customer/Register'/>">Chưa có tài khoản?</a>
            </div>
            <div class=" col-md-4">
                <a class="btn-change-content" controller="<c:url value='/Customer/ResetPassword'/>">Quên mật khẩu?</a>
            </div>
        </div>
    </div>
</div>
