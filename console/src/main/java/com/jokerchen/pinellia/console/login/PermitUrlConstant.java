package com.jokerchen.pinellia.console.login;

/**   
 * @description: 允许直接访问的路径
 * @author Joker Chen 
 * @date 2019-06-18 14:24:25  
 */
public class PermitUrlConstant {
	
	public static String[] permitUrl = {
			"/login",
//			"/favicon.ico",
//			"/favicon.png",
//			"/manifest.json",
//			"/static/js/**",
//			"/static/css/**",
//			"/static/media/**",
//			"/index.html",
//			"/index.html?*",
			"/common/image"
	};
	
	public static String[] staticUrl = {
			"/favicon.ico",
			"/favicon.png",
			"/manifest.json",
			"/service-worker.js",
			"/precache-manifest.*.js",
			"/static/js/**",
			"/static/css/**",
			"/static/media/**",
			"/index.html",
			"/chart",
			"/"
	};

}
