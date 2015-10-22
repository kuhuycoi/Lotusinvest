<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="/WEB-INF/tlds/functions.tld" %>
<div class="modal modal-insert-customer diagram" id="myModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lgs">
        <div class="modal-content">
            <div class="modal-header">
                <div class="row">
                    <div class="col-md-6">
                        <c:set value="${f:findDistributorById(sessionScope['CUSTOMER_ID'])}" var="cus"/>
                        <h4 class="col-md-12"><i>Cây phả hệ chỉ định</i></h4>
                        <span class="col-md-12"><small>Người đứng đầu: ${cus.userName}</small></span>
                    </div>
                    <div class="col-md-6 text-right">
                        <button type="button" class="btn btn-default" data-dismiss="modal" data-toggle="tooltip" data-placement="bottom" data-title="Đóng trang hiển thị.">
                            <i class="fa fa-times-circle"></i>&nbsp;Đóng
                        </button>
                    </div>
                </div>
            </div>
            <div class="modal-body">
                <div id="sample" style="position: relative;">
                    <div id="myDiagram" datas='${f:getJSON(LIST_TREE)}'></div>
                    <div id="myOverview"></div>
                </div>
            </div>
            <div class="modal-footer">                        
                <div class="row">
                    <div class="col-md-1">
                        <button class="btn btn-default" id="zoomToFit" data-toggle="tooltip" data-placement="top" data-title="Hiển thị toàn bộ thành phần đã mở rộng."><i class="fa fa-expand"></i></button>
                    </div>
                    <div class="col-md-1">
                        <button class="btn btn-default" id="centerRoot" data-toggle="tooltip" data-placement="top" data-title="Đến vị trí của tôi."><i class="fa fa-compress"></i></button>
                    </div>
                    <div class="col-md-4">
                        <div class="input-group">                        
                            <input type="search" id="mySearch" class="form-control" 
                                   onkeypress="if (event.keyCode === 13)
                                               searchDiagram();
                                   " />
                            <div class="input-group-btn">
                                <button class="btn btn-default" onclick="searchDiagram()"><i class="fa fa-search"></i></button>
                            </div>
                        </div>                        
                    </div>
                    <div class="col-md-7"></div>
                </div>
            </div>
        </div>
    </div>
</div>
