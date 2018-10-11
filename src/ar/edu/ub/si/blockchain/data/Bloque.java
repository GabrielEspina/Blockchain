package ar.edu.ub.si.blockchain.data;


import java.util.Date;
import java.util.Objects;

import ar.edu.ub.si.blockchain.interfaces.IOperacionesHash;

public class Bloque implements IOperacionesHash{
	
	//El contenido de un bloque debe tener 
	//le hash del bloque anterior y la transaccion a hashear
	
	private String 	hash;
	private String 	hashDato;
	private String 	previousHash;
	private Long 	timeStamp;
	
	
	public Bloque( String previousHash , String hashDato) {
		
		setPreviousHash(previousHash);
		setHashDato(hashDato);
		setTimeStamp(generarTimeStamp());
		generarHash();
		
	}



	
	public Long generarTimeStamp() {
		return new Date().getTime();
	}
	
	//--------------------------------------------------------------//
	public String getPreviousHash() {
		return previousHash;
	}
	public void setPreviousHash(String previousHash) {
		this.previousHash = previousHash;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}



	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getHashDato() {
		return hashDato;
	}

	public void setHashDato(String hashDato) {
		this.hashDato = hashDato;
	}

	@Override
	public void generarHash() {
		String hashcode = Integer.toString(Objects.hash(
				this.hashCode(),
				getTimeStamp(),
				getPreviousHash()));
		
		setHash(hashcode);
	}


	@Override
	public String getHash() {
		return hash;
	}



	
}
