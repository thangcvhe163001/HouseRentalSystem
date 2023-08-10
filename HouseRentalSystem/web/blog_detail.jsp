<%-- 
    Document   : blog_detail
    Created on : Jul 12, 2023, 3:43:12 PM
    Author     : win
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="assets/css/blog_detail.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
        <title>Blog_detail</title>
        <style>
            .page-header {
                display: flex;
                align-items: center;
            }
            .page-h1 {
                margin-right: 10px; /* Khoảng cách giữa tiêu đề và trái tim */
            }
            .active{
                color:red;
                cursor: default;
            }
            #favoblog ion-icon{
                color:red;
                font-size: 30px;
                transition: 0.5s;
                transition-timing-function: ease;
            }
            #nonfavoblog ion-icon{
                color: black;
                font-size: 30px;
                transition: 0.5s;
                transition-timing-function: ease;
            }
            #na {
                display: flex;
                align-items: center;
            }
            .fa-heart {
                color: black;
            }
            .fa-heart.active {
                color: red;
            }
            .cmtcontent {
                display: flex;
                align-items: center;
            }

            .cmtimg img {
                width: 50px;
                height: 50px;
                border-radius: 50%;
            }

            .cmttext {
                margin-left: 10px;
            }

            .cmtname h6 {
                margin: 0;
            }

            .cmtbl {
                display: flex;
                align-items: center;
            }
             .optionP{
                display: flex;
                justify-content: center;
                align-items: center;
                width: 5%;
                height: 2em;
                position: absolute;
                top: 0;
                right: 0;
                border-radius: 50%;
            }
            .optionP:hover{
                background-color: #cccccc;
                color: black;
                cursor: pointer;
            }
            .optionP ion-icon{
                font-size: 20px;
            }
            .listOption{
                background-color: #333333;
                box-shadow: 0 0 5px black;
                border-radius: 10px;
                position: absolute;
                right: 0;
                top:2em;
            }
            .listOption a{
                display: inline-flex;
                text-decoration: none;
                color: white;
                padding: 1em 1em;
                width: 100%;
                line-height: 1em;
            }
            .listOption a:hover{
                background-color: #cccccc;
                border-radius: 10px;
                color: black;
            }
            .cmt{
                border-top: 1px solid black;
            }
            .cmtcontent{
                display: flex;
                width: 95%;
                height: auto;
                margin: 0.5em auto;
            }
            .lastcontent{
                position: relative;
            }
            .firstcontent{
                margin-bottom: 0.5em;
            }
            .cmtimg{
                width: 8%;
                height: 3em;
            }
            .cmtimg img{
                margin-top: 0.5em;
                width: 100%;
                height: 3em;
                border-radius: 50%;
            }
            .cmttext{
                margin: 0 auto;
                width: 90%;
                height: auto;
            }
            .cmtname{
                padding-top: 0.2em;
                height: 1.8em;
                width: 100%;
            }
            .cmtname h6{
                margin-bottom: 0;
            }

            .cmtbl{
                display: flex;
            }
            .cmtbl input{
                width: 92%;
                border-radius: 10px;
            }
            .cmtbl p{
                color: black;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <main id="main">
                <div class="container clearfix">
                    <div id="left-col">
                        <article class="the-article">
                            <div id="breadcrumb">
                                <ol class="clearfix">
                                    <li class="first link"><a href="homeController" title="Trang chủ"><span>Trang
                                                chủ</span></a></li>
                                    <li class="link last"><a href="listblog"
                                                             title="Tin tức"><span>Tin tức</span></a></li>
                                </ol>
                            </div>

                            <header class="page-header">
                                <h1 class="page-h1">${b.getTopic()}</h1>
                            <c:if test="${sessionScope.acc!=null}">
                                <div id="${na}">
                                    <a href="favouriteblog?blogId=${b.getId()}&userId=${sessionScope.acc.getId()}"><ion-icon name="heart"></ion-icon></a>
                                </div>
                            </c:if>
                            </header>
                        <div class="article-main-content">
                            <figure>
                                <img
                                    src="./assets/images/${b.getImage()}"
                                    alt="#" width="960" height="720">
                            </figure>
                            <p>${b.getContent()}</p>
                        </div>
                        <div class="cmt">
                            <h2 style="margin-left: 0.5em;">Bình luận</h2>
                            <c:forEach items="${lsC}" var="c">
                                <div class="cmtcontent lastcontent">
                                    <div class="cmtimg">
                                        <img src="./assets/images/${c.getProfilePicture()}" alt="alt"/>
                                    </div>
                                    <div class="cmttext">
                                        <div class="cmtname"><h6>${c.getFullName()}</h6></div>
                                        <div class="cmtbl">
                                            <form id="${c.getId()}f" method="get" action="editcommentblog">
                                                <input name="idBlog" value="${b.getId()}" type="hidden"/>
                                                <input name="id" value="${c.getId()}" type="hidden"/>
                                                <input name="v" id="${c.getId()}a" style="border: none" type="text" value="${c.getContent()}" readonly=""/>
                                            </form>
                                        </div>
                                    </div>
                                    <!-- Fix -->
                                    <c:if test="${c.getCommenterId() == sessionScope.acc.getId()}">
                                        <div class="optionP" onclick="showList('${c.getId()}')"> <i class="fas fa-ellipsis-h"></i></div>
                                    </c:if>
                                            <div id="${c.getId()}" class="listOption" style="display: none;">
                                                <div><a onclick="eidtcom('${c.getId()}a', '${c.getId()}')" href="#"><i class="far fa-edit"></i><span>Sửa</span></a></div>
                                                <div><a href="deletecommentblog?id=${c.getId()}&idBlog=${b.getId()}"><i class="fas fa-trash-alt"></i><span>Xóa</span></a></div>
                                            </div>
                                    </div>
                            </c:forEach>
                            <c:if test="${sessionScope.acc != null}">
                                <form action="commentblog" method="post">

                                    <input type="hidden" value="${b.getId()}" name="idBlog">
                                    <div class="cmtcontent firstcontent">
                                        <div class="cmtimg">
                                            <img src="./assets/images/${sessionScope.acc.getProfile_Picture()}" alt="alt"/>
                                        </div>
                                        <div class="cmttext">
                                            <div class="cmtname"><h6>${sessionScope.acc.getFullname()}</h6></div>
                                            <div class="cmtbl">
                                                <input type="text" name="ctxt">
                                                <input style="margin-left: 0.5em;width: 10%;cursor: pointer;background-color: #6699ff;color: white" type="submit" value="Gửi">
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </c:if>
                        </div>
                    </article>
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
        <script src="js/jquery/jquery-2.2.4.min.js"></script>
        <!-- Popper js -->
        <script src="js/popper.min.js"></script>
        <!-- Bootstrap js -->
        <script src="js/bootstrap.min.js"></script>
        <!-- Plugins js -->
        <script src="js/plugins.js"></script>
        <script src="js/classy-nav.min.js"></script>
        <script src="js/jquery-ui.min.js"></script>
        <!-- Active js -->
        <script src="js/active.js"></script>
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
                                            function eidtcom(x, s) {
                                                var input = document.getElementById(x);
                                                input.style.paddingLeft = '10px';
                                                input.removeAttribute("readonly");
                                                input.focus();
                                                showList(s);
                                            }
        </script>   
    </body>
</html>
