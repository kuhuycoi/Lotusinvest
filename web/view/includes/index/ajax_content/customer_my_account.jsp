<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="/WEB-INF/tlds/functions.tld" %>
<c:set value="${f:findDistributorById(sessionScope['CUSTOMER_ID'])}" var="CUSTOMER" />
<h1>Thông tin hệ thống</h1>
<div class="panel">
    <div class="panel-heading">
        <h2 class="login-title">Thông tin tài khoản</h2>
    </div>
    <div class="panel-body">
        <p>Thông tin cố định:</p>
        <div class="row">
            <div class="col-md-2">
                <label>ID</label>
            </div>
            <div class="col-md-4">
                <span>${CUSTOMER.id}</span>
            </div>
            <div class="col-md-3">
                <label>Họ tên</label>
            </div>
            <div class="col-md-3">
                <span>${CUSTOMER.firstName} ${CUSTOMER.lastName}</span>
            </div>
        </div> 
        <div class="row">
            <div class="col-md-2">
                <label>Tên đăng nhập</label>
            </div>
            <div class="col-md-4">
                <span>${CUSTOMER.userName}</span>
            </div>
            <div class="col-md-3">
                <label>Tên đăng nhập nhanh</label>
            </div>
            <div class="col-md-3">
                <span>${CUSTOMER.title}</span>
            </div>
        </div> 
        <div class="row">
            <div class="col-md-2">
                <label>Người chỉ định</label>
            </div>
            <div class="col-md-4">
                <span>${CUSTOMER.parentName}</span>
            </div>
            <div class="col-md-3">
                <label>Người giới thiệu</label>
            </div>
            <div class="col-md-3">
                <span>${CUSTOMER.customerName}</span>
            </div>
        </div> 
        <div class="row">
            <div class="col-md-2">
                <label>Đại lý</label>
            </div>
            <div class="col-md-4">
                <span>${CUSTOMER.provinceAgencyName}</span>
            </div>
            <div class="col-md-3">
                <label>Ngày tham gia</label>
            </div>
            <div class="col-md-3">
                <span>${f:formatTime(CUSTOMER.createdOnUtc)}</span>
            </div>
        </div> 
        <div class="buttonBar"></div>
        <p>Thông tin có thể thay đổi:</p>
        <form class="form-insert" novalidate method="post" action="<c:url value='/Customer/Edit'/>">
            <div class="row">
                <div class="col-md-3">
                    <label>Giới tính</label>
                </div>
                <div class="col-md-9">
                    <label class="checkbox-inline"><input type="radio" name="gender" value="true" ${CUSTOMER.gender?'checked':''}>Nam</label>
                    <label class="checkbox-inline"><input type="radio" name="gender" value="false" ${!CUSTOMER.gender?'checked':''}>Nữ</label>
                </div>
            </div> 
            <div class="row">
                <div class="col-md-3">
                    <label>Số CMND</label>
                </div>
                <div class="col-md-9">
                    <input type="text" name="peoplesIdentity" pattern="^[\d]+$" value="${CUSTOMER.peoplesIdentity}" data-original-title-pattern="Vui lòng nhập các ký tự số" class="form-control">
                    <p class="help-block"><i>Chỉ chấp nhận các ký tư số.</i></p>
                </div>
            </div>  
            <div class="row">
                <div class="col-md-3">
                    <label>Email</label>
                </div>
                <div class="col-md-9">
                    <input type="text" name="email" value="${CUSTOMER.email}" pattern="^[\w\d_-]+@[\w\d-_]+(.[\w\d-_]+)+$" data-original-title-pattern="Định dạng email không hợp lệ" class="form-control">
                    <p class="help-block"><i>Sử dụng để lấy lại thông tin đăng nhập.</i></p>
                </div>
            </div> 
            <div class="row">
                <div class="col-md-3">
                    <label>Số điện thoại</label>
                </div>
                <div class="col-md-9">
                    <input type="text" name="mobile" pattern="^0|\+[\d]+[\d]+$" value="${CUSTOMER.mobile}" data-original-title-pattern="Định dạng số điện thoại không hợp lệ" class="form-control">
                </div>
            </div> 
            <div class="row">
                <div class="col-md-3">
                    <label>Địa chỉ ngân hàng</label>
                </div>
                <div class="col-md-9">
                    <input class="form-control" type="text" name="billingAddress" value="${CUSTOMER.billingAddress}">
                </div>
            </div> 
            <div class="row">
                <div class="col-md-3">
                    <label>Số tài khoản</label>
                </div>
                <div class="col-md-9">
                    <input class="form-control" type="text" name="taxCode" value="${CUSTOMER.taxCode}">
                </div>
            </div> 
            <div class="row">
                <div class="col-md-3">
                    <label>Loại khách hàng</label>
                </div>
                <div class="col-md-9">
                    <select name="customerTypeId" class="form-control">
                        <option value="">-- Để trống --</option>
                        <c:forEach items="${f:findAllAvailableCustomerType()}" var="customerType">
                            <option value='${customerType.id}' ${CUSTOMER.customerTypeId==customerType.id?'selected':''}>${customerType.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div> 
            <div class="row">
                <div class="col-md-3">
                    <label>Địa chỉ liên hệ</label>
                </div>
                <div class="col-md-9">
                    <textarea class="form-control" name="address" rows="4"></textarea>
                </div>
            </div> 
            <div class="buttonBar">
                <button type="submit" class="btn btn-default">
                    Cập nhật thông tin
                </button>
                <button type="reset" class="btn btn-default">
                    Nhập lại
                </button>
            </div>
        </form>
    </div>
</div>