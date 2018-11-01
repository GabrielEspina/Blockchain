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
		
	}
	
	public void connection(Configuracion configuration) {
		
	}
	
	public IConfiguracion configuration() {
		return this.configuration;
	}
	
	public Connection connection() {
		
		return this.connection;
	}
	
	@Override
	public void openConnection() {
		try {
			this.connection = ConectorBaseDeDatos.obtenerConexion( configuration() );
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
