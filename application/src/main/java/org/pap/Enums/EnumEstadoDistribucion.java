package org.pap.Enums;

import jakarta.xml.bind.annotation.XmlEnumValue;

public enum EnumEstadoDistribucion {
		@XmlEnumValue("PENDIENTE")
	    PENDIENTE,
		@XmlEnumValue("ENCAMINO")
		ENCAMINO,
		@XmlEnumValue("ENTREGADO")
		ENTREGADO
	}