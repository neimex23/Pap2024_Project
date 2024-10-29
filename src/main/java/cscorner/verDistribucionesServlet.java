/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package cscorner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        
        DtDistribucion[] distribuciones;

        try {
            distribuciones = controlador.listarDistribucionesPorEstado("PENDIENTE");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error al obtener las distribuciones.");
            return;
        }

        List<DtDistribucion> lstDistribuciones = Arrays.asList(distribuciones);
        UsuarioLogin userControler = UsuarioLogin.GetInstancia();

        if (lstDistribuciones.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            response.getWriter().write("No hay distribuciones pendientes.");
            return;
        }

        List<DtDistribucion> lstDistribucionesFilter = new ArrayList<>();

        if (userControler.getUsuario() instanceof DtBeneficiario) {
            String email = userControler.getUsuario().getEmail();
            for (DtDistribucion distribucion : lstDistribuciones) {
                if (distribucion.getEmailBenefAsc().equals(email)) {
                    lstDistribucionesFilter.add(distribucion);
                }
            }
            if (lstDistribucionesFilter.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                response.getWriter().write("No hay distribuciones pendientes para tu cuenta.");
                return;
            } else {
                request.setAttribute("distribuciones", lstDistribucionesFilter);
            }
        } else {
            request.setAttribute("distribuciones", lstDistribuciones);
        }

        // En lugar de hacer un forward, puedes retornar un JSON o una respuesta HTML
        request.getRequestDispatcher("verDistribuciones.jsp").forward(request, response);
    }
}



