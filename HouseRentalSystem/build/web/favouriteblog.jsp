<%-- 
    Document   : favouriteblog
    Created on : Jul 19, 2023, 3:10:55 PM
    Author     : win
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="assets/css/single-blog.css">
        <title>Single-blog</title>
        <style>
            #cr {
                display: flex;
                justify-content: center;
                margin-bottom: 20px;
            }
            .pagination {
                display: flex;
                list-style-type: none;
                padding: 0;
                justify-content: center;
            }

            .page-item {
                display: flex;
            }

            .page-link {
                display: inline-block;
                padding: 5px 10px;
                margin: 0 2px;
                text-decoration: none;
                background-color: #f0f0f0;
                color: #333;
            }

            .page-link.active {
                background-color: #333;
                color: #fff;
            }
            .spaced-icon {
                margin-left: 5px;
            }
            .optionP {
                cursor: pointer;
            }
            .listOption {
                display: none;
                position: absolute;
                background-color: #fff;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
                padding: 10px;
                border-radius: 4px;
            }

            .listOption a {
                display: block;
                color: #333;
                text-decoration: none;
                padding: 5px 0;
            }

            .listOption a:hover {
                background-color: #f5f5f5;
            }

            .listOption ion-icon {
                margin-right: 5px;
            }

        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <main id="main">
                <div id="breadcrumb">
                    <ol class="clearfix">
                        <li class="first link"><a href="homeController" title="Trang chủ"><span>Trang
                                    chủ</span></a></li>
                        <li class="link last"><span>Tin tức</span></li>
                    </ol>
                </div>
                <header class="page-header">
                    <h1 class="page-h1">Tin tức đã thích</h1>
                </header>
                <div class="container clearfix">
                    <div id="left-col">
                        <section class="section section-aside-tinmoidang">
                        <c:forEach items="${lsB}" var="b">
                            <ul class="blog-listing clearfix">
                                <article class="article-item clearfix">
                                    <a
                                        href="detailblog?id=${b.getId()}&idPoster=${b.getPosterId()}">
                                        <figure>
                                            <img class="lazy_done" src="./assets/images/${b.getImage()}" alt="${b.getImage()}">
                                        </figure>
                                        <div class="article-meta">
                                            <h6 class="article-title">${b.getTopic()}</h6>
                                            <p class="article-summary">${b.getContent()}</p>
                                        </div>
                                    </a>
                                    <div class="optionP" onclick="showList('${b.getId()}')"><ion-icon name="ellipsis-horizontal-outline"></ion-icon></div>
                                    <div id="${b.getId()}" class="listOption" style="display: none;">
                                        <div><a href="favouriteblog?blogId=${b.getId()}&userId=${sessionScope.acc.getId()}&index=${index}"><ion-icon name="trash-bin-outline"></ion-icon>Xóa</a></div>
                                    </div>  
                                </article>
                            </ul>
                        </c:forEach>
                    </section>
                    <ul class="pagination">
                        <c:if test="${countB != 0}"> 
                            <c:forEach begin="1" end="${endPage}" step="1" var="i">
                                <li class="page-item">
                                    <a class="page-link ${index eq i ? 'active' : 'inactive'}" href="listfavouriteblog?index=${i}">${i}</a>
                                </li>
                            </c:forEach>
                        </c:if>
                    </ul>
                </div>
                <aside id="aside">
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
        <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
        <script>
                                            function showList(s) {
                                                var m = document.getElementById(s);
                                                if (m.style.display === "none") {
                                                    m.style.display = "block";
                                                } else {
                                                    m.style.display = "none";
                                                }
                                            }
        </script>
    </body>
</html>

