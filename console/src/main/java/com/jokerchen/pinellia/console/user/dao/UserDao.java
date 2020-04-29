package com.jokerchen.pinellia.console.user.dao;

import java.util.Set;

import com.jokerchen.pinellia.common.dao.BaseDao;
import com.jokerchen.pinellia.common.model.Page;
import com.jokerchen.pinellia.console.user.entity.User;

/**   
 * @description: 用户信息持久层
 * @author Joker Chen 
 * @date 2019-03-13 10:09:39  
 */
public interface UserDao extends BaseDao<User> {
	
	
	/**
	 * 分页查询用户信息
	 * @param username	用户名称
	 * @param orgCode	组织编码
	 * @return
	 */
	Page findUserPage(String username, Set<String> orgCode);
	
	/**
	 * 判断用户名是否已经存在
	 * @param username
	 * @param id
	 * @return
	 */
	boolean existUsername(String username, int id);
	
}
