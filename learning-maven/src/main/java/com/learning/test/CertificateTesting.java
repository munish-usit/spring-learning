package com.learning.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;

import javax.security.auth.x500.X500Principal;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;




public class CertificateTesting {

	private static final String SIGNATURE_ALGORITHM = "SHA256WithRSA";
	  private static final String PROVIDER = "BC";

	  static {
			if (Objects.isNull(Security.getProvider(BouncyCastleProvider.PROVIDER_NAME))) {
	            Security.addProvider(new BouncyCastleProvider());
	        }
		}
	
	public static void generateCertificate() throws Exception {
		KeyPairGenerator generator;
		generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(2048);
		KeyPair pair = generator.generateKeyPair();
		PublicKey publicKey = pair.getPublic();
		String type = "CERTIFICATE";
		
		PemObject pemObject = new PemObject(type, publicKey.getEncoded());
		
		StringWriter sw = new StringWriter();
		try (PemWriter pemWriter = new PemWriter(sw)) {
			pemWriter.writeObject(pemObject);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String content = sw.toString();
		System.out.println(content);
		

	    X509Certificate cert = generateCertificate(pair);
	    
	    PemObject certObj = new PemObject(type, cert.getEncoded());
	    
	    PemWriter pemWriter = new PemWriter(new OutputStreamWriter(new FileOutputStream("src/main/resources/certificate.pem")));
	    try {
	      pemWriter.writeObject(certObj);
	    } finally {
	      pemWriter.close();
	    }

	   // FileOutputStream fos = new FileOutputStream("src/main/resources/certificate.crt");
	   // fos.write(cert.getEncoded());
	    //fos.close();
	    
	    System.out.println("finished");

	}
	
	public static X509Certificate generateCertificate(KeyPair keyPair) throws Exception {
	    X500Principal subject = new X500Principal("CN=Test Certificate");
	    SubjectPublicKeyInfo subjectPublicKeyInfo = SubjectPublicKeyInfo.getInstance(keyPair.getPublic().getEncoded());

	    X500Name name = new X500Name("CN=Test Certificate");

	    X509v3CertificateBuilder builder = new X509v3CertificateBuilder(
	    		name,
	        BigInteger.valueOf(new SecureRandom().nextInt()),
	        new Date(System.currentTimeMillis() - 1000L * 60 * 60 * 24 * 30),
	        new Date(System.currentTimeMillis() - (1000L * 60 * 60 * 24 * 365 * 10)),
	        name,
	        subjectPublicKeyInfo
	    );
	    
	    
	    
	    ContentSigner signer = getSigner(keyPair);
	    X509Certificate certificate = new JcaX509CertificateConverter()
	        .setProvider(PROVIDER)
	        .getCertificate(builder.build(signer)); 

	    return certificate;
	  }

	  private static ContentSigner getSigner(KeyPair keyPair) throws OperatorCreationException {
	    return new JcaContentSignerBuilder(SIGNATURE_ALGORITHM)
	        .setProvider(PROVIDER)
	        .build(keyPair.getPrivate());
	  }
	  
	private static PrivateKey getPrivateKey(String base64PrivateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
			base64PrivateKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCP+AL76xoHMyRXedrtAFvRj/QWuC9RQNc7I7jE5l6MF2uwQXrrNEw4eBB7XtCDVaI2cSlti9SjcelR0fd9bQ1guR8QDN2mV/AO/6X+SIil6VlT5Gu4s2bvu6eAQCPw3mkJavNKTla5RN+Ra0i2Sn/JufTB/Y4ar/azlMiImC10NO5G2ibIBPMCd+6FierEXrIT8ZNtW9Fm3WL56Axv+MYFbGBhC4IylPJi9gKerKAxaupYqHOSCHnDt51jtFv+DCSBvjRpwb2oX6Eadz1VEhwkLTCpcY5WzTMA8zptZTa4e53A8CxVuZJQYp4zRK4wgBc2C5ayAP1om8u68GmEGuszAgMBAAECggEAA39K00PKxhH/+AAFyWwQzu5oR4UB4T9p+w3LFz1hXpXTD90HV19V2qlP/S84cXhLEHd/QiH7cFz8BfjLwkg74vWcQ4+MctYABt0+8wZgC2e3u8n4s+vnvDsavorQVLGSa6AleNCeWrHJnIa2pXb507SotW2rXgqYO5SJqofH0uLM7bhgLeh7rDZMqQqhGEoXQ8zbCIohTW2Xljzn8eGGkW9OV9x+7V+yTtz7gLLPwHvjCtVDAaWBmt3EY7M9upC4JnOQEtXuDdjPWeyEJDbtLiciBx6peEKYlijmEydIvya62BwTy20ACrVQgZoW/EjlU/JsU1PcTaw7K9SofarYgQKBgQDNfJlck3l9210xzZzfM/V1Pp3kenlZYzf5oTfeovwgykrF/hBvsZg+XaK5vZxGywm5eTFTpmTRTWNtMDnxQA9DQcMm4Q07LBvOhHG33u67QP6QSZo7CDlEMa3kSr+4G7nVp5u/h532zd/o+zAUyHEbUYwhIPPy/WnSQ2nomyhqawKBgQCzXA2rm1pRqgDeRYw4HrFZeVPe9zM3w4CVwXQCQdDVo6ySzUxP87Ntf6aFtuH3GeUgWun27iE0n5h9JqxbqF42SazRVEhOa7lWph+PoquEFzt2M/3S1kkvAn1CL+97AS7Ot7v/icyT1o3E1JuUhapiB0WGpuPvKDb8B6IcMbzEWQKBgH9IZerNQt0ty011Mm2jzrCAR+dl4dL3vY14SpYy2QAqriR0WpQFKuu+rnRlOe80B2MG6gIJuLtZN3REAjAo4QtlKc/kaYC0ssi6i4I8qh39hXG01b8pOee+6hrWHmmZawQoHTaHd9aP8dlr9n15J26nlahTtChYxnrC3VCDA4bfAoGAXVcYnif49urcDNBwynvem8uyRCSteOW7MzQ75TjPL39APh2tCyauR9vYk1m7etb1EMN/YsxGZ5ZuyyKnLAQRE3t//mg4QOd6yoclgaCMVe+pODiSbXirGVXYTeuYW5EgBOBFbBUQbBeKK9bhKIfvRZi3K/4KPNnW0449ZSLVM/ECgYA9D7gxJLcwK+L/VFFN21/QMX2+iv0dZPkxhY/THyuaaX670NqH7oefGl2IqaHncnBf5fFy+IbWYsHhvzL1PcAerWoFxHSfEDyk8UUjhBfL9J7J3U1c6Pfo0Rb7l9NKf4FPpZ0ydo5WA11VH2iuWehpFPpQhyFKd/PBt2e/BQtSLg==";
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64PrivateKey.getBytes()));
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			System.out.println("success");
			return keyFactory.generatePrivate(keySpec);
			
			
	 }
	  
	public static void main(String [] args) throws Exception{
		//generateCertificate();
		getPrivateKey("");
	}
}
