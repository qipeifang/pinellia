package com.jokerchen.pinellia.console.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;

/**   
 * @description: 通用接口层，包含一些比较通用的访问接口，比如获取图片信息，微信登录等
 * @author Joker Chen 
 * @date 2019-04-24 19:00:17  
 */
@RequestMapping("/")
public interface CommonController {
	
	/**
	 * 将当前用户和微信标识绑定
	 * @param jsCode 获取微信账户信息时需要带上的编码
	 * @param encryptedData 加密的信息，带有unionId或者openId
	 * @param iv 密钥之一
	 */
	@RequestMapping("/common/bindWxId")
	void bindWxId(String jsCode, String encryptedData, String iv);
	
	/**
	 * 将当前登录的用户和微信标识解绑
	 */
	@RequestMapping("/common/unbindWxId")
	void unbindWxId();
	
	/**
	 * 获取图片信息，因为小程序访问图片时没办法带上session信息，所以需要自己在逻辑中处理
	 * @param request
	 * @param response
	 * @param imagePath
	 * @param sessionId
	 */
	@RequestMapping("/common/image")
	void image(HttpServletRequest request, HttpServletResponse response,
			String imagePath, String sessionId);
}
