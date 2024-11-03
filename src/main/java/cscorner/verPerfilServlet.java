package cscorner;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.rpc.ServiceException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Verificar si el usuario está autenticado
        UsuarioLogin usuarioLogin = UsuarioLogin.GetInstancia();
        if (usuarioLogin.getUsuario() == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        DtUsuario usuario = usuarioLogin.getUsuario();
        System.out.println("Usuario recuperado: " + (usuario != null ? usuario.getNombre() : "null"));

        if (usuario != null) {
        // Almacena los datos en la sesión
        HttpSession session = request.getSession();
        session.setAttribute("nombreUsuario", usuario.getNombre());
        session.setAttribute("emailUsuario", usuario.getEmail());

        if (usuario instanceof DtBeneficiario) {
            DtBeneficiario beneficiario = (DtBeneficiario) usuario;
            session.setAttribute("tipoUsuario", "Beneficiario");
            session.setAttribute("direccion", beneficiario.getDireccion());

            // Obtener y formatear la fecha de nacimiento
            String fechaNacSOAP = beneficiario.getFechaNacimiento();
            if (fechaNacSOAP != null) {
                try {
                    DateTimeFormatter formatoOriginal = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                    LocalDateTime fecha = LocalDateTime.parse(fechaNacSOAP, formatoOriginal);
                    DateTimeFormatter formatoNuevo = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    String fechaFormateada = fecha.format(formatoNuevo);
                    session.setAttribute("fechaNacimiento", fechaFormateada);
                } catch (Exception e) {
                    System.out.println("Error al formatear la fecha: " + e.getMessage());
                    session.setAttribute("fechaNacimiento", "Fecha no disponible");
                }
            } else {
                session.setAttribute("fechaNacimiento", "Fecha no disponible");
            }

            session.setAttribute("estado", beneficiario.getEstado());
            session.setAttribute("barrio", beneficiario.getBarrio());
        } else if (usuario instanceof DtRepartidor) {
            DtRepartidor repartidor = (DtRepartidor) usuario;
            session.setAttribute("tipoUsuario", "Repartidor");
            session.setAttribute("numeroLicencia", repartidor.getNumeroLicencia());
        }
    } else {
        request.setAttribute("mensaje", "Error al recuperar la información del usuario.");
        System.out.println("No se pudo recuperar la información del usuario");
    }

    // Redirigir a la JSP
    response.sendRedirect("verPerfil.jsp");
    }
}