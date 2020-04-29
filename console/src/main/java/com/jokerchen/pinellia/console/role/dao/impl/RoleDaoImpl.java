package com.jokerchen.pinellia.console.role.dao.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.jokerchen.pinellia.common.dao.impl.BaseDaoImpl;
import com.jokerchen.pinellia.common.model.Page;
import com.jokerchen.pinellia.common.util.StringUtil;
import com.jokerchen.pinellia.console.role.dao.RoleDao;
import com.jokerchen.pinellia.console.role.entity.Role;
import com.jokerchen.pinellia.console.role.vo.RoleVO;

/**
 * @author joker
 * 2019-03-18 21:25
 * @Description:
 */
@Repository
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {

    @Override
    public Page findRolePage(String roleName, Set<String> orgCode, Set<Integer> roleId) {
    	Map<String,Object> param = new HashMap<>();
        String hql = " select r.roleId, r.roleName, r.orgCode, r.authType, "
        		+ " (select o.name from tc_auth_org o where o.code = r.orgCode ) as orgName , "
        		+ " group_concat(rm.menuCode) as menuCode, " 
        		+ " group_concat(m.name) as menuName "
        		+ " FROM tc_auth_role r LEFT JOIN tc_auth_role_menu rm ON r.roleId = rm.roleId "
        		+ " LEFT JOIN tc_auth_menu m on rm.menuCode = m.code"
        		+ " where ( r.orgCode in (:orgCode) or r.roleId in (:roleId) ) ";
        param.put("orgCode", orgCode);
        param.put("roleId", roleId);
        if(StringUtil.isNotEmpty(roleName)) {
        	hql += " and r.roleName like :roleName ";
        	param.put("roleName", "%"+roleName+"%");
        }
        hql += " GROUP BY r.roleId ";
        return this.pageBySql(hql,RoleVO.class,param);
    }
}
