package handlers;
import java.util.ArrayList;
import java.util.List;

import classes.Usuario;
import classes.Beneficiario;
import dtClasses.DTBeneficiario;

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
	// Nueva función para obtener todos los usuarios
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
		boolean existe = false;
		for (Usuario users : usuarios) {
			if (users.getEmail().equals(email)) {
				existe = true;
			}
		}
		return existe;
	}



}


