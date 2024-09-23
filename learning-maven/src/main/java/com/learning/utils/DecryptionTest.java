package com.learning.utils; 
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.zip.Inflater; 

public class DecryptionTest {         

	// TM_EVNT_HEADER length
	private static final String TM_EVNT_HEADER = "TM_EVNT_QUEDEFLATE()";
	// buffer length hardcoded in cpp = 33
	private static final int bufLen = 33; 

	public static String decompressZlib(String hexString) {         
		try {  
			
			String decodedString = decodeHexString(hexString);
			System.out.println("decode hex string :: "+decodedString);
			if(decodedString.startsWith(TM_EVNT_HEADER)) {
				int hexStringStart = (TM_EVNT_HEADER.length()+bufLen)*2;
				System.out.println(hexStringStart);
				hexString = hexString.substring(hexStringStart);
			}
			// Convert hex string to byte array             
			byte[] compressedData = new BigInteger(hexString, 16).toByteArray();             
			if (compressedData[0] == 0) {                 
				compressedData = Arrays.copyOfRange(compressedData, 1, compressedData.length);             
			}             
			// Create Inflater instance            
			Inflater inflater = new Inflater();             
			inflater.setInput(compressedData);             
			// Create an expandable byte array to hold the decompressed data             
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream(compressedData.length);             
			// Buffer for extracted data             
			byte[] buffer = new byte[1024];             
			while (!inflater.finished()) {                 
				int count = inflater.inflate(buffer);                 
				outputStream.write(buffer, 0, count);             
			}             
			outputStream.close();            
			byte[] output = outputStream.toByteArray();                         
			String outputString = new String(output, 0, output.length, StandardCharsets.UTF_8);  
			System.out.println(outputString);
			return outputString;         
		} catch (Exception e) {             
			e.printStackTrace();             
			return null;         
		}     
	}
	
	private static String decodeHexString(String hex) {
	    int l = hex.length();
	    byte[] data = new byte[l / 2];
	    for (int i = 0; i < l; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
	                + Character.digit(hex.charAt(i + 1), 16));
	    }
	    String st = new String(data, StandardCharsets.UTF_8);
	    return st;
	}
	
	public static void main(String [] args){         
		System.out.println("'testing");         
		
		String input1 = "789C8D556D6FDA3A14FEDE5F61E5CBEE2615125AFA26E894927445CB0297844EEA972B2F39B4D69238B31D34FEFDECD80E81BB6E459550CFCBE3E73CE785C9C79F6581B6C038A1D5D4F106AE83A0CA684EAAE7A9D388CDE99583B8C0558E0B5AC1D4D90177D0C7DB934944719E8048E9B2C0550539F2EBBAD8AD80D3866530A315170C934AF0A9F32D5DAD430705644BD433339A4BA0AF0F8983C22D5422C00252524A9B371A7A97C391EB9D236F7C33BABA71CF4D4C8C95FFF84DE35C6459C318E46F01B2B12AEE4956B4D86C3888A9E30EDCF663C256505326DE06B9E6C06C3D3F055439E45F813CBF888028E53250E81763EF4CA1AB1202E01923B56825FF0C50A344D0FA1D4749F60279530092CF62A455530961D523E28E87679E24E25E22CFBD71D5DFFFA25EAFAE95D03C33A3655D80ACB26B9116F847234700E2A6FCA60AB349023371C062746D585CDF9CED591CC4FD89C7B3548764FC13A34D3D753E04F7A983968C9498ED2CC107CC7249B2244293BCF7A344B25C6121C7F3111764CF7C0598DBD15A4671D0B71CE8DD8DCE0A0AAC3A0CB524DCB03F704D644F53D55776B70B83F99E48B2E3024A55F53C983A17D79717725B522A701130B2857DFFC7A38167C0F66E23E4E0EAB2E7D1AA2F36C90BA94B68D7C73B76C96191E69134CBAAD4CB32F9113887C267121817AF97A2C3FE5EF3EDC9094293199678C090F9EE16F7343995021BAB5E4D6345FF046192CE633F9D2FE2F71206C9CF64B908906C57A37AB30882E8314EEF57FF3A43E34E66FECCFADB39B00EF3C25DC34925893FC8D3C2D1EF8CAFD6D1224D862647579500DB920C90F9D655A591630D6A8959858B79E0297BF21BC768EA3C3D3D29740518FE6874B3D25D2D874D6052A0039B1DEF03E31E4DBEF260B152CCC866D3516C074CDB54DCD5F5C8B5A2FA8DA07E96412DF46C5A05CD709ACA0FF04CFD0DAFDB2BA53724AC9A52A6DAF47891A2649D2C43B94386935FD2469E695DD8EC05B367D026751507E79DD2EDC8A8EB5A653B55A1855C271DD47E63E49D100DB72151F45FB08CFC380EBBD025DEB552012BBBE159854B7FDE452898F5E28BD143FDF7488BA60469B319BD61527E7D977BFEE8CE0AD58159E476E7787BD3960432E0EDCF63FF8C7E27393FBEAD9AC1B155BF2B7776DC1B4CFBD01DA5DFE53D333DEA0BD7851DAD6D4433ACAE593B4687ED3B4A3107E12D09C7F7F0AF395AB3FEEFF1EDC92FC70AA181";                 
		
		String input2 = "544D5F45564E545F5155454445464C4154452829373538232020202020202020202020202020202020202020202020202020202020789C8D52618B824010FDEEAF58F67BA91DDC55A8C169714164A77670F72596750A4177BDDDB5CB7F7FAB25D8155C82E0BC79EFCDCC4367762A7274042133CE5C6C0F2D8C80519E66ECE0E24AED07638CA4222C253967E0E21A244633CF70964CC14110A575EF1554303F0253C8174014A4AFF556827071B05D055B8C82EC989D073400462D37D0C4242BB4A76D99F6C41C59230B5993E9D3CBD41A5F386BD2F4EF8EBA30424A2B21207DC4ADE336BC2F7D4DB8DF4B502EB68656FB5C6811945CA8C72CAFCED47B1E22F86E775C068DCC7EC6685D153E2FCA1CB4A59ED5020B92E5BD4A17B22B36825390F2D20D7FD84D9A1110C999CFD3269A6817E87715FABB7E2300494556AA73E69900AAB8A8512F48B4E2B4FDC028E695A0D0D567DFC52299C749D78B292F1B30F6076F3C4F87EAA4E38F15F93F25CF301072FAC16899AA24FA2079A5359B28F4B179665DEFE169E8064CEA123AA9BE7BEE2761F4D9E835D3FCAB371CF3EE9FE319BF65A1F466";                
		
		decompressZlib(input1);         
		decompressZlib(input2);                    
	}  
}