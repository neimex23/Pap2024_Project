package interfaces;

import dtClasses.DtFechaHora;
import enums.EnumBarrio;
import enums.EnumEstadoBeneficiario;

public interface IControlador {
    public void altaBeneficiario(String nombre, String email, String dir, DtFechaHora fNac, EnumEstadoBeneficiario estBen, EnumBarrio barrio);
    public void altaRepartidor(String nombre, String email, String numeroLicencia);


}
