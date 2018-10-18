package ar.edu.ub.si.blockchain.interfaces;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

import ar.edu.ub.si.blockchain.data.Bloque;
import ar.edu.ub.si.blockchain.data.Dato;

public interface IAdministradorBlockchain {
	
	public void crearDato(File arhivo);
	
	public void generarBlockchain(); 
	
	public ArrayList<Dato> getDatos();
	
	public  ArrayList<Bloque> getBlockchain() throws SQLException, Exception;
	
	public void almacenarBloque(Bloque bloque) throws SQLException, Exception;
	
	public void mostrarBlockChain() throws SQLException,Exception;

	public void eliminarTodosLosRegistros() throws SQLException, Exception;

	public String validarArchivo(File archivo) throws SQLException,Exception;

	
}
