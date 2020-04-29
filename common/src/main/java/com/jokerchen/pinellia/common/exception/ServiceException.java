package com.jokerchen.pinellia.common.exception;

/**   
 * @description: 自定义业务异常，这里都定义为service层异常
 * @author Joker Chen
 * @date 2019-03-18 09:23:47  
 */
public class ServiceException extends RuntimeException {


	private static final long serialVersionUID = -2434996940080548758L;

	public ServiceException() {
		super();
	}

	/**
	 * @param 描述信息
	 */
	public ServiceException(String message) {
		super(message);
	}
}
