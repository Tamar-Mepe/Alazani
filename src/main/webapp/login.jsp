<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log In</title>
</head>
<body>
<div>
    <header>
        <nav>
            <a href="index.jsp">
                <img src="images/logo.jpg" alt="Alazani logo"
                     width="5%">
            </a>
        </nav>
    </header>
</div>
<h1>Log In</h1>
<form>
    <label>Username: </label>
    <input type="text" name="username"><br><br>
    <label>Password: </label>
    <input type="password" name="password">
    <input type="submit" value="Log in"><br>
</form>
<label>Not Registered Yet? </label>
<a href="register.jsp">Click Here</a>
</body>
</html>