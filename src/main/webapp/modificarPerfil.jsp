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
    <title>Modificar Perfil</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
    <!-- Incluir la barra de navegación -->
    <jsp:include page="navbar.jsp" />
    
    <div class="container text-center my-4">
        <h1>Modificar Perfil</h1> <!-- Título centrado -->
        <div class="d-flex justify-content-center mt-4 mb-3">
            <div class="input-group" style="width: auto;">
                <span class="input-group-text input-group-text-custom">${sessionScope.tipoUsuario}</span>
            </div>
        </div>

        <hr class="my-4"> <!-- Separador entre Tipo de Usuario y Datos del Usuario -->

        <form action="modificarPerfilServlet" method="post">
            <!-- Información común -->
            <div class="input-group mb-3">
                <span class="input-group-text input-group-text-custom" id="basic-addon1">Nombre</span>
                <input type="text" class="form-control" name="nombre" value="${sessionScope.nombreUsuario}" aria-label="nombre" aria-describedby="basic-addon1">
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
                    <input type="text" class="form-control" name="direccion" value="${sessionScope.direccion}" aria-label="direccion" aria-describedby="basic-addon1">
                </div>
                
                <div class="input-group mb-3">
                    <label class="input-group-text" for="barrioSelect">Barrio</label>
                    <select class="form-select" id="barrioSelect" name="barrio">
                        <option value="CENTRO">Centro</option>
                        <option value="CIUDAD_VIEJA">Ciudad Vieja</option>
                        <option value="CORDON">Cordón</option>
                        <option value="PALERMO">Palermo</option>
                        <option value="PARQUE_RODO">Parque Rodó</option>
                    </select>
                </div>

                <div class="input-group mb-3">
                    <span class="input-group-text input-group-text-custom" id="basic-addon1">Fecha de Nacimiento</span>
                    <input type="text" class="form-control" name="fechaNacimiento" value="${sessionScope.fechaNacimiento}" aria-label="fechaNac" aria-describedby="basic-addon1">
                </div>  

                <div class="input-group mb-3">
                    <label class="input-group-text" for="estadoSelect">Estado del Usuario</label>
                    <select class="form-select" id="estadoSelect" name="estado">
                        <option value="ACTIVO">Activo</option>
                        <option value="SUSPENDIDO">Suspendido</option>
                    </select>
                </div>
            </c:if>

            <!-- Información específica del Repartidor -->
            <c:if test="${sessionScope.tipoUsuario eq 'Repartidor'}">
                <div class="input-group mb-4">
                    <span class="input-group-text input-group-text-custom" id="basic-addon1">Número de Licencia</span>
                    <input type="text" class="form-control" name="numeroLicencia" value="${sessionScope.numeroLicencia}" aria-label="numeroLicencia" aria-describedby="basic-addon1">
                </div>
            </c:if>

            <button type="submit" class="btn btn-primary">Modificar Perfil</button>
        </form>
    </div>
            
    <%-- Verificar y mostrar el mensaje emergente --%>
    <c:if test="${not empty sessionScope.mensaje}">
        <script>
            alert("${sessionScope.mensaje}");
        </script>
        <c:remove var="mensaje" scope="session" />
    </c:if>
</body>
</html>

