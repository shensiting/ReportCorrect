package org.gzhmc.common.base;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis.utils.StringUtils;
import org.gzhmc.common.util.JsonUtils;

/**
*@Author : gcliang 
*@Date : 2016年6月8日
*/
public class BaseController {
	
	//数据库操作成功
	public final static int RESULT_ONE=1;
	//数据库操作失败
	public final static int RESULT_NONE=0;
	
		
	/**
	 * 将对象转换成JSON字符串，并响应回前台
	 * @param response
	 * @param obj
	 */
	public void writeResultJson(HttpServletResponse response,Object obj){
		try {
			String json = JsonUtils.toJson(obj);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * get请求取值,预防乱码问题
	 * @param key
	 * @return
	 */
	public String getParameter(HttpServletRequest request ,String key){
		String val = request.getParameter(key);
		if (StringUtils.isEmpty(val)) {
			return null;
		}
		String result= null;
		try {
			result = new String(val.getBytes(),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}  
		return result;
	}
}
