package com.jokerchen.pinellia.common.log.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.jokerchen.pinellia.common.log.controller.OperationLogController;
import com.jokerchen.pinellia.common.log.service.OperationLogService;
import com.jokerchen.pinellia.common.model.Page;

/**   
 * @description:	操作日志展示应用层
 * @author Joker Chen 
 * @date 2019-04-10 16:34:18  
 */
@RestController
public class OperationLogControllerImpl implements OperationLogController {

	@Autowired
	private OperationLogService operationLogService;
	
	@Override
	public Page findOperationLogPage(String queryString) {
		return operationLogService.findOperationLogPage(queryString);
	}

}
