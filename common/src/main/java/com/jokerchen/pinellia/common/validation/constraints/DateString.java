package com.jokerchen.pinellia.common.validation.constraints;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.jokerchen.pinellia.common.validation.DateStringValidator;

/**   
 * @description: 字符串日期的输入格式要求	要求为 yyyy-MM-dd
 * @author Joker Chen 
 * @date 2019-04-24 14:11:29  
 */
@Constraint(validatedBy = DateStringValidator.class)
@Target({FIELD, PARAMETER })
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface DateString {

	String message() default "时间格式错误";
	
	/**
	 * 字符串时间表达式
	 */
	String regex() default "^\\d{4}-(0[1-9]|1[12])-(0[1-9]|[12]\\d|3[01])$";
	
	// groups 和 payload 这两个parameter 必须包含
	// 否则会报 contains Constraint annotation, but does not contain a groups parameter. 异常
    Class<?>[] groups() default {};
 
    Class<? extends Payload>[] payload() default {};
    
}
