package ar.edu.ub.si.blockchain.data;


import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.sun.org.apache.bcel.internal.generic.GETSTATIC;

import ar.edu.ub.si.blockchain.interfaces.IOperacionesHash;

public class Bloque implements IOperacionesHash{
	
	//El contenido de un bloque debe tener 
	//le hash del bloque anterior y la transaccion a hashear
	
	private String 	hash;
	private String 	hashDato;
	private String 	previousHash;
	private Date 	timeStamp;
	private String  tsbd;
	
	
	public Bloque( String previousHash , String hashDato) {
		
		setPreviousHash(previousHash);
		setHashDato(hashDato);
		setTimeStamp(generarTimeStamp());
		generarHash();
		
	}
	
	public String toString() {
		
		return getHash() +","+ getHashDato() +","+ getPreviousHash() +","+ getTsbd()+"," ;
		
	}


	public Bloque() {
		this.setHash(null);
		this.setHashDato(null);
		this.setPreviousHash(null);
		this.setTsbd(null);
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

		MessageDigest m;
		try {
			m = MessageDigest.getInstance("MD5");
			m.update(longToBytes(getTimeStamp().getTime()));
			m.update(getHashDato().getBytes());
			m.update(getPreviousHash().getBytes());
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
	
	public String getTimeStampStr() {
		
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		//TRANSFORMO EL DATE EN UN STRING CON EL FORMATO INDICADO 
		String dateString = df.format(this.getTimeStamp());
		return dateString;
	}
	
	public void setTimeStampStr(String timeStamp) {
		try {
			//ARMO UN OBJETO DATE CON EL STRING
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date2 = df.parse(timeStamp);
			this.setTimeStamp(date2);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	


	@Override
	public String getHash() {
		return hash;
	}


	public String getTsbd() {
		return tsbd;
	}


	public void setTsbd(String tsbd) {
		this.tsbd = tsbd;
	}



	
}
