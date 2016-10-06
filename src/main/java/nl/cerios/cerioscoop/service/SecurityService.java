package nl.cerios.cerioscoop.service;

import java.io.UnsupportedEncodingException;
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
}
