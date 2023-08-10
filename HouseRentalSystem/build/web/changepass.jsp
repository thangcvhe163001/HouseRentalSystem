<%-- 
    Document   : changepass
    Created on : Jul 12, 2023, 2:49:56 AM
    Author     : win
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
        <meta name="robots" content="noindex,nofolow">
        <meta name="_token" content="ad0BOalbGjFaYLL4hTEY4texgMXBD7KaiapWPxwP">

        <link rel="stylesheet" href="assets/css/style1.css">
        <link rel="stylesheet" href="assets/css/style2.css">
        <link rel="stylesheet" href="assets/css/style3.css">
        <link rel="stylesheet" href="assets/css/style4.css">
        <link rel="stylesheet" href="assets/css/style5.css">
        <link rel="stylesheet" href="assets/css/style6.css">
        <link rel="stylesheet" href="assets/css/style7.css">
        <link rel="stylesheet" href="assets/css/style8.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <style>
            .dropdown-menu {
                display: none;
            }
            .dropdown.show .dropdown-menu {
                display: block;
            }
        </style>
        <title>Quản lý tin đăng</title>
    </head>

    <body class="desktop dashboard quan-ly tin-dang loaded ready">
        <div id="webpage">
            <jsp:include page="header_sidebar.jsp"></jsp:include>
                <div class="container-fluid">
                    <div class="row">
                    <jsp:include page="sidebar.jsp"></jsp:include>   
                    <main role="main" class="ml-sm-auto col-lg-10">

                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="homeController">Phongtro123.com</a></li>
                                <li class="breadcrumb-item"><a href="https://phongtro123.com/quan-ly/index.html">Quản lý</a>
                                </li>
                                <li class="breadcrumb-item"><a
                                        href="changeinformation">Cập nhật
                                        thông tin cá nhân</a></li>
                                <li class="breadcrumb-item active" aria-current="page">Đổi mật khẩu</li>
                            </ol>
                        </nav>
                        <div
                            class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
                            <h1 class="h2">Đổi mật khẩu</h1>
                        </div>

                        <form class="js-form-submit-data" action="changepassword"
                              method="POST"
                              novalidate="novalidate">
                            <div class="form-group row mt-5">
                                <label for="currentPassword" class="col-md-2 offset-md-2 col-form-label">Mật khẩu cũ</label>
                                <div class="col-md-6">
                                    <input type="password" name="oldPass" class="form-control" id="currentPassword"
                                           value="">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="newPassword" class="col-md-2 offset-md-2 col-form-label">Mật khẩu mới</label>
                                <div class="col-md-6">
                                    <input type="password" name="newPass" class="form-control" id="newPassword" value="">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="confirmPassword" class="col-md-2 offset-md-2 col-form-label"> Xác nhận mật khẩu mới</label>
                                <div class="col-md-6">
                                    <input type="password" name="confirmPass" class="form-control" id="confirmPassword" value="">
                                </div>
                            </div>
                            <div class="form-group row mt-5">
                                <label for="" class="col-md-2 col-form-label"></label>
                                <div class="col-md-8">
                                    <button type="submit" class="btn btn-primary mb-2 btn-block">Cập nhật</button>
                                </div>
                            </div>
                        </form>
                        <br><br>
                        <section class="section section-support">
                            <div class="section-content clearfix">
                                <div class="support-bg"></div>
                                <div class="list-support clearfix">
                                    <div class="list-support-title">Liên hệ với chúng tôi nếu bạn cần hỗ trợ:</div>
                                    <div class="support-item">
                                        <span class="support-item-title">Hỗ trợ đăng tin</span>
                                        <a rel="nofollow" href="tel:0902657123">Điện thoại: 0902657123</a>
                                        <a rel="nofollow" target="_blank" href="https://zalo.me/0902657123">Zalo:
                                            0902657123</a>
                                    </div>
                                    <div class="support-item">
                                        <span class="support-item-title">Hỗ trợ đăng tin</span>
                                        <a rel="nofollow" href="tel:0901424123">Điện thoại: 0901424123</a>
                                        <a rel="nofollow" target="_blank" href="https://zalo.me/0901424123">Zalo:
                                            0901424123</a>
                                    </div>
                                    <div class="support-item">
                                        <span class="support-item-title">Hỗ trợ đăng tin</span>
                                        <a rel="nofollow" href="tel:0903007123">Điện thoại: 0903007123</a>
                                        <a rel="nofollow" target="_blank" href="https://zalo.me/0903007123">Zalo:
                                            0903007123</a>
                                    </div>
                                    <div class="support-item">
                                        <span class="support-item-title">Phản ánh/khiếu nại</span>
                                        <a rel="nofollow" href="tel:0917686101">Điện thoại: 0917686101</a>
                                        <a rel="nofollow" target="_blank" href="https://zalo.me/0917686101">Zalo:
                                            0917686101</a>
                                    </div>
                                    <a class="btn btn-page-contact" rel="nofollow" href="/lien-he">Gửi liên hệ</a>
                                </div>
                            </div>
                        </section>
                    </main>
                </div>
            </div>
        </div>
        <script src="/js/main-dashboard.min.js?v=20230606"></script>
        <script src="/js/3rd/feather.min.js"></script>
        <script>feather.replace();</script>
        <script>
            $(document).ready(function () {
                $('.dropdown-toggle').click(function () {
                    $('.dropdown-menu').toggle();
                });
            });
        </script>
    </body>

</html>

