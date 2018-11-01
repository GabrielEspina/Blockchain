package ar.edu.ub.si.blockchain.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import ar.edu.ub.si.blockchain.interfaces.IConfiguracion;

public class Configuracion implements IConfiguracion{
	
	
	private Properties properties;
	private FileInputStream fis;
	public Configuracion(String fileName) {
		
		this.setProperties(new Properties());
		
		try {
			
			this.setFis(new FileInputStream(fileName));
			
			getProperties().load(this.getFis());
					
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String getConfiguracion(String propiedad) {
		
		return this.getProperties().getProperty(propiedad);
		
	}
	
	@Override
	public int getConfiguracionAsInt(String propiedad) {		
		return Integer.parseInt( this.getProperties().getProperty(propiedad) );		
	}
	
	@Override
	public void close() {
		
		try {
			this.getFis().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	private Properties getProperties() {
		return properties;
	}

	private void setProperties(Properties properties) {
		this.properties = properties;
	}

	public FileInputStream getFis() {
		return fis;
	}

	public void setFis(FileInputStream fis) {
		this.fis = fis;
	}
	
	
}
