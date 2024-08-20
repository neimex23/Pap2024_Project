package classes;
import dtClasses.DtFechaHora;

public class Distribucion {
	
	public DtFechaHora FechaPreparacion;
	public DtFechaHora FechaEntrega;
	public EstadoDistribucion estado;	
	
	public DtFechaHora getFechaPreparacion() {
        return FechaPreparacion;
    }

    // Setter para FechaPreparacion
    public void setFechaPreparacion(DtFechaHora FechaPreparacion) {
        this.FechaPreparacion = FechaPreparacion;
    }

    // Getter para FechaEntrega
    public DtFechaHora getFechaEntrega() {
        return FechaEntrega;
    }

    // Setter para FechaEntrega
    public void setFechaEntrega(DtFechaHora FechaEntrega) {
        this.FechaEntrega = FechaEntrega;
    }

    // Getter para estado
    public EstadoDistribucion getEstado() {
        return estado;
    }

    // Setter para estado
    public void setEstado(EstadoDistribucion estado) {
        this.estado = estado;
    }
}
