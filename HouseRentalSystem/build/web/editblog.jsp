<%-- 
    Document   : editblog
    Created on : Jul 13, 2023, 2:37:08 PM
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
            }
            .centered-heading {
                text-align: center;
            }
            .btn-file {
                position: relative;
                overflow: hidden;
            }

            .btn-file input[type=file] {
                position: absolute;
                top: 0;
                right: 0;
                min-width: 100%;
                min-height: 100%;
                font-size: 100px;
                text-align: right;
                filter: alpha(opacity=0);
                opacity: 0;
                outline: none;
                background: white;
                cursor: inherit;
                display: block;
            }

            input[readonly] {
                background-color: white !important;
                cursor: text !important;
            }
        </style>
        <title>Chỉnh sửa tin tức</title>
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
                        <h1 class="centered-heading">Sửa tin</h1>
                        <form method="post" action="editblog">
                            <input type="hidden" value="${nav}" name="nav">
                        <input type="hidden" value="${idB}" name="idB">
                        <div class="form-group">
                            <label for="title">Tiêu đề</label>
                            <input type="text" class="form-control" id="title" value="${title}" name="title">
                        </div>
                        <div class="form-group">
                            <label> Ảnh </label>
                            <div class="input-group">
                                <span class="input-group-btn">
                                    <span class="btn btn-primary btn-file">
                                        Browse <input type="file" name="imageblog" multiple>
                                    </span>
                                </span>
                                <input type="text" value="${image}" class="form-control" readonly>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="content">Nội dung mô tả</label>
                            <br/>
                            <textarea class="form-control" name="content" id="content" rows="5">${content}</textarea>
                        </div>

                        <button type="submit" class="btn btn-primary btn-block">Cập nhật</button>
                        <a class="btn btn-primary btn-block" href="listblog" style="text-decoration: none">Hủy</a>
                    </form>
                </div>
            </div>
        </main>
        <jsp:include page="footer.jsp"></jsp:include>
        <!--        <script src="js/jquery/jquery-2.2.4.min.js"></script>
                <script src="js/plugins.js"></script>
                <script src="js/active.js"></script>-->
        <script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js'></script>
        <script
        src='https://cdn.rawgit.com/bootstrap-wysiwyg/bootstrap3-wysiwyg/master/dist/bootstrap3-wysihtml5.all.min.js'></script>
        <script src="js/script.js"></script>

    </body>
</html>


