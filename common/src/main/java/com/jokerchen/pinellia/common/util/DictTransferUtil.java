package com.jokerchen.pinellia.common.util;

import java.lang.reflect.Method;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.jokerchen.pinellia.common.dict.entity.Dict;
import com.jokerchen.pinellia.common.dict.service.DictService;

import lombok.extern.slf4j.Slf4j;

/**
 * @description: 字典信息转义工具类，用于将数据字典的code转成text，便于在前端显示
 * @author Joker Chen
 * @date 2019-06-12 10:06:05
 */
@Slf4j
@Component
public class DictTransferUtil {

	@Autowired
	private DictService dictService;

	/**
	 * 将字典的编码转成字典名称
	 * 
	 * @param object
	 *            要转义的实体
	 * @param category
	 *            字典类型
	 * @param field
	 *            要转义的字段
	 */
	public void codeToName(Object object, String category, String field) {
		this.codeToName(object, category, field, field);
	}

	/**
	 * 将字典的编码转成字典名称
	 * 
	 * @param object
	 *            要转义的实体
	 * @param category
	 *            字典类型
	 * @param sourceField
	 *            要转义的字段
	 * @param targetField
	 *            转义后接收的字段
	 */
	public void codeToName(Object object, String category, String sourceField, String targetField) {
		if (StringUtil.isEmpty(category) || object == null || StringUtil.isEmpty(sourceField)
				|| StringUtil.isEmpty(targetField)) {
			return;
		}
		try {
			if (object instanceof JSONObject) {
				// 如果是json 则无需使用反射
				JSONObject json = (JSONObject) object;
				String code = json.getString(sourceField);
				if (StringUtil.isNotEmpty(code)) {
					// 获取数据字典
					Dict dict = dictService.findDictByCode(category, code);
					if (dict != null) {
						// 赋值
						json.put(targetField, dict.getName());
					}
				}
			} else {
				// 将首字母置换成大写字母
				sourceField = sourceField.substring(0, 1).toUpperCase() + sourceField.substring(1);
				// 获取该字段的get方法
				Method getMethod = object.getClass().getMethod("get" + sourceField);
				// 查询数据字典
				String code = (String) getMethod.invoke(object);
				if (StringUtil.isNotEmpty(code)) {
					// 获取数据字典
					Dict dict = dictService.findDictByCode(category, code);
					if (dict != null) {
						// 将首字母置换成大写字母
						targetField = targetField.substring(0, 1).toUpperCase() + targetField.substring(1);
						// 获取该字段的set方法
						Method setMethod = object.getClass().getMethod("set" + targetField, String.class);
						// 赋值
						setMethod.invoke(object, dict.getName());
					}
				}
			}

		} catch (Exception e) {
			log.error("字典转换异常", e);
		}
	}

	/**
	 * 将字典的编码转成字典名称
	 * 
	 * @param object
	 *            要转义的实体
	 * @param category
	 *            字典类型
	 * @param field
	 *            要转义的字段
	 */
	public void codeToName(Object object, String[] category, String[] field) {
		this.codeToName(object, category, field, field);
	}

	/**
	 * 将字典的编码转成字典名称
	 * 
	 * @param object
	 *            要转义的实体
	 * @param category
	 *            字典类型
	 * @param sourceField
	 *            要转义的字段
	 * @param targetField
	 *            转义后接收的字段
	 */
	public void codeToName(Object object, String[] category, String[] sourceField, String[] targetField) {
		if (object == null || category == null || sourceField == null || targetField == null
				|| category.length != sourceField.length || sourceField.length != targetField.length) {
			return;
		}
		for (int i = 0; i < category.length; i++) {
			this.codeToName(object, category[i], sourceField[i], targetField[i]);
		}
	}
	
	/**
	 * 将字典的编码转成字典名称
	 * 
	 * @param list
	 *            要转义的实体
	 * @param category
	 *            字典类型
	 * @param field
	 *            要转义的字段
	 */
	public void codeToName(List<?> list, String category, String field) {
		this.codeToName(list, category, field, field);
	}

	/**
	 * 将字典的编码转成字典名称
	 * 
	 * @param list
	 *            要转义的实体
	 * @param category
	 *            字典类型
	 * @param sourceField
	 *            要转义的字段
	 * @param targetField
	 *            转义后接收的字段
	 */
	public void codeToName(List<?> list, String category, String sourceField, String targetField) {
		if (StringUtil.isEmpty(category) || list == null || list.size() == 0 
				|| StringUtil.isEmpty(sourceField) || StringUtil.isEmpty(targetField)) {
			return;
		}
		for(Object object : list) {
			this.codeToName(object, category, sourceField, targetField);
		}
	}

	/**
	 * 将字典的编码转成字典名称
	 * 
	 * @param list
	 *            要转义的实体
	 * @param category
	 *            字典类型
	 * @param field
	 *            要转义的字段
	 */
	public void codeToName(List<?> list, String[] category, String[] field) {
		this.codeToName(list, category, field, field);
	}

	/**
	 * 将字典的编码转成字典名称
	 * 
	 * @param list
	 *            要转义的实体
	 * @param category
	 *            字典类型
	 * @param sourceField
	 *            要转义的字段
	 * @param targetField
	 *            转义后接收的字段
	 */
	public void codeToName(List<?> list, String[] category, String[] sourceField, String[] targetField) {
		if (list == null || list.size() == 0 || category == null || sourceField == null || targetField == null
				|| category.length != sourceField.length || sourceField.length != targetField.length) {
			return;
		}
		for(Object object : list) {
			for (int i = 0; i < category.length; i++) {
				this.codeToName(object, category[i], sourceField[i], targetField[i]);
			}
		}
	}
	

}
