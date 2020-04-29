package com.jokerchen.pinellia.console.common.controller.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.jokerchen.pinellia.console.common.controller.CommonController;
import com.jokerchen.pinellia.console.common.service.CommonService;

/**   
 * @description: 
 * @author Joker Chen 
 * @date 2019-04-24 19:00:36  
 */
@RestController
public class CommonControllerImpl implements CommonController{

	@Autowired
	private CommonService commonService;
	
	@Override
	public void bindWxId(String jsCode, String encryptedData, String iv) {
		commonService.bindWxId(jsCode, encryptedData, iv);
	}
	
	/**
	 * 将当前登录的用户和微信标识解绑
	 */
	@Override
	public void unbindWxId() {
		commonService.unbindWxId();
	}
	
	
	@Override
	public void image(HttpServletRequest request, HttpServletResponse response,String imagePath, String sessionId) {
		//TODO: 需要在逻辑自己处理session的问题，防止非法读取图片资源信息
		response.setContentType("image/*");
		System.out.println(imagePath);
		try {
			response.setStatus(200);
			File file = new File(imagePath);
			@SuppressWarnings("resource")
			FileInputStream inputStream = new FileInputStream(file);
			int length = inputStream.available();
			byte data[] = new byte[length];
			response.setContentLength(length);
			inputStream.read(data);
			OutputStream toClient = response.getOutputStream();
			toClient.write(data);
			toClient.flush();
		} catch (Exception e) {
			response.setStatus(500);
			e.printStackTrace();
		}
	}
}
