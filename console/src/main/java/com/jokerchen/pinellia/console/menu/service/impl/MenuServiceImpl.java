package com.jokerchen.pinellia.console.menu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jokerchen.pinellia.common.constant.Constant;
import com.jokerchen.pinellia.common.exception.ServiceException;
import com.jokerchen.pinellia.common.model.TreeNode;
import com.jokerchen.pinellia.common.util.TreeUtil;
import com.jokerchen.pinellia.console.auth.service.AuthService;
import com.jokerchen.pinellia.console.menu.dao.MenuDao;
import com.jokerchen.pinellia.console.menu.entity.Menu;
import com.jokerchen.pinellia.console.menu.service.MenuService;
import com.jokerchen.pinellia.console.menu.vo.MenuVO;
import com.jokerchen.pinellia.console.role.dao.RoleDao;
import com.jokerchen.pinellia.console.role.entity.Role;

/**   
 * @description: 菜单管理
 * @author Joker Chen 
 * @date 2019-03-13 16:36:43  
 */
@Service
public class MenuServiceImpl implements MenuService{

	@Autowired
	private MenuDao menuDao;

	@Autowired
	private AuthService authService;
	
	@Autowired
	private RoleDao roleDao;

	@Override
	public List<TreeNode> findMenu() {
		List<MenuVO> res = menuDao.findAllMenu();
		List<TreeNode> menuVO = TreeUtil.buildTree(res);
		return menuVO;
	}

	@Override
	public void deleteMenu(int id) {
		Menu menu = menuDao.getOne(id);
		menuDao.delete(menu);
		authService.deleteMenuRole(menu.getCode());
	}

	@Override
	public void disableMenu(int id) {
		Menu menu = menuDao.getOne(id);
		menu.setState(Constant.DATA_STATE_DISABLE);
		menuDao.update(menu);
	}

	@Override
	public void enableMenu(int id) {
		Menu menu = menuDao.getOne(id);
		menu.setState(Constant.DATA_STATE_NORMAL);
		menuDao.update(menu);
	}

	@Override
	public void saveMenu(Menu menu) {
		boolean flag = menuDao.existCode(menu.getCode(),menu.getId());
		if(flag) throw new ServiceException(menu.getCode()+"，菜单编码已经存在");
		if(menu.getId() > 0) {
			Menu entity = menuDao.getOne(menu.getId());
			if(entity != null && !menu.getCode().equals(entity.getCode())) {
				//菜单编码有变化，先删除之前已经给该菜单编码配置的角色权限
				authService.deleteMenuRole(entity.getCode());
				//将新的菜单编码权限赋给超管角色
				List<Role> roles = roleDao.findByField("authType", Constant.AUTH_TYPE_SUPER_ADMIN);
				if(roles != null && roles.size()>0) {
					roles.forEach(item->authService.addRoleMenu(item.getRoleId(), menu.getCode()));
				}
			}
			//因为这里获取了新的对象，所以需要对这个对象做持久化操作，否则会报错
			entity.setAuthType(menu.getAuthType());
			entity.setCode(menu.getCode());
			entity.setFunc(menu.getFunc());
			entity.setLevel(menu.getLevel());
			entity.setMapping(menu.getMapping());
			entity.setName(menu.getName());
			entity.setParentCode(menu.getParentCode());
			entity.setSequence(menu.getSequence());
			entity.setState(menu.getState());
			entity.setType(menu.getType());
			menuDao.update(entity);
		}else {
			//将新的菜单编码权限赋给超管角色
			List<Role> roles = roleDao.findByField("authType", Constant.AUTH_TYPE_SUPER_ADMIN);
			if(roles != null && roles.size()>0) {
				roles.forEach(item->authService.addRoleMenu(item.getRoleId(), menu.getCode()));
			}
			menuDao.save(menu);
		}
	}

}
