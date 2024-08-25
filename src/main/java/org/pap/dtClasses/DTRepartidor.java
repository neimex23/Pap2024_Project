package org.pap.dtClasses;



public class DTRepartidor extends DTUsuario{

    private String numeroLicencia;

    // Constructor
    public DTRepartidor(String nombre, String email, String numeroLicencia) {
        super(nombre, email);
        this.numeroLicencia = numeroLicencia;
    }


    // Getter para numeroLicencia
    public String getNumeroLicencia() {
        return numeroLicencia;
    }


}

