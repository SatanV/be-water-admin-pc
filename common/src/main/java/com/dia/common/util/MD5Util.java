package com.dia.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class MD5Util {
	// 获得MD5摘要算法的 MessageDigest 对象
    private static MessageDigest MD5_INSTANCE;
    
    private static final char[] HEX_DIGITS = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
    
    static {
    	try {
			MD5_INSTANCE = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * MD5摘要
     * @param s
     * @return
     */
	public final static String MD5(String s) {
        try {
            byte[] btInput = s.getBytes();
            
            // 使用指定的字节更新摘要
            MD5_INSTANCE.update(btInput);
            
            // 获得密文
            byte[] md = MD5_INSTANCE.digest();
            
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = HEX_DIGITS[byte0 >>> 4 & 0xf];
                str[k++] = HEX_DIGITS[byte0 & 0xf];
            }
            
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	/**
     * MD5摘要
     * @param s
     * @return
     */
	public final static String MD5(String s,String charsetName) {
        try {
            byte[] btInput = s.getBytes(charsetName);
            
            // 使用指定的字节更新摘要
            MD5_INSTANCE.update(btInput);
            
            // 获得密文
            byte[] b = MD5_INSTANCE.digest();
            
            // 把密文转换成十六进制的字符串形式
            StringBuilder output = new StringBuilder(32);
	        for (int i = 0; i < b.length; i++) {
	            String temp = Integer.toHexString(b[i] & 0xff);
	            if (temp.length() < 2) {
	                output.append("0");
	            }
	            output.append(temp);
	        }
	        return output.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	/**
	 * 生成一个token
	 * @return
	 */
	public static String generateToken() {
		return MD5(String.valueOf(new Random().nextInt(Integer.MAX_VALUE)));
	}
	
//	public static void main(String[] args) {
//	    System.out.println(MD5("scadmin20161122"));
//
//		System.out.println(generateToken());
//        System.out.println(MD5Util.MD5("20121221SDFSDFDARFSDAFWRFWERWERWERWEDFBHETGREWQTRQRQEWRWEFASFDSGVFDGBVERTWRETRETGREGFGVFDGSDFEW顶戴要要"));
//        System.out.println(MD5Util.MD5("加密"));
//    }
}
