package org.pap.dtClasses;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.pap.Enums.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.time.LocalDateTime;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DTBeneficiario extends DTUsuario{

    @XmlElement(name = "direccion")
    private String direccion;

    @XmlElement(name = "fechaNacimiento")
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime fechaNacimiento;

    @XmlElement(name = "estado")
    private EnumEstadoBeneficiario estado;

    @XmlElement(name = "barrio")
    private EnumBarrio barrio;

    //constructor
    public DTBeneficiario(String nombre, String email, String password, String direccion, LocalDateTime fechaNacimiento, EnumEstadoBeneficiario estado, EnumBarrio barrio) {
        super(nombre, email, password);
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.estado = estado;
        this.barrio = barrio;
    }

    public  DTBeneficiario(){}

    // Getters

    public String getDireccion() { return direccion; }
    public LocalDateTime getFechaNacimiento() { return fechaNacimiento; }
    public EnumEstadoBeneficiario getEstado() { return estado; }
    public EnumBarrio getBarrio() { return barrio; }

    /*
    @Override
    public String toString() {
        return "Beneficiario: " + nombre + "\n" +
               "Email: " + email + "\n" +
               "Direccion: " + direccion + "\n" +
               "Fecha de Nacimiento: " + fechaNacimiento + "\n" +
               "Estado: " + estado + "\n" +
               "Barrio: " + barrio;
    }*/
}

