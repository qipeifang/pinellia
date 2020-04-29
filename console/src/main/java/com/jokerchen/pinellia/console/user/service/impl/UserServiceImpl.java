package com.jokerchen.pinellia.console.user.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jokerchen.pinellia.common.constant.Constant;
import com.jokerchen.pinellia.common.constant.SysParamConstant;
import com.jokerchen.pinellia.common.exception.ServiceException;
import com.jokerchen.pinellia.common.model.Page;
import com.jokerchen.pinellia.common.sysParam.service.SysParamService;
import com.jokerchen.pinellia.common.util.MD5;
import com.jokerchen.pinellia.console.auth.service.AuthService;
import com.jokerchen.pinellia.console.login.SecurityUtil;
import com.jokerchen.pinellia.console.user.dao.UserDao;
import com.jokerchen.pinellia.console.user.entity.User;
import com.jokerchen.pinellia.console.user.service.UserService;
import com.jokerchen.pinellia.console.user.vo.UserVO;

/**   
 * @description: 
 * @author Joker Chen 
 * @date 2019-03-12 17:14:31  
 */
@Service
public class UserServiceImpl implements UserService{

	@Autowired
    private UserDao userDao;
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private SysParamService sysParamService;

	@Override
	public User findByUsername(String username) {
		User user = userDao.getOneByField("username",username);
		if(user != null && user.getState() == Constant.DATA_STATE_NORMAL) {
			return user;
		}
		return null;
	}
	
	@Override
	public User findByWxId(String wxId) {
		User user = userDao.getOneByField("wxId", wxId);
		if(user != null && user.getState() == Constant.DATA_STATE_NORMAL) {
			return user;
		}
		return null;
	}

	@Override
	public Page findUserPage(String username){
		//获取当前登录用户有权限的组织机构信息
		Set<String> orgCode = SecurityUtil.getLoginUserOrgCode();
		return userDao.findUserPage(username, orgCode);
	}
	
	@Override
	public void saveUser(UserVO userVO) {
		//先验证用户名是否已经存在，用户名不可重复，如果存在，则抛异常，不允许保存
		boolean flag = userDao.existUsername(userVO.getUsername(), userVO.getId());
		if(flag) throw new ServiceException(userVO.getUsername()+"，用户名已经存在");
		User entity = null;
		if(userVO.getId() > 0) {
			entity = userDao.getOne(userVO.getId());
		}
		if(entity == null) {
			entity = new User();
			entity.setUsername(userVO.getUsername());
			//先默认所有用户的初始化密码都为123456
			entity.setPassword(MD5.MD5Encode(
					sysParamService.getSysParamValue(SysParamConstant.INIT_USER_PASSWORD)));
		}else {
		}
		userDao.saveOrUpdate(entity);
		// 保存用户的组织信息
		authService.saveUserOrg(userVO.getUsername(), userVO.getOrgCode());
		// 保存用户的角色信息
		authService.saveUserRole(userVO.getUsername(), userVO.getRoleId());
	}
	
	@Override
	public void updateUser(User user) {
		userDao.update(user);
	}

	@Override
	public void updatePassword(String oldPassword, String newPassword) {
		User user = findByUsername(SecurityUtil.getLoginUsername());
		if(user.getPassword().equals(MD5.MD5Encode(oldPassword))){
			user.setPassword(MD5.MD5Encode(newPassword));
			userDao.update(user);
		}else{
			throw new ServiceException("密码错误");
		}
	}

}
