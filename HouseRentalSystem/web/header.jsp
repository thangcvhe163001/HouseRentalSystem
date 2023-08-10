<%-- 
    Document   : header
    Created on : Jun 15, 2023, 2:25:42 AM
    Author     : win
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="content-language" content="vi">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>House Rental System</title>
        <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon">
        <link rel="icon" href="/images/favicon.ico" type="image/x-icon">
        <link rel="stylesheet" href="assets/css/styleHome.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
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
    </head>
    <body>
        <div class="container container-header clearfix">
            <a href="homeController"><img id="top-logo" src="assets/images/logo/logo3.png"></a>    
            <div class="user-welcome clearfix js-reload-html-header">
                <c:if test="${sessionScope.acc == null}">
                    <a rel="nofollow" class="btn" href="login"><i class="fas fa-heart"></i> Yêu thích </a>
                    <a rel="nofollow" class="btn" href="login"><i class="fab fa-facebook-messenger"></i> Nhắn tin</a>
                    <a rel="nofollow" class="btn" href="login"><i class="fas fa-sign-in-alt"></i> Đăng nhập</a>
                    <a rel="nofollow" class="btn" href="register"><i class="fas fa-user"></i> Đăng ký</a>
                </c:if>
                    <c:if test="${sessionScope.acc != null}">
                        <c:if test="${sessionScope.acc.getRole_ID() == 3}">
                        <div class="user-welcome clearfix js-reload-html-header"><a class="welcome-text"
                                                                                    href="managehouse" rel="nofollow">
                                <img src="./assets/images/${sessionScope.acc.getProfile_Picture()}"
                                     style="border: 1px solid #ddd; width: 40px; height: 40px; border-radius: 50%; margin-right: 5px;">
                                <div>
                                    <span
                                        style="display: block;font-size: 1.2rem;margin-bottom: 3px;max-width: 300px;text-overflow: ellipsis;overflow: hidden;white-space: nowrap;">Xin
                                        chào, <strong>${sessionScope.acc.fullname}</strong></span><span style="font-size: 0.9rem">Mã tài khoản:
                                        <strong>${sessionScope.acc.id}</strong></span>
                                </div>
                            </a>
                            <a rel="nofollow" class="btn" href="favourites"><i class="fas fa-heart"></i> Yêu thích <span
                                    class="number-count js-save-post-total">${sessionScope.acc.favourites.size()}</span></a>

<!--                            <a rel="nofollow" class="btn" href="historyhouse"><i class="fas fa-history"></i> Lịch sử <span
                                    class="number-count js-save-post-total">${sessionScope.acc.history.size()}</span></a>       -->

                            <a rel="nofollow" class="btn" href="message"><i class="fab fa-facebook-messenger"></i> Nhắn tin</a>



                            <div class="dropdown">
                                <button class="btn dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <i class="fas fa-th-large"></i> Quản lý tài khoản
                                </button>
                                <ul class="dropdown-menu" aria-labelledby="dropdownMenu2">
                                    <li><a class="dropdown-menu-item dang-tin" rel="nofollow" href="posthouse"><i class="fas fa-pen"></i> Đăng tin cho thuê</a></li>
                                    <li><a class="dropdown-menu-item quan-ly-tin-dang" rel="nofollow" href="managehouse"><i class="fas fa-tasks"></i> Quản lý tin đăng nhà</a></li>
                                    <li><a class="dropdown-menu-item quan-ly-tin-dang" rel="nofollow" href="manageblogs"><i class="fas fa-receipt"></i> Quản lý tin tức</a></li>
                                    <li><a class="dropdown-menu-item thong-tin-ca-nhan" rel="nofollow" href="changeinformation"><i class="fas fa-user-check"></i> Thông tin cá nhân</a></li>
                                    <li><a class="dropdown-menu-item thong-tin-ca-nhan" rel="nofollow" href="favourites"><i class="fas fa-heart"></i> Tin nhà đã lưu</a></li>
                                    <li><a class="dropdown-menu-item thong-tin-ca-nhan" rel="nofollow" href="listfavouriteblog"><i class="fab fa-gratipay"></i> Tin tức đã lưu</a></li>
                                    <li><a rel="nofollow" class="dropdown-menu-item thong-tin-ca-nhan" href="historyhouse"><i class="fas fa-history"></i> Lịch sử xem </a></li>
                                    <li><a class="dropdown-menu-item thoat js-user-logout" rel="nofollow" href="logout"><i class="fas fa-sign-out-alt"></i> Thoát</a></li>
                                </ul>
                            </div>            
                            <a rel="nofollow" class="btn btn-add-post" href="posthouse">Đăng tin mới <i class="fas fa-plus"></i></a>
                        </div>
                    </c:if>
                    <c:if test="${sessionScope.acc.getRole_ID() == 2}">
                        <div class="user-welcome clearfix js-reload-html-header"><a class="welcome-text"
                                                                                    href="changeinformation" rel="nofollow">
                                <img src="./assets/images/${sessionScope.acc.getProfile_Picture()}"
                                     style="border: 1px solid #ddd; width: 40px; height: 40px; border-radius: 50%; margin-right: 5px;">
                                <div>
                                    <span
                                        style="display: block;font-size: 1.2rem;margin-bottom: 3px;max-width: 300px;text-overflow: ellipsis;overflow: hidden;white-space: nowrap;">Xin
                                        chào, <strong>${sessionScope.acc.fullname}</strong></span><span style="font-size: 0.9rem">Mã tài khoản:
                                        <strong>${sessionScope.acc.id}</strong></span>
                                </div>
                            </a>
                            <a rel="nofollow" class="btn" href="favourites"><i class="fas fa-heart"></i> Yêu thích <span
                                    class="number-count js-save-post-total">${sessionScope.acc.favourites.size()}</span></a>

