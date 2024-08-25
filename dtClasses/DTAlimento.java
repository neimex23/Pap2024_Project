package dtClasses;

public class DTAlimento extends DTDonacion{
	    
	private String descProducto;
	private int cantElemntos;
	
	 // Getter para descProducto
    public String getDescProducto() {
        return this.descProducto;
    }

    // Getter para cantElemntos
    public int getCantElemntos() {
        return cantElemntos;
    }

    public DTAlimento(int id, DtFechaHora fechaIngresada, String descProducto, int cantElemntos) {
        super(id, fechaIngresada);
        this.descProducto = descProducto;
        this.cantElemntos = cantElemntos;
    }

}