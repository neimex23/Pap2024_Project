<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Listar Distribuciones</title>   
</head>
<body>
    <!-- Incluir la barra de navegación -->
    <jsp:include page="navbar.jsp" />
    
    <div class="container mt-5">
        <h1 class="mb-4">Listar Distribuciones</h1>

        <div id="result" class="mt-4"></div>
        <script>
             document.addEventListener("DOMContentLoaded", function() {
                fetchDistribuciones();
                checkLoginStatus();
            });
            async function fetchDistribuciones(event) {
                try {
                    const response = await fetch('verDistribucionesServlet', {
                        method: 'POST',
                        headers: {
                                'Content-Type': 'application/x-www-form-urlencoded',
                                'X-Requested-With': 'XMLHttpRequest'
                        }
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
    </div>
</body>
</html>

