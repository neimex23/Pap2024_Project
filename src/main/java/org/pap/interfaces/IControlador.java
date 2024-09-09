package org.pap.interfaces;

import java.time.LocalDate;
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
    DTBeneficiario obtenerDTBeneficiario(String emailBeneficiario);

    void altaDonacionAlimento(LocalDateTime FechaIng, String descripcionProducto, int cantElementos);
    void altaDonacionArticulo(LocalDateTime FechaIng, String descripcionArt, float peso, String dimensiones);
    void modificarDonacion(DTDonacion donacion);
    DTDonacion obtenerDonacion(int id); //Se queda
    
    List<DTUsuario> ListarBeneficiario(); //CrearDTBeneficiario
    List<DTDonacion> ListarDonaciones(); //CrearDTDonaciones

    void agregarDistribucion(LocalDateTime fechaPreparacion, LocalDateTime fechaEntrega, EnumEstadoDistribucion estado, int donacionID, String emailBenf);
    void modificarDistribucion(int idDistribucion, LocalDateTime fechaEntrega, EnumEstadoDistribucion estado);
    List<DTDistribucion>  listarDistribuciones(); //Se va a utilizar para listar las distribuciones y luego invocar modificarDistribucion
    List<DTDistribucion> listarDistribucionesPorEstado(EnumEstadoDistribucion estado); //Para listar Distribuciones por Estado
    List<DTDistribucion> ListarDistribucionesPorZona(EnumBarrio barrio);
    void cargarBaseDatos();

    List<DTUsuario> ListarBeneficiarioZona(EnumBarrio barrio); //Para listar beneficiarios por zona
    List<DTUsuario> ListarBeneficiarioEstado(EnumEstadoBeneficiario estado); //Para listar beneficiarios por estado

    public List<DTDistribucion> obtenerDistribucionesEnRango(LocalDate fechaInicio, LocalDate fechaFin);
}
