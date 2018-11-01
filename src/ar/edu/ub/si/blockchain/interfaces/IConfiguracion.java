package ar.edu.ub.si.blockchain.interfaces;

public interface IConfiguracion {
	
	public String getConfiguracion(String propiedad);
	
	public int getConfiguracionAsInt(String propiedad);
	
	public void close();

}
