package org.pap.handlers;

import java.util.ArrayList;
import org.pap.Clases.Distribucion;

import java.util.List;

public class ManejadorDistribucion {

    private static ManejadorDistribucion instancia = null;
    private List<Distribucion> distribuciones;

    private ManejadorDistribucion() {
        // Inicializa la lista
        this.distribuciones = new ArrayList<>();
    }

    public static ManejadorDistribucion getInstancia() {
        if (instancia == null) {
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

    public int obtenerUltimoID() {
        return this.distribuciones.size();
    }

}
