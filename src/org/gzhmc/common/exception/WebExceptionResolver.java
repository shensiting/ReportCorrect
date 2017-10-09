package org.gzhmc.common.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
*@Author : gcliang 
*@Date : 2016年6月23日
*/
public class WebExceptionResolver implements HandlerExceptionResolver{
	
	//接收系统抛出的异常
	@Override
	public ModelAndView resolveException(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
			Exception e) {
		// TODO Auto-generated method stub
		WebException exception = null;
		if (e instanceof WebException){//判断类型
			exception=(WebException)e;
			
		}else{
			exception=new WebException();
		}
		
		return new ModelAndView("common/error").addObject("msg", exception.getMsg());
	}

}
