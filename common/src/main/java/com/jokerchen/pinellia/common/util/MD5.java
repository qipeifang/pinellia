package com.jokerchen.pinellia.common.util;

import java.io.InputStream;
import java.security.MessageDigest;

/**   
 * @description: MD5加密
 * @author Joker Chen 
 * @date 2019-03-18 10:07:04  
 */
public class MD5 {
	
    private static char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

	/**
	 * 计算md5值
	 * 
	 * @param s
	 * @return
	 */
	public final static String MD5Encode(String s) {
		return MD5Encode(s.getBytes());
	}

	/**
	 * 计算md5值
	 * 
	 * @param s
	 * @return
	 */
	public final static String MD5Encode(byte[] s) {
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(s);
			return digestToString(mdTemp.digest());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public final static String MD5Encode(InputStream input) throws RuntimeException {
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			int bufferSize = 1024;
			int length = 0;
			byte[] buffer = new byte[bufferSize];
			while ((length = input.read(buffer)) > 0) {
				mdTemp.update(buffer, 0, length);
			}
			return digestToString(mdTemp.digest());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public final static String digestToString(byte[] digest) {
		int j = digest.length;
        char[] str = new char[j * 2];
		int k = 0;
		for (int i = 0; i < j; i++) {
			byte byte0 = digest[i];
			str[k++] = hexDigits[byte0 >>> 4 & 0xf];
			str[k++] = hexDigits[byte0 & 0xf];
		}
		return new String(str);
	}

}
