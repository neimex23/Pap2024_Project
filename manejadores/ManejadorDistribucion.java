package manejadores;
import java.util.ArrayList;
import java.util.List;

import classes.Distribucion;

public class ManejadorDistribucion {
	private static ManejadorDistribucion intancia = null;
	private List<Distribucion> distribuciones = new ArrayList<Distribucion>();
	
	private ManejadorDistribucion() {}
	
	static ManejadorDistribucion getIntancia() {
		if (intancia == null) 
		{
			intancia = new ManejadorDistribucion();
		}
		return intancia;
	}
	
	public void añadirDistribucion(Distribucion dist) {
		this.distribuciones.add(dist);
	}
	
	
	
}
