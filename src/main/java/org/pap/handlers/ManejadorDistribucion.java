package org.pap.handlers;

import org.pap.Clases.Donacion;
import org.pap.Clases.Distribucion;

import java.util.Map;
import java.util.HashMap;


public class ManejadorDistribucion {
	private static ManejadorDistribucion instancia = null;
	private Map<Distribucion, Donacion> distribuciones = new HashMap<>();

	private ManejadorDistribucion() {}

	public static ManejadorDistribucion getInstancia() {
		if (instancia == null)
		{
			instancia = new ManejadorDistribucion();
		}
		return instancia;
	}

	public void agregarDistribucion(Distribucion dist, Donacion don) {
		this.distribuciones.put(dist, don);
	}

	public Map<Distribucion, Donacion> getDistribuciones() {
		return distribuciones;
	}

}

