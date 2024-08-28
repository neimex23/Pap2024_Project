package org.pap.interfaces;

import org.pap.dtClasses.*;
import org.pap.Enums.*;
import java.time.LocalDateTime;

import java.util.List;

public interface IControlador {
    void altaBeneficiario(String nombre, String email, String dir, LocalDateTime fNac, EnumEstadoBeneficiario estBen, EnumBarrio barrio);
    void altaRepartidor(String nombre, String email, String numeroLicencia);
    boolean existeEmail(String email);
    boolean existeLicencia(String licencia);
    int conGetCantBeneficiarios();
    int conGetCantRepartidores();


    void altaDonacionAlimento(LocalDateTime FechaIng, String descripcionProducto, int cantElementos);
    void altaDonacionArticulo(LocalDateTime FechaIng, String descripcionArt, float peso, String dimensiones);

    List<DTUsuario> ListarBeneficiario(); //CrearDTBeneficiario
    List<DTDonacion> ListarDonaciones(); //CrearDTDonaciones
    DTUsuario obtenerDTBeneficiario(String email);

    void agregarDistribucion(LocalDateTime fechaPreparacion, LocalDateTime fechaEntrega, EnumEstadoDistribucion estado, int donacionID);
    void modificarDistribucion(DTDistribucion distribucion);
    List<DTDistribucion> listarDistribuciones(); //Se va a utilizar para listar las distribuciones y luego invocar modificarDistribucion
    List<DTDistribucion> ListarDistribuciones(EnumEstadoDistribucion estado); //Para listar Distribuciones por Estado
	


}
