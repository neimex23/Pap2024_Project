/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package cscorner;

import java.io.IOException;
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
            // Aquí podrías redirigir a una página de error o enviar un mensaje
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (controlador == null) {
            request.setAttribute("mensaje", "Error al inicializar el controlador.");
            request.getRequestDispatcher("verDistribuciones.jsp").forward(request, response);
            return;
        }
        
        DtDistribucion[] distribuciones = controlador.listarDistribucionesPorEstado("PENDIENTE");
        List<DtDistribucion> lstDistribuciones = Arrays.asList(distribuciones);
        UsuarioLogin userControler = UsuarioLogin.GetInstancia();
        if (userControler.getTipo() == UsuarioLogin.LoginL.Beneficiario) {
           String email = userControler.getUsuario().getEmail();
           lstDistribuciones.removeIf(x -> x.getEmailBenefAsc() != email);
        }


        
        for (DtDistribucion distribucion : lstDistribuciones) {
            System.out.println("ID: " + distribucion.getId());
            System.out.println("Fecha de Preparación: " + distribucion.getFechaPreparacion());
            System.out.println("Fecha de Entrega: " + distribucion.getFechaEntrega());
            System.out.println("Estado: " + distribucion.getEstado());
            System.out.println("Donación ASC: " + distribucion.getDonacionAsc());
            System.out.println("Email Beneficiario ASC: " + distribucion.getEmailBenefAsc());
            System.out.println("-------------------------------");
        }
        
        
        if (!lstDistribuciones.isEmpty()) {
            request.setAttribute("distribuciones", lstDistribuciones);
        } else {
            request.setAttribute("mensaje", "No hay distribuciones pendientes.");
        }
        
        request.getRequestDispatcher("verDistribuciones.jsp").forward(request, response);
        
        
    }
}
