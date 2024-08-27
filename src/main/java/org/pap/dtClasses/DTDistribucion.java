package org.pap.dtClasses;

import org.pap.Enums.EnumEstadoDistribucion;

public class DTDistribucion {
    private DTFechaHora fechaPreparacion;
    private DTFechaHora fechaEntrega;
    private EnumEstadoDistribucion estado;
    private DTDonacion donacionAsc;

    public DTDistribucion(DTFechaHora FechaPreparacion, DTFechaHora FechaEntrega, EnumEstadoDistribucion estado, DTDonacion DonacionAsc) {
        this.fechaEntrega = FechaEntrega;
        this.fechaPreparacion = FechaPreparacion;
        this.estado = estado;
        this.donacionAsc = DonacionAsc;
    }

    // Getter para fechaPreparacion
    public DTFechaHora getFechaPreparacion() {
        return fechaPreparacion;
    }

    // Getter para FechaEntrega
    public DTFechaHora getFechaEntrega() {
        return fechaEntrega;
    }

    // Getter para estado
    public EnumEstadoDistribucion getEstado() {
        return estado;
    }

    public DTDonacion getDonacionAsc() {return donacionAsc;}

}
