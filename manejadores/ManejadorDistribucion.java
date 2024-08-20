package manejadores;
import java.util.ArrayList;
import java.util.List;

import classes.Distribucion;

public class ManejadorDistribucion {
	private static ManejadorDistribucion instancia = null;
	private List<Distribucion> distribuciones = new ArrayList<Distribucion>();
	
	private ManejadorDistribucion() {}
	
	static ManejadorDistribucion getInstancia() {
		if (instancia == null) 
		{
			instancia = new ManejadorDistribucion();
		}
		return instancia;
	}
	
	public void añadirDistribucion(Distribucion dist) {
		this.distribuciones.add(dist);
	}
	
	
	
}
