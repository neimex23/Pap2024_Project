package org.pap.Clases;
import java.time.LocalDateTime;
import org.pap.dtClasses.DTDonacion;

public abstract class Donacion {
	
	    private int id;
	    private LocalDateTime fechaIngresada;

		public Donacion(int id, LocalDateTime fechaIngresada) {
	        this.id = id;
	        this.fechaIngresada = fechaIngresada;
	    }
	    
	    public int getId() {
	        return id;
	    }
	
	    public LocalDateTime getFechaIngresada() {
	        return fechaIngresada;
	    }
	    
	    public void setId(int id) {
	        this.id = id;
	    }
	    
	    public void setFechaIngresada(LocalDateTime fechaIngresada) {
	        this.fechaIngresada = fechaIngresada;
	    }

		public abstract DTDonacion transformarADtDonacion();
}





