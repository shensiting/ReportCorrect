package org.gzhmc.common.util;

import java.util.Random;

/**
*@Author : gcliang 
*@Date : 2016年6月7日
*/
public class StringUtils {
	
	/**
	 * 判断字符串是否为空
	 * @param string
	 * @return
	 */
	static public boolean isNotEmpty(String string){
		if (string==null || "".equals(string) || " ".equals(string)){
			return false;
		}
		return true;
	}
	/**
	 * string to int
	 * @param str
	 * @return
	 */
	static public int string2int(String str){
		Integer i;
		try{
			i = Integer.parseInt(str);
		}catch(Exception e){
			e.printStackTrace();
			i=0;
		}
		return i;
	}
	/**
	 * string to double
	 * @param str
	 * @return
	 */
	static public double string2double(String str){
		double i;
		try{
			i = Double.parseDouble(str);
		}catch(Exception e){
			e.printStackTrace();
			i=0;
		}
		return i;
	}

	/**
	 * 获取字符串中的数字
	 * 
	 * @param string
	 * @return
	 */
	public static String stringgetnum(String string) {
		string = string.trim();
		String string2 = "";
		if (string != null && !"".equals(string)) {
			for (int i = 0; i < string.length(); i++) {
				if (string.charAt(i) >= 48 && string.charAt(i) <= 57) {
					string2 += string.charAt(i);
				}
			}
			}
        return string2;
	}
	/**
	 * 获取三位随机数
	 * @return
	 */
	public static String getrandom() {
		String string="";
		Random random=new Random();
		for(int i=0;i<3;i++){
			string+=random.nextInt(10);
		}
		return string;
	}	
	
	/**
	 * 检查身份证号是否合格
	 */
	public static boolean isIdcard(String idcard) {
		char a;
		if(idcard.length()!=18){
			return false;
		}
		for(int i=0;i<idcard.length();i++){
			a=idcard.charAt(i);			
			if(!(a>='0'&&a<='9')&&!(a>='a'&&a<='z')&&!(a>='A'&&a<='Z')){
				return false;
			}
		}		
		return true;
	}
	
}
