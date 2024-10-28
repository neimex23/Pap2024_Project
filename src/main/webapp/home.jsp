<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.pap.publicadores.DtUsuario"%>
<%@ page import="cscorner.UsuarioLogin"%>
<%@ page import="org.pap.publicadores.DtRepartidor"%>
<%@ page import="org.pap.publicadores.DtBeneficiario"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        header {
            background: #007bff;
            color: white;
            padding: 10px 20px;
            text-align: center;
        }
        nav {
            display: flex;
            justify-content: center;
            margin: 20px 0;
        }
        nav a {
            margin: 0 15px;
            padding: 10px 15px;
            color: white;
            text-decoration: none;
            background: #28a745;
            border-radius: 5px;
            transition: background 0.3s;
        }
        nav a:hover {
            background: #218838;
        }
        h1 {
            text-align: center;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <header>
        <h1>Login Successful</h1>
    </header>
    <nav>        
        <% 
            // Obtener el usuario desde la sesión
            String email = (String) request.getSession().getAttribute("usuario");
            UsuarioLogin usuarioLogin = UsuarioLogin.GetInstancia();
            DtUsuario usuarioObtenido = usuarioLogin.getUsuario();
            
            // Verificar el tipo de usuario para mostrar el menu correspondiente
            if (usuarioObtenido instanceof DtRepartidor) { // Si es Repartidor
        %>
            <a href="/verDistribuciones.jsp"><i class="fas fa-eye"></i> Ver Distribuciones</a>   
            <a href="/filtrarDistribucionesPorZona.jsp"><i class="fas fa-filter"></i> Filtrar Distribuciones Por Zona</a>
            <a href="/verPerfil.jsp"><i class="fas fa-user"></i> Mi Perfil</a> 
        <% 
            } else { // Si es Beneficiario
        %>
            <a href="/verDistribuciones.jsp"><i class="fas fa-eye"></i> Mis Distribuciones</a>  
            <a href="/verPerfil.jsp"><i class="fas fa-user"></i> Mi Perfil</a> 
        <%
            }
        %>
        
    </nav>
</body>
</html>


