package org.pap.Clases;

import org.pap.dtClasses.DTUsuario;

import javax.persistence.*;


@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TIPOUSUARIO")
public abstract class Usuario {

    @Id
	private String nombre;
	private String email;

    public Usuario(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    // Getter para nombre
    public String getNombre() {
        return nombre;
    }

    // Setter para nombre
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter para email
    public String getEmail() {
        return email;
    }

    // Setter para email
    public void setEmail(String email) {
        this.email = email;
    }

    public abstract DTUsuario transformarADtUsuario();
	
	}