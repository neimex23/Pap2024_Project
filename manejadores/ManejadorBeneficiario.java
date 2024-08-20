package manejadores;
import java.util.ArrayList;
import java.util.List;

import classes.Distribucion;

public class ManejadorBeneficiario{
	private static ManejadorBeneficiario instancia = null;
	private List<Beneficiario> beneficiarios = new ArrayList<Distribucion>();
	
	private ManejadorBeneficiario() {}
	
	static ManejadorBeneficiario getInstancia() {
		if (instancia == null) 
		{
			instancia = new ManejadorBeneficiario();
		}
		return instancia;
	}
	
	public void añadirBeneficiario(Beneficiario ben) {
		this.beneficiarios.add(ben);
	}
	
	
	
}
