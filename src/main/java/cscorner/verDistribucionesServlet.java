/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package cscorner;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;
import org.pap.publicadores.*;

/**
 *
 * @author Neimex23
 */

@WebServlet("/verDistribucionesServlet")
public class verDistribucionesServlet extends HttpServlet {
    
    private ControladorPublishService cps = new ControladorPublishServiceLocator();
    private ControladorPublish controlador;

    public verDistribucionesServlet() {
        super();
        try {
            controlador = cps.getControladorPublishPort();
        } catch (ServiceException e) {
            e.printStackTrace();
            // Manejo de error aquí
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        DtDistribucion[] distribuciones = controlador.listarDistribucionesPorEstado("PENDIENTE");

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
            }else {request.setAttribute("distribuciones", null);}  
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
    
   public static List<DtDistribucion> formatFecha(List<DtDistribucion> listChange) {
    // Lista de posibles formatos de entrada
        DateTimeFormatter[] formatosOriginales = {
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
        };

        // Formato deseado
        DateTimeFormatter formatoDeseado = DateTimeFormatter.ofPattern("dd-MM-yyyy - HH:mm:ss");

        for (var dist : listChange) {
            String fechaEn = dist.getFechaEntrega();
            String fechaPre = dist.getFechaPreparacion();

            // Convierte las fechas desde el formato original al nuevo formato
            LocalDateTime fechaEntrega = null;
            LocalDateTime fechaPreparacion = null;

            // Intenta analizar la fecha de entrega con cada formato
            for (DateTimeFormatter formato : formatosOriginales) {
                try {
                    fechaEntrega = LocalDateTime.parse(fechaEn, formato);
                    break; // Si se analiza correctamente, se sale del bucle
                } catch (Exception e) {
                    // Ignorar y seguir intentando con el siguiente formato
                }
            }

            // Intenta analizar la fecha de preparación con cada formato
            for (DateTimeFormatter formato : formatosOriginales) {
                try {
                    fechaPreparacion = LocalDateTime.parse(fechaPre, formato);
                    break; // Si se analiza correctamente, se sale del bucle
                } catch (Exception e) {
                    // Ignorar y seguir intentando con el siguiente formato
                }
            }

            // Si las fechas se analizaron correctamente, las formatea
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


}



