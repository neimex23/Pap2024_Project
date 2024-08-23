package classes;	

public class Repartidor extends Usuario {
	
	    private String numeroLicencia;

	    public Repartidor(String nombre, String email, String numeroLicencia) {
	        super(nombre, email);
	        this.numeroLicencia = numeroLicencia;
	    }

	    // Getter para numeroLicencia
	    public String getNumeroLicencia() {
	        return numeroLicencia;
	    }

	    // Setter para numeroLicencia
	    public void setNumeroLicencia(String numeroLicencia) {
	        this.numeroLicencia = numeroLicencia;
	    }
	    
	    
	}