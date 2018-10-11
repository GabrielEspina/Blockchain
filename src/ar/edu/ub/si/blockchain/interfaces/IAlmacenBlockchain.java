package ar.edu.ub.si.blockchain.interfaces;

import java.util.ArrayList;

import ar.edu.ub.si.blockchain.data.Bloque;

public interface IAlmacenBlockchain {
	
	public ArrayList<Bloque> getBlockChain();
	
	public void saveBlockChain(ArrayList<Bloque> blockChain);
	
}
