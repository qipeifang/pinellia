package com.jokerchen.pinellia.common.validation.constraints;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.jokerchen.pinellia.common.validation.OnlyEnNumValidator;

/**   
 * @description: 
 * @author Joker Chen 
 * @date 2019-04-24 15:43:28  
 */
@Constraint(validatedBy = OnlyEnNumValidator.class)
@Target({FIELD, PARAMETER })
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface OnlyEnNum {

	String message() default "只可输入英文和数字";

	/**
	 * 只有数字和英文的正则表达式
	 */
	String regex() default "^[0-9A-Za-z]+$";
	
	// groups 和 payload 这两个parameter 必须包含
	// 否则会报 contains Constraint annotation, but does not contain a groups parameter. 异常
    Class<?>[] groups() default {};
 
    Class<? extends Payload>[] payload() default {};
}