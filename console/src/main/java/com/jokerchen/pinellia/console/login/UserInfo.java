package com.jokerchen.pinellia.console.login;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import com.jokerchen.pinellia.common.model.TreeNode;
import com.jokerchen.pinellia.console.role.entity.Role;

/**
 * @description: 主要用于处理登陆相关的用户信息的处理
 * @author Joker Chen
 * @date 2019-03-14 09:24:00
 */
public class UserInfo implements UserDetails {

	private static final long serialVersionUID = 5243285244485076610L;

	private String username;
	private String password;
	private List<Role> roles;
	/** 当前登陆用户所拥有的菜单信息 */
	private List<TreeNode> menus;
	/** 当前登陆用户所拥有的api权限信息 */
	private Set<String> mapping;
	private Set<? extends GrantedAuthority> authorities;
	/** 当前登陆用户所拥有权限的组织编码信息 */
	private Set<String> orgCode;
	/** 当前登陆用户所拥有权限的组织信息，树形结构 */
	private List<TreeNode> orgTree;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<TreeNode> getMenus() {
		return menus;
	}

	public void setMenus(List<TreeNode> menus) {
		this.menus = menus;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
    public void setAuthorities(Set<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

	public Set<String> getMapping() {
		return mapping;
	}

	public void setMapping(Set<String> mapping) {
		this.mapping = mapping;
	}

	public Set<String> getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(Set<String> orgCode) {
		this.orgCode = orgCode;
	}

	public List<TreeNode> getOrgTree() {
		return orgTree;
	}

	public void setOrgTree(List<TreeNode> orgTree) {
		this.orgTree = orgTree;
	}

	/**
	 * Creates a UserInfo with a specified user name
	 *
	 * @param username the username to use
	 * @return the UserInfo
	 */
	public static UserInfo withUsername(String username) {
		return builder().username(username);
	}
	
	/**
	 * Creates a UserInfo
	 * @return the UserInfo
	 */
	public static UserInfo builder() {
		return new UserInfo();
	}

	public UserInfo username(String username) {
		Assert.notNull(username, "username cannot be null");
		this.username = username;
		return this;
	}
	
	public UserInfo password(String password) {
		Assert.notNull(password, "password cannot be null");
		this.password = password;
		return this;
	}

}
