package com.jokerchen.pinellia.console.auth.dao;

import java.util.List;
import java.util.Set;

import com.jokerchen.pinellia.common.dao.BaseDao;
import com.jokerchen.pinellia.common.model.TreeNode;
import com.jokerchen.pinellia.console.auth.vo.MenuTreeVO;
import com.jokerchen.pinellia.console.role.entity.Role;

/**   
 * @description: 权限相关查询接口
 * @author Joker Chen 
 * @date 2019-03-21 08:56:51  
 */
public interface AuthDao extends BaseDao<Object>{

	/**
	 * 通过用户名获取该用户所配有的菜单信息
	 * @param username
	 * @return
	 */
	List<MenuTreeVO> findMenuByUsername(String username);
	
	/**
	 * 通过用户名获取用户所配置的角色信息
	 * @param username
	 * @return
	 */
	List<Role> findRoleByUsername(String username);

	/**
	 * 删除角色下的菜单关系
	 * @param roleId 角色id
	 */
	void deleteRoleMenu(int roleId);
	
	/**
	 * 删除角色下的用户关系
	 * @param roleId 角色id
	 */
	void deleteRoleUser(int roleId);

	/**
	 * 为角色添加菜单权限
	 * @param roleId 角色id
	 * @param menuCodes 菜单编码
	 */
	void saveRoleMenu(int roleId, String[] menuCodes);
	
	/**
	 * 删除菜单相关的配置
	 * @param menuCode 菜单编码
	 */
	void deleteMenuRole(String menuCode);
	
	/**
	 * 获取当前登录用户有权限的组织信息
	 * @param username	当前登录用户
	 * @return 组织信息
	 */
	List<String> findOrgByUsername(String username);
	
	/**
	 * 查询某些组织编码下的所有组织信息
	 * @param codes
	 * @return
	 */
	List<TreeNode> findOrgByCodes(List<String> codes);
	
	/**
	 * 获取当前登陆用户的有权限进行分配的权限信息，主要用于用户分配角色时使用
	 * @param orgCode	当前用户有权限的组织信息
	 * @return 角色信息
	 */
	List<Role> findAuthRole(Set<String> orgCode);
	
	/**
	 * 保存用户的组织信息
	 * @param username 用户名
	 * @param orgCodes 组织编码
	 */
	void saveUserOrg(String username,  String[] orgCodes);
	
	/**
	 * 删除用户有权限的所有组织
	 * @param username 用户名
	 */
	void deleteUserOrg(String username);
	
	/**
	 * 给用户添加角色
	 * @param username 用户名
	 * @param roleIds  角色信息
	 */
	void saveUserRole(String username, Object[] roleIds);
	
	/**
	 * 删除用户的所有角色
	 * @param username 用户名
	 */
	void deleteUserRole(String username);
}
