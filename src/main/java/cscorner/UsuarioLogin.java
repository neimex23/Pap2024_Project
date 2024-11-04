/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cscorner;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.pap.publicadores.*;


public class UsuarioLogin { // Esta Clase se utilizara para consultar datos sobre el usuario logeado en el sistema
    
    private static UsuarioLogin instancia = null;
    private UsuarioLogin() {}
    public static UsuarioLogin GetInstancia() {
        if (instancia == null)
            instancia = new UsuarioLogin();
        return instancia;
    }
    
    private DtUsuario usuario = null;

    public DtUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(DtUsuario usuario) {
        this.usuario = usuario;
    }

    public boolean isLogin(){return usuario!=null;}
    
    public void Logout(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException { 
        {setUsuario(null);
        // Redirigir a Login
        request.setAttribute("error", "Se Cerro Sesión");
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response); 
    }}
    
    public void checkLogin(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        if (!isLogin()) {
            //Establece la pagina sin Cache al iniciar
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
            response.setHeader("Pragma", "no-cache"); // HTTP 1.0
            response.setDateHeader("Expires", 0); // Proxies

            // Redirigir a Login
            if (!response.isCommitted()) {
                // Redirigir a Login
                request.setAttribute("error", "Debe Iniciar Sesión Primero");
                RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
            }
        }
    }
    
     
}
