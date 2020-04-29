package com.jokerchen.pinellia.common.sysParam.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.jokerchen.pinellia.common.annotation.OperationLogger;
import com.jokerchen.pinellia.common.constant.Constant;
import com.jokerchen.pinellia.common.model.Page;
import com.jokerchen.pinellia.common.sysParam.controller.SysParamController;
import com.jokerchen.pinellia.common.sysParam.entity.SysParam;
import com.jokerchen.pinellia.common.sysParam.service.SysParamService;

/**   
 * @description: 
 * @author Joker Chen 
 * @date 2019-04-11 14:51:40  
 */
@RestController
public class SysParamControllerImpl implements SysParamController{

	@Autowired
	private SysParamService sysParamService;

	@Override
	public Page findSysParamPage(String queryString) {
		return sysParamService.findSysParamPage(queryString);
	}

	@Override
	@OperationLogger(operationType=Constant.OPERATION_SAVE,operationDesc="保存系统参数")
	public void saveSysParam(SysParam sysParam) {

		sysParamService.saveSysParam(sysParam);
	}

	@Override
	@OperationLogger(operationType=Constant.OPERATION_DELETE,operationDesc="删除系统参数")
	public void deleteSysParam(String code) {
		sysParamService.deleteSysParam(code);
	}

}
