<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Form</title>
    <link rel="stylesheet" href="styles/login.css">
</head>
<body>
    <div class="login-container">
        <img src="https://cdn.pixabay.com/photo/2023/11/03/00/48/heart-8361820_1280.png" alt="Ayudémonos Logo" class="logo">
        <h1>Ayudémonos</h1>
        <h2>Iniciar Sesión</h2>
        <div class="login-subcontainer">
            <form action="LoginServlet" method="post">
                <label for="email">Enter Email:</label>
                <input type="text" name="t1" id="email" required>

                <label for="password">Enter Password:</label>
                <input type="password" name="t2" id="password" required>

                <input type="submit" value="Login">
                <input type="reset" value="Reset">
            </form>
            <% 
                String errorMessage = (String) request.getAttribute("error");
                if (errorMessage != null) { 
            %>
                <p style="color: red;"><%= errorMessage %></p>
            <% 
                } 
            %>
        </div>
    </div>
</body>
</html>
