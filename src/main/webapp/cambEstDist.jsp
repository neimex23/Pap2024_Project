<%@page import="java.time.temporal.ChronoField"%>
<%@page import="java.time.format.DateTimeFormatterBuilder"%>
<%@page import="org.pap.publicadores.DtDistribucion"%>
<%@page import="java.util.List"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    DateTimeFormatter formatter = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd'T'HH:mm:ss")
            .optionalStart()
            .appendFraction(ChronoField.MILLI_OF_SECOND, 1, 3, true)
            .optionalEnd()
            .toFormatter();
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Cambiar Estado de Distribución</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script>
            function cambiarEstadoDistribucion(idDistribucion) {
                const nuevoEstado = $('#estado_' + idDistribucion).val();
                const fechaEntrega = $('#fechaEntrega_' + idDistribucion).val();

                $.ajax({
                    url: 'CambEstServlet',
                    method: 'POST',
                    data: { idDistribucion, nuevoEstado, fechaEntrega },
                    success: function (response) {
                        alert(response.mensaje);
                    },
                    error: function () {
                        alert('Error al cambiar el estado.');
                    }
                });
            }
        </script>
    </head>
    <body class="container my-4">
        <jsp:include page="navbar.jsp" />
        <h1 class="text-center mb-4">Cambiar Estado de Distribución</h1>
        <div class="table-responsive">
            <table class="table table-bordered table-striped">
                <thead class="thead-dark">
                    <tr>
                        <th>ID</th>
                        <th>Repartidor</th>
                        <th>Estado</th>
                        <th>Fecha de Entrega</th>
                        <th>Acción</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<DtDistribucion> distribuciones = (List<DtDistribucion>) request.getAttribute("distribuciones");
                        if (distribuciones != null) {
                            for (DtDistribucion dist : distribuciones) {
                                LocalDateTime fechaEntrega = LocalDateTime.parse(dist.getFechaEntrega(), formatter);
                                String formattedFechaEntrega = fechaEntrega.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
                    %>
                    <tr>
                        <td><%= dist.getId() %></td>
                        <td><%= dist.getEmailBenefAsc() %></td>
                        <td>
                            <select class="form-control" id="estado_<%= dist.getId() %>">
                                <option value="PENDIENTE" <%= "PENDIENTE".equals(dist.getEstado().toString()) ? "selected" : "" %>>Pendiente</option>
                                <option value="ENCAMINO" <%= "ENCAMINO".equals(dist.getEstado().toString()) ? "selected" : "" %>>En camino</option>
                                <option value="ENTREGADO" <%= "ENTREGADO".equals(dist.getEstado().toString()) ? "selected" : "" %>>Entregado</option>
                            </select>
                        </td>
                        <td>
                            <input type="datetime-local" class="form-control" id="fechaEntrega_<%= dist.getId() %>" value="<%= formattedFechaEntrega %>" required>
                        </td>
                        <td>
                            <button class="btn btn-primary" onclick="cambiarEstadoDistribucion(<%= dist.getId() %>)">Cambiar Estado</button>
                        </td>
                    </tr>
                    <%
                            }
                        } else {
                    %>
                    <tr>
                        <td colspan="5" class="text-center">No hay distribuciones disponibles</td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </body>
</html>
