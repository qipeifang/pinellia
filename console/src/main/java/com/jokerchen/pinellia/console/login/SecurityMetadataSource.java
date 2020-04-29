package com.jokerchen.pinellia.console.login;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

/**   
 * @description: 通过当前的请求地址，获取该地址需要的用户角色
 * @author Joker Chen 
 * @date 2019-03-14 13:59:14  
 */
@Component
public class SecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	
    AntPathMatcher antPathMatcher = new AntPathMatcher();
    
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) {
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        if(requestUrl.startsWith("/html/")||requestUrl.startsWith("/css/")
                ||requestUrl.startsWith("/js/")||requestUrl.startsWith("/img/")
                ||requestUrl.equals("/index.html")){
            return SecurityConfig.createList("NONE");
        }
        Set<String> mapping = SecurityUtil.getLoginUserMapping();
        for(String item : mapping){
            if (antPathMatcher.match(item, requestUrl)){
                return SecurityConfig.createList("SUCCESS");
            }
        }
        //没有匹配上的资源，都是登录访问
        return SecurityConfig.createList("NEED_LOGIN");
    }
    
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }
    
    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
