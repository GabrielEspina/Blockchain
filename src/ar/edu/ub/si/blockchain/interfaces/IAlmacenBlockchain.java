package ar.edu.ub.si.blockchain.interfaces;
import ar.edu.ub.si.blockchain.util.*;

import java.util.ArrayList;

import ar.edu.ub.si.blockchain.data.Bloque;

public interface IAlmacenBlockchain {
	
	public ArrayList<Bloque> getBlockChain(Configuracion configuracion) throws Exception;
	
	public void saveBlockChain(Bloque blockChain, Configuracion configuracion) throws Exception;
	
	
	
}
