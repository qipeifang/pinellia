package com.jokerchen.pinellia.console.util;

import java.lang.reflect.Method;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.jokerchen.pinellia.common.util.StringUtil;
import com.jokerchen.pinellia.console.org.entity.Org;
import com.jokerchen.pinellia.console.org.service.OrgService;

import lombok.extern.slf4j.Slf4j;

/**
 * @description: 组织信息转义工具类，用于将数据组织的code转成text，便于在前端显示
 * @author Joker Chen
 * @date 2019-06-12 10:06:05
 */
@Slf4j
@Component
public class OrgTransferUtil {

	@Autowired
	private OrgService orgService;

	/**
	 * 将组织的编码转成组织名称
	 * 
	 * @param object
	 *            要转义的实体
	 * @param field
	 *            要转义的字段
	 */
	public void codeToName(Object object, String field) {
		this.codeToName(object, field, field);
	}

	/**
	 * 将组织的编码转成组织名称
	 * 
	 * @param object
	 *            要转义的实体
	 * @param sourceField
	 *            要转义的字段
	 * @param targetField
	 *            转义后接收的字段
	 */
	public void codeToName(Object object, String sourceField, String targetField) {
		if (object == null || StringUtil.isEmpty(sourceField) || StringUtil.isEmpty(targetField)) {
			return;
		}
		try {
			if (object instanceof JSONObject) {
				// 如果是json 则无需使用反射
				JSONObject json = (JSONObject) object;
				String code = json.getString(sourceField);
				if (StringUtil.isNotEmpty(code)) {
					// 获取组织信息
					Org org = orgService.findOrgByCode(code);
					if (org != null) {
						// 赋值
						json.put(targetField, org.getName());
					}
				}
			} else {
				// 将首字母置换成大写字母
				sourceField = sourceField.substring(0, 1).toUpperCase() + sourceField.substring(1);
				// 获取该字段的get方法
				Method getMethod = object.getClass().getMethod("get" + sourceField);
				// 查询组织信息
				String code = (String) getMethod.invoke(object);
				if (StringUtil.isNotEmpty(code)) {
					// 获取组织信息
					Org org = orgService.findOrgByCode(code);
					if (org != null) {
						// 将首字母置换成大写字母
						targetField = targetField.substring(0, 1).toUpperCase() + targetField.substring(1);
						// 获取该字段的set方法
						Method setMethod = object.getClass().getMethod("set" + targetField, String.class);
						// 赋值
						setMethod.invoke(object, org.getName());
					}
				}
			}

		} catch (Exception e) {
			log.error("组织机构代码转名称转换异常", e);
		}
	}

	/**
	 * 将组织的编码转成组织名称
	 * 
	 * @param object
	 *            要转义的实体
	 * @param field
	 *            要转义的字段
	 */
	public void codeToName(Object object, String[] field) {
		this.codeToName(object, field, field);
	}

	/**
	 * 将组织的编码转成组织名称
	 * 
	 * @param object
	 *            要转义的实体
	 * @param sourceField
	 *            要转义的字段
	 * @param targetField
	 *            转义后接收的字段
	 */
	public void codeToName(Object object, String[] sourceField, String[] targetField) {
		if (object == null  || sourceField == null || targetField == null
				|| sourceField.length != targetField.length) {
			return;
		}
		for (int i = 0; i < sourceField.length; i++) {
			this.codeToName(object, sourceField[i], targetField[i]);
		}
	}
	
	/**
	 * 将组织的编码转成组织名称
	 * 
	 * @param list
	 *            要转义的实体
	 * @param field
	 *            要转义的字段
	 */
	public void codeToName(List<?> list, String field) {
		this.codeToName(list, field, field);
	}

	/**
	 * 将组织的编码转成组织名称
	 * 
	 * @param list
	 *            要转义的实体
	 * @param sourceField
	 *            要转义的字段
	 * @param targetField
	 *            转义后接收的字段
	 */
	public void codeToName(List<?> list, String sourceField, String targetField) {
		if (list == null || list.size() == 0 
				|| StringUtil.isEmpty(sourceField) || StringUtil.isEmpty(targetField)) {
			return;
		}
		for(Object object : list) {
			this.codeToName(object, sourceField, targetField);
		}
	}

	/**
	 * 将组织的编码转成组织名称
	 * 
	 * @param list
	 *            要转义的实体
	 * @param field
	 *            要转义的字段
	 */
	public void codeToName(List<?> list, String[] field) {
		this.codeToName(list, field, field);
	}

	/**
	 * 将组织的编码转成组织名称
	 * 
	 * @param list
	 *            要转义的实体
	 * @param sourceField
	 *            要转义的字段
	 * @param targetField
	 *            转义后接收的字段
	 */
	public void codeToName(List<?> list, String[] sourceField, String[] targetField) {
		if (list == null || list.size() == 0 || sourceField == null || targetField == null
				|| sourceField.length != targetField.length) {
			return;
		}
		for(Object object : list) {
			for (int i = 0; i < sourceField.length; i++) {
				this.codeToName(object, sourceField[i], targetField[i]);
			}
		}
	}
	

}
