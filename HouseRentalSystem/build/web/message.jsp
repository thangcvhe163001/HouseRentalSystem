<%-- 
    Document   : message
    Created on : Jun 23, 2023, 4:21:36 PM
    Author     : win
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" type="text/css" rel="stylesheet">
        <link rel="stylesheet" href="assets/css/message.css">
        <style>
            .received_withd_msg{
                position: relative;
            }
            .sent_msg{
                position: relative;
            }
            .text-center{
                margin-top: 50px;
                margin-bottom: 20px;
            }
            .optionL{
                display: flex;
                justify-content: center;
                align-items: center;
                width: 8%;
                height: 2em;
                position: absolute;
                top: 0;
                left: 23em;
                border-radius: 50%;
                z-index: 99;
            }
            .optionL:hover{
                background-color: #cccccc;
                color: black;
                cursor: pointer;
            }
            .optionL ion-icon{
                font-size: 20px;
            }
            .optionR{
                display: flex;
                justify-content: center;
                align-items: center;
                width: 9%;
                height: 2em;
                position: absolute;
                top: 0;
                right: 21em;
                border-radius: 50%;
                z-index: 99;
            }
            .optionR:hover{
                background-color: #cccccc;
                color: black;
                cursor: pointer;
            }
            .optionR ion-icon{
                font-size: 20px;
            }
            .listOptionR{
                background-color: #333333;
                box-shadow: 0 0 5px black;
                border-radius: 10px;
                position: absolute;
                left: -3em;
                top:2.2em;
            }
            .listOptionR a{
                display: inline-flex;
                text-decoration: none;
                color: white;
                padding: 0.3em 0.3em;
                width: 100%;
                line-height: 1em;
            }
            .listOptionR a:hover{
                background-color: #cccccc;
                border-radius: 10px;
                color: black;
            }
            .listOptionL{
                background-color: #333333;
                box-shadow: 0 0 5px black;
                border-radius: 10px;
                position: absolute;
                right: -2.5em;
                top:2.2em;
            }
            .listOptionL a{
                display: inline-flex;
                text-decoration: none;
                color: white;
                padding: 0.3em 0.3em;
                width: 100%;
                line-height: 1em;
            }
            .listOptionL a:hover{
                background-color: #cccccc;
                border-radius: 10px;
                color: black;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <div class="container">
                <h3 class=" text-center">Nhắn tin</h3>
                <div class="messaging">
                    <div class="inbox_msg">
                        <div class="inbox_people">
                            <div class="headind_srch">
                                <div class="recent_heading">
                                    <h4>Trò chuyện</h4>
                                </div>
                                <div class="srch_bar">
                                    <form action="message" method="post">
                                    <div class="stylish-input-group">
                                        <input name="searchname" type="text" class="search-bar" value="${name}"  placeholder="Tìm kiếm" >
                                        <span class="input-group-addon">
                                            <button type="submit"> <i class="fa fa-search" aria-hidden="true"></i> </button>
                                        </span>
                                    </div>
                                    </form>
                                </div>
                            </div>
                            <div class="inbox_chat">
                            <c:forEach items="${lsMgr}" var="c">
                                <div class="chat_list ${c.getSender_ID()==activeMess?'active_chat':''}">
                                    <a href="message?receiverid=${c.getSender_ID()}">
                                        <div class="chat_people">
                                            <div class="chat_img"> <img src="assets/images/${c.getImage()}" alt="sunil"> </div>
                                            <div class="chat_ib">
                                                <h5>${c.getFullname()} <span class="chat_date">${c.getSent_Date()}</span></h5>
                                                <p>${c.getContent()}</p>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <form action="message" method="post">
                        <div class="mesgs">
                            <div class="msg_history">
                                <c:forEach items="${lsMsg}" var="m">
                                    <c:choose>
                                        <c:when test="${m.getSender_ID()==sessionScope.acc.getId()}">
                                            <c:if test="${m.isDeleted_By_Sender()==false}">
                                                <div class="outgoing_msg">
                                                    <div class="sent_msg">
                                                        <p>${m.getContent()}</p>
                                                        <span class="time_date"> ${m.getSent_Date()}</span> 
                                                        <div class="optionR" onclick="showList('${m.getId()}')"><ion-icon name="ellipsis-horizontal-outline"></ion-icon></div>
                                                        <div id="${m.getId()}" class="listOptionR" style="display: none;">
                                                            <div><a href="deletemessage?id=${m.getId()}&senderId=${m.getSender_ID()}&receiverId=${receiver.getId()}"><ion-icon name="trash-bin-outline"></ion-icon>Xóa</a></div>
                                                        </div>
                                                    </div>
                                                </div>

                                            </c:if>
                                        </c:when>
                                        <c:otherwise>
                                            <c:if test="${m.isDeleted_By_Receiver() == false}">
                                                <div class="incoming_msg">
                                                    <div class="incoming_msg_img"> <img src="assets/images/${receiver.getProfile_Picture()}" alt="sunil"> </div>
                                                    <div class="received_msg">
                                                        <div class="received_withd_msg">
                                                            <p>${m.getContent()}</p>
                                                            <span class="time_date"> ${m.getSent_Date()}</span>
                                                            <div class="optionL" onclick="showList('${m.getId()}')"><ion-icon name="ellipsis-horizontal-outline"></ion-icon></div>
                                                            <div id="${m.getId()}" class="listOptionL" style="display: none;">
                                                                <div><a href="deletemessage?id=${m.getId()}&senderId=${m.getSender_ID()}&receiverId=${receiver.getId()}"><ion-icon name="trash-bin-outline"></ion-icon>Xóa</a></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                            </c:if>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </div>
                            <div class="type_msg">
                                <div class="input_msg_write">
                                    <input type="hidden" name="receiverID" value="${receiver.getId()}"/>
                                    <input type="text" name="messcontent" class="write_msg" placeholder="Nhập tin nhắn" />
                                    <button class="msg_send_btn" type="submit"><i class="fas fa-paper-plane" aria-hidden="true"></i></button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <jsp:include page="footer.jsp"></jsp:include>
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
        <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
    </body>
</html>
