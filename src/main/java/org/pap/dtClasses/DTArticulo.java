package org.pap.dtClasses;

public class DTArticulo extends DTDonacion {

	private String descr;
	private float peso;
	private String dimensiones;
	
	// Getter para descr
    public String getDescr() {
        return descr;
    }

    // Getter para peso
    public float getPeso() {
        return peso;
    }

    // Getter para dimensiones
    public String getDimensiones() {
        return dimensiones;
    }

    public DTArticulo(int id, LocalDateTime fechaIngresada, String descr, float peso, String dimensiones) {
        super(id, fechaIngresada);
        this.descr = descr;
        this.peso = peso;
        this.dimensiones = dimensiones;
    }
    
    }