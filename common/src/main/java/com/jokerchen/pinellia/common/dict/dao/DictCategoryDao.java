package com.jokerchen.pinellia.common.dict.dao;

import java.util.List;

import com.jokerchen.pinellia.common.dao.BaseDao;
import com.jokerchen.pinellia.common.dict.entity.DictCategory;

/**   
 * @description: 数据字典类型持久层接口
 * @author Joker Chen 
 * @date 2019-03-21 16:48:49  
 */
public interface DictCategoryDao extends BaseDao<DictCategory>{

	
	/**
	 * 根据字典类型编码或名称查找字典类型信息
	 * @param category
	 * @return
	 */
	List<DictCategory> findDictCategory(String category);

	/**
	 * 判断字典编码是否已经存在
	 * @param category
	 * @param id
	 */
	boolean existCategory(String category, int id);
}
