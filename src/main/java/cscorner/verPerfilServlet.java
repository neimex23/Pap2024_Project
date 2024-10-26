package cscorner;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;
import org.pap.publicadores.*;

@WebServlet("/verPerfil")
public class verPerfilServlet extends HttpServlet {
    
    private ControladorPublishService cps = new ControladorPublishServiceLocator();
    private ControladorPublish controlador;
    
    public verPerfilServlet() {
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

        // Verificar si el usuario está autenticado
        UsuarioLogin usuarioLogin = UsuarioLogin.GetInstancia();
        if (usuarioLogin.getTipo() == UsuarioLogin.LoginL.NoLogin) {
            response.sendRedirect("login.jsp");
            return;
        }

        if (controlador == null) {
            request.setAttribute("mensaje", "Error al inicializar el controlador.");
            request.getRequestDispatcher("verPerfil.jsp").forward(request, response);
            return;
        }

        DtUsuario usuario = usuarioLogin.getUsuario();
        System.out.println("Usuario recuperado: " + (usuario != null ? usuario.getNombre() : "null"));

        if (usuario != null) {
            request.setAttribute("nombreUsuario", usuario.getNombre());
            request.setAttribute("emailUsuario", usuario.getEmail());

            if (usuario instanceof DtBeneficiario) {
                DtBeneficiario beneficiario = (DtBeneficiario) usuario;
                request.setAttribute("tipoUsuario", "Beneficiario");
                request.setAttribute("direccion", beneficiario.getDireccion());

                // Obtener y formatear la fecha de nacimiento
                org.pap.publicadores.LocalDateTime fechaNacSOAP = beneficiario.getFechaNacimiento();
                if (fechaNacSOAP != null) {
                    try {
                        // Formatear la fecha simple YYYY-MM-DD
                        String fechaNacStr = String.format("%04d-%02d-%02d",
                            fechaNacSOAP.getYear(),
                            fechaNacSOAP.getMonth(),
                            fechaNacSOAP.getDay());
                        
                        request.setAttribute("fechaNacimiento", fechaNacStr);
                        System.out.println("Fecha formateada: " + fechaNacStr);
                    } catch (Exception e) {
                        System.out.println("Error al formatear la fecha: " + e.getMessage());
                        request.setAttribute("fechaNacimiento", "Fecha no disponible");
                    }
                } else {
                    request.setAttribute("fechaNacimiento", "Fecha no disponible");
                }
                
                request.setAttribute("estado", beneficiario.getEstado());
                request.setAttribute("barrio", beneficiario.getBarrio());
            } else if (usuario instanceof DtRepartidor) {
                DtRepartidor repartidor = (DtRepartidor) usuario;
                request.setAttribute("tipoUsuario", "Repartidor");
                request.setAttribute("numeroLicencia", repartidor.getNumeroLicencia());
            }
        } else {
            request.setAttribute("mensaje", "Error al recuperar la información del usuario.");
            System.out.println("No se pudo recuperar la información del usuario");
        }

        request.getRequestDispatcher("verPerfil.jsp").forward(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UsuarioLogin usuarioLogin = UsuarioLogin.GetInstancia();
        if (usuarioLogin.getTipo() == UsuarioLogin.LoginL.NoLogin) {
            response.sendRedirect("login.jsp");
            return;
        }
        request.getRequestDispatcher("verPerfil.jsp").forward(request, response);
    }
}