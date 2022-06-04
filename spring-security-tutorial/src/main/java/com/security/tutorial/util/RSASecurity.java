package com.security.tutorial.util;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSASecurity {
	
	private static PrivateKey privateKey = null;
	private static PublicKey publicKey = null;
	
	private static String encodedMessage = null;
	
	private static void generateKeyPair() {
		KeyPairGenerator generator = null;
		try {
			generator = KeyPairGenerator.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		generator.initialize(2048);
		KeyPair pair = generator.generateKeyPair();
		privateKey = pair.getPrivate();
		publicKey = pair.getPublic();
		System.out.println(publicKey.toString());
	}
	
	
	private static void keyToString() throws InvalidKeySpecException, NoSuchAlgorithmException {
		//converting public key to byte            
		byte[] byte_pubkey = publicKey.getEncoded();
		//System.out.println("\nBYTE KEY::: " + byte_pubkey);

		//converting byte to String 
		String str_key = Base64.getEncoder().encodeToString(byte_pubkey);
		System.out.println("\nSTRING KEY::" + str_key);

		//converting string to Bytes
		byte_pubkey  = Base64.getDecoder().decode(str_key);
		//System.out.println("BYTE KEY::" + byte_pubkey);

		//converting it back to public key
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(byte_pubkey);
		PublicKey public_key = keyFactory.generatePublic(publicKeySpec);
		
		System.out.println("FINAL OUTPUT" + public_key);
	}
	
	// encryption is done using public key
	public static void  encryptRSA() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		String secretMessage = "Baeldung secret message";
		Cipher encryptCipher = Cipher.getInstance("RSA");
		encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] encryptedMessageBytes = encryptCipher.doFinal(secretMessage.getBytes(StandardCharsets.UTF_8));
		encodedMessage = Base64.getEncoder().encodeToString(encryptedMessageBytes);
		System.out.println("encrypted and encoded :: "+encodedMessage);
	}
	
	// decryption is done using private key
	public static void decryptRSA() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Cipher decryptCipher = Cipher.getInstance("RSA");
		decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
	
		byte[] decryptedMessageBytes = decryptCipher.doFinal( Base64.getDecoder().decode(encodedMessage));
		String decryptedMessage = new String(decryptedMessageBytes, StandardCharsets.UTF_8);
		System.out.println("decrypted string :: "+decryptedMessage);
	}
	
	public static void testRSA() throws InvalidKeySpecException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		generateKeyPair();
		keyToString();
		encryptRSA();
		decryptRSA();
	}
	
	
	public static void main(String[] args)  {
		try {
			testRSA();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
