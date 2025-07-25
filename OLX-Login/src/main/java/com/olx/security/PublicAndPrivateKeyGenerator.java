package com.olx.security;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import org.springframework.stereotype.Service;

@Service
public class PublicAndPrivateKeyGenerator {
	
//	public static void main(String[] args) throws Exception {
//        new PublicAndPrivateKeyGenerator().generateKeyPair();
//    }
//	
	 public String generateKeyPair() throws NoSuchAlgorithmException, IOException {
	        // Generate RSA key pair
	        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
	        generator.initialize(2048);
	        KeyPair pair = generator.generateKeyPair();

	        PrivateKey privateKey = pair.getPrivate();
	        PublicKey publicKey = pair.getPublic();

	        // Convert to PEM format
	        String privatePem = convertToPEM("PRIVATE KEY", privateKey.getEncoded());
	        String publicPem = convertToPEM("PUBLIC KEY", publicKey.getEncoded());
	        String outputDir = "src/main/resources/keys";
	        // Ensure directory exists
	        File dir = new File(outputDir);
	        if (!dir.exists()) dir.mkdirs();

	        // Write to files
	        writeToFile(outputDir + "/private.key", privatePem);
	        writeToFile(outputDir + "/public.key", publicPem);

	        System.out.println("✅ RSA key pair generated successfully:");
	        //System.out.println("🔐 Private Key: " + outputDir + "/private.key");
	        //System.out.println("🔓 Public  Key: " + outputDir + "/public.key");
	        return "Generated";
	    }

	    private static String convertToPEM(String type, byte[] encoded) {
	        String base64 = Base64.getEncoder().encodeToString(encoded);
	        StringBuilder pem = new StringBuilder();
	        pem.append("-----BEGIN ").append(type).append("-----\n");
	        for (int i = 0; i < base64.length(); i += 64) {
	            pem.append(base64, i, Math.min(i + 64, base64.length())).append("\n");
	        }
	        pem.append("-----END ").append(type).append("-----\n");
	        return pem.toString();
	    }

	    private static void writeToFile(String path, String content) throws IOException {
	        try (FileOutputStream fos = new FileOutputStream(path)) {
	            fos.write(content.getBytes(StandardCharsets.UTF_8));
	        }
	    }
}
