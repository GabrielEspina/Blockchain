import ar.edu.ub.si.blockchain.controlador.AdministradorBlockchain;
import ar.edu.ub.si.blockchain.interfaces.IAdministrador;
import ar.edu.ub.si.blockchain.vista.AdministradorDeVentana;

public class Aplication {
	

	public static void main(String[] args) {

		IAdministrador admin = new AdministradorBlockchain(args[0]);
		
		
		AdministradorDeVentana.generarVentana( admin );
	
	}
		
}
