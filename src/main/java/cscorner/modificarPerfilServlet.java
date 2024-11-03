package cscorner;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.pap.publicadores.*;

@WebServlet("/modificarPerfilServlet")
public class ModificarPerfilServlet extends HttpServlet {
    
    private ControladorPublishService cps = new ControladorPublishServiceLocator();
    private ControladorPublish controlador;
    
    public ModificarPerfilServlet() {
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
        
        response.setContentType("text/plain");
        
        try {
            UsuarioLogin usuarioLogin = UsuarioLogin.GetInstancia();
            DtUsuario usuario = usuarioLogin.getUsuario();
            
            if (usuario instanceof DtBeneficiario) {
                DtBeneficiario beneficiario = (DtBeneficiario) usuario;
                beneficiario.setNombre(request.getParameter("nombre"));
                beneficiario.setDireccion(request.getParameter("direccion"));
                
                // Convertir el valor de barrio al enum correspondiente
                EnumBarrio barrio = obtenerEnumBarrio(request.getParameter("barrio"));
                if (barrio != null) {
                    beneficiario.setBarrio(barrio);
                } else {
                    response.getWriter().write("Error: Barrio inválido");
                    return;
                }

                String fechaNac = request.getParameter("fechaNacimiento");
                if (fechaNac != null && !fechaNac.isEmpty()) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate fechaNacimiento = LocalDate.parse(fechaNac, formatter);
                    beneficiario.setFechaNacimiento(fechaNacimiento.toString());
                }
                
                controlador.actualizarBeneficiario(beneficiario);
                
            } else if (usuario instanceof DtRepartidor) {
                DtRepartidor repartidor = (DtRepartidor) usuario;
                repartidor.setNombre(request.getParameter("nombre"));
                repartidor.setNumeroLicencia(request.getParameter("numeroLicencia"));
                
                controlador.actualizarRepartidor(repartidor);
            }
            
            response.getWriter().write("Perfil actualizado exitosamente");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Error al actualizar el perfil: " + e.getMessage());
        }
    }

    private EnumBarrio obtenerEnumBarrio(String barrioStr) {
        if (barrioStr == null) {
            return null;
        }
        for (EnumBarrio barrio : EnumBarrio.values()) {
            if (barrio.name().equalsIgnoreCase(barrioStr)) {
                return barrio;
            }
        }
        return null; // Retorna null si no encuentra coincidencias
    }
}




