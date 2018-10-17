package ar.edu.ub.si.blockchain.data;
import java.io.File;

public class Dato{
	
	private File dato;
	private String hash;
	private Encriptador encriptador;
	
	public Dato(File dato) {
		setDato(dato);
		generarHash();
		encriptador = new Encriptador();
	}

	public File getDato() {
		return dato;
	}

	public void setDato(File dato) {
		this.dato = dato;
	}



	public void generarHash() {
		setHash(encriptador.encriptarArchivo(this.getDato()));
	}
		

	public String getHash() {
		return hash;
	}


	private void setHash(String hash) {
		this.hash = hash;
	}

}
