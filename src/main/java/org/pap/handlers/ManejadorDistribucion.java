package org.pap.handlers;

import org.pap.Clases.Distribucion;

import java.util.List;


public class ManejadorDistribucion {
	private static ManejadorDistribucion instancia = null;
	private List<Distribucion> distribuciones;

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

	public List<Distribucion> getDistribuciones() {
		return distribuciones;
	}

	public int obtenerUltimoID(){ return this.distribuciones.size(); }

}

