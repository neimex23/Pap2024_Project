package org.pap.dtClasses;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DTRepartidor extends DTUsuario{

    @XmlElement(name = "numeroLicencia")
    private String numeroLicencia;

    // Constructor
    public DTRepartidor(String nombre, String email, String password, String numeroLicencia) {
        super(nombre, email, password);
        this.numeroLicencia = numeroLicencia;
    }

    public DTRepartidor(){super();}

    // Getter para numeroLicencia
    public String getNumeroLicencia() {
        return numeroLicencia;
    }


}

