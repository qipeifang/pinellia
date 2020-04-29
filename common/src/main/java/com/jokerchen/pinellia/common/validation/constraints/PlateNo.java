package com.jokerchen.pinellia.common.validation.constraints;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.jokerchen.pinellia.common.validation.PlateNoValidator;

/**   
 * @description: 
 * @author Joker Chen 
 * @date 2019-04-24 15:43:28  
 */
@Constraint(validatedBy = PlateNoValidator.class)
@Target({FIELD, PARAMETER })
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface PlateNo {

	String message() default "车牌号有误";

	/**
	 * 车牌号的正则表达式
	 */
	String regex() default "^(([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领][A-Z](([0-9]{5}[DF])|([DF]([A-HJ-NP-Z0-9])[0-9]{4})))"
			+ "|([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领]"
			+ "[A-Z][A-HJ-NP-Z0-9]{4}[A-HJ-NP-Z0-9挂学警港澳使领]))$";
	
	// groups 和 payload 这两个parameter 必须包含
	// 否则会报 contains Constraint annotation, but does not contain a groups parameter. 异常
    Class<?>[] groups() default {};
 
    Class<? extends Payload>[] payload() default {};
}