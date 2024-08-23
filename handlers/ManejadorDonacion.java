package handlers;
import java.util.ArrayList;
import java.util.List;

import classes.Donacion;

public class ManejadorDonacion {
	private static ManejadorDonacion instancia = null;
	private List<Donacion> donaciones = new ArrayList<Donacion>();
	
	private ManejadorDonacion() {}

	public static ManejadorDonacion getInstancia() {
		if (instancia == null) 
		{
			instancia = new ManejadorDonacion();
		}
		return instancia;
	}
	
	public void agregarDonacion(Donacion don) {
		this.donaciones.add(don);
	}
	
	
	
}


