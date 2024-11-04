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
        
        // Creo las variables para modificar al usuario
        String modName;
        String modDirection;
        String modBarrio;
        String modBirthdate;
        String modStatus;
        String modLicencia;
        String modPassBeneficiario;
        String modPassRepartidor;
        
        if (usuario instanceof DtBeneficiario) {
            // Si es un beneficiario, actualiza sus datos
            DtBeneficiario beneficiario = (DtBeneficiario) usuario;
            
            // Obtengo los datos anteriores y actuales del usuario
            String OLD_name = request.getParameter("OLD_name");
            String NEW_name = request.getParameter("NEW_name");

            String OLD_direction = request.getParameter("OLD_direction");
            String NEW_direction = request.getParameter("NEW_direction");

            String OLD_barrio = request.getParameter("OLD_barrio");
            String NEW_barrio = request.getParameter("NEW_barrio");

            String OLD_birthdate = request.getParameter("OLD_birthdate");
            String NEW_birthdate = request.getParameter("NEW_birthdate");

            String ODL_status = request.getParameter("ODL_status");
            String NEW_status = request.getParameter("NEW_status");

            String OLD_passBene = beneficiario.getPassword();
            String NEW_passBene = request.getParameter("NEW_passwordBeneficiario");
            
            String email = beneficiario.getEmail();
            
            // Comparo si el usuario ingreso algun dato a modificar, de lo contrario se mantiene el dato anterior
            if (NEW_name == null || NEW_name.isEmpty()) {
                modName = OLD_name;
            } else {
                modName = NEW_name;
            }
            
            if (NEW_direction == null || NEW_direction.isEmpty()) {
                modDirection = OLD_direction;
            } else {
                modDirection = NEW_direction;
            }
                        
            if (NEW_barrio == null || NEW_barrio.isEmpty()) {
                modBarrio = OLD_barrio;
            } else {
                modBarrio = NEW_barrio;
            }
                        
            if (NEW_status == null || NEW_status.isEmpty()) {
                modStatus = ODL_status;
            } else {
                modStatus = NEW_status;
            } 
            
            if (NEW_passBene == null || NEW_passBene.isEmpty()) {
                modPassBeneficiario = OLD_passBene;
            } else {
                modPassBeneficiario = NEW_passBene;
            } 
            
            if (NEW_birthdate == null || NEW_birthdate.isEmpty()) {
                modBirthdate = OLD_birthdate;
            } else {
                modBirthdate = NEW_birthdate;
            } 
            
            // Se parsea la fecha de nacimiento
            Calendar fechaNacimientoCalendar = null;
            
            if (modBirthdate != null && !modBirthdate.isEmpty()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Ajusta el formato según tus necesidades
                try {
                    Date date = sdf.parse(modBirthdate);
                    fechaNacimientoCalendar = Calendar.getInstance();
                    fechaNacimientoCalendar.setTime(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                    // Manejo de error: la fecha no se pudo parsear
                    request.setAttribute("error", "Formato de fecha no válida.");
                    return;
                }
            }

            // Se llama al metodo para hacer la modificacion
            controlador.modificarBeneficiario(modName, email, modPassBeneficiario, modDirection, fechaNacimientoCalendar, modStatus, modBarrio); 
            
            // Actualiza los datos en la sesión
            HttpSession session = request.getSession();
            session.setAttribute("nombreUsuario", modName);
            session.setAttribute("fechaNacimiento", modBirthdate);
            session.setAttribute("direccion", modDirection);
            session.setAttribute("barrio", modBarrio);
            session.setAttribute("estado", modStatus);
        } else {
            // Si es un repartidor, actualiza sus datos
            DtRepartidor repartidor = (DtRepartidor) usuario;
            
            // Obtengo los datos anteriores y actuales del usuario
            String OLD_name = request.getParameter("OLD_name");
            String NEW_name = request.getParameter("NEW_name");

            String OLD_license = request.getParameter("OLD_license");
            String NEW_license = request.getParameter("NEW_license");

            String OLD_passRep = repartidor.getPassword();
            String NEW_passRep = request.getParameter("NEW_passwordRepartidor");
            
            String email = repartidor.getEmail();
            
            // Comparo si el usuario ingreso algun dato a modificar, de lo contrario se mantiene el dato anterior
            if (NEW_name == null || NEW_name.isEmpty()) {
                modName = OLD_name;
            } else {
                modName = NEW_name;
            }
                        
            if (NEW_license == null || NEW_license.isEmpty()) {
                modLicencia = OLD_license;
            } else {
                modLicencia = NEW_license;
            } 
            
            if (NEW_passRep == null || NEW_passRep.isEmpty()) {
                modPassRepartidor = OLD_passRep;
            } else {
                modPassRepartidor = NEW_passRep;
            } 
            
            // Se llama al metodo para hacer la modificacion
            controlador.modificarRepartidor(modName, email, modPassRepartidor, modLicencia);
            
            // Actualiza los datos en la sesión
            HttpSession session = request.getSession();
            session.setAttribute("nombreUsuario", modName);
            session.setAttribute("numeroLicencia", modLicencia);
        }

        // Almacenar el mensaje en la sesión
        request.getSession().setAttribute("mensaje", "Datos actualizados correctamente");

        // Redirigir a la JSP
        response.sendRedirect("verPerfil.jsp");
    }
}
