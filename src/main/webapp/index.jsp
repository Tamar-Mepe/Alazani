<!DOCTYPE html>
<html>

<jsp:include page="WEB-INF/head.jsp">
    <jsp:param name="title" value="Home"/>
</jsp:include>

<body>

<div class="wrapper">
    <%--suppress CheckTagEmptyBody Sidebar--%>
    <jsp:include page="WEB-INF/sidebar.jsp"></jsp:include>

    <!-- Page Content  -->
    <div id="content">
        <header>
            <jsp:include page="WEB-INF/header-bar.jsp"></jsp:include>
        </header>
        <main>
            <ul class="product-list">
                <% for (int i = 0; i < 12; i++) { %>
                <jsp:include page="WEB-INF/single-product.jsp"></jsp:include>
                <%}%>
            </ul>
            <%--TODO: Pagination--%>
        </main>
        <jsp:include page="WEB-INF/footer.jsp"></jsp:include>
    </div>
</div>
</body>
</html>