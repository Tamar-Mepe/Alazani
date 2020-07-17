<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Account</title>
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
<h1>Create New Account</h1>
<form>
    <label>Full Name: </label>
    <input type="text" name="register_name_surname"><br><br>
    <label>Username: </label>
    <input type="text" name="register_username"><br><br>
    <label>Email: </label>
    <input type="text" name="register_email"><br><br>
    <label>Password: </label>
    <input type="password" name="register_password"><br><br>
    <input type="submit" value="Create"><br><br>
</form>
</body>
</html>