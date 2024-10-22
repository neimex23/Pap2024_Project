package org.pap.dtClasses;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.time.LocalDateTime;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DTAlimento extends DTDonacion{

    @XmlElement(name = "descProducto")
	private String descProducto;

    @XmlElement(name = "cantElemntos")
	private int cantElemntos;
	
	 // Getter para descProducto
    public String getDescProducto() {
        return this.descProducto;
    }

    // Getter para cantElemntos
    public int getCantElemntos() {
        return cantElemntos;
    }

    public DTAlimento(int id, LocalDateTime fechaIngresada, String descProducto, int cantElemntos) {
        super(id, fechaIngresada);
        this.descProducto = descProducto;
        this.cantElemntos = cantElemntos;
    }
    public DTAlimento() {super();}
}
