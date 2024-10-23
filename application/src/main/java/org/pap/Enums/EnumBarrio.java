package org.pap.Enums;
import jakarta.xml.bind.annotation.XmlEnumValue;


public enum EnumBarrio {
		@XmlEnumValue("CIUDAD_VIEJA")
	    CIUDAD_VIEJA,
		@XmlEnumValue("CORDON")
		CORDON,
		@XmlEnumValue("PARQUE_RODO")
		PARQUE_RODO,
		@XmlEnumValue("CENTRO")
		CENTRO,
		@XmlEnumValue("PALERMO")
		PALERMO
	}