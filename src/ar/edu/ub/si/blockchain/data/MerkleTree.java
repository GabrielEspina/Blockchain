package ar.edu.ub.si.blockchain.data;

import ar.edu.ub.si.blockchain.interfaces.IOperacionesHash;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class MerkleTree implements IOperacionesHash {
	
	  // transaction List
	  List<String> txList;
	  // Merkle Root
	  String root;
	  
	  /**
	   * constructor
	   * @param txList transaction List
	   */
	  public MerkleTree(List<String> txList) {
	    this.txList = txList;
	    root = "";
	  }
	   
	  /**
	   * execute merkle_tree and set root.
	   */
	  public void merkle_tree() {
	    
	    List<String> tempTxList = new ArrayList<String>();
	    
	    for (int i = 0; i < this.txList.size(); i++) {
	      tempTxList.add(this.txList.get(i));
	    }
	    
	    List<String> newTxList = getNewTxList(tempTxList);
	    while (newTxList.size() != 1) {
	      newTxList = getNewTxList(newTxList);
	    }
	    
	    this.root = newTxList.get(0);
	  }
	  
	  /**
	   * return Node Hash List.
	   * @param tempTxList
	   * @return
	   */
	  private List<String> getNewTxList(List<String> tempTxList) {
	    
	    List<String> newTxList = new ArrayList<String>();
	    int index = 0;
	    while (index < tempTxList.size()) {
	      // left
	      String left = tempTxList.get(index);
	      index++;

	      // right
	      String right = "";
	      if (index != tempTxList.size()) {
	        right = tempTxList.get(index);
	      }else {
	    	  right = left;
	      }

	      // MD5
	     String mD5Value = getMD5(left + right);
	      newTxList.add(mD5Value);
	      index++;
	      
	    }
	    
	    return newTxList;
	  }
	  

	  /**
	   * Get Root
	   * @return
	   */
	  public String getRoot() {
	    return this.root;
	  }

	
	private String getMD5(String input) {
		 try {
		 MessageDigest md = MessageDigest.getInstance("MD5");
		 byte[] messageDigest = md.digest(input.getBytes());
		 BigInteger number = new BigInteger(1, messageDigest);
		 String hashtext = number.toString(16);

		 while (hashtext.length() < 32) {
		 hashtext = "0" + hashtext;
		 }
		 return hashtext;
		 }
		 catch (NoSuchAlgorithmException e) {
		 throw new RuntimeException(e);
		 }
		 }

	@Override
	public void generarHash() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getHash() {
		return this.root;
	}

}
