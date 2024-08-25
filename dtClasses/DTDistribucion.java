package dtClasses;

import enums.EnumEstadoDistribucion;

public class DTDistribucion {
    private DtFechaHora fechaPreparacion;
    private DtFechaHora fechaEntrega;
    private EnumEstadoDistribucion estado;
    private DTDonacion donacionAsc;

    public DTDistribucion(DtFechaHora FechaPreparacion, DtFechaHora FechaEntrega, EnumEstadoDistribucion estado, DTDonacion DonacionAsc) {
        this.fechaEntrega = FechaEntrega;
        this.fechaPreparacion = FechaPreparacion;
        this.estado = estado;
        this.donacionAsc = DonacionAsc;
    }

    // Getter para fechaPreparacion
    public DtFechaHora getFechaPreparacion() {
        return fechaPreparacion;
    }

    // Getter para FechaEntrega
    public DtFechaHora getFechaEntrega() {
        return fechaEntrega;
    }

    // Getter para estado
    public EnumEstadoDistribucion getEstado() {
        return estado;
    }

    public DTDonacion getDonacionAsc() {return donacionAsc;}

}
