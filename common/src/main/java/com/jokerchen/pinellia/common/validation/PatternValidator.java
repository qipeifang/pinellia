package com.jokerchen.pinellia.common.validation;

import java.lang.annotation.Annotation;
import java.util.regex.Matcher;
import java.util.regex.PatternSyntaxException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import lombok.extern.slf4j.Slf4j;

/**   
 * @description: 
 * @author Joker Chen 
 * @date 2019-04-24 14:30:08  
 */
@Slf4j
public abstract class PatternValidator<A extends Annotation> implements ConstraintValidator<A, String>{

	/**
	 * 电话号码的格式
	 */
	private java.util.regex.Pattern pattern;
	
	/**
	 * 获取正则表达式
	 * @return
	 */
	abstract String regex(A a);
	
	/**
	 * 初始化
	 */
	@Override
	public void initialize(A annotation) {
		try {
			pattern = java.util.regex.Pattern.compile(regex(annotation));
		}catch (PatternSyntaxException e) {
			log.error("自定义"+annotation.getClass()+"校验器异常",e);
		}
	}
	
	/**
	 * 校验逻辑
	 */
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if ( value == null || value.isEmpty()) {
			return true;
		}
		//校验正则表达式是否正确
		Matcher m = pattern.matcher( value );
		return m.matches();
	}
	
}
