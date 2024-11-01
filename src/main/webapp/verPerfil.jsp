<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="cscorner.UsuarioLogin" %> 
<%
    // Verificar el estado de la sesión y Establece no cache en la pagina
    UsuarioLogin.GetInstancia().checkLogin(request, response);
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mi Perfil</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <!-- Incluir la barra de navegación -->
    <jsp:include page="navbar.jsp" />
    <div class="container mt-4">
        <div class="card">
            <div class="card-header text-center">
                <h1>Mi Perfil</h1>
                <c:if test="${not empty tipoUsuario}">
                    <span class="badge badge-success">${tipoUsuario}</span>
                </c:if>
            </div>
            <div class="card-body">
                <form action="verPerfil" method="post">
                    <button type="submit" class="btn btn-primary btn-block">Actualizar Información</button>
                </form>

                <c:if test="${not empty mensaje}">
                    <div class="alert alert-danger text-center" role="alert">
                        <p>${mensaje}</p>
                    </div>
                </c:if>

                <c:if test="${not empty nombreUsuario}">
                    <!-- Información común -->
                    <div class="mb-3">
                        <label class="font-weight-bold">Nombre:</label>
                        <p>${nombreUsuario}</p>
                    </div>
                    <div class="mb-3">
                        <label class="font-weight-bold">Email:</label>
                        <p>${emailUsuario}</p>
                    </div>

                    <!-- Información específica del Beneficiario -->
                    <c:if test="${tipoUsuario eq 'Beneficiario'}">
                        <div class="mb-3">
                            <label class="font-weight-bold">Dirección:</label>
                            <p>${direccion}</p>
                        </div>
                        <div class="mb-3">
                            <label class="font-weight-bold">Fecha de Nacimiento:</label>
                            <p>
                                <c:choose>
                                    <c:when test="${empty fechaNacimiento or fechaNacimiento eq 'Fecha no disponible'}">
                                        Fecha no disponible
                                    </c:when>
                                    <c:otherwise>
                                        ${fechaNacimiento}
                                    </c:otherwise>
                                </c:choose>
                            </p>
                        </div>
                        <div class="mb-3">
                            <label class="font-weight-bold">Estado:</label>
                            <p>${estado}</p>
                        </div>
                        <div class="mb-3">
                            <label class="font-weight-bold">Barrio:</label>
                            <p>${barrio}</p>
                        </div>
                    </c:if>

                    <!-- Información específica del Repartidor -->
                    <c:if test="${tipoUsuario eq 'Repartidor'}">
                        <div class="mb-3">
                            <label class="font-weight-bold">Número de Licencia:</label>
                            <p>${numeroLicencia}</p>
                        </div>
                    </c:if>
                </c:if>
            </div>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
