package com.jokerchen.pinellia.console.common.service;

/**
 * @description: 通用逻辑
 * @author Joker Chen
 * @date 2019-06-06 16:00:56
 */
public interface CommonService {

	/**
	 * 将当前用户和微信标识绑定
	 * @param jsCode 获取微信账户信息时需要带上的编码
	 * @param encryptedData 加密的信息，带有unionId或者openId
	 * @param iv 密钥之一
	 */
	void bindWxId(String jsCode, String encryptedData, String iv);
	
	/**
	 * 将当前登录的用户和微信标识解绑
	 */
	void unbindWxId();
	
	/**
	 * 获取微信标识
	 * @param jsCode 获取微信账户信息时需要带上的编码
	 * @param encryptedData 加密的信息，带有unionId或者openId
	 * @param iv 密钥之一
	 */
	String getWxId(String jsCode, String encryptedData, String iv);
}
