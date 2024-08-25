package interfaces;

import dtClasses.*;
import enums.*;

import java.util.List;

public interface IControlador {
    void altaBeneficiario(String nombre, String email, String dir, DtFechaHora fNac, EnumEstadoBeneficiario estBen, EnumBarrio barrio);
    void altaRepartidor(String nombre, String email, String numeroLicencia);
    boolean existeEmail(String email);
    boolean existeLicencia(String licencia);
    int conGetCantBeneficiarios();
    int conGetCantRepartidores();


    void altaDonacionAlimento(DtFechaHora FechaIng, String descripcionProducto, int cantElementos);
    void altaDonacionArticulo(DtFechaHora FechaIng, String descripcionArt, float peso, String dimensiones);

    List<DTBeneficiario> ListarBeneficiario(); //CrearDTBeneficiario
    List<DTDonacion> ListarDonaciones(); //CrearDTDonaciones

    void agregarDistribucion(DTDistribucion distribucion, DTDonacion donacion);
    void modificarDistribucion(DTDistribucion distribucion);
    List<DTDistribucion> listarDistribuciones(); //Se va a utilizar para listar las distribuciones y luego invocar modificarDistribucion
    List<DTDistribucion> ListarDistribuciones(EnumEstadoDistribucion estado); //Para listar Distribuciones por Estado
	


}
