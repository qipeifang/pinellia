package com.jokerchen.pinellia.console.common.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.jokerchen.pinellia.common.constant.SysParamConstant;
import com.jokerchen.pinellia.common.sysParam.service.SysParamService;
import com.jokerchen.pinellia.common.util.StringUtil;
import com.jokerchen.pinellia.common.util.WechatUtil;
import com.jokerchen.pinellia.console.common.service.CommonService;
import com.jokerchen.pinellia.console.login.SecurityUtil;
import com.jokerchen.pinellia.console.user.entity.User;
import com.jokerchen.pinellia.console.user.service.UserService;

import lombok.extern.slf4j.Slf4j;
import net.dongliu.requests.Requests;

/**   
 * @description: 
 * @author Joker Chen 
 * @date 2019-06-06 16:00:45  
 */
@Slf4j
@Service
public class CommonServiceImpl implements CommonService{

	@Autowired
	private UserService userService;
	
	@Autowired
	private SysParamService sysParamService;
	
	
	public String getWxId(String jsCode, String encryptedData, String iv) {
		if(StringUtil.isEmpty(jsCode)) {
			//jsCode 为空，直接返回
			return null;
		}
		//需先调用微信的接口获取unionId，如果获取不到unionId，则使用openId
		//调用时需要用到的参数
		Map<String,String> param = new HashMap<>();
		// 获取unionId或者openId的接口
		String jscode2sessionUrl = sysParamService.getSysParamValue(SysParamConstant.wechat_jscode2session_url);
		//小程序的id
		String appId = sysParamService.getSysParamValue(SysParamConstant.wechat_app_id);
		//小程序的密钥
		String secret = sysParamService.getSysParamValue(SysParamConstant.wechat_secret);
		param.put("appid", appId);
		param.put("secret", secret);
		param.put("js_code", jsCode);
		param.put("grant_type", "authorization_code");	//这个值是默认带上的值
		//读取session_key 和 openid
		String result = Requests.get(jscode2sessionUrl).body(param).send().readToText();
		JSONObject json = JSONObject.parseObject(result);
		String sessionKey = json.getString("session_key");
		String wxId = json.getString("openid");
		//尝试读取unionId
		try {
			String resData = WechatUtil.decryptData(encryptedData, sessionKey, iv);
			JSONObject data = JSONObject.parseObject(resData);
			if(data.containsKey("unionId")) {
				wxId = data.getString("unionId");
			}
		}catch(Exception e) {
			//报异常
			log.info(e.getMessage());
		}
		if(StringUtil.isEmpty(wxId)) {
			//throw new ServiceException("获取wxId失败");
			return null;
		}
		//返回wxId
		return wxId;
	}
	
	public void bindWxId(String jsCode, String encryptedData, String iv) {
		String wxId = this.getWxId(jsCode, encryptedData, iv);
		if(StringUtil.isNotEmpty(wxId)) {
			//获取当前登录的用户信息
			String username = SecurityUtil.getLoginUsername();
			User user = userService.findByUsername(username);
			//将当前登录的用户和微信标识绑定
			user.setWxId(wxId);
			userService.updateUser(user);
		}
	}
	
	/**
	 * 将当前登录的用户和微信标识解绑
	 */
	@Override
	public void unbindWxId() {
		//获取当前登录的用户信息
		String username = SecurityUtil.getLoginUsername();
		User user = userService.findByUsername(username);
		//将当前登录的用户和微信标识绑定
		user.setWxId(null);
		userService.updateUser(user);
	}
}
