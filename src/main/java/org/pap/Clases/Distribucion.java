package org.pap.Clases;
import org.pap.dtClasses.LocalDateTime;
import org.pap.Enums.*;

public class Distribucion {

    private LocalDateTime fechaPreparacion;
    private LocalDateTime fechaEntrega;
    private EnumEstadoDistribucion estado;
    private Donacion donacion;

    public Distribucion(LocalDateTime FechaPreparacion, LocalDateTime FechaEntrega, EnumEstadoDistribucion estado, Donacion donacion) {
        this.fechaEntrega = FechaEntrega;
        this.fechaPreparacion = FechaPreparacion;
        this.estado = estado;
        this.donacion = donacion;
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

    // Devuelve la donacion asociada a la distribucion

    public Donacion getDonacion() {
        return this.donacion;
    }

    public void setDonacion(Donacion donacionSeleccionada) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        //esta operacion no esta implementada`, implemento el setter (Viky comment)
        this.donacion =donacionSeleccionada;
    }
}
