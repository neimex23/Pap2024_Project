package interfaces;
import dtClasses.DTBeneficiario;
import dtClasses.DtFechaHora;
import Enums.EnumEstadoBeneficiario;

import java.util.List;

import Enums.EnumBarrio;
import Enums.EnumEstadoDistribucion;
import handlers.ManejadorUsuario;
import classes.Beneficiario;

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

	


	@Override
	public List<DTBeneficiario> ListarBeneficiario() {
		return manejadorUsuario.listarBeneficiarios();
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


