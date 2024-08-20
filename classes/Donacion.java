package classes;	

public class Donacion {
	
	    public int id;
	    public DateTime fechaIngresada;
	    
	    public int getId() {
	        return id;
	    }
	
	    public DateTime getFechaIngresada() {
	        return fechaIngresada;
	    }
	    
	    public void setId(int id) {
	        this.id = id;
	    }
	    
	    public void setFechaIngresada(DateTime fechaIngresada) {
	        this.fechaIngresada = fechaIngresada;
	    }
	    
	    public Donacion() {
	    }

	    public Donacion(int id, DateTime fechaIngresada) {
	        this.id = id;
	        this.fechaIngresada = fechaIngresada;
	    }


}





