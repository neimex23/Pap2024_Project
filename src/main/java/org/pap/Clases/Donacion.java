package org.pap.Clases;
import java.time.LocalDateTime;
import org.pap.dtClasses.DTDonacion;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class Donacion {

		@Id
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





