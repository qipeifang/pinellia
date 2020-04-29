package com.jokerchen.pinellia.common.constant;

/**   
 * @description: 系统参数的编码
 * @author Joker Chen 
 * @date 2019-04-12 09:00:37  
 */
public class SysParamConstant {

	/** 用户初始密码的系统参数code */
	public static final String INIT_USER_PASSWORD = "INIT_USER_PASSWORD";
	
	/** 百度ocr 访问相关的参数 */
	public static final String baidu_ocr_access_token_url = "baidu_ocr_access_token_url";
	public static final String baidu_ocr_app_id = "baidu_ocr_app_id";
	public static final String baidu_ocr_api_key = "baidu_ocr_api_key";
	public static final String baidu_ocr_secret_key = "baidu_ocr_secret_key";
	/** 百度的ocr识别路径对应系统参数key的前缀 */
	public static final String baidu_ocr_url_prefix = "baidu_orc_id_car_url_";
	
	/** 微信小程序登录时需要的系统参数 */
	public static String wechat_app_id = "wechat_app_id";
	public static String wechat_jscode2session_url = "wechat_jscode2session_url";
	public static String wechat_secret = "wechat_secret";
	
	/** window系统下图片的保存路径 */
	private static final String window_image_url = "window_image_url";
	/** Linux系统下图片的保存路径 */
	private static final String linux_image_url = "linux_image_url";
	
	/**
	 * 获取系统保存图片的路径地址的编码
	 * @return
	 */
	public static String getImageUrlCode() {
		String os = System.getProperty("os.name");  
		if(os.toLowerCase().startsWith("win")){  
			return window_image_url;
		}
		return linux_image_url;
	}
}
