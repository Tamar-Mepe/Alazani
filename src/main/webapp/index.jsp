<%@ page import="models.Product" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<%! List<Product> products = Product.getAll(); %>
<jsp:include page="WEB-INF/head.jsp">
    <jsp:param name="title" value="Alazani"/>
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
        <main class="mb-5">
            <div class="container">
                <div class="product-list row">
                    <% for (Product product : products) { %>
                    <jsp:include page="WEB-INF/product-single.jsp">
                        <jsp:param name="product-name" value="<%=product.getName()%>"/>
                        <jsp:param name="product-description" value="<%=product.getDescription()%>"/>
                        <jsp:param name="product-image-path" value="<%=product.getImageAddress()%>"/>
                        <jsp:param name="product-price" value="<%=product.getPrice()%>"/>
                    </jsp:include>
                    <%}%>
                </div>
            </div>
        </main>
        <jsp:include page="WEB-INF/footer.jsp"></jsp:include>
    </div>
</div>
</body>
</html>