<%-- 
    Document   : feedbacksystem
    Created on : Jun 21, 2023, 1:24:22 AM
    Author     : Trung Hieu
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/style9.css">
        <style>
            /* Áp dụng cho hình tròn */
            /*.img-responsive {
              border-radius: 50%;
            }
            
             Đảm bảo ảnh luôn có kích thước bằng nhau 
            .img-responsive {
              width: 200px;  Thay 200px bằng kích thước mong muốn 
              height: 200px;  Thay 200px bằng kích thước mong muốn 
              object-fit: cover;  Đảm bảo ảnh không bị bóp méo 
            }*/

        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <section class="content-item" id="comments">
                <div class="container">   
                    <div class="row">
                        <div class="col-ms-12"> 
                        <c:if test="${sessionScope.acc != null}">
                            <form method="post" action="feedbackforsystem">
                                <div class="section-header" style="margin-bottom: 50px; text-align: center;">
                                    <div class="section-title big"><h2>Bạn nghĩ sao về chúng tôi</h2></div>
                                    <h3>Sự hài lòng của khách hàng là động lực phát triển của chúng tôi</h3>
                                </div>
                                <fieldset>
                                    <div class="row">
                                        <div class="cmtimg col-sm-3 col-lg-2">
                                            <img style="width: 100px; height: 100px; margin-top: 25px; margin-left: 70px " class="img-responsive" src="./assets/images/${sessionScope.acc.getProfile_Picture()}" alt="" >
                                        </div>
                                        <div class="form-group col-xs-12 col-sm-9 col-lg-10">
                                            <h4 class="media-heading">
                                                <c:if test="${sessionScope.acc.id == i.getAccount_ID()}">
                                                    <span id="fullname-${i.getID()}">${sessionScope.acc.fullname}</span>
                                                    <input type="text" id="edit-fullname-${i.getID()}" class="edit-fullname" value="${i.getFullname()}" style="display: none;">
                                                </c:if>
                                                <c:if test="${sessionScope.acc.id != i.getAccount_ID()}">
                                                    ${sessionScope.acc.fullname}
                                                </c:if>
                                            </h4>
                                            <textarea class="form-control" id="message" placeholder="Nhập phản hồi" name="comment" required=""></textarea>
                                        </div>
                                        <button type="submit" class="btn btn-normal pull-right">Gửi</button>
                                    </div>  	
                                </fieldset>

                            </form>
                            <div class="post-sort">
                                <span>Sắp xếp: </span>
                                <a class="${request.orderBy == 1? '' : 'active'}" href="feedbackforsystem">Mặc định</a>
                                <a class="${request.orderBy == 1? '' : 'active'}" href="feedbackforsystem?orderBy=1">Mới nhất</a>
                                <a class="${request.orderBy == 1? '' : 'active'}" href="feedbackforsystem?accID=${sessionScope.acc.id}">Bình luận của tôi</a>
                            </div>
                        </c:if>

                        <c:if test="${sessionScope.acc == null}">
                            <form>
                                <div class="section-header" style="margin-bottom: 50px; text-align: center;">
                                    <div class="section-title big"><h2>Bạn nghĩ sao về chúng tôi</h2></div>
                                    <h3>Sự hài lòng của khách hàng là động lực phát triển của chúng tôi</h3>
                                </div>
                            </form>
                            <div class="post-sort">
                                <span>Sắp xếp: </span>
                                <a class="${request.orderBy == 1? '' : 'active'}" href="feedbackforsystem">Mặc định</a>
                                <a class="${request.orderBy == 1? '' : 'active'}" href="feedbackforsystem?orderBy=1">Mới nhất</a>
                            </div>
                        </c:if>
                        <c:forEach items="${requestScope.listf}" var="i">
                            <div class="media">
                                <a class="pull-left" href="#"><img class="media-object" src="./assets/images/${i.account.getProfile_Picture()}" alt=""></a>
                                <div class="media-body">
                                    <h4 class="media-heading">${i.account.getFullname()}</h4>
                                    <p>
                                        <c:if test="${sessionScope.acc.id == i.getAccount_ID()}">
                                        <form action="editfeedbacksystem?accountID=${i.getID()}" method="post">
    <span id="content-${i.getID()}">${i.getContent()}</span>
    <textarea id="edit-content-${i.getID()}" class="edit-content" style="display: none;" onkeydown="handleEnterKey(event, '${i.getID()}')">${i.getContent()}</textarea>
    <textarea name="comment" id="edit-input-${i.getID()}" class="edit-input" type="text" style="display: none; height: 100px; width: 1000px" onkeydown="handleEnterKey(event, '${i.getID()}')"></textarea>
    <input type="submit" value="Save" style="display: none;">
