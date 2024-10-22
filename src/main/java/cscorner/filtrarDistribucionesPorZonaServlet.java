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
        
        // Almacenar el resultado en el request
        request.setAttribute("distribuciones", distribuciones);
        
        // Redirigir a una página de resultados (HTML o JSP)
        RequestDispatcher dispatcher = request.getRequestDispatcher("/resultadosDistribuciones.html");
        dispatcher.forward(request, response);
    }
}
