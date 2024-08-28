package org.pap.dtClasses;

public abstract class DTDonacion {
	private int id;
    
    private LocalDateTime fechaIngresada;
    
    // Constructor
    public DTDonacion(int id, LocalDateTime fechaIngresada) {
        this.id = id;
        this.fechaIngresada = fechaIngresada;
    }

    // Getters
    public int getId() {
        return id;
    }

    public LocalDateTime getFechaIngresada() {
        return fechaIngresada;
    }
}

