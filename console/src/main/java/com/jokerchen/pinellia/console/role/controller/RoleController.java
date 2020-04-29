package com.jokerchen.pinellia.console.role.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;

import com.jokerchen.pinellia.common.model.Page;
import com.jokerchen.pinellia.console.role.entity.Role;

/**
 * @description: 角色信息mvc控制层
 * @author Joker Chen
 * @date 2019-03-12 17:20:51
 */
@RequestMapping("role")
public interface RoleController {

    /**
     * 获取角色信息
     * @param roleName  角色名称
     * @return
     */
    @RequestMapping("findRolePage")
    Page findRolePage(String roleName);

    /**
     * 保存角色信息
     * @param role
     * @param menuCodes 菜单编码，多个用逗号隔开
     */
    @RequestMapping("saveRole")
    void saveRole(@Valid Role role, String menuCode);

    /**
     * 删除角色
     * @param id    角色id
     */
    @RequestMapping("deleteRole")
    void deleteRole(int id);
}
