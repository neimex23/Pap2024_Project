<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.pap.publicadores.DtUsuario"%>
<%@ page import="cscorner.UsuarioLogin"%>
<%@ page import="org.pap.publicadores.DtRepartidor"%>
<%@ page import="org.pap.publicadores.DtBeneficiario"%>

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
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ayudemonos.uy</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container">
            <a class="navbar-brand" href="#">
                <img src="https://www.pngplay.com/wp-content/uploads/7/Home-Logo-Background-PNG-Image.png" alt="Logo" width="30" height="24">
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavDropdown">
                <ul class="navbar-nav">
                    <% 
                    // Obtener el usuario desde la sesión
                    String email = (String) request.getSession().getAttribute("usuario");
                    UsuarioLogin usuarioLogin = UsuarioLogin.GetInstancia();
                    DtUsuario usuarioObtenido = usuarioLogin.getUsuario();

                    // Verificar el tipo de usuario para mostrar el menú correspondiente
                    if (usuarioObtenido instanceof DtRepartidor) { // Si es Repartidor
                    %>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Distribuciones</a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="/filtrarDistribucionesPorZona.jsp">Distribuciones Por Zona</a></li>
                            <li><a class="dropdown-item" href="/verDistribuciones.jsp">Todas Las Distribuciones</a></li>
                            <li><a class="dropdown-item" href="#">Modificar Distribuciones</a></li>
                        </ul>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Perfil</a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="/verPerfil.jsp">Mi Perfil</a></li>
                            <li><a class="dropdown-item" href="#">Modificar Mi Perfil</a></li>
                        </ul>
                    </li>
                    <% 
                    } else if (usuarioObtenido instanceof DtBeneficiario) { // Si es Beneficiario
                    %>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Mis Distribuciones</a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="#">Mis Distribuciones Por Estado</a></li>
                            <li><a class="dropdown-item" href="/verDistribuciones.jsp">Todas Mis Distribuciones</a></li>
                        </ul>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Perfil</a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="/verPerfil.jsp">Mi Perfil</a></li>
                            <li><a class="dropdown-item" href="#">Modificar Mi Perfil</a></li>
                        </ul>
                    </li>
                    <% 
                    } 
                    %>
                    <li class="nav-item">
                    <a class="nav-link" href="/about.jsp">Sobre</a>
                    </li>
                </ul>
                <div class="dropdown ms-auto"> <!-- Dropdown para el botón UserLogueado -->
                    <button class="btn btn-outline-success dropdown-toggle" type="button" id="userDropdown" data-bs-toggle="dropdown" aria-expanded="false">
                        <%= usuarioObtenido.getNombre() %>
                    </button>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="userDropdown">
                        <li>
                            <form method="post" style="margin: 0;">
                                <button type="submit" name="logoutButton" class="dropdown-item" style="background: none; border: none; cursor: pointer; padding: 0; width: 100%; text-align: center;">
                                    Cerrar sesión
                                </button>
                            </form>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </nav>
</body>
</html>
