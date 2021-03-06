<%@ page import="models.Product" %>
<%@ page import="models.User" %>
<%@ page import="models.Review" %>
<%@ page import="java.util.List" %>
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
            <div class="container item-container">
                <div class="single-prod-layout">
                    <div>
                        <img src=<%=currProd.getImageAddress()%> width="80%">
                    </div>
                    <div>
                        <label class="title-label"><%=currProd.getName()%>
                        </label><br>
                        <label class="seller-label">Added by: <%=User.get(currProd.getUserId()).getUsername()%>
                        </label><br><br>
                        <div>
                            <img src="images/star.png" alt="star" id="image-style">
                            <label id="review-styling">
                                <%=Review.getAverageReviewByProductId(id)%>/5
                                (<%=Review.getReviewsByProductId(id).size()%>)
                            </label>
                        </div>
                        <% int quantity = currProd.getQuantity();
                            if (quantity > 0) { %>
                        <label class="quantity-label">Only <%=quantity%> Left in stock - order
                            soon.</label><br>
                        <%} else { %>
                        <label class="quantity-label">Out of stock.</label><br>
                        <%} %>
                        <label class="product-description-label">Description: </label>
                        <br>
                        <label class="product-description"><%=currProd.getDescription()%>
                        </label><br>
                        <label class="price-label">$<%=currProd.getPriceString()%>
                        </label>
                        <form method="post" action="CartServlet">
                            <input name="productId" type="hidden" value=<%=id%>>
                            <label>
                                <select name="quantity-select">
                                    <% for (int i = 1; i <= currProd.getQuantity(); i++) { %>
                                    <option>
                                        <%=i%>
                                    </option>
                                    <%}%>
                                </select>
                            </label>
                            <button onclick="checkIfLogged()" type="submit" name="cartButton" id="buy-button"
                                    class="btn btn-primary btn-sm">Add
                                To Cart
                            </button>
                            <br>
                            <%
                                Object err = request.getSession().getAttribute("error");
                                if (err != null) {%>
                            <label class="error-message-cant-add"><%=(String) err%>
                            </label>
                            <%
                                    request.getSession().setAttribute("error", null);
                                }
                            %>
                        </form>
                    </div>
                </div>
                <div>
                    <form method="post" action="ReviewServlet">
                        <input name="productId" type="hidden" value=<%=id%>>
                        <div>
                            <textarea class="form-control" id="comment" name="comment" rows="3"
                                      placeholder="Comment..."
                                      maxlength="1024"></textarea>
                        </div>
                        <label>
                            <select name="points">
                                <% for (int i = 1; i <= 5; i++) { %>
                                <option>
                                    <%=i%>
                                </option>
                                <%}%>
                            </select>
                            Stars
                        </label>
                        <button onclick="checkIfLogged()" type="submit" name="reviewButton" id="add-review-button"
                                class="btn btn-primary btn-lg btn-block">Add Your
                            Review
                        </button>
                    </form>
                </div>
                <div>
                    <p id="review-label">Reviews</p>
                    <% List<Review> productReviews = Review.getReviewsByProductId(id); %>
                    <% for (Review rev : productReviews) { %>
                    <p id="username-label">user: <%=User.get(rev.getUserId()).getUsername()%>
                    </p>
                    <label id="rating-label">Rating: <%=rev.getPoints()%>
                    </label>
                    <% for (int i = 0; i < rev.getPoints(); i++) { %>
                    <img id="star-image" src="/images/star.png" alt="review-star" width="1.5%">
                    <%}%>
                    <label id="rating-label"><%=rev.getPoints()%>/5</label>
                    <p id="review-text"><%=rev.getComment()%>
                    </p>
                    <%}%>
                </div>
            </div>
        </main>
        <jsp:include page="WEB-INF/footer.jsp"></jsp:include>
    </div>
</div>
<script>
    function checkIfLogged() {
        <%if (request.getSession().getAttribute(User.ATTRIBUTE_NAME) == null){%>
        confirm("Sign in first!");
        <%}%>
    }
</script>
</body>
</html>
