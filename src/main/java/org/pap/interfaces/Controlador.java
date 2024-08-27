package org.pap.interfaces;
import org.pap.Clases.*;
import org.pap.dtClasses.*;
import org.pap.handlers.*;
import org.pap.Enums.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Controlador implements IControlador {
	private ManejadorUsuario manejadorUsuario;
    private ManejadorDonacion manejadorDonacion;
    private ManejadorDistribucion manejadorDistribucion;

    private static EntityManager em;
    private static EntityManagerFactory emf;

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
        
        emf = Persistence.createEntityManagerFactory("Conexion");

        //Generamos un EntityManager
        em = emf.createEntityManager();

        //Iniciamos una transacción
        em.getTransaction().begin();

        //Persistimos el objeto
        em.persist(NuevoUsuario);

        //Commmiteamos la transacción
        em.getTransaction().commit();

        //Cerramos el EntityManager
        em.close();

	}
    @Override
    public void altaRepartidor(String nombre, String email, String numeroLicencia) {
        // Se crea la instancia del nuevo usuario
        Usuario NuevoUsuario = new Repartidor(nombre, email, numeroLicencia);
        // Se agrega la instancia a la coleccion
        manejadorUsuario.agregarUsuario(NuevoUsuario);

        emf = Persistence.createEntityManagerFactory("Conexion");
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(NuevoUsuario);
        em.getTransaction().commit();
        em.close();
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
    public int conGetCantBeneficiarios() {
        return manejadorUsuario.manGetCantBeneficiarios();
    }

    @Override
    public int conGetCantRepartidores() {
        return manejadorUsuario.manGetCantRepartidores();
    }

    //Operaciones de Donacion

    @Override
    public void altaDonacionArticulo(LocalDateTime FechaIng, String descripcionArt, float peso, String dimensiones) {
        //Tener en cuenta que Id es autoincremental
        int ultimoID = manejadorDonacion.obtenerUltimoID() + 1;
        manejadorDonacion.agregarDonacion(new Articulo(ultimoID, FechaIng, descripcionArt, peso, dimensiones));

        emf = Persistence.createEntityManagerFactory("Conexion");
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(alimentoAgregar);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void altaDonacionAlimento(LocalDateTime FechaIng, String descripcionProducto, int cantElementos) {
        //Tener en cuenta que Id es autoincremental
        int ultimoID = manejadorDonacion.obtenerUltimoID() + 1;
        Alimento alimentoAgregar = new Alimento(ultimoID, FechaIng, descripcionProducto, cantElementos);
        manejadorDonacion.agregarDonacion(alimentoAgregar);

        emf = Persistence.createEntityManagerFactory("Conexion");
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(donacionAgregar);
        em.getTransaction().commit();
        em.close();
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
    public void agregarDistribucion(LocalDateTime fechaPreparacion, LocalDateTime fechaEntrega, EnumEstadoDistribucion estado, int donacionID, String emailBenf) {
        int ultimoID = manejadorDistribucion.obtenerUltimoID() + 1;
        Distribucion distribucion = new Distribucion(ultimoID, fechaPreparacion, fechaEntrega, estado, donacionID, emailBenf);
        manejadorDistribucion.agregarDistribucion(distribucion);

        emf = Persistence.createEntityManagerFactory("Conexion");
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(distAgregar);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void modificarDistribucion(int idDistribucion, LocalDateTime fechaEntrega, EnumEstadoDistribucion estado){
        // Obtener la lista de distribuciones del manejador
        List<Distribucion> distribuciones = manejadorDistribucion.getDistribuciones();

        // Buscar la distribución a actualizar por su ID
        for (int i = 0; i < distribuciones.size(); i++) {
            Distribucion distribucionActual = distribuciones.get(i);
            if (distribucionActual.getId() == idDistribucion) {
                // Se encontró la distribución, actualiza sus datos
                distribucionActual.setFechaEntrega(fechaEntrega);
                distribucionActual.setEstado(estado);
                modificarDistribucion(distribucionActual.transform());
                break;
            }
        }
    }

    @Override
    public void modificarDistribucion(DTDistribucion distribucion) {
    // Obtener la lista de distribuciones del manejador
    List<Distribucion> distribuciones = ManejadorDistribucion.getInstancia().getDistribuciones();

    // Buscar la distribución a modificar por su ID
    for (int i = 0; i < distribuciones.size(); i++) {
        if (distribuciones.get(i).getId() == distribucion.getId()) {
            // Se encontró la distribución, actualiza sus datos

            Distribucion distribucionActualizada = new Distribucion(distribucion.getId(),distribucion.getFechaPreparacion(), distribucion.getFechaEntrega(), distribucion.getEstado(), distribucion.getDonacionAsc(), distribucion.getEmailBenefAsc());
            distribuciones.set(i, distribucionActualizada);
            break;
        }
    }

        // Dependiendo de cómo esté diseñado el sistema, podrías necesitar
        // persistir los cambios o notificar que la distribución fue actualizada.
    }


    //Lista Todas las Distribuciones indiscriminadamente
    @Override
    public List<DTDistribucion> listarDistribuciones() {
        List<Distribucion> distribuciones = manejadorDistribucion.getDistribuciones();
        List<DTDistribucion> dTDistribuciones = new ArrayList<>();
        for (Distribucion distribucion : distribuciones) {
            dTDistribuciones.add(distribucion.transform());
        }
        return dTDistribuciones;
    }

    @Override
    public List<DTDistribucion> listarDistribucionesPorEstado(EnumEstadoDistribucion estado) {
        List<Distribucion> distribuciones = manejadorDistribucion.getDistribuciones();
        List<DTDistribucion> lista = new ArrayList<>();
        if (estado != null) {
            for (Distribucion distribucionIter : distribuciones) {
                if (distribucionIter.getEstado().equals(estado)) {
                    lista.add(distribucionIter.transform());
                }
            }
        }
        return lista;
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
    @Override
    public DTUsuario obtenerDTBeneficiario(String email) {
        List<Usuario> usuarios = manejadorUsuario.obtenerUsuarios();

        for (Usuario usuario : usuarios) {
            if (usuario instanceof Beneficiario && usuario.getEmail().equals(email)) {
                return usuario.transformarADtUsuario();
            }
        }

        return null; // Si no se encuentra el beneficiario, devuelve null.
    }

    @Override
    public DTDonacion obtenerDonacion(int id) {
        DTDonacion donacion = manejadorDonacion.obtenerDonacionPorID(id).transformarADtDonacion();
        return donacion;
    }

}

