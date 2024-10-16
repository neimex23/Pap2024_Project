<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Form</title>
</head>
<body>
    <h2>Iniciar Sesión</h2>
    <form action="LoginServlet" method="post">
        <pre>
            Enter Email: <input type="text" name="t1" required>
            
            Enter Password: <input type="password" name="t2" required>
            
            <input type="submit" value="Login"> <input type="reset">
        </pre>
    </form>

    <% 
        String errorMessage = (String) request.getAttribute("error");
        if (errorMessage != null) { 
    %>
        <p style="color: red;"><%= errorMessage %></p>
    <% 
        } 
    %>
</body>
</html>
