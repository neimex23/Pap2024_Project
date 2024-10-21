<%-- 
    Document   : verDistibuciones
    Created on : 21 oct 2024, 5:59:23 p. m.
    Author     : Neimex23
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Listar Distribuciones</h1>
        <form action="ListarDistribuciones" method="post">
            <button type="submit">Listar</button>
        </form>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>EmailBeneficiario</th>
                    <th>Estado</th>
                    <th>FechaEntrega</th>
                    <th>FechaPreparacion</th>
                    <th>IdDonacion</th>
                    <!-- Añade aquí más columnas si es necesario -->
                </tr>
            </thead>
            <tbody>
                <!-- Itera sobre el array 'distribuciones' para crear filas dinámicamente -->
                <c:forEach var="distribucion" items="${distribuciones}">
                    <tr>
                        <td>${distribucion.Id}</td>
                        <td>${distribucion.emailBenefAsc}</td>
                        <td>${distribucion.estado}</td>
                        <td>${distribucion.fechaEntrega}</td>
                        <td>${distribucion.fechaPreparacion()}</td>
                        <td>${distribucion.donacionAsc}</td>
                        <!-- Añade más columnas si es necesario -->
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </body>
</html>
