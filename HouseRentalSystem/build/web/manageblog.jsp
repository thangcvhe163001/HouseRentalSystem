<%-- 
    Document   : manageblog
    Created on : Jul 18, 2023, 12:28:56 AM
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
            #tble td{
                width: 20%;
            }
            .numberP{
                width: 10%;
                height: 2em;
                margin: 0.5em auto;
            }
            .numberP a{
                text-decoration: none;
            }
            .active{
                color:red;
                cursor: default;
                padding: 0.5em;
            }
            .inactive{
                color: black;
                padding: 0.5em;
            }
            td .pra{
                color: #37a344;
                margin: auto 0;
                width: 90%;
                display: -webkit-box;
                -webkit-box-orient: vertical;
                -webkit-line-clamp: 2;
                overflow: hidden;
                text-overflow: ellipsis;
            }

        </style>
        <title>Quản lý tin đăng blogs</title>
    </head>

    <body class="desktop dashboard quan-ly tin-dang loaded ready">
        <div id="webpage">
            <jsp:include page="header_sidebar.jsp"></jsp:include>
                <div class="container-fluid">
                    <div class="row">
                    <jsp:include page="sidebar.jsp"></jsp:include>
                        <main role="main" class="ml-sm-auto col-lg-10">

                            <div
                                class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
                                <h1 class="h2">Quản lý tin tức</h1>

                                <div class="btn-toolbar mb-2 mb-md-0">
                                    <div class="dropdown mr-1">
                                        <button class="btn btn-outline-secondary dropdown-toggle btn-sm" type="button"
                                                id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
                                                aria-expanded="false">
                                            Lọc theo trạng thái
                                        </button>
                                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                            <a class="dropdown-item"
                                               href="manageblogs?index=${index}">Tất cả</a>
                                        <a class="dropdown-item"
                                           href="sortedblogs?index=${index}&nav=d">Mới nhất</a>
                                        <a class="dropdown-item"
                                           href="sortedblogs?index=${index}&nav=a">Cũ nhất</a>
                                        <a class="dropdown-item"
                                           href="sortedblogs?index=${index}&nav=az">A-Z</a>
                                    </div>
                                </div>
                                <a class="btn btn-danger btn-sm d-none d-md-block"
                                   href="postblog">Đăng tin mới</a>

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
                                            <th>Nội dung</th>
                                            <th style="white-space: nowrap;">Ngày đăng</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <!-- Edited content -->        
                                        <c:forEach items="${lsB}" var="b">
                                            <tr>
                                                <td style="width: 10%">#${b.getId()}</td>
                                                <td style="width: 10%">
                                                    <div class="post_thumb">
                                                        <a href="#" target="_self"><img
                                                                src="./assets/images/${b.getImage()}"></a>
                                                    </div>
                                                </td>
                                                <td style="width: 25%">
                                                    <a class="post_title" target="_self" href="detailblog?id=${b.getId()}&idPoster=${b.getPosterId()}" style="color: black;">${b.getTopic()}</a>

                                                    <div class="post_btn_toolbar mt-3">
                                                        <a style="background-color: #52a4fa;color: white" href="editblog?id=${b.getId()}&nav=null"
                                                           class="btn btn-sm"><svg xmlns="http://www.w3.org/2000/svg"
                                                                                width="24" height="24" viewBox="0 0 24 24" fill="none"
                                                                                stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                                                                stroke-linejoin="round" class="feather feather-edit-2">
                                                            <polygon points="16 3 21 8 8 21 3 21 3 16 16 3"></polygon>
                                                            </svg> Sửa tin</a>
                                                        <a style="background-color: #f75757;color: white" href="deleteblog?id=${b.getId()}&nav=null"
                                                           class="btn btn-sm">
                                                            <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg"><path d="M7 4a2 2 0 0 1 2-2h6a2 2 0 0 1 2 2v2h4a1 1 0 1 1 0 2h-1.069l-.867 12.142A2 2 0 0 1 17.069 22H6.93a2 2 0 0 1-1.995-1.858L4.07 8H3a1 1 0 0 1 0-2h4V4zm2 2h6V4H9v2zM6.074 8l.857 12H17.07l.857-12H6.074z" fill="#0D0D0D"/>
                                                            </svg> Xoá tin</a>
                                                    </div>

                                                </td>
                                                <td>
                                                    <div class="pra">${b.getContent()}</div>
                                                </td>
                                                <td style="width: 15%">${b.getPostTime()}</td>
                                            </tr>
                                        </c:forEach>

                                    </tbody>
                                </table>
                            </div>


                            <!-- end pagination -->
                        </div>
                        <br>
                        <c:if test="${countB != 0}"> 
                            <div class="numberP">
                                <c:forEach begin="1" end="${endPage}" step="1" var="i">
                                    <a href="manageblogs?index=${i}${nav!=null?"&nav=":""}${nav!=null?nav:''}" class = "${index eq i ? 'active' : 'inactive'}">${i}</a>
                                </c:forEach>
                            </div>
                        </c:if>
                        <br>
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