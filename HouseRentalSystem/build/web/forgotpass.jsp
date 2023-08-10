<%-- 
    Document   : forgotpass
    Created on : Jul 14, 2023, 3:24:07 PM
    Author     : win
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <section class="section section-access">
                <div class="section-header">
                    <h1 class="section-title big">Quên mật khẩu</h1>
                </div>
                <div class="section-content">
                    <form class="form-access login-form js-login-form clearfix" action="forgotpassword" method="POST" id="frm">
                        <div class="form-group form-group-phone">
                            <label for="email">Email</label>
                            <input type="text" class="form-control" value="${email}" id="email" name="email">
                        <div id="email-error" class="error-message" style="color: red;"></div>
                    </div>
                    <div class="form-group form-group-password">
                        <label for="password">Mật khẩu mới</label>
                        <input value="${newPass}" type="password" class="form-control" id="password" name="newPass" >
                        <div class="error-message" style="color: red;"></div>
                    </div>
                    <div class="form-group form-group-password">
                        <label for="password1">Xác nhận mật khẩu</label>
                        <input value="${confirmPass}" type="password" class="form-control" id="password1" name="confirmPass" >
                        <div class="error-message" style="color: red;"></div>
                    </div>
                    <div class="form-group form-group-password"><label for="">Câu hỏi bảo mật</label>
                        <select onchange="change()" style="border-radius: 10px" name="question">
                            <c:forEach items="${lsQF}" var="q">
                            <c:if test="${idQ!=null}">
                                <option value="${q.getId()}" ${q.getId() == idQ ? "selected" : ""}>${q.getQuestion()}</option>
                            </c:if>
                            <c:if test="${idQ==null}">
                                <option value="${q.getId()}">${q.getQuestion()}</option>
                            </c:if>
                        </c:forEach>
                        </select>
                    </div>
                    <div class="form-group form-group-password"><label for="">Câu trả lời bảo mật</label>
                        <select required="" style="width: 150px ; border-radius: 10px; padding-left: 10px " name="answer">
                            <c:forEach items="${lsA}" var="a">
                            <option value="${a.getId()}">${a.getAnswer()}</option>
                        </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <button type="submit" name="forgot" class="btn btn-submit" value="1">Xác nhận</button>
                    </div>
                </form>
            </div>
        </section>
        <jsp:include page="footer.jsp"></jsp:include>
        <script>
            function change() {
                var a = document.getElementById('frm').submit();
            }
        </script>
    </body>
</html>
