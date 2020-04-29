package com.jokerchen.pinellia.console.org.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jokerchen.pinellia.common.cache.CacheUtil;
import com.jokerchen.pinellia.common.constant.CacheKeyConstant;
import com.jokerchen.pinellia.common.constant.Constant;
import com.jokerchen.pinellia.common.exception.ServiceException;
import com.jokerchen.pinellia.common.model.TreeNode;
import com.jokerchen.pinellia.common.util.StringUtil;
import com.jokerchen.pinellia.common.util.TreeUtil;
import com.jokerchen.pinellia.console.login.SecurityUtil;
import com.jokerchen.pinellia.console.org.dao.OrgDao;
import com.jokerchen.pinellia.console.org.entity.Org;
import com.jokerchen.pinellia.console.org.service.OrgService;
import com.jokerchen.pinellia.console.org.vo.OrgVO;

/**
 * @description:
 * @author Joker Chen
 * @date 2019-03-13 16:36:43
 */
@Service
public class OrgServiceImpl implements OrgService {

    @Autowired
    private OrgDao orgDao;

    @Override
    public List<TreeNode> findOrg() {
        String username = SecurityUtil.getLoginUsername();
        if(username == null) return null;
        List<OrgVO> res = orgDao.findOrgByCode(username);
        List<TreeNode> menuVO = TreeUtil.buildTree(res);
        return menuVO;
    }

    @Override
    public void deleteOrg(String code) {
    	List<Org> list = orgDao.findSubOrg(code);
    	list.forEach(item->{
        	CacheUtil.delete(CacheKeyConstant.ORG_PREFIX + item.getCode());
        });
        orgDao.batchDelete(list);
    }

    @Override
    public void disableOrg(String code) {
        List<Org> list = orgDao.findSubOrg(code);
        list.forEach(item->{
        	item.setState(Constant.DATA_STATE_DISABLE);
        	CacheUtil.delete(CacheKeyConstant.ORG_PREFIX + item.getCode());
        });
        orgDao.batchUpdate(list);
    }

    @Override
    public void enableOrg(String code) {
        Org org = orgDao.getOrg(code);
        org.setState(Constant.DATA_STATE_NORMAL);
        orgDao.update(org);
        
        CacheUtil.delete(CacheKeyConstant.ORG_PREFIX + org.getCode());
    }

    @Override
    public void saveOrg(Org org) {
        if(org.getCode() == null || org.getCode().trim().isEmpty()){
            //先判断父级代码是否为空
            String parentCode = org.getParentCode();
            String code = "";
            if(parentCode == null) parentCode = "";
            Org maxCodeOrg = orgDao.findMaxCodeOrg(parentCode);
            if(maxCodeOrg == null){
                code = "0001";
            }else{
                code = maxCodeOrg.getCode();
                code = code.substring(parentCode.length());
                int maxCode = Integer.parseInt(code);
                maxCode++;
                code = StringUtil.appendLeft(String.valueOf(maxCode), '0', 4);
            }
            code = parentCode + code;
            org.setCode(code);
            org.setParentCode(parentCode);
            org.setState(Constant.DATA_STATE_NORMAL);
            org.setLevel(code.length() / 4);
            orgDao.save(org);
        }else {
        	//修改，为防止攻击，这里只对部分数据做修改
        	Org entity = orgDao.getOneByField("code", org.getCode());
        	if(entity == null) throw new ServiceException("数据异常！");
            entity.setState(org.getState());
            entity.setName(org.getName());
            entity.setSequence(org.getSequence());
            orgDao.update(entity);
        }
        CacheUtil.delete(CacheKeyConstant.ORG_PREFIX + org.getCode());
    }

	@Override
	public Org findOrgByCode(String code) {
		if(StringUtil.isNotEmpty(code)) {
			String key = CacheKeyConstant.ORG_PREFIX + code;
			Org org = CacheUtil.get(code, Org.class);
			if(org == null) {
				org = orgDao.getOneByField("code", code);
				if(org != null) {
					CacheUtil.set(key, org);
				}
			}
			return org;
		}
		return null;
	}
    
    
}
