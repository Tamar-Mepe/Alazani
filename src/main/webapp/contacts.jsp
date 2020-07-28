<%@ page import="models.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Category" %>
<!DOCTYPE html>
<html>

<jsp:include page="WEB-INF/head.jsp">
    <jsp:param name="title" value="Alazani"/>
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
                <h3>
                    This project was designed and made for oop course at free university of tbilisi.<br>
                </h3>
                <p2>
                    <I>A team of four members including:</I><br>
                        <strong>irakli gabelia</strong>  : igabe17@freeuni.edu.ge<br>
                        <strong>zura khutsishvili</strong>: zkhut16@freeuni.edu.ge<br>
                        <strong>davit targamadze</strong>   :dtarg16@freeuni.edu.ge<br>
                        <strong>tamar davitaia</strong>   : tdavi18@freeuni.edu.ge<br>
                </p2>
            </div>
        </main>
        <jsp:include page="WEB-INF/footer.jsp"></jsp:include>
    </div>
</div>
</body>
</html>