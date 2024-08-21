package handlers;
import java.util.ArrayList;
import java.util.List;

import classes.Repartidor;

public class ManejadorRepartidor {
	private static ManejadorRepartidor instancia = null;
	private List<Repartidor> repartidores = new ArrayList<Repartidor>();
	
	private ManejadorRepartidor() {}
	
	public static ManejadorRepartidor getInstancia() {
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
