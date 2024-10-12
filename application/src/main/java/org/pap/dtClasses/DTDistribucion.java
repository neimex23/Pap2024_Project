package org.pap.dtClasses;
import java.time.LocalDateTime;

import org.pap.Enums.EnumEstadoDistribucion;

public class DTDistribucion {
    private int Id;
    private LocalDateTime fechaPreparacion;
    private LocalDateTime fechaEntrega;
    private EnumEstadoDistribucion estado;
    private int donacionAsc;
    private String emailBenefAsc;

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