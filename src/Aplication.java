import ar.edu.ub.si.blockchain.interfaces.IAdministradorBlockchain;
import ar.edu.ub.si.blockchain.vista.AdminScreen;



public class Aplication {
	
	
	
	public static void main(String[] args) {
		
		IAdministradorBlockchain admin = new AdministradorBlockchain();
		
		AdminScreen.generateScreen( admin );
		
		
		/*	
			//TENEMOS CUATRO TIPOS DE DATOS QUE QUEREMOS METER A LA BLOCKCHAIN
			
			ArrayList<Dato> datos = new ArrayList<Dato>();

			File fPDF = new File("C:\\Ejemplo1.pdf");
			File fPDF2 = new File("C:\\Ejemplo2.pdf");
			File fPDF3 = new File("C:\\Ejemplo3.pdf");
			File fPDF4 = new File("C:\\Ejemplo3.pdf");
			
			datos.add(new Dato(fPDF));
			datos.add(new Dato(fPDF2));
			datos.add(new Dato(fPDF3));
			datos.add(new Dato(fPDF4));
			
			generarBlockChain(datos);
			
			//ESTE COMENTARIO ES PARA PROBAR UN COMMIT NADA MAS
			
			mostrarBlockChain();
			
			
			
			System.out.println(fPDF.equals(fPDF2));
			
			almacenarBlockchain();
*/
	}
	
	

}
