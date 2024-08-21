package classes;	

public class Articulo extends Donacion {

	private String descr;
	private float peso;
	private String dimensiones;
	
	// Getter para descr
    public String getDescr() {
        return descr;
    }

    // Setter para descr
    public void setDescr(String descr) {
        this.descr = descr;
    }

    // Getter para peso
    public float getPeso() {
        return peso;
    }

    // Setter para peso
    public void setPeso(float peso) {
        this.peso = peso;
    }

    // Getter para dimensiones
    public String getDimensiones() {
        return dimensiones;
    }

    // Setter para dimensiones
    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }

    public Articulo(int id, DateTime fechaIngresada, String descr, float peso, String dimensiones) {
        super(id, fechaIngresada);
        this.descr = descr;
        this.peso = peso;
        this.dimensiones = dimensiones;
    }
    
	}