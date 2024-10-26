<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Filtrar Distribuciones por Zona</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 8px;
            border: 1px solid #ddd;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <h1>Filtrar Distribuciones por Zona</h1>
    <form action="filtrarDistribucionesPorZonaServlet" method="post">
        <label for="barrio">Seleccione un barrio:</label>
        <select name="barrio" id="barrio">
            <option value="TODAS">Todas</option>
            <option value="CENTRO">Centro</option>
            <option value="CIUDAD_VIEJA">Ciudad Vieja</option>
            <option value="CORDON">Cordon</option>
            <option value="PARQUE_RODO">Parque Rodo</option>            
            <option value="PALERMO">Palermo</option>
        </select>
        <button type="submit">Filtrar</button>
    </form>
<%
String barrioSeleccionado = request.getParameter("barrio");

if (barrioSeleccionado == null) { // Primera vez que se entra a la pagina web
%>
    <h2>No se ha seleccionado ningún barrio</h2>
<%
    } else { // Luego de seleccionar algun barrio
%>
    <c:choose>
        <c:when test="${param.barrio == 'TODAS'}">
            <h2>Distribuciones en las Zonas</h2>
        </c:when>
        <c:when test="${param.barrio == 'CENTRO'}">
            <h2>Distribuciones en Centro</h2>
        </c:when>
        <c:when test="${param.barrio == 'CIUDAD_VIEJA'}">
            <h2>Distribuciones en Ciudad Vieja</h2>
        </c:when>
        <c:when test="${param.barrio == 'CORDON'}">
            <h2>Distribuciones en Cordón</h2>
        </c:when>
        <c:when test="${param.barrio == 'PARQUE_RODO'}">
            <h2>Distribuciones en Parque Rodó</h2>
        </c:when>
        <c:when test="${param.barrio == 'PALERMO'}">
            <h2>Distribuciones en Palermo</h2>
        </c:when>     
        <c:otherwise>
            <h2>No se ha seleccionado ningún barrio</h2>
        </c:otherwise>
    </c:choose>

    <c:choose>
        <c:when test="${not empty distribuciones}">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Email Beneficiario</th>
                        <th>Estado</th>
                        <th>Fecha Entrega</th>
                        <th>Fecha Preparación</th>
                        <th>ID Donación</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="distribucion" items="${distribuciones}">
                        <tr>
                            <td>${distribucion.id}</td>
                            <td>${distribucion.emailBenefAsc}</td>
                            <td>${distribucion.estado}</td>
                            <td>${distribucion.fechaEntrega}</td>
                            <td>${distribucion.fechaPreparacion}</td>
                            <td>${distribucion.donacionAsc}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <h2>No hay distribuciones pendientes</h2>
        </c:otherwise>
    </c:choose>
<%
} // Cierra el else 
%>
</body>
</html>

