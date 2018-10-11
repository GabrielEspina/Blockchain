package ar.edu.ub.si.blockchain.interfaces;

import java.io.File;
import java.util.ArrayList;

import ar.edu.ub.si.blockchain.data.Bloque;
import ar.edu.ub.si.blockchain.data.Dato;

public interface IAdministradorBlockchain {
	
	public void crearDato(File arhivo);
	
	public void generarBlockchain(); 
	
	public ArrayList<Dato> getDatos();
	
	public  ArrayList<Bloque> getBlockchain();
	
	public void almacenarBlockchain();
	
	public void mostrarBlockChain();
	
}
