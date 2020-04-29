package com.jokerchen.pinellia.common.config;

import javax.servlet.Filter;
import javax.servlet.Servlet;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

/**   
 * @description: 
 * @author Joker Chen 
 * @date 2019-03-13 16:00:24  
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	/**
	 * 添加拦截器
	 */
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(new AuthorizationInterceptor())
//			.addPathPatterns("/**")
//			.excludePathPatterns(
//					"/static/**","/js/**","/css/**","/img/**",//静态文件不需要权限验证
//					"noAuthority","logout","login"); 
//	}

	/**
	 * 配置druid的控制访问界面
	 * @return
	 */
	@Bean
    public ServletRegistrationBean<?> statViewServlet(){
        ServletRegistrationBean<?> servletRegistrationBean = 
        		new ServletRegistrationBean<Servlet>(new StatViewServlet(),"/druid/*");
        //设置ip白名单
        //servletRegistrationBean.addInitParameter("allow","127.0.0.1");
        //设置ip黑名单，优先级高于白名单
        //servletRegistrationBean.addInitParameter("deny","192.168.0.19");
        //设置控制台管理用户
        servletRegistrationBean.addInitParameter("loginUsername","admin");
        servletRegistrationBean.addInitParameter("loginPassword","admin");
        //是否可以重置数据
        servletRegistrationBean.addInitParameter("resetEnable","false");
        return servletRegistrationBean;
    }

	/**
	 * 配置druid的控制访问界面
	 * @return
	 */
    @Bean
    public FilterRegistrationBean<?> statFilter(){
        //创建过滤器
        FilterRegistrationBean<?> filterRegistrationBean = new FilterRegistrationBean<Filter>(new WebStatFilter());
        //设置过滤器过滤路径
        filterRegistrationBean.addUrlPatterns("/*");
        //忽略过滤的形式
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
