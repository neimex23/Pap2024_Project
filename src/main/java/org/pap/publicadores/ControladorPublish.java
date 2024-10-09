package org.pap.publicadores;

import org.pap.Enums.EnumEstadoDistribucion;
import org.pap.interfaces.*;
import org.pap.dtClasses.*;
import org.pap.configuraciones.*;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import javax.xml.ws.Endpoint;
import java.time.LocalDateTime;
import java.util.List;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class ControladorPublish {
    private Fabrica fabrica;
    private IControlador icon;
    private WebServiceConfiguracion configuracion;
    private Endpoint endpoint;

    public ControladorPublish() {
        fabrica = Fabrica.getInstancia();
        icon = fabrica.getIControlador();
        try {
            configuracion = new WebServiceConfiguracion();
        } catch (Exception ex) {

        }
    }

    @WebMethod(exclude = true)
    public void publicar() {
        endpoint = Endpoint.publish("http://" + configuracion.getConfigOf("#WS_IP") + ":" + configuracion.getConfigOf("#WS_PORT") + "/controlador", this);
        System.out.println("http://" + configuracion.getConfigOf("#WS_IP") + ":" + configuracion.getConfigOf("#WS_PORT") + "/controlador");
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }

    @WebMethod
    public DTUsuario InicioSecion (String email, String password){ // Retorna un DTusuario con los datos proporcionadoes, si retorna null es que el usuario o la contrasenia no son correctas
        return icon.autenticarUsuario(email, password); //Puede ser null
    }

    @WebMethod
    public List<DTDistribucion> listarDistribuciones(){
        return icon.listarDistribucionesBD();
    }

    @WebMethod
    public void ModificarDistribucion(int idDistribucion, LocalDateTime fechaEntrega, EnumEstadoDistribucion estadoDistribucion){
        icon.modificarDistribucion(idDistribucion, fechaEntrega, estadoDistribucion);
    }



}
