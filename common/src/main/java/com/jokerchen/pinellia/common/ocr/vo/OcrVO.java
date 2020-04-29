package com.jokerchen.pinellia.common.ocr.vo;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**   
 * @description:	做ocr识别时需要传入的一些参数 
 * @author Joker Chen 
 * @date 2019-04-19 10:55:41  
 */
@Data
public class OcrVO {
	
	/** 要识别的图片类型 */
	@NotBlank
	private String imageType;
	
	/** base64 格式的图片 */
	@NotBlank
	private String image;
	
	/** 如果是身份证的话，识别的是正面还是反面  ront：身份证含照片的一面；back：身份证带国徽的一面*/
	private String idCardSide;
}
