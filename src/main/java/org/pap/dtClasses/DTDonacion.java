package org.pap.dtClasses;

public abstract class DTDonacion {
	private int id;
    
    private DtFechaHora fechaIngresada;
    
    // Constructor
    public DTDonacion(int id, DtFechaHora fechaIngresada) {
        this.id = id;
        this.fechaIngresada = fechaIngresada;
    }

    // Getters
    public int getId() {
        return id;
    }

    public DtFechaHora getFechaIngresada() {
        return fechaIngresada;
    }
}

