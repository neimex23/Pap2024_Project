package org.pap.interfaces;
import org.pap.Clases.*;
import org.pap.dtClasses.*;
import org.pap.handlers.*;
import org.pap.Enums.*;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Controlador implements IControlador {
	private ManejadorUsuario manejadorUsuario;
    private ManejadorDonacion manejadorDonacion;
    private ManejadorDistribucion manejadorDistribucion;
	public Controlador() {
        super();
        this.manejadorUsuario = ManejadorUsuario.getInstancia();
        this.manejadorDonacion = ManejadorDonacion.getInstancia();
        this.manejadorDistribucion = ManejadorDistribucion.getInstancia();
    }


    //Operaciones de usario
	
    @Override
	public void altaBeneficiario(String nombre, String email, String dir, LocalDateTime fNac,
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
    public void altaDonacionAlimento(LocalDateTime FechaIng, String descripcionProducto, int cantElementos) {
        //Tener en cuenta que Id es autoincremental
        int ultimoID = manejadorDonacion.obtenerUltimoID() + 1;
        manejadorDonacion.agregarDonacion(new Alimento(ultimoID, FechaIng, descripcionProducto, cantElementos));
    }

    @Override
    public void altaDonacionArticulo(LocalDateTime FechaIng, String descripcionArt, float peso, String dimensiones) {
        //Tener en cuenta que Id es autoincremental
        int ultimoID = manejadorDonacion.obtenerUltimoID() + 1;
        manejadorDonacion.agregarDonacion(new Articulo(ultimoID, FechaIng, descripcionArt,peso, dimensiones));
    }
    @Override
    public List<DTDonacion> ListarDonaciones() { //Manejar Instancia de alimentos o articulos en Main
        List<Donacion> donaciones = manejadorDonacion.obtenerDonaciones();
        List<DTDonacion> dTDonaciones = new ArrayList<>();
        for (Donacion donacion : donaciones) {
            dTDonaciones.add(donacion.transformarADtDonacion());
        }
        return dTDonaciones;
    }

    //Operaciones de Distribucion

    @Override
    public void agregarDistribucion(LocalDateTime fechaPreparacion, LocalDateTime fechaEntrega, EnumEstadoDistribucion estado, int donacionID){
        Donacion donacion = manejadorDonacion.obtenerDonacionPorID(donacionID); //Donacion puede tirar null si el id no es controlado
        Distribucion distribucion = new Distribucion(fechaPreparacion,fechaEntrega,estado);
        manejadorDistribucion.agregarDistribucion(distribucion, donacion);
    }

    @Override
    public void modificarDistribucion(DTDistribucion distribucion) {

    }

    @Override
    public Map<DTDistribucion, DTDonacion> listarDistribuciones() {
        return null;
    }

    @Override
    public Map<DTDistribucion, DTDonacion> ListarDistribuciones(EnumEstadoDistribucion estado) {
        // falta Implementar para listar distribuciones por estado
        return null;
    }

    // Operaciones Beneficiario

    //ManejadorUsuario retorna una lista de usuarios,que luego se arma aca
    @Override
    public List<DTUsuario> ListarBeneficiario() {
        List<Usuario> usuarios = manejadorUsuario.obtenerUsuarios();
        List<DTUsuario> beneficiarios = new ArrayList<>();
        
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Beneficiario) {
                beneficiarios.add(usuario.transformarADtUsuario());
            }
        }
        
        return beneficiarios;
    }
    
    // Nueva funcion para obtener DTBeneficiario por email
    public DTUsuario obtenerDTBeneficiario(String email) {
        List<Usuario> usuarios = manejadorUsuario.obtenerUsuarios();
        
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Beneficiario && usuario.getEmail().equals(email)) {
                return usuario.transformarADtUsuario();
            }
        }
        
        return null; // Si no se encuentra el beneficiario, devuelve null.
    }
}


