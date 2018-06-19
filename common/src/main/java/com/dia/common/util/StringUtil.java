package com.dia.common.util;

/**
 * 字符串工具类
 * 
 * @author wcy
 * @createdate 2016-05-30 09:38
 * 
 * @UpdateUser:
 * @UpdateDate: 
 * @UpdateRemark:
 * 
 * @version 1.0
 *
 */
public class StringUtil {
	
	/**
	 * 将字节数组转换成十六进制字符串
	 * 
	 * @author wcy
	 * @createdate 2016-05-30 09:31
	 * 
	 * @param bArray
	 * @return
	 */
	public static final String bytesToHexString(byte[] bArray) {
	    StringBuffer sb = new StringBuffer(bArray.length);
	    String sTemp;
	    for (int i = 0; i < bArray.length; i++) {
	        sTemp = Integer.toHexString(0xFF & bArray[i]);
	   
	        if (sTemp.length() < 2) {
	        	sb.append(0);
	        }
          
	        sb.append(sTemp.toUpperCase());
	    }
	    
	    return sb.toString();
	}
	
	/**
	 * 将十六进制字符串转换成字节数组
	 * 
	 * @author wcy
	 * @createdate 2016-05-30 09:31
	 * 
	 * @param hex
	 * @return
	 */
	public static byte[] hexStringToByte(String hex) {
	    int len = (hex.length() / 2);
	    byte[] result = new byte[len];
	    char[] achar = hex.toCharArray();
	    for (int i = 0; i < len; i++) {
	        int pos = i * 2;
	        result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
	    }
	    return result;
	}
	
	/**
	 * 按系统统一编码将字节数组转换成字符串
	 * @param bs
	 * @return
	 * @throws Exception
	 */
	public static String getBytes(byte[] bs) throws Exception {
		return new String(bs, Constants.CHARSET_UTF8);
	}
	
	/**
	 * 按系统统一编码将字符串转换成字节数组
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static byte[] getBytes(String str) throws Exception {
		return str.getBytes(Constants.CHARSET_UTF8);
	}
	
	public static int toByte(char c) {
	    byte b = (byte) "0123456789ABCDEF".indexOf(c);
	    return b;
	}
	
	/**
	 * 多个字符串连接
	 * 
	 * @author wcy
	 * @createdate 2016-06-20 11:33
	 * 
	 * @param objs
	 * @return
	 */
	public static String concat(Object ... objs) {
		StringBuilder sb = new StringBuilder(50);
		
		for(Object str : objs) {
			if(str!=null) {
				sb.append(str);
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * 多个字符串连接
	 * 
	 * @author wcy
	 * @createdate 2016-06-24 09:10
	 * 
	 * @param strs
	 * @return
	 */
	public static String concat(String ... strs) {
		
		int length = 0;
		for(String str : strs) {
			if(str!=null) {
				length += str.length();
			}
		}
		
		StringBuilder sb = new StringBuilder(length);
		
		for(String str : strs) {
			if(str!=null) {
				sb.append(str);
			}
		}
		
		return sb.toString();
	}
}
