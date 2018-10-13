import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ar.edu.ub.si.blockchain.almacen.AlmacenBDD;
import ar.edu.ub.si.blockchain.connectordb.AdministradorDeConexiones;
import ar.edu.ub.si.blockchain.data.Bloque;
import ar.edu.ub.si.blockchain.data.Dato;
import ar.edu.ub.si.blockchain.interfaces.IAdministradorBlockchain;
import ar.edu.ub.si.blockchain.interfaces.IAlmacenBlockchain;
import ar.edu.ub.si.blockchain.util.Configuracion;

	

public class AdministradorBlockchain implements IAdministradorBlockchain{
	
	//CREAMOS LA BLOCKCHAIN	
	private  ArrayList<Bloque> blockchain;
	private  ArrayList<Dato> datos;
	private  ArrayList<Bloque> blockchainLocal;
	
	//ESTE OBJETO ES TEMPORAL;
	private Dato dato;
	
	public AdministradorBlockchain() {
		
		setBlockchain(new ArrayList<Bloque>());
		setDatos(new ArrayList<Dato>());
	}
	
	
	@Override
	public void crearDato(File archivo) {	
		setDato(new Dato(archivo));	
	}

	@Override
	public ArrayList<Dato> getDatos() {
		
		return datos;
	}
	
	@Override
	public void generarBlockchain() {
		
		try {
			validarDato(getDato());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public  ArrayList<Bloque> getBlockchain() throws Exception{
		
		Configuracion configuracion = new Configuracion("blockchain.properties");
		// Defino la conexion
		Connection laConexion = AdministradorDeConexiones.obtenerConexion(configuracion);
		
		String laConsulta = "SELECT * FROM [Blockchain].[dbo].[Hash]";
		Statement stmtConsulta = laConexion.createStatement();
		ResultSet rs = stmtConsulta.executeQuery(laConsulta);
		
		//INFORMO QUE SE ESTA POR HACER LA CONSULTA
		System.out.println(">>SQL: " + laConsulta);
		
		// Armo el array de bloques
		
		ArrayList<Bloque> bloques = new ArrayList();
		
		// muestro los datos
		
		while (rs.next()) {
			// armo el bloque con los datos obtenidos
			Bloque bl = new Bloque();
			bl.setHash(rs.getString("Hash"));
			bl.setHashDato(rs.getString("HashDato"));
			bl.setPreviousHash(rs.getString("PreviusHash"));
			bl.setTimeStamp(rs.getDate("TimeStamp"));
			//bl.setTimeStamp(convertUtilToJava(rs.getDate("TimeStamp")));
			
			// agrego el bloque al array
			bloques.add(bl);
			
		}
		
		// cierro el statement
		stmtConsulta.close();
		
		return bloques;
	}
	
	@Override
	public void almacenarBloque(Bloque blockChain) throws Exception {
		
		Configuracion configuracion = new Configuracion("blockchain.properties");
		
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
		ps.setDate(4, convertUtilToSql(blockChain.getTimeStamp()));
		
		ps.execute();
		
		ps.close();
		
		laConexion.close();
		
	}


	private java.sql.Date convertUtilToSql(java.util.Date uDate){
		java.sql.Date sDate = new java.sql.Date(uDate.getTime());
		return sDate;
	}
	
	private java.util.Date convertUtilToJava(java.sql.Date sDate){
		java.util.Date jDate = new java.util.Date(sDate.getDate());
		return jDate;
	}
	
	@Override
	public void mostrarBlockChain() throws Exception {
		
		try {
			for(Bloque bloque: getBlockchain())
				System.out.println("\n\n" + "Hash Bloque:\t" + bloque.getHash() + "\n" + 
								   "Hash Bloque Anterior:\t" + bloque.getPreviousHash()+ "\n" + 
								   "Hash Dato:\t" + bloque.getHashDato()+ "\n" +
								   "Time stamp:\t" + bloque.getTimeStamp().toString() + "\n" + "\n");
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	


	private void validarDato(Dato dato) throws Exception {
		
		if( hashValido(dato) )
			try {
				{		
					if(getBlockchain().isEmpty()) {
						getBlockchain().add( new Bloque("0", dato.getHash()) );

					}else {
						getBlockchain().add( new Bloque(getBlockchain().get(getBlockchain().size()-1).getHash(), dato.getHash()) );
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		else {
			System.out.println("Dato invalido: el documento ya se encuentra en la blockchain");
			
		}
	}

	private boolean hashValido( Dato dato ) {
		
		try {
			for(Bloque bloque: getBlockchainLocal()) 
				if(bloque.getHashDato().equals(dato.getHash()))
					return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	private void setBlockchain(ArrayList<Bloque> blockchain){
		this.blockchain = blockchain;
	}
	
	private void setDatos(ArrayList<Dato> datos){
		this.datos = datos;
	}

	public Dato getDato() {
		return dato;
	}
	public void setDato(Dato dato) {
		this.dato = dato;
	}
	@Override
	public void eliminarTodosLosRegistros() throws Exception {
		
		
		Configuracion configuracion = new Configuracion("blockchain.properties");
		
		// Defino la conexion
		Connection laConexion = AdministradorDeConexiones.obtenerConexion(configuracion);
		
		// EN algun momento lo paso como sp dentro de la base de datos para llamar a SP_NUEVO_BLOQUE
		//ARMAR UN INSERT
		String elTruncate = "Truncate Table [Blockchain].[dbo].[Hash]";
		
		//Informao la inser
		System.out.println(">>SQL: " + elTruncate);

		// preparo la insercion
		PreparedStatement stmt = laConexion.prepareStatement(elTruncate);
		stmt.execute();
		stmt.close();
		laConexion.close();	
	}
	@Override
	public String validarArchivo(File archivo) throws Exception {
		
		crearDato(archivo);
		this.setBlockchainLocal(getBlockchain());
		if( hashValido(dato) ) {		
			if(getBlockchainLocal().isEmpty()) {
					almacenarBloque(new Bloque("0", dato.getHash()));
			}else {
				      almacenarBloque(new Bloque(getBlockchainLocal().get(getBlockchainLocal().size()-1).getHash(), dato.getHash()) );
			}
			return "Pdf Valido!";
		}else {
			return "Pdf invalido: el documento ya se encuentra en la blockchain";
			
		}
	}
	public ArrayList<Bloque> getBlockchainLocal() {
		return blockchainLocal;
	}
	public void setBlockchainLocal(ArrayList<Bloque> blockchainLocal) {
		this.blockchainLocal = blockchainLocal;
	}
}
