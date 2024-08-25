package org.pap.handlers;
import java.util.ArrayList;
import java.util.List;

import org.pap.Clases.*;

public class ManejadorUsuario {
	private static ManejadorUsuario instancia = null;
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	
	private ManejadorUsuario() {}

	public static ManejadorUsuario getInstancia() {
		if (instancia == null) 
		{
			instancia = new ManejadorUsuario();
		}
		return instancia;
	}
	
	public void agregarUsuario(Usuario user) {
		this.usuarios.add(user);
	}
	// Nueva funcion para obtener todos los usuarios
    public List<Usuario> obtenerUsuarios() {
        return new ArrayList<>(usuarios);
    }
	
	//obtener DTbeneficiario  de beneficiario x email
	//public DTBeneficiario obtenerDTBeneficiario(String email) {
	//    for (Usuario u : usuarios) {
	//        if (u instanceof Beneficiario && u.getEmail().equals(email)) {
	//            return ((Beneficiario) u).toDTBeneficiario();
	//        }
	  //  }
	 //   return null; // Si no se encuentra el beneficiario, devuelve null.
	//}

	public boolean existeUsuario(String email) {
		boolean Existe = false;
		int i = 0;

		while (i < usuarios.size() && !Existe) {
			if (usuarios.get(i).getEmail().equals(email)) {
				Existe = true;
			}
			i++;
		}

		return Existe;
	}

	public boolean manExisteLicencia(String licencia) {
		boolean Existe = false;
		int i = 0;

		while (i < usuarios.size() && !Existe) {
			Usuario user = usuarios.get(i);

			// Verificamos si el usuario es una instancia de Repartidor
			if (user instanceof Repartidor) {
				// Convertimos el Usuario a Repartidor para acceder a su licencia
				Repartidor repartidor = (Repartidor) user;
				// Comparamos la licencia
				if (repartidor.getNumeroLicencia().equals(licencia)) {
					Existe = true; // Licencia encontrada
				}
			}
			i++;
		}

		return Existe; // Devuelve si se encontró la licencia o no
	}

	public int manGetCantBeneficiarios() {
		int cant = 0;
		for (Usuario user : usuarios) {
			// Verificamos si el usuario es una instancia de Beneficiario
			if (user instanceof Beneficiario) {
				// Si el Usuario es de tipo Beneficiario lo contamos
				cant ++;
			}
		}
		return cant;
	}

	public int manGetCantRepartidores() {
		int cant = 0;
		for (Usuario user : usuarios) {
			// Verificamos si el usuario es una instancia de Beneficiario
			if (user instanceof Repartidor) {
				// Si el Usuario es de tipo Repartidor lo contamos
				cant ++;
			}
		}
		return cant;
	}


}


