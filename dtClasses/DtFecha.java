package dtClasses;

public class DtFechaHora {
    private int dia;
    private int mes;
    private int anio;
    

    public DtFechaHora(int dia, int mes, int anio) {
    this.dia = dia;
    this.mes = mes;
    this.anio = anio;
   
    }

    public int getDia () {return this.dia;}
    public int getMes () {return this.mes;}
    public int getAnio () {return this.anio;}
    
}

