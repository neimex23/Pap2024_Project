/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package cscorner;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;
import org.pap.publicadores.ControladorPublishServiceLocator;

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
        
    }


}
