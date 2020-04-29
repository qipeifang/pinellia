package com.jokerchen.pinellia.common.model;

import lombok.Data;

/**   
 * @description: 统一整个系统返回的数据格式，用于前后端能够统一处理异常信息
 * @author Joker Chen 
 * @date 2019-03-13 11:27:12  
 */
@Data
public class ResponseMessage {
	
	/**
	 * 返回的状态码
	 * 成功时默认返回0，异常时，返回各个异常码
	 */
	private String code;
	
	/**
	 * 返回的状态信息，如果是异常时，则为异常信息
	 */
	private String message;
	
	/**
	 * 实际操作时返回的结果信息
	 */
	private Object data;
	
	public ResponseMessage() {}
	
	public ResponseMessage(String code,String message) {
		this.code = code;
		this.message = message;
	}

}
