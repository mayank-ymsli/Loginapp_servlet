<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
</head>
<body>
<h1>Login Page</h1>
	<form action="loginservlet" method="post">
	<input type="text" name="username" placeholder="enter your username"/><br>
	<input type="password" name="password" placeholder="enter your password"><br>
	<button type="submit">Login</button>
	</form>
</body>
</html>