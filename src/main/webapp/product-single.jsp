<%@ page import="models.Review" %>
<% int id = Integer.parseInt(request.getParameter("product-id"));%>
<div class="mb-4 col-md-4">
    <div class="product">
        <a href="item-page.jsp?id=<%=request.getParameter("product-id")%>">
            <p class="item-name-style"><%=request.getParameter("product-name")%>
            </p>
        </a>
        <div class="product-layout">
            <div id="one-prod">
                <img src="<%=request.getParameter("product-image-path")%>" alt="product" width="80%">
            </div>
            <div>
                <p class="gray-label"><%=request.getParameter("product-description")%>
                </p>
                <p id="font-30">$<%=request.getParameter("product-price")%>
                </p>
                <div>
                    <img src="images/star.png" alt="star" id="image-style">
                    <label id="review-styling">
                        <%=Review.getAverageReviewByProductId(id)%>/5 (<%=Review.getReviewsByProductId(id).size()%>)
                    </label>
                </div>
            </div>
        </div>
    </div>
</div>