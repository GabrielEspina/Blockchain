package ar.edu.ub.si.blockchain.controlador;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ar.edu.ub.si.blockchain.data.Bloque;
import ar.edu.ub.si.blockchain.data.Dato;
import ar.edu.ub.si.blockchain.data.MerkleTree;

	

public class AdministradorBlockchain extends Administrador {
	
	//CREAMOS LA BLOCKCHAIN	
	private  ArrayList<Bloque> blockchainLocal;
	
	//ESTE OBJETO ES TEMPORAL;
	private Dato dato;
	private String rootHash;
	
	public AdministradorBlockchain(String configuration) {
		super(configuration);
		
		
		
	}
	
	
	@Override
	public void crearDato(File archivo) {	
		setDato(new Dato(archivo));	
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
		// Defino la conexion
		
		
		String consulta = "SELECT * FROM [Blockchain].[dbo].[Hash]";
		Statement stmtConsulta = connection().createStatement();
		ResultSet rs = stmtConsulta.executeQuery(consulta);
		
		//INFORMO QUE SE ESTA POR HACER LA CONSULTA
		System.out.println(">>SQL: " + consulta);
		
		// Armo el array de bloques
		
		ArrayList<Bloque> bloques = new ArrayList<Bloque>();
		
		// muestro los datos
		
		while (rs.next()) {
			// armo el bloque con los datos obtenidos
			Bloque bl = new Bloque();
			bl.setHash(rs.getString("Hash").trim());
			bl.setHashDato(rs.getString("HashDato").trim());
			bl.setPreviousHash(rs.getString("PreviusHash").trim());
			
			//bl.setTimeStamp(rs.getDate("TimeStamp"));
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
		
		
		
		// EN algun momento lo paso como sp dentro de la base de datos para llamar a SP_NUEVO_BLOQUE
		//ARMAR UN INSERT
		String insercion = "INSERT INTO [Blockchain].[dbo].[Hash] "
							+"(Hash, HashDato, PreviusHash, TimeStamp)" 
							+ "VALUES (?,?,?,?)";
		
		//Informao la inser
		System.out.println(">>SQL: " + insercion);

		// preparo la insercion
		PreparedStatement ps = connection().prepareStatement(insercion, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, blockChain.getHash());
		ps.setString(2, blockChain.getHashDato());
		ps.setString(3, blockChain.getPreviousHash());
		ps.setString(4, blockChain.getTimeStamp().toString());
		//ps.setDate(4, convertUtilToSql(blockChain.getTimeStamp()));
		
		ps.execute();
		
		ps.close();
		
		
	}


	private java.sql.Date convertUtilToSql(java.util.Date uDate){
		java.sql.Date sDate = new java.sql.Date(uDate.getTime());
		return sDate;
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
			System.out.println("Validando dato...");
			for(Bloque bloque: getBlockchainLocal()) {
				System.out.println("dato viejo: " + bloque.getHashDato().length());
				if(bloque.getHashDato().equals(dato.getHash()))
					return false;
			}
				
				
		} catch (Exception e) {
			System.out.println("error");
			e.printStackTrace();
		}
		return true;
	}

	public void insertarUltimoHashRoot() throws Exception {
		
		
		this.setBlockchainLocal(getBlockchain());
		
		List<String> hashtree = new ArrayList<String>();
		for (Bloque bloque: getBlockchainLocal())
			hashtree.add(bloque.getHash());
			
		
		MerkleTree merkletree = new MerkleTree(hashtree);
		merkletree.merkle_tree();
		this.setRootHash(merkletree.getRoot());
		
		System.out.println(merkletree.getRoot());
		
		String insercion = "INSERT INTO [Blockchain].[dbo].[RootHash] "
							+"(hashroot)" 
							+ "VALUES (?)";
		
		//Informao la insert
		System.out.println(">>SQL: " + insercion);

		// preparo la insercion
		PreparedStatement ps = connection().prepareStatement(insercion, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, this.getRootHash());	
		ps.execute();
		
		ps.close();
	}

	public String getUltimoHashRoot() throws Exception {
		// Defino la conexion
		String consulta = "SELECT * FROM [Blockchain].[dbo].[RootHash]";
		Statement stmtConsulta = connection().createStatement();
		ResultSet rs = stmtConsulta.executeQuery(consulta);
		
		//INFORMO QUE SE ESTA POR HACER LA CONSULTA
		System.out.println(">>SQL: " + consulta);
		System.out.println(rs.getString("hashroot"));

		this.setRootHash(rs.getString("hashroot"));
	
		// cierro el statement
		stmtConsulta.close();
		
		return rs.getString("hashroot");
	}


	public Dato getDato() {
		return dato;
	}
	public void setDato(Dato dato) {
		this.dato = dato;
	}
	@Override
	public void eliminarTodosLosRegistros() throws Exception {
		
		// EN algun momento lo paso como sp dentro de la base de datos para llamar a SP_NUEVO_BLOQUE
		//ARMAR UN INSERT
		String elTruncate = "Truncate Table [Blockchain].[dbo].[Hash]Truncate Table [Blockchain].[dbo].[RootHash]";
		
		//Informao la inser
		System.out.println(">>SQL: " + elTruncate);

		// preparo la insercion
		PreparedStatement stmt = connection().prepareStatement(elTruncate);
		stmt.execute();
		stmt.close();
		
	}
	@Override
	public String validarArchivo(File archivo) throws Exception {
		
		crearDato(archivo);
		this.setBlockchainLocal(getBlockchain());
		if( hashValido(dato) ) {		
			if(getBlockchainLocal().isEmpty()) {
					almacenarBloque(new Bloque("0", dato.getHash()));
					insertarUltimoHashRoot();
			}else {
				    almacenarBloque(new Bloque(getBlockchainLocal().get(getBlockchainLocal().size()-1).getHash(), dato.getHash()) );
				    insertarUltimoHashRoot();
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


	public String getRootHash() {
		return rootHash;
	}


	public void setRootHash(String rootHash) {
		this.rootHash = rootHash;
	}
}
