package manejadores;
import java.util.ArrayList;
import java.util.List;

import classes.Distribucion;

public class ManejadorDonacion {
	private static ManejadorDonacion instancia = null;
	private List<Donacion> donaciones = new ArrayList<Distribucion>();
	
	private ManejadorDonacion() {}
	
	static ManejadorDonacion getInstancia() {
		if (instancia == null) 
		{
			instancia = new ManejadorDonacion();
		}
		return instancia;
	}
	
	public void añadirDonacion(Donacion don) {
		this.donaciones.add(don);
	}
	
	
	
}
