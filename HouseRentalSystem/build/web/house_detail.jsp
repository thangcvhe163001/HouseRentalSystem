<%-- 
    Document   : house_detail
    Created on : Jun 15, 2023, 4:41:43 PM
    Author     : win
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <style>
        .messcontain{
            position: fixed;
            right: 6em;
            bottom: 0;
            width: 22%;
            height: 25em;
            border: 1px solid black;
            background-color: #333333;
            z-index: 99;
            border-top-left-radius: 10px;
            border-top-right-radius: 10px;
        }
        #hdmess ion-icon{
            color: white;
            font-size: 2em;

        }
        #hdmess #cls ion-icon:hover{
            color: black;
            background-color: gray;
            cursor: pointer;
            border-radius: 50%;
        }
        #banmess p{
            color: white;
            margin-bottom: 0.5em;
            padding-left: 0.2em;
        }
        #banmess p span{
            padding: 0.5em;
        }
        .msl{
            background-color: #818281;
            border-radius: 10px;
            width: 70%;
        }
        .msr{
            background-color: #f72f57;
            border-radius: 10px;
            width: 70%;
            margin-left: 5em;
        }
        #sn{
            display: grid;
            place-items: center;
            cursor: pointer;
        }
        #sn ion-icon{
            color: #f72f57;
            font-size: 22px;
        }
        #ftmess{
            margin-right: 0.5em;
        }
        #ftmess input{
            padding-left: 0.5em;
            width: 100%;
            height: 100%;
            border-radius: 10px;
        }
        .listimg img{
            width: 100%;
            height: 85%;
            object-fit: cover;
            border-radius: 10px;
        }
        .inforuser{
            text-align: center;
            width: 30%;
            height: 50em;
        }
        .btnc{
            height: 3em;
            display: flex;
            justify-content: center;
        }
        .btnc input{
            padding: 0;
            text-align: center;
            padding: 0 1em;
            background-color: #1E90FF;
            border-radius: 10px;
            color: white;

        }
        #lsStar{
            display: none;
            position: absolute;
            top: -2.0em;
            background-color: #f1f1f1;
            box-shadow: 0 0 1px black;
        }
        #lsStar ul{
            display: flex;
        }
        #lsStar ul li{
            margin: 0;
            padding: 0;
        }
        #lsStar ul li a{
            font-size: 17px;
            padding: 0.2em 0.5em;
            font-weight: normal;
            display: block;
        }
        #vote{
            position: relative;
        }
        #noteVote{
            font-weight: 500;
            font-family: sans-serif;
            cursor: pointer;
            color: #1E90FF;
        }
        #vote span{
            font-weight: 500;
            font-family: sans-serif;
            cursor: pointer;
            font-weight: 600;
        }
        #activevote{
            background-color: #ddd;
            color: #16c784;
            cursor: default;
        }
        #lsStar ul li .inactivevote:hover{
            background-color: #ddd;
        }
        #vote:hover span{
            font-weight: 600;
            font-family: sans-serif;
            cursor: pointer;
            color: #1E90FF;
        }
        #vote:hover #lsStar{
            display: block;
        }
        .list-comment-2020 .item-comment-2020:first-child {
            padding-top: 0;
        }
        .list-comment-2020 .item-comment-2020 {
            display: flex;
            padding: 20px 0;
            border-bottom: 1px solid #f3f3f3;
        }
        .list-comment-2020 .item-comment-2020 .first-letter {
            float: left;
            width: 64px;
            height: 64px;
            background: #ddd;
            text-align: center;
            color: #666;
            text-transform: uppercase;
            font-size: 20px;
            line-height: 64px;
            font-weight: 600;
            text-shadow: 1px 1px 0 rgba(255,255,255,.2);
            border-radius: 9999px;
        }
        .user-info {
            display: flex;
            flex-direction: column;
            -webkit-box-align: center;
            align-items: center;
            width: 180px;
            padding: 5px 10px;
        }
        .cmtimg{
            width: 40%;
            height: 3em;
            margin-bottom: 10px;
        }
        .cmtimg img{
            margin-top: 0.5em;
            width: 100%;
            height: 3em;
            border-radius: 50%;
        }
    </style>

    <body>
        <form method="post" action="messages" id="sen">
            <input type="hidden" name="receiverId" value="${finforowner.getId()}"/>
            <input type="hidden" name="houseId" value="${houseId}"/>
            <div class="messcontain" id="ofmess" style="display: ${showM==null?"none":"block"}">
                <div id="hdmess" style="padding: 0.3em;width: 100%;height: 11%;border-bottom: 1px solid #5c5c5c;display: flex">
                    <div style=" width:13%; height:100%"><img style="border-radius: 50%" src="./assets/images/${finforowner.getProfilePicture()}" alt="alt"/></div>
                    <div style="width: 77%;height: 100%"><p style="color:white;font-weight: 600;margin-top: 5px;">${finforowner.getFullName()}</p></div>
                    <div id="cls" style="width: 10%;height: 100%;" onclick="disMess()"><ion-icon name="close-outline"></ion-icon></div>
                </div>
                <div id="banmess" style="padding: 0.5em;height: 78%;width: 100%;overflow-x: hidden">
                    <c:forEach items="${lsM}" var="m">
                        <c:choose>
                            <c:when test="${m.getSender_ID()==sessionScope.acc.getId()}">
                                <c:if test="${m.isDeleted_By_Sender() == false}">
                                    <div class=${m.getSender_ID() == sessionScope.acc.getId()?"msr":"msl"}><p><span>${m.getContent()}</span></p></div>
                                </c:if>
                            </c:when>
                            <c:otherwise>
                                <c:if test="${m.isDeleted_By_Receiver() == false}">
                                    <div class=${m.getSender_ID() == sessionScope.acc.getId()?"msr":"msl"}><p><span>${m.getContent()}</span></p></div>
                                </c:if>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </div>
                <div id="ftmess" style="display: flex;height: 11%;width: 100%;padding: 0.3em 0;padding-left: 0.2em;">
                    <div style="width: 88%"><input type="text" name="inbox"></div>
                    <div style="width: 10%" id="sn" onclick="submitForm()"><ion-icon name="send"></ion-icon></div>
                </div>
            </div> 
        </form>
        <jsp:include page="header.jsp"></jsp:include>
            <div id="webpage">
                <main id="main">
                    <div class="container clearfix">
                        <div id="breadcrumb">
                            <ol class="clearfix">
                                <li class="first link"><a href="homeController"
                                                          title="Cho thuê phòng trọ"><span>Trang chủ</span></a></li>
                            </ol>
                        </div>
                        <div id="left-col">
                            <article class="the-post tin-vip vipnoibat">
                                <div class="post-images">
                                    <div class="images-swiper-container swiper-container-horizontal">
                                        <img style="width: 500px; margin: auto; height: 100%" id="sliderimg" src="${firstImg}" alt="alt">
                                    <img id="sliderimg" src="${firstImg}" alt="alt" style="width: 100%;">
                                    <c:if test="${!imgPath.isEmpty()}">
                                        <div class="swiper-button-prev" tabindex="0" type="button" aria-label="Previous slide" onclick="prevImage()"><
                                        </div>
                                        <div class="swiper-button-next" tabindex="0" role="button" aria-label="Next slide" onclick="nextImage()">>
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                            <header class="page-header">
                                <h1 class="page-h1"> <span style="color: #E13427;" class="star star-5"><i style="color: #e4f03c;" class="fas fa-star"></i>${fhouse.getRating()}</span>
                                    <a style="color: #E13427;" href="">${fhouse.getTitle()}</a>
                                </h1>
                                <br/>
                                <div class="clearfix">
                                    <address class="post-address"><i class="fas fa-map-marker-alt" style="margin-right: 10px"></i>Địa chỉ: ${fhouse.getFull_Address()}</address> 
                                </div>
                                <div class="post-attributes">
                                    <div class="item price"><i></i><span
                                            style="color: #16c784; font-weight: bold; font-size: 1.5rem">${fhouse.getPrice()/1000000}triệu/tháng</span></div>
                                    <div class="item acreage"><i class="fas fa-vector-square"></i><span> ${fhousedetail.getArea()}m<sup>2</sup></span></div>
                                    <div class="item acreage"><i class="fas fa-bed"></i><span> ${fhousedetail.getNumber_Of_Bedrooms()}</span></div>
                                    <div class="item acreage"><i class="fas fa-bath"></i><span> ${fhousedetail.getNumber_Of_Bathrooms()}</span></div>
                                    <div class="item acreage"><i class="fas fa-swimming-pool"></i><span> ${fhousedetail.getPool()}</span></div>
                                    <div class="item acreage"><i class="fas fa-compass"></i><span> ${fhousedirection.getDirection()}</span></div>
                                </div>
                            </header>
                            <section class="section post-main-content">
                                <div class="section-header">
                                    <h2 class="section-title">Thông tin mô tả</h2>
                                </div>
                                <div class="section-content">
                                    <p>${fhouse.getDescription()}</p>
                                </div>
                            </section>
                            <section class="section post-overview">
                                <div class="section-header">
                                    <h3 class="section-title">Đặc điểm tin đăng</h3>
                                </div>
                                <div class="section-content">
                                    <table class="table">
                                        <tbody>
                                            <tr>
                                                <td class="name">Mã tin:</td>
                                                <td>#${fhouse.getId()}</td>
                                            </tr>
                                            <tr>
                                                <td class="name">Quận:</td>
                                                <td>${fdistrict.getDistrict()}</td>
                                            </tr>
                                            <tr>
                                                <td class="name">Loại nhà:</td>
                                                <td>${fhousecategory.getCategory()}</td>
                                            </tr>
                                            <tr>
                                                <td class="name">Đối tượng thuê:</td>
                                                <td>Tất cả</td>
                                            </tr>
                                            <tr>
                                                <td class="name">Ngày đăng:</td>
                                                <td><time>${fhouse.getAdded_Date()}</time>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </section>
                            <section class="section post-contact">
                                <div class="section-header">
                                    <h3 class="section-title">Thông tin liên hệ</h3>
                                </div>
                                <div class="section-content">
                                    <table class="table">
                                        <tbody>
                                            <tr>
                                                <td class="name">Liên hệ:</td>
                                                <td> ${finforowner.getFullName()} </td>
                                            </tr>
                                            <tr>
                                                <td class="name">Điện thoại:</td>
                                                <td> ${finforowner.getPhoneNumber()} </td>
                                            </tr>
                                            <tr>
                                                <td class="name">Email:</td>
                                                <td> ${finforowner.getEmail()} </td>
                                            </tr>
                                            <tr>
                                                <td class="name">Địa chỉ:</td>
                                                <td> ${finforowner.getAddress()} </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </section>
                            <c:if test="${(sessionScope.acc!=null)&&(sessionScope.acc.getId() != fhouse.getHouse_Owner_ID())}">
                                <div id="vote" style="margin-bottom: 2em;margin-top: 3em;">
                                    <span id="${hr!=null?'noteVote':''}">Đánh giá</span>
                                    <div id="lsStar">
                                        <ul>
                                            <c:forEach begin="1" end="5" step="1" var="c">
                                                <c:choose>
                                                    <c:when test="${(hr.getVote_Id() == sessionScope.acc.getId())&&(c == hr.getRating())}">
                                                        <li><a id="activevote" href="#">${c} <ion-icon name="star"></ion-icon></a></li>
                                                                </c:when>
                                                                <c:otherwise>
                                                        <li><a class="inactivevote" href="houseratings?rating=${c}&houseid=${fhouse.getId()}">${c} <ion-icon name="star"></ion-icon></a></li>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                        </ul>
                                    </div>
                                </div>
                            </c:if>
                            <section class="section post-map">
                                <div id="comment">
                                    <h2>Bình luận</h2>
                                    <c:if test="${sessionScope.acc != null}">
                                        <div class="box-comment-2020">
                                            <textarea style="width: 100%;height: 100px" id="comment_reply_content_0" placeholder="Mời bạn để lại bình luận..." name="content"></textarea>
                                            <div class="actions-comment-2020 js-actions-comment-2020">
                                                <div class="actions-comment-wrap">
                                                    <div class="cmt_right float-right" style="float: right">
                                                        <button style="text-decoration: none" type="button" class="btn btn-success" onclick="reviewReply()">Gửi bình luận</button>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>
                                    </c:if>

                                    <div class="list-comment-2020" style="margin-top: 8%">
                                        <div id="js-show-comment-list">
                                            <c:forEach items="${requestScope.feedbacks}" var="i">
                                                <div class="item-comment-2020">
                                                    <div class="user-info">
                                                        <div class="cmtimg">
                                                            <img src="./assets/images/${i.sender.getProfile_Picture()}">
                                                        </div>
                                                        <span class="ten-khach-hang">${i.sender.getFullname()}</span>              
                                                        <span class="comment-time"><fmt:formatDate value="${i.getFeedback_Date()}" pattern="dd/MM/yyyy" /></span>
                                                    </div>
                                                    <div style="margin-top: 4%;display: flex;width: 100%" class="information row">
                                                        <textarea onkeypress="UpdateComment(event,${i.getId()})" id="feedback${i.getId()}" disabled="" class="comment-content-2020" style="width: 80%;height: 100%;margin-right: 2%">${i.getContent()}</textarea>
                                                        <c:if test="${sessionScope.acc != null && i.getSender_ID() == sessionScope.acc.getId()}">
                                                            <button style="height: 40%;font-size:24px;margin-right: 2%;cursor: pointer" onclick="window.location.href = '${pageContext.request.contextPath}/deletefeedbackhouse?id=${i.getId()}&houseid=${requestScope.fhouse.getId()}'"><i class="fa fa-trash"></i></button>
                                                            <button style="height: 40%;font-size:24px;margin-right: 2%;cursor: pointer" onclick="editComment('feedback${i.getId()}')"><i class="fa fa-edit"></i></button>
                                                            </c:if>
                                                    </div>
                                                </div>            
                                            </c:forEach>                   
                                        </div>
                                        <div id="js-show-comment-paging" style="text-align: center; margin: 20px auto;">
                                            <c:forEach begin="1" end="${requestScope.totalPage}" var="i">
                                                <a style="${requestScope.pageNum == i ? 'color : red' : ''}" href="detailhouse?id=${requestScope.fhouse.getId()}&pageNum=${i}">${i}</a>
                                            </c:forEach>
                                        </div>
                                    </div>

                                </div>
                            </section>
                        </article>
                    </div>
                    <aside id="aside">
                        <div class="author-aside">
                            <figure class="author-avatar"><img src="./assets/images/${finforowner.getProfilePicture()}">
                            </figure><span class="author-name">${finforowner.getFullName()}</span>
                            <div style="min-height: 16px; margin-bottom: 10px;">
