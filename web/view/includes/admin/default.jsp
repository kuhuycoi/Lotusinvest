<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="/WEB-INF/tlds/functions.tld" %>
<div id="dashboard-container">
    <div class="row">
        <div class="col-md-3">
            <div class="panel panel-bluegray quick-dashboard">
                <div class="panel-heading">
                    <h5>Tổng thu tháng này</h5>
                    <span class="btn-icon">
                        <a class="btn-close"><i class="ti-close"></i></a>
                    </span>
                </div>
                <div class="panel-body">
                    <a class="panel-icon get">
                        <i class="fa fa-dollar"></i>
                    </a>
                    <h4>${f:formatCurrency(TOTAL_IN_CURRENT_MONTH)}</h4>
                </div>
                <div class="panel-footer">
                    Thu gần nhất: ${f:formatCurrency(NEWEST_DEPOSIT)}
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="panel panel-bluegray quick-dashboard">
                <div class="panel-heading">
                    <h5>Tổng chi tháng này</h5>
                    <span class="btn-icon">
                        <a class="btn-close"><i class="ti-close"></i></a>
                    </span>
                </div>
                <div class="panel-body">
                    <a class="panel-icon out">
                        <i class="fa fa-credit-card"></i>
                    </a>
                    <h4>${f:formatCurrency(TOTAL_OUT_CURRENT_MONTH)}</h4>
                </div>
                <div class="panel-footer">
                    Chi gần nhất: ${f:formatCurrency(NEWEST_AWARD)}
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="panel panel-bluegray quick-dashboard">
                <div class="panel-heading">
                    <h5>Chi/thu tháng này</h5>
                    <span class="btn-icon">
                        <a class="btn-close"><i class="ti-close"></i></a>
                    </span>
                </div>
                <div class="panel-body">
                    <a class="panel-icon div">
                        <i class="fa fa-calculator"></i>
                    </a>
                    <h4>
                        <c:if test="${f:isZero(TOTAL_IN_CURRENT_MONTH)}">0%</c:if>
                        <c:if test="${!f:isZero(TOTAL_IN_CURRENT_MONTH)}">
                            ${f:formatPercentTwoInput(TOTAL_OUT_CURRENT_MONTH,TOTAL_IN_CURRENT_MONTH)}
                        </c:if>
                    </h4>
                </div>
                <div class="panel-footer">
                    Lãi tháng này: ${f:formatCurrency(TOTAL_IN_CURRENT_MONTH-TOTAL_OUT_CURRENT_MONTH)}
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="panel panel-bluegray quick-dashboard">
                <div class="panel-heading">
                    <h5>Tham gia tháng này</h5>
                    <span class="btn-icon">
                        <a class="btn-close"><i class="ti-close"></i></a>
                    </span>
                </div>
                <div class="panel-body">
                    <a class="panel-icon">
                        <i class="fa fa-users"></i>
                    </a>
                    <h4>${TOTAL_USER_CURRENT_MONTH==null?0:TOTAL_USER_CURRENT_MONTH} (Thành Viên)</h4>
                </div>
                <div class="panel-footer">
                    User mới nhất: ${NEWEST_USER}
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6">
            <div class="panel panel-teal quick-dashboard">
                <div class="panel-heading">
                    <h5>Top 5 nạp tiền nhiều nhất trong tháng</h5>
                    <span class="btn-icon">
                        <a class="btn-toggle"><i class="ti-angle-up"></i></a>
                        <a class="btn-close"><i class="ti-close"></i></a>
                    </span>
                </div>
                <div class="panel-body">
                    <table class="table table-hover table-align-center">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th class="text-left">Tên NPP</th>
                                <th>Username</th>
                                <th>Số tiền đã nạp</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="i" value="1"/>
                            <c:forEach items="${TOP_5_DEPOSIT_MONTH}" var="row">
                                <tr>
                                    <td>${i}</td>
                                    <td class="text-left">${row[0]}</td>
                                    <td>${row[1]}</td>
                                    <td>${f:formatCurrency(row[2])}</td>
                                </tr>
                                <c:set var="i" value="${i+1}"/>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="panel panel-teal quick-dashboard">
                <div class="panel-heading">
                    <h5>Top 5 thưởng nhiều nhất trong tháng</h5>
                    <span class="btn-icon">
                        <a class="btn-toggle"><i class="ti-angle-up"></i></a>
                        <a class="btn-close"><i class="ti-close"></i></a>
                    </span>
                </div>
                <div class="panel-body">
                    <table class="table table-hover table-align-center">                        
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th class="text-left">Tên NPP</th>
                                <th>Username</th>
                                <th>Số tiền thưởng</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="i" value="1"/>
                            <c:forEach items="${TOP_5_AWARD_MONTH}" var="row">
                                <tr>
                                    <td>${i}</td>
                                    <td class="text-left">${row[0]}</td>
                                    <td>${row[1]}</td>
                                    <td>${f:formatCurrency(row[2])}</td>
                                </tr>
                                <c:set var="i" value="${i+1}"/>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-3">
            <div class="panel panel-bluegray quick-dashboard">
                <div class="panel-heading">
                    <h5>Tổng thu năm nay</h5>
                    <span class="btn-icon">
                        <a class="btn-close"><i class="ti-close"></i></a>
                    </span>
                </div>
                <div class="panel-body">
                    <a class="panel-icon get">
                        <span>
                            <i class="fa fa-dollar"></i>
                        </span>
                    </a>
                    <h4>${f:formatCurrency(TOTAL_IN_CURRENT_YEAR)}</h4>
                </div>
            </div>
            <div class="panel panel-bluegray quick-dashboard">
                <div class="panel-heading">
                    <h5>Tổng chi năm nay</h5>
                    <span class="btn-icon">
                        <a class="btn-close"><i class="ti-close"></i></a>
                    </span>
                </div>
                <div class="panel-body">
                    <a class="panel-icon out">
                        <span>
                            <i class="fa fa-credit-card"></i>
                        </span>
                    </a>
                    <h4>${f:formatCurrency(TOTAL_OUT_CURRENT_YEAR)}</h4>
                </div>
            </div>
            <div class="panel panel-bluegray quick-dashboard">
                <div class="panel-heading">
                    <h5>Kết toán năm nay</h5>
                    <span class="btn-icon">
                        <a class="btn-close"><i class="ti-close"></i></a>
                    </span>
                </div>
                <div class="panel-body">  
                    <h5>
                        <b>Tỉ lệ chi thu năm nay:                       
                            <c:if test="${f:isZero(TOTAL_IN_CURRENT_YEAR)}">0%</c:if>
                            <c:if test="${!f:isZero(TOTAL_IN_CURRENT_YEAR)}">
                                ${f:formatPercent(100*TOTAL_OUT_CURRENT_YEAR/TOTAL_IN_CURRENT_YEAR)}
                            </c:if>
                        </b>
                    </h5>  
                    <h5><b>Lãi năm nay: ${f:formatCurrency(TOTAL_IN_CURRENT_YEAR-TOTAL_OUT_CURRENT_YEAR)}</b></h5>                           
                </div>
            </div>
        </div>
        <div class="col-md-9">
            <div class="panel panel-brown quick-dashboard">
                <div class="panel-heading">
                    <h5>Biểu đồ doanh số thu chi năm nay</h5>
                    <span class="btn-icon">
                        <a class="btn-toggle"><i class="ti-angle-up"></i></a>
                        <a class="btn-close"><i class="ti-close"></i></a>
                    </span>
                </div>
                <div class="panel-body">
                    <div class="chart" data-chart-type="line" 
                         data-title-h="&nbsp;" 
                         data-unit="PV" data-enabled="true" 
                         data-height="446" 
                         data-enableMouseTracking="true" data-yaxis-percent="true"
                         <input class="categories" title="hidden" type="hidden" value="['Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4', 'Tháng 5', 'Tháng 6', 'Tháng 7', 'Tháng 8', 'Tháng 9', 'Tháng 10', 'Tháng 11', 'Tháng 12']">
                        <c:forEach items="${HISTORY_COMISSION_MAP}" var="entry">
                            <c:if test="${entry.key=='Chi/Thu(%)'}">        
                                <input class="serial" name="${entry.key}" display-type="line" data-type="%" yAxis="1" type="hidden" value="${f:getJSON(entry.value)}"/>
                            </c:if>
                            <c:if test="${entry.key!='Chi/Thu(%)'}">
                                <input class="serial" name="${entry.key}" display-type="column" data-type="VNĐ" yAxis="0" type="hidden" value="${f:getJSON(entry.value)}"/>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6">
            <div class="panel panel-teal quick-dashboard">
                <div class="panel-heading">
                    <h5>Top 5 nạp tiền nhiều nhất trong năm</h5>
                    <span class="btn-icon">
                        <a class="btn-toggle"><i class="ti-angle-up"></i></a>
                        <a class="btn-close"><i class="ti-close"></i></a>
                    </span>
                </div>
                <div class="panel-body">
                    <table class="table table-hover table-align-center">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th class="text-left">Tên NPP</th>
                                <th>Username</th>
                                <th>Số tiền đã nạp</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="i" value="1"/>
                            <c:forEach items="${TOP_5_DEPOSIT_YEAR}" var="row">
                                <tr>
                                    <td>${i}</td>
                                    <td class="text-left">${row[0]}</td>
                                    <td>${row[1]}</td>
                                    <td>${f:formatCurrency(row[2])}</td>
                                </tr>
                                <c:set var="i" value="${i+1}"/>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="panel panel-teal quick-dashboard">
                <div class="panel-heading">
                    <h5>Top 5 thưởng nhiều nhất trong năm</h5>
                    <span class="btn-icon">
                        <a class="btn-toggle"><i class="ti-angle-up"></i></a>
                        <a class="btn-close"><i class="ti-close"></i></a>
                    </span>
                </div>
                <div class="panel-body">
                    <table class="table table-hover table-align-center">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th class="text-left">Tên NPP</th>
                                <th>Username</th>
                                <th>Số tiền thưởng</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="i" value="1"/>
                            <c:forEach items="${TOP_5_AWARD_YEAR}" var="row">
                                <tr>
                                    <td>${i}</td>
                                    <td class="text-left">${row[0]}</td>
                                    <td>${row[1]}</td>
                                    <td>${f:formatCurrency(row[2])}</td>
                                </tr>
                                <c:set var="i" value="${i+1}"/>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-8">
            <div class="panel panel-brown quick-dashboard">
                <div class="panel-heading">
                    <h5>Biểu đồ chi tiết thưởng năm nay</h5>
                    <span class="btn-icon">
                        <a class="btn-toggle"><i class="ti-angle-up"></i></a>
                        <a class="btn-close"><i class="ti-close"></i></a>
                    </span>
                </div>
                <div class="panel-body">
                    <div class="chart"data-chart-type="column" 
                         data-title-h="&nbsp;" 
                         data-title-v-pri="Thống kê hoa hồng(VNĐ)" data-unit="VNĐ" data-enabled="true"
                         data-height="480"
                         data-enableMouseTracking="true">         
                        <input class="categories" title="hidden" type="hidden" value="['Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4', 'Tháng 5', 'Tháng 6', 'Tháng 7', 'Tháng 8', 'Tháng 9', 'Tháng 10', 'Tháng 11', 'Tháng 12']">
                        <c:forEach items="${HISTORY_AWARD_MAP}" var="entry">
                            <input class="serial" name="${entry.key}" display-type="column" data-type="VNĐ" yAxis="0" type="hidden" value="${f:getJSON(entry.value)}"/>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="panel panel-bluegray quick-dashboard">
                <div class="panel-heading">
                    <h5>Tổng thưởng trong năm</h5>
                    <span class="btn-icon">
                        <a class="btn-toggle"><i class="ti-angle-up"></i></a>
                        <a class="btn-close"><i class="ti-close"></i></a>
                    </span>
                </div>
                <div class="panel-body" style="padding: 0">
                    <div class="chart" data-chart-type="pie">
                        <c:forEach items="${HISTORY_TOTAL_AWARD_YEAR}" var="row">
                            <c:if test="${!f:isZero(TOTAL_OUT_CURRENT_YEAR)}">
                                <input class="data" name="${row[0]}" type="hidden" value="${row[1]*100/TOTAL_OUT_CURRENT_YEAR}"/>
                            </c:if>
                        </c:forEach>
                    </div>
                    <table class="table table-hover" style="margin-top: 15px">
                        <tbody>
                            <c:forEach items="${HISTORY_TOTAL_AWARD_YEAR}" var="row">
                                <tr>
                                    <td style="text-align: left">${row[0]}</td>
                                    <td>${f:formatCurrency(row[1])}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>