package classes;
import dtClasses.DtFechaHora;

public class Distribucion {
	
	private DtFechaHora FechaPreparacion;
	private DtFechaHora FechaEntrega;
	private EstadoDistribucion estado;	
	
    public Distribucion(DtFechaHora FechaPreparacion, DtFechaHora FechaEntrega, EstadoDistribucion estado){
        this.FechaEntrega = FechaEntrega;
        this.FechaPreparacion = FechaPreparacion;
        this.estado = estado;
    }

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
