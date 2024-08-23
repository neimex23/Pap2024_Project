package interfaces;
import dtClasses.DTBeneficiario;
import dtClasses.DtFechaHora;
import Enums.EnumEstadoBeneficiario;

import java.util.ArrayList;
import java.util.List;

import Enums.EnumBarrio;
import Enums.EnumEstadoDistribucion;
import handlers.ManejadorUsuario;
import classes.Beneficiario;
import classes.Usuario;

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
		// TODO Auto-generated method stub
		
	}
    @Override
    public void altaRepartidor(String nombre, String email, String numeroLicencia) {
    	
    }    	
    @Override
    public void existeEmail(String email) {
        
    }

    @Override
    public void existeLicencia(String licencia) {

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
    
    // Nueva función para obtener DTBeneficiario por email
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
		// Implementar la lógica para agregar una distribución
		
	}


}


