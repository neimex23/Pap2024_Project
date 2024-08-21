package manejadores;
import java.util.ArrayList;
import java.util.List;

import classes.Distribucion;

public class ManejadorArticulo {
	private static ManejadorArticulo instancia = null;
	private List<Articulo> articulos = new ArrayList<Articulo>();
	
	private ManejadorArticulo() {}
	
	static ManejadorArticulo getInstancia() {
		if (instancia == null) 
		{
			instancia = new ManejadorArticulo();
		}
		return instancia;
	}
	
	public void añadirArticulo(Articulo art) {
		this.donaciones.add(art);
	}
	
	
	
}
