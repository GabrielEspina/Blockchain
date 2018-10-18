package ar.edu.ub.si.blockchain.almacen;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import ar.edu.ub.si.blockchain.data.Bloque;
import ar.edu.ub.si.blockchain.interfaces.IAlmacenBlockchain;
import ar.edu.ub.si.blockchain.util.Configuracion;
import ar.edu.ub.si.blockchain.connectordb.AdministradorDeConexiones;

public class AlmacenBDD implements IAlmacenBlockchain{

	
	@Override
	public ArrayList<Bloque> getBlockChain(Configuracion configuracion) throws Exception {
		// Defino la conexion
		Connection laConexion = AdministradorDeConexiones.obtenerConexion(configuracion);
		
		String laConsulta = "SELECT * FROM [Blockchain].[dbo].[Hash]";
		Statement stmtConsulta = laConexion.createStatement();
		ResultSet rs = stmtConsulta.executeQuery(laConsulta);
		
		//INFORMO QUE SE ESTA POR HACER LA CONSULTA
		System.out.println(">>SQL: " + laConsulta);
		
		// Armo el array de bloques
		
		ArrayList<Bloque> bloques = new ArrayList<Bloque>();
		
		// muestro los datos
		
		while (rs.next()) {
			// armo el bloque con los datos obtenidos
			Bloque bl = new Bloque();
			String hash = rs.getString("Hash");

			bl.setHash(rs.getString("Hash"));
			
			bl.setHashDato(rs.getString("HashDato"));
			bl.setPreviousHash(rs.getString("PreviusHash"));
			bl.setTimeStamp(rs.getDate("TimeStamp"));
			
			// agrego el bloque al array
			bloques.add(bl);
			
		}
		
		// cierro el statement
		stmtConsulta.close();
		
		return bloques;
	}

	@Override
	public void saveBlockChain(Bloque blockChain, Configuracion configuracion) throws Exception {
		
		// Defino la conexion
		Connection laConexion = AdministradorDeConexiones.obtenerConexion(configuracion);
		
		
		// EN algun momento lo paso como sp dentro de la base de datos para llamar a SP_NUEVO_BLOQUE
		//ARMAR UN INSERT
		String laInsercion = "INSERT INTO [Blockchain].[dbo].[Hash] "
							+"(Hash, HashDato, PreviusHash, TimeStamp)" 
							+ "VALUES (?,?,?,?)";
		
		//Informao la inser
		System.out.println(">>SQL: " + laInsercion);
		
		// preparo la insercion
		PreparedStatement ps = laConexion.prepareStatement(laInsercion, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, blockChain.getHash());
		ps.setString(2, blockChain.getHashDato());
		ps.setString(3, blockChain.getPreviousHash());
		ps.setDate(4, (Date) blockChain.getTimeStamp());
		
		ps.execute();
		
		ps.close();
		
		//Statement stmtInsercion = laConexion.createStatement();
		//stmtInsercion.execute(laInsercion);
		
		
		// se cierra el statement y la connection
		//stmtConsulta.close();
		//stmtInsercion.close();
		laConexion.close();
		
	}


}
