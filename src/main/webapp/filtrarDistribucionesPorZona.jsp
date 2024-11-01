<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <title>Filtrar Distribuciones por Zona</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <!-- Incluir la barra de navegación -->
    <jsp:include page="navbar.jsp" />
    <h1>Filtrar Distribuciones por Zona</h1>
    <form id="filterForm" onsubmit="return false;" class="mb-3">
        <div class="form-group">
            <label for="barrio">Seleccione un barrio:</label>
            <select name="barrio" id="barrio" class="form-control" onchange="fetchDistribuciones(event)">
                <option value="" selected disabled></option>
                <option value="CENTRO">Centro</option>
                <option value="CIUDAD_VIEJA">Ciudad Vieja</option>
                <option value="CORDON">Cordón</option>
                <option value="PARQUE_RODO">Parque Rodó</option>
                <option value="PALERMO">Palermo</option>
            </select>
        </div>
    </form>

    <div id="result"></div> <!-- aca se muestra la tabla -->

    <script>
        async function fetchDistribuciones(event) {
            event.preventDefault(); // Evita el comportamiento predeterminado del formulario
            const select = document.getElementById('barrio');
            const barrio = select.value;

            if (barrio === "") {
                alert("Por favor, seleccione un barrio.");
                return;
            }

            try {
                const response = await fetch('filtrarDistribucionesPorZonaServlet', {
                    method: 'POST',
                    headers: {
                            'Content-Type': 'application/x-www-form-urlencoded',
                            'X-Requested-With': 'XMLHttpRequest',
                    },
                    body: 'barrio=' + encodeURIComponent(barrio)
                });

                if (response.ok) {
                    const data = await response.text();
                    document.getElementById('result').innerHTML = data; // Muestra el resultado en el div
                } else {
                    alert('Error al obtener distribuciones.');
                }
            } catch (error) {
                console.error('Error:', error);
                alert('Error al comunicarse con el servidor.');
            }
        }
    </script>
</body>
</html>
