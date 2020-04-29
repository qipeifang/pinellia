package com.jokerchen.pinellia.console.login;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import com.alibaba.fastjson.JSON;
import com.jokerchen.pinellia.common.constant.ResponseEnum;
import com.jokerchen.pinellia.common.model.ResponseMessage;
import com.jokerchen.pinellia.console.auth.service.AuthService;

/**
 * @description: security 用于用户权限验证的配置，这里主要是修改所有的验证返回都是信息返回，而不是页面跳转，主要是为了前后端的分离
 * @author Joker Chen
 * @date 2019-03-13 15:33:23
 */
@Configuration
public class SecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

	/**
	 * 于判断决定当前用户是否有该菜单的访问权限
	 */
	@Autowired
	private SecurityAccessDecisionManager SecurityAccessDecisionManager;
	
	//@Autowired
	//private SecurityMetadataSource securityMetadataSource;
	
	/**
	 * 权限信息相关接口
	 */
	@Autowired
	private AuthService authService;
	
	/**
	 * 用于校验登录的用户信息是否正确
	 */
	//@Autowired
	//private LoginService loginService;
	/** 自定义安全认证 */
	@Autowired
	private SecurityAuthenticationProvider provider;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 加入自定义的安全认证，用来对前台传过来的信息做初步处理，比如验证码校验，密码加密等
		auth.authenticationProvider(provider);
		// 验证用户登录信息,因为已经在中定于了userDetailsService，所以这里不需要在增加定义，否则会导致
		// #｛link DaoAuthenticationProvider} 被创建两次
		//auth.userDetailsService(loginService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//返回信息的配置
		http.authorizeRequests()
				// "/login"不进行权限验证，其他的需要登陆后才能访问
				.antMatchers(PermitUrlConstant.permitUrl)
				.permitAll().anyRequest().authenticated()
				//对于以已经登录后的一些连接的访问做权限校验，验证用户是否有访问权限
				.withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
		            @Override
		            public <O extends FilterSecurityInterceptor> O postProcess(O o) {
		                //o.setSecurityMetadataSource(securityMetadataSource);
		                o.setAccessDecisionManager(SecurityAccessDecisionManager);
		                return o;
		            }
		        })
				.and().exceptionHandling().authenticationEntryPoint((request, response, authException) -> {
					response.setCharacterEncoding("UTF-8");
					// 处理未登录时返回信息，而不是页面跳转，返回未登录信息
					writeMessage(response,ResponseEnum.FORBIDDEN.code(), ResponseEnum.FORBIDDEN.message());
				}).and().formLogin()
				// loginProcessingUrl用于指定前后端分离的时候调用后台登录接口的名称
				.loginProcessingUrl("/login").successHandler((request, response, authException) -> {
					//登录成功后将用户的菜单信息和角色信息加载到security
					//加载角色信息
					authService.setLoginUserRole();
					//加载菜单信息
					authService.setLoginUserMenu();
					//加载组织信息
					authService.setLoginUserOrg();
					// 处理登录成功的处理，返回信息，不进行页面跳转
					writeMessage(response,ResponseEnum.SUCCESS.code(), ResponseEnum.SUCCESS.message());
				})
				.failureHandler((request, response, authException) -> {
					// 处理登录失败的处理，返回信息，不进行页面跳转
					writeMessage(response,ResponseEnum.LOGIN_FAIL.code(), ResponseEnum.LOGIN_FAIL.message());
				}).and()
				// loginProcessingUrl用于指定前后端分离的时候调用后台注销接口的名称
				.logout().logoutUrl("/logout").logoutSuccessHandler((request, response, authException) -> {
					// 处理注销成功后的返回消息，不进行页面跳转
					writeMessage(response,ResponseEnum.SUCCESS.code(), ResponseEnum.SUCCESS.message());
				}).and().exceptionHandling().accessDeniedHandler((request, response, authException) -> {
					// 配置没有权限的访问路径的返回消息，不进行页面跳转
					writeMessage(response,ResponseEnum.UNAUTHORIZED.code(), ResponseEnum.UNAUTHORIZED.message());
				}).and()
				// 因为前后端分离的缘故，所以取消跨站请求伪造防护
				.cors().and().csrf().disable();

	}

	/**
	 * 将信息写入response
	 * @param response	要写入的返回对象
	 * @param code		信息的编码
	 * @param message	描述信息
	 */
	private void writeMessage(HttpServletResponse response,String code,String message) throws IOException {
		response.setCharacterEncoding("UTF-8");
		// 处理未登录时返回信息，而不是页面跳转，返回未登录信息
		response.getWriter().write(JSON.toJSONString(new ResponseMessage(code, message)));
	}
}