package com.jokerchen.pinellia.common.ocr.controller.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jokerchen.pinellia.common.ocr.controller.OcrController;
import com.jokerchen.pinellia.common.ocr.service.OcrService;
import com.jokerchen.pinellia.common.ocr.vo.OcrVO;
import com.jokerchen.pinellia.common.util.Base64ImageUtil;

/**
 * @description:
 * @author Joker Chen
 * @date 2019-04-19 10:55:06
 */
@RestController
public class OcrControllerImpl implements OcrController {

	@Autowired
	private OcrService ocrService;

	@Override
	public Object imageRecognition(OcrVO ocr) {
		return ocrService.imageRecognition(ocr);
	}

	@Override
	public Object wxImageRecognition(HttpServletRequest request, 
			@RequestParam("image") MultipartFile[] files)
			throws IOException {
		byte[] buffer = files[0].getBytes();
		String image = Base64ImageUtil.getImage(buffer);
		OcrVO ocr = new OcrVO();
		ocr.setImage(image);
		ocr.setImageType(request.getParameter("imageType"));
		ocr.setIdCardSide(request.getParameter("idCardSide"));
		return ocrService.imageRecognition(ocr);
	}

	@Override
	public String findBaiduOcrToken() {
		return ocrService.findBaiduOcrToken();
	}
}
