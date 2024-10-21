<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Filtrar Distribuciones por Zona</title>
</head>
<body>
    <h1>Filtrar Distribuciones por Zona</h1>
    <form action="FiltrarDistribucionesPorZonaServlet" method="post">
        <label for="barrio">Seleccione un barrio:</label>
        <select name="barrio" id="barrio">
            <option value="CENTRO">Centro</option>
            <option value="CORDON">Cordón</option>
            <option value="POCITOS">Pocitos</option>
            <!-- Agrega más opciones según sea necesario -->
        </select>
        <button type="submit">Filtrar</button>
    </form>
</body>
</html>
