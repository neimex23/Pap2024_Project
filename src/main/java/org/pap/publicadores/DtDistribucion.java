/**
 * DtDistribucion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4.1-SNAPSHOT Nov 07, 2023 (07:57:58 UTC) WSDL2Java emitter.
 */

package org.pap.publicadores;

public class DtDistribucion  implements java.io.Serializable {
    private int id;

    private java.lang.String fechaPreparacion;

    private java.lang.String fechaEntrega;

    private org.pap.publicadores.EnumEstadoDistribucion estado;

    private int donacionAsc;

    private java.lang.String emailBenefAsc;

    public DtDistribucion() {
    }

    public DtDistribucion(
           int id,
           java.lang.String fechaPreparacion,
           java.lang.String fechaEntrega,
           org.pap.publicadores.EnumEstadoDistribucion estado,
           int donacionAsc,
           java.lang.String emailBenefAsc) {
           this.id = id;
           this.fechaPreparacion = fechaPreparacion;
           this.fechaEntrega = fechaEntrega;
           this.estado = estado;
           this.donacionAsc = donacionAsc;
           this.emailBenefAsc = emailBenefAsc;
    }


    /**
     * Gets the id value for this DtDistribucion.
     * 
     * @return id
     */
    public int getId() {
        return id;
    }


    /**
     * Sets the id value for this DtDistribucion.
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Gets the fechaPreparacion value for this DtDistribucion.
     * 
     * @return fechaPreparacion
     */
    public java.lang.String getFechaPreparacion() {
        return fechaPreparacion;
    }


    /**
     * Sets the fechaPreparacion value for this DtDistribucion.
     * 
     * @param fechaPreparacion
     */
    public void setFechaPreparacion(java.lang.String fechaPreparacion) {
        this.fechaPreparacion = fechaPreparacion;
    }


    /**
     * Gets the fechaEntrega value for this DtDistribucion.
     * 
     * @return fechaEntrega
     */
    public java.lang.String getFechaEntrega() {
        return fechaEntrega;
    }


    /**
     * Sets the fechaEntrega value for this DtDistribucion.
     * 
     * @param fechaEntrega
     */
    public void setFechaEntrega(java.lang.String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }


    /**
     * Gets the estado value for this DtDistribucion.
     * 
     * @return estado
     */
    public org.pap.publicadores.EnumEstadoDistribucion getEstado() {
        return estado;
    }


    /**
     * Sets the estado value for this DtDistribucion.
     * 
     * @param estado
     */
    public void setEstado(org.pap.publicadores.EnumEstadoDistribucion estado) {
        this.estado = estado;
    }


    /**
     * Gets the donacionAsc value for this DtDistribucion.
     * 
     * @return donacionAsc
     */
    public int getDonacionAsc() {
        return donacionAsc;
    }


    /**
     * Sets the donacionAsc value for this DtDistribucion.
     * 
     * @param donacionAsc
     */
    public void setDonacionAsc(int donacionAsc) {
        this.donacionAsc = donacionAsc;
    }


    /**
     * Gets the emailBenefAsc value for this DtDistribucion.
     * 
     * @return emailBenefAsc
     */
    public java.lang.String getEmailBenefAsc() {
        return emailBenefAsc;
    }


    /**
     * Sets the emailBenefAsc value for this DtDistribucion.
     * 
     * @param emailBenefAsc
     */
    public void setEmailBenefAsc(java.lang.String emailBenefAsc) {
        this.emailBenefAsc = emailBenefAsc;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DtDistribucion)) return false;
        DtDistribucion other = (DtDistribucion) obj;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.id == other.getId() &&
            ((this.fechaPreparacion==null && other.getFechaPreparacion()==null) || 
             (this.fechaPreparacion!=null &&
              this.fechaPreparacion.equals(other.getFechaPreparacion()))) &&
            ((this.fechaEntrega==null && other.getFechaEntrega()==null) || 
             (this.fechaEntrega!=null &&
              this.fechaEntrega.equals(other.getFechaEntrega()))) &&
            ((this.estado==null && other.getEstado()==null) || 
             (this.estado!=null &&
              this.estado.equals(other.getEstado()))) &&
            this.donacionAsc == other.getDonacionAsc() &&
            ((this.emailBenefAsc==null && other.getEmailBenefAsc()==null) || 
             (this.emailBenefAsc!=null &&
              this.emailBenefAsc.equals(other.getEmailBenefAsc())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        _hashCode += getId();
        if (getFechaPreparacion() != null) {
            _hashCode += getFechaPreparacion().hashCode();
        }
        if (getFechaEntrega() != null) {
            _hashCode += getFechaEntrega().hashCode();
        }
        if (getEstado() != null) {
            _hashCode += getEstado().hashCode();
        }
        _hashCode += getDonacionAsc();
        if (getEmailBenefAsc() != null) {
            _hashCode += getEmailBenefAsc().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DtDistribucion.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://publicadores.pap.org/", "dtDistribucion"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaPreparacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechaPreparacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaEntrega");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechaEntrega"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "estado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://publicadores.pap.org/", "enumEstadoDistribucion"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("donacionAsc");
        elemField.setXmlName(new javax.xml.namespace.QName("", "donacionAsc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("emailBenefAsc");
        elemField.setXmlName(new javax.xml.namespace.QName("", "emailBenefAsc"));
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
