<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Listar Distribuciones Ajax</title>
</head>
<c:choose>
    <c:when test="${not empty distribuciones}">
        <div class="table-responsive">
            <table class="table table-striped table-bordered">
                <thead class="thead-dark">
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
        </div>
    </c:when>
     <c:otherwise>
            <div class="alert alert-info" role="alert">
                <h4 class="alert-heading">Informacion</h4>
                <p>No hay distribuciones pendientes</p>
            </div>
     </c:otherwise>
</c:choose>


