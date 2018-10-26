package ar.edu.ub.si.blockchain.merkleTrees;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class MerkleTrees2 {

	//store transactions in this list
	private List<String> transactions;
	
	public MerkleTrees2(List<String> transactions) {
		this.transactions = transactions;
	}
	
	// the rott is in this list in the end
	
	public List<String> getMerkeRoot(){
		return construct(this.transactions);
	}
	
	// it is a recursive function that keeps mergin the
	// neighboring hashes (index i and i+1)
	
	

	private List<String> construct(List<String> transactions) {
		// base case for recursive method
		
		if(transactions.size() == 1) return transactions;
		
		// it contains fewer and fewer items in every iteration
		List<String> updatedList = new ArrayList<>();
		
		// mergin the neigboring items
		for (int i=0; i<transactions.size()-1; i+=2 )
			updatedList.add(mergeHash(transactions.get(i), transactions.get(i+1)));
		
		// recursive method call
		return construct(updatedList);
	}

	// concatenate two string and hash it with sha
	private String mergeHash(String hash1, String hash2) {
		String mergedHash = hash1+hash2;
		return generarHashMD5(mergedHash);
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

}
