package com.security.tutorial.util;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {
	
	private static String clientIv;
	private static String clientSecret;
	
	public static IvParameterSpec generateIv() {
	    byte[] iv = new byte[16];
	    clientIv = Base64.getEncoder().encodeToString(iv);
	    System.out.println("originalIV::"+clientIv);
	    //new SecureRandom().nextBytes(iv);
	    return new IvParameterSpec(iv);
	}
	
	public static SecretKey generateKey(int n) throws NoSuchAlgorithmException {
	    KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
	    keyGenerator.init(n);
	    SecretKey key = keyGenerator.generateKey();
	    clientSecret = Base64.getEncoder().encodeToString(key.getEncoded());
	    System.out.println("secret_key::"+clientSecret);
	    return key;
	}
	
	public static SecretKey getKeyFromPassword(String password, String salt)
		    throws NoSuchAlgorithmException, InvalidKeySpecException {
		    
		    SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
		    KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);
		    SecretKey secret = new SecretKeySpec(factory.generateSecret(spec)
		        .getEncoded(), "AES");
		    String secretKeyString = Base64.getEncoder().encodeToString(secret.getEncoded());
		    System.out.println("secret_key::"+secretKeyString);
		    return secret;
		}
	
	public static String encrypt(String algorithm, String input, SecretKey key,
			IvParameterSpec iv) throws NoSuchPaddingException, NoSuchAlgorithmException,
	InvalidAlgorithmParameterException, InvalidKeyException,
	BadPaddingException, IllegalBlockSizeException {

		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.ENCRYPT_MODE, key, iv);
		byte[] cipherText = cipher.doFinal(input.getBytes());
		return Base64.getEncoder()
				.encodeToString(cipherText);
	}

	public static String decrypt(String algorithm, String cipherText, SecretKey key,
			IvParameterSpec iv) throws NoSuchPaddingException, NoSuchAlgorithmException,
	InvalidAlgorithmParameterException, InvalidKeyException,
	BadPaddingException, IllegalBlockSizeException {

		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.DECRYPT_MODE, key, iv);
		byte[] plainText = cipher.doFinal(Base64.getDecoder()
				.decode(cipherText));
		return new String(plainText);
	}
	
	public static String decrypt(String algorithm, String cipherText) throws NoSuchPaddingException, NoSuchAlgorithmException,
	InvalidAlgorithmParameterException, InvalidKeyException,
	BadPaddingException, IllegalBlockSizeException {
		
		// creating secretkey from string
		// decode the base64 encoded string
		byte[] decodedKey = Base64.getDecoder().decode(clientSecret);
		// rebuild key using SecretKeySpec
		SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES"); 
		System.out.println("decoded secret_key::"+Base64.getEncoder().encodeToString(originalKey.getEncoded()));
		
		// creating iv from string
		byte [] decodedIv = Base64.getDecoder().decode(clientIv);
		IvParameterSpec iv = new IvParameterSpec(decodedIv);
		System.out.println("decodedIV::"+Base64.getEncoder().encodeToString(decodedIv));
		
		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.DECRYPT_MODE, originalKey, iv);
		byte[] plainText = cipher.doFinal(Base64.getDecoder()
				.decode(cipherText));
		return new String(plainText);
	}
	
	public static void main(String [] args) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException {
		String input = "baeldung";
	    SecretKey key = AESUtil.generateKey(128);
	    //SecretKey key = AESUtil.getKeyFromPassword("rsaPublicKey", "mySalt");
	    IvParameterSpec ivParameterSpec = AESUtil.generateIv();
	    
	    String algorithm = "AES/CBC/PKCS5Padding";
	    
	    String cipherText = AESUtil.encrypt(algorithm, input, key, ivParameterSpec);
	    System.out.println("cipherText::"+cipherText);
	    
	    //String plainText = AESUtil.decrypt(algorithm, cipherText, key, ivParameterSpec);
	    String plainText = AESUtil.decrypt(algorithm, cipherText);
	    System.out.println("plainText::"+plainText);
	}
}
