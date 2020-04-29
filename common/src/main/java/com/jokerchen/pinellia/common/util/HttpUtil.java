package com.jokerchen.pinellia.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;

import com.jokerchen.pinellia.common.model.RequestHeaders;

/**   
 * @description: 作为客户端发起http请求，做一些对第三方系统的数据请求
 * @author Joker Chen 
 * @date 2019-03-25 08:54:45  
 */
public class HttpUtil {
	
	/**
	 * 获取某个网站的cookies信息，用于后面登录之后保存登录信息时使用
	 * @param urlString	要获取cookies的网站路径
	 * @return urlString路径访问后生成的cookies信息
	 * @throws IOException 
	 */
	public static String getCookies(String urlString) throws IOException {
		CookieManager manager=new CookieManager();
		manager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
		CookieHandler.setDefault(manager);
		URL url=new URL(urlString);
		HttpURLConnection conn= (HttpURLConnection) url.openConnection();
        conn.getHeaderFields();
        CookieStore store = manager.getCookieStore();
        String cookies = store.getCookies().toString();
        cookies = cookies.replace("[", "").replace("]", "");
        return cookies;
	}
	
	/**
	 * 获取某个网站的cookies信息，用于后面登录之后保存登录信息时使用
	 * @param conn
	 * @throws IOException 
	 * @throws URISyntaxException 
	 */
	public static String getCookies(HttpURLConnection conn) throws IOException, URISyntaxException {
		CookieManager manager=new CookieManager();
		manager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
		manager.put(conn.getURL().toURI(),conn.getHeaderFields());
		CookieHandler.setDefault(manager);
        conn.getHeaderFields();
        CookieStore store = manager.getCookieStore();
        String cookies = store.getCookies().toString();
        cookies = cookies.replace("[", "").replace("]", "");
        return cookies;
	}
	
