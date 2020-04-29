package com.jokerchen.pinellia.console.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jokerchen.pinellia.console.user.entity.User;
import com.jokerchen.pinellia.console.user.service.UserService;

/**   
 * @description: 登录功能逻辑处理实现
 * 					这里实现UserDetailsService接口是为了重写security的校验接口，以处理用户信息从数据库读取
 * @author Joker Chen 
 * @date 2019-03-14 09:10:02  
 */
@Service
public class LoginService implements UserDetailsService{

	@Autowired
	private UserService userService;

	/**
	 * 重写security的接口，用于从数据库读取用户信息，进行登录校验
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		User defUser = userService.findByUsername(username);
		if(defUser == null) throw new UsernameNotFoundException(username + " do not exist!");
		UserDetails user = UserInfo.withUsername(defUser.getUsername())
				.password(encoder.encode(defUser.getPassword()));
		/*UserDetails user = User.withUsername(defUser.getUsername())
                .password(encoder.encode(defUser.getPassword()))
                //因为没有加角色的话。一开始通不过，所以先默认一个role
                .roles("role")
                .build();*/
        return user;
	}
	
	

}
