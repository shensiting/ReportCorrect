package org.gzhmc.common.exception;



/**
*@Author : gcliang 
*@Date : 2016年6月23日
*/
public class WebException extends Exception{
	final static String SERVER_ERROR="服务器异常，正在拼命修复，请稍后再试...";
	//异常信息
	private String msg ; 
	
	public WebException(String msg){
			this.msg=msg;
	}
	public WebException(){
		this.msg=SERVER_ERROR;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
