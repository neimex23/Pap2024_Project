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
    DTDonacion obtenerDonacion(int id); //Se queda
    
    List<DTUsuario> ListarBeneficiario(); //CrearDTBeneficiario
    List<DTDonacion> ListarDonaciones(); //CrearDTDonaciones
    DTUsuario obtenerDTBeneficiario(String email);

    void agregarDistribucion(LocalDateTime fechaPreparacion, LocalDateTime fechaEntrega, EnumEstadoDistribucion estado, int donacionID, String emailBenf);
    void modificarDistribucion(DTDistribucion distribucion);

    void modificarDistribucion(int idDistribucion, LocalDateTime fechaEntrega, EnumEstadoDistribucion estado);
    List<DTDistribucion>  listarDistribuciones(); //Se va a utilizar para listar las distribuciones y luego invocar modificarDistribucion
    List<DTDistribucion> listarDistribucionesPorEstado(EnumEstadoDistribucion estado); //Para listar Distribuciones por Estado

}
