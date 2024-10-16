package org.pap.publicadores;

public class ControladorPublishProxy implements org.pap.publicadores.ControladorPublish {
  private String _endpoint = null;
  private org.pap.publicadores.ControladorPublish controladorPublish = null;
  
  public ControladorPublishProxy() {
    _initControladorPublishProxy();
  }
  
  public ControladorPublishProxy(String endpoint) {
    _endpoint = endpoint;
    _initControladorPublishProxy();
  }
  
  private void _initControladorPublishProxy() {
    try {
      controladorPublish = (new org.pap.publicadores.ControladorPublishServiceLocator()).getControladorPublishPort();
      if (controladorPublish != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)controladorPublish)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)controladorPublish)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (controladorPublish != null)
      ((javax.xml.rpc.Stub)controladorPublish)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.pap.publicadores.ControladorPublish getControladorPublish() {
    if (controladorPublish == null)
      _initControladorPublishProxy();
    return controladorPublish;
  }
  
  public boolean inicioSecion(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException{
    if (controladorPublish == null)
      _initControladorPublishProxy();
    return controladorPublish.inicioSecion(arg0, arg1);
  }
  
  public void modificarDistribucion(int arg0, org.pap.publicadores.LocalDateTime arg1, java.lang.String arg2) throws java.rmi.RemoteException{
    if (controladorPublish == null)
      _initControladorPublishProxy();
    controladorPublish.modificarDistribucion(arg0, arg1, arg2);
  }
  
  public org.pap.publicadores.DtDistribucion[] listarDistribuciones() throws java.rmi.RemoteException{
    if (controladorPublish == null)
      _initControladorPublishProxy();
    return controladorPublish.listarDistribuciones();
  }
  
  public org.pap.publicadores.DtUsuario obtenerUsuario(java.lang.String arg0) throws java.rmi.RemoteException{
    if (controladorPublish == null)
      _initControladorPublishProxy();
    return controladorPublish.obtenerUsuario(arg0);
  }
  
  
}