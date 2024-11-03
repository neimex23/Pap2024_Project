package cscorner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.xml.rpc.ServiceException;
import org.pap.publicadores.*;

@WebServlet("/filtrarDistribucionesPorZonaServlet")
public class filtrarDistribucionesPorZonaServlet extends HttpServlet {
 
    private ControladorPublishService cps = new ControladorPublishServiceLocator();
    private ControladorPublish controlador;

    public filtrarDistribucionesPorZonaServlet() {
        super();
        try {
            controlador = cps.getControladorPublishPort();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        
        String barrio = request.getParameter("barrio");
        
        // Llamar al método del servicio para listar distribuciones por zona
        DtDistribucion[] distribuciones = controlador.listarDistribucionesPorZona(barrio);
        
        // Depuración
        System.out.println("Barrio seleccionado: " + barrio);
        System.out.println("Distribuciones: " + (distribuciones != null ? distribuciones.length : 0));
    
        // Almacenar el resultado en el request
        request.setAttribute("distribuciones", distribuciones);
    
        // Verifica si la petición es Ajax
        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
            // Enviar solo la parte de la tabla como respuesta
            RequestDispatcher dispatcher = request.getRequestDispatcher("/ajaxTablaDistribuciones.jsp");
            dispatcher.include(request, response);
        } else {    // Para una solicitud normal
            RequestDispatcher dispatcher = request.getRequestDispatcher("/filtrarDistribucionesPorZona.jsp");
            dispatcher.forward(request, response);
        }
    }
}
