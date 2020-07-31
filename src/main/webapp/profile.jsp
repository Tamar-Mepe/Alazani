<%@ page import="models.User" %>
<%@ page import="models.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="javafx.util.Pair" %>
<!DOCTYPE html>
<html>

<%
    int id = (Integer) request.getSession().getAttribute(User.ATTRIBUTE_NAME);
    User user = User.get(id);
%>

<jsp:include page="WEB-INF/head.jsp">
    <jsp:param name="title" value="Profile"/>
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
                <p class="label-styling-bold-2">
                    Account Settings<br>
                </p>
                <div class="container">
                    <div class="row" style="margin-bottom: 20px">
                        <div class="col-md-2 profile-styling bold-name">
                            First Name
                        </div>
                        <div class="col-lg-2 profile-styling gray-label"><%=user.getFirstName()%>
                        </div>
                        <input type="text" name="change-first-name"
                               placeholder="Enter First Name...">
                    </div>
                    <div class="row" style="margin-bottom: 20px">
                        <div class="col-md-2 profile-styling bold-name">
                            Last Name
                        </div>
                        <div class="col-lg-2 profile-styling gray-label"><%=user.getLastName()%>
                        </div>
                        <input type="text" name="change-last-name"
                               placeholder="Enter Last Name...">
                    </div>
                    <div class="row" style="margin-bottom: 20px">
                        <div class="col-md-2 profile-styling bold-name">
                            Username
                        </div>
                        <div class="col-lg-2 profile-styling gray-label"><%=user.getUsername()%>
                        </div>
                        <input type="text" name="change-username"
                               placeholder="Enter Username...">
                    </div>
                    <div class="row" style="margin-bottom: 20px">
                        <div class="col-md-2 profile-styling bold-name">
                            Password
                        </div>
                        <div class="col-lg-2 profile-styling gray-label">********
                        </div>
                        <input type="password" name="change-password"
                               placeholder="Enter New Password...">
                        <input style="margin-left: 20px" type="password" name="change-password-confirm"
                               placeholder="Confirm Password...">
                    </div>
                    <button type="button" class="btn btn-warning change-button">Change</button>
                    <div>
                        <p class="label-styling-bold-3">
                            Already Purchased<br>
                        </p>
                    </div>
                    <div class="product-list row">
                        <% List<Pair<Product, Integer>> products = user.purchasedProducts();
                            if (products.size() == 0) { %>
                        <p class="empty-label">You haven't purchased anything yet.</p>
                        <% } else {
                            for (Pair<Product, Integer> purchased : products) {
                                Product product = purchased.getKey();
                                int quantity = purchased.getValue();%>
                        <jsp:include page="already-purchased.jsp">
                            <jsp:param name="product-id" value="<%=product.getId()%>"/>
                            <jsp:param name="product-quantity" value="<%=quantity%>"/>
                            <jsp:param name="product-name" value="<%=product.getName()%>"/>
                            <jsp:param name="product-description" value="<%=product.getDescription()%>"/>
                            <jsp:param name="product-image-path" value="<%=product.getImageAddress()%>"/>
                            <jsp:param name="product-price" value="<%=product.getPriceString()%>"/>
                        </jsp:include>
                        <%
                                }
                            }
                        %>
                    </div>
                    <div>
                        <p class="label-styling-bold-3">
                            Products for sale<br>
                        </p>
                    </div>
                    <div class="product-list row">
                        <% List<Product> currProducts = user.products();
                            if (currProducts.size() == 0) { %>
                        <p class="empty-label">You don't have any items on sale.</p>
                        <%
                        } else {
                            for (Product currProd : currProducts) {
                        %>
                        <jsp:include page="already-purchased.jsp">
                            <jsp:param name="product-id" value="<%=currProd.getId()%>"/>
                            <jsp:param name="product-quantity" value="<%=currProd.getQuantity()%>"/>
                            <jsp:param name="product-name" value="<%=currProd.getName()%>"/>
                            <jsp:param name="product-description" value="<%=currProd.getDescription()%>"/>
                            <jsp:param name="product-image-path" value="<%=currProd.getImageAddress()%>"/>
                            <jsp:param name="product-price" value="<%=currProd.getPriceString()%>"/>
                        </jsp:include>
                        <%
                                }
                            }
                        %>
                    </div>
                </div>
            </div>
        </main>
        <jsp:include page="WEB-INF/footer.jsp"></jsp:include>
    </div>
</div>
</body>
</html>