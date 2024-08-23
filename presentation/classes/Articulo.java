package classes;

import dtClasses.DtFechaHora;
import java.util.Calendar;
import java.util.Date;

public class Articulo extends Donacion {

    private String descr;
    private float peso;
    private String dimensiones;

    // Getter para descr
    public String getDescr() {
        return descr;
    }

    // Setter para descr
    public void setDescr(String descr) {
        this.descr = descr;
    }

    // Getter para peso
    public float getPeso() {
        return peso;
    }

    // Setter para peso
    public void setPeso(float peso) {
        this.peso = peso;
    }

    // Getter para dimensiones
    public String getDimensiones() {
        return dimensiones;
    }

    // Setter para dimensiones
    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }

//Modificacion de paramentros se cambio el tipo de dato de fechaIngresada    
public Articulo(int id, DtFechaHora fechaIngresada, String descr, float peso, String dimensiones) {
        // Llamar al constructor de la clase base (Donacion)
        super(id, fechaIngresada);

        // Inicializar las variables de la subclase
        this.descr = descr;
        this.peso = peso;
        this.dimensiones = dimensiones;
    }

}
