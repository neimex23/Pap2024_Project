<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% //Elimina Cache 
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0
    response.setDateHeader("Expires", 0); // Proxies
%>

<%@ page import="cscorner.UsuarioLogin" %> 
<%
    // Verificar el estado de la sesión
    UsuarioLogin usuarioLogin = UsuarioLogin.GetInstancia();

    if (!usuarioLogin.isLogin()) {
        // Redirigir a LogoutServlet si no está logueado
        request.setAttribute("error", "Debe Iniciar Secion Primero");
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response); 
        return; // Salir para evitar que el resto de la página se ejecute
    }
%>

<%@ page import="cscorner.UsuarioLogin" %> 
<%
    // Verifica si se ha presionado el botón de cierre de sesión
    if (request.getParameter("logoutButton") != null) {
        // Lógica de cierre de sesión
        UsuarioLogin usuarioLog = UsuarioLogin.GetInstancia();
        usuarioLog.Logout();
        // Redirigir a la página de login
        response.sendRedirect("login.jsp");
        return; // 
    }
%>

<!DOCTYPE html>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>Login Sussess</h1>
        <a href="/verDistribuciones.jsp">Ver Distribuciones</a>
        <a href="/filtrarDistribucionesPorZona.jsp"> Filtrar Distribuciones Por Zona</a>
        <form method="post">
        <button type="submit" name="logoutButton">Cerrar Sesión</button>
</form>
</body>
</html>