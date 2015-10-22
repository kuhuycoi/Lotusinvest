<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="panel">
    <div class="panel-heading">
        <a data-toggle="collapse" href="#collapseOne">
            Thông tin hệ thống
            
        </a>
    </div>
    <div class="panel-body collapse in" id="collapseOne">
        <ul>
            <li><a class="btn-change-content" controller="<c:url value="/Customer/MyAccount" />">Thông tin tài khoản</a></li> 
            <li><a class="btn-change-content" controller="<c:url value="/Customer/ChangePassword" />">Thay đổi mật khẩu</a></li> 
            <li><a class="btn-open-diagram" controller="<c:url value="/Customer/TreeCustomer" />">Cây phả hệ chỉ định</a></li> 
            <li><a class="btn-change-content" controller="<c:url value='/Customer/CustomerForCustomer'/>">Danh sách đã giới thiệu</a></li>
        </ul>
    </div>
</div>
<div class="panel">
    <div class="panel-heading">
        <a data-toggle="collapse" href="#collapseTwo">
            Lịch sử giao dịch
            
        </a>
    </div>
    <div class="panel-body collapse in" id="collapseTwo">
        <ul>
            <li><a class="btn-change-content" controller="<c:url value="/History/CustomerRankCustomer/ViewInsert" />">Nạp PV</a></li>
            <li><a class="btn-change-content" controller="<c:url value="/History/CustomerRankCustomer" />">Lịch sử nạp PV</a></li>
            <li><a class="btn-change-content" controller="<c:url value="/History/UsedPinSys" />">Lịch sử nạp mã PIN</a></li>
        </ul>
    </div>
</div>
<div class="panel">
    <div class="panel-heading">
        <a data-toggle="collapse" href="#collapseThree">
            Thống kê thưởng
            
        </a>
    </div>
    <div class="panel-body collapse in" id="collapseThree">
        <ul>
            <li><a class="btn-change-content" controller="<c:url value="/Report/Award/1" />">Thưởng mã rơi</a></li>
            <li><a class="btn-change-content" controller="<c:url value="/Report/Award/4" />">Thưởng hoa hồng trực tiếp</a></li>
            <li><a class="btn-change-content" controller="<c:url value="/Report/Award/13" />">Thưởng cân cặp</a></li>
            <li><a class="btn-change-content" controller="<c:url value="/Report/Award/28" />">Thưởng hoa hồng gói đầu tư</a></li>
            <li><a class="btn-change-content" controller="<c:url value="/Report/Award/29" />">Thưởng lên cấp</a></li>
            <li><a class="btn-change-content" href="#">Kết quả tri ân</a></li>
            <li><a class="btn-change-content" href="#">Thưởng lên quản lý</a></li>
        </ul>
    </div>
</div>
