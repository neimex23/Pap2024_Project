package interfaces;

import dtClasses.DtFechaHora;
import enums.EnumBarrio;
import enums.EnumEstadoBeneficiario;
import enums.EnumEstadoDistribucion;

public interface IControlador {
    public void altaBeneficiario(String nombre, String email, String dir, DtFechaHora fNac, EnumEstadoBeneficiario estBen, EnumBarrio barrio);
    public void altaRepartidor(String nombre, String email, String numeroLicencia);
    public void existeEmail(String email);
    public void existeLicencia(String licencia);

    public void altaDonacionAlimento(DtFechaHora FechaIng, String descripcionProducto, int cantElementos);
    public void altaDonacionArticulo(DtFechaHora FechaIng, String descripcionArt, float peso, String dimensiones);

    public List<DTBeneficiario> ListarBeneficiario(); //CrearDTBeneficiario
    public List<DTDistribuciones> ListarDistribuciones(EnumEstadoDistribucion estado); //Crear DTDistribuciones
    public List<DTDonaciones> ListarDonaciones(); //CrearDTDonaciones

    public void agregarDistribucion(DTBeneficiario ben, DTDonaciones Donacion);
    //Pensar ModificarDistribucion


}
