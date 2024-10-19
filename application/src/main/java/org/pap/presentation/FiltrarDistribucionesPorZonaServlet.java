package org.pap.presentation;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import org.pap.publicadores.ControladorPublish;
import org.pap.dtClasses.DTDistribucion;

public class FiltrarDistribucionesPorZonaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        
        String barrio = request.getParameter("barrio");
        
        // Crear una instancia del servicio web
        ControladorPublish controlador = new ControladorPublish();
        
        // Llamar al método del servicio para listar distribuciones por zona
        DTDistribucion[] distribuciones = controlador.ListarDistribucionesPorZona(barrio);
        
        // Almacenar el resultado en el request
        request.setAttribute("distribuciones", distribuciones);
        
        // Redirigir a una página de resultados (HTML o JSP)
        RequestDispatcher dispatcher = request.getRequestDispatcher("/resultadosDistribuciones.html");
        dispatcher.forward(request, response);
    }
}
