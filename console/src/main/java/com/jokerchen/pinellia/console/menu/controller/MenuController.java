package com.jokerchen.pinellia.console.menu.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;

import com.jokerchen.pinellia.common.model.TreeNode;
import com.jokerchen.pinellia.console.menu.entity.Menu;

/**   
 * @description: 
 * @author Joker Chen 
 * @date 2019-03-13 16:35:13  
 */
@RequestMapping("/menu")
public interface MenuController {

    /**
     * 获取当前登录用户有权限的菜单信息
     * @return	树形结构的菜单信息
     */
    @RequestMapping("/findMenu")
    List<TreeNode> findMenu();

    /**
     * 删除菜单
     * @param id 要删除的菜单id
     */
    @RequestMapping("/deleteMenu")
    void deleteMenu(int id);

    /**
     * 停用菜单
     * @param id 要停用的菜单id
     */
    @RequestMapping("/disableMenu")
    void disableMenu(int id);

    /**
     * 启用菜单
     * @param id 要停用的菜单id
     */
    @RequestMapping("/enableMenu")
    void enableMenu(int id);

    /**
     * 保存菜单
     * @param menu
     */
    @RequestMapping("/saveMenu")
    void saveMenu(@Valid Menu menu);
}
