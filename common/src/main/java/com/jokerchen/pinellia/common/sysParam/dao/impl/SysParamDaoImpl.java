package com.jokerchen.pinellia.common.sysParam.dao.impl;

import org.springframework.stereotype.Repository;

import com.jokerchen.pinellia.common.dao.impl.BaseDaoImpl;
import com.jokerchen.pinellia.common.model.Page;
import com.jokerchen.pinellia.common.sysParam.dao.SysParamDao;
import com.jokerchen.pinellia.common.sysParam.entity.SysParam;

/**   
 * @description: 系统参数持久层
 * @author Joker Chen 
 * @date 2019-04-11 14:50:58  
 */
@Repository
public class SysParamDaoImpl extends BaseDaoImpl<SysParam> implements SysParamDao {

	@Override
	public Page findSysParamPage(String queryString) {
		String hql = "FROM SysParam WHERE code like ?0 or name like ?1 ";
		return pageByHql(hql,"%"+queryString+"%","%"+queryString+"%");
	}
	
}
