package com.jokerchen.pinellia.common.log.dao;

import com.jokerchen.pinellia.common.dao.BaseDao;
import com.jokerchen.pinellia.common.log.entity.OperationLog;
import com.jokerchen.pinellia.common.model.Page;

/**   
 * @description: 
 * @author Joker Chen 
 * @date 2019-04-10 16:30:32  
 */
public interface OperationLogDao extends BaseDao<OperationLog> {

	/**
	 * 查询操作日志信息
	 * @param queryString 查询信息
	 * @return 操作日志
	 */
	Page findOperationLogPage(String queryString);
}
