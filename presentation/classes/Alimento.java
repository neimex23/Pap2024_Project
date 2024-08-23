package classes;

import dtClasses.DtFechaHora;

public class Alimento extends Donacion {

    private String descProducto;
    private int cantElemntos;

    // Getter para descProducto
    public String getDescProducto() {
        return this.descProducto;
    }

    // Setter para descProducto
    public void setDescProducto(String descProducto) {
        this.descProducto = descProducto;
    }

    // Getter para cantElemntos
    public int getCantElemntos() {
        return cantElemntos;
    }

    // Setter para cantElemntos
    public void setCantElemntos(int cantElemntos) {
        this.cantElemntos = cantElemntos;
    }

    public Alimento(int id, DtFechaHora fechaIngresada, String descProducto, int cantElemntos) {
        super(id, fechaIngresada);
        this.descProducto = descProducto;
        this.cantElemntos = cantElemntos;
    }

}
