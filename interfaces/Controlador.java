package interfaces;

import dtClasses.DtFechaHora;
import enums.EnumEstadoBeneficiario;
import enums.EnumBarrio;
import enums.EnumEstadoDistribucion;

public class Controlador implements IControlador {

    public Controlador() {
        super();
    }


    //Operaciones de usario
    @Override
    public void altaBeneficiario(String nombre, String email, String dir, DtFechaHora fNac, EnumEstadoBeneficiario estBen, EnumBarrio barrio) {

    }

    @Override
    public void altaRepartidor(String nombre, String email, String numeroLicencia) {

    }

    @Override
    public void existeEmail(String email) {

    }

    @Override
    public void existeLicencia(String licencia) {

    }

    //Operaciones de Donacion
    @Override
    public DTDonaciones ListarDonaciones() {
        return null;
    }

    @Override
    public void altaDonacionAlimento(DtFechaHora FechaIng, String descripcionProducto, int cantElementos) {
        //Tener en cuenta que Id es autoincremental
    }

    @Override
    public void altaDonacionArticulo(DtFechaHora FechaIng, String descripcionArt, float peso, String dimensiones) {
        //Tener en cuenta que Id es autoincremental
    }

    @Override
    public DTBeneficiario ListarBeneficiario() {
        return null;
    }

    @Override
    public DTDistribuciones ListarDistribuciones(EnumEstadoDistribucion estado) {
        return null;
    }

    @Override
    public void agregarDistribucion(DTBeneficiario ben, DTDonaciones Donacion) {

    }


}


