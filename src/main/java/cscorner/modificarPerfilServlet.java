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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.pap.publicadores.*;

@WebServlet("/modificarPerfilServlet")
public class modificarPerfilServlet extends HttpServlet {
    
    private ControladorPublishService cps = new ControladorPublishServiceLocator();
    private ControladorPublish controlador;
    
    public modificarPerfilServlet() {
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
    response.sendRedirect("modificarPerfil.jsp");
    }
    
   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Verificar si el usuario está autenticado
        UsuarioLogin usuarioLogin = UsuarioLogin.GetInstancia();
        if (usuarioLogin.getUsuario() == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        DtUsuario usuario = usuarioLogin.getUsuario();
        System.out.println("Usuario recuperado: " + (usuario != null ? usuario.getNombre() : "null"));

        // Obteniendo los parámetros del formulario
        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");
        String barrio = request.getParameter("barrio");
        String fechaNacimiento = request.getParameter("fechaNacimiento");
        String estado = request.getParameter("estado");
        String numeroLicencia = request.getParameter("numeroLicencia");
        
        // Si no se selecciona un nuevo barrio, se carga el que tenia el usuario
        if (barrio == null || barrio.isEmpty()) {
            barrio = (String) request.getSession().getAttribute("barrio");
        }

        if (usuario instanceof DtBeneficiario) {
            // Si es un beneficiario, actualiza sus datos
            DtBeneficiario beneficiario = (DtBeneficiario) usuario;
            
            // Se formatea la fecha de nacimiento
            Calendar fechaNacimientoCalendar = null;
            
            if (fechaNacimiento != null && !fechaNacimiento.isEmpty()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Ajusta el formato según tus necesidades
                try {
                    Date date = sdf.parse(fechaNacimiento);
                    fechaNacimientoCalendar = Calendar.getInstance();
                    fechaNacimientoCalendar.setTime(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                    // Manejo de error: la fecha no se pudo parsear
                    request.setAttribute("error", "Formato de fecha no válido.");
                    request.getRequestDispatcher("modificarPerfil.jsp").forward(request, response);
                    return;
                }
            }
            
            // obtengo el mail y el password 
            String email = beneficiario.getEmail();
            String password = beneficiario.getPassword();

            // Se llama al metodo para hacer la modificacion
            controlador.modificarBeneficiario(nombre, email, password, direccion, fechaNacimientoCalendar, estado, barrio); 
            
            // Actualiza los datos en la sesión
            HttpSession session = request.getSession();
            session.setAttribute("nombreUsuario", nombre);
            session.setAttribute("fechaNacimiento", fechaNacimiento);
            session.setAttribute("direccion", direccion);
            session.setAttribute("barrio", barrio);
            session.setAttribute("estado", estado);
        } else {
            // Si es un repartidor, actualiza sus datos
            DtRepartidor repartidor = (DtRepartidor) usuario;
            
            // obtengo el mail y el password 
            String email = repartidor.getEmail();
            String password = repartidor.getPassword();
            
            // Se llama al metodo para hacer la modificacion
            controlador.modificarRepartidor(nombre, email, password, numeroLicencia);
            
            // Actualiza los datos en la sesión
            HttpSession session = request.getSession();
            session.setAttribute("nombreUsuario", nombre);
            session.setAttribute("numeroLicencia", numeroLicencia);
            
            System.out.println("Licencia actualizada: " + numeroLicencia);
            System.out.println("Licencia en sesión: " + session.getAttribute("numeroLicencia"));
        }

        // Almacenar el mensaje en la sesión
        request.getSession().setAttribute("mensaje", "Datos actualizados correctamente");

        // Redirigir a la JSP
        response.sendRedirect("verPerfil.jsp");
    }
}

