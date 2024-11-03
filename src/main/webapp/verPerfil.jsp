<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0
    response.setDateHeader("Expires", 0); // Proxies
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mi Perfil</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
    <!-- Incluir la barra de navegación -->
    <jsp:include page="navbar.jsp" />
    
    <div class="container text-center my-4">
        <h1>Mi perfil</h1> <!-- Título centrado -->
        <div class="d-flex justify-content-center mt-4 mb-3">
                <div class="input-group" style="width: auto;">
                        <span class="input-group-text input-group-text-custom">${sessionScope.tipoUsuario}</span>
                </div>
        </div>

        <hr class="my-4"> <!-- Separador entre Tipo de Usuario y Datos del Usuario -->
        
        <!-- Información común -->
        <div class="input-group mb-3">
                <span class="input-group-text input-group-text-custom" id="basic-addon1">Nombre</span>
                <div class="form-control text-start" aria-label="nombre" aria-describedby="basic-addon1">${sessionScope.nombreUsuario}</div>
        </div>
        
        <c:if test="${not empty sessionScope.emailUsuario}">
            <c:set var="emailUsuario" value="${sessionScope.emailUsuario}" />
            <c:set var="correo" value="${fn:substringBefore(emailUsuario, '@')}" />
            <c:set var="dominio" value="${fn:substring(emailUsuario, fn:indexOf(emailUsuario, '@'), fn:length(emailUsuario))}" />
        </c:if>
        <c:if test="${empty sessionScope.emailUsuario}">
            <c:set var="correo" value="Desconocido" />
            <c:set var="dominio" value="@desconocido.com" />
        </c:if>

        <div class="input-group mb-3">
            <span class="input-group-text input-group-text-custom" id="basic-addon1">Email</span>
            <div class="form-control text-start" aria-label="email2" aria-describedby="basic-addon2">${correo}</div>
            <span class="input-group-text input-group-text-custom" id="basic-addon2">${dominio}</span>
        </div>

        <!-- Información específica del Beneficiario -->
        <c:if test="${sessionScope.tipoUsuario eq 'Beneficiario'}">
            <div class="input-group mb-3">
                <span class="input-group-text input-group-text-custom" id="basic-addon1">Dirección</span>
                <div class="form-control text-start" aria-label="direccion" aria-describedby="basic-addon1">${sessionScope.direccion}</div>
            </div>

            <div class="input-group mb-3">
                <span class="input-group-text input-group-text-custom" id="basic-addon1">Barrio</span>
                <div class="form-control text-start" aria-label="barrio" aria-describedby="basic-addon1">${sessionScope.barrio}</div>
            </div>

            <div class="input-group mb-3">
                <span class="input-group-text input-group-text-custom" id="basic-addon1">Fecha de Nacimiento</span>
                <div class="form-control text-start" aria-label="fechaNac" aria-describedby="basic-addon1">${sessionScope.fechaNacimiento}</div>
            </div>

            <div class="input-group mb-4">
                <span class="input-group-text input-group-text-custom" id="basic-addon1">Estado del Usuario</span>
                <div class="form-control text-start" aria-label="estado" aria-describedby="basic-addon1">${sessionScope.estado}</div>
            </div>
        </c:if>
        
        <!-- Información específica del Repartidor -->
        <c:if test="${sessionScope.tipoUsuario eq 'Repartidor'}">
            <div class="input-group mb-4">
                <span class="input-group-text input-group-text-custom" id="basic-addon1">Número de Licencia</span>
                <div class="form-control text-start" aria-label="estado" aria-describedby="basic-addon1">${sessionScope.numeroLicencia}</div>
            </div>
        </c:if>
    </div>
</body>
</html>