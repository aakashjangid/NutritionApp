package com.wastedpotential.nutritionapp.serviceimpl;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Service;

@Service
public class AESEncryption {

	private final SecretKey secKey = getSecretEncryptionKey();

	private SecretKey getSecretEncryptionKey() {
		KeyGenerator generator;
		try {
			generator = KeyGenerator.getInstance("AES");
			generator.init(128);
			return generator.generateKey();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	public byte[] encryptText(String plainText) throws Exception {

		Cipher aesCipher = Cipher.getInstance("AES");
		aesCipher.init(Cipher.ENCRYPT_MODE, secKey);
		byte[] byteCipherText = aesCipher.doFinal(plainText.getBytes());
		return byteCipherText;
	}

	public String decryptText(byte[] byteCipherText) throws Exception {

		Cipher aesCipher = Cipher.getInstance("AES");
		aesCipher.init(Cipher.DECRYPT_MODE, secKey);
		byte[] bytePlainText = aesCipher.doFinal(byteCipherText);
		return new String(bytePlainText);
	}

	public String bytesToHex(byte[] hash) {
		return DatatypeConverter.printHexBinary(hash);
	}

	public byte[] hexToBytes(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
		}
		return data;
	}
}