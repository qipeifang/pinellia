package com.jokerchen.pinellia.common.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

/**   
 * @description: 用户会话信息拦截器，用于对用户会话信息做校验
 * @author Joker Chen 
 * @date 2019-03-13 16:03:59  
 */
@Slf4j
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
   
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //HttpSession session = request.getSession();
        //Object userInfo = session.getAttribute("userInfo");
        log.debug(request.getRequestURL().toString());
        log.debug(request.getRequestURI().toString());
        //未设置session
        /*if (userInfo == null) {
            return false;
        }*/
        return super.preHandle(request, response, handler);
    }


}
