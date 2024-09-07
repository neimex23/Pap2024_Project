package org.pap.Clases;
import java.time.LocalDateTime;
import org.pap.Enums.*;

public class Distribucion {

    private LocalDateTime fechaPreparacion;
    private LocalDateTime fechaEntrega;
    private EnumEstadoDistribucion estado;

    public Distribucion(LocalDateTime FechaPreparacion, LocalDateTime FechaEntrega, EnumEstadoDistribucion estado) {
        this.fechaEntrega = FechaEntrega;
        this.fechaPreparacion = FechaPreparacion;
        this.estado = estado;
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

}
