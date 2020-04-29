package com.jokerchen.pinellia.common.log.service;

import com.jokerchen.pinellia.common.log.entity.OperationLog;
import com.jokerchen.pinellia.common.model.Page;

/**   
 * @description: 操作日志逻辑层接口
 * @author Joker Chen 
 * @date 2019-04-10 15:43:04  
 */
public interface OperationLogService {

	/**
	 * 报错操作日志信息
	 * @param operationLog
	 */
	void saveOperationLog(OperationLog operationLog);
	
	/**
	 * 查询操作日志信息
	 * @param queryString 查询信息
	 * @return 操作日志
	 */
	Page findOperationLogPage(String queryString);
}
