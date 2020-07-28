<!DOCTYPE html>
<html>

<jsp:include page="WEB-INF/head.jsp">
    <jsp:param name="title" value="About Us"/>
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
                <p class="label-styling-bold">
                    About Us<br>
                </p>
                <p id="about-us-styling">This project was designed and made for OOP course at Free University of
                    Tbilisi</p>
                <p id="text-about-us-styling">Team of four members including:</p>
                <label class="bold-name">Davit Targamadze: </label>
                <label class="gray-label">dtarg16@freeuni.edu.ge</label><br>
                <label class="bold-name">Irakli Gabelia: </label>
                <label class="gray-label">igabe17@freeuni.edu.ge</label><br>
                <label class="bold-name">Zura Khutsishvili: </label>
                <label class="gray-label">zkhut16@freeuni.edu.ge</label><br>
                <label class="bold-name">Tamar Davitaia: </label>
                <label class="gray-label">tdavi18@freeuni.edu.ge</label><br>
            </div>
        </main>
        <jsp:include page="WEB-INF/footer.jsp"></jsp:include>
    </div>
</div>
</body>
</html>