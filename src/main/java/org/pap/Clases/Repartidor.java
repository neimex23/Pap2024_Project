package org.pap.Clases;

import org.pap.dtClasses.DTRepartidor;
import org.pap.dtClasses.DTUsuario;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.DiscriminatorValue;


@Entity
@DiscriminatorValue("R")
public class Repartidor extends Usuario {


	    private String numeroLicencia;

	    public Repartidor(String nombre, String email, String numeroLicencia) {
	        super(nombre, email);
	        this.numeroLicencia = numeroLicencia;
	    }

		public Repartidor() {};
	    // Getter para numeroLicencia
	    public String getNumeroLicencia() {
	        return numeroLicencia;
	    }

	    // Setter para numeroLicencia
	    public void setNumeroLicencia(String numeroLicencia) {
	        this.numeroLicencia = numeroLicencia;
	    }


	@Override
	public DTUsuario transformarADtUsuario() {
		return new DTRepartidor(this.getNombre(), this.getEmail(), this.getNumeroLicencia());
	}
}