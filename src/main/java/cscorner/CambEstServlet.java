package cscorner;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;
import org.pap.publicadores.ControladorPublish;
import org.pap.publicadores.ControladorPublishService;
import org.pap.publicadores.ControladorPublishServiceLocator;
import org.pap.publicadores.DtDistribucion;


/*Por Damaso*/

public class CambEstServlet extends HttpServlet {

    private ControladorPublish controlador;

    @Override
    public void init() throws ServletException {
        try {
            ControladorPublishService cps = new ControladorPublishServiceLocator();
            controlador = cps.getControladorPublishPort();
        } catch (ServiceException ex) {
            Logger.getLogger(CambEstServlet.class.getName()).log(Level.SEVERE, "Error al inicializar el controlador", ex);
            throw new ServletException("No se pudo inicializar el controlador", ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CheckLogin(request,response);
        try {
            DtDistribucion[] distribucionesArray = controlador.listarDistribuciones();
            List<DtDistribucion> distribuciones = Arrays.asList(distribucionesArray);
            request.setAttribute("distribuciones", distribuciones);
            request.getRequestDispatcher("cambEstDist.jsp").forward(request, response);
        } catch (Exception e) {
            Logger.getLogger(CambEstServlet.class.getName()).log(Level.SEVERE, "Error al obtener las distribuciones", e);
            response.setContentType("application/json");
            response.getWriter().write("{\"mensaje\":\"Error al obtener las distribuciones\"}");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CheckLogin(request,response);
        try {
            int idDistribucion = Integer.parseInt(request.getParameter("idDistribucion"));
            LocalDateTime fechaEntrega = LocalDateTime.parse(request.getParameter("fechaEntrega"), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
            String nuevoEstado = request.getParameter("nuevoEstado");
            
            // Conversión de LocalDateTime a Calendar
            Calendar fechaEntregaCalendar = Calendar.getInstance();
            fechaEntregaCalendar.set(fechaEntrega.getYear(), fechaEntrega.getMonthValue() - 1, fechaEntrega.getDayOfMonth(),
                                     fechaEntrega.getHour(), fechaEntrega.getMinute(), fechaEntrega.getSecond());
            
            // Log para mostrar los datos enviados al método modificarDistribucion
            Logger.getLogger(CambEstServlet.class.getName()).log(Level.INFO, "Modificando distribución con ID: {0}, Fecha de Entrega: {1}, Nuevo Estado: {2}", 
                new Object[]{idDistribucion, fechaEntregaCalendar.getTime(), nuevoEstado});

            // Confirmación de los valores en consola
            System.out.println("ID Distribución: " + idDistribucion);
            System.out.println("Fecha de Entrega (Calendar): " + fechaEntregaCalendar.getTime());
            System.out.println("Nuevo Estado: " + nuevoEstado);

            controlador.modificarDistribucion(idDistribucion, fechaEntregaCalendar, nuevoEstado);

            response.setContentType("application/json");
            response.getWriter().write("{\"mensaje\":\"Estado modificado correctamente\"}");

        } catch (Exception e) {
            Logger.getLogger(CambEstServlet.class.getName()).log(Level.SEVERE, "Error al modificar el estado", e);
            response.setContentType("application/json");
            response.getWriter().write("{\"mensaje\":\"Error al modificar el estado\"}");
        }
    }
    
    private void CheckLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Elimina el cache en todas las paginas
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        response.setDateHeader("Expires", 0); // Proxies

        //Chequea si el usuario esta en sesion, se asigna parametro usuario en /LoginServlet
        if (request.getSession().getAttribute("usuario") == null){
                request.setAttribute("error", "Debe Iniciar Sesión Primero");
                RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
        }

        // Verifica si se ha presionado el botón de cierre de sesión
        if (request.getParameter("logoutButton") != null) {
            request.getSession(false).invalidate(); 

            request.setAttribute("error", "Se Cerro Sesión");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response); 
        }
    }
}
