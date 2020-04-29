package com.jokerchen.pinellia.common.ocr.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.jokerchen.pinellia.common.ocr.vo.OcrVO;

/**   
 * @description: ocr 识别接口
 * @author Joker Chen 
 * @date 2019-04-19 10:54:56  
 */
@RequestMapping("/ocr")
public interface OcrController {
	
	/**
	 * ocr 图片识别成文字
	 * @param ocr	识别的相关信息
	 * @return	识别结果
	 */
	@PostMapping("/imageRecognition")
	Object imageRecognition(@Valid OcrVO ocr);
	
	
	@PostMapping("/wxImageRecognition")
	Object wxImageRecognition(HttpServletRequest request, MultipartFile[] files) throws IOException;
	
	/**
	 * 获取百度的ocr访问需要用到的token
	 * @return
	 */
	@RequestMapping("/findBaiduOcrToken")
	String findBaiduOcrToken();
}
