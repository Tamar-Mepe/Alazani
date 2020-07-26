<div class="product col-md-4">
    <p style="font-size:40px;margin-bottom: 20px;margin-top: 20px;"><%=request.getParameter("product-name")%>
    </p>
    <div class="product-layout">
        <div>
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
                <label style="font-size: 22px;vertical-align:middle;">4.5/5 (2)</label>
            </div>
        </div>
    </div>
</div>