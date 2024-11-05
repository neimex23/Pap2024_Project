package cscorner;

import static cscorner.verDistribucionesServlet.formatFecha;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;
import org.pap.publicadores.ControladorPublish;
import org.pap.publicadores.ControladorPublishService;
import org.pap.publicadores.ControladorPublishServiceLocator;
import org.pap.publicadores.DtBeneficiario;
import org.pap.publicadores.DtDistribucion;

/**
 *
 * @author damaso
 */
public class verDistribucionesPorEstado extends HttpServlet {

    ControladorPublishService cps = new ControladorPublishServiceLocator();
    ControladorPublish controlador;

    public verDistribucionesPorEstado() {
        super();
        try {
            controlador = cps.getControladorPublishPort();
        } catch (ServiceException e) {
            e.printStackTrace();
            // Manejo de error aquí
        }
    }
    
     public static List<DtDistribucion> formatFecha(List<DtDistribucion> listChange) {
        DateTimeFormatter[] formatosOriginales = {
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
        };

        DateTimeFormatter formatoDeseado = DateTimeFormatter.ofPattern("dd-MM-yyyy - HH:mm:ss");

        for (var dist : listChange) {
            String fechaEn = dist.getFechaEntrega();
            String fechaPre = dist.getFechaPreparacion();

            LocalDateTime fechaEntrega = null;
            LocalDateTime fechaPreparacion = null;

            for (DateTimeFormatter formato : formatosOriginales) {
                try {
                    fechaEntrega = LocalDateTime.parse(fechaEn, formato);
                    break;
                } catch (Exception e) {
                    // Ignorar y seguir intentando con el siguiente formato
                }
            }

            for (DateTimeFormatter formato : formatosOriginales) {
                try {
                    fechaPreparacion = LocalDateTime.parse(fechaPre, formato);
                    break;
                } catch (Exception e) {
                    // Ignorar y seguir intentando con el siguiente formato
                }
            }

            if (fechaEntrega != null) {
                String fechaEntregaFormateada = fechaEntrega.format(formatoDeseado);
                dist.setFechaEntrega(fechaEntregaFormateada);
            }

            if (fechaPreparacion != null) {
                String fechaPreparacionFormateada = fechaPreparacion.format(formatoDeseado);
                dist.setFechaPreparacion(fechaPreparacionFormateada);
            }
        }

        return listChange;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
 @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Obtener el estado del parámetro de la solicitud
        String estado = request.getParameter("estado");
        
        DtDistribucion[] distribuciones = controlador.listarDistribucionesPorEstado(estado);

        List<DtDistribucion> lstDistribuciones = Arrays.asList(distribuciones);
        UsuarioLogin userControler = UsuarioLogin.GetInstancia();
        List<DtDistribucion> lstDistribucionesFilter = new ArrayList<>();

        if (userControler.getUsuario() instanceof DtBeneficiario) {
            String email = userControler.getUsuario().getEmail();
            for (DtDistribucion distribucion : lstDistribuciones) {
                if (distribucion.getEmailBenefAsc().equals(email)) {
                    lstDistribucionesFilter.add(distribucion);
                }
            }
            if (!lstDistribucionesFilter.isEmpty()) {
                lstDistribucionesFilter = formatFecha(lstDistribucionesFilter);
                request.setAttribute("distribuciones", lstDistribucionesFilter);
            } else {
                request.setAttribute("distribuciones", null);
            }
        } else {
            lstDistribuciones = formatFecha(lstDistribuciones);
            request.setAttribute("distribuciones", lstDistribuciones);
        }

        // Verifica si la petición es Ajax
        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
            // Enviar solo la parte de la tabla como respuesta
            RequestDispatcher dispatcher = request.getRequestDispatcher("/ajaxTablaDistribuciones.jsp");
            dispatcher.include(request, response); // No escribir más después de esto
        } else { // Para una solicitud normal
            RequestDispatcher dispatcher = request.getRequestDispatcher("/verDistribuciones.jsp");
            dispatcher.forward(request, response); // En este caso, se puede escribir en el flujo
        }
    }
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}