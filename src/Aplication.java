import ar.edu.ub.si.blockchain.interfaces.IAdministradorBlockchain;
import ar.edu.ub.si.blockchain.vista.AdminScreen;



public class Aplication {
	

	public static void main(String[] args) {
		
		IAdministradorBlockchain admin = new AdministradorBlockchain();
		
		
		AdminScreen.generateScreen( admin);

	}
	
	

}
