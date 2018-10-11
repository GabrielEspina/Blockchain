package ar.edu.ub.si.blockchain.data;


import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import ar.edu.ub.si.blockchain.interfaces.IOperacionesHash;

public class Bloque implements IOperacionesHash{
	
	//El contenido de un bloque debe tener 
	//le hash del bloque anterior y la transaccion a hashear
	
	private String 	hash;
	private String 	hashDato;
	private String 	previousHash;
	private Date 	timeStamp;
	
	
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
	
	public byte[] longToBytes(long x) { //Transforma el timestamp en un array de bytes para ser hasheado
	    ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
	    buffer.putLong(x);
	    return buffer.array();
	}

	@Override
	public void generarHash() {
		/*
		String hashcode = Integer.toString(Objects.hash(
				this.hashCode(),
				getTimeStamp(),
				getPreviousHash()));
		
		setHash(hashcode);*/
		
		MessageDigest m;
		try {
			m = MessageDigest.getInstance("MD5");
			m.update(longToBytes(getTimeStamp().getTime()));
			m.update(getHashDato().getBytes());
			byte[] digest = m.digest();
			StringBuffer sb = new StringBuffer();
			for (byte b : digest) {
				sb.append(String.format("%02x", b & 0xff));
			}
			setHash(sb.toString());
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		
		
	}


	@Override
	public String getHash() {
		return hash;
	}



	
}
