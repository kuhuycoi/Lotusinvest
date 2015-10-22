<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>Lịch sử giao dich</h1>
<div class="panel">
    <div class="panel-heading">
        <h2>Nạp PV qua thẻ PIN</h2>
    </div><!-- end panel heading -->
    <div class="panel-body">        
        <p>Thẻ PIN chỉ có giá trị sử dụng một lần duy nhất.<br>
            Vui lòng giữ lại thẻ PIN cho đến khi nhận được thông báo hoặc để liên hệ với đại lý.</p>
        <div class="row">
            <form class="form-insert-normal" method="post" novalidate action="<c:url value='/History/CustomerRankCustomer/DepositPV'/>">
                <div class="row">
                    <div class="col-md-3">
                        <label>Mã PIN</label>
                    </div>
                    <div class="col-md-9">
                        <input type="text" name="pinNumber" class="pin form-control">
                    </div>
                </div> 
                <div class="buttonBar">
                    <button type="submit" class="btn btn-default">
                        Nạp
                    </button>
                    <button type="reset" class="btn btn-default">
                        Nhập lại
                    </button>
                </div>
            </form>     
        </div>   
    </div>
</div><!-- end panel-->
