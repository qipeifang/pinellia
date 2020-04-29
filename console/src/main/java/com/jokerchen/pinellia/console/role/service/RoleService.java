package com.jokerchen.pinellia.console.role.service;

import com.jokerchen.pinellia.common.model.Page;
import com.jokerchen.pinellia.console.role.entity.Role;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description: 角色信息mvc控制层
 * @author Joker Chen
 * @date 2019-03-12 17:20:51
 */
public interface RoleService {

    /**
     * 获取角色信息
     * @param roleName  角色名称
     * @return
     */
    Page findRolePage(String roleName);

    /**
     * 保存角色信息
     * @param role
     */
    @Transactional
    void saveRole(Role role,String menuCode);

    /**
     * 删除角色
     * @param id    角色id
     */
    @Transactional
    void deleteRole(int id);
}
