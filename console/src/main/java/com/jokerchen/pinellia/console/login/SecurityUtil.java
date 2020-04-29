package com.jokerchen.pinellia.console.login;

import java.util.List;
import java.util.Set;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.jokerchen.pinellia.common.model.TreeNode;
import com.jokerchen.pinellia.console.role.entity.Role;

import lombok.extern.slf4j.Slf4j;

/**   
 * @description: 因为登录的校验控制是交给security的，所以在这里增加一个security的工具类，用于获取与登录相关的一些信息，比如当前登录用户名等 
 * @author Joker Chen 
 * @date 2019-03-21 09:07:34  
 */
@Slf4j
public class SecurityUtil {

	/**
	 * 获取当前登录的用户的用户名
	 * @return
	 */
	public static String getLoginUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    String currentUserName = authentication.getName();
		    return currentUserName;
		}else {
			return null;
		}
	}

	/**
	 * 获取当前登陆用户的角色id
	 */
	public static List<Role> getLoginUserRole(){
		UserInfo userVO = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(userVO != null) {
			return userVO.getRoles();
		}
		return null;
	}
	
	/**
	 * 设置当前登录用户的角色信息
	 * @param roles
	 */
	public static void setLoginUserRole(List<Role> roles) {
		UserInfo userVO = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(userVO != null) {
			log.info(userVO.getUsername()+"用户加载角色信息成功。");
			userVO.setRoles(roles);
		}
	}
	
	/**
	 * 获取当前登录用户的菜单权限信息
	 */
	public static List<TreeNode> getLoginUserMenu() {
		UserInfo userVO = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(userVO != null) {
			return userVO.getMenus();
		}
		return null;
	}
	
	/**
	 * 设置当前登录用户的菜单信息
	 * @param menus
	 */
	public static void setLoginUserMenu(List<TreeNode> menus) {
		UserInfo userVO = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(userVO != null) {
			log.info(userVO.getUsername()+"用户加载菜单信息成功。");
			userVO.setMenus(menus);
		}
	}

	/**
	 * 获取当前登录用户的菜单权限信息
	 */
	public static Set<String> getLoginUserMapping() {
		UserInfo userVO = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(userVO != null) {
			return userVO.getMapping();
		}
		return null;
	}

	/**
	 * 设置当前登录用户的接口信息
	 * @param mapping
	 */
	public static void setLoginUserMapping(Set<String> mapping) {
		UserInfo userVO = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(userVO != null) {
			log.info(userVO.getUsername()+"用户加载接口信息成功。");
			userVO.setMapping(mapping);
		}
	}
	
	/**
	 * 获取当前登录用户的菜单权限信息
	 */
	public static Set<String> getLoginUserOrgCode() {
		UserInfo userVO = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(userVO != null) {
			return userVO.getOrgCode();
		}
		return null;
	}

	/**
	 * 设置当前登录用户的接口信息
	 * @param mapping
	 */
	public static void setLoginUserOrgCode(Set<String> orgCode) {
		UserInfo userVO = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(userVO != null) {
			log.info(userVO.getUsername()+"用户加载组织信息成功。");
			userVO.setOrgCode(orgCode);
		}
	}
	
	/**
	 * 获取当前登录用户的菜单权限信息
	 */
	public static List<TreeNode> getLoginUserOrgTree() {
		UserInfo userVO = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(userVO != null) {
			return userVO.getOrgTree();
		}
		return null;
	}

	/**
	 * 设置当前登录用户的接口信息
	 * @param mapping
	 */
	public static void setLoginUserOrgTree(List<TreeNode> orgTree) {
		UserInfo userVO = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(userVO != null) {
			log.info(userVO.getUsername()+"用户加载组织信息成功。");
			userVO.setOrgTree(orgTree);
		}
	}
}
