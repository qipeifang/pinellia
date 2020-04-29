package com.jokerchen.pinellia.console.user.service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.transaction.annotation.Transactional;

import com.jokerchen.pinellia.common.model.Page;
import com.jokerchen.pinellia.console.user.entity.User;
import com.jokerchen.pinellia.console.user.vo.UserVO;

/**   
 * @description: 用户信息逻辑处理层
 * @author Joker Chen 
 * @date 2019-03-12 17:14:05  
 */
public interface UserService {

	/**
	 * 通过用户名获取用户信息
	 * @param username
	 * @return
	 */
	User findByUsername(String username);
	
	/**
	 * 通过微信的unionId或者openId获取用户信息
	 * @param wxId	unionId或者openId
	 * @return
	 */
	User findByWxId(String wxId);
	
	/**
	 * 分页查询用户信息
	 * @param username	用户名称
	 * @return
	 */
	Page findUserPage(String username);
	
    /**
     * 保存用户信息
     * @param user
     */
	@Transactional
    void saveUser(UserVO user);
	
	/**
	 * 修改用户信息
	 * @param user
	 */
	@Transactional
	void updateUser(User user);
	
    /**
     * 修改用户密码
     * @param oldPassword   旧密码
     * @param newPassword    新密码
     */
    void updatePassword(@NotNull String oldPassword, @NotBlank String newPassword);
}
