<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <!-- Mobile Specific Meta -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Always force latest IE rendering engine -->
        <meta http-equiv="X-UA-Compatible" content="IE=9">
        <!-- Meta Keyword -->
        <meta name="keywords" content="aal AutoAncillariesLimited Auto Ancillaries Limited">
        <!-- meta character set -->
        <meta charset="utf-8">
        <title>Admin Login Page</title>
        <link rel="shortcut icon" href='<c:url value="/resources/img/web-icon.png"/>'>
        <link rel="stylesheet" href='http://fonts.googleapis.com/css?family=Open+Sans' type="text/css">
        <link rel="stylesheet" href='<c:url value="/resources/css/font-awesome.min.css"/>' type="text/css">
        <link rel="stylesheet" href='<c:url value="/resources/css/bootstrap.min.css"/>' type="text/css">
        <link rel="stylesheet" href='<c:url value="/resources/css/animate.css"/>' type="text/css">
        <link rel="stylesheet" href='<c:url value="/resources/css/nprogress.css"/>' type="text/css">
        <link rel="stylesheet" href='<c:url value="/resources/css/reset-css.css"/>' type="text/css">
        <link rel="stylesheet" href='<c:url value="/resources/css/login_admin.css"/>' type="text/css">
        <link rel="stylesheet" href='<c:url value="/resources/css/responsive_admin.css"/>' type="text/css">
    </head>
    <body>     
        <div class="overlay"></div>
        <div id="container">
            <div class="panel signin">
                <div class="panel-heading">
                    <h1>Lotusinvest</h1>
                    <h4>Xin chào! Mời bạn đăng nhập.</h4>
                </div>
                <div class="panel-body">
                    <div class="or">v</div>
                    <form action="<c:url value="/Admin/Login" />" id="form-login-admin">
                        <div class="form-group mb10">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <input type="text" name="userName" class="form-control" placeholder="Enter Username">
                            </div>
                        </div>
                        <div class="form-group nomargin">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                <input type="password" name="password" class="form-control" placeholder="Enter Password">
                            </div>
                        </div>
                        <div class="form-group clearfix">
                            <div class="checkbox-group pull-left">
                                <input type="checkbox" class="external" id="inputCheckbox" />
                                <label for="inputCheckbox">Ghi nhớ đăng nhập</label>
                            </div>
                            <a href="#" class="forgot pull-right">Quên mật khẩu?</a>
                        </div>
                        <div class="form-group">
                            <button class="btn btn-primary btn-quirk btn-block btn-lg">Sign In</button>
                        </div>
                    </form>
                </div>
                <div class="panel-footer">
                    <h3>WEBSITE BY <a href="#">Leoteam</a></h3>
                    <h3>© 2015. All RIGHT RESERVED.</h3>
                    <div>
                        <a href="#"><i class="fa fa-twitter"></i></a>
                        <a href="#"><i class="fa fa-facebook"></i></a>
                        <a href="#"><i class="fa fa-dribbble"></i></a>
                    </div>
                </div>
            </div>
        </div> 
        <!-- for old browser -->
        <script src='<c:url value="/resources/js/vendor/modernizr-2.6.2.min.js"/>'></script>
        <!-- main jQuery -->
        <script src='<c:url value="/resources/js/vendor/jquery-1.11.1.min.js"/>'></script>
        <!-- Validator -->
        <script src='<c:url value="/resources/js/jquery.validate.min.js"/>'></script>
        <!-- Additional method validator -->
        <script src='<c:url value="/resources/js/additional-methods.min.js"/>'></script>
        <!-- Bootstrap -->
        <script src='<c:url value="/resources/js/bootstrap.min.js"/>'></script>
        <!-- Calendar -->
        <script src='<c:url value="/resources/js/jquery.calendar.js"/>'></script>
        <!-- MD5 script -->    
        <script src='<c:url value="/resources/js/jquery.md5.js"/>'></script>
        <!-- WOW script -->
        <script src='<c:url value="/resources/js/wow.min.js"/>'></script>
        <!-- ajax progress -->
        <script src='<c:url value="/resources/js/nprogress.js"/>'></script>
        <!-- theme custom scripts -->
        <script src='<c:url value="/resources/js/login_admin.js"/>'></script>
    </body>
</html>