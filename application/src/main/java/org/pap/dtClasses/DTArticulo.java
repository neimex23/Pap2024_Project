package org.pap.dtClasses;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.time.LocalDateTime;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DTArticulo extends DTDonacion {

    @XmlElement(name = "descr")
	private String descr;

    @XmlElement(name = "peso")
	private float peso;

    @XmlElement(name = "dimensiones")
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

    public DTArticulo() {super();}

    }