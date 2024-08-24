package interfaces;

import classes.*;
import dtClasses.*;
import Enums.*;
import handlers.*;
import interfaces.*;

import java.util.List;

public interface IControlador {
    public void altaBeneficiario(String nombre, String email, String dir, DtFechaHora fNac, EnumEstadoBeneficiario estBen, EnumBarrio barrio);
    public void altaRepartidor(String nombre, String email, String numeroLicencia);
    public boolean existeEmail(String email);
    public boolean existeLicencia(String licencia);
    public int conGetCantBeneficiarios();
    public int conGetCantRepartidores();


    public void altaDonacionAlimento(DtFechaHora FechaIng, String descripcionProducto, int cantElementos);
    public void altaDonacionArticulo(DtFechaHora FechaIng, String descripcionArt, float peso, String dimensiones);

    List<DTBeneficiario> ListarBeneficiario(); //CrearDTBeneficiario
    List<DTDistribuciones> ListarDistribuciones(EnumEstadoDistribucion estado); //Crear DTDistribuciones
    List<DTDonaciones> ListarDonaciones(); //CrearDTDonaciones

    void agregarDistribucion(DTBeneficiario ben, DTDonaciones Donacion);
    //Pensar ModificarDistribucion
	


}
