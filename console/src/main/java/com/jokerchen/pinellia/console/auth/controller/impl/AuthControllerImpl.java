package com.jokerchen.pinellia.console.auth.controller.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.jokerchen.pinellia.common.model.TreeNode;
import com.jokerchen.pinellia.console.auth.controller.AuthController;
import com.jokerchen.pinellia.console.auth.service.AuthService;
import com.jokerchen.pinellia.console.login.SecurityUtil;
import com.jokerchen.pinellia.console.role.entity.Role;

/**   
 * @description: 权限管理控制层实现
 * @author Joker Chen 
 * @date 2019-03-15 09:10:32  
 */
@RestController
public class AuthControllerImpl implements AuthController{

	@Autowired
	private AuthService authService;
	
	@Override
	public List<TreeNode> findMenu() {
		return SecurityUtil.getLoginUserMenu();
	}

	@Override
	public List<TreeNode> findUserMenu(){
		return authService.findUserMenu();
	}

	@Override
	public void saveRoleMenu(int roleId, String menuCodes) {
		authService.saveRoleMenu(roleId, menuCodes);
	}

	@Override
	public List<Role> findRole(){
		return SecurityUtil.getLoginUserRole();
	}
	
	/**
	 * 获取当前登陆用户的有权限进行分配的权限信息，主要用于用户分配角色时使用
	 * @return 角色信息
	 */
	@Override
	public List<Role> findAuthRole(){
		return authService.findAuthRole();
	}

	@Override
	public List<TreeNode> findOrgTree() {
		return SecurityUtil.getLoginUserOrgTree();
	}

	@Override
	public Map<String,Object> findUserInfo() {
		Map<String,Object> map = new HashMap<>();
		map.put("username", SecurityUtil.getLoginUsername());
		return map;
	}
	
}
