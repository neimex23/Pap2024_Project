<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="cscorner.UsuarioLogin" %> 
<%
    // Verificar el estado de la sesión y Establece no cache en la pagina
    UsuarioLogin.GetInstancia().checkLogin(request, response);
%>

<%
    // Verifica si se ha presionado el botón de cierre de sesión
    if (request.getParameter("logoutButton") != null) {
        UsuarioLogin.GetInstancia().Logout(request, response);
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