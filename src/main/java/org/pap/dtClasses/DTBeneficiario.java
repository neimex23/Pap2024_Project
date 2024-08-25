package org.pap.dtClasses;

import org.pap.Enums.*;

public class DTBeneficiario extends DTUsuario{

    private String direccion;
    private DtFechaHora fechaNacimiento;
    private EnumEstadoBeneficiario estado;
    private EnumBarrio barrio;

    //constructor
    public DTBeneficiario(String nombre, String email, String direccion, DtFechaHora fechaNacimiento, EnumEstadoBeneficiario estado, EnumBarrio barrio) {
        super(nombre, email);
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.estado = estado;
        this.barrio = barrio;
    }

    // Getters

    public String getDireccion() { return direccion; }
    public DtFechaHora getFechaNacimiento() { return fechaNacimiento; }
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

