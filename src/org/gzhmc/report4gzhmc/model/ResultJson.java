package org.gzhmc.report4gzhmc.model;

/**
 * @Author : gcliang
 * @Date : 2016年6月22日 回传结果实体
 */
public class ResultJson {
	private String msg;// 信息
	private boolean success = true; // 默认是true
	private Object obj;// 对象实体

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

}
