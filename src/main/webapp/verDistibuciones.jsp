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
            <label for="Estado">Seleccione Estado:</label>
            <select name="Estado" id="Estado">
                <option value="Todas">Todas</option>
                <option value="Pendientes">Pendientes</option>
                <option value="En Camino">En Camino</option>
                <option value="Entregado">Entregado</option>
                <!-- Agrega más opciones según sea necesario -->
            </select>
            <button type="submit">Filtrar</button>
        </form>
    </body>
</html>
