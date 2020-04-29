package com.jokerchen.pinellia.common.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**   
 * @description: 缓存工具类，用于提供缓存相关的工具方法
 * 现在系统暂未引入缓存，所以所有的都是null，后面引入相关的缓存工具后，可直接在这个工具类中修改。
 * @author Joker Chen 
 * @date 2019-03-21 16:53:10  
 */
public class CacheUtil {

	//不允许创建缓存工具
	private CacheUtil() {}
	
	//现在暂时没有缓存相关的技术引入，先用map来模拟
	private static Map<String,Object> cacheMap = new HashMap<>();
	
	/**
     * 保存到缓存中
     * @param key   键
     * @param value 值
     */
    public static void set(String key, Object value) {
    	cacheMap.put(key, value);
    }

    /**
     * 保存到缓存中
     * @param key   键
     * @param value 值
     */
    public static void set(String key, String value) {
    	cacheMap.put(key, value);
    }

    /**
     * 保存到缓存中
     *
     * @param key   键
     * @param value 值
     * @param time  存活时间，单位：秒
     */
    public static void set(String key, Object value, int time) {
    }

    /**
     * 保存到缓存中
     *
     * @param key   键
     * @param value 值
     * @param time  存活时间，单位：秒
     */
    public static void set(String key, String value, int time) {
    }

    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    public static boolean keyExists(String key) {
    	return false;
    }

    /**
     * 获取key对应的值
     * @param key
     * @return
     */
    public static String get(String key) {
    	Object value = cacheMap.get(key);
    	return value != null ? value.toString() : null;
    }

    /**
     * 获得键值为key的对象
     *
     * @param key    键
     * @param clazz 要转换的类型
     * @return 对应的值
     */
    @SuppressWarnings("unchecked")
	public static <T> T get(String key, Class<T> clazz) {
    	return (T) cacheMap.get(key);
    }
    

    /**
     * 获得键值为key的对象
     *
     * @param key    键
     * @param clazz 要转换的类型
     * @return 对应的值
     */
    @SuppressWarnings("unchecked")
	public static <T> List<T> getList(String key, Class<T> clazz) {
    	return (List<T>) cacheMap.get(key);
    }

    /**
     * 从缓存中删除键值为key的对象
     *
     * @param key 键
     */
    public static void delete(String key) {
    	cacheMap.remove(key);
    }

    /**
     * 保存或在原有值的基础上加value
     * @param key
     * @param value
     */
    public static void setOrIncr(String key, int value) {
        if (keyExists(key)) {
        } else {
            set(key, value);
        }
    }
}
