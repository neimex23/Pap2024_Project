package org.pap.dtClasses;
import java.time.LocalDateTime;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.pap.Enums.EnumEstadoDistribucion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DTDistribucion {
    @XmlElement(name = "id")
    private int Id;

    @XmlElement(name = "fechaPreparacion")
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime fechaPreparacion;

    @XmlElement(name = "fechaEntrega")
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime fechaEntrega;

    @XmlElement(name = "estado")
    private EnumEstadoDistribucion estado;

    @XmlElement(name = "donacionAsc")
    private int donacionAsc;

    @XmlElement(name = "emailBenefAsc")
    private String emailBenefAsc;

    public DTDistribucion() {super();}
    public DTDistribucion(int Id, LocalDateTime FechaPreparacion, LocalDateTime FechaEntrega, EnumEstadoDistribucion estado, int idDonacionAsc, String emailBenefAsc) {
        this.fechaEntrega = FechaEntrega;
        this.fechaPreparacion = FechaPreparacion;
        this.estado = estado;
        this.Id = idDonacionAsc;
        this.donacionAsc = idDonacionAsc;
        this.emailBenefAsc = emailBenefAsc;
    }


    public int getId() {
        return Id;
    }

    public LocalDateTime getFechaPreparacion() {
        return fechaPreparacion;
    }

    public LocalDateTime getFechaEntrega() {
        return fechaEntrega;
    }

    public EnumEstadoDistribucion getEstado() {
        return estado;
    }

    public int getDonacionAsc() {
        return donacionAsc;
    }

    public String getEmailBenefAsc() {
        return emailBenefAsc;
    }
}