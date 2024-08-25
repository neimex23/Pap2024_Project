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

	public int obtenerUltimoID(){ return this.donaciones.size(); }

	public List<Donacion> obtenerDonaciones(){ return this.donaciones; }

	public Donacion obtenerDonacionPorID(int id){
		boolean encontrado = false;
		int i = 0;
		Donacion donacion = null;
		while(i <= donaciones.size() && !encontrado ){
			if(donaciones.get(i).getId() == id){
				encontrado = true;
				donacion = this.donaciones.get(i);
			}
		}
		return donacion;
	}

    public List<Donacion> getListaDonacion() {
        return this.donaciones;
    }



}


