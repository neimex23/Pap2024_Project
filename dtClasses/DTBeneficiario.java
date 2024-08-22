package dtClasses;

import Enums.EnumBarrio;
import Enums.EnumEstadoBeneficiario;
public class DTBeneficiario {
	private String nombre;
    private String email;
    private String direccion;
    private DtFechaHora fechaNacimiento;
    private EnumEstadoBeneficiario estado;
    private EnumBarrio barrio;

    public DTBeneficiario(String nombre, String email, String direccion, DtFechaHora fechaNacimiento, EnumEstadoBeneficiario estado, EnumBarrio barrio) {
        this.nombre = nombre;
        this.email = email;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.estado = estado;
        this.barrio = barrio;
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public String getDireccion() { return direccion; }
    public DtFechaHora getFechaNacimiento() { return fechaNacimiento; }
    public EnumEstadoBeneficiario getEstado() { return estado; }
    public EnumBarrio getBarrio() { return barrio; }

    @Override
    public String toString() {
        return "Beneficiario: " + nombre + "\n" +
               "Email: " + email + "\n" +
               "Dirección: " + direccion + "\n" +
               "Fecha de Nacimiento: " + fechaNacimiento + "\n" +
               "Estado: " + estado + "\n" +
               "Barrio: " + barrio;
    }
}

