package classes;	

public class Articulo extends Donacion {

	public string direccion;
	public DtFecha fechaNacimiento;
	public EstadoBeneficiario estado;
	public Barrio barrio;
	
	public Articulo() {
        super();
    }

    public Articulo(int id, DateTime fechaIngresada, String direccion, DtFecha fechaNacimiento, EstadoBeneficiario estado, Barrio barrio) {
        super(id, fechaIngresada);
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
    public DtFecha getFechaNacimiento() {
        return fechaNacimiento;
    }

    // Setter para fechaNacimiento
    public void setFechaNacimiento(DtFecha fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    // Getter para estado
    public EstadoBeneficiario getEstado() {
        return estado;
    }

    // Setter para estado
    public void setEstado(EstadoBeneficiario estado) {
        this.estado = estado;
    }

    // Getter para barrio
    public Barrio getBarrio() {
        return barrio;
    }

    // Setter para barrio
    public void setBarrio(Barrio barrio) {
        this.barrio = barrio;
    }
    
    
	}