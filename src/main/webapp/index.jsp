<!DOCTYPE html>
<html>

<jsp:include page="WEB-INF/head.jsp">
    <jsp:param name="title" value="Home"/>
</jsp:include>

<body>

<div class="wrapper">
    <%--suppress CheckTagEmptyBody Sidebar--%>
    <jsp:include page="WEB-INF/sidebar.jsp"></jsp:include>

    <!-- Page Content  -->
    <div id="content">
        <jsp:include page="WEB-INF/header-bar.jsp"></jsp:include>
        <main>
            <ul class="product-list">
                <% for (int i = 0; i < 20; i++) { %>
                <li class="product">
                    <div class="product-layout">
                        <div>
                            <img src="images/iphone.jpeg" alt="IPhone" width="80%">
                        </div>
                        <div>
                            <p style="font-size:40px;margin-bottom: 20px;margin-top: 20px;">Product 1</p>
                            <p>Description: ....</p>
                            <p style="font-size:30px">Price: "--"</p>
                            <div>
                                <img src="images/star.png" alt="star"
                                     style="vertical-align:middle;width: 22px; height: 22px;">
                                <label style="font-size: 22px;vertical-align:middle;">Review: 4.5/5 (2)</label>
                            </div>
                        </div>
                    </div>
                </li>
                <%}%>
            </ul>
        </main>
    </div>
</div>

</body>

</html>