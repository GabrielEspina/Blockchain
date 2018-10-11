package ar.edu.ub.si.blockchain.connectordb;

import java.sql.Connection;
import java.sql.DriverManager;

import ar.edu.ub.si.blockchain.util.Configuracion;


public abstract class AdministradorDeConexiones {
	
	
	public AdministradorDeConexiones() {
		
	}

	public static Connection obtenerConexion(Configuracion configuracion) throws Exception {
		
		
		// Establecer el nombre del driver a utilizar
		
		String dbDriver = configuracion.getConfiguracion("dbDriver");
		
		// Establece la conexion a utilizar
		
		String dbConnString = configuracion.getConfiguracion("instancia");
		
		// Estable el usuario de la base de datos
		
		String dbUser = configuracion.getConfiguracion("usuario");
		
		// Establece la contrasea de la base de datos
		
		String dbPassword = configuracion.getConfiguracion("password");
		
		// Establece el driver de conexion
		
		Class.forName(dbDriver).newInstance();
		
		// Retorna la conexion
		
		return DriverManager.getConnection(
							dbConnString, dbUser, dbPassword
				);
				
	}
}