<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.pap.publicadores.DtUsuario"%>
<%@ page import="org.pap.publicadores.DtRepartidor"%>
<%@ page import="org.pap.publicadores.DtBeneficiario"%>
<%@ page import="cscorner.UsuarioLogin" %> 
<%
    // Verificar el estado de la sesión y Establece no cache en la pagina
    UsuarioLogin.GetInstancia().checkLogin(request, response);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>

    <!-- Incluir la barra de navegación -->
    <jsp:include page="navbar.jsp" />
    <div class="text-center">
        <img src="https://i.imgur.com/VLo82eq.png" alt="Ayudémonos Logo" class="img-fluid mb-3" style="max-width: 100%; height: auto; width: 230px;">
        <p class="h4 font-weight-bold">Bienvenidos</p>
    </div>
</body>
</html>

