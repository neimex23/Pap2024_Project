package handlers;

import dtClasses.DtFechaHora;

public interface IControlador {
    public void altaBeneficiario(string nom, String email, String dir, DtFechaHora fNac,Estado estBen, Bario barrio);
    public void altaRepartidor(string Nombre, String Email, String numeroLicencia);


}
