package interfaces;
import classes.*;
import dtClasses.*;
import handlers.*;

import java.util.ArrayList;
import java.util.List;

public class Controlador implements IControlador {
	private ManejadorUsuario manejadorUsuario;
    private ManejadorDonacion manejadorDonacion;
	public Controlador() {
        super();
        this.manejadorUsuario = ManejadorUsuario.getInstancia();
        this.manejadorDonacion = ManejadorDonacion.getInstancia();
    }


    //Operaciones de usario
	
    @Override
	public void altaBeneficiario(String nombre, String email, String dir, DtFechaHora fNac,
	    EnumEstadoBeneficiario estBen, EnumBarrio barrio) {
        // Se crea la instancia del nuevo usuario
        Usuario NuevoUsuario = new Beneficiario(nombre, email, dir, fNac, estBen, barrio);
        // Se agrega la instancia a la coleccion
        manejadorUsuario.agregarUsuario(NuevoUsuario);
		
	}
    @Override
    public void altaRepartidor(String nombre, String email, String numeroLicencia) {
        // Se crea la instancia del nuevo usuario
        Usuario NuevoUsuario = new Repartidor(nombre, email, numeroLicencia);
        // Se agrega la instancia a la coleccion
        manejadorUsuario.agregarUsuario(NuevoUsuario);
    }

    @Override
    public boolean existeEmail(String email) {
        return manejadorUsuario.existeUsuario(email);
    }

    @Override
    public boolean existeLicencia(String licencia) {
        return manejadorUsuario.manExisteLicencia(licencia);
    }

    @Override
    public int conGetCantBeneficiarios(){
        return manejadorUsuario.manGetCantBeneficiarios();
    }

    @Override
    public int conGetCantRepartidores(){
        return manejadorUsuario.manGetCantRepartidores();
    }

    //Operaciones de Donacion

    @Override
    public void altaDonacionAlimento(DtFechaHora FechaIng, String descripcionProducto, int cantElementos) {
        //Tener en cuenta que Id es autoincremental
        int ultimoID = manejadorDonacion.obtenerUltimoID() + 1;
        manejadorDonacion.agregarDonacion(new Alimento(ultimoID, FechaIng, descripcionProducto, cantElementos));
    }

    @Override
    public void altaDonacionArticulo(DtFechaHora FechaIng, String descripcionArt, float peso, String dimensiones) {
        //Tener en cuenta que Id es autoincremental
        int ultimoID = manejadorDonacion.obtenerUltimoID() + 1;
        manejadorDonacion.agregarDonacion(new Articulo(ultimoID, FechaIng, descripcionArt,peso, dimensiones));
    }
    @Override
    public List<DTDonacion> ListarDonaciones() { //Manejar Instancia de alimentos o articulos en Main
        List<Donacion> donaciones = manejadorDonacion.obtenerDonaciones();
        List<DTDonacion> dTDonaciones = new ArrayList<>();
        for (Donacion donacion : donaciones) {
            int idDon = donacion.getId(); DtFechaHora fechaIng = donacion.getFechaIngresada();
            if (donacion instanceof Articulo) {
                Articulo articuloCast = (Articulo) donacion;
                DTArticulo artToAdd = new DTArticulo(idDon, fechaIng, articuloCast.getDescr(), articuloCast.getPeso(), articuloCast.getDimensiones());
                dTDonaciones.add(artToAdd);
            }else {
                Alimento alimentoCast = (Alimento) donacion;
                DTAlimento alimToAdd = new DTAlimento(idDon,fechaIng, alimentoCast.getDescProducto(), alimentoCast.getCantElemntos() );
                dTDonaciones.add(alimToAdd);
            }
        }

        return dTDonaciones;
    }

    //Operaciones de Distribucion

    @Override
    public void agregarDistribucion(DTBeneficiario ben, DTDonacion Donacion) {
        // Implementar la logica para agregar una distribucion
    }

    //ManejadorUsuario retorna una lista de usuarios,que luego se arma aca
    @Override
    public List<DTBeneficiario> ListarBeneficiario() {
        List<Usuario> usuarios = manejadorUsuario.obtenerUsuarios();
        List<DTBeneficiario> beneficiarios = new ArrayList<>();
        
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Beneficiario) {
                beneficiarios.add(((Beneficiario) usuario).toDTBeneficiario());
            }
        }
        
        return beneficiarios;
    }
    
    // Nueva funci�n para obtener DTBeneficiario por email
    public DTBeneficiario obtenerDTBeneficiario(String email) {
        List<Usuario> usuarios = manejadorUsuario.obtenerUsuarios();
        
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Beneficiario && usuario.getEmail().equals(email)) {
                return ((Beneficiario) usuario).toDTBeneficiario();
            }
        }
        
        return null; // Si no se encuentra el beneficiario, devuelve null.
    }

	@Override
	public List<DTDistribucion> ListarDistribuciones(EnumEstadoDistribucion estado) {
		// falta Implementar para listar distribuciones por estado
		return null;
	}

}


