<%-- 
    Document   : header_sidebar
    Created on : Jun 22, 2023, 2:01:43 AM
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
        <nav class="navbar navbar-expand-md fixed-top flex-md-nowrap p-0" id="mobile_navbar_top">
            <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="homeController">House Rental System</a>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link" target="_blank" href="homeController">Trang chủ</a>
                    </li>
                    <li class="nav-item d-none d-md-block">
                        <a class="nav-link" target="_blank" href="homeController?categoryID=1">Phòng trọ</a>
                    </li>
                    <li class="nav-item d-none d-md-block">
                        <a class="nav-link" target="_blank" href="homeController?categoryID=2">Chung cư</a>
                    </li>
                    <li class="nav-item d-none d-md-block">
                        <a class="nav-link" target="_blank" href="homeController?categoryID=3">Biệt thự</a>
                    </li>
                    <li class="nav-item d-none d-md-block">
                        <a class="nav-link" target="_blank" href="homeController?categoryID=4">Căn hộ</a>
                    </li>
                </ul>
            </div>
        </nav>
    </body>
</html>
