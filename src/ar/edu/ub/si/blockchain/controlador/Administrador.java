package ar.edu.ub.si.blockchain.controlador;

import ar.edu.ub.si.blockchain.config.ConectorBaseDeDatos;
import ar.edu.ub.si.blockchain.interfaces.IAdministrador;
import ar.edu.ub.si.blockchain.interfaces.IConfiguracion;
import ar.edu.ub.si.blockchain.util.Configuracion;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class Administrador implements IAdministrador{
	
	IConfiguracion configuration;
	
	Connection connection;
	
	
	public Administrador(String configuration) {
		
		this.configuration = new Configuracion(configuration);
		openConnection();
		
	}

	
	private IConfiguracion configuration() {
		return this.configuration;
	}
	
	protected Connection connection() {
		
		return this.connection;
	}
	
	private void connection(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public void openConnection() {
		try {
			connection( ConectorBaseDeDatos.obtenerConexion( configuration() ));
		} catch (Exception e) {
			System.out.println("No se pudo conectar");
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void closeConnection() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}
