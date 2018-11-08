import java.io.IOException;

import ar.edu.ub.si.blockchain.controlador.AdministradorBlockchain;
import ar.edu.ub.si.blockchain.interfaces.IAdministrador;
import ar.edu.ub.si.blockchain.vista.AdministradorDeVentana;

public class Aplication {
	

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		
		//Se crea un administrador de blockchain para realizar las operaciones correspondientes
		IAdministrador admin = new AdministradorBlockchain(args[0]);
		
		//Se crea un administrador de ventana para visualizar las operaciones del administrador de blockchain
		AdministradorDeVentana.generarVentana( admin );
	
	}
		
}