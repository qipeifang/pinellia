package com.jokerchen.pinellia.common.dict.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.jokerchen.pinellia.common.annotation.OperationLogger;
import com.jokerchen.pinellia.common.constant.Constant;
import com.jokerchen.pinellia.common.dict.controller.DictController;
import com.jokerchen.pinellia.common.dict.entity.Dict;
import com.jokerchen.pinellia.common.dict.entity.DictCategory;
import com.jokerchen.pinellia.common.dict.service.DictService;

/**   
 * @description: 数据字典维护
 * @author Joker Chen 
 * @date 2019-03-21 16:44:34  
 */
@RestController
public class DictControllerImpl implements DictController {

	@Autowired
	private DictService dictService;
	
	@Override
	public List<DictCategory> findDictCategory(String category) {
		return dictService.findDictCategory(category);
	}

	@Override
	public List<Dict> findDictByCategory(String category) {
		return dictService.findDictByCategory(category);
	}

	@Override
	public List<Dict> findDict(String category) {
		return dictService.findDict(category);
	}

	@Override
	@OperationLogger(operationType=Constant.OPERATION_SAVE,operationDesc="保存字典类型")
	public void saveCategory(DictCategory dictCategory,String oldCategory) {
		dictService.saveCategory(dictCategory,oldCategory);
	}
	
	@Override
	@OperationLogger(operationType=Constant.OPERATION_DELETE,operationDesc="删除字典类型")
	public void deleteCategory(String category) {
		dictService.deleteCategory(category);
	}

	@Override
	@OperationLogger(operationType=Constant.OPERATION_SAVE,operationDesc="保存字典信息")
	public void saveDict(Dict dict) {
		if(dict.getId() == 0) {
			//设置数据状态为正常
			dict.setState(Constant.DATA_STATE_NORMAL);
		}
		dictService.saveDict(dict);
	}

	@Override
	@OperationLogger(operationType=Constant.OPERATION_DELETE,operationDesc="删除字典信息")
	public void deleteDict(int id) {
		dictService.deleteDict(id);
	}

	@Override
	@OperationLogger(operationType=Constant.OPERATION_UPDATE,operationDesc="停用字典")
	public void disableDict(int id) {
		dictService.disableDict(id);
	}

	@Override
	@OperationLogger(operationType=Constant.OPERATION_UPDATE,operationDesc="启用字典")
	public void enableDict(int id) {
		dictService.enableDict(id);
	}


}
