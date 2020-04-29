package com.jokerchen.pinellia.console.auth.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;

import com.jokerchen.pinellia.common.model.TreeNode;
import com.jokerchen.pinellia.console.role.entity.Role;

/**   
 * @description: 权限相关功能mvc 控制层接口
 * @author Joker Chen 
 * @date 2019-03-15 09:10:47  
 */
@RequestMapping("/auth")
public interface AuthController {
	
	/**
	 * 获取当前登录用户有权限的菜单信息
	 * @return	树形结构的菜单信息，拥有构建导航菜单树
	 */
	@RequestMapping("/findMenu")
	List<TreeNode> findMenu();
	
	/**
	 * 获取当前登录用户的所有的菜单信息，用于菜单权限的分配
	 * @return
	 */
	@RequestMapping("/findUserMenu")
	List<TreeNode> findUserMenu();

	/**
	 * 为角色添加菜单权限
	 * @param roleId 角色id
	 * @param menuCodes 菜单编码，多个用逗号隔开
	 */
	@RequestMapping("saveRoleMenu")
	void saveRoleMenu(int roleId, String menuCodes);
	
	/**
	 * 获取当前登陆用户的角色信息
	 * @return 角色信息
	 */
	@RequestMapping("findRole")
	List<Role> findRole();
	
	/**
	 * 获取当前登陆用户的有权限进行分配的权限信息，主要用于用户分配角色时使用
	 * @return 角色信息
	 */
	@RequestMapping("findAuthRole")
	List<Role> findAuthRole();
	
	/**
	 * 获取当前登录用户有权限的组织信息
	 * @return 组织信息
	 */
	@RequestMapping("findOrgTree")
	List<TreeNode> findOrgTree();
	
	/**
	 * 获取当前登录用户的用户信息
	 */
	@RequestMapping("findUserInfo")
	Map<String,Object> findUserInfo();
}