</form>
                                    </c:if>
                                    <c:if test="${sessionScope.acc.id != i.getAccount_ID()}">
                                        ${i.getContent()}
                                    </c:if>
                                    </p>
                                    <ul class="list-unstyled list-inline media-detail pull-left">
                                        <li><i class="fa fa-calendar"></i>${i.getAddDate()}</li>

                                    </ul>
                                    <c:if test="${sessionScope.acc.id == i.getAccount_ID()}">
                                        <ul class="list-unstyled list-inline media-detail pull-right">
                                            <li class=""><a href="#" onclick="showEditInput(${i.getID()})">Edit</a></li>
                                            <li class=""><a href="deletefeedbacksystem?accountID=${i.getID()}">Delete</a></li>
                                        </ul>

                                    </c:if>
                                </div>
                                <script>
                                    function showEditInput(feedbackID) {
                                        var contentSpan = document.getElementById("content-" + feedbackID);
                                        var editContentTextarea = document.getElementById("edit-content-" + feedbackID);
                                        var editInput = document.getElementById("edit-input-" + feedbackID);

                                        // Hiển thị nội dung chỉnh sửa và ẩn nội dung ban đầu
                                        contentSpan.style.display = "none";
                                        editContentTextarea.style.display = "inline";

                                        // Đưa nội dung ban đầu vào input
                                        editInput.value = contentSpan.textContent;

                                        // Hiển thị input và ẩn textarea
                                        editInput.style.display = "block";
                                        editContentTextarea.style.display = "none";
                                    }
                                </script>
                                <script>
                                    function sendEditRequest(feedbackID) {
                                        var editInput = document.getElementById("edit-input-" + feedbackID).value;

                                        // Tạo object XMLHttpRequest
                                        var xhr = new XMLHttpRequest();

                                        // Xác định phương thức và URL của servlet
                                        xhr.open("POST", "editfeedbacksystem", true);

                                        // Thiết lập header cho request (nếu cần thiết)
                                        xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

                                        // Định dạng dữ liệu gửi đến servlet
                                        var data = "feedbackID=" + feedbackID + "&editInput=" + encodeURIComponent(editInput);

                                        // Gửi request
                                        xhr.send(data);
                                    }
                                </script>
                            </div>
                        </c:forEach>
                        <div class="text-center">
                            <ul class="pagination">
                                <c:forEach begin="1" end="${requestScope.total}" var="i">
                                    <li class="page-item"><a class="page-link" href="feedbackforsystem?pageNum=${i}&orderBy=${requestScope.orderBy}">${i}</a></li>
                                    </c:forEach>
                            </ul>
                        </div>
                        <script>
                            const editFullnameFields = document.querySelectorAll('.edit-fullname');
                            const editContentFields = document.querySelectorAll('.edit-content');

                            editFullnameFields.forEach((editFullnameField) => {
                                const commentId = editFullnameField.id.split('-')[2];
                                const fullnameElement = document.getElementById(`fullname-${commentId}`);
                                const editFullnameElement = document.getElementById(`edit-fullname-${commentId}`);

                                editFullnameField.addEventListener('input', () => {
                                    fullnameElement.innerText = editFullnameField.value;
                                });

                                editFullnameElement.addEventListener('input', () => {
                                    editFullnameField.value = editFullnameElement.value;
                                });
                            });

                            editContentFields.forEach((editContentField) => {
                                const commentId = editContentField.id.split('-')[2];
                                const contentElement = document.getElementById(`content-${commentId}`);
                                const editContentElement = document.getElementById(`edit-content-${commentId}`);

                                editContentField.addEventListener('input', () => {
                                    contentElement.innerText = editContentField.value;
                                });

                                editContentElement.addEventListener('input', () => {
                                    editContentField.value = editContentElement.value;
                                });
                            });
                        </script>
                        </section>

                        <jsp:include page="footer.jsp"></jsp:include>
                       <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    var updatedData; // Biến để lưu trữ dữ liệu mới

    function handleEnterKey(event, id) {
        if (event.keyCode === 13) { // Kiểm tra nếu phím nhấn là phím Enter
            event.preventDefault(); // Ngăn người dùng nhập xuống dòng mới trong ô textarea
            updateFeedback(id); // Gọi hàm để cập nhật dữ liệu
        }
    }

    function updateFeedback(id) {
        var contentSpan = $("#content-" + id);
        var editContentTextarea = $("#edit-content-" + id);
        var editInputTextarea = $("#edit-input-" + id);

        updatedData = editContentTextarea.val(); // Lưu trữ dữ liệu mới

        // Gửi yêu cầu Ajax để cập nhật dữ liệu trên máy chủ
        $.ajax({
            url: "editfeedbacksystem?accountID=" + id,
            type: "POST",
            data: { comment: editInputTextarea.val() },
            success: function(response) {
                // Xử lý kết quả thành công (nếu cần)
                location.reload(); // Tải lại trang
            },
            error: function() {
                // Xử lý khi có lỗi (nếu cần)
            }
        });
    }

    $(document).ready(function() {
        // Sau khi tải lại trang, cập nhật dữ liệu mới
        contentSpan.text(updatedData);
    });
</script>
                        </body>
                        </html>
