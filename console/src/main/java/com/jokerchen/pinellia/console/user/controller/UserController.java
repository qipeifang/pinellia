package com.jokerchen.pinellia.console.user.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.RequestMapping;

import com.jokerchen.pinellia.common.model.Page;
import com.jokerchen.pinellia.console.user.vo.UserVO;

/**   
 * @description: 用户信息mvc控制层
 * @author Joker Chen 
 * @date 2019-03-12 17:20:51  
 */
@RequestMapping("/user")
public interface UserController {

    /**
     * 查询用户信息，返回分页数据
     * @param username 用户名称
     */
    @RequestMapping("/findUserPage")
    Page findUserPage(String username);
    
    /**
     * 保存用户信息
     * @param user
     */
    @RequestMapping("/saveUser")
    void saveUser(@Valid UserVO user);
    
    /**
     * 修改用户密码
     * @param oldPassword   旧密码
     * @param newPassword    新密码
     */
    @RequestMapping("updatePassword")
    void updatePassword(@NotNull String oldPassword, @NotBlank String newPassword);
}
