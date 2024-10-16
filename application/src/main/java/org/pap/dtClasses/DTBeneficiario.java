package org.pap.dtClasses;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.pap.Enums.*;
import java.time.LocalDateTime;

@XmlRootElement
public class DTBeneficiario extends DTUsuario{

    private String direccion;
    private LocalDateTime fechaNacimiento;
    private EnumEstadoBeneficiario estado;
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

