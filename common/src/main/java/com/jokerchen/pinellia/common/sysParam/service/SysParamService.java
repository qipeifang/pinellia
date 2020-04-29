package com.jokerchen.pinellia.common.sysParam.service;

import javax.validation.constraints.NotNull;

import org.springframework.transaction.annotation.Transactional;

import com.jokerchen.pinellia.common.model.Page;
import com.jokerchen.pinellia.common.sysParam.entity.SysParam;

/**   
 * @description: 
 * @author Joker Chen 
 * @date 2019-04-11 14:50:20  
 */
public interface SysParamService {

	/**
	 * 查询系统参数
	 * @param queryString
	 * @return
	 */
	Page findSysParamPage(String queryString);
	
	/**
	 * 通过系统参数编码获取系统参数
	 * @param code
	 * @return
	 */
	String getSysParamValue(@NotNull String code);

	/**
	 * 保存系统参数
	 * @param sysParam
	 */
	@Transactional
	void saveSysParam(SysParam sysParam);
	
	/**
	 * 删除系统参数
	 * @param code
	 */
	@Transactional
	void deleteSysParam(@NotNull String code);

}
