package com.base.utils;

import org.apache.commons.codec.binary.Base64;

public class Base64Encoding {


	public static String encodeBytes(byte[] bytes) {
		return Base64.encodeBase64String(bytes);
	}

	public static byte[] decodeString(String encodedString) {
		return Base64.decodeBase64(encodedString);
	}

	public static String encodeStringForString(String str) {
		return Base64.encodeBase64String(str.getBytes());
	}

	public static String decodeStringForString(String str) {
		return new String(Base64.decodeBase64(str));
	}

}
