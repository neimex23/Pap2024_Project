/**
 * DtBeneficiario.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4.1-SNAPSHOT Nov 07, 2023 (07:57:58 UTC) WSDL2Java emitter.
 */

package org.pap.publicadores;

public class DtBeneficiario  extends org.pap.publicadores.DtUsuario  implements java.io.Serializable {
    private java.lang.String direccion;

    private java.lang.String fechaNacimiento;

    private org.pap.publicadores.EnumEstadoBeneficiario estado;

    private org.pap.publicadores.EnumBarrio barrio;

    public DtBeneficiario() {
    }

    public DtBeneficiario(
           java.lang.String nombre,
           java.lang.String password,
           java.lang.String email,
           java.lang.String direccion,
           java.lang.String fechaNacimiento,
           org.pap.publicadores.EnumEstadoBeneficiario estado,
           org.pap.publicadores.EnumBarrio barrio) {
        super(
            nombre,
            password,
            email);
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.estado = estado;
        this.barrio = barrio;
    }


    /**
     * Gets the direccion value for this DtBeneficiario.
     * 
     * @return direccion
     */
    public java.lang.String getDireccion() {
        return direccion;
    }


    /**
     * Sets the direccion value for this DtBeneficiario.
     * 
     * @param direccion
     */
    public void setDireccion(java.lang.String direccion) {
        this.direccion = direccion;
    }


    /**
     * Gets the fechaNacimiento value for this DtBeneficiario.
     * 
     * @return fechaNacimiento
     */
    public java.lang.String getFechaNacimiento() {
        return fechaNacimiento;
    }


    /**
     * Sets the fechaNacimiento value for this DtBeneficiario.
     * 
     * @param fechaNacimiento
     */
    public void setFechaNacimiento(java.lang.String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }


    /**
     * Gets the estado value for this DtBeneficiario.
     * 
     * @return estado
     */
    public org.pap.publicadores.EnumEstadoBeneficiario getEstado() {
        return estado;
    }


    /**
     * Sets the estado value for this DtBeneficiario.
     * 
     * @param estado
     */
    public void setEstado(org.pap.publicadores.EnumEstadoBeneficiario estado) {
        this.estado = estado;
    }


    /**
     * Gets the barrio value for this DtBeneficiario.
     * 
     * @return barrio
     */
    public org.pap.publicadores.EnumBarrio getBarrio() {
        return barrio;
    }


    /**
     * Sets the barrio value for this DtBeneficiario.
     * 
     * @param barrio
     */
    public void setBarrio(org.pap.publicadores.EnumBarrio barrio) {
        this.barrio = barrio;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DtBeneficiario)) return false;
        DtBeneficiario other = (DtBeneficiario) obj;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.direccion==null && other.getDireccion()==null) || 
             (this.direccion!=null &&
              this.direccion.equals(other.getDireccion()))) &&
            ((this.fechaNacimiento==null && other.getFechaNacimiento()==null) || 
             (this.fechaNacimiento!=null &&
              this.fechaNacimiento.equals(other.getFechaNacimiento()))) &&
            ((this.estado==null && other.getEstado()==null) || 
             (this.estado!=null &&
              this.estado.equals(other.getEstado()))) &&
            ((this.barrio==null && other.getBarrio()==null) || 
             (this.barrio!=null &&
              this.barrio.equals(other.getBarrio())));
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
        if (getDireccion() != null) {
            _hashCode += getDireccion().hashCode();
        }
        if (getFechaNacimiento() != null) {
            _hashCode += getFechaNacimiento().hashCode();
        }
        if (getEstado() != null) {
            _hashCode += getEstado().hashCode();
        }
        if (getBarrio() != null) {
            _hashCode += getBarrio().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DtBeneficiario.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://publicadores.pap.org/", "dtBeneficiario"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("direccion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "direccion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaNacimiento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechaNacimiento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "estado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://publicadores.pap.org/", "enumEstadoBeneficiario"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("barrio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "barrio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://publicadores.pap.org/", "enumBarrio"));
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
