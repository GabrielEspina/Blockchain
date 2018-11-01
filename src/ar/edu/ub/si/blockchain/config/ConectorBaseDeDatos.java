package ar.edu.ub.si.blockchain.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import ar.edu.ub.si.blockchain.interfaces.IConfiguracion;


public abstract class ConectorBaseDeDatos {
	
	
	public ConectorBaseDeDatos() {
		
	}

	@SuppressWarnings("deprecation")
	public static Connection obtenerConexion(IConfiguracion configuracion)  {
		
		
		// Establecer el nombre del driver a utilizar
		
		String dbDriver = configuracion.getConfiguracion("dbDriver");
		
		// Establece la conexion a utilizar
		
		String dbConnString = configuracion.getConfiguracion("instancia");
		
		// Estable el usuario de la base de datos
		
		String dbUser = configuracion.getConfiguracion("usuario");
		
		// Establece la contrasea de la base de datos
		
		String dbPassword = configuracion.getConfiguracion("password");
		
		// Establece el driver de conexion
		
		try {
			Class.forName(dbDriver).newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Retorna la conexion
		
		try {
			return DriverManager.getConnection(
								dbConnString, dbUser, dbPassword
					);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
				
	}
}