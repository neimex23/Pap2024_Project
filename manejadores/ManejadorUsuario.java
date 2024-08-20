package manejadores;
import java.util.ArrayList;
import java.util.List;

import classes.Distribucion;

public class ManejadorUsuario {
	private static ManejadorUsuario instancia = null;
	private List<Usuario> usuarios = new ArrayList<Distribucion>();
	
	private ManejadorUsuario() {}
	
	static ManejadorUsuario getInstancia() {
		if (instancia == null) 
		{
			instancia = new ManejadorUsuario();
		}
		return instancia;
	}
	
	public void añadirUsuario(Usuario user) {this.usuarios.add(user);
	}
	
	
	
}
