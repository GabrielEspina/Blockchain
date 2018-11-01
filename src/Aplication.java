import java.util.ArrayList;
import java.util.List;

import ar.edu.ub.si.blockchain.interfaces.IAdministradorBlockchain;
import ar.edu.ub.si.blockchain.merkleTrees.MerkleTrees;
import ar.edu.ub.si.blockchain.merkleTrees.MerkleTrees2;
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
	  //  tempTxList.add("e");
	    
	    
	    // NOTA:  con ambos merkletrees,  si son pares los string que hay en el array, devuelve el mismo hash
	    // Pero si es impar, el merkletrees2 no estaria haciendo bien el hasheo creo. en el 2, si es un solo item, no lo hashea
	    
	    MerkleTrees merkleTrees = new MerkleTrees(tempTxList);
	    merkleTrees.merkle_tree();
	    System.out.println("root merkle1:  " + merkleTrees.getRoot());
	    
	    MerkleTrees2 merkleTrees2 = new MerkleTrees2(tempTxList);
	    System.out.println("root merkle2:  " + merkleTrees2.getMerkeRoot().get(0));

		
	}
	
	

}
