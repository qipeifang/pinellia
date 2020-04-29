package com.jokerchen.pinellia.common.util;

/**   
 * @description: 字符串工具类
 * @author Joker Chen 
 * @date 2019-03-12 16:29:34  
 */
public class StringUtil {

    /**
     * 在左边添加字符，达到一定长度的字符串
     * @param source	原始的字符串
     * @param append	要添加的字符
     * @param finalLength	最终要得到的字符长度
     * @return
     */
    public static String appendLeft(String source, char append, int finalLength){
        //原始的字符不为空，最终长度要大于原始的字符串长度
        if(source != null){
            for(int i=source.length(); i<finalLength; i++){
                source = append + source;
            }
        }
        return source;
    }

    /**
     * 在右边添加字符，达到一定长度的字符串
     * @param source	原始的字符串
     * @param append	要添加的字符
     * @param finalLength	最终要得到的字符长度
     * @return
     */
    public static String appendRight(String source, char append, int finalLength){
        //原始的字符不为空，最终长度要大于原始的字符串长度
        if(source != null){
            for(int i=source.length(); i<finalLength; i++){
                source = source + append;
            }
        }
        return source;
    }
    
    /**
     * 是否为空
     * @param string
     * @return
     */
    public static boolean isEmpty(String string) {
    	return string == null || string.trim().length() == 0;
    }
    
    /**
     * 是否为空
     * @param string
     * @return
     */
    public static boolean isNotEmpty(String string) {
    	return string != null && string.trim().length() > 0;
    }
    
    /**
     * 首字母转大写
     * @param value
     * @return
     */
    public static String firstToUpperCase(String value) {
    	if(isNotEmpty(value)) {
    		return value.substring(0,1).toUpperCase().concat(value.substring(1));
    	}
    	return value;
    }
}