	/**
	 * 创建连接信息，用来模拟浏览器发起访问
	 * @param url
	 * @param headers
	 * @return
	 * @throws IOException 
	 */
	public static HttpURLConnection getConnection(URL url,RequestHeaders headers) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("User-Agent", headers.getUserAgent());
        conn.setRequestProperty("Accept", headers.getAccept());
        conn.setRequestProperty("Accept-Language", headers.getAcceptLanguage());
        conn.setRequestProperty("Accept-Encoding", headers.getAcceptEncoding());
        conn.setRequestProperty("Referer", headers.getReferer());
        conn.setRequestProperty("Cookie", headers.getCookies());
        conn.setRequestProperty("Connection", headers.getConnection());
        conn.setRequestProperty("Upgrade-Insecure-Requests", headers.getUpgradeInsecureRequests());
        //以后就可以使用conn.getOutputStream().write() ，get请求用不到conn.getOutputStream()，因为参数直接追加在地址后面，因此默认是false
        conn.setDoOutput(true);
        //URL 连接可用于输入和/或输出。如果打算使用 URL 连接进行输出，则将 DoOutput 标志设置为 true；如果不打算使用，则设置为 false。默认值为 false。
        conn.setDoInput(true);
		return conn;
	}
	
	/**
	 * 发起http请求，使用get发起请求
	 * @param urlString 要获取请求的http路径地址
	 * @return	请求返回的结果
	 * @throws IOException 
	 */
	public static String doGet(String urlString,Map<String,Object> params) throws IOException {
		//将请求参数拼接到路径上
		urlString += createParamString(params);
		URL url = new URL(urlString);
		HttpURLConnection conn = getConnection(url, new RequestHeaders());
		conn.setRequestMethod("GET");
		return getResult(conn);
	}
	
	/**
	 * 发起http请求，使用get发起请求
	 * @param urlString 要获取请求的http路径地址
	 * @param cookies 请求带上的cookies信息，在发起有登录控制时的请求时使用
	 * @param params 请求要带上的参数，会被拼接到url上
	 * @return	请求返回的结果
	 * @throws IOException 
	 */
	public static String doGet(String urlString,String cookies, Map<String,Object> params) throws IOException {
		//将请求参数拼接到路径上
		urlString += createParamString(params);
		URL url = new URL(urlString);
		HttpURLConnection conn = getConnection(url, new RequestHeaders(cookies));
		conn.setRequestMethod("GET");
		return getResult(conn);
	}
	
	/**
	 * 将的请求参数拼接成string
	 * @param params
	 * @return
	 */
	private static String createParamString(Map<String,Object> params) {
		if(params != null && params.size() > 0) {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("?");
			params.forEach((key, value) -> {
			    stringBuilder.append(key+"="+value+"&");
			});
			return stringBuilder.subSequence(0, stringBuilder.length()-1).toString();
		}
		return "";
	}
	
	/**
	 * 发起http请求，使用post发起请求
	 * @param urlString 要获取请求的http路径地址
	 * @param params 请求要带上的参数
	 * @return	请求返回的结果
	 * @throws IOException 
	 */
	public static String doPost(String urlString, Map<String,Object> params) throws IOException {
		URL url = new URL(urlString);
		HttpURLConnection conn = getConnection(url, new RequestHeaders());
		conn.setRequestMethod("POST");
		//post请求，将使用还粗设置为false
		conn.setUseCaches(false);
		//设置参数
		String param = createParamString(params);
		if(param != null && param.startsWith("?")) param = param.substring(1);
		setPostParams(conn, param);
		return getResult(conn);
	}
	
	/**
	 * 发起http请求，使用post发起请求
	 * @param urlString 要获取请求的http路径地址
	 * @param params 请求要带上的参数
	 * @return	请求返回的结果
	 * @throws IOException 
	 */
	public static String doPost(String urlString, String params) throws IOException {
		URL url = new URL(urlString);
		HttpURLConnection conn = getConnection(url, new RequestHeaders());
		conn.setRequestMethod("POST");
		//post请求，将使用还粗设置为false
		conn.setUseCaches(false);
		//设置参数
		setPostParams(conn, params);
		return getResult(conn);
	}
	
	/**
	 * 发起http请求，使用post发起请求
	 * @param urlString 要获取请求的http路径地址
	 * @param cookies 请求带上的cookies信息，在发起有登录控制时的请求时使用
	 * @param params 请求要带上的参数
	 * @return	请求返回的结果
	 * @throws IOException 
	 */
	public static String doPost(String urlString,String cookies, Map<String,Object> params) throws IOException {
		URL url = new URL(urlString);
		HttpURLConnection conn = getConnection(url, new RequestHeaders(cookies));
		conn.setRequestMethod("POST");
		//post请求，将使用还粗设置为false
		conn.setUseCaches(false);
		//设置参数
		String param = createParamString(params);
		if(param != null && param.startsWith("?")) param = param.substring(1);
		setPostParams(conn, param);
		return getResult(conn);
	}
	
	/**
	 * 发起http请求，使用post发起请求
	 * @param urlString 要获取请求的http路径地址
	 * @param cookies 请求带上的cookies信息，在发起有登录控制时的请求时使用
	 * @param params 请求要带上的参数
	 * @return	请求返回的结果
	 * @throws IOException 
	 */
	public static String doPost(String urlString,String cookies, String params) throws IOException {
		URL url = new URL(urlString);
		HttpURLConnection conn = getConnection(url, new RequestHeaders(cookies));
		conn.setRequestMethod("POST");
		//post请求，将使用还粗设置为false
		conn.setUseCaches(false);
		//设置参数
		setPostParams(conn, params);
		return getResult(conn);
	}
	
	/**
	 * post 设置参数
	 * @param conn
	 * @param params
	 * @throws IOException 
	 */
	private static void setPostParams(HttpURLConnection conn,String params) throws IOException {
		if(params != null && params.length()>0) {
	        OutputStream out = null;
	        try {
	        	out = conn.getOutputStream();
	        	out.write(params.getBytes(Charset.defaultCharset()));
	        	//清空缓冲区,发送数据
	        	out.flush();
	        }catch (Exception e) {
	        	e.printStackTrace();
			}finally {
				try{  
	                if(out != null){  
	                	//关闭发送流
	                    out.close();  
	                }  
	            }catch(IOException ex){  
	                ex.printStackTrace();  
	            }  
			}
		}
	}
	
	/**
	 * 获取conn连接的请求结果
	 * @param conn
	 * @return
	 */
	private static String getResult(HttpURLConnection conn) {
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
		try {
	        in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        String line;
	        while((line = in.readLine())!=null){
	        	result.append("\n"+line);
	        }
        }catch(Exception e){  
            e.printStackTrace();  
        }finally{  
            try{  
                if(in!=null){  
                    in.close();  
                }  
            }catch(IOException ex){  
                ex.printStackTrace();  
            }  
        } 
        return result.toString();
	}

}
