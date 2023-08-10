<%-- 
    Document   : register
    Created on : Jun 21, 2023, 12:17:11 AM
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
                    <h1 class="section-title big">Tạo tài khoản mới</h1>
                </div>
                <div class="section-content">
                    <form class="form-access register-form js-register-form clearfix" id="frm" action="register" method="POST">
                        <div class="form-group form-group-fullname"><label for="email">Email</label>
                            <input type="text" class="form-control" min-length="3"  name="email" value="${email}"/>
                        <p style="color: red">${alertE}</p>
                    </div>
                    <div class="form-group form-group-phone"><label for="phone">Số điện thoại</label>
                        <input
                            type="number" class="form-control" min-length="10" max-length="11" 
                            name="phonenumber" value="${phone}"/>
                    </div>
                    <div class="form-group form-group-password"><label for="pass">Tạo mật khẩu</label><input
                            type="password" class="form-control" min-length="6" id="pass" name="pass"
                            value="${password}"/>
                        <p style="color: red">${alertP}</p>
                    </div>
                    <div class="form-group form-group-password"><label for="repass">Tạo lại mật khẩu</label><input
                            type="password" class="form-control" min-length="6" id="repass" name="repass"
                            value="${repassword}"/>
                    </div>
                    <div class="form-group form-group-password"><label for="fullname">Họ và tên</label><input
                            type="text" class="form-control" id="fullname" name="fullname"
                            value="${fullname}"/>
                        <p style="color: red">${alertF}</p>
                    </div>
                    <div class="form-group form-group-password"><label for="adress">Địa chỉ </label><input
                            type="text" class="form-control" id="adress" name="adress"
                            value="${address}"/>
                    </div>
                    <div class="form-group row"><label  class="label-title">Giới tính</label>
                        <div class="col-sm-10">
                            <div class="radio-group row post_cat_group section-support">
                                <label class="col-md-2 col-sm-12 col-xs-12 clearfix">
                                    <div class="form-group">
                                        <label for="male" class="css-label">
                                            <input id="male" type="radio" name="gender" class="css-checkbox" value="1"> Nam
                                        </label>
                                    </div>
                                </label>
                                <label class="col-md-2 col-sm-12 col-xs-12 clearfix">
                                    <div class="form-group">
                                        <label for="female" class="css-label">
                                            <input id="female" type="radio" name="gender" class="css-checkbox" value="0"> Nữ
                                        </label>
                                    </div>
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group form-group-password"><label for="">Câu hỏi bảo mật</label>
                        <select onchange="change()" style="border-radius: 10px" name="question">
                            <c:forEach items="${lsQ}" var="q">
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
                    <div class="form-group form-group-password">
                        <label for="">Ảnh đại diện</label>
                        <input type="file" name="file" value="${picture}" size="60" required="">
                    </div>
                    <div style="text-align: left" class="form-group"><label class="label-title">Loại tài khoản</label>
                        <div class="col-sm-10">
                            <div class="radio-group row post_cat_group section-support ">
                                <c:forEach items="${lsR}" var="r">
                                    <label class="col-md-2 col-sm-12 col-xs-12 support-item">
                                        <div class="form-group">
                                            <label for="${r.getId()}" class="css-label"><input
                                                    type="radio" name="role" required=""
                                                    class="css-checkbox" id="${r.getId()}" value="${r.getId()}"> ${r.getRole()}
                                            </label>
                                        </div>
                                    </label>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <button type="submit" name="signup" class="btn btn-submit">Tạo tài khoản</button>
                    </div>
                    <div class="form-group">
                        <p style="padding: 5px 0;">Bạn đã có tài khoản? 
                            <a class="link" href="login">Đăng nhập ngay</a></p>
                    </div><input type="hidden" name="redirect" value="">
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
