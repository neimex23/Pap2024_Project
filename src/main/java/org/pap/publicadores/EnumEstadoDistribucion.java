/**
 * EnumEstadoDistribucion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4.1-SNAPSHOT Nov 07, 2023 (07:57:58 UTC) WSDL2Java emitter.
 */

package org.pap.publicadores;

public class EnumEstadoDistribucion implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected EnumEstadoDistribucion(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _PENDIENTE = "PENDIENTE";
    public static final java.lang.String _ENCAMINO = "ENCAMINO";
    public static final java.lang.String _ENTREGADO = "ENTREGADO";
    public static final EnumEstadoDistribucion PENDIENTE = new EnumEstadoDistribucion(_PENDIENTE);
    public static final EnumEstadoDistribucion ENCAMINO = new EnumEstadoDistribucion(_ENCAMINO);
    public static final EnumEstadoDistribucion ENTREGADO = new EnumEstadoDistribucion(_ENTREGADO);
    public java.lang.String getValue() { return _value_;}
    public static EnumEstadoDistribucion fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        EnumEstadoDistribucion enumeration = (EnumEstadoDistribucion)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static EnumEstadoDistribucion fromString(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        return fromValue(value);
    }
    public boolean equals(java.lang.Object obj) {return (obj == this);}
    public int hashCode() { return toString().hashCode();}
    public java.lang.String toString() { return _value_;}
    public java.lang.Object readResolve() throws java.io.ObjectStreamException { return fromValue(_value_);}
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumSerializer(
            _javaType, _xmlType);
    }
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumDeserializer(
            _javaType, _xmlType);
    }
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EnumEstadoDistribucion.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://publicadores.pap.org/", "enumEstadoDistribucion"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
