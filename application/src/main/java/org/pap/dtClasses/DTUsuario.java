package org.pap.dtClasses;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlSeeAlso({DTBeneficiario.class, DTRepartidor.class})
public abstract class DTUsuario {
    private String nombre;
    private String password;
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

    public DTUsuario(){} //para jakarta que requiere constructores vacios

    public String getPassword() {
        return password;
    }
}

