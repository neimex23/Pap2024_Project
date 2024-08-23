package handlers;
import java.util.ArrayList;
import java.util.List;
import classes.Alimento;

public class ManejadorAlimento {
	private static ManejadorAlimento instancia = null;
	private List<Alimento> alimentos = new ArrayList<Alimento>();
	
	private ManejadorAlimento() {}

	public static ManejadorAlimento getInstancia() {
		if (instancia == null) 
		{
			instancia = new ManejadorAlimento();
		}
		return instancia;
	}
	
	public void añadirAlimento(Alimento ali) {this.alimentos.add(ali);
	}
	
        // Devuelve el valor mas alto de Id sirve para no sobre escribir datos en la lista de alimentos
        public int obtenerMayorId() {
        // Inicializar el mayorId con -1 se usa para indicar que no hay elementos.
        int mayorId = -1;
        // Recorre la lista de alimentos
        for (Alimento alimento : alimentos) {
            if (alimento.getId() > mayorId) {
                mayorId = alimento.getId();
            }
        }
        return mayorId;
    }	

}
