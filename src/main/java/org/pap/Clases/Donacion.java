package org.pap.Clases;
import org.pap.dtClasses.DTFechaHora;
import org.pap.dtClasses.DTDonacion;

public abstract class Donacion {
	
	    private int id;
	    private DTFechaHora fechaIngresada;

		public Donacion(int id, DTFechaHora fechaIngresada) {
	        this.id = id;
	        this.fechaIngresada = fechaIngresada;
	    }
	    
	    public int getId() {
	        return id;
	    }
	
	    public DTFechaHora getFechaIngresada() {
	        return fechaIngresada;
	    }
	    
	    public void setId(int id) {
	        this.id = id;
	    }
	    
	    public void setFechaIngresada(DTFechaHora fechaIngresada) {
	        this.fechaIngresada = fechaIngresada;
	    }

		public abstract DTDonacion transformarADtDonacion();
}





