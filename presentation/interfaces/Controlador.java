package interfaces;

import dtClasses.DtFechaHora;
import enums.EnumEstadoBeneficiario;
import enums.EnumBarrio;

public class Controlador implements IControlador {

    public Controlador() {
        super();
    }


    @Override
    public void altaBeneficiario(String nombre, String email, String dir, DtFechaHora fNac, EnumEstadoBeneficiario estBen, EnumBarrio barrio) {

    }

    @Override
    public void altaRepartidor(String nombre, String email, String numeroLicencia) {

    }
}


