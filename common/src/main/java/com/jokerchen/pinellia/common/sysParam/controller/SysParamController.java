package com.jokerchen.pinellia.common.sysParam.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.RequestMapping;

import com.jokerchen.pinellia.common.model.Page;
import com.jokerchen.pinellia.common.sysParam.entity.SysParam;

/**   
 * @description: 
 * @author Joker Chen 
 * @date 2019-04-11 14:51:53  
 */
@RequestMapping("/sysParam")
public interface SysParamController {
	
	/**
	 * 查询系统参数
	 * @param queryString
	 * @return
	 */
	@RequestMapping("/findSysParamPage")
	Page findSysParamPage(String queryString);
	
	/**
	 * 保存系统参数
	 * @param sysParam
	 */
	@RequestMapping("/saveSysParam")
	void saveSysParam(@Valid SysParam sysParam);
	
	/**
	 * 删除系统参数
	 * @param code
	 */
	@RequestMapping("/deleteSysParam")
	void deleteSysParam(@NotNull String code);

}
