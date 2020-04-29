package com.jokerchen.pinellia.common.validation.constraints;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.jokerchen.pinellia.common.validation.TelValidator;

/**   
 * @description: 电话号码的校验器
 * @author Joker Chen 
 * @date 2019-04-22 10:32:24  
 */
@Constraint(validatedBy = TelValidator.class)
@Target({FIELD, PARAMETER })
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface Tel {

	String message() default "电话号码错误";

	/**
	 * 电话号码的正则表达式
	 */
	String regex() default "(^1(3|4|5|7|8)\\d{9})|(^(0\\d{2,3}-?)?\\d{7,8})$";
	
	// groups 和 payload 这两个parameter 必须包含
	// 否则会报 contains Constraint annotation, but does not contain a groups parameter. 异常
    Class<?>[] groups() default {};
 
    Class<? extends Payload>[] payload() default {};
}
