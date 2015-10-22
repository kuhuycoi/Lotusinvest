<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="/WEB-INF/tlds/functions.tld" %>
<div class="modal modal-insert-customer" id="myModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <div class="modal-title">
                    <div class="content-title">
                        <h3 class="text-center">Cập nhật nhà phân phối</h3>
                    </div>
                </div>
            </div>
            <form id="form-insert" class="form-insert form-horizontal" novalidate method="POST" action="${pageContext.servletContext.contextPath}/Admin/Customer/Distributor/Edit">
                <div class="modal-body">
                    <input type="hidden" name="id" value="${CUSTOMER.id}">
                    <div class="form-group">
                        <label for="peoplesIdentity" class="control-label col-sm-3">Số CMND</label>
                        <div class="col-sm-9">
                            <input type="text" id="peoplesIdentity" name="peoplesIdentity" value="${CUSTOMER.peoplesIdentity}" class="form-control" placeholder="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3">Loại khách hàng</label>
                        <div class="col-sm-9">
                            <select class="form-control text-center" name="customerTypeId">
                                <option value="">-- Để trống --</option>
                                <c:forEach items="${f:findAllAvailableCustomerType()}" var="customerType">
                                    <option value='${customerType.id}' ${customerType.id==CUSTOMER.customerTypeId?'selected':''}>${customerType.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3">Email</label>
                        <div class="col-sm-9">
                            <input type="email" id="moduleIcon" name="email" value="${CUSTOMER.email}" class="form-control" placeholder="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3">Giới tính</label>
                        <div class="col-sm-9">
                            <label class="checkbox-inline"><input type="radio" name="gender" ${CUSTOMER.gender?'checked':''} value="true">Nam</label>
                            <label class="checkbox-inline"><input type="radio" name="gender" ${CUSTOMER.gender?'':'checked'} value="false">Nữ</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="mobile" class="control-label col-sm-3">Số điện thoại</label>
                        <div class="col-sm-9">
                            <input type="text" id="mobile" name="mobile" value="${CUSTOMER.mobile}" class="form-control" placeholder="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="billingAddress" class="control-label col-sm-3">Địa chỉ ngân hàng</label>
                        <div class="col-sm-9">
                            <input type="text" id="billingAddress" name="billingAddress" value="${CUSTOMER.billingAddress}" class="form-control" placeholder="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="taxCode" class="control-label col-sm-3">Số tài khoản</label>
                        <div class="col-sm-9">
                            <input type="text" id="taxCode" name="taxCode" value="${CUSTOMER.taxCode}" class="form-control" placeholder="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="taxCode" class="control-label col-sm-3">Địa chỉ</label>
                        <div class="col-sm-9">
                            <textarea name="address" rows="4" id="address" class="form-control">${CUSTOMER.address}</textarea>
                        </div>
                    </div>
                </div>
                <div class="modal-footer text-center">
                    <button type="submit" class="btn btn-primary">Lưu</button>
                    <button type="reset" class="btn btn-danger">Nhập lai</button>
                </div>
            </form>
        </div>
    </div>
</div>
