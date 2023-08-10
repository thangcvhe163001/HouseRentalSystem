<%-- 
    Document   : sidebar
    Created on : Jun 22, 2023, 1:37:43 AM
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
          <nav class="col-lg-2 d-none d-lg-block bg-light sidebar">
                    <div class="user_info">
                        <a href="#" class="clearfix">
                            <div class="user_avatar"><img src="./assets/images/${sessionScope.acc.getProfile_Picture()}"></div>
                            <div class="user_meta">
                                <div class="inner">
                                    <div class="user_name">${sessionScope.acc.fullname}</div>
                                    <div class="user_verify" style="color: #555; font-size: 0.9rem;">${sessionScope.acc.email}</div>
                                </div>
                            </div>
                        </a>
                        <ul>
                            <li><span>Mã thành viên:</span> <span style="font-weight: 700;">${sessionScope.acc.id}</span></li>
                        </ul>
                    </div>
                    <c:if test="${sessionScope.acc.getRole_ID() == 3}">
                    <ul class="nav nav-sidebar">
                        <li class="nav-item">
                            <a class="nav-link active" href="managehouse">
                                <svg xmlns="" width="16" height="16" viewBox="0 0 24 24"
                                    fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                    stroke-linejoin="round" class="feather feather-file-text">
                                    <path d="M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z"></path>
                                    <polyline points="14 2 14 8 20 8"></polyline>
                                    <line x1="16" y1="13" x2="8" y2="13"></line>
                                    <line x1="16" y1="17" x2="8" y2="17"></line>
                                    <polyline points="10 9 9 9 8 9"></polyline>
                                </svg>
                                Quản lý tin đăng
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link " href="changeinformation">
                                <svg xmlns="" width="16" height="16" viewBox="0 0 24 24"
                                    fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                    stroke-linejoin="round" class="feather feather-edit">
                                    <path d="M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7"></path>
                                    <path d="M18.5 2.5a2.121 2.121 0 0 1 3 3L12 15l-4 1 1-4 9.5-9.5z"></path>
                                </svg>
                                Sửa thông tin cá nhân
                            </a>
                        </li>
<!--                        <li class="nav-item">
                            <a class="nav-link" href="">
                                <svg xmlns="" width="16" height="16" viewBox="0 0 24 24"
                                    fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                    stroke-linejoin="round" class="feather feather-message-circle">
                                    <path
                                        d="M21 11.5a8.38 8.38 0 0 1-.9 3.8 8.5 8.5 0 0 1-7.6 4.7 8.38 8.38 0 0 1-3.8-.9L3 21l1.9-5.7a8.38 8.38 0 0 1-.9-3.8 8.5 8.5 0 0 1 4.7-7.6 8.38 8.38 0 0 1 3.8-.9h.5a8.48 8.48 0 0 1 8 8v.5z">
                                    </path>
                                </svg>
                                Liên hệ
                            </a>
                        </li>-->
                        <li class="nav-item">
                            <a class="nav-link js-user-logout" href="logout">
                                <svg xmlns="" width="16" height="16" viewBox="0 0 24 24"
                                    fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                    stroke-linejoin="round" class="feather feather-log-out">
                                    <path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4"></path>
                                    <polyline points="16 17 21 12 16 7"></polyline>
                                    <line x1="21" y1="12" x2="9" y2="12"></line>
                                </svg>
                                Thoát
                            </a>
                        </li>
                    </ul>
                     </c:if>
                        <c:if test="${sessionScope.acc.getRole_ID() == 2}">
                    <ul class="nav nav-sidebar">
                        <li class="nav-item">
                            <a class="nav-link active " href="changeinformation">
                                <svg xmlns="" width="16" height="16" viewBox="0 0 24 24"
                                    fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                    stroke-linejoin="round" class="feather feather-edit">
                                    <path d="M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7"></path>
                                    <path d="M18.5 2.5a2.121 2.121 0 0 1 3 3L12 15l-4 1 1-4 9.5-9.5z"></path>
                                </svg>
                                Sửa thông tin cá nhân
                            </a>
                        </li>
<!--                        <li class="nav-item">
                            <a class="nav-link" href="">
                                <svg xmlns="" width="16" height="16" viewBox="0 0 24 24"
                                    fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                    stroke-linejoin="round" class="feather feather-message-circle">
                                    <path
                                        d="M21 11.5a8.38 8.38 0 0 1-.9 3.8 8.5 8.5 0 0 1-7.6 4.7 8.38 8.38 0 0 1-3.8-.9L3 21l1.9-5.7a8.38 8.38 0 0 1-.9-3.8 8.5 8.5 0 0 1 4.7-7.6 8.38 8.38 0 0 1 3.8-.9h.5a8.48 8.48 0 0 1 8 8v.5z">
                                    </path>
                                </svg>
                                Liên hệ
                            </a>
                        </li>-->
                        <li class="nav-item">
                            <a class="nav-link js-user-logout" href="logout">
                                <svg xmlns="" width="16" height="16" viewBox="0 0 24 24"
                                    fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                    stroke-linejoin="round" class="feather feather-log-out">
                                    <path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4"></path>
                                    <polyline points="16 17 21 12 16 7"></polyline>
                                    <line x1="21" y1="12" x2="9" y2="12"></line>
                                </svg>
                                Thoát
                            </a>
                        </li>
                    </ul>
                     </c:if>
                </nav>
    </body>
</html>
