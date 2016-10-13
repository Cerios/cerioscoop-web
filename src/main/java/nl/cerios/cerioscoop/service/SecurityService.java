package nl.cerios.cerioscoop.service;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityService {
	public static String hashPassword(final String input){
		try{
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(input.getBytes("UTF-8"));
			byte[] digest = messageDigest.digest();
	
			return String.format("%064x", new java.math.BigInteger(1, digest));
		}catch(final NoSuchAlgorithmException | UnsupportedEncodingException e){
			 throw new RuntimeException( e );
		}
	}
	
	
	//snipper from: https://gist.github.com/DandyDev/5394643
	public static boolean ibanTest(final String accountNumber) {
		final int ibanNumberMinSize = 15; 
		final int ibanNumberMaxSize = 34;
		final BigInteger ibanMagicNumber = new BigInteger("97");
		
	    String newAccountNumber = accountNumber.trim();
	
	    // Check that the total IBAN length is correct as per the country. If not, the IBAN is invalid. We could also check
	    // for specific length according to country, but for now we won't
	    if (newAccountNumber.length() < ibanNumberMinSize || newAccountNumber.length() > ibanNumberMaxSize) {
	        return false;
	    }
	
	    // Move the four initial characters to the end of the string.
	    newAccountNumber = newAccountNumber.substring(4) + newAccountNumber.substring(0, 4);
	
	    // Replace each letter in the string with two digits, thereby expanding the string, where A = 10, B = 11, ..., Z = 35.
	    StringBuilder numericAccountNumber = new StringBuilder();
	    int numericValue;
	    for (int i = 0;i < newAccountNumber.length();i++) {
	        numericValue = Character.getNumericValue(newAccountNumber.charAt(i));
	        if(-1 >= numericValue) {
	            return false;
	        } else {
	            numericAccountNumber.append(numericValue);
	        }
	    }
	
	    // Interpret the string as a decimal integer and compute the remainder of that number on division by 97.
	    BigInteger ibanNumber = new BigInteger(numericAccountNumber.toString());
	    return ibanNumber.mod(ibanMagicNumber).intValue() == 1;
	
	}
}
