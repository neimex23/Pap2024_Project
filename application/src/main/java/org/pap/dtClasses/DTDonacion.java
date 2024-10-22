package org.pap.dtClasses;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.time.LocalDateTime;

@XmlRootElement
@XmlSeeAlso({DTAlimento.class, DTArticulo.class})
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class DTDonacion {

    @XmlElement(name = "id")
	private int id;

    @XmlElement(name = "fechaIngresada")
    private LocalDateTime fechaIngresada;
    
    // Constructor
    public DTDonacion(int id, LocalDateTime fechaIngresada) {
        this.id = id;
        this.fechaIngresada = fechaIngresada;
    }

    public DTDonacion() {super();}
    // Getters
    public int getId() {
        return id;
    }

    public LocalDateTime getFechaIngresada() {
        return fechaIngresada;
    }
}

