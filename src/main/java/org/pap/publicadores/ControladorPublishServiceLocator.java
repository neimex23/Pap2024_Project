/**
 * ControladorPublishServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4.1-SNAPSHOT Nov 07, 2023 (07:57:58 UTC) WSDL2Java emitter.
 */

package org.pap.publicadores;

public class ControladorPublishServiceLocator extends org.apache.axis.client.Service implements org.pap.publicadores.ControladorPublishService {

    public ControladorPublishServiceLocator() {
    }


    public ControladorPublishServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ControladorPublishServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ControladorPublishPort
    private java.lang.String ControladorPublishPort_address = "http://localhost:3002/controlador";

    public java.lang.String getControladorPublishPortAddress() {
        return ControladorPublishPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ControladorPublishPortWSDDServiceName = "ControladorPublishPort";

    public java.lang.String getControladorPublishPortWSDDServiceName() {
        return ControladorPublishPortWSDDServiceName;
    }

    public void setControladorPublishPortWSDDServiceName(java.lang.String name) {
        ControladorPublishPortWSDDServiceName = name;
    }

    public org.pap.publicadores.ControladorPublish getControladorPublishPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ControladorPublishPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getControladorPublishPort(endpoint);
    }

    public org.pap.publicadores.ControladorPublish getControladorPublishPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.pap.publicadores.ControladorPublishPortBindingStub _stub = new org.pap.publicadores.ControladorPublishPortBindingStub(portAddress, this);
            _stub.setPortName(getControladorPublishPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setControladorPublishPortEndpointAddress(java.lang.String address) {
        ControladorPublishPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.pap.publicadores.ControladorPublish.class.isAssignableFrom(serviceEndpointInterface)) {
                org.pap.publicadores.ControladorPublishPortBindingStub _stub = new org.pap.publicadores.ControladorPublishPortBindingStub(new java.net.URL(ControladorPublishPort_address), this);
                _stub.setPortName(getControladorPublishPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("ControladorPublishPort".equals(inputPortName)) {
            return getControladorPublishPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://publicadores.pap.org/", "ControladorPublishService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://publicadores.pap.org/", "ControladorPublishPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ControladorPublishPort".equals(portName)) {
            setControladorPublishPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
