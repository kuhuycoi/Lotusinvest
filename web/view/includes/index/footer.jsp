<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="footer-top">
    <div class="container">
        <div class="row">
            <div class="col-md-4">
                    <h3>Tài khoản</h3>
                <c:if test="${sessionScope['CUSTOMER_ID']==null}">
                    <ul>
                        <li>
                            <a class="btn-change-content" controller="<c:url value='/Customer/Login'/>">Đăng nhập</a>
                        </li>
                        <li>
                            <a class="btn-change-content" controller="<c:url value='/Customer/Register'/>">Đăng ký</a>
                        </li>
                        <li>
                            <a class="btn-change-content" controller="<c:url value='/Customer/ResetPassword'/>">Reset mật khẩu</a>
                        </li>
                    </ul>
                </c:if>
                <c:if test="${sessionScope['CUSTOMER_ID']!=null}">
                    <ul>
                        <li><a class="btn-change-content" controller="<c:url value="/Customer/MyAccount" />">Thông tin tài khoản</a></li> 
                        <li><a class="btn-open-modal" controller="<c:url value="/Customer/TreeCustomer" />">Cây chỉ định trực hệ</a></li> 
                        <li><a class="btn-change-content" controller="<c:url value='/Customer/CustomerForCustomer'/>">Danh sách đã giới thiệu</a></li>
                    </ul>
                </c:if>
            </div>
            <div class="col-md-4">
                <h3>Điều hướng</h3>
                <ul>
                    <li><a href="#">Trang chủ</a></li>
                    <li><a href="#">Giới thiệu</a></li>
                    <li><a href="#">Tin tức</a></li>
                    <li><a href="#">Sản phẩm</a></li>
                    <li><a href="#">Tuyển dụng</a></li>
                    <li><a href="#">Liên hệ</a></li>
                </ul>
            </div>
            <div class="col-md-4">
                <h3>Liên hệ</h3>
                <ul>
                    <li><span><i class="fa fa-location-arrow"></i>Cầu Giấy - Hà Nội</span></li>
                    <li><span><i class="fa fa-phone-square"></i>(04)6.266.0259</span></li>
                    <li><a href="mailto:lotusinvest2015@gmail.com" class="external"><i class="fa fa-envelope-square"></i>lotusinvest2015@gmail.com</a></li>
                    <li><a href="http://tk.lotusinvest.vn" class="external"><i class="fa fa-home"></i>http://tk.lotusinvest.vn</a></li>
                </ul>
            </div>
        </div>        
    </div>
</div>
<div class="footer-bottom text-center">
    <span>© 2015 <a href="http://tk.lotusinvest.vn">tk.lotusinvest.vn</a>. Designed By LeoTeams</span>
</div>