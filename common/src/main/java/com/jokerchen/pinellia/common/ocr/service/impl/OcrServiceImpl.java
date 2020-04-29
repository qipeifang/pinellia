package com.jokerchen.pinellia.common.ocr.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.jokerchen.pinellia.common.cache.CacheUtil;
import com.jokerchen.pinellia.common.constant.CacheKeyConstant;
import com.jokerchen.pinellia.common.constant.SysParamConstant;
import com.jokerchen.pinellia.common.exception.ServiceException;
import com.jokerchen.pinellia.common.ocr.service.OcrService;
import com.jokerchen.pinellia.common.ocr.vo.OcrVO;
import com.jokerchen.pinellia.common.sysParam.service.SysParamService;
import com.jokerchen.pinellia.common.util.StringUtil;
//import com.baidu.aip.ocr.AipOcr;
//import com.baidu.aip.util.Base64Util;

import lombok.extern.slf4j.Slf4j;
import net.dongliu.requests.Requests;

/**   
 * @description: ocr 识别逻辑层实现
 * @author Joker Chen 
 * @date 2019-04-19 10:55:26  
 */
@Slf4j
@Service
public class OcrServiceImpl implements OcrService{

	@Autowired
	private SysParamService sysParamService;
	
//	private static AipOcr client;
	
	@Override
	public Object imageRecognition(OcrVO ocr) {
		return this.imageRecognition_api(ocr);
	}
	
	/**
	 * ocr 图片识别成文字,使用百度提供的sdk
	 * @param ocr	识别的相关信息
	 * @return	识别结果
	 */
	public Object imageRecognition_sdk(OcrVO ocr){
//		if(StringUtil.isEmpty(ocr.getImage())) {
//			throw new ServiceException("图片信息为空");
//		}
//		org.json.JSONObject jsonObject = null;
//		if(client == null) {
//			//获取访问百度ocr的相关信息
//			String appId = sysParamService.getSysParamValue(SysParamConstant.baidu_ocr_app_id);
//			String apiKey = sysParamService.getSysParamValue(SysParamConstant.baidu_ocr_api_key);
//			String secretKey = sysParamService.getSysParamValue(SysParamConstant.baidu_ocr_secret_key);
//			client = new AipOcr(appId, apiKey, secretKey);
//		}
//		byte[] imageByte = Base64Util.decode(ocr.getImage());
//		if(ocr.getImageType().equals("P1")) {
//			//身份证识别
//			jsonObject = client.idcard(imageByte, ocr.getIdCardSide(), null);
//		}else if(ocr.getImageType().equals("C3")) {
//			// 营业执照识别
//			jsonObject = client.businessLicense(imageByte,null);
//		}
//		if(jsonObject != null) {
//			return JSONObject.parse(jsonObject.toString());
//		}
		return null;
	}
	
	/**
	 * ocr 图片识别成文字，使用百度提供的api
	 * @param ocr	识别的相关信息
	 * @return	识别结果
	 */
	public Object imageRecognition_api(OcrVO ocr){
		String url = sysParamService.getSysParamValue(SysParamConstant.baidu_ocr_url_prefix+ocr.getImageType());
		if(StringUtil.isEmpty(url)) {
			throw new ServiceException("无该类型的OCR识别");
		}
		//获取token的相关信息
		String token = findBaiduOcrToken();
		if(StringUtil.isEmpty(token)) {
			throw new ServiceException("获取token失败");
		}
		Map<String,String> param = new HashMap<>();
		param.put("image", ocr.getImage());
		param.put("access_token", token);
		if(StringUtil.isNotEmpty(ocr.getIdCardSide())) {
			//身份证识别，标志正反面
			param.put("id_card_side", ocr.getIdCardSide());
		}
		Map<String,String> header = new HashMap<>();
		header.put("Content-Type","application/x-www-form-urlencoded");
		String resp = Requests.post(url).headers(header).body(param).send().readToText();
		//识别结果
		log.info(resp);
		//将返回结果转成json
		JSONObject json = (JSONObject) JSONObject.parse(resp);
		return json;
	}
	
	
	
	@Override
	public String findBaiduOcrToken() {
		//当前时间戳
		long currentTime = System.currentTimeMillis();
		@SuppressWarnings("unchecked")
		HashMap<String,Object> map = CacheUtil.get(CacheKeyConstant.BAIDU_OCR_TOKEN_KEY, HashMap.class);
		if(map != null && map.get("token") != null) {
			String token = map.get("token").toString();
			long expires = (long) map.get("expires");
			//如果token存在，并且还未过期，则返回，否则发起请求获取token
			if(expires > currentTime) {
				return token;
			}
		}else {
			map = new HashMap<>();
		}
		//获取token的相关信息
		String url = sysParamService.getSysParamValue(SysParamConstant.baidu_ocr_access_token_url);
		String id = sysParamService.getSysParamValue(SysParamConstant.baidu_ocr_api_key);
		String secret = sysParamService.getSysParamValue(SysParamConstant.baidu_ocr_secret_key);
		Map<String,String> param = new HashMap<>();
		param.put("client_id", id);
		param.put("client_secret", secret);
		//固定的一个参数
		param.put("grant_type", "client_credentials");
		String resp = Requests.get(url).body(param).send().readToText();
		//将返回结果转成json
		JSONObject json = (JSONObject) JSONObject.parse(resp);
		if(StringUtil.isNotEmpty(json.getString("error"))) {
			//如果异常信息不为空，则说明获取token失败。返回获取的异常信息
			throw new ServiceException(resp);
		}
		//可访问的token
		String token = json.getString("access_token");
		// 有效期限，返回的单位是秒，这里需转成毫秒，加上当前的时间，就是这个token在这个时间之前都是有效的，
		// 为防止前台拿到这个token后在操作过程出现token过期的情况，这里的有效期设置比实际期限少一天
		long expires = currentTime + (json.getLong("expires_in") * 1000) - ( 86400 * 1000);
		map.put("token", token);
		map.put("expires", expires);
		//将结果放到缓存中
		CacheUtil.set(CacheKeyConstant.BAIDU_OCR_TOKEN_KEY, map);
		return token;
	}

}
