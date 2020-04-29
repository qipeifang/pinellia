package com.jokerchen.pinellia.console.menu.controller.impl;

import com.jokerchen.pinellia.common.annotation.OperationLogger;
import com.jokerchen.pinellia.common.constant.Constant;
import com.jokerchen.pinellia.common.model.TreeNode;
import com.jokerchen.pinellia.console.menu.controller.MenuController;
import com.jokerchen.pinellia.console.menu.entity.Menu;
import com.jokerchen.pinellia.console.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**   
 * @description: 
 * @author Joker Chen 
 * @date 2019-03-13 16:35:20  
 */
@RestController
public class MenuControllerImpl implements MenuController{

    @Autowired
    private MenuService menuService;

    @Override
    public List<TreeNode> findMenu() {
        return menuService.findMenu();
    }

    @Override
    @OperationLogger(operationType=Constant.OPERATION_DELETE,operationDesc="删除菜单")
    public void deleteMenu(int id) {
        menuService.deleteMenu(id);
    }

    @Override
    @OperationLogger(operationType=Constant.OPERATION_UPDATE,operationDesc="停用菜单")
    public void disableMenu(int id) {
        menuService.disableMenu(id);
    }

    @Override
    @OperationLogger(operationType=Constant.OPERATION_UPDATE,operationDesc="启用菜单")
    public void enableMenu(int id) {
        menuService.enableMenu(id);
    }

    @Override
    @OperationLogger(operationType=Constant.OPERATION_SAVE,operationDesc="保存菜单信息")
    public void saveMenu(Menu menu) {
        menuService.saveMenu(menu);
    }

}
