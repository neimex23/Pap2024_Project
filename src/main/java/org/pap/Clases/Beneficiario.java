package org.pap.Clases;

import org.pap.Enums.EnumBarrio;
import org.pap.Enums.EnumEstadoBeneficiario;
import org.pap.dtClasses.DTUsuario;
import java.time.LocalDateTime;
import java.util.List;
import org.pap.dtClasses.DTBeneficiario;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("B")
public class Beneficiario extends Usuario {

    // Lista de donaciones del beneficiario
    @Id
	private String direccion;
	private LocalDateTime fechaNacimiento;
	private EnumEstadoBeneficiario estado;
	private EnumBarrio barrio;

    public Beneficiario(String nombre, String email, String direccion, LocalDateTime fechaNacimiento, EnumEstadoBeneficiario estado, EnumBarrio barrio) {
        super(nombre, email);
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.estado = estado;
        this.barrio = barrio;
    }

    // Getter para direccion
    public String getDireccion() {
        return direccion;
    }

    // Setter para direccion
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    // Getter para fechaNacimiento
    public LocalDateTime getFechaNacimiento() {
        return fechaNacimiento;
    }

    // Setter para fechaNacimiento
    public void setFechaNacimiento(LocalDateTime fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    // Getter para estado
    public EnumEstadoBeneficiario getEstado() {
        return estado;
    }

    // Setter para estado
    public void setEstado(EnumEstadoBeneficiario estado) {
        this.estado = estado;
    }

    // Getter para barrio
    public EnumBarrio getBarrio() {
        return barrio;
    }

    // Setter para barrio
    public void setBarrio(EnumBarrio barrio) {
        this.barrio = barrio;
    }


    @Override
    public DTUsuario transformarADtUsuario() {;
        return new DTBeneficiario(this.getNombre(), this.getEmail(), this.getDireccion(), this.getFechaNacimiento(), this.getEstado(), this.getBarrio());
    }

}
