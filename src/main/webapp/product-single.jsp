<%@ page import="models.Review" %>
<div class="mb-4 col-md-4">
    <div class="product">
        <a href="itempage.jsp?id=<%=request.getParameter("product-id")%>">
            <p class="item-name-style"><%=request.getParameter("product-name")%>
            </p>
        </a>
        <div class="product-layout">
            <div id="one-prod">
                <img src="<%=request.getParameter("product-image-path")%>" alt="product" width="80%">
            </div>
            <div>
                <p><%=request.getParameter("product-description")%>
                </p>
                <p id="font-30">$<%=request.getParameter("product-price")%>
                </p>
                <div>
                    <img src="images/star.png" alt="star" id="image-style">
                    <label id="review-styling"><%=Review.getAverageReviewByProductId(Integer.parseInt(request.getParameter("product-id")))%>
                        /5
                        (<%=Review.getReviewsByProductId(Integer.parseInt(request.getParameter("product-id"))).size()%>)
                    </label>
                </div>
            </div>
        </div>
    </div>
</div>