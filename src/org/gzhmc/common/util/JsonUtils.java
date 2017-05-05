package org.gzhmc.common.util;


import com.google.gson.Gson;

/**
*@Author : gcliang 
*@Date : 2016年6月8日
*Json 工具类
*/
public class JsonUtils {
	static Gson  gson =new Gson();
	
	/**
	 * Json 转 实体
	 * @param json
	 * @param clazz
	 * @return
	 */
    public static <T> T fromJson(String json, Class<? extends T> clazz) {
        if (json==null||json.length()==0) return null;
        try {
            return gson.fromJson(json, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * 实体转Json
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        return gson.toJson(object);
    }
}
