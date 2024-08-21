package handlers;
import classes.Beneficiario;
import java.util.ArrayList;
import java.util.List;

import classes.Distribucion;

public class ManejadorBeneficiario{
	private static ManejadorBeneficiario instancia = null;
	private List<Beneficiario> beneficiarios = new ArrayList<Beneficiario>();
	
	private ManejadorBeneficiario() {}
	
	public static ManejadorBeneficiario getInstancia() {
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
