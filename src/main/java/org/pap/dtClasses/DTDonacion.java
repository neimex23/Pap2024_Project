package org.pap.dtClasses;

public abstract class DTDonacion {
	private int id;
    
    private DTFechaHora fechaIngresada;
    
    // Constructor
    public DTDonacion(int id, DTFechaHora fechaIngresada) {
        this.id = id;
        this.fechaIngresada = fechaIngresada;
    }

    // Getters
    public int getId() {
        return id;
    }

    public DTFechaHora getFechaIngresada() {
        return fechaIngresada;
    }
}

