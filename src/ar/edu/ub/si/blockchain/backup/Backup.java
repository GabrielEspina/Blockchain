package ar.edu.ub.si.blockchain.backup;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import ar.edu.ub.si.blockchain.data.Bloque;

public class Backup implements Serializable {

	private static final long serialVersionUID = 6020110967780955319L;


	public static void guardar( ArrayList<Bloque> bloques  ) throws IOException {
		
		String ruta ="backup.txt";
        File archivo = new File(ruta);
		
		if (archivo.exists()) archivo.delete();
		
        BufferedWriter bw;
        bw = new BufferedWriter(new FileWriter(archivo));
        for(Bloque bloque : bloques)
        	bw.write(bloque.toString() +"\n");
        bw.close();	
        
        System.out.println("Blockchain actualizada");
        
	}
	
	
	public static ArrayList<Bloque> cargar(  ) throws IOException, ClassNotFoundException {
		
		String fichero = "backup.txt";
		
		String cadena;
		FileReader f = new FileReader(fichero);
		
		BufferedReader b = new BufferedReader(f);
		
		ArrayList<Bloque> bloques = new ArrayList<Bloque>();
		
		while((cadena = b.readLine())!=null) {
			String[] arraycadena = cadena.split(",");
			
			Bloque bloque = new Bloque();
			
			bloque.setHash(arraycadena[0]);
			bloque.setHashDato(arraycadena[1]);
			bloque.setPreviousHash(arraycadena[2]);
			bloque.setTimeStampStr(arraycadena[3]);
			
			bloques.add(bloque);
			
		}
		
		b.close();
		
		return bloques;
		
}

}
