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
                <p style="font-size:30px">$<%=request.getParameter("product-price")%>
                </p>
                <div>
                    <img src="images/star.png" alt="star"
                         style="vertical-align:middle;width: 22px; height: 22px;">
                    <label style="font-size: 22px;vertical-align:middle;"><%=Review.getAverageReviewByProductId(Integer.parseInt(request.getParameter("product-id")))%>/5 (2)</label>
                </div>
            </div>
        </div>
    </div>
</div>