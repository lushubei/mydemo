package com.example.demo.common.exception;

/**
 * 业务异常类型
 * 
 */
public enum LogisticsBusinessExceptionCode {
	NOT_LOGIN("401", " 登录超时")
	, RPC_ERROR("-999998", " 远程调用异常")
	, SYSTEM_ERROR("-999999", " 系统异常")
	, VERIFY_STATUS_REJECT("000001", "审核不通过")
	, CAN_NOT_SEND_VERIFY_CODE("20018","60秒内不能重复发送验证码！")

	, WEICHAT_CONNECT_REFUSED("000002", "调用微信接口失败")
	, CODE_IS_WRONG("000003", "CODE参数错误,未能从微信取得信息")
	, NAME_IS_NULL("000004", "请输入正确的姓名!")
	, MOBILE_IS_WRONG("000004", "请输入正确的手机号码!")
	, IDENTITY_IS_WRONG("000005", "请输入正确的身份证号码!")
	, IDENTITY_FILE_IS_WRONG("000006", "请上传身份证照片!")
	, VEHICLE_NO_IS_WRONG("000007", "请输入正确的车牌号码!")
	, LICENSE_NO_IS_NULL("000008", "请上传行驶证照片!")
	, OPEN_ID_IS_EXIST("000009", "该微信已经注册过!")
	, MOBILE_IS_EXIST("000010", "该手机号已经注册过!")
	, IDENTITY_CODE_IS_EXIST("000011", "身份证号码已经注册过!")
	, MOBILE_IS_CONNECTED("000012", "本微信账号已经关联账户,请不要重复注册!")
	, LOGIN_NAME_IS_NULL("000013", "登录名为空!")
	, ORDER_DETAIL_IS_NULL("000014", "请录入商品!")
	, WAREHOUSE_ID_FROM_IS_NULL("000015", "请输入提货仓库!")
	, WAREHOUSE_ID_TO_IS_NULL("000016", "请输入目的地仓库!")
	, CATEGORY_ID_IS_NULL("000017", "请选择第%s行的货物名称!")
	, BRAND_IS_NULL("000017", "请输入第%s行的品牌!")
	, SIZE_IS_NULL("000018", "请输入第%s行的规格!")
	, COUNT_IS_NULL("000019", "请输入第%s行的件数!")
	, WEIGHT_IS_NULL("000020", "请输入第%s行的重量!")
	, VEHICLE_IS_NULL("000021", "请选择司机及车辆!")
	, LADING_TIME_IS_NULL("000022", "提货时间为空!")
	, PRODUCT_IS_NULL("000023", "请勾选商品!")
	, PRODUCT_COUNT_IS_NULL("000024", "请输入商品数量!")
	, WAREHOUSE_IS_USED("000025", "仓库已经使用过不能删除!")
	, WAREHOUSE_IS_USED_UPDATE("000025", "仓库已经使用过不能修改!")
	, MESSAGE_IS_FAILED("000026", "消息推送失败!")
	, CONSIGNER_IS_NULL("000027", "请输入货主公司名!")
	, VEHICLE_IS_NULL_ERROR("000028","车辆信息为空！")
	, VEHICLE_ADD_ERROR("000029","新增车辆信息不能为空！")
	, VEHICLE_ADD_FAILED("000030","新增车辆失败！")
	, USER_VEHICLE_REF_ADD_FAILED("000031","新增用户车辆关联表失败！")
	, VEHICLE_ADDSAME_FAILED("000032","同一个人不能添加相同的车牌的车辆！")
	, WAYBILL_IS_CHANGED("000033","订单信息已修改!")
	, WAYBILL_IS_CHANGED_2("000033","订单信息已修改,请刷新后再操作!")
	;

	/**
	 * 构造方法
	 * 
	 * @param code
	 * @param message
	 */
	private LogisticsBusinessExceptionCode(String code, String text) {
		this.code = code;
		this.text = text;
	}

	private static final String EMPTY = "";

	private String code;

	private String text;

	/**
	 * 通过code获取对应名称描述
	 * 
	 * @param code
	 * @return name
	 */
	public static String getText(String code) {
		if (code == null) {
			return EMPTY;
		}
		for (LogisticsBusinessExceptionCode em : LogisticsBusinessExceptionCode.values()) {
			if (em.code.equals(code)) {
				return em.text;
			}
		}
		return EMPTY;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
