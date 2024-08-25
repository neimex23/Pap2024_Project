package dtClasses;

import enums.EnumEstadoDistribucion;

public class DTDistribucion {
    private DtFechaHora fechaPreparacion;
    private DtFechaHora fechaEntrega;
    private EnumEstadoDistribucion estado;

    public DTDistribucion(DtFechaHora FechaPreparacion, DtFechaHora FechaEntrega, EnumEstadoDistribucion estado){
        this.fechaEntrega = FechaEntrega;
        this.fechaPreparacion = FechaPreparacion;
        this.estado = estado;
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

}
