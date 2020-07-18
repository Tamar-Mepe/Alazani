
<div class="container">
    <div class="product-list row">
        <% for (int i = 0; i < 13; i++) { %>
        <jsp:include page="product-single.jsp"></jsp:include>
        <%}%>
    </div>
</div>