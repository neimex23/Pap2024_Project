package manejadores;
import java.util.ArrayList;
import java.util.List;

import classes.Distribucion;

public class ManejadorRepartidor {
	private static ManejadorRepartidor instancia = null;
	private List<Repartidor> repartidores = new ArrayList<Distribucion>();
	
	private ManejadorRepartidor() {}
	
	static ManejadorRepartidor getInstancia() {
		if (instancia == null) 
		{
			instancia = new ManejadorRepartidor();
		}
		return instancia;
	}
	
	public void añadirRepartidor(Repartidor rep) {
		this.repartidores.add(rep);
	}
	
	
	
}
