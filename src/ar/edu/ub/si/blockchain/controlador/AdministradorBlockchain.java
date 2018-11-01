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
		
		// Armo el array de bloques
		
		ArrayList<Bloque> bloques = new ArrayList<Bloque>();
		
		// muestro los datos
		
		while (rs.next()) {
			// armo el bloque con los datos obtenidos
			Bloque bl = new Bloque();
			bl.setHash(rs.getString("Hash").trim());
			bl.setHashDato(rs.getString("HashDato").trim());
			bl.setPreviousHash(rs.getString("PreviusHash").trim());
			bl.setTimeStampStr(rs.getString("TimeStamp"));

			
			// agrego el bloque al array
			bloques.add(bl);
			
		}
		
		// cierro el statement
		stmtConsulta.close();
		
		return bloques;
	}
	
	@Override
	public void almacenarBloque(Bloque blockChain) throws Exception {
		
		//ARMAR UN INSERT
		String insercion = "INSERT INTO [Blockchain].[dbo].[Hash] "
							+"(Hash, HashDato, PreviusHash, TimeStamp)" 
							+ "VALUES (?,?,?,?)";
		// preparo la insercion
		PreparedStatement ps = connection().prepareStatement(insercion, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, blockChain.getHash());
		ps.setString(2, blockChain.getHashDato());
		ps.setString(3, blockChain.getPreviousHash());
		ps.setString(4, blockChain.getTimeStampStr());
		
		ps.execute();
		
		ps.close();
		
		
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
			for(Bloque bloque: getBlockchainLocal()) {
				if(bloque.getHashDato().equals(dato.getHash()))
					return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	

	public void insertarUltimoHashRoot() throws Exception {
		
		actualizarRootLocal();
		
		String insercion = "INSERT INTO [Blockchain].[dbo].[RootHash] "
							+"(hashroot)" 
							+ "VALUES (?)";


		// preparo la insercion
		PreparedStatement ps = connection().prepareStatement(insercion, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, this.getRootHash());	
		ps.execute();
		
		ps.close();
	}

	
	public void actualizarRootLocal() throws Exception{
		this.setBlockchainLocal(getBlockchain());
		
		List<String> hashtree = new ArrayList<String>();
		for (Bloque bloque: getBlockchainLocal()) {
			bloque.generarHash();
			hashtree.add(bloque.getHash());	
		}
		MerkleTree merkletree = new MerkleTree(hashtree);
		merkletree.merkle_tree();
		this.setRootHash(merkletree.getRoot());
	}
	
	public String getUltimoHashRootBD() throws Exception {
		String mHashRootBD = null;
		
		// Defino la conexion
		String consulta = "SELECT TOP (1) [idRoot]\r\n" + 
				"      ,[hashroot]\r\n" + 
				"  FROM [Blockchain].[dbo].[RootHash]\r\n" + 
				"  order by idRoot desc";
		
		Statement stmtConsulta = connection().createStatement();
		ResultSet rs = stmtConsulta.executeQuery(consulta);

		
		// muestro los datos
		
		while (rs.next()) {
			mHashRootBD = (rs.getString("hashroot").trim());			
		}
		// cierro el statement
		stmtConsulta.close();
		return mHashRootBD;
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

		// preparo la insercion
		PreparedStatement stmt = connection().prepareStatement(elTruncate);
		stmt.execute();
		stmt.close();
		
	}
	@Override
	public String validarArchivo(File archivo) throws Exception {
		
		
		if(consistenciaBlockChain()) {
			
		
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
	}else {
		return "BlockChain Corrupta! . Reinicie la blockchain.";
	}
	}
	
	public boolean consistenciaBlockChain() throws Exception{
		this.setBlockchainLocal(getBlockchain());
		
		if (blockchainLocal.isEmpty()) {
			return true;
		}else {
		
		actualizarRootLocal();
		
		if(getUltimoHashRootBD().equals(this.getRootHash())) {
			return true;
		}else {
			return false;			
		}
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
