package com.jokerchen.pinellia.common.log.dao.impl;

import org.springframework.stereotype.Repository;

import com.jokerchen.pinellia.common.dao.impl.BaseDaoImpl;
import com.jokerchen.pinellia.common.log.dao.OperationLogDao;
import com.jokerchen.pinellia.common.log.entity.OperationLog;
import com.jokerchen.pinellia.common.model.Page;

/**   
 * @description: 操作日志持久层
 * @author Joker Chen 
 * @date 2019-04-10 16:30:48  
 */
@Repository
public class OperationLogDaoImpl extends BaseDaoImpl<OperationLog> implements OperationLogDao{

	@Override
	public Page findOperationLogPage(String queryString) {
		String hql = "from OperationLog where operator like ?0 "
				+ " or remoteAddr like ?1 "
				+ " or operateDesc like ?2 "
				+ " or exception like ?3 "
				+ " or method like ?4 ";
		queryString = "%" + queryString + "%";
		return pageByHql(hql, queryString, queryString, queryString, queryString, queryString);
	}

}
