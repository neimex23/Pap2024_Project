<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="cscorner.UsuarioLogin" %> 
<%
    // Verificar el estado de la sesión y Establece no cache en la pagina
    UsuarioLogin.GetInstancia().checkLogin(request, response);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Listar Distribuciones</title>
    </style>
</head>
<body>
    <!-- Incluir la barra de navegación -->
    <jsp:include page="navbar.jsp" />
    <h1>Listar Distribuciones</h1>
    
     <div id="result"></div>
     
     <script>
         document.addEventListener("DOMContentLoaded", function() {
            fetchDistribuciones(); // Llama a la función al inicio
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
</body>
</html>

