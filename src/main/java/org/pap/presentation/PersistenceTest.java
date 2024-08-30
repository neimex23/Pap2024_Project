package org.pap.presentation;

import org.pap.Clases.Beneficiario;
import org.pap.Clases.Distribucion;
import org.pap.Clases.Repartidor;
import org.pap.Enums.EnumBarrio;
import org.pap.Enums.EnumEstadoBeneficiario;
import org.pap.Enums.EnumEstadoDistribucion;
import org.pap.handlers.ManejadorUsuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class PersistenceTest {

    public static void main(String[] args) {
        System.out.println("Testeando persistencia");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Conexion");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Distribucion d = new Distribucion(LocalDateTime.now(), LocalDateTime.now(), EnumEstadoDistribucion.ENCAMINO);
        em.persist(d);
        //em.getTransaction().commit();
//        em.close();
//
//        Beneficiario b = new Beneficiario("Victoria Pilone","victoriapilone@gmail.com", "Fakestreet 123", LocalDateTime.now(), EnumEstadoBeneficiario.ACTIVO, EnumBarrio.CORDON );
//        Beneficiario b1 = new Beneficiario("Victoria Pilone22","victoriapilone22@gmail.com", "Fakest22reet 123", LocalDateTime.now(), EnumEstadoBeneficiario.ACTIVO, EnumBarrio.CENTRO );
//        Repartidor r1 = new Repartidor("Repartidor1", "repartidor@gmail.com", "12312idjod");
//        em.persist(b);
//        em.persist(b1);
//        em.persist(r1);
//
//        em.getTransaction().commit();
//        em.close();
        ManejadorUsuario mu = ManejadorUsuario.getInstancia();
        System.out.println(mu.obtenerUsuarios().size());
        mu.obtenerUsuarios().forEach(u -> System.out.println(u.getNombre() + (u instanceof Beneficiario)));
    }
}
