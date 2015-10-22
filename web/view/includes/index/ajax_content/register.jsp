<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="/WEB-INF/tlds/functions.tld" %>
<h1>Tài khoản</h1>
<div class="panel">
    <div class="panel-heading">
        <h2>Đăng ký</h2>
    </div>
    <div class="panel-body">
        <form class="form-insert" method="post" novalidate action="<c:url value='/Customer/Register'/>">
            <p>Thông tin bắt buộc:</p>
            <div class="row">
                <div class="col-md-2">
                    <label>Họ *</label>
                </div>
                <div class="col-md-4">
                    <input type="text" required name="firstName" class="form-control">
                </div>
                <div class="col-md-1">
                    <label>Tên *</label>
                </div>
                <div class="col-md-4">
                    <input type="text" required name="lastName" class="form-control">
                </div>   
            </div> 
            <div class="row">
                <div class="col-md-2">
                    <label>Tên đăng nhập nhanh *</label>
                </div>
                <div class="col-md-10">
                    <input type="text" required pattern="^[\w\d]{3,100}$" data-original-title-pattern="Vui lòng nhập các ký tự A-Z, a-z, 0-9" name="title" class="form-control">
                    <p class="help-block"><i>Chỉ chấp nhận các ký tự trong bảng chữ cái và ký tư số.</i></p>
                </div>
            </div> 
            <div class="row">
                <div class="col-md-2">
                    <label>Mật khẩu *</label>
                </div>
                <div class="col-md-10">
                    <input type="password" required pattern="^[\w\d]+$" data-original-title-pattern="Vui lòng nhập các ký tự A-Z, a-z, 0-9" name="password" class="form-control">
                    <p class="help-block"><i>Chỉ chấp nhận các ký tự bảng chữ cái và ký tư số.</i></p>
                </div>
            </div> 
            <div class="row">
                <div class="col-md-2">
                    <label>Người chỉ định *</label>
                </div>
                <div class="col-sm-10 block-auto-complete">
                    <div class="auto-complete-container">
                        <input type="text" required controller='<c:url value="/Customer/SearchParentId/"/>' pattern="^[\w\d]+$" data-original-title-pattern="Vui lòng nhập các ký tự A-Z, a-z, 0-9" name="parentName" id="parentName" class="form-control" placeholder="Nhập ít nhất 2 ký tự để xuất hiện gợi ý">
                        <div class="panel panel-auto-complete">
                            <div class="panel-body">
                            </div>
                        </div>
                    </div>
                </div>
            </div>  
            <div class="row">
                <div class="col-md-2">
                    <label>Người giới thiệu *</label>
                </div>
                <div class="col-sm-10 block-auto-complete">
                    <div class="auto-complete-container">
                        <input type="text" required controller='<c:url value="/Customer/SearchCustomerId/"/>' data-parent="#parentName" id="customerName" name="customerName" class="form-control" required pattern="^[\w\d]+$" data-original-title-pattern="Vui lòng nhập các ký tự A-Z, a-z, 0-9" placeholder="Nhập ít nhất 2 ký tự để xuất hiện gợi ý">
                        <p class="help-block"><i>Người giới thiêu và người chỉ định phải trong cùng một hệ thống.</i></p>
                        <div class="panel panel-auto-complete">
                            <div class="panel-body">
                            </div>
                        </div>
                    </div>
                </div>
            </div> 
            <p>Thông tin bổ sung:</p> 
            <div class="row">
                <div class="col-md-2">
                    <label>Đại lý</label>
                </div>
                <div class="col-md-10">                   
                    <select name="provinceAgencyId" class="form-control">
                        <c:forEach items="${f:findAllAvailableProvincialAgencies()}" var="provincialAgency">
                            <option value='${provincialAgency.id}'>${provincialAgency.name}</option>
                        </c:forEach>
                    </select>
                    <p class="help-block text-danger"><i>Thông tin này không thay đổi được sau khi đã đăng ký.</i></p>
                </div>
            </div>  
            <div class="row">
                <div class="col-md-2">
                    <label>Giới tính</label>
                </div>
                <div class="col-md-10">
                    <label class="checkbox-inline"><input type="radio" name="gender" value="true">Nam</label>
                    <label class="checkbox-inline"><input type="radio" name="gender" value="false">Nữ</label>
                </div>
            </div> 
            <div class="row">
                <div class="col-md-2">
                    <label>Số CMND</label>
                </div>
                <div class="col-md-10">
                    <input type="text" name="peoplesIdentity" pattern="^[\d]+$" data-original-title-pattern="Vui lòng nhập các ký tự số" class="form-control">
                    <p class="help-block"><i>Chỉ chấp nhận các ký tư số.</i></p>
                </div>
            </div> 
            <div class="row">
                <div class="col-md-2">
                    <label>Email</label>
                </div>
                <div class="col-md-10">
                    <input type="text" name="email" pattern="^[\w\d_-]+@[\w\d-_]+(.[\w\d-_]+)+$" data-original-title-pattern="Định dạng email không hợp lệ" class="form-control">
                    <p class="help-block"><i>Sử dụng để lấy lại thông tin đăng nhập.</i></p>
                </div>
            </div> 
            <div class="row">
                <div class="col-md-2">
                    <label>Số điện thoại</label>
                </div>
                <div class="col-md-10">
                    <input type="text" name="mobile" pattern="^0|\+[\d]+[\d]+$" data-original-title-pattern="Định dạng số điện thoại không hợp lệ" class="form-control">
                </div>
            </div> 
            <div class="row">
                <div class="col-md-2">
                    <label>Địa chỉ liên hệ</label>
                </div>
                <div class="col-md-10">
                    <textarea class="form-control" name="address" rows="4"></textarea>
                </div>
            </div> 
            <div class="row">
                <div class="col-md-2">
                    <label>Địa chỉ ngân hàng</label>
                </div>
                <div class="col-md-10">
                    <input type="text" name="billingAddress" class="form-control">
                </div>
            </div> 
            <div class="row">
                <div class="col-md-2">
                    <label>Số tài khoản</label>
                </div>
                <div class="col-md-10">
                    <input type="text" name="taxCode" class="form-control">
                </div>
            </div> 
            <div class="row">
                <div class="col-md-2">
                    <label>Loại khách hàng</label>
                </div>
                <div class="col-md-10">
                    <select name="customerTypeId" class="form-control">
                        <option value="">-- Để trống --</option>
                        <c:forEach items="${f:findAllAvailableCustomerType()}" var="customerType">
                            <option value='${customerType.id}'>${customerType.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div> 
            <div class="buttonBar">
                <button type="submit" class="btn btn-default">
                    Đăng ký
                </button>
                <button type="reset" class="btn btn-default">
                    Nhập lại
                </button>
            </div>
        </form>   
    </div>
</div>
