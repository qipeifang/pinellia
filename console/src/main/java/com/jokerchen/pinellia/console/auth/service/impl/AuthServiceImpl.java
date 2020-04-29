package com.jokerchen.pinellia.console.auth.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jokerchen.pinellia.common.constant.Constant;
import com.jokerchen.pinellia.common.model.TreeNode;
import com.jokerchen.pinellia.common.util.StringUtil;
import com.jokerchen.pinellia.common.util.TreeUtil;
import com.jokerchen.pinellia.console.auth.dao.AuthDao;
import com.jokerchen.pinellia.console.auth.service.AuthService;
import com.jokerchen.pinellia.console.auth.vo.MenuTreeVO;
import com.jokerchen.pinellia.console.login.SecurityUtil;
import com.jokerchen.pinellia.console.role.entity.Role;

/**   
 * @description: 权限管理逻辑实现层
 * @author Joker Chen 
 * @date 2019-03-15 09:15:10  
 */
@Service
public class AuthServiceImpl implements AuthService{

	@Autowired
	private AuthDao authDao;
	
	@Override
	public void setLoginUserRole() {
		//获取当前登录用户名
		String username = SecurityUtil.getLoginUsername();
		if(username != null && !username.trim().isEmpty()) {
			//通过用户名获取角色信息
			List<Role> list = authDao.findRoleByUsername(username);
			//将角色信息放到security中
			SecurityUtil.setLoginUserRole(list);
		}
	}

	@Override
	public void setLoginUserMenu() {
		//获取当前登录用户名
		String username = SecurityUtil.getLoginUsername();
		if(username != null && !username.trim().isEmpty()) {
			//读取有权限的菜单信息
			List<MenuTreeVO> res = authDao.findMenuByUsername(username);
			//获取可以访问的接口信息，用于后台对请求的拦截
			Set<String> mapping = res.stream().map(MenuTreeVO::getMapping)
					.filter(item-> StringUtil.isNotEmpty(item))
					.collect(Collectors.toSet());
			//设置到security中
			SecurityUtil.setLoginUserMapping(mapping);
			//过滤出用来前台展示的菜单信息
			List<MenuTreeVO>  list = res.stream()
					.filter(item->{
						//这个信息对于菜单的构建没有作用，在此设为空
						item.setMapping(null);
						return item.getType() != null && item.getType().equals(Constant.MENU_TYPE_MENU);
					})
					.collect(Collectors.toList());
			//构建成菜单树
			List<TreeNode> menuVO = TreeUtil.buildTree(list);
			//设置到security中
			SecurityUtil.setLoginUserMenu(menuVO);
		}
	}
	
	@Override
	public void setLoginUserOrg() {
		//获取当前登录用户名
		String username = SecurityUtil.getLoginUsername();
		if(username != null && !username.trim().isEmpty()) {
			//先读取当前用户所配置的组织信息
			List<String> res = authDao.findOrgByUsername(username);
			if(res != null && res.size() > 0) {
				//获取当前组织及其下属组织信息
				List<TreeNode> org = authDao.findOrgByCodes(res);
				//获取有权限的组织结构的编码信息
				Set<String> orgCode = org.stream().map(TreeNode::getCode).collect(Collectors.toSet());
				//设置到security中
				SecurityUtil.setLoginUserOrgCode(orgCode);
				//构建组织树
				List<TreeNode> orgVO = TreeUtil.buildTree(org);
				//设置到security中
				SecurityUtil.setLoginUserOrgTree(orgVO);
			}
		}
	}

	@Override
	public List<TreeNode> findUserMenu(){
		//获取当前登录用户名
		String username = SecurityUtil.getLoginUsername();
		if(username != null && !username.trim().isEmpty()) {
			//读取有权限的菜单信息
			List<MenuTreeVO> res = authDao.findMenuByUsername(username);
			//过滤出用来前台展示的菜单信息
			List<TreeNode> menuVO = TreeUtil.buildTree(res);
			return menuVO;
		}
		return null;
	}

	@Override
	public void saveRoleMenu(int roleId, String menuCodes) {
		//先删除之前的配置
		authDao.deleteRoleMenu(roleId);
		if(menuCodes != null && !menuCodes.trim().isEmpty()){
			String[] menuCodeArray = menuCodes.split(",");
			//增加新的配置
			authDao.saveRoleMenu(roleId,menuCodeArray);
		}
	}
	
	@Override
	public void addRoleMenu(int roleId, String menuCode) {
		if(menuCode != null && !menuCode.trim().isEmpty()){
			//增加新的配置
			authDao.saveRoleMenu(roleId, new String[]{menuCode});
		}
	}

	@Override
	public void deleteRoleMenu(int roleId) {
		authDao.deleteRoleMenu(roleId);
	}
	
	@Override
	public void deleteRoleUser(int roleId) {
		authDao.deleteRoleUser(roleId);
	}

	@Override
	public void deleteMenuRole(String menuCode) {
		authDao.deleteMenuRole(menuCode);
	}

	@Override
	public List<Role> findAuthRole(){
		List<Role> list = SecurityUtil.getLoginUserRole();
		//获取当前登录用户有权限的组织机构信息
		Set<String> orgCode = SecurityUtil.getLoginUserOrgCode();
		List<Role> roles = authDao.findAuthRole(orgCode);
		roles.addAll(list);
		roles = roles.stream().distinct().collect(Collectors.toList());
		return roles;
	}

	@Override
	public void saveUserOrg(String username, String orgCodes) {
		//先删除之前的配置
		authDao.deleteUserOrg(username);
		if(orgCodes != null && !orgCodes.trim().isEmpty()){
			String[] orgCodeArray = orgCodes.split(",");
			//增加新的配置
			authDao.saveUserOrg(username, orgCodeArray);
		}
		
	}

	@Override
	public void addUserOrg(String username, String orgCode) {
		if(orgCode != null && !orgCode.trim().isEmpty()){
			//增加新的配置
			authDao.saveUserOrg(username, new String[]{orgCode});
		}
	}

	@Override
	public void deleteUserOrg(String username) {
		//删除配置
		authDao.deleteUserOrg(username);
	}

	@Override
	public void saveUserRole(String username, String roleIds) {
		//先删除之前的配置
		authDao.deleteUserRole(username);
		if(roleIds != null && !roleIds.trim().isEmpty()){
			String[] roleIdArray = roleIds.split(",");
			//增加新的配置
			authDao.saveUserRole(username, roleIdArray);
		}
	}

	@Override
	public void addUserRole(String username, int roleId) {
		//增加新的配置
		authDao.saveUserRole(username, new Object[]{roleId});
	}

	@Override
	public void deleteUserRole(String username) {
		//删除配置
		authDao.deleteUserRole(username);
	}
	
}
