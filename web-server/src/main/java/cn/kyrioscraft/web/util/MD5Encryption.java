package cn.kyrioscraft.web.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Encryption {
	/**
	 * 
	 * @param originalStr 要加密的字符串
	 * @return 加密后的字符串
	 */
	public final static String encryption(String originalStr){
		try {
			byte[] strTemp = originalStr.getBytes();
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(strTemp);
			byte[] bin = messageDigest.digest();
			return byte2HexStr(bin);
		} catch (NoSuchAlgorithmException e){
			e.printStackTrace();
			
		} catch (Exception e){
			e.printStackTrace();
			
		}
		return null;
	}
	/**
	 * 把二进制数组转化为16进制字符串
	 * @param b 二进制数组
	 * @return 转换后的16进制字符串
	 */
	 public static String byte2HexStr(byte[] b){
		 StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < b.length; i++) {
				String plainText = Integer.toHexString(0xff & b[i]);
				if (plainText.length() < 2)
					plainText = "0" + plainText;
				hexString.append(plainText);
			}

			return hexString.toString();
	}
	 /**
		 * 原始MD5加密
		 * @param inStr 要加密的字符串
		 * @return 加密后的字符串
		 */
	 public static String md5Encode(String inStr){
	        MessageDigest md5 = null;
	        try {
	            md5 = MessageDigest.getInstance("MD5");
	            byte[] byteArray = inStr.getBytes("UTF-8");
		        byte[] md5Bytes = md5.digest(byteArray);
		        StringBuffer hexValue = new StringBuffer();
		        for (int i = 0; i < md5Bytes.length; i++) {
		            int val = ((int) md5Bytes[i]) & 0xff;
		            if (val < 16) {
		                hexValue.append("0");
		            }
		            hexValue.append(Integer.toHexString(val));
		        }
		        return hexValue.toString();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return "";
	    }
}

