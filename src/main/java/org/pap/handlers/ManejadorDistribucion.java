package org.pap.handlers;
import java.util.ArrayList;
import java.util.List;

import org.pap.Clases.Distribucion;

public class ManejadorDistribucion {
	private static ManejadorDistribucion instancia = null;
	private List<Distribucion> distribuciones = new ArrayList<Distribucion>();
	
	private ManejadorDistribucion() {}

	public static ManejadorDistribucion getInstancia() {
		if (instancia == null) 
		{
			instancia = new ManejadorDistribucion();
		}
		return instancia;
	}
	
	public void agregarDistribucion(Distribucion dist) {
		this.distribuciones.add(dist);
	}
	
	public List<Distribucion> getListaDistribuciones(){
            return distribuciones;
        }
	
}

