<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="cscorner.UsuarioLogin" %> 
<%
    // Verificar el estado de la sesión y Establece no cache en la pagina
    UsuarioLogin.GetInstancia().checkLogin(request, response);
%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<c:choose>
    <c:when test="${not empty distribuciones}">
        <table class="table table-striped">
            <thead class="thead-light">
                <tr>
                    <th>ID</th>
                    <th>Email Beneficiario</th>
                    <th>Estado</th>
                    <th>Fecha Entrega</th>
                    <th>Fecha Preparacion</th>
                    <th>ID Donacion</th>
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


