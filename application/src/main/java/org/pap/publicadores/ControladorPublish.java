package org.pap.publicadores;

import org.pap.Enums.*;
import org.pap.interfaces.*;
import org.pap.dtClasses.*;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;
import java.time.LocalDateTime;
import java.util.List;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class ControladorPublish {
    private Fabrica fabrica;
    private IControlador icon;
    private Endpoint endpoint;

    public ControladorPublish() {
        fabrica = Fabrica.getInstancia();
        icon = fabrica.getIControlador();
    }

    @WebMethod(exclude = true)
    public void publicar() {
        endpoint = Endpoint.publish("http://localhost:3002/controlador", this);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }

    @WebMethod
    public boolean InicioSecion (String email, String password){
        return icon.autenticarUsuario(email, password);
    }

    @WebMethod
    public DTUsuario obtenerUsuario(String email){
        return icon.obtenerUsuario(email);
    }

    @WebMethod
    public DTDistribucion[] listarDistribuciones(){
        List<DTDistribucion> distribuciones = icon.listarDistribucionesBD();
        if (distribuciones == null) {
            return new DTDistribucion[0]; // Devolver un array vacío en lugar de null
        }
        return distribuciones.toArray(new DTDistribucion[0]);
    }

    @WebMethod
    public void ModificarDistribucion(int idDistribucion, LocalDateTime fechaEntrega, String estadoDistribucion){
        EnumEstadoDistribucion dist = EnumEstadoDistribucion.valueOf(estadoDistribucion);
        icon.modificarDistribucion(idDistribucion, fechaEntrega, dist);
    }



}
