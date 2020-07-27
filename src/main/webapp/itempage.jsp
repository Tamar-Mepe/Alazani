<%@ page import="models.Product" %>
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
                        <label class="seller-label">By Anonymous User
                        </label><br>
                        <label class="quantity-label">Only <%=currProd.getQuantity()%> Left in stock - order
                            soon.</label><br>
                        <label class="product-description-label">Description: </label>
                        <br>
                        <label class="product-description"><%=currProd.getDescription()%>
                        </label><br>
                        <label class="price-label">$<%=currProd.getPriceString()%>
                        </label>
                        <form action="quantity-form">
                            <select name="quantity-select">
                                <% for (int i = 1; i <= currProd.getQuantity(); i++) { %>
                                <option value="quant"><%=i%>
                                </option>
                                <%}%>
                            </select>
                            <button type="submit" class="buy-button btn btn-¬primary btn-sm">Small button</button>
                        </form>
                    </div>
                </div>
            </div>
        </main>
        <jsp:include page="WEB-INF/footer.jsp"></jsp:include>
    </div>
</div>
</body>
</html>
