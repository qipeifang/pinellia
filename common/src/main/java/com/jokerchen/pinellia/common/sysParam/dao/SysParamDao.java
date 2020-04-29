package com.jokerchen.pinellia.common.sysParam.dao;

import com.jokerchen.pinellia.common.dao.BaseDao;
import com.jokerchen.pinellia.common.model.Page;
import com.jokerchen.pinellia.common.sysParam.entity.SysParam;

/**   
 * @description:	系统参数持久层 
 * @author Joker Chen 
 * @date 2019-04-11 14:50:42  
 */
public interface SysParamDao extends BaseDao<SysParam>{

	/**
	 * 查询系统参数
	 * @param queryString
	 * @return
	 */
	Page findSysParamPage(String queryString);
	
}
