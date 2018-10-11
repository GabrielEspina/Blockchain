package ar.edu.ub.si.blockchain.data;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import ar.edu.ub.si.blockchain.interfaces.IOperacionesHash;

public class Dato implements IOperacionesHash{
	
	private File dato;
	private String hash;
	
	public Dato(File dato) {
		setDato(dato);
		generarHash();
	}

	public File getDato() {
		return dato;
	}

	public void setDato(File dato) {
		this.dato = dato;
	}


	@Override
	public void generarHash() {
		/*
		String hashcode = Integer.toString(getDato().hashCode());
		setHash(hashcode);
		*/
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.update(Files.readAllBytes(getDato().toPath()));
			byte[] digest = m.digest();
			StringBuffer sb = new StringBuffer();
			for (byte b : digest) {
				sb.append(String.format("%02x", b & 0xff));
			}
			setHash(sb.toString());
		} catch (NoSuchAlgorithmException | IOException e) {
			e.printStackTrace();
		}
		    	
		
	}
		
	@Override
	public String getHash() {
		return hash;
	}


	private void setHash(String hash) {
		this.hash = hash;
	}

}
