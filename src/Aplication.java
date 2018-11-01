import app.AdministradorBlockchain;
import app.IAdministrador;
import ar.edu.ub.si.blockchain.interfaces.IAdministradorBlockchain;
import ar.edu.ub.si.blockchain.vista.AdminScreen;




public class Aplication {
	

	public static void main(String[] args) {
		
		IAdministrador admin = new AdministradorBlockchain("blockchain.properties");
		
		
		AdminScreen.generateScreen( admin );
		
	}
	
	

}
