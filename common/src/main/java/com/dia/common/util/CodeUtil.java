package com.dia.common.util;

import java.util.Random;
import java.util.UUID;

/**   
* @author: Dia
* @Description: 代码工具类
* @version: v1.0.0
* @date: 2018年5月8日 下午5:44:06 
*/
public class CodeUtil {

	public static String getToken() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public static String getRandom() {
		Random random = new Random();
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < 6; i++) {
			buff.append(random.nextInt(10));
		}
		return buff.toString();
	}
}
