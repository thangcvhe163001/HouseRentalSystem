<%-- 
    Document   : login
    Created on : Jun 15, 2023, 2:41:11 AM
    Author     : win
--%>

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
                    <h1 class="section-title big">Đăng nhập</h1>
                </div>
                <div class="section-content">
                    <form class="form-access login-form js-login-form clearfix" action="login" method="POST">
                        <div class="form-group form-group-phone">
                            <label for="email">Email</label>
                            <input type="text" class="form-control" id="email" name="email">
                            <div id="email-error" class="error-message" style="color: red;"></div>
                        </div>

                        <script>
                            var inputEmail = document.getElementById('email');
                            var errorSpan = document.getElementById('email-error');


                            inputEmail.addEventListener('blur', function () {
                                var email = inputEmail.value;
                                 var emailRegex = /^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]{2,7}$/;

                                if (!emailRegex.test(email)) {
                                    errorSpan.textContent = 'Email không hợp lệ.';
                                } else {
                                    errorSpan.textContent = '';
                                }
                            });
                        </script>

                        <div class="form-group form-group-password">
                            <label for="password">Mật khẩu</label>
                            <input type="password" class="form-control" id="password" name="password" >
                            <div class="error-message" style="color: red;"></div>
                        </div>

                        <script>
                            document.querySelector('form').addEventListener('submit', function (event) {
                                var passwordInput = document.getElementById('password');
                                var password = passwordInput.value.trim();
                                var errorDiv = passwordInput.nextElementSibling;

                                if (password === '') {
                                    errorDiv.textContent = 'Vui lòng nhập mật khẩu.';
                                    event.preventDefault();
                                } else {
                                    errorDiv.textContent = '';
                                }
                            });
                        </script>

                        <div class="form-group">
                            <p style="color: red">${requestScope.mess}</p>
                        <button type="submit" name="signin" id="signin" class="btn btn-submit">Đăng nhập</button></div>
                    <div class="form-group clearfix">
                        <a href="forgotpassword">Bạn quên mật khẩu?</a>
                        <a style="float: right;" href="register">Tạo tài khoản mới</a></div>
                </form>
            </div>
        </section>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
