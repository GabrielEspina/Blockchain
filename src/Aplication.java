import java.util.ArrayList;
import java.util.List;

import ar.edu.ub.si.blockchain.interfaces.IAdministradorBlockchain;
import ar.edu.ub.si.blockchain.merkleTrees.MerkleTrees;
import ar.edu.ub.si.blockchain.vista.AdminScreen;




public class Aplication {
	

	public static void main(String[] args) {
		
		/*
		
		IAdministradorBlockchain admin = new AdministradorBlockchain();

		
		AdminScreen.generateScreen( admin);

		//levantar un archivo conf para los properties

		
		AdminScreen.generateScreen( admin );
		*/
		
		
		
		
		// PRUEBA DEL ROOT MERKLE TREE
		
		List<String> tempTxList = new ArrayList<String>();
	    tempTxList.add("a");
	    tempTxList.add("b");
	    tempTxList.add("c");
	    tempTxList.add("d");
	    tempTxList.add("e");
	    
	    MerkleTrees merkleTrees = new MerkleTrees(tempTxList);
	    merkleTrees.merkle_tree();
	    System.out.println("root : " + merkleTrees.getRoot());
	
		
	}
	
	

}
