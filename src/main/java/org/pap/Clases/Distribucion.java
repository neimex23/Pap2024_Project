package org.pap.Clases;
import java.time.LocalDateTime;
import org.pap.Enums.*;
import org.pap.dtClasses.DTDistribucion;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Distribucion {

    @Id
    private Integer id;
    private LocalDateTime fechaPreparacion;
    private LocalDateTime fechaEntrega;
    private EnumEstadoDistribucion estado;
    private int idDonAsc;
    private String emailbenAsc;


    public Distribucion(int id, LocalDateTime FechaPreparacion, LocalDateTime FechaEntrega, EnumEstadoDistribucion estado, int idDonAsc, String emailbenAsc) {
        this.id = id;
        this.fechaEntrega = FechaEntrega;
        this.fechaPreparacion = FechaPreparacion;
        this.estado = estado;
        this.idDonAsc = idDonAsc;
        this.emailbenAsc = emailbenAsc;
    }

    public DTDistribucion transform() {
        return new DTDistribucion(this.id, this.fechaEntrega, this.fechaPreparacion, this.estado, this.idDonAsc, this.emailbenAsc);
    }

    public LocalDateTime getFechaPreparacion() {
        return fechaPreparacion;
    }

    // Setter para FechaPreparacion
    public void setFechaPreparacion(LocalDateTime FechaPreparacion) {
        this.fechaPreparacion = FechaPreparacion;
    }

    // Getter para FechaEntrega
    public LocalDateTime getFechaEntrega() {
        return fechaEntrega;
    }

    // Setter para FechaEntrega
    public void setFechaEntrega(LocalDateTime FechaEntrega) {
        this.fechaEntrega = FechaEntrega;
    }

    // Getter para estado
    public EnumEstadoDistribucion getEstado() {
        return estado;
    }

    // Setter para estado
    public void setEstado(EnumEstadoDistribucion estado) {
        this.estado = estado;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public int getIdDonAsc() {
        return idDonAsc;
    }

    public void setIdDonAsc(int idDonAsc) {
        this.idDonAsc = idDonAsc;
    }

    public String getEmailbenAsc() {
        return emailbenAsc;
    }

    public void setEmailbenAsc(String emailbenAsc) {
        this.emailbenAsc = emailbenAsc;
    }
}

