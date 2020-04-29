package com.jokerchen.pinellia.console.menu.service;

import com.jokerchen.pinellia.common.model.TreeNode;
import com.jokerchen.pinellia.console.menu.entity.Menu;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**   
 * @description: 
 * @author Joker Chen 
 * @date 2019-03-13 16:36:33  
 */
public interface MenuService {

	/**
	 * 查询菜单信息
	 * @return
	 */
	List<TreeNode> findMenu();

	/**
	 * 删除菜单
	 * @param id 要删除的菜单id
	 */
	@Transactional
	void deleteMenu(int id);

	/**
	 * 停用菜单
	 * @param id 要停用的菜单id
	 */
	void disableMenu(int id);

	/**
	 * 启用菜单
	 * @param id 要停用的菜单id
	 */
	void enableMenu(int id);

	/**
	 * 保存菜单
	 * @param menu
	 */
	void saveMenu(Menu menu);
}
