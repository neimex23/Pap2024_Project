package org.pap.dtClasses;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlRootElement
@XmlSeeAlso({DTBeneficiario.class, DTRepartidor.class})
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class DTUsuario {

    @XmlElement(name = "nombre")
    private String nombre;

    @XmlElement(name = "password")
    private String password;

    @XmlElement(name = "email")
    private String email;

    // Getter para nombre
    public String getNombre() {
        return nombre;
    }

    // Getter para email
    public String getEmail() {
        return email;
    }

    public DTUsuario(String nombre, String email, String password) {
        this.nombre = nombre;
        this.password = password;
        this.email = email;
    }

    public DTUsuario(){super();} //para jakarta que requiere constructores vacios

    public String getPassword() {
        return password;
    }
}

