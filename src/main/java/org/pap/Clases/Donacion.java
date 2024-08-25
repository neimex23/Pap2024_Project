package org.pap.Clases;
import org.pap.dtClasses.DtFechaHora;
import org.pap.dtClasses.DTDonacion;

public abstract class Donacion {
	
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

		public abstract DTDonacion transformarADtDonacion();
}





