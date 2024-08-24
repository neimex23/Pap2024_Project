package interfaces;

import classes.*;
import dtClasses.*;
import Enums.*;
import handlers.*;

public class Controlador implements IControlador {
	private ManejadorUsuario manejadorUsuario;
	public Controlador() {
        super();
        this.manejadorUsuario = ManejadorUsuario.getInstancia();
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
    }

    @Override
    public void altaDonacionArticulo(DtFechaHora FechaIng, String descripcionArt, float peso, String dimensiones) {
        //Tener en cuenta que Id es autoincremental
    }

      

    @Override
    public void agregarDistribucion(DTBeneficiario ben, DTDonaciones Donacion) {

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
	public List<DTDistribuciones> ListarDistribuciones(EnumEstadoDistribucion estado) {
		// falta Implementar para listar distribuciones por estado
		return null;
	}


	@Override
	public List<DTDonaciones> ListarDonaciones() {
		
		return null;
	}


	@Override
	public void agregarDistribucion(DTBeneficiario ben, DTDonaciones Donacion) {
		// Implementar la l�gica para agregar una distribuci�n
		
	}


}


