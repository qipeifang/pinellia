package com.jokerchen.pinellia.console.user.controller.impl;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.jokerchen.pinellia.common.annotation.OperationLogger;
import com.jokerchen.pinellia.common.constant.Constant;
import com.jokerchen.pinellia.common.model.Page;
import com.jokerchen.pinellia.console.user.controller.UserController;
import com.jokerchen.pinellia.console.user.service.UserService;
import com.jokerchen.pinellia.console.user.vo.UserVO;

/**   
 * @description: 用户信息mvc控制层
 * @author Joker Chen 
 * @date 2019-03-12 17:21:08  
 */
@RestController
public class UserControllerImpl implements UserController{

	@Autowired
	private UserService userService;

	@Override
	public Page findUserPage(String username) {
		Page page = userService.findUserPage(username);
		return page;
	}
	
	@Override
	@OperationLogger(operationType=Constant.OPERATION_SAVE,operationDesc="保存用户信息")
	public void saveUser(@Valid UserVO user) {
		userService.saveUser(user);
	}

	@Override
	public void updatePassword(@NotNull String oldPassword, @NotBlank String newPassword) {
		userService.updatePassword(oldPassword, newPassword);
	}
}
