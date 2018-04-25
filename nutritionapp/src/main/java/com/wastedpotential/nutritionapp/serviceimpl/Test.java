package com.wastedpotential.nutritionapp.serviceimpl;

public class Test {

	public static void main(String[] args) {
	
		AESEncryption aesEncryption = new AESEncryption();
		try {
			byte[] cy1 = aesEncryption.encryptText("nitesh.yadav@yash.com");
			String code = aesEncryption.bytesToHex(cy1);
			System.out.println(code);
			byte[] nitesh = aesEncryption.hexToBytes(code);
			String cy = aesEncryption.decryptText(nitesh);
			System.out.println(cy);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
