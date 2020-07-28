<%@ page import="models.User" %>
<!DOCTYPE html>
<html>

<%
    int id = (Integer) request.getSession().getAttribute(User.ATTRIBUTE_NAME);
    User user = User.get(id);
%>

<jsp:include page="WEB-INF/head.jsp">
    <jsp:param name="title" value="Profile"/>
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
            <div class="container item-container">
                <p class="label-styling-bold-2">
                    Account Settings<br>
                </p>
                <div class="container">
                    <div class="row" style="margin-bottom: 20px">
                        <div class="col-md-2 profile-styling bold-name">
                            First Name
                        </div>
                        <div class="col-md-3 profile-styling gray-label"><%=user.getFirstName()%>
                        </div>
                        <button type="button" class="btn btn-warning">Change</button>
                    </div>
                    <div class="row" style="margin-bottom: 20px">
                        <div class="col-md-2 profile-styling bold-name">
                            Last Name
                        </div>
                        <div class="col-md-3 profile-styling gray-label"><%=user.getLastName()%>
                        </div>
                        <button type="button" class="btn btn-warning">Change</button>
                    </div>
                    <div class="row" style="margin-bottom: 20px">
                        <div class="col-md-2 profile-styling bold-name">
                            Username
                        </div>
                        <div class="col-md-3 profile-styling gray-label"><%=user.getUsername()%>
                        </div>
                        <button type="button" class="btn btn-warning">Change</button>
                    </div>
                    <div class="row" style="margin-bottom: 20px">
                        <div class="col-md-2 profile-styling bold-name">
                            Email
                        </div>
                        <div class="col-md-3 profile-styling gray-label"><%=user.getEmail()%>
                        </div>
                        <button type="button" class="btn btn-warning">Change</button>
                    </div>
                </div>
            </div>
        </main>
        <jsp:include page="WEB-INF/footer.jsp"></jsp:include>
    </div>
</div>
</body>
</html>