package com.example.imakeyouth.vo.resp;

/**
 *
 *
 */
@SuppressWarnings("rawtypes")
public class GatewayResp<T> {

	private String code = "000000";

	private String msg = "成功";

	private boolean success = true;

	private String timeStamp = System.currentTimeMillis() + "";

	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	/**
	 * 构造方法
	 */
	public GatewayResp() {
	}

	public GatewayResp(T t) {
		this.data = t;
	}

	public GatewayResp(String code, String msg, boolean success) {
		this.code = code;
		this.msg = msg;
		this.success = success;
	}


	/**
	 * 获取code
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 设置code
	 *
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 获取msg
	 *
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * 设置msg
	 *
	 * @param msg
	 *            the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * 获取success
	 *
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * 设置success
	 *
	 * @param success
	 *            the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
}
