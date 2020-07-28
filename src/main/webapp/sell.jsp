<%@ page import="models.Category" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<%
    List<Category> categories = Category.getAll();
%>
<jsp:include page="WEB-INF/head.jsp">
    <jsp:param name="title" value="Sell Your Item"/>
</jsp:include>
<body>
<div class="wrapper">
    <%--suppress CheckTagEmptyBody Sidebar--%>
    <jsp:include page="/sidebar.jsp"></jsp:include>

    <!-- Page Content  -->
    <div id="content">
        <header>
            <jsp:include page="WEB-INF/header-bar.jsp"></jsp:include>
        </header>
        <main class="mb-5">
            <div class="container item-container">
                <p class="label-styling-bold text-left">Please Fill The Form</p>
                <form method="post" action="SellServlet" enctype="multipart/form-data">
                    <div class="form-group">
                        <label class="sell-labels" for="formGroupExampleInput">Name</label>
                        <input type="text" class="form-control" id="formGroupExampleInput" name="product-name"
                               placeholder="Enter name...">
                    </div>
                    <div class="form-group">
                        <label class="sell-labels" for="exampleFormControlTextarea1">Description</label>
                        <textarea class="form-control" id="exampleFormControlTextarea1" rows="2"
                                  name="product-description"
                                  maxlength="256"></textarea>
                    </div>
                    <div class="form-group">
                        <label class="sell-labels">Category</label>
                        <br>
                        <select name="category-select">
                            <% for (int i = 1; i < categories.size(); i++) { %>
                            <option name="cat"><%=categories.get(i).getName()%>
                            </option>
                            <%}%>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="sell-labels" for="inputQuantity">Quantity</label>
                        <input type="text" class="form-control" id="inputQuantity" name="product-quantity"
                               placeholder="Enter quantity...">
                    </div>
                    <div class="form-group">
                        <label class="sell-labels" for="inputPrice">Price</label>
                        <input type="text" class="form-control" id="inputPrice" name="product-price"
                               placeholder="Enter price...">
                    </div>
                    <label class="sell-labels" for="customFile">Upload Image</label><br>
                    <div class="custom-file">
                        <input type="file" class="custom-file-input" id="customFile" name="image-name">
                        <label class="custom-file-label" for="customFile">Choose file</label>
                    </div>
                    <% if (request.getAttribute("error") != null) {%>
                    <div class="form-group">
                        <br>
                        <label><%=request.getAttribute("error")%>
                        </label>
                    </div>
                    <% }%>
                    <div class="custom-file">
                        <button type="submit" id="sell-button" class="btn btn-primary btn-lg btn-block">Sell
                        </button>
                    </div>
                </form>
            </div>
        </main>
        <jsp:include page="WEB-INF/footer.jsp"></jsp:include>
    </div>
</div>
</body>
</html>

<script>
    $(".custom-file-input").on("change", function () {
        var fileName = $(this).val().split("\\").pop();
        $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
    });
</script>