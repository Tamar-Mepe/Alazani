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
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">
                <button type="button" id="sidebarCollapse" class="btn btn-info">
                    <i class="fas fa-align-left"></i>
                    <span>Toggle Sidebar</span>
                </button>
                <button class="btn btn-dark d-inline-block d-lg-none ml-auto" type="button" data-toggle="collapse"
                        data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                        aria-expanded="false" aria-label="Toggle navigation">
                    <i class="fas fa-align-justify"></i>
                </button>


                <form class="form-inline d-flex justify-content-center md-form form-sm">
                    <input class="form-control form-control-sm mr-3 w-75" type="text" placeholder="Search"
                           aria-label="Search">
                    <i class="fas fa-search" aria-hidden="true"></i>
                </form>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="nav navbar-nav ml-auto">
                        <li class="nav-item"><a class="nav-link" href="#">Page</a></li>
                        <li class="nav-item"><a class="nav-link" href="#">Page</a></li>
                        <li class="nav-item"><a class="nav-link" href="#">Page</a></li>
                        <li class="nav-item"><a class="nav-link" href="#">Page</a></li>
                    </ul>
                </div>
            </div>
        </nav>

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