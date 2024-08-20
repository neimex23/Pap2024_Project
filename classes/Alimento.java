package classes;	

public class Alimento extends Donacion{
	    
	private string descProducto;
	private int cantElemntos;
	
	 // Getter para descProducto
    public String getDescProducto() {
        return descProducto;
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

    public Alimento(int id, DateTime fechaIngresada, String descProducto, int cantElemntos) {
        super(id, fechaIngresada);
        this.descProducto = descProducto;
        this.cantElemntos = cantElemntos;
    }

}
