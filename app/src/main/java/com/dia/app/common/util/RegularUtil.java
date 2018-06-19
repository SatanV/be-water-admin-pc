package com.dia.app.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**   
* @author: Dia
* @Description: 正则表达式
* @version: v1.0.0
* @date: 2018年5月21日 下午5:33:23 
*/
public class RegularUtil {
	
	public static final boolean verify(String regularStr,String str) {
		Pattern pattern = Pattern.compile(regularStr);
		Matcher is = pattern.matcher(str);
		if(!is.matches()) {
			return false;
		}
		return true;
	}
	
}
