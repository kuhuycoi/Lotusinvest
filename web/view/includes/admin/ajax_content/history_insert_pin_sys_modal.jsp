<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="/WEB-INF/tlds/functions.tld" %>
<div class="modal" id="myModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <div class="modal-title">
                    <h4 class="modal-title">Tạo mã PIN mới</h4>
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                </div>
            </div>                           
            <form id="form-insert" confirm="true" class="form-insert-normal form-horizontal" novalidate method="POST" action="${pageContext.servletContext.contextPath}/Admin/History/NotUsedPinSys/Insert">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="rankCustomerId" required class="control-label col-sm-3">Gói PV <i>(*)</i></label>
                        <div class="col-sm-9 block-auto-complete">
                            <select class="form-control text-center" name="rankCustomerId">
                                <c:forEach items="${f:findAllAvailableRankCustomer()}" var="rank">
                                    <option value='${rank.id}'>${rank.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="count" required class="control-label col-sm-3">Số lượng <i>(*)</i></label>
                        <div class="col-sm-9">
                            <input type="number" min="1" max="200" required pattern="^\d+$" class="form-control" id="count" name="count" />
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-info">Thêm</button>
                    <button type="reset" class="btn btn-danger">Nhập lai</button>
                </div>
            </form>
        </div>
    </div>
</div>