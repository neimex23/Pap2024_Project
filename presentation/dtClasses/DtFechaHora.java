package dtClasses;

public class DtFechaHora {
    private int dia;
    private int mes;
    private int anio;
    private int hora;
    private int minutos;

    public DtFechaHora(int dia, int mes, int anio, int hora, int minutos) {
    this.dia = dia;
    this.mes = mes;
    this.anio = anio;
    this.hora = hora;
    this.minutos = minutos;
    }

    public int getDia () {return this.dia;}
    public int getMes () {return this.mes;}
    public int getAnio () {return this.anio;}
    public int getHora () {return this.hora;}
    public int getMinutos() {return this.minutos;}
}

