package org.pap.Clases;
import org.pap.dtClasses.DtFechaHora;
import org.pap.Enums.*;

public class Distribucion {

    private DtFechaHora fechaPreparacion;
    private DtFechaHora fechaEntrega;
    private EnumEstadoDistribucion estado;
    private Donacion donacion;

    public Distribucion(DtFechaHora FechaPreparacion, DtFechaHora FechaEntrega, EnumEstadoDistribucion estado, Donacion donacion) {
        this.fechaEntrega = FechaEntrega;
        this.fechaPreparacion = FechaPreparacion;
        this.estado = estado;
        this.donacion = donacion;
    }

    public DtFechaHora getFechaPreparacion() {
        return fechaPreparacion;
    }

    // Setter para FechaPreparacion
    public void setFechaPreparacion(DtFechaHora FechaPreparacion) {
        this.fechaPreparacion = FechaPreparacion;
    }

    // Getter para FechaEntrega
    public DtFechaHora getFechaEntrega() {
        return fechaEntrega;
    }

    // Setter para FechaEntrega
    public void setFechaEntrega(DtFechaHora FechaEntrega) {
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
