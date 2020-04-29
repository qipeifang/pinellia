package com.jokerchen.pinellia.console.login;

import java.lang.reflect.Field;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.jokerchen.pinellia.common.util.MD5;
import com.jokerchen.pinellia.common.util.StringUtil;
import com.jokerchen.pinellia.console.common.service.CommonService;
import com.jokerchen.pinellia.console.user.entity.User;
import com.jokerchen.pinellia.console.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**   
 * @description: 登录时对登录信息的处理，当前使用security默认的处理方式，
 * 				后面如果登录时需要做额外的处理，例如增加校验码，可以使用这个类进行处理
 * @author Joker Chen 
 * @date 2019-03-14 14:30:56  
 */
@Slf4j
@Component
public class SecurityAuthenticationProvider extends DaoAuthenticationProvider {

	@Autowired
	private CommonService commonService;
	
	@Autowired
	private UserService userService;
	
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		try {
			//增加一个微信登录的逻辑
			HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
			String wxLogin = request.getParameter("wxLogin");
			if(StringUtil.isNotEmpty(wxLogin) && wxLogin.equals("true")) {	
				//微信登录
				//在这里通过code获取用户的信息，构建登录用户信息
				String jsCode = request.getParameter("jsCode");
				String encryptedData = request.getParameter("encryptedData");
				String iv = request.getParameter("iv");
				
				//获取微信标识
				String wxId = commonService.getWxId(jsCode, encryptedData, iv);
				if(StringUtil.isNotEmpty(wxId)) {
					//通过微信标识获取用户信息
					User user = userService.findByWxId(wxId);
					//能获取到用户信息，说明以前用微信登录过，可以直接通过微信登录
					if(user != null) {
						//获取到了用户信息，说明可以直接登录
						//用户名放入
						Field field = authentication.getClass().getDeclaredField("principal");
						field.setAccessible(true);
						field.set(authentication, user.getUsername());
						field.setAccessible(false);
						//密码放入
						field = authentication.getClass().getDeclaredField("credentials");
						field.setAccessible(true);
						field.set(authentication, user.getPassword());
						field.setAccessible(false);
					}
				}
			}else {
				//正常用户名/密码登录
				// 这个是表单中输入的密码
				String password = (String) authentication.getCredentials();
				if(password != null && !password.isEmpty()) {
					//为了避免在数据库中密码保存为明文，所以会在数据库中保存的用户密码会用{link MD5}加密。
					//因此这里需要对前端传过来的密码先用MD5处理一下，之后的校验就交给security处理
					Field field = authentication.getClass().getDeclaredField("credentials");
					field.setAccessible(true);
					field.set(authentication, MD5.MD5Encode(password));
					field.setAccessible(false);
				}
			}
			
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
        return super.authenticate(authentication);
    }
    
    /**
     * 自定义用于用户登录时，用户名、密码校验的处理
     */
    @Override
    @Autowired
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		super.setUserDetailsService(userDetailsService);
	}
}
