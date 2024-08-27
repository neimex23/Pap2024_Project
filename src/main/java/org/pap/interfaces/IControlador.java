package org.pap.interfaces;

import org.pap.dtClasses.*;
import org.pap.Enums.*;

import java.util.List;

public interface IControlador {
    void altaBeneficiario(String nombre, String email, String dir, DTFechaHora fNac, EnumEstadoBeneficiario estBen, EnumBarrio barrio);
    void altaRepartidor(String nombre, String email, String numeroLicencia);
    boolean existeEmail(String email);
    boolean existeLicencia(String licencia);
    int conGetCantBeneficiarios();
    int conGetCantRepartidores();


    void altaDonacionAlimento(DTFechaHora FechaIng, String descripcionProducto, int cantElementos);
    void altaDonacionArticulo(DTFechaHora FechaIng, String descripcionArt, float peso, String dimensiones);

    List<DTUsuario> ListarBeneficiario(); //CrearDTBeneficiario
    List<DTDonacion> ListarDonaciones(); //CrearDTDonaciones
    DTUsuario obtenerDTBeneficiario(String email);

    void agregarDistribucion(DTFechaHora fechaPreparacion, DTFechaHora fechaEntrega, EnumEstadoDistribucion estado, int donacionID);
    void modificarDistribucion(DTDistribucion distribucion);
    List<DTDistribucion> listarDistribuciones(); //Se va a utilizar para listar las distribuciones y luego invocar modificarDistribucion
    List<DTDistribucion> ListarDistribuciones(EnumEstadoDistribucion estado); //Para listar Distribuciones por Estado
	


}
