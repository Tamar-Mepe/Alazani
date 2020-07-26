<%@ page import="models.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Category" %>
<!DOCTYPE html>
<html>
<%
    // Cateogirzation
    List<Product> products = Product.getAll();
    String categoryIdStr = request.getParameter("category");
    if (categoryIdStr != null) {
        Category cat = Category.get(Integer.parseInt(categoryIdStr));
        products = cat.products();
    }

    // Pagination
    int prodPerPage = 18;
    int pageNum = 1;
    int totalPages = (int) Math.ceil((double) products.size() / prodPerPage);
    String pageNumStr = request.getParameter("page");
    if (pageNumStr != null) {
        pageNum = Integer.parseInt(pageNumStr);
    }

    products = products.subList((pageNum - 1) * prodPerPage, Math.min(pageNum * prodPerPage, products.size()));
%>
<jsp:include page="WEB-INF/head.jsp">
    <jsp:param name="title" value="Alazani"/>
</jsp:include>

<body>

<div class="wrapper">
    <%--suppress CheckTagEmptyBody Sidebar--%>
    <jsp:include page="WEB-INF/sidebar.jsp"></jsp:include>

    <!-- Page Content  -->
    <div id="content">
        <header>
            <jsp:include page="WEB-INF/header-bar.jsp"></jsp:include>
        </header>
        <main class="mb-5">
            <div class="container">
                <div class="product-list row">
                    <% for (Product product : products) { %>
                    <jsp:include page="WEB-INF/product-single.jsp">
                        <jsp:param name="product-name" value="<%=product.getName()%>"/>
                        <jsp:param name="product-description" value="<%=product.getDescription()%>"/>
                        <jsp:param name="product-image-path" value="<%=product.getImageAddress()%>"/>
                        <jsp:param name="product-price" value="<%=product.getPriceString()%>"/>
                    </jsp:include>
                    <%}%>
                </div>
                <nav aria-label="Page navigation example">
                    <ul class="pagination justify-content-center">
                        <% String currPath = pageContext.getRequest().getServletContext().getContextPath();%>
                        <li class="page-item <%= pageNum == 1 ? "disabled" :""%>">
                            <a class="page-link" href="<%=currPath%>/?page=<%=pageNum-1%>">Previous</a>
                        </li>
                        <% for (int i = 1; i <= totalPages; i++) {%>
                        <li class="page-item <%= i == pageNum ? "active" : ""%>">
                            <a class="page-link" href="<%=currPath%>/?page=<%=i%>"><%=i%>
                            </a>
                        </li>
                        <%}%>
                        <li class="page-item <%= pageNum == totalPages ? "disabled" : ""%>">
                            <a class="page-link" href="<%=currPath%>/?page=<%=pageNum+1%>">Next</a>
                        </li>
                    </ul>
                </nav>
            </div>
        </main>
        <jsp:include page="WEB-INF/footer.jsp"></jsp:include>
    </div>
</div>
</body>
</html>