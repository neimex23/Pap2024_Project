<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listar Distribuciones</title>
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
    <h1>Listar Distribuciones</h1>
    <form action="verDistribucionesServlet" method="post">
    <button type="submit">Listar</button>
    </form>
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
            <c:choose>
                <c:when test="${not empty distribuciones}">
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
                </c:when>
                <c:otherwise>
                    <tr>
                        <td colspan="6">No hay distribuciones pendientes.</td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>
</body>
</html>
