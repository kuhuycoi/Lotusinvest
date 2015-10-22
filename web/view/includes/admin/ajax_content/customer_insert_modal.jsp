<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="/WEB-INF/tlds/functions.tld" %>
<div class="modal modal-insert-customer" id="myModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <div class="modal-title">
                    <div class="content-title">
                        <h3 class="text-center">Thêm mới người dùng</h3>
                    </div>
                </div>
            </div>
            <form id="form-insert" class="form-insert form-horizontal" novalidate method="POST" action="<c:url value='/Admin/Customer/Distributor/Insert'/>">
                <div class="modal-body">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a href="customer_edit_modal.jsp"></a>
                                Thông tin yêu cầu <i>(vui lòng nhập tất cả)</i>
                            </h4>
                        </div>
                        <div class="panel-body">
                            <div class="form-group">
                                <label for="firstName" class="control-label col-sm-3">Họ</label>
                                <div class="col-sm-4">
                                    <input type="text" id="firstName" required name="firstName" class="form-control" placeholder="">
                                </div>
                                <label class="control-label col-sm-1">Tên</label>
                                <div class="col-sm-4">
                                    <input type="text" id="moduleName" required name="lastName" class="form-control" placeholder="">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-3">Tên đăng nhập nhanh</label>
                                <div class="col-sm-9">
                                    <input type="text" id="moduleController" required pattern="^[\w\d]{3,100}$" data-original-title-pattern="Chỉ chấp nhận các ký tự A-Z,a-z,0-9" name="title" class="form-control" placeholder="">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="parentName" class="control-label col-sm-3">Người chỉ định</label>
                                <div class="col-sm-9 block-auto-complete">
                                    <div class="auto-complete-container">
                                        <input type="text" required controller='<c:url value="/Admin/Customer/SearchParentId/"/>' required pattern="^[\d]+$" data-original-title-pattern="Vui lòng nhập các ký tự số" name="parentName" id="parentName" class="form-control" placeholder="Nhập ít nhất 2 ký tự để xuất hiện gợi ý">
                                        <div class="panel panel-auto-complete">
                                            <div class="panel-body">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="customerName" required class="control-label col-sm-3">Người giới thiệu</label>
                                <div class="col-sm-9 block-auto-complete">
                                    <div class="auto-complete-container">
                                        <input type="text" required controller='<c:url value="/Admin/Customer/SearchCustomerId/"/>' id="customerName" data-parent="#parentName" name="customerName" class="form-control" required pattern="^[\d]+$" data-original-title-pattern="Vui lòng nhập các ký tự số" placeholder="Nhập ít nhất 2 ký tự để xuất hiện gợi ý">
                                        <div class="panel panel-auto-complete">
                                            <div class="panel-body">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div><div class="panel panel-info">
                        <div class="panel-heading">
                            <h4 class="panel-title">                                
                                Thông tin bổ sung <i>(có thể bổ sung sau)</i>
                            </h4>
                        </div>
                        <div class="panel-body">
                            <div class="form-group">
                                <label class="control-label col-sm-3">Giới tính</label>
                                <div class="col-sm-9">
                                    <label class="checkbox-inline"><input type="radio" name="gender" value="true">Nam</label>
                                    <label class="checkbox-inline"><input type="radio" name="gender" value="false">Nữ</label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="peoplesIdentity" class="control-label col-sm-3">Số CMND</label>
                                <div class="col-sm-9">
                                    <input type="text" id="peoplesIdentity" name="peoplesIdentity" pattern="^[\d]+$" data-original-title-pattern="Chỉ chấp nhận các ký tự số" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-3">Loại khách hàng</label>
                                <div class="col-sm-9">
                                    <select class="form-control text-center" name="customerTypeId">
                                        <option value="">-- Để trống --</option>
                                        <c:forEach items="${f:findAllAvailableCustomerType()}" var="customerType">
                                            <option value='${customerType.id}'>${customerType.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-3">Email</label>
                                <div class="col-sm-9">
                                    <input type="email" id="moduleIcon" name="email" class="form-control" pattern="^[\w\d_-]+@[\w\d-_]+(.[\w\d-_]+)+$" data-original-title-pattern="Định dạng email không hợp lệ">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="mobile" class="control-label col-sm-3">Số điện thoại</label>
                                <div class="col-sm-9">
                                    <input type="text" id="mobile" name="mobile" class="form-control" pattern="^0|\+[\d]+[\d]+$" data-original-title-pattern="Định dạng số điện thoại không hợp lệ">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="taxCode" class="control-label col-sm-3">Số tài khoản</label>
                                <div class="col-sm-9">
                                    <input type="text" id="taxCode" name="taxCode" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="billingAddress" class="control-label col-sm-3">Địa chỉ ngân hàng</label>
                                <div class="col-sm-9">
                                    <input type="text" id="billingAddress" name="billingAddress" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-3">Đại lý</label>  
                                <div class="col-sm-9">                      
                                    <select class="form-control text-center" name="provinceAgencyId">
                                        <c:forEach items="${f:findAllAvailableProvincialAgencies()}" var="provincialAgency">
                                            <option value='${provincialAgency.id}'>${provincialAgency.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="address" class="control-label col-sm-3">Địa chỉ</label>
                                <div class="col-sm-9">
                                    <textarea name="address" id="address" rows="4" class="form-control"></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="isActive" class="control-label col-sm-3">Kích hoạt</label>  
                                <div class="col-sm-9">
                                    <label class="checkbox-inline"><input type="checkbox" id="isActive" name="isActive">Kích hoạt</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">Lưu</button>
                    <button type="reset" class="btn btn-danger">Nhập lai</button>
                </div>
            </form>
        </div>
    </div>
</div>
