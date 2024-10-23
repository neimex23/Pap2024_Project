package cscorner;

import java.io.Console;
import java.io.IOException;
import java.rmi.RemoteException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;
import org.pap.publicadores.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ControladorPublishService cps = new ControladorPublishServiceLocator();
    private ControladorPublish controlador;


    public LoginServlet() {
        super();
        try {
            controlador = cps.getControladorPublishPort();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("t1");
        String password = request.getParameter("t2");
        
        try {

            if (controlador.inicioSecion(email, password) == true) {
                UsuarioLogin usuarioLogin = UsuarioLogin.GetInstancia();
                DtUsuario usuarioObtenido = controlador.obtenerUsuario(email);
                if (usuarioObtenido instanceof DtBeneficiario beneficiarioObtenido) {
                    EnumEstadoBeneficiario estadoBeneficiario = beneficiarioObtenido.getEstado();

                    if (estadoBeneficiario == EnumEstadoBeneficiario.SUSPENDIDO) {
                        request.setAttribute("error", "Beneficiario Suspendido");
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    }
                }
                usuarioLogin.setUsuario(usuarioObtenido);                   
                request.getSession().setAttribute("usuario", email);
                response.sendRedirect("home.jsp");
            } else {
                // Login fallido
                request.setAttribute("error", "Email o contraseña incorrectos");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al conectar con el servicio");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
