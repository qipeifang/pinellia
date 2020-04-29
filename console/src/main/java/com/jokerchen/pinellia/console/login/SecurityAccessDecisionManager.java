package com.jokerchen.pinellia.console.login;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

/**   
 * @description: 用于判断决定当前用户是否有该菜单的访问权限
 * 已授权的用户请求权限之外的资源时会交给这个类处理
 * @author Joker Chen 
 * @date 2019-03-13 16:00:24  
 */
@Component
public class SecurityAccessDecisionManager implements AccessDecisionManager {

	AntPathMatcher antPathMatcher = new AntPathMatcher();

	/**
	 * @param auth 当前登录用户的角色信息
	 * @param o
	 * @param cas	UrlFilterInvocationSecurityMetadataSource中的getAttributes方法传来的，表示当前请求需要的角色（可能有多个）
	 */
	@Override
	public void decide(Authentication auth, Object o, Collection<ConfigAttribute> cas) {
		String requestUrl = ((FilterInvocation) o).getRequestUrl();
		for(String parten : PermitUrlConstant.staticUrl) {
			if(antPathMatcher.match(parten, requestUrl)) {
				// 静态资源信息，不做任何权限设置都允许访问
				return ;
			}
		}
		if (auth instanceof AnonymousAuthenticationToken) {
			throw new BadCredentialsException("未登录");
		}
		/*String username = SecurityUtil.getLoginUsername();
		if(username == null || username.isEmpty()){

		}*/
		Set<String> mapping = SecurityUtil.getLoginUserMapping();
		for(String item : mapping){
			if (antPathMatcher.match(item, requestUrl)){
				//只要用能匹配一个允许访问的mapping, 就说明有权限
				return ;
			}
		}
		//没有匹配上的资源，不允许访问呢
		throw new AccessDeniedException("权限不足!");
	}

	@Override
	public boolean supports(ConfigAttribute configAttribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return true;
	}
}
