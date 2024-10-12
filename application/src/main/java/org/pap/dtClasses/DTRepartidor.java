package org.pap.dtClasses;



public class DTRepartidor extends DTUsuario{

    private String numeroLicencia;

    // Constructor
    public DTRepartidor(String nombre, String email, String password, String numeroLicencia) {
        super(nombre, email, password);
        this.numeroLicencia = numeroLicencia;
    }


    // Getter para numeroLicencia
    public String getNumeroLicencia() {
        return numeroLicencia;
    }


}

