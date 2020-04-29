package com.jokerchen.pinellia.console.role.dao;

import java.util.Set;

import com.jokerchen.pinellia.common.dao.BaseDao;
import com.jokerchen.pinellia.common.model.Page;
import com.jokerchen.pinellia.console.role.entity.Role;

/**
 * @description: 角色信息mvc控制层
 * @author Joker Chen
 * @date 2019-03-12 17:20:51
 */
public interface RoleDao extends BaseDao<Role> {

    /**
     * 获取角色信息
     * @param roleName  角色名称
     * @param orgCode	组织编码
     * @return
     */
    Page findRolePage(String roleName, Set<String> orgCode, Set<Integer> roleId);

}
