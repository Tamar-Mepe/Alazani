<%@ page import="models.Product" %>
<%@ page import="models.User" %>
<!DOCTYPE html>
<html>
<%
    int id = Integer.parseInt(request.getParameter("id"));
    Product currProd = Product.get(id);
%>
<jsp:include page="WEB-INF/head.jsp">
    <jsp:param name="title" value="<%=currProd.getName()%>"/>
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
            <div class="container">
                <div class="single-prod-layout">
                    <div>
                        <img src=<%=currProd.getImageAddress()%> width="80%">
                    </div>
                    <div>
                        <label class="title-label"><%=currProd.getName()%>
                        </label><br>
                        <label class="seller-label">Added by: <%=User.get(currProd.getUserId()).getUsername()%>
                        </label><br>
                        <label class="quantity-label">Only <%=currProd.getQuantity()%> Left in stock - order
                            soon.</label><br>
                        <label class="product-description-label">Description: </label>
                        <br>
                        <label class="product-description"><%=currProd.getDescription()%>
                        </label><br>
                        <label class="price-label">$<%=currProd.getPriceString()%>
                        </label>
                        <form method="post" action="CartServlet">
                            <input name="productId" type="hidden" value=<%=id%>>
                            <select name="quantity-select">
                                <% for (int i = 1; i <= currProd.getQuantity(); i++) { %>
                                <option name="quant"><%=i%>
                                </option>
                                <%}%>
                            </select>
                            <button type="submit" class="buy-button btn btn-primary btn-sm">Add To Cart</button>
                        </form>
                    </div>
                </div>
                <div>
                    <p id="review-label">Reviews</p>
                    <% for (int i = 0; i < 10; i++) { %>
                    <p id="username-label">user: vighacatipi</p>
                    <label id="rating-label">Rating: </label>
                    <img id="star-image" src="/images/star.png" alt="review-star" width="1.5%">
                    <label id="rating-label">4.7/5</label>
                    <p id="review-text">Magari itemia, kidev vikidi</p>
                    <%}%>
                </div>
            </div>
        </main>
        <jsp:include page="WEB-INF/footer.jsp"></jsp:include>
    </div>
</div>
</body>
</html>