<!--                            <a rel="nofollow" class="btn" href="historyhouse"><i class="fas fa-history"></i> Lịch sử <span
                                    class="number-count js-save-post-total">${sessionScope.acc.history.size()}</span></a> -->
                            <a rel="nofollow" class="btn" href="message"><i class="fab fa-facebook-messenger"></i> Nhắn tin</a>
                            <div class="dropdown">
                                <button class="btn dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <i class="fas fa-th-large"></i> Quản lý tài khoản
                                </button>
                                <ul class="dropdown-menu" aria-labelledby="dropdownMenu2">
                                    <li><a class="dropdown-menu-item thong-tin-ca-nhan" rel="nofollow" href="changeinformation"><i class="fas fa-user-check"></i> Thông tin cá nhân</a></li>
                                    <li><a class="dropdown-menu-item quan-ly-tin-dang" rel="nofollow" href="manageblogs"><i class="fas fa-receipt"></i> Quản lý tin tức</a></li>
                                    <li><a class="dropdown-menu-item tin-da-luu" rel="nofollow" href="favourites"><i class="fas fa-heart"></i> Tin nhà đã lưu</a></li>
                                    <li><a class="dropdown-menu-item thong-tin-ca-nhan" rel="nofollow" href="listfavouriteblog"><i class="fab fa-gratipay"></i> Tin tức đã lưu</a></li>
                                    <li><a rel="nofollow" class="dropdown-menu-item thong-tin-ca-nhan" href="historyhouse"><i class="fas fa-history"></i> Lịch sử xem </a></li>
                                    <li><a class="dropdown-menu-item thoat js-user-logout" rel="nofollow" href="logout"><i class="fas fa-sign-out-alt"></i> Thoát</a></li>
                                </ul>
                            </div>            
                        </div>
                    </c:if>
                </c:if>
            </div>
        </div>
        <nav id="navbar-menu" class="">
            <ul id="menu-main-menu" class="container-menu clearfix level-1">
                <li class="navbar_item clearfix ${requestScope.categoryID == null ? 'active current-menu-item' : ''}"><a href="homeController">Trang
                        chủ</a></li>
                <li class="navbar_item clearfix ${requestScope.categoryID == 1 ? 'active current-menu-item' : ''}"><a href="homeController?categoryID=1">Cho thuê phòng trọ</a></li>
                <li class="navbar_item clearfix ${requestScope.categoryID == 2 ? 'active current-menu-item' : ''}"><a href="homeController?categoryID=2">Cho thuê chung cư</a></li>
                <li class="navbar_item clearfix ${requestScope.categoryID == 3 ? 'active current-menu-item' : ''}"><a href="homeController?categoryID=3">Cho thuê biệt thự</a></li>
                <li class="navbar_item clearfix ${requestScope.categoryID == 4 ? 'active current-menu-item' : ''}"><a href="homeController?categoryID=4">Cho thuê căn hộ</a></li>
                <li class="navbar_item clearfix"><a href="listblog">Tin tức</a></li>
                <li class="navbar_item clearfix"><a href="feedbackforsystem">Ðánh giá</a></li>
            </ul>
        </nav>
        <script>
            $(document).ready(function () {
                $('.dropdown-toggle').click(function () {
                    $('.dropdown-menu').toggle();
                });
            });
        </script>
    </body>
</html>
