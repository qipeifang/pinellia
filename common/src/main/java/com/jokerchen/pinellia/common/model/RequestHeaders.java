package com.jokerchen.pinellia.common.model;

import lombok.Data;

/**   
 * @description: 发起http请求时，创建连接信息时用到的一些信息
 * @author Joker Chen 
 * @date 2019-03-25 09:54:32  
 */
@Data
public class RequestHeaders {

	/** 
	 * 模拟的浏览器版本 
	 * 
	 */
	private String userAgent;
	/** 
	 * 客户端接受什么类型的响应
	 * 默认application/json, text/javascript, *\/*; q=0.01
	 */
	private String accept;
	/** 浏览器可接受的语言 */
	private String acceptLanguage;
	/** 指定浏览器可以支持的web服务器返回内容压缩编码类型 */
	private String acceptEncoding;
	/** 
	 * 请求是从哪个URL过来的
	 */
	private String referer;
	/** 
	 * 用于保持登录信息时，为请求带上cookies信息
	 */
	private String cookies;
	/** 表示是否需要持久连接，如果是一次性请求不需要，如果是保持登录请求，需要 */
	private String connection;
	/** 向服务器指定某种传输协议以便服务器进行转换（如果支持） */
	private String upgradeInsecureRequests;

	public RequestHeaders() {
		this.userAgent = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36";
		this.accept = "application/json, text/javascript, */*; q=0.01";
		this.acceptLanguage = "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3";
		this.acceptEncoding = "gzip, deflate";
		this.referer = "http://www.jokerchen.com/";
		this.connection = "Keep-Alive";
		this.upgradeInsecureRequests = "1";
	}
	
	public RequestHeaders(String cookies) {
		this();
		this.cookies = cookies;
	}

	public RequestHeaders(String userAgent, String accept, String acceptLanguage, String acceptEncoding,
			String referer, String cookies, String connection, String upgradeInsecureRequests) {
		super();
		this.userAgent = userAgent;
		this.accept = accept;
		this.acceptLanguage = acceptLanguage;
		this.acceptEncoding = acceptEncoding;
		this.referer = referer;
		this.cookies = cookies;
		this.connection = connection;
		this.upgradeInsecureRequests = upgradeInsecureRequests;
	}
	
}
