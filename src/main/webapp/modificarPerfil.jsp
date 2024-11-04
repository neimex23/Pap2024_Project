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
                <span class="input-group-text input-group-text-custom" id="basic-addon1">Nombre Actual</span>
                <input type="text" class="form-control" name="OLD_name" value="${sessionScope.nombreUsuario}" aria-describedby="basic-addon1" readonly>
            </div>

            <div class="input-group mb-3">
                <span class="input-group-text input-group-text-custom" id="basic-addon1">Nuevo Nombre</span>
                <input type="text" class="form-control" name="NEW_name" placeholder="Ingresar..." aria-describedby="basic-addon1">
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
                <!-- Constraseña -->
                <div class="input-group mb-3"> 
                    <span class="input-group-text input-group-text-custom" id="basic-addon1">Nueva Contraseña</span> 
                    <input type="password" class="form-control" name="NEW_passwordBeneficiario" placeholder="Ingresar..." aria-describedby="basic-addon1"> 
                </div>
                
                <!-- Dirección -->
                <div class="input-group mb-3">
                    <span class="input-group-text input-group-text-custom" id="basic-addon1">Dirección Actual</span>
                    <input type="text" class="form-control" name="OLD_direction" value="${sessionScope.direccion}" aria-describedby="basic-addon1" readonly>
                </div>
                
                <div class="input-group mb-3">
                    <span class="input-group-text input-group-text-custom" id="basic-addon1">Nueva Dirección</span>
                    <input type="text" class="form-control" name="NEW_direction" placeholder="Ingresar..." aria-describedby="basic-addon1">
                </div>                
                
                <!-- Barrio -->
                <div class="input-group mb-3">
                    <span class="input-group-text input-group-text-custom" id="basic-addon1">Barrio Actual</span>
                    <input type="text" class="form-control" name="OLD_barrio" 
                           value="${sessionScope.barrio}"
                           data-display-value="<c:choose>
                                               <c:when test="${sessionScope.barrio eq 'CENTRO'}">Centro</c:when>
                                               <c:when test="${sessionScope.barrio eq 'CIUDAD_VIEJA'}">Ciudad Vieja</c:when>
                                               <c:when test="${sessionScope.barrio eq 'CORDON'}">Cordón</c:when>
                                               <c:when test="${sessionScope.barrio eq 'PALERMO'}">Palermo</c:when>
                                               <c:when test="${sessionScope.barrio eq 'PARQUE_RODO'}">Parque Rodó</c:when>
                                               <c:otherwise>Barrio no seleccionado</c:otherwise>
                                               </c:choose>"
                           readonly>
                </div>
            
                <div class="input-group mb-3">
                    <label class="input-group-text" for="NEW_barrioSelect">Nuevo Barrio</label>
                    <select class="form-select" id="NEW_barrioSelect" name="NEW_barrio" onchange="handleSelectChange(this)">
                        <option value="" disabled selected>Seleccionar...</option>
                        <option value="CENTRO">Centro</option>
                        <option value="CIUDAD_VIEJA">Ciudad Vieja</option>
                        <option value="CORDON">Cordón</option>
                        <option value="PALERMO">Palermo</option>
                        <option value="PARQUE_RODO">Parque Rodó</option>
                    </select>
                </div>
                
                <script>
                    function handleSelectChange(select) {
                        if (select.options[0].selected) {
                            select.options[0].disabled = true; // Deshabilita la opción "Seleccionar..."
                        }
                    }
                </script>

                <!-- Fecha de Nacimiento -->
                <div class="input-group mb-3">
                    <span class="input-group-text input-group-text-custom" id="basic-addon1">Fecha de Nacimiento Actual</span>
                    <input type="text" class="form-control" name="OLD_birthdate" value="${sessionScope.fechaNacimiento}" aria-describedby="basic-addon1" readonly>
                </div>
                
                <div class="input-group mb-3">
                    <span class="input-group-text input-group-text-custom" id="basic-addon1">Nueva Fecha</span>
                    <input type="text" class="form-control" name="NEW_birthdate" placeholder="Ingresar... (yyyy-mm-dd)" aria-describedby="basic-addon1">
                </div>  
                
                <!-- Estado del Usuario -->
                <div class="input-group mb-3">
                    <span class="input-group-text input-group-text-custom" id="basic-addon1">Estado Actual del Usuario</span>
                    <input type="text" class="form-control" name="ODL_status" 
                           value="${sessionScope.estado}" 
                           data-display-value="<c:choose>
                                               <c:when test="${sessionScope.estado eq 'ACTIVO'}">Activo</c:when>
                                               <c:when test="${sessionScope.estado eq 'SUSPENDIDO'}">Suspendido</c:when>
                                               <c:otherwise>Estado no seleccionado</c:otherwise>
                                               </c:choose>"
                           readonly>
                </div>
                
                <div class="input-group mb-3">
                    <label class="input-group-text" for="NEW_estadoSelect">Nuevo Estado</label>
                    <select class="form-select" id="NEW_estadoSelect" name="NEW_status"onchange="handleSelectChange(this)">
                        <option value="" disabled selected>Seleccionar...</option>>
                        <option value="ACTIVO">Activo</option>
                        <option value="SUSPENDIDO">Suspendido</option>
                    </select>
                </div>
                        
                <script>
                    function handleSelectChange(select) {
                        if (select.options[0].selected) {
                            select.options[0].disabled = true; // Deshabilita la opción "Seleccionar..."
                        }
                    }
                </script>                        
            </c:if>

            <!-- Información específica del Repartidor -->
            <c:if test="${sessionScope.tipoUsuario eq 'Repartidor'}">
                <!-- Constraseña -->
                <div class="input-group mb-3"> 
                    <span class="input-group-text input-group-text-custom" id="basic-addon1">Nueva Contraseña</span> 
                    <input type="password" class="form-control" name="NEW_passwordRepartidor" placeholder="Ingresar..." aria-describedby="basic-addon1"> 
                </div>
                
                <!-- Licencia -->
                <div class="input-group mb-4">
                    <span class="input-group-text input-group-text-custom" id="basic-addon1">Número de Licencia Actual</span>
                    <input type="text" class="form-control" name="OLD_license" value="${sessionScope.numeroLicencia}" aria-describedby="basic-addon1" readonly>
                </div>
                
                <div class="input-group mb-3">
                    <span class="input-group-text input-group-text-custom" id="basic-addon1">Nueva Número de Licencia</span>
                    <input type="text" class="form-control" name="NEW_license" placeholder="Ingresar..." aria-describedby="basic-addon1">
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