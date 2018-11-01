package pruebapruebita;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConvertidorDateString {

	public static void main(String[] args) {

		//CREO EL OBJETO DATE
		Date date1 = new Date();
		//MUESTRO EL DATE
		System.out.println("date = " + date1);
		
		//CREO EL FORMATEADOR EN EL CUAL INDICO LA ESTRUCTURA DEL STRING
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		//TRANSFORMO EL DATE EN UN STRING CON EL FORMATO INDICADO 
		String dateString = df.format(date1);
		System.out.println("date to string = " + dateString);
		
		try {
			//ARMO UN OBJETO DATE CON EL STRING
			Date date2 = df.parse(dateString);
			System.out.println("string to date = " + date2);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
}
