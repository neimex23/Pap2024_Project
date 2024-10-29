<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="cscorner.UsuarioLogin" %> 
<%
    // Verificar el estado de la sesión y Establece no cache en la pagina
    UsuarioLogin.GetInstancia().checkLogin(request, response);
%>
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

