<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Meta Keyword -->
        <meta name="keywords" content="aal AutoAncillariesLimited Auto Ancillaries Limited">
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
        <title>Lotusinvest Admin Page</title>
        <link rel="shortcut icon" href='<c:url value="/resources/img/web-icon.jpg"/>'>
        <link rel="stylesheet" href="<c:url value="/resources/css/font-open-sans.css"/>" type="text/css">
        <link rel="stylesheet" href='<c:url value="/resources/css/latofonts.css"/>' type="text/css">
        <link rel="stylesheet" href='<c:url value="/resources/css/font-awesome.min.css"/>' type="text/css">
        <link rel="stylesheet" href='<c:url value="/resources/css/themify-icons.css"/>' type="text/css">
        <link rel="stylesheet" href='<c:url value="/resources/css/bootstrap.min.css"/>' type="text/css">
        <link rel="stylesheet" href='<c:url value="/resources/css/bootstrap-dialog.min.css"/>' type="text/css">
        <link rel="stylesheet" href='<c:url value="/resources/css/animate.css"/>' type="text/css">
        <link rel="stylesheet" href='<c:url value="/resources/css/nprogress.css"/>' type="text/css">
        <link rel="stylesheet" href='<c:url value="/resources/css/reset-css.css"/>' type="text/css">
        <link rel="stylesheet" href='<c:url value="/resources/css/message.css"/>' type="text/css">
        <link rel="stylesheet" href='<c:url value="/resources/css/main_admin.css"/>' type="text/css">
        <link rel="stylesheet" href='<c:url value="/resources/css/responsive_admin.css"/>' type="text/css">
    </head>
    <body>
        <div id="sidebar">
            <c:import url="/Admin/Module/Sidebar" />  
        </div>
        <div id="container">
            <div id="header">
                <%@include file="includes/admin/navigation.jsp" %>         
            </div>
            <div id="content">
                <%@include file="includes/admin/default.jsp" %>  
            </div>
        </div>
        <div id="ajax-loading" class="hidden">
            <div class="progress">
                <div class="progress-bar" role="progressbar" style="width: 0">
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
        <!-- Bootbox -->
        <script src='<c:url value="/resources/js/bootstrap-dialog.min.js"/>'></script>
        <!-- Calendar -->
        <script src='<c:url value="/resources/js/jquery.calendar.js"/>'></script>
        <!-- MD5 script -->    
        <script src='<c:url value="/resources/js/jquery.md5.js"/>'></script>
        <!-- WOW script -->
        <script src='<c:url value="/resources/js/wow.min.js"/>'></script>
        <!-- ajax progress -->
        <script src='<c:url value="/resources/js/nprogress.js"/>'></script>
        <!-- hight chart -->
        <script src='<c:url value="/resources/js/highcharts.js"/>'></script>
        <!-- hight chart 3d -->
        <script src='<c:url value="/resources/js/highcharts-3d.js"/>'></script>
        <!-- exporting -->
        <script src='<c:url value="/resources/js/exporting.js"/>'></script>
        <!-- theme custom scripts -->
        <script src='<c:url value="/resources/js/main_admin.js"/>'></script>
    </body>
</html>
