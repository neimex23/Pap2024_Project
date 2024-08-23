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
	
	
	
}
