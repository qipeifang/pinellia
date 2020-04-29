package com.jokerchen.pinellia.common.log.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import com.jokerchen.pinellia.common.model.Page;

/**   
 * @description: 操作日志应用层接口
 * @author Joker Chen 
 * @date 2019-04-10 16:51:52  
 */
@RequestMapping("/operationLog")
public interface OperationLogController {

	/**
	 * 查询操作日志信息
	 * @param queryString 查询信息
	 * @return 操作日志
	 */
	@RequestMapping("/findOperationLogPage")
	Page findOperationLogPage(String queryString);
}
