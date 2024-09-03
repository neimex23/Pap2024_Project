package org.pap.interfaces;

import org.pap.Clases.*;
import org.pap.dtClasses.*;
import org.pap.handlers.*;
import org.pap.Enums.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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


    @Override
    public void cargarBaseDatos() {
        // Solucion utilizando Hibernate ORM con CriteriaQuery:
        emf = Persistence.createEntityManagerFactory("Conexion");
        // Crear una instancia de EntityManager
        EntityManager instanceEM = emf.createEntityManager();

        try {
            // Comenzar la transacción
            instanceEM.getTransaction().begin();

            // Cargar todos los usuarios usando CriteriaQuery
            CriteriaBuilder criBuild = instanceEM.getCriteriaBuilder();
            CriteriaQuery<Usuario> queryUsuario = criBuild.createQuery(Usuario.class);
            Root<Usuario> rootUsuario = queryUsuario.from(Usuario.class);
            queryUsuario.select(rootUsuario);
            List<Usuario> usuarios = instanceEM.createQuery(queryUsuario).getResultList();
            for (Usuario usuario : usuarios) {
                manejadorUsuario.agregarUsuario(usuario);
            }

            // Cargar todas las donaciones usando CriteriaQuery
            CriteriaQuery<Donacion> queryDonacion = criBuild.createQuery(Donacion.class);
            Root<Donacion> rootDonacion = queryDonacion.from(Donacion.class);
            queryDonacion.select(rootDonacion);
            List<Donacion> donaciones = instanceEM.createQuery(queryDonacion).getResultList();
            for (Donacion donacion : donaciones) {
                manejadorDonacion.agregarDonacion(donacion);
            }

            // Cargar todas las distribuciones usando CriteriaQuery
            CriteriaQuery<Distribucion> queryDistribucion = criBuild.createQuery(Distribucion.class);
            Root<Distribucion> rootDistribucion = queryDistribucion.from(Distribucion.class);
            queryDistribucion.select(rootDistribucion);
            List<Distribucion> distribuciones = instanceEM.createQuery(queryDistribucion).getResultList();
            for (Distribucion distribucion : distribuciones) {
                manejadorDistribucion.agregarDistribucion(distribucion);
            }

            // Confirmar la transacción
            instanceEM.getTransaction().commit();
        } catch (Exception e) {
            // En caso de error, revertir la transacción
            instanceEM.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            // Cerrar el EntityManager
            instanceEM.close();
        }
    }

        /* Solucion anterior:
        emf = Persistence.createEntityManagerFactory("Conexion");
        // Crear una instancia de EntityManager
        em = emf.createEntityManager();

        try {
            // Comenzar la transacción
            em.getTransaction().begin();

            // Cargar todos los usuarios y agregarlos al ManejadorUsuario
            List<Usuario> usuarios = em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
            for (Usuario usuario : usuarios) {
                manejadorUsuario.agregarUsuario(usuario);
            }

            // Cargar todas las donaciones y agregarlas al ManejadorDonacion
            List<Donacion> donaciones = em.createQuery("SELECT d FROM Donacion d", Donacion.class).getResultList();
            for (Donacion donacion : donaciones) {
                manejadorDonacion.agregarDonacion(donacion);
            }

            // Cargar todas las distribuciones y agregarlas al ManejadorDistribucion
            List<Distribucion> distribuciones = em.createQuery("SELECT d FROM Distribucion d", Distribucion.class).getResultList();
            for (Distribucion distribucion : distribuciones) {
                manejadorDistribucion.agregarDistribucion(distribucion);
            }

            // Confirmar la transacción
            em.getTransaction().commit();
        } catch (Exception e) {
            // En caso de error, revertir la transacción
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            // Cerrar el EntityManager
            em.close();
        }
    }*/

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
        Articulo artAgregar = new Articulo(ultimoID, FechaIng, descripcionArt, peso, dimensiones);
        manejadorDonacion.agregarDonacion(artAgregar);

        emf = Persistence.createEntityManagerFactory("Conexion");
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(artAgregar);
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
        em.persist(alimentoAgregar);
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
        em.persist(distribucion);
        em.getTransaction().commit();
        em.close();
    }

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


    private void modificarDistribucion(DTDistribucion dtoDistribucion) {
    // Obtener la lista de distribuciones del manejador
        List<Distribucion> distribuciones = ManejadorDistribucion.getInstancia().getDistribuciones();

        // Buscar la distribución a modificar por su ID
        int i = 0;
        boolean encontrado = false;

        while (i < distribuciones.size() && !encontrado) {
            if (distribuciones.get(i).getId() == dtoDistribucion.getId()) {
                // Se encontró la distribución, actualiza sus datos
                Distribucion distribucionActualizada = new Distribucion(dtoDistribucion.getId(),dtoDistribucion.getFechaPreparacion(), dtoDistribucion.getFechaEntrega(), dtoDistribucion.getEstado(), dtoDistribucion.getDonacionAsc(), dtoDistribucion.getEmailBenefAsc());
                distribuciones.set(i, distribucionActualizada);
                encontrado = true;
            }
            i++;
        }

        try {
            emf = Persistence.createEntityManagerFactory("Conexion");
            em = emf.createEntityManager();
            em.getTransaction().begin();
            Distribucion distribucion = em.find(Distribucion.class, dtoDistribucion.getId());

            if (distribucion != null) {
                // Actualizar los campos de la distribución
                distribucion.setFechaPreparacion(dtoDistribucion.getFechaPreparacion());
                distribucion.setFechaEntrega(dtoDistribucion.getFechaEntrega());
                distribucion.setEstado(dtoDistribucion.getEstado());
                distribucion.setIdDonAsc(dtoDistribucion.getDonacionAsc());
                distribucion.setEmailbenAsc(dtoDistribucion.getEmailBenefAsc());

                // Guardar los cambios en la base de datos
                em.merge(distribucion);
            }

            // Confirmar la transacción
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace(); // Manejar la excepción según corresponda
        } finally {
            em.close();
        }


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
    //listar Beneficiarios por Zona
    @Override
    public List<DTUsuario> ListarBeneficiarioZona(EnumBarrio barrio) {
        List<Usuario> usuarios = manejadorUsuario.obtenerUsuarios();
        List<DTUsuario> lista = new ArrayList<>();

        if (barrio != null) {
            for (Usuario usuario : usuarios) {
                if (usuario instanceof Beneficiario) {
                    Beneficiario beneficiario = (Beneficiario) usuario;
                    if (beneficiario.getBarrio().equals(barrio)) {
                    // Creación manual del objeto DTBeneficiario
                        lista.add(beneficiario.transformarADtUsuario());
                    }
                }
            }
        }


        return lista;
    }
    //listar Beneficiarios por Estado
    @Override
    public List<DTUsuario> ListarBeneficiarioEstado(EnumEstadoBeneficiario estado) {
        List<Usuario> usuarios = manejadorUsuario.obtenerUsuarios();
        List<DTUsuario> lista = new ArrayList<>();
        if (estado != null) {
            for (Usuario usuario : usuarios) {
                if(usuario instanceof Beneficiario){
                    Beneficiario beneficiario = (Beneficiario) usuario;
                    if (beneficiario.getEstado().equals(estado)) {
                    // Creación manual del objeto DTBeneficiario
                        lista.add(beneficiario.transformarADtUsuario());
                    }
                }
            }
        }
        return lista;
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
