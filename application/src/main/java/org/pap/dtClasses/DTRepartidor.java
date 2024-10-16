package org.pap.dtClasses;
import jakarta.xml.bind.annotation.XmlRootElement;



@XmlRootElement
public class DTRepartidor extends DTUsuario{

    private String numeroLicencia;

    // Constructor
    public DTRepartidor(String nombre, String email, String password, String numeroLicencia) {
        super(nombre, email, password);
        this.numeroLicencia = numeroLicencia;
    }

    public DTRepartidor(){}

    // Getter para numeroLicencia
    public String getNumeroLicencia() {
        return numeroLicencia;
    }


}

