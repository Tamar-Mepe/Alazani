<%@ page import="models.User" %>
<%@ page import="models.Cart" %>
<%@ page import="models.Product" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html>
<%
    int id = (Integer) request.getSession().getAttribute(User.ATTRIBUTE_NAME);
    Map<Product, Integer> products = Cart.getProductsByUserId(id);
%>
<jsp:include page="WEB-INF/head.jsp">
    <jsp:param name="title" value="Shopping Cart"/>
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
            <h1 class="cart-header">Shopping Cart</h1>
            <div class="container-fluid">
                <div class="product-list row">
                    <% for (Product product : products.keySet()) { %>
                    <jsp:include page="product-single-cart.jsp">
                        <jsp:param name="product-id" value="<%=product.getId()%>"/>
                        <jsp:param name="product-quantity" value="<%=products.get(product)%>"/>
                        <jsp:param name="product-name" value="<%=product.getName()%>"/>
                        <jsp:param name="product-description" value="<%=product.getDescription()%>"/>
                        <jsp:param name="product-image-path" value="<%=product.getImageAddress()%>"/>
                        <jsp:param name="product-price" value="<%=product.getPriceString()%>"/>
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