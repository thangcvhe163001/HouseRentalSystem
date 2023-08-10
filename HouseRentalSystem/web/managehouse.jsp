<%-- 
    Document   : managehouse
    Created on : Jul 22, 2023, 2:41:41 AM
    Author     : win
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                                    <li class="breadcrumb-item"><a href="homeController">House Rental System</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">Danh sách tin đăng</li>
                                </ol>
                            </nav>
                            <div
                                class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
                                <h1 class="h2">Quản lý tin đăng</h1>

                                <div class="btn-toolbar mb-2 mb-md-0">
                                    <div class="dropdown mr-1">
                                    </div>
                                    <a class="btn btn-danger btn-sm d-none d-md-block"
                                       href="posthouse">Đăng tin mới</a>

                                </div>

                            </div>

                            <div class="d-none d-md-block">
                                <div class="table-responsive">
                                    <table class="table table_post_listing table-bordered _table-hover">
                                        <thead>
                                            <tr>
                                                <th>Mã tin</th>
                                                <th style="text-align: center; white-space: nowrap;">Ảnh đại diện</th>
                                                <th>Tiêu đề</th>
                                                <th>Giá</th>
                                                <th style="white-space: nowrap;">Ngày đăng</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${requestScope.list}" var="i">
                                            <tr>
                                                <td>${i.getId()}</td>
                                                <td>
                                                    <div class="post_thumb">
                                                        <a href="detailhouse?id=${i.getId()}" target="_blank"><img
                                                                src="assets/images/${i.getImg()}"></a>
                                                    </div>
                                                </td>
                                                <td>
                                                    <a class="post_title" target="_blank" href="detailhouse?id=${i.getId()}">${i.getTitle()}</a>
                                                    <p style="margin-top:10px;"><strong>Địa chỉ:</strong> ${i.getFull_Address()}</p>
                                                    <div class="post_btn_toolbar mt-3">
                                                        <a href="deletehouse?id=${i.getId()}"
                                                           class="btn btn-sm btn_danglai text-danger btn-warning"><svg
                                                                xmlns="http://www.w3.org/2000/svg" width="24" height="24"
                                                                viewBox="0 0 24 24" fill="none" stroke="currentColor"
                                                                stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
                                                                class="feather feather-credit-card">
                                                            <rect x="1" y="4" width="22" height="16" rx="2" ry="2"></rect>
                                                            <line x1="1" y1="10" x2="23" y2="10"></line>
                                                            </svg> Xóa tin</a>
                                                        <a href="edithouse?id=${i.getId()}"
                                                           class="btn btn-sm"><svg xmlns="http://www.w3.org/2000/svg"
                                                                                width="24" height="24" viewBox="0 0 24 24" fill="none"
                                                                                stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                                                                stroke-linejoin="round" class="feather feather-edit-2">
                                                            <polygon points="16 3 21 8 8 21 3 21 3 16 16 3"></polygon>
                                                            </svg> Sửa tin</a>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="post_price">${i.getPrice()/1000000}/tháng</div>
                                                </td>
                                                <td><fmt:formatDate value="${i.getAdded_Date()}" pattern="dd/MM/yyyy" /></td>

                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>


                            <!-- end pagination -->
                        </div>
                        <br><br>
<!--                        <section class="section section-support">
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
                                        <a rel="nofollow" href="tel:0981504039">Điện thoại: 0981504039</a>
                                        <a rel="nofollow" target="_blank" href="https://zalo.me/0981504039">Zalo:
                                            0981504039</a>
                                    </div>
                                    <div class="support-item">
                                        <span class="support-item-title">Phản ánh/khiếu nại</span>
                                        <a rel="nofollow" href="tel:0917686101">Điện thoại: 0917686101</a>
                                        <a rel="nofollow" target="_blank" href="https://zalo.me/0917686101">Zalo:
                                            0917686101</a>
                                    </div>
                                </div>
                            </div>
                        </section>-->
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
