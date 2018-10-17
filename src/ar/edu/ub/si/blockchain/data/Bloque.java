package ar.edu.ub.si.blockchain.data;


import java.util.Date;

public class Bloque{
	
	//El contenido de un bloque debe tener 
	//le hash del bloque anterior y la transaccion a hashear
	
	private String 	hash;
	private String 	hashDato;
	private String 	previousHash;
	private Date 	timeStamp;
	private Encriptador encriptador;
	
	
	public Bloque( String previousHash , String hashDato) {
		
		setPreviousHash(previousHash);
		setHashDato(hashDato);
		setTimeStamp(generarTimeStamp());
		generarHash();
		
	}


	public Bloque() {
		this.setHash(null);
		this.setHashDato(null);
		this.setPreviousHash(null);
		this.setTimeStamp(null);
		this.encriptador = new Encriptador();
	}




	public Date generarTimeStamp() {
		return new Date();
	}
	
	//--------------------------------------------------------------//
	public String getPreviousHash() {
		return previousHash;
	}
	public void setPreviousHash(String previousHash) {
		this.previousHash = previousHash;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
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
	
	public void generarHash() {
		setHash(encriptador.encriptarBloque(this.getTimeStamp(), this.getHashDato()));
	}


	public String getHash() {
		return hash;
	}



	
}
