package ar.edu.ub.si.blockchain.data;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class Encriptador {
	
	private MessageDigest m;

	public Encriptador() {
		try {
			this.m = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public String encriptarArchivo(File archivo) {
		
		StringBuffer sb = new StringBuffer();
		try {
			m.update(Files.readAllBytes(archivo.toPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] digest = m.digest();
		for (byte b : digest) {
			sb.append(String.format("%02x", b & 0xff));
		}
		m.reset();
		return sb.toString();
		
	}
	
	public String encriptarBloque(Date timestamp, String hashDato) {
		m.update(DateToBytes(timestamp));
		m.update(hashDato.getBytes());
		byte[] digest = m.digest();
		StringBuffer sb = new StringBuffer();
		for (byte b : digest) {
			sb.append(String.format("%02x", b & 0xff));
		}
		m.reset();
		return sb.toString();
	}
	
	private byte[] DateToBytes(Date timestamp) {
	    ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
	    buffer.putLong(timestamp.getTime());
	    return buffer.array();
	}

}
