<%@ page import="models.User" %>
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
                <% if (request.getSession().getAttribute(User.ATTRIBUTE_NAME) == null) {%>
                <li class="nav-item"><a class="nav-link" href="/login.jsp" style="color: white">Sign In</a></li>
                <% } else { %>
                <li class="nav-item"><a class="nav-link" href="/sell.jsp" style="color: white">Sell Items</a></li>
                <li class="nav-item"><a class="nav-link" href="/cart.jsp" style="color: white">Cart</a></li>
                <li class="nav-item"><a class="nav-link" href="/login.jsp" style="color: white">Profile</a></li>
                <li class="nav-item"><a class="nav-link" href="/index.jsp" style="color: white">Sign Out</a></li>
                <%}%>
            </ul>
            .
        </div>
    </div>
</nav>
