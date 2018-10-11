package ar.edu.ub.si.blockchain.data;
import ar.edu.ub.si.blockchain.interfaces.IOperacionesHash;

public class Dato implements IOperacionesHash{
	
	private Object dato;
	private String hash;
	
	public Dato(Object dato) {
		setDato(dato);
		generarHash();
	}


	public Object getDato() {
		return dato;
	}

	public void setDato(Object dato) {
		this.dato = dato;
	}


	@Override
	public void generarHash() {
		
		String hashcode = Integer.toString(getDato().hashCode());
		setHash(hashcode);
		
	}
		
	@Override
	public String getHash() {
		return hash;
	}


	private void setHash(String hash) {
		this.hash = hash;
	}

}
