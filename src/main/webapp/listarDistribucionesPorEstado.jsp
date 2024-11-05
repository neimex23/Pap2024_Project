<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="cscorner.UsuarioLogin" %>

<%
    // Verificar el estado de la sesión y establece no cache en la pagina
    UsuarioLogin.GetInstancia().checkLogin(request, response);
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Listar Distribuciones por Estado</title>

    <!-- Vinculación con Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Bootstrap Icons (opcional) -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="navbar.jsp" />

    <div class="container mt-4">
        <h1>Listar Distribuciones</h1>

        <!-- Formulario con clases de Bootstrap -->
        <form id="filterForm" class="form-inline">
            <div class="mb-3">
                <label for="estado" class="form-label">Filtrar por estado:</label>
                <select id="estado" name="estado" class="form-select">
                    <option value="PENDIENTE">Pendiente</option>
                    <option value="ENCAMINO">En Camino</option>
                    <option value="ENTREGADO">Entregado</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Filtrar</button>
        </form>

        <!-- Div para mostrar los resultados -->
        <div id="result" class="mt-4"></div>
    </div>

    <!-- Scripts necesarios para Bootstrap -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"></script>

    <!-- Script para manejar la lógica del formulario -->
    <script>
        document.getElementById("filterForm").addEventListener("submit", async function (event) {
            event.preventDefault(); // Evitar el envío del formulario

            const estado = document.getElementById("estado").value;
            const response = await fetch('verDistribucionesPorEstado', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                    'X-Requested-With': 'XMLHttpRequest'
                },
                body: new URLSearchParams({ estado })
            });

            if (response.ok) {
                const data = await response.text();
                document.getElementById('result').innerHTML = data; // Muestra el resultado en el div
            } else {
                alert('Error al obtener distribuciones.');
            }
        });
    </script>
</body>
</html>