<!--                                <div class="author-online-status online" author_id="29210"><i></i><span>Đang hoạt
                                        động</span></div>-->
                            </div>
                            <a class="btn author-phone" rel="nofollow" href="tel:${finforowner.getPhoneNumber()}"><i class="fas fa-phone-alt"></i> ${finforowner.getPhoneNumber()}</a>
                            <c:if test="${(sessionScope.acc!=null)&&sessionScope.acc.getId() != finforowner.getId()}">
                                <div class="btn author-zalo" id="butmess" onclick="disMess()">
                                    <span><i class="fab fa-facebook-messenger"></i>Nhắn tin</span>
                                </div>
                            </c:if>
                            <c:if test="${(sessionScope.acc==null)||(sessionScope.acc.getId() == finforowner.getId())}">
                                <div class="btn author-zalo" id="butmess">
                                    <a style="color: black" href="login"><span><i class="fab fa-facebook-messenger"></i>Nhắn tin</span></a>
                                </div>
                            </c:if>
                            <div id="butfa">
                                <c:if test="${sessionScope.acc != null && heart eq 'noactiveHeart'}">
                                    <span onclick="window.location.href = '${pageContext.request.contextPath}/changeStatus?id=${requestScope.fhouse.getId()}&uid=${sessionScope.acc.getId()}'" class="btn post-save js-btn-save saved"><i style="color: gray" class="fas fa-heart"></i>Yêu thích</span>
                                </c:if>
                                <c:if test="${sessionScope.acc != null && heart eq 'activeHeart'}">
                                    <span onclick="window.location.href = '${pageContext.request.contextPath}/changeStatus?id=${requestScope.fhouse.getId()}&uid=${sessionScope.acc.getId()}'" class="btn post-save js-btn-save saved"><i style="color: red" class="fas fa-heart"></i>Yêu thích</span>
                                </c:if>
                                <c:if test="${sessionScope.acc==null}">
                                    <a class="btn post-save js-btn-save saved" href="login"><h4 id="${heart}"><i class="fas fa-heart"></i>Yêu thích</h4> </a>
                                </c:if>
                            </div>
                        </div>
                        <section class="section section-aside-tinmoidang">
                            <div class="section-header"><span class="section-title">Tin nổi bật</span></div>
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
                    </aside>
                </div>
            </main>

        </div>
        <script type="text/javascript">
            function reviewReply() {
                $.ajax({
                    type: 'POST',
                    url: "${pageContext.request.contextPath}/feedbackhouse",
                    data: {
                        content: $('#comment_reply_content_0').val(),
                        houseid: ${requestScope.fhouse.getId()}
                    },
                    success: function (data, textStatus, jqXHR) {
                        location.reload();
                        var list = JSON.parse(data);
                        var html = '';
                        var id = ${sessionScope.acc.getId()};
                        var size = list.length > 5 ? 5 : list.length;
                        for (let idx = 0; idx < size; idx++) {
                            html += '<div class="item-comment-2020">';
                            html += '<div class="user-info">';
                            html += '<div class="first-letter js-first-letter loaded">c</div>';
                            html += '<span class="ten-khach-hang">' + list[idx].sender.Fullname + '</span>';
                            html += '<span class="comment-time">' + list[idx].Feedback_Date + '</span>';
                            html += '</div>';
                            html += '<div style="margin-top: 4%;display: flex;width: 100%" class="information">';
                            html += '<textarea disabled="" id="feedback' + list[idx].Id + '" class="comment-content-2020" style="width: 80%;height: 100%;margin-right: 2%">' + list[idx].Content + '</textarea>';
                            if (id === Number(list[idx].Sender_ID)) {
                                var url = '${pageContext.request.contextPath}/deletefeedbackhouse?id=' + list[idx].Id + '&houseid=' + list[idx].House_ID;
                                html += '<button style="height: 40%;font-size:24px;margin-right: 2%;cursor: pointer" onclick="window.location.href = \'' + url + '\'"><i class="fa fa-trash"></i></button>';
                                html += '<button style="height: 40%;font-size:24px;margin-right: 2%" onclick="editComment(\'feedback' + list[idx].Id + '\')"><i class="fa fa-edit"></i></button>';
                            }
                            html += '</div>';
                            html += '</div>';
                        }
                        $('#js-show-comment-list').html(html);
                        var totalPage = list.length % 5 === 0 ? list.length / 5 : (list.length / 5 + 1);
                        var paging = '';
                        for (let idx = 1; idx <= totalPage; idx++) {
                            if (idx === 1) {
                                paging += '<a style="color:red" href="detailhouse?id=' + list[0].House_ID + '&pageNum=' + idx + '">' + idx + '</a>';
                            } else {
                                paging += '<a href="detailhouse?id=' + list[0].House_ID + '&pageNum=' + idx + '">' + idx + '</a>';
                            }
                        }
                        $('#js-show-comment-paging').html(paging);
                        $('#comment_reply_content_0').val('')
                    }
                })
            }

            function editComment(id) {
                $('#' + id).prop("disabled", false);
            }

            function UpdateComment(e, i) {
                if (e.which === 13) {
                    var pageNum = ${requestScope.pageNum};
                    $.ajax({
                        type: 'POST',
                        url: "${pageContext.request.contextPath}/editfeedbackhouse",
                        data: {
                            id: i,
                            content: $('#feedback' + i).val(),
                            houseid: ${requestScope.fhouse.getId()}
                        },
                        success: function (data, textStatus, jqXHR) {
                            $('#feedback' + i).attr("disabled", true);
                            var list = JSON.parse(data);
                            var html = '';
                            var id = ${sessionScope.acc.getId()};
                            var size = pageNum * 5;
                            for (let idx = (pageNum - 1) * 5; idx < size; idx++) {
                                html += '<div class="item-comment-2020">';
                                html += '<div class="user-info">';
                                html += '<div class="first-letter js-first-letter loaded">c</div>';
                                html += '<span class="ten-khach-hang">' + list[idx].sender.Fullname + '</span>';
                                html += '<span class="comment-time">' + list[idx].Feedback_Date + '</span>';
                                html += '</div>';
                                html += '<div style="margin-top: 4%;display: flex;width: 100%" class="information">';
                                html += '<textarea disabled="" id="feedback' + list[idx].Id + '" class="comment-content-2020" style="width: 80%;height: 100%;margin-right: 2%">' + list[idx].Content + '</textarea>';
                                if (id === Number(list[idx].Sender_ID)) {
                                    var url = '${pageContext.request.contextPath}/deletefeedbackhouse?id=' + list[idx].Id + '&houseid=' + list[idx].House_ID;
                                    html += '<button style="height: 40%;font-size:24px;margin-right: 2%;cursor: pointer" onclick="window.location.href = \'' + url + '\'"><i class="fa fa-trash"></i></button>';
                                    html += '<button style="height: 40%;font-size:24px;margin-right: 2%" onclick="editComment(\'feedback' + list[idx].Id + '\')"><i class="fa fa-edit"></i></button>';
                                }
                                html += '</div>';
                                html += '</div>';
                            }
                            $('#js-show-comment-list').html(html);
                            var totalPage = list.length % 5 === 0 ? list.length / 5 : (list.length / 5 + 1);
                            var paging = '';
                            for (let idx = 1; idx <= totalPage; idx++) {
                                if (idx === pageNum) {
                                    paging += '<a style="color:red" href="detailhouse?id=' + list[0].House_ID + '&pageNum=' + idx + '">' + idx + '</a>';
                                } else {
                                    paging += '<a href="detailhouse?id=' + list[0].House_ID + '&pageNum=' + idx + '">' + idx + '</a>';
                                }
                            }
                            $('#js-show-comment-paging').html(paging);
                        }
                    })
                }
            }
        </script>
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
            var imagePaths = [];
            imagePaths = '${imgPath}'.split(',');
            var slider = document.getElementById('sliderimg');
            var currentIndex = 0;
            function changeImage() {
                slider.src = "./assets/images/" + imagePaths[currentIndex];
            }
            function nextImage() {
                currentIndex++;
                if (currentIndex >= imagePaths.length) {
                    currentIndex = 0;
                }
                changeImage();
            }
            function prevImage() {
                currentIndex--;
                if (currentIndex < 0) {
                    currentIndex = imagePaths.length - 1;
                }
                changeImage();
            }
            function disMess() {
                var m = document.getElementById('ofmess');
                if (m.style.display === 'none') {
                    m.style.display = 'block';
                } else {
                    m.style.display = 'none';
                }
            }
            function submitForm() {
                document.getElementById('sen').submit();
            }
        </script>
    </body>
</html>
