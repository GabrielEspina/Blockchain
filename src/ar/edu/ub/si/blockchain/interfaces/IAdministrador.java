package ar.edu.ub.si.blockchain.interfaces;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

import ar.edu.ub.si.blockchain.data.Bloque;

public interface IAdministrador {
	
	public void openConnection();
	public void closeConnection();
	
	public void crearDato(File arhivo);
	
	public void generarBlockchain(); 
	
	public  ArrayList<Bloque> getBlockchain() throws SQLException, Exception;
	
	public void almacenarBloque(Bloque bloque) throws SQLException, Exception;

	public void eliminarTodosLosRegistros() throws SQLException, Exception;

	public String validarArchivo(File archivo) throws SQLException,Exception;

	public ArrayList<Bloque> getBloques ()  throws Exception;
	
	public void reiniciarBlockchain() throws Exception;
}
