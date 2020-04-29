package com.jokerchen.pinellia.console.role.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.jokerchen.pinellia.common.annotation.OperationLogger;
import com.jokerchen.pinellia.common.constant.Constant;
import com.jokerchen.pinellia.common.model.Page;
import com.jokerchen.pinellia.console.role.controller.RoleController;
import com.jokerchen.pinellia.console.role.entity.Role;
import com.jokerchen.pinellia.console.role.service.RoleService;

/**
 * @description:
 * @author Joker Chen
 * @date 2019-03-12 17:14:31
 */
@RestController
public class RoleControllerImpl implements RoleController {

    @Autowired
    private RoleService roleService;

    @Override
    public Page findRolePage(String roleName) {
        return roleService.findRolePage(roleName);
    }

    @Override
    @OperationLogger(operationType=Constant.OPERATION_SAVE,operationDesc="保存角色信息")
    public void saveRole(Role role, String menuCode) {
        roleService.saveRole(role, menuCode);
    }

    @Override
    @OperationLogger(operationType=Constant.OPERATION_DELETE,operationDesc="删除角色信息")
    public void deleteRole(int id) {
        roleService.deleteRole(id);
    }
}
