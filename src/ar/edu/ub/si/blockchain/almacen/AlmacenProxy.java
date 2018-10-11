package ar.edu.ub.si.blockchain.almacen;

import java.util.ArrayList;

import ar.edu.ub.si.blockchain.data.Bloque;
import ar.edu.ub.si.blockchain.interfaces.IAlmacenBlockchain;

public class AlmacenProxy implements IAlmacenBlockchain{

	private IAlmacenBlockchain almacen;
	
	public  IAlmacenBlockchain almacenArchivo() {
		setAlmacen( new AlmacenArchivo() );
		return getAlmacen();
	}
	
	public  IAlmacenBlockchain almacenBaseDeDatos() {
		setAlmacen( new AlmacenBDD() );
		return getAlmacen();
	}
	
	
	@Override
	public ArrayList<Bloque> getBlockChain() {
		return getAlmacen().getBlockChain();
	}

	@Override
	public void saveBlockChain(ArrayList<Bloque> blockChain) {
		getAlmacen().saveBlockChain(blockChain);
		
	}

	public IAlmacenBlockchain getAdministrador() {
		return getAlmacen();
	}

	public void setAdministrador(IAlmacenBlockchain administrador) {
		this.setAlmacen(administrador);
	}

	public IAlmacenBlockchain getAlmacen() {
		return almacen;
	}

	private void setAlmacen(IAlmacenBlockchain almacen) {
		this.almacen = almacen;
	}

}
