package com.jokerchen.pinellia.common.sysParam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jokerchen.pinellia.common.cache.CacheUtil;
import com.jokerchen.pinellia.common.constant.CacheKeyConstant;
import com.jokerchen.pinellia.common.model.Page;
import com.jokerchen.pinellia.common.sysParam.dao.SysParamDao;
import com.jokerchen.pinellia.common.sysParam.entity.SysParam;
import com.jokerchen.pinellia.common.sysParam.service.SysParamService;
import com.jokerchen.pinellia.common.util.StringUtil;

/**   
 * @description: 系统参数逻辑实现层 
 * @author Joker Chen 
 * @date 2019-04-11 14:50:30  
 */
@Service
public class SysParamServiceImpl implements SysParamService {

	@Autowired
	private SysParamDao sysParamDao;
	
	@Override
	public Page findSysParamPage(String queryString) {
		if(StringUtil.isNotEmpty(queryString)) {
			return sysParamDao.findSysParamPage(queryString);
		}else {
			return sysParamDao.pageAll();
		}
	}
	
	@Override
	public String getSysParamValue(String code) {
		String key = CacheKeyConstant.SYSTEM_PARAM_PREFIX + code;
		String value = CacheUtil.get(key);
		if(value == null) {
			SysParam sysParam = sysParamDao.getOneByField("code", code);
			if(sysParam != null ) {
				CacheUtil.set(key, sysParam.getValue());
				return sysParam.getValue();
			}
		}
		return value;
	}

	@Override
	public void saveSysParam(SysParam sysParam) {
		String key = CacheKeyConstant.SYSTEM_PARAM_PREFIX + sysParam.getCode();
		CacheUtil.delete(key);
		sysParamDao.saveOrUpdate(sysParam);
	}

	@Override
	public void deleteSysParam(String code) {
		sysParamDao.delete(sysParamDao.getOneByField("code",code));
	}
}
