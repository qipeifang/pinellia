package com.jokerchen.pinellia.common.log.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jokerchen.pinellia.common.log.dao.OperationLogDao;
import com.jokerchen.pinellia.common.log.entity.OperationLog;
import com.jokerchen.pinellia.common.log.service.OperationLogService;
import com.jokerchen.pinellia.common.model.Page;
import com.jokerchen.pinellia.common.model.Sort;
import com.jokerchen.pinellia.common.util.StringUtil;

/**   
 * @description: 操作日志逻辑实现层
 * @author Joker Chen 
 * @date 2019-04-10 16:29:19  
 */
@Service
public class OperationLogServiceImpl implements OperationLogService {

	@Autowired
	private OperationLogDao operationLogDao;
	
	@Override
	public void saveOperationLog(OperationLog operationLog) {
		operationLogDao.save(operationLog);
	}

	@Override
	public Page findOperationLogPage(String queryString) {
		if(StringUtil.isNotEmpty(queryString)) {
			return operationLogDao.findOperationLogPage(queryString);
		}else {
			return operationLogDao.pageAll(Sort.createSort("operateTime", false));
		}
	}

}
