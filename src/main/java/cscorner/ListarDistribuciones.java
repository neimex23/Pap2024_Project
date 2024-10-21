/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package cscorner;

import java.io.IOException;
import static java.time.InstantSource.system;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;
import org.pap.publicadores.*;

/**
 *
 * @author Neimex23
 */
public class ListarDistribuciones extends HttpServlet {

    private org.pap.publicadores.ControladorPublish controlador;
    private UsuarioLogin userLogin = UsuarioLogin.GetInstancia();

    public ListarDistribuciones() {
        super();
        try {
            controlador = new ControladorPublishServiceLocator().getControladorPublishPort();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
    
    @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {

    DtDistribucion[] distribuciones = controlador.listarDistribucionesPorEstado("PENDIENTE");

    // Valida que no sea null o vacío antes de proceder
    if (distribuciones != null && distribuciones.length > 0) {
        request.setAttribute("distribuciones", distribuciones);
    } else {
        // En caso de no encontrar distribuciones, puedes enviar un mensaje informativo
        request.setAttribute("mensaje", "No hay distribuciones pendientes.");
    }

    // Redirige a la JSP
    request.getRequestDispatcher("verDistribuciones.jsp").forward(request, response);

    // Imprime el log en el servidor
    System.out.println("Botón Listar presionado.");
}



}
