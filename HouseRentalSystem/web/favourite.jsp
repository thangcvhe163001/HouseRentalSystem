<%-- 
    Document   : favourite
    Created on : Jun 29, 2023, 10:48:41 AM
    Author     : win
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Danh sách yêu thích</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
        <style>
            a.disabled {
                pointer-events: none;
                cursor: default;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <main id="main">
            <header class="page-header welcome">
                <h1 class="page-h1">Kênh thông tin phòng trọ Hà Nội</h1>
            </header>
            <section class="section section-top-location">
            </section>
            <div class="container clearfix">
                <div id="left-col">
                    <section class="section section-post-listing">
                        <div class="section-header"><span class="section-title">BÀI VIẾT ĐÃ THÍCH</span></div>
                        <ul class="post-listing clearfix">
                            <c:forEach items="${requestScope.list}" var="i">
                                <li class="post-item post-id-627736 style-4 clearfix tin-vip vipnoibat" style="border-color: #E13427;" post-id="627736">
                                    <figure class="post-thumb">
                                        <a class="clearfix" href="detailhouse?id=${i.getId()}">
                                            <img class="lazy_done" src="assets/images/${i.getImg()}" data-src="" alt="" height="100" width="100" style="width:1200" layout="responsive" data-loaded="true">
                                        </a>
                                        <span class="post-save js-btn-save"></span>
                                    </figure>
                                    <div class="post-meta">
                                        <h3 class="post-title">
                                            <a style="color: #E13427;" href="detailhouse?id=${i.getId()}">
                                                <span class="star star-5"><i style="color: #e4f03c;" class="fas fa-star"></i>${i.getRating()}</span>
                                                    ${i.getTitle()}
                                            </a>
                                        </h3>
                                        <div class="meta-row clearfix">
                                            <span class="post-price">${(i.getPrice())/1000000} triệu/tháng</span>
                                            <span class="post-location">${i.getFull_Address()}</span>
                                            <span class="post-location">${i.getArea()}m<sup>2</sup></span>
                                            <time class="post-time">${i.getAdded_Date()}</time>
                                        </div>
                                        <div class="meta-row clearfix"><p class="post-summary">${i.getDescription()}</p></div>
                                        <div class="meta-row clearfix">
                                            <div class="post-author">
                                                <img src="./assets/images/${i.account.getProfile_Picture()}">
                                                <span class="author-name">${i.account.getFullname()}</span>
                                            </div>
<!--                                            <a rel="nofollow" target="_blank" href="" class="btn-quick-zalo">Nhắn Zalo</a>-->
                                            <a rel="nofollow" target="_blank" href="tel:0965889995" class="btn-quick-call">Gọi ${i.account.getPhone_Number()}</a>
                                        </div>
                                        <a href="changeStatus?id=${i.getId()}&uid=${sessionScope.acc.getId()}"><i class="fas fa-trash-alt"></i></a>
                                    </div>
                                </li>
                            </c:forEach>                           
                        </ul>
                    </section>
                    <ul class="pagination">
                        <c:if test="${sessionScope.searchSession == 0}">
                            <c:forEach begin="1" end="${requestScope.total}" var="i">
                                <a class="page-item" href="favourites?pageNum=${i}&orderBy=${requestScope.orderBy}&districtId=${requestScope.districtId}&categoryID=${requestScope.categoryID}&minArea=${requestScope.minArea}&maxArea=${requestScope.maxArea}&minPrice=${requestScope.minPrice}&maxPrice=${requestScope.maxPrice}&txt=${txt}" style="color : ${requestScope.pageNum == i ? 'red' : ''}"><span class="page-link">${i}</span></a>
                            </c:forEach>
                        </c:if>
                    </ul>
                </div>
                <aside id="aside">
                    <section class="section section-sublink">
                        <div class="section-header"><span class="section-title">Xem theo giá</span></div>
                        <ul class="list-links price clearfix">
                            <li><a href="homeController?minPrice=0&maxPrice=999999">Dưới 1 triệu</a></li>
                            <li><a href="homeController?minPrice=1000000&maxPrice=2000000">Từ 1 - 2 triệu</a></li>
                            <li><a href="homeController?minPrice=2000001&maxPrice=3000000">Từ 2 - 3 triệu</a></li>
                            <li><a href="homeController?minPrice=3000001&maxPrice=5000000">Từ 3 - 5 triệu</a></li>
                            <li><a href="homeController?minPrice=5000001&maxPrice=7000000">Từ 5 - 7 triệu</a></li>
                            <li><a href="homeController?minPrice=7000001&maxPrice=10000000">Từ 7 - 10 triệu</a></li>
                            <li><a href="homeController?minPrice=10000000&maxPrice=14999999">Từ 10 - 15 triệu</a></li>
                            <li><a href="homeController?minPrice=15000000&maxPrice=999999999">Trên 15 triệu</a></li>
                        </ul>
                    </section>
                    <section class="section section-sublink">
                        <div class="section-header"><span class="section-title">Xem theo diện tích</span></div>
                        <ul class="list-links acreage clearfix">
                            <li><a href="homeController?minArea=0&maxArea=24">Dưới 25 m<sup>2</sup></a></li>
                            <li><a href="homeController?minArea=25&maxArea=29">Từ 25 - 30m<sup>2</sup></a></li>
                            <li><a href="homeController?minArea=30&maxArea=49">Từ 30 - 50m<sup>2</sup></a></li>
                            <li><a href="homeController?minArea=50&maxArea=69">Từ 50 - 70m<sup>2</sup></a></li>
                            <li><a href="homeController?minArea=70&maxArea=89">Từ 70 - 90m<sup>2</sup></a></li>
                            <li><a href="homeController?minArea=90&maxArea=999999999">Trên 90m<sup>2</sup></a></li>
                        </ul>
                    </section>
                    <section class="section section-aside-tinmoidang">
                        <div class="section-header">
                            <span class="section-title">Tin mới đăng</span>
                        </div>
                        <c:forEach items="${requestScope.listNew}" var="i">
                            <ul class="post-listing aside clearfix">
                                <li class="post-item clearfix normal" post-id="628215">
                                    <a href="detailhouse?id=${i.getId()}" >
                                        <figure>
                                            <img class="lazy_done" src="assets/images/${i.getImg()}" height="100" width="100" layout="responsive" data-loaded="true"/>
                                        </figure>
                                        <div class="post-meta">
                                            <span class="post-title" style="color: #055699">${i.getTitle()}</span>
                                            <span class="post-price">${i.getPrice()/1000000} triệu/tháng</span>
                                            <time class="post-time" title="Chủ Nhật, 14:29 18/06/2023">${i.getAdded_Date()}</time>
                                        </div>
                                    </a>
                                </li>
                            </ul>
                        </c:forEach>
                    </section>
                </aside>
            </div>
        </main>
        <jsp:include page="footer.jsp"></jsp:include> 
    </body>
</html>
