<%-- 
    Document   : postblog
    Created on : May 27, 2023, 1:54:10 PM
    Author     : win
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <style>
            .centered {
                display: flex;
                justify-content: center;
                align-items: flex-start;
            }
            .custom-form {
                max-width: 800px;
                width: 100%;
                margin: 0 auto;
                margin-top: 50px;
            }
            .centered-heading {
                text-align: center;
            }
        </style>
        <title>Blog Post Form</title>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
         <main id="main">
            <div id="breadcrumb">
                <ol class="clearfix">
                    <li class="first link"><a href="homeController" title="Trang chủ"><span>Trang
                                chủ</span></a>></li>
                    <li class="link last"><span>Tin tức</span></li>
                </ol>
            </div>
            <div class="container centered">
                <div class="custom-form">
                    <h1 class="centered-heading">Đăng tin</h1>
                    <form method="post" action="postblog">
                        <div class="form-group">
                            <label for="title">Tiêu đề</label>
                            <input type="text" class="form-control" id="title" name="title">
                        </div>

                        <div class="form-group">
                            <label for="image">Ảnh</label>
                            <input type="file" class="form-control-file" id="image" name="imageblog">
                        </div>

                        <div class="form-group">
                            <label for="content">Nội dung mô tả</label>
                            <textarea class="form-control" id="content" name="content" rows="5"></textarea>
                        </div>

                        <button type="submit" class="btn btn-primary btn-block">Đăng tin</button>
                    </form>
                </div>
            </div>
        </main>
        <jsp:include page="footer.jsp"></jsp:include>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>


