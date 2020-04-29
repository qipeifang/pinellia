package com.jokerchen.pinellia.common.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.jokerchen.pinellia.common.constant.Constant;

import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author Joker Chen
 * @date 2019-04-22 16:43:22
 */
@Slf4j
public class Base64ImageUtil {

	/**
	 * 图片转化成base64字符串
	 * @param filePath	图片地址
	 * @return
	 */
	@SuppressWarnings("restriction")
	public static String getImage(String filePath) {
		if(StringUtil.isEmpty(filePath)) return null;
		InputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(filePath);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
        	log.error("获取图片异常",e);
        }
        // 加密
		sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
        return encoder.encode(data);
	}
	
	/**
	 * 二进制文件转成base64编码
	 * @param file
	 * @return
	 */
	@SuppressWarnings("restriction")
	public static String getImage(byte[] file) {
        // 加密
		sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
        return encoder.encode(file);
	}

	/**
	 * 将base64字符串转化成图片 
	 * @param image	要转成图片的base64字符串
	 * @param filePath	保存的路径地址
	 * @return
	 */
	@SuppressWarnings("restriction")
	public static boolean generateImage(String image,String filePath) { // 对字节数组字符串进行Base64解码并生成图片
		if (image == null) // 图像数据为空
			return false;
		sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
		try {
			//因为image可以能包含base64的压缩信息，所以需先去除
			if(image.startsWith(Constant.base64_image_jpeg_prefix)) {
				image = image.replace(Constant.base64_image_jpeg_prefix, "");
			}else if(image.startsWith(Constant.base64_image_png_prefix)) {
				image = image.replace(Constant.base64_image_png_prefix, "");
			}
			// Base64解码
			byte[] b = decoder.decodeBuffer(image);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			// 生成jpeg图片
			OutputStream out = new FileOutputStream(filePath);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			log.error("生成图片异常",e);
			return false;
		}
	}
}
