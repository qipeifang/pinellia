package com.jokerchen.pinellia.common.ocr.service;

import com.jokerchen.pinellia.common.ocr.vo.OcrVO;

/**   
 * @description: ocr 识别
 * @author Joker Chen 
 * @date 2019-04-19 10:55:18  
 */
public interface OcrService {

	/**
	 * ocr 图片识别成文字,使用百度提供的sdk
	 * @param ocr	识别的相关信息
	 * @return	识别结果
	 */
	Object imageRecognition(OcrVO ocr);
	
	/**
	 * 获取百度的ocr访问需要用到的token
	 * @return
	 */
	String findBaiduOcrToken();
}
