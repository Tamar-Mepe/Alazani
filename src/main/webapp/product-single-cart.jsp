<%@ page import="models.Review" %>
<% int id = Integer.parseInt(request.getParameter("product-id"));%>
<div class="mb-4 col-xl-3 col-lg-4 col-md-6">
    <div class="product">
        <a href="itempage.jsp?id=<%=request.getParameter("product-id")%>">
            <h3 class="cart-item-style" style="margin-top: 10px">
                <%=request.getParameter("product-name")%>
                <span class="badge badge-primary quantity-label-style">
                    x<%=request.getParameter("product-quantity")%>
                </span>
            </h3>
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
                    <label id="review-styling">
                        <%=Review.getAverageReviewByProductId(id)%>/5 (<%=Review.getReviewsByProductId(id).size()%>)
                    </label><br>
                    <form method="post" action="RemoveItemFromCart"
                    <button type="submit" class="btn btn-secondary btn-sm cart-buttons">Remove</button>
                </div>
            </div>
        </div>
    </div>
</div>