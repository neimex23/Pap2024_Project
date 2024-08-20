package classes;	

public class Usuario {

	private string nombre;
	private string email;

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
	
	}