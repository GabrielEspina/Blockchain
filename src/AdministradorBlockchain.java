import java.io.File;
import java.util.ArrayList;

import ar.edu.ub.si.blockchain.almacen.AlmacenBDD;
import ar.edu.ub.si.blockchain.data.Bloque;
import ar.edu.ub.si.blockchain.data.Dato;
import ar.edu.ub.si.blockchain.interfaces.IAdministradorBlockchain;
import ar.edu.ub.si.blockchain.interfaces.IAlmacenBlockchain;
import ar.edu.ub.si.blockchain.util.Configuracion;

	

public class AdministradorBlockchain implements IAdministradorBlockchain{
	
	//CREAMOS LA BLOCKCHAIN	
	private  ArrayList<Bloque> blockchain;
	private  ArrayList<Dato> datos;
	
	//ESTE OBJETO ES TEMPORAL;
	private Dato dato;
	
	public AdministradorBlockchain() {
		
		setBlockchain(new ArrayList<Bloque>());
		setDatos(new ArrayList<Dato>());
	}
	
	
	@Override
	public void crearDato(File archivo) {
		
		//getDatos().add(new Dato(arhivo));
		
		setDato(new Dato(archivo));
		
	}

	@Override
	public ArrayList<Dato> getDatos() {
		
		return datos;
	}
	
	@Override
	public void generarBlockchain() {
		
		/*for(Dato dato: getDatos())
			validarDato(dato);
		*/
		
		validarDato(getDato());
	}
	
	@Override
	public  ArrayList<Bloque> getBlockchain(){
		return blockchain;
	}
	
	@Override
	public void almacenarBloque() {
		Configuracion configuracion = new Configuracion("blockchain.properties");
		
		
	}

	@Override
	public void mostrarBlockChain() {
		
		for(Bloque bloque: getBlockchain())
			System.out.println("\n\n" + "Hash Bloque:\t" + bloque.getHash() + "\n" + 
							   "Hash Bloque Anterior:\t" + bloque.getPreviousHash()+ "\n" + 
							   "Hash Dato:\t" + bloque.getHashDato()+ "\n" +
							   "Time stamp:\t" + bloque.getTimeStamp().toString() + "\n" + "\n");	
	}
	


	private void validarDato(Dato dato) {
		
		if( hashValido(dato) ) {
			
			System.out.println("Dato valido");
			
			if(getBlockchain().isEmpty()) {
				getBlockchain().add( new Bloque("0", dato.getHash()) );

			}else {
				getBlockchain().add( new Bloque(getBlockchain().get(getBlockchain().size()-1).getHash(), dato.getHash()) );
			}
		}else {
			System.out.println("Dato invalido: el documento ya se encuentra en la blockchain");
			
		}
	}
	
	
	private boolean hashValido( Dato dato ) {
		for(Bloque bloque: getBlockchain()) 
			if(bloque.getHashDato().equals(dato.getHash()))
				return false;
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
	public void eliminarTodosLosRegistros() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public String validarArchivo(File archivo) {
		crearDato(archivo);
		
		if( hashValido(dato) ) {		
			//System.out.println("Dato valido");
			if(getBlockchain().isEmpty()) {
				getBlockchain().add( new Bloque("0", dato.getHash()) );

			}else {
				getBlockchain().add( new Bloque(getBlockchain().get(getBlockchain().size()-1).getHash(), dato.getHash()) );
			}
			return "Pdf Valido!";
		}else {
			return "Pdf invalido: el documento ya se encuentra en la blockchain";
			
		}
	}
	
	

}
