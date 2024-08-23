package handlers;

import classes.Articulo;
import java.util.ArrayList;
import java.util.List;

public class ManejadorArticulo {

    private static ManejadorArticulo instancia = null;
    private List<Articulo> articulos = new ArrayList<Articulo>();

    private ManejadorArticulo() {
    }

    public static ManejadorArticulo getInstancia() {
        if (instancia == null) {
            instancia = new ManejadorArticulo();
        }
        return instancia;
    }

    public void añadirArticulo(Articulo art) {
        this.articulos.add(art);
    }

    public int obtenerMayorId() {
        // Inicializar el mayorId con -1 se usa para indicar que no hay elementos.
        int mayorId = -1;
        // Recorre la lista de alimentos
        for (Articulo alimento : articulos) {
            if (alimento.getId() > mayorId) {
                mayorId = alimento.getId();
            }
        }
        return mayorId;
    }

}
