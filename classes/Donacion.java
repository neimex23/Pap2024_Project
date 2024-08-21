package classes;
import dtClasses.DtFechaHora;

public class Donacion {
	
	    private int id;
	    private DtFechaHora fechaIngresada;

		public Donacion(int id, DtFechaHora fechaIngresada) {
	        this.id = id;
	        this.fechaIngresada = fechaIngresada;
	    }
	    
	    public int getId() {
	        return id;
	    }
	
	    public DtFechaHora getFechaIngresada() {
	        return fechaIngresada;
	    }
	    
	    public void setId(int id) {
	        this.id = id;
	    }
	    
	    public void setFechaIngresada(DtFechaHora fechaIngresada) {
	        this.fechaIngresada = fechaIngresada;
	    }

}





