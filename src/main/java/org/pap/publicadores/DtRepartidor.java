/**
 * DtRepartidor.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4.1-SNAPSHOT Nov 07, 2023 (07:57:58 UTC) WSDL2Java emitter.
 */

package org.pap.publicadores;

public class DtRepartidor  extends org.pap.publicadores.DtUsuario  implements java.io.Serializable {
    private java.lang.String numeroLicencia;

    public DtRepartidor() {
    }

    public DtRepartidor(
           java.lang.String nombre,
           java.lang.String password,
           java.lang.String email,
           java.lang.String numeroLicencia) {
        super(
            nombre,
            password,
            email);
        this.numeroLicencia = numeroLicencia;
    }


    /**
     * Gets the numeroLicencia value for this DtRepartidor.
     * 
     * @return numeroLicencia
     */
    public java.lang.String getNumeroLicencia() {
        return numeroLicencia;
    }


    /**
     * Sets the numeroLicencia value for this DtRepartidor.
     * 
     * @param numeroLicencia
     */
    public void setNumeroLicencia(java.lang.String numeroLicencia) {
        this.numeroLicencia = numeroLicencia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DtRepartidor)) return false;
        DtRepartidor other = (DtRepartidor) obj;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.numeroLicencia==null && other.getNumeroLicencia()==null) || 
             (this.numeroLicencia!=null &&
              this.numeroLicencia.equals(other.getNumeroLicencia())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getNumeroLicencia() != null) {
            _hashCode += getNumeroLicencia().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DtRepartidor.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://publicadores.pap.org/", "dtRepartidor"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroLicencia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroLicencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}