import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import ar.edu.ub.si.blockchain.data.Bloque;
import ar.edu.ub.si.blockchain.data.Dato;



public class Aplication {
	
	//CREAMOS LA BLOCKCHAIN	
	public static ArrayList<Bloque> blockchain = new ArrayList<Bloque>();
	
	public static void main(String[] args) {
			
			//TENEMOS CUATRO TIPOS DE DATOS QUE QUEREMOS METER A LA BLOCKCHAIN
			
			ArrayList<Dato> datos = new ArrayList<Dato>();
			
			datos.add(new Dato( new String("Soy un dato")));
			datos.add(new Dato( new Integer(24)));
			datos.add(new Dato( new Double (3.4)));

			File fPDF = new File("C:\\EjemploEF1.pdf");
			File fPDF2 = new File("C:\\EjemploEF1.pdf");
			
			datos.add(new Dato(fPDF));
			datos.add(new Dato(fPDF2)); //DATO INVALIDO , NO ENTRA EN LA BLOCKCHAIN
			
			generarBlockChain(datos);
			
			mostrarBlockChain();
			
			
			
			System.out.println(fPDF.equals(fPDF2));
			
			almacenarBlockchain();

	}
	
	private static void almacenarBlockchain() {
		
		 String ruta = "registro.txt";
	        File archivo = new File(ruta);
	        BufferedWriter bw = null;
	        if(archivo.exists()) {
	            try {
					bw = new BufferedWriter(new FileWriter(archivo));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	            try {
					bw.write("El fichero de texto ya estaba creado.");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        } else {
	            try {
					bw = new BufferedWriter(new FileWriter(archivo));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            try {
					bw.write("Acabo de crear el fichero de texto.");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        try {
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	private static void mostrarBlockChain() {
		
		for(Bloque bloque: blockchain)
			System.out.println("Hash Bloque:\t" + bloque.getHash() + "\n" + 
							   "Hash Bloque Anterior:\t" + bloque.getPreviousHash()+ "\n" + 
							   "Hash Dato:\t" + bloque.getHashDato()+ "\n");
		
	}

	private static void generarBlockChain(ArrayList<Dato> datos) {
		
		for(Dato dato: datos)
			validarDato(dato);
		
	}

	public static void validarDato(Dato dato) {
		
		if( hashValido(dato) ) {
			System.out.println("Dato valido");
			if(blockchain.isEmpty()) {
				blockchain.add( new Bloque("0", dato.getHash()) );

			}else {
				blockchain.add( new Bloque(blockchain.get(blockchain.size()-1).getHash(), dato.getHash()) );
			}
		}else {
			System.out.println("Dato invalido");
			
		}
	}
	
	
	public static boolean hashValido( Dato dato ) {
		for(Bloque bloque: blockchain) 
			if(bloque.getHashDato().equals(dato.getHash()))
				return false;
		return true;
	}

}
