package com.jokerchen.pinellia.common.constant;

/**   
 * @description: 定义返回的数据信息
 * @author Joker Chen 
 * @date 2019-03-13 13:03:59  
 */
public enum ResponseEnum {
	SUCCESS("200","成功"),
	LOGIN_FAIL("400","用户名或密码错误"),
	UNAUTHORIZED("401","没有访问权限"),
	FORBIDDEN("403","未登录或会话已过期")
	;
	private final String code;
	private final String message;
	
	ResponseEnum(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public String code() {
		return this.code;
	}
	
	public String message() {
		return this.message;
	}
}
