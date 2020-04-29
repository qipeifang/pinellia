package com.jokerchen.pinellia.console.auth.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.jokerchen.pinellia.common.model.TreeNode;
import com.jokerchen.pinellia.console.role.entity.Role;

/**
 * @description: 权限管理逻辑处理层
 * @author Joker Chen 
 * @date 2019-03-15 09:15:04  
 */
public interface AuthService {

	/**
	 * 设置登录用户的角色信息
	 */
	void setLoginUserRole();
	
	/**
	 * 设置登录用户的菜单信息
	 */
	void setLoginUserMenu();
	
	/**
	 * 设置当前登录用户的组织信息
	 */
	void setLoginUserOrg();

	/**
	 * 获取当前登录用户的所有的菜单信息，用于菜单权限的分配
	 * @return
	 */
	List<TreeNode> findUserMenu();

	/**
	 * 为角色添加菜单权限
	 * @param roleId 角色id
	 * @param menuCodes 菜单编码，多个用逗号隔开
	 */
	@Transactional
	void saveRoleMenu(int roleId, String menuCodes);
	
	/**
	 * 为角色添加菜单权限
	 * @param roleId 角色id
	 * @param menuCode 菜单编码
	 */
	@Transactional
	void addRoleMenu(int roleId, String menuCode);
	
	/**
	 * 删除角色下的菜单关系
	 * @param roleId 角色id
	 */
	@Transactional
	void deleteRoleMenu(int roleId);
	
	/**
	 * 删除角色下的用户关系
	 * @param roleId 角色id
	 */
	@Transactional
	void deleteRoleUser(int roleId);
	
	/**
	 * 删除菜单相关的配置
	 * @param menuCode 菜单编码
	 */
	@Transactional
	void deleteMenuRole(String menuCode);
	
	/**
	 * 获取当前登陆用户的有权限进行分配的权限信息，主要用于用户分配角色时使用
	 * @return 角色信息
	 */
	List<Role> findAuthRole();
	
	/**
	 * 保存用户的组织信息，会先删除原先的用户组织关系信息，在保存
	 * @param username 用户名
	 * @param orgCodes 组织编码
	 */
	@Transactional
	void saveUserOrg(String username, String orgCodes);
	
	/**
	 * 将组织添加给该用户，不会删除原先保存的信息
	 * @param username 用户名
	 * @param orgCode 组织编码
	 */
	@Transactional
	void addUserOrg(String username, String orgCode);
	
	/**
	 * 删除用户有权限的所有组织
	 * @param username 用户名
	 */
	@Transactional
	void deleteUserOrg(String username);
	
	/**
	 * 给用户添加角色，会先删除原先的用户组织关系信息，再保存
	 * @param username 用户名
	 * @param roleIds  角色信息
	 */
	@Transactional
	void saveUserRole(String username, String roleIds);
	
	/**
	 * 给用户添加角色，不会删除原先保存的信息
	 * @param username 用户名
	 * @param roleId   角色信息
	 */
	@Transactional
	void addUserRole(String username, int roleId);
	
	/**
	 * 删除用户的所有角色
	 * @param username 用户名
	 */
	@Transactional
	void deleteUserRole(String username);
}
