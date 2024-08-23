package interfaces;
import dtClasses.DTBeneficiario;
import dtClasses.DtFechaHora;
import Enums.EnumBarrio;
import Enums.EnumEstadoBeneficiario;
import Enums.EnumEstadoDistribucion;
import java.util.List;

public interface IControlador {
    public void altaBeneficiario(String nombre, String email, String dir, DtFechaHora fNac,Enums.EnumEstadoBeneficiario estBen, Enums.EnumBarrio barrio);
    public void altaRepartidor(String nombre, String email, String numeroLicencia);
    public void existeEmail(String email);
    public void existeLicencia(String licencia);

    public void altaDonacionAlimento(DtFechaHora FechaIng, String descripcionProducto, int cantElementos);
    public void altaDonacionArticulo(DtFechaHora FechaIng, String descripcionArt, float peso, String dimensiones);

    List<DTBeneficiario> ListarBeneficiario(); //CrearDTBeneficiario
    List<DTDistribuciones> ListarDistribuciones(EnumEstadoDistribucion estado); //Crear DTDistribuciones
    List<DTDonaciones> ListarDonaciones(); //CrearDTDonaciones

    void agregarDistribucion(DTBeneficiario ben, DTDonaciones Donacion);
    //Pensar ModificarDistribucion
	


}
