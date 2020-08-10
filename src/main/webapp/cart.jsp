<%@ page import="models.User" %>
<%@ page import="models.Cart" %>
<%@ page import="models.Product" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="css/loader.css">

<%
    int id = (Integer) request.getSession().getAttribute(User.ATTRIBUTE_NAME);
    Map<Product, Integer> products = Cart.getProductsByUserId(id);
%>
<jsp:include page="WEB-INF/head.jsp">
    <jsp:param name="title" value="Shopping Cart"/>
</jsp:include>
<body>
<script src="js/password.js">
</script>
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
                    <% if (products.size() == 0) {%>
                    <p class="empty-label">Your shopping cart is empty.</p>
                    <%
                    } else {
                        for (Product product : products.keySet()) {
                    %>
                    <jsp:include page="product-single-cart.jsp">
                        <jsp:param name="product-id" value="<%=product.getId()%>"/>
                        <jsp:param name="product-quantity" value="<%=products.get(product)%>"/>
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
                <%if (products.size() != 0) {%>
                <span class="total-price">TOTAL: $<%=Cart.totalPrice(id)%></span>
                <span class="free-shipping"> + FREE SHIPPING </span><br>
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal"
                        style="margin-top: 20px">
                    Buy All
                </button>

                <!-- Modal -->
                <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
                     aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">
                                    Alazani</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <form id="buy-form-id" name="changeForm" method="post" action="BuyServlet"
                                  onsubmit="return validateForm(true)">
                                <div class="modal-body">
                                    <p>Please enter password to confirm payment</p>
                                    <input style="width: 100%" type="password" id="current-password"
                                           name="current-password" placeholder=" Enter Password">
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close
                                    </button>
                                    <button type="submit" class="btn btn-primary">Checkout</button>
                                </div>
                                <div class="loader-wrapper-class">
                                </div>
                                <p class="loader-text-class"></p>
                            </form>
                        </div>
                    </div>
                </div>

                <%}%>
            </div>
        </main>
        <jsp:include page="WEB-INF/footer.jsp"></jsp:include>
    </div>
</div>
</body>
</html>