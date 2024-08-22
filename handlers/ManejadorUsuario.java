package handlers;
import java.util.ArrayList;
import java.util.List;

import classes.Usuario;

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
	
	public void añadirUsuario(Usuario user) {
		this.usuarios.add(user);
	}
	
	
	
}


