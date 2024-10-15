/**
 * ControladorPublish.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4.1-SNAPSHOT Nov 07, 2023 (07:57:58 UTC) WSDL2Java emitter.
 */

package org.pap.publicadores;

public interface ControladorPublish extends java.rmi.Remote {
    public org.pap.publicadores.DtUsuario inicioSecion(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException;
    public org.pap.publicadores.DtDistribucion[] listarDistribuciones() throws java.rmi.RemoteException;
    public void modificarDistribucion(int arg0, org.pap.publicadores.LocalDateTime arg1, org.pap.publicadores.EnumEstadoDistribucion arg2) throws java.rmi.RemoteException;
}
