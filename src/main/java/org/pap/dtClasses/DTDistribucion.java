package org.pap.dtClasses;

import org.pap.Enums.EnumEstadoDistribucion;

public class DTDistribucion {
    private LocalDateTime fechaPreparacion;
    private LocalDateTime fechaEntrega;
    private EnumEstadoDistribucion estado;
    private DTDonacion donacionAsc;

    public DTDistribucion(LocalDateTime FechaPreparacion, LocalDateTime FechaEntrega, EnumEstadoDistribucion estado, DTDonacion DonacionAsc) {
        this.fechaEntrega = FechaEntrega;
        this.fechaPreparacion = FechaPreparacion;
        this.estado = estado;
        this.donacionAsc = DonacionAsc;
    }

    // Getter para fechaPreparacion
    public LocalDateTime getFechaPreparacion() {
        return fechaPreparacion;
    }

    // Getter para FechaEntrega
    public LocalDateTime getFechaEntrega() {
        return fechaEntrega;
    }

    // Getter para estado
    public EnumEstadoDistribucion getEstado() {
        return estado;
    }

    public DTDonacion getDonacionAsc() {return donacionAsc;}

}
