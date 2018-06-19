package com.dia.common.util;

/**
 * <p>
 * RSA公钥/私钥/签名工具包
 * <p>
 * 字符串格式的密钥在未在特殊说明情况下都为BASE64编码格式<br/>
 * 由于非对称加密速度极其缓慢，一般文件不使用它来加密而是使用对称加密，<br/>
 * 非对称加密算法可以用来对对称加密的密钥加密，这样保证密钥的安全也就保证了数据的安全
 * </p>
 * 
 */
public class RSAUtil {

//	/**
//	 * 加密算法RSA
//	 */
//	public static final String KEY_ALGORITHM = "RSA";
//
//	/**
//	 * 签名算法
//	 */
//	public static final String SIGNATURE_ALGORITHM = "MD5withRSA";
//
//	/**
//	 * 获取公钥的key
//	 */
//	private static final String PUBLIC_KEY = "LocatorPublicKey";
//
//	/**
//	 * 获取私钥的key
//	 */
//	private static final String PRIVATE_KEY = "LocatorPrivateKey";
//
//	/**
//	 * RSA最大加密明文大小
//	 */
//	private static final int MAX_ENCRYPT_BLOCK = 117;
//
//	/**
//	 * RSA最大解密密文大小
//	 */
//	private static final int MAX_DECRYPT_BLOCK = 128;
//
//	public static void main(String[] args) throws Exception {
//		String s = "{\"username\":\"zhouyansheng\",\"password\":\"123456\",\"clienttime\":\"3600\"}";
//
//		System.out.println(s);
//		byte[] encript = encryptByPublicKey(StringUtil.getBytes(s), "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCxFSBjkxIFuG4zsXm1Oqw08apgA6RXTK/qEuy2KibEGEjAw78azH1fPuAkOzJaqtLYPzffeWkncRFCXXsD+F/OG2z8KOzJ/z1ccR4SGrzbK9GI70eG73Af+Do3nw9N/qkfOrG60EcehFm6K2p/nf6bhwjFQJX3IByoGE4BnTTzgQIDAQAB");
//
//		System.out.println(StringUtil.bytesToHexString(encript));
//
//		System.out.println(new String(RSAUtil.decryptByPrivateKey(encript, "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALEVIGOTEgW4bjOxebU6rDTxqmADpFdMr+oS7LYqJsQYSMDDvxrMfV8+4CQ7Mlqq0tg/N995aSdxEUJdewP4X84bbPwo7Mn/PVxxHhIavNsr0YjvR4bvcB/4OjefD03+qR86sbrQRx6EWboran+d/puHCMVAlfcgHKgYTgGdNPOBAgMBAAECgYBTcwVEZ3ZCw9sK+UPFp9SnTotfm9fCXB06TZjHb629E0SVsqlIU71xOQwfhtX/7ChYR9JouapYL+xWPf9Oq6lZigmY2C64badX12Hqugk8GfFGDFvLJI/sevsA9RJnYsFlrgaTa/Y+b3+4OHWGzby+OYoi5YM038CxIGYhEah/AQJBAOk0PyABo+/aQQJkAkqdphEanBIwhwqwEMlU6JkiykWSp2W9rxW6mpj7riKZPY3KaY7OaA3QL1WzuQj/qRDumUkCQQDCZHsa9Y4AiPWUHNEl3U7fptgxlw2vAvXLZds1HFJ56yERB8uEW46D1eBVOm+gThcxjWJGL0wX0SMKNMUreoB5AkEAhyg3ubYZrGaDLP6HcXVjDSCt+cDUU3ZtlDI4yKAoOSBhEo9YDTDkbuvXJchIq4f3m8O2/OJ+vJ34DOYXt2+BuQJAFnr+TZjWKjOsistmWIWmaHDoEZwRZgrml/YYiqTS2OybL1gCbjUUXyStw811OUmunvllww4XM63o6nY90xBgqQJAA71EpJQ4s66aneGnYdrHiiO1G11/FstWw5KLHCl1s7rQqhxrTy331v+1h8MY8cTgkWzdttHG4MtZPh/w/cZQSA==")));
//
///*		String s = "34DF71B1E92153D1A8A4FC0C2463C31E423B1284E3706D1F5466CD5C349132355C694F51E27535834D85A6A312D534AEAF599468DBD9B7E95F606699476A11D7971881E9ACC72D5B811556AF7F0232EDF2EE676DEBC548EBF099C9BA27A0F869631DC8C9D8DD5325B923CCDE326C2427E08CBF3355C5BE022D267F9D35BED90A843E87CA0FC73901786B4B8ABD7CF3B803C9850B291CD5C38D102E577DFBB346F3E97C7E1072FCA05568A09A063EC53526E1AA3C64A2E779CFE94105FC2E3B01EACA66CCB6DA1FA462AA7514BCC749A92040B4388C05CA1537A3CB1726843AD072D9C614C3AEB4071BEB7FEF67F082D007EA42CD0AAC4ECD8157445A99A8136473C1172788F4F701C30D72326751EF549F696F91ED9FCE2CCAFF16E808A8B80AEECCE3E07E9432716703BF927E1254E05D79EE8BEE00D71437655B690882F8283A6DF49F16F81959A3C97CCB6E9DC9BED14876FEF0F39E5E30EF5E644C50767E481607BCF03BD35B4AFF0ED9D68C2E2CF7C12BA5BAAD86D9F894F7047AFF73F05E4BF023A6819A1117DE607C4052EB2F2D1B0C7A017A5F9EDA90FC7D3EB79DBD823DB2FA77E7928551DA9A9CD409CD8AB45D1BF77E5A2B2EBA4D1FDD27B4C5F3DE300CDA47ABD156E26E86474D808DF751409861048343CDE8781E381A437AF0261877CF6BF6CC8617F174A57A19EBEE6133958009716AC4407613F39BC8067F7DD0E2F8EF51A46B4BA6F595836C52C5EF82745DFD82CDC28EE854074A439F8092AC12306E84584ACCB38639403E045BF3F878B453F362D3D284B6A1ED9F20F2D87940C8BA49B6B84D91B16D5EC13C0E2E29513E6BAE84B1A49844433D4345C0760A0E3755E7A56B0AC654DED22CA780B4047DF19DEF2C707BF99E164F0E665968D757BA4998684DFAEAFDA3BD0D291720657D9775D07B55D3080DB0BEC8961675D998EDE7B1A61FBDCD96AB7934E09BB9ED06B979526D99269EDEB69221AA2188BF4272EF50B5D1EC43D4A9E8C1FDC04726005422BC8AA67B958D48B6B9586F200611FC241D5876CD7A877F8CE17059359059ADD72DEF858C5188EC139F93581A7DCB6AC1C42B2D76D0887E65DAEECD3811EA588A5D734B2649E7F2402E7CB5770099CF962EEAE53145D6CB49E3AA385AD6031B486A56A0A9F3D8D5015813A4D436D29B5EDDB3C5350B85CE63259231BA2F33B9B8DF189C51399FDB56CCD0E7192E21506890F16A2AC7651AC627044039ECCC328468F2E49EE9990AA960EBC8";
//
//		System.out.println(s.length());
//
//		Map<String, Object> keyPair = genKeyPair();
//
//
//		String publicKey = getPublicKey(keyPair);
//		System.out.println("公钥:");
//		System.out.println(publicKey);
//
//		String privateKey = getPrivateKey(keyPair);
//		System.out.println("私钥:");
//		System.out.println(privateKey);
//
//		String content = "加密内容xvgvzgvsdfsf佚要sdffsdfs加密内容xvgvzgvsdfsf佚要sdffsdfs加密内容xvgvzgvsdfsf佚要sdffsdfs加密内容xvgvzgvsdfsf佚要sdffsdfs加密内容xvgvzgvsdfsf佚要sdffsdfs加密内容xvgvzgvsdfsf佚要sdffsdfs加密内容xvgvzgvsdfsf佚要sdffsdfs加密内容xvgvzgvsdfsf佚要sdffsdfs加密内容xvgvzgvsdfsf佚要sdffsdfs加密内容xvgvzgvsdfsf佚要sdffsdfs加密内容xvgvzgvsdfsf佚要sdffsdfs加密内容xvgvzgvsdfsf佚要sdffsdfs加密内容xvgvzgvsdfsf佚要sdffsdfs加密内容xvgvzgvsdfsf佚要sdffsdfs加密内容xvgvzgvsdfsf佚要sdffsdfs加密内容xvgvzgvsdfsf佚要sdffsdfs加密内容xvgvzgvsdfsf佚要sdffsdfs加密内容xvgvzgvsdfsf佚要sdffsdfs加密内容xvgvzgvsdfsf佚要sdffsdfs加密内容xvgvzgvsdfsf佚要sdffsdfs加密内容xvgvzgvsdfsf佚要sdffsdfs";
//
//		byte[] encript = encryptByPublicKey(content.getBytes(), publicKey);
//		System.out.println("加密后内容:");
//		System.out.println(StringUtil.bytesToHexString(encript));
//
//		byte[] decript = decryptByPrivateKey(StringUtil.hexStringToByte(StringUtil.bytesToHexString(encript)), privateKey);
//
//		System.out.println(new String(decript));*/
//	}
//
//	/**
//	 * <p>
//	 * 生成密钥对(公钥和私钥)
//	 * </p>
//	 *
//	 * @return
//	 * @throws Exception
//	 */
//	public static Map<String, Object> genKeyPair() throws Exception {
//		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
//		keyPairGen.initialize(1024);
//
//		KeyPair       keyPair    = keyPairGen.generateKeyPair();
//		RSAPublicKey  publicKey  = (RSAPublicKey)  keyPair.getPublic();
//		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
//
//		Map<String, Object> keyMap = new HashMap<String, Object>(2);
//		keyMap.put(PUBLIC_KEY, publicKey);
//		keyMap.put(PRIVATE_KEY, privateKey);
//
//		return keyMap;
//	}
//
//	/**
//	 * <p>
//	 * 用私钥对信息生成数字签名
//	 * </p>
//	 *
//	 * @param data
//	 *            已加密数据
//	 * @param privateKey
//	 *            私钥(BASE64编码)
//	 *
//	 * @return
//	 * @throws Exception
//	 */
//	public static String sign(byte[] data, String privateKey) throws Exception {
//		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(AndroidBase64Utils.decode(privateKey));
//		KeyFactory          keyFactory   = KeyFactory.getInstance(KEY_ALGORITHM);
//		PrivateKey          privateK     = keyFactory.generatePrivate(pkcs8KeySpec);
//
//		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
//		signature.initSign(privateK);
//		signature.update(data);
//
//		return AndroidBase64Utils.encode(signature.sign());
//	}
//
//	/**
//	 * <p>
//	 * 校验数字签名
//	 * </p>
//	 *
//	 * @param data
//	 *            已加密数据
//	 * @param publicKey
//	 *            公钥(BASE64编码)
//	 * @param sign
//	 *            数字签名
//	 *
//	 * @return
//	 * @throws Exception
//	 *
//	 */
//	public static boolean verify(byte[] data, String publicKey, String sign) throws Exception {
//		X509EncodedKeySpec keySpec    = new X509EncodedKeySpec(AndroidBase64Utils.decode(publicKey));
//		KeyFactory         keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
//		PublicKey          publicK    = keyFactory.generatePublic(keySpec);
//
//		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
//		signature.initVerify(publicK);
//		signature.update(data);
//
//		return signature.verify(AndroidBase64Utils.decode(sign));
//	}
//
//	/**
//	 * <P>
//	 * 私钥解密
//	 * </p>
//	 *
//	 * @param encryptedData
//	 *            已加密数据
//	 * @param privateKey
//	 *            私钥(BASE64编码)
//	 * @return
//	 * @throws Exception
//	 */
//	public static byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey) throws Exception {
//		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(AndroidBase64Utils.decode(privateKey));
//		KeyFactory          keyFactory   = KeyFactory.getInstance(KEY_ALGORITHM);
//		Key                 privateK     = keyFactory.generatePrivate(pkcs8KeySpec);
//
//		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
//		cipher.init(Cipher.DECRYPT_MODE, privateK);
//
//		ByteArrayOutputStream out = null;
//
//		try {
//			out = new ByteArrayOutputStream();
//
//			int    inputLen = encryptedData.length;
//			int    offSet   = 0;
//			byte[] cache    = null;
//			int    index    = 0;
//
//			// 对数据分段解密
//			while (inputLen - offSet > 0) {
//				if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
//					cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
//				} else {
//					cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
//				}
//
//				out.write(cache, 0, cache.length);
//
//				index++;
//				offSet = index * MAX_DECRYPT_BLOCK;
//			}
//
//			return out.toByteArray();
//		} finally {
//			if(out!=null) {
//				out.close();
//			}
//		}
//	}
//
//	/**
//	 * <p>
//	 * 公钥解密
//	 * </p>
//	 *
//	 * @param encryptedData
//	 *            已加密数据
//	 * @param publicKey
//	 *            公钥(BASE64编码)
//	 * @return
//	 * @throws Exception
//	 */
//	public static byte[] decryptByPublicKey(byte[] encryptedData, String publicKey) throws Exception {
//		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(AndroidBase64Utils.decode(publicKey));
//		KeyFactory         keyFactory  = KeyFactory.getInstance(KEY_ALGORITHM);
//		Key                publicK     = keyFactory.generatePublic(x509KeySpec);
//
//		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
//		cipher.init(Cipher.DECRYPT_MODE, publicK);
//
//		ByteArrayOutputStream out = null;
//
//		try {
//			out = new ByteArrayOutputStream();
//
//			int    inputLen = encryptedData.length;
//			int    offSet   = 0;
//			byte[] cache    = null;
//			int    index    = 0;
//
//			// 对数据分段解密
//			while (inputLen - offSet > 0) {
//				if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
//					cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
//				} else {
//					cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
//				}
//
//				out.write(cache, 0, cache.length);
//				index++;
//				offSet = index * MAX_DECRYPT_BLOCK;
//			}
//
//			return out.toByteArray();
//		} finally {
//			if(out!=null) {
//				out.close();
//			}
//		}
//	}
//
//	/**
//	 * <p>
//	 * 公钥加密
//	 * </p>
//	 *
//	 * @param data
//	 *            源数据
//	 * @param publicKey
//	 *            公钥(BASE64编码)
//	 * @return
//	 * @throws Exception
//	 */
//	public static byte[] encryptByPublicKey(byte[] data, String publicKey) throws Exception {
//		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(AndroidBase64Utils.decode(publicKey));
//		KeyFactory         keyFactory  = KeyFactory.getInstance(KEY_ALGORITHM);
//		Key                publicK     = keyFactory.generatePublic(x509KeySpec);
//
//		// 对数据加密
//		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
//		cipher.init(Cipher.ENCRYPT_MODE, publicK);
//
//		ByteArrayOutputStream out = null;
//
//		try {
//            out = new ByteArrayOutputStream();
//
//            int    inputLen = data.length;
//    		int    offSet   = 0;
//    		byte[] cache    = null;
//    		int    index    = 0;
//
//    		// 对数据分段加密
//    		while (inputLen - offSet > 0) {
//    			if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
//    				cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
//    			} else {
//    				cache = cipher.doFinal(data, offSet, inputLen - offSet);
//    			}
//
//    			out.write(cache, 0, cache.length);
//    			index++;
//    			offSet = index * MAX_ENCRYPT_BLOCK;
//    		}
//
//    		return out.toByteArray();
//		} finally {
//			if(out!=null) {
//				out.close();
//			}
//		}
//
//
//
//	}
//
//	/**
//	 * <p>
//	 * 私钥加密
//	 * </p>
//	 *
//	 * @param data
//	 *            源数据
//	 * @param privateKey
//	 *            私钥(BASE64编码)
//	 * @return
//	 * @throws Exception
//	 */
//	public static byte[] encryptByPrivateKey(byte[] data, String privateKey) throws Exception {
//		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(AndroidBase64Utils.decode(privateKey));
//		KeyFactory          keyFactory   = KeyFactory.getInstance(KEY_ALGORITHM);
//		Key                 privateK     = keyFactory.generatePrivate(pkcs8KeySpec);
//
//		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
//		cipher.init(Cipher.ENCRYPT_MODE, privateK);
//
//		ByteArrayOutputStream out = null;
//
//		try {
//			out = new ByteArrayOutputStream();
//
//			int    inputLen = data.length;
//			int    offSet   = 0;
//			byte[] cache    = null;
//			int    index    = 0;
//
//			// 对数据分段加密
//			while (inputLen - offSet > 0) {
//				if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
//					cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
//				} else {
//					cache = cipher.doFinal(data, offSet, inputLen - offSet);
//				}
//
//				out.write(cache, 0, cache.length);
//				index++;
//				offSet = index * MAX_ENCRYPT_BLOCK;
//			}
//
//			return out.toByteArray();
//		} finally {
//			if(out!=null) {
//				out.close();
//			}
//		}
//	}
//
//	/**
//	 * <p>
//	 * 获取私钥
//	 * </p>
//	 *
//	 * @param keyMap
//	 *            密钥对
//	 * @return
//	 * @throws Exception
//	 */
//	public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
//		Key key = (Key) keyMap.get(PRIVATE_KEY);
//		return AndroidBase64Utils.encode(key.getEncoded());
//	}
//
//	/**
//	 * <p>
//	 * 获取公钥
//	 * </p>
//	 *
//	 * @param keyMap
//	 *            密钥对
//	 * @return
//	 * @throws Exception
//	 */
//	public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
//		Key key = (Key) keyMap.get(PUBLIC_KEY);
//		return AndroidBase64Utils.encode(key.getEncoded());
//	}
}
