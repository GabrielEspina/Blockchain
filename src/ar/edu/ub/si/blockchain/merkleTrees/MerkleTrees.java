package ar.edu.ub.si.blockchain.merkleTrees;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;



public class MerkleTrees  {


	  // transaction List
	  List<String> txList;
	  // Merkle Root
	  String root;
	  
	  /**
	   * constructor
	   * @param txList transaction List
	   */
	  public MerkleTrees(List<String> txList) {
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
/*
	      // sha2 hex value
	      String sha2HexValue = getSHA2HexValue(left + right);
	      newTxList.add(sha2HexValue);
	      index++;
	      */
	      // MD5
	     // String mD5Value = generarHashMD5(left + right);
	     String mD5Value = getMD5(left + right);
	      newTxList.add(mD5Value);
	      index++;
	      
	    }
	    
	    return newTxList;
	  }
	  
	  /**
	   * Return hex string
	   * @param str
	   * @return
	   */
	  public String getSHA2HexValue(String str) {
	        byte[] cipher_byte;
	        try{
	            MessageDigest md = MessageDigest.getInstance("SHA-256");
	            md.update(str.getBytes());
	            cipher_byte = md.digest();
	            StringBuilder sb = new StringBuilder(2 * cipher_byte.length);
	            for(byte b: cipher_byte) {
	              sb.append(String.format("%02x", b&0xff) );
	            }
	            return sb.toString();
	        } catch (Exception e) {
	                e.printStackTrace();
	        }
	        
	        return "";
	  }
	  
	  /**
	   * Get Root
	   * @return
	   */
	  public String getRoot() {
	    return this.root;
	  }

	private String generarHashMD5(String str) {


			   String md5 = null;
			   try {
			         MessageDigest mdEnc = MessageDigest.getInstance("MD5"); //Encryption algorithm
			         mdEnc.update(str.getBytes(), 0, str.length());
			         md5 = new BigInteger(1, mdEnc.digest()).toString(16); // Encrypted string
			        } 
			    catch (Exception ex) {
			         return "";
			    }
			    return md5;
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
		
		
	}
   
	