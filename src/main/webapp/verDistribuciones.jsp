<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
            <c:forEach var="distribucion" items="${distribuciones}">
                <tr>
                    <td>${distribucion.getId()}</td>
                    <td>${distribucion.getEmailBenefAsc()}</td>
                    <td>${distribucion.getEstado()}</td>
                    <td>${distribucion.getFechaEntrega()}</td>
                    <td>${distribucion.getFechaPreparacion()}</td>
                    <td>${distribucion.getDonacionAsc()}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <c:if test="${not empty mensaje}">
        <p>${mensaje}</p>
    </c:if>
</body>
</html>
