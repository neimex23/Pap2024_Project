<form id="perfilForm">
    <label for="nombre">Nombre:</label>
    <input type="text" id="nombre" name="nombre" value="${nombreUsuario}" required>

    <label for="direccion">Dirección:</label>
    <input type="text" id="direccion" name="direccion" value="${direccion}" required>

    <label for="fechaNacimiento">Fecha de Nacimiento:</label>
    <input type="date" id="fechaNacimiento" name="fechaNacimiento" value="${fechaNacimiento}" required>

    <label for="estado">Estado:</label>
    <select id="estado" name="estado" required>
        <option value="Activo" ${estado == 'Activo' ? 'selected' : ''}>Activo</option>
        <option value="Inactivo" ${estado == 'Inactivo' ? 'selected' : ''}>Inactivo</option>
    </select>

    <label for="barrio">Barrio:</label>
    <input type="text" id="barrio" name="barrio" value="${barrio}" required>

    <button type="button" onclick="enviarFormulario()">Guardar Cambios</button>
</form>

<div id="resultado"></div>

<script>
    function enviarFormulario() {
        const formData = new FormData(document.getElementById("perfilForm"));
        
        fetch("modificarPerfilServlet", {
            method: "POST",
            body: formData
        })
        .then(response => response.text())
        .then(message => {
            document.getElementById("resultado").innerText = message;
        })
        .catch(error => {
            console.error("Error al actualizar el perfil:", error);
            document.getElementById("resultado").innerText = "Ocurrió un error al actualizar el perfil.";
        });
    }
</script>
