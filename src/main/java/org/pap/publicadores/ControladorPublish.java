/**
 * ControladorPublish.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4.1-SNAPSHOT Nov 07, 2023 (07:57:58 UTC) WSDL2Java emitter.
 */

package org.pap.publicadores;

public interface ControladorPublish extends java.rmi.Remote {
    public org.pap.publicadores.DtUsuario obtenerUsuario(java.lang.String arg0) throws java.rmi.RemoteException;
    public org.pap.publicadores.DtDistribucion[] listarDistribucionesPorEstado(java.lang.String arg0) throws java.rmi.RemoteException;
    public org.pap.publicadores.DtDistribucion[] listarDistribucionesPorZona(java.lang.String arg0) throws java.rmi.RemoteException;
    public org.pap.publicadores.DtDistribucion[] listarDistribuciones() throws java.rmi.RemoteException;
    public void modificarBeneficiario(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3, java.util.Calendar arg4, java.lang.String arg5, java.lang.String arg6) throws java.rmi.RemoteException;
    public void modificarRepartidor(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3) throws java.rmi.RemoteException;
    public void modificarDistribucion(int arg0, java.util.Calendar arg1, java.lang.String arg2) throws java.rmi.RemoteException;
    public boolean inicioSecion(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException;
}
