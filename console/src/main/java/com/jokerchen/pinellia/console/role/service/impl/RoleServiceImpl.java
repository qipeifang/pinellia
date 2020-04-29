package com.jokerchen.pinellia.console.role.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jokerchen.pinellia.common.constant.Constant;
import com.jokerchen.pinellia.common.exception.ServiceException;
import com.jokerchen.pinellia.common.model.Page;
import com.jokerchen.pinellia.console.auth.service.AuthService;
import com.jokerchen.pinellia.console.login.SecurityUtil;
import com.jokerchen.pinellia.console.role.dao.RoleDao;
import com.jokerchen.pinellia.console.role.entity.Role;
import com.jokerchen.pinellia.console.role.service.RoleService;
import com.jokerchen.pinellia.console.role.vo.RoleVO;

/**   
 * @description: 
 * @author Joker Chen 
 * @date 2019-03-12 17:14:31  
 */
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
    private RoleDao roleDao;

	@Autowired
	private AuthService authService;

	@Override
	public Page findRolePage(String roleName) {
		//先获取当前登录用户有权限的角色
		List<Role> list = SecurityUtil.getLoginUserRole();
		Set<Integer> roleId = list.stream().map(Role::getRoleId).collect(Collectors.toSet());
		//获取当前登录用户有权限的组织机构信息
		Set<String> orgCode = SecurityUtil.getLoginUserOrgCode();
		//获取当前用户有权限的所有角色信息
		Page page = roleDao.findRolePage(roleName,orgCode,roleId);
		List<?> roles = page.getContent();
		if(roles != null && roles.size() > 0) {
			roles.forEach(item->{
				RoleVO roleVO = (RoleVO) item;
				roleVO.setEditFlag(orgCode.contains(roleVO.getOrgCode()));
			});
		}
		return page;
	}

	@Override
	public void saveRole(Role role, String menuCode) {
		Role entity = roleDao.getOne(role.getRoleId());
		if(entity != null) {
			if(entity.getAuthType() == Constant.AUTH_TYPE_SUPER_ADMIN) {
				throw new ServiceException("不能编辑超级管理员");
			}
			entity.setRoleName(role.getRoleName());
			entity.setOrgCode(role.getOrgCode());
		}else {
			entity = role;
			entity.setAuthType(Constant.AUTH_TYPE_OPERATOR);
		}
		roleDao.saveOrUpdate(entity);
		//保存角色和菜单关系
		authService.saveRoleMenu(entity.getRoleId(), menuCode);
	}

	@Override
	public void deleteRole(int id) {
		authService.deleteRoleMenu(id);
		authService.deleteRoleUser(id);
		roleDao.delete(roleDao.getOne(id));
	}
}
