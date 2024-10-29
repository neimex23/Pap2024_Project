<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Mi Perfil</title>
    <style>
        .profile-container {
            max-width: 800px;
            margin: 20px auto;
            padding: 30px;
            border: 1px solid #ddd;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        .profile-header {
            text-align: center;
            margin-bottom: 30px;
            padding-bottom: 20px;
            border-bottom: 2px solid #eee;
        }
        .profile-field {
            margin: 15px 0;
            padding: 10px;
            background-color: #f9f9f9;
            border-radius: 4px;
        }
        .profile-label {
            font-weight: bold;
            color: #333;
            width: 150px;
            display: inline-block;
        }
        .profile-value {
            color: #666;
        }
        .error-message {
            color: #dc3545;
            text-align: center;
            padding: 10px;
            margin: 10px 0;
            background-color: #fff3f3;
            border-radius: 4px;
        }
        button {
            display: block;
            width: 200px;
            margin: 20px auto;
            padding: 12px 20px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        button:hover {
            background-color: #0056b3;
        }
        .user-type-badge {
            display: inline-block;
            padding: 5px 10px;
            background-color: #28a745;
            color: white;
            border-radius: 15px;
            font-size: 14px;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <div class="profile-container">
        <div class="profile-header">
            <h1>Mi Perfil</h1>
            <c:if test="${not empty tipoUsuario}">
                <div class="user-type-badge">${tipoUsuario}</div>
            </c:if>
        </div>
        
        <form action="verPerfil" method="post">
            <button type="submit">Actualizar Información</button>
        </form>
        
        <c:if test="${not empty mensaje}">
            <div class="error-message">
                <p>${mensaje}</p>
            </div>
        </c:if>
        
        <c:if test="${not empty nombreUsuario}">
            <!-- Información común -->
            <div class="profile-field">
                <span class="profile-label">Nombre:</span>
                <span class="profile-value">${nombreUsuario}</span>
            </div>
            <div class="profile-field">
                <span class="profile-label">Email:</span>
                <span class="profile-value">${emailUsuario}</span>
            </div>
            
            <!-- Información específica del Beneficiario -->
            <c:if test="${tipoUsuario eq 'Beneficiario'}">
                <div class="profile-field">
                    <span class="profile-label">Dirección:</span>
                    <span class="profile-value">${direccion}</span>
                </div>
                <div class="profile-field">
                    <span class="profile-label">Fecha de Nacimiento:</span>
                    <span class="profile-value">
                        <c:choose>
                            <c:when test="${empty fechaNacimiento or fechaNacimiento eq 'Fecha no disponible'}">
                                Fecha no disponible
                            </c:when>
                            <c:otherwise>
                                ${fechaNacimiento}
                            </c:otherwise>
                        </c:choose>
                    </span>
                </div>
                <div class="profile-field">
                    <span class="profile-label">Estado:</span>
                    <span class="profile-value">${estado}</span>
                </div>
                <div class="profile-field">
                    <span class="profile-label">Barrio:</span>
                    <span class="profile-value">${barrio}</span>
                </div>
            </c:if>
            
            <!-- Información específica del Repartidor -->
            <c:if test="${tipoUsuario eq 'Repartidor'}">
                <div class="profile-field">
                    <span class="profile-label">Número de Licencia:</span>
                    <span class="profile-value">${numeroLicencia}</span>
                </div>
            </c:if>
        </c:if>
    </div>
</body>
</html>