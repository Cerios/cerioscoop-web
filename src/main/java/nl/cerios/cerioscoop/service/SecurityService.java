package nl.cerios.cerioscoop.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityService {
	public static String hashPassword(final String input){
		try{
		MessageDigest md = MessageDigest.getInstance("SHA-256");

		md.update(input.getBytes("UTF-8"));
		byte[] digest = md.digest();

		return String.format("%064x", new java.math.BigInteger(1, digest));
		}catch(final NoSuchAlgorithmException e){
			//TODO print message with the logger
			return null;
		}catch(final UnsupportedEncodingException e){
			//TODO print message with the logger
			return null;
		}
	}
}
