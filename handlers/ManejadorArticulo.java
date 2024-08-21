package handlers;
import classes.Articulo;
import java.util.ArrayList;
import java.util.List;

public class ManejadorArticulo {
	private static ManejadorArticulo instancia = null;
	private List<Articulo> articulos = new ArrayList<Articulo>();
	
	private ManejadorArticulo() {}

	public static ManejadorArticulo getInstancia() {
		if (instancia == null) 
		{
			instancia = new ManejadorArticulo();
		}
		return instancia;
	}
	
	public void añadirArticulo(Articulo art) {
		this.articulos.add(art);
	}
	
	
	
}
