package com.dia.common.util;

/**   
* @author: 
* @Description: 密码加密工具类
* @version: v1.0.0
* @date: 2018年5月8日 下午1:20:20 
*/
public class EncryptUtil {
	/// <summary>
    /// -加密字符串(对汉字不起作用),重复加密一次即可解密
    /// -参  数:mstext-需加密的字符串,CodeWord-密钥
    /// -返回值:加密后的字符串
    /// </summary>
    /// <param name="mstext"></param>
    /// <param name="CodeWord"></param>
    /// <returns></returns>
    public static String EncryptEx(String mstext){
    	String CodeWord = "RegentSoft";
        //用于数据连接
        //string Matrix = @"8x3p5BeabcdfghijklmnoqrstuvwyzACDEFGHIJKLMNOPQRSTUVWXYZ 1246790-.#/\!@$<>&*()[]{};:,?=+~`^|%_";

        //用于登录系统
        String Matrix = "8x3p5BeabcdfghijklmnoqrstuvwyzACDEFGHIJKLMNOPQRSTUVWXYZ 1246790-.#/\\!@$<>&*()[]{}';:,?=+~`^|%_";
        Matrix += "\r";//13  //Chr(13);//Add Carriage Return to Matrix
        Matrix += "\n";//Chr(10); //Add Line Feed to Matrix			
        Matrix += "\"";//Chr(34);//Add "
        // Unique String used to make Matrix - 8x3p5Be
        // Unique String can be any combination that has a character only ONCE.
        // EACH Letter in the Matrix is Input ONLY once.

        int W = 1;
        int LAM = Matrix.length();
        String[] strCryptMatrix = new String[98];
        strCryptMatrix[1] = Matrix;
        String mov1, mov2;
        for (int x = 2; x <= LAM; x++)
        {
            mov1 = strCryptMatrix[W].substring(0, 1);
            mov2 = strCryptMatrix[W].substring(1);
            strCryptMatrix[x] = mov2 + mov1;		//'Makes up each row of the Array
            W = W + 1;
        }
        String Str2Encrypt = mstext;
        int LS2E = mstext.length();
        int LCW = CodeWord.length();
        String EncryptedLetter = "";
        String EncryptedString = "";

        int Y = 0;
        String C2E;
        int MP;
        String CWL;
        for (int x = 0; x < LS2E; x++)
        {
            C2E = Str2Encrypt.substring(x, x+1);
            MP = Matrix.indexOf(C2E, 0);
            //MP = InStr(1, Matrix, C2E, 0)
            CWL = CodeWord.substring(Y, Y+1);
            for (int z = 1; z <= LAM; z++)
            {
                if (strCryptMatrix[z].substring(MP, MP+1).equals(CWL))
                {
                    EncryptedLetter = strCryptMatrix[z].substring(0, 1);
                    EncryptedString = EncryptedString + EncryptedLetter;
                    break;
                }
            }
            Y = Y + 1;
            if (Y >= LCW) {
            	Y = 0;
            }
                
        }
        return EncryptedString;
    }
    public static void main(String[] args) {
		System.out.println(EncryptUtil.EncryptEx("123456"));
		System.out.println(EncryptUtil.EncryptEx(EncryptUtil.EncryptEx("123456")));
		
	}
}
