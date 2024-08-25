package org.pap.dtClasses;

public abstract class DTUsuario {
    private String nombre;
    private String email;

    // Getter para nombre
    public String getNombre() {
        return nombre;
    }

    // Getter para email
    public String getEmail() {
        return email;
    }

    public DTUsuario(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }



}

