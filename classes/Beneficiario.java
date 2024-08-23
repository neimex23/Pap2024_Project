package classes;
import Enums.EnumBarrio;
import Enums.EnumEstadoBeneficiario;
import dtClasses.DtFechaHora;
import dtClasses.DTBeneficiario;

public class Beneficiario extends Usuario {

	private String direccion;
	private DtFechaHora fechaNacimiento;
	private EnumEstadoBeneficiario estado;
	private EnumBarrio barrio;

    public Beneficiario(String nombre,String email, String direccion, DtFechaHora fechaNacimiento, EnumEstadoBeneficiario estado, EnumBarrio barrio) {
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
    public DtFechaHora getFechaNacimiento() {
        return fechaNacimiento;
    }

    // Setter para fechaNacimiento
    public void setFechaNacimiento(DtFechaHora fechaNacimiento) {
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
    // Metodo para convertir Beneficiario a DTBeneficiario
    public DTBeneficiario toDTBeneficiario() {
        return new DTBeneficiario(this.getNombre(), this.getEmail(), this.direccion, this.fechaNacimiento, this.estado, this.barrio);
    }
    
	}