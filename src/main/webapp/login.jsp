<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Form</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="styles/login.css">
</head>
<body>
    <div class="container d-flex justify-content-center align-items-center vh-100">
        <div class="login-container bg-white p-4 rounded shadow">
            <img src="https://i.imgur.com/VLo82eq.png" alt="Ayudémonos Logo" class="logo mx-auto mb-3" style="width: 16rem; height: 9rem;">
            <h1 class="text-danger text-center">Ayudémonos</h1>
            <h2 class="text-center">Iniciar Sesión</h2>
            <div class="login-subcontainer">
                <form action="LoginServlet" method="post">
                    <div class="form-group">
                        <label for="email">Enter Email:</label>
                        <input type="email" class="form-control" name="t1" id="email" required>
                    </div>
                    <div class="form-group">
                        <label for="password">Enter Password:</label>
                        <input type="password" class="form-control" name="t2" id="password" required>
                    </div>
                    <button type="submit" class="btn btn-primary btn-block">Login</button>
                    <button type="reset" class="btn btn-secondary btn-block">Reset</button>
                </form>
                <% 
                    String errorMessage = (String) request.getAttribute("error");
                    if (errorMessage != null) { 
                %>
                    <p class="text-danger text-center"><%= errorMessage %></p>
                <% 
                    } 
                %>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS and dependencies (optional) -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
