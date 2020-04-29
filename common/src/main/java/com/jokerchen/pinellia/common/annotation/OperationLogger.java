package com.jokerchen.pinellia.common.annotation;

import java.lang.annotation.*;

/**   
 * @description: 操作日志注解标记
 * @author Joker Chen 
 * @date 2019-03-21 16:53:10  
 */
@Inherited
@Target({ElementType.METHOD})	//用于描述注解的使用范围
@Retention(RetentionPolicy.RUNTIME)	//用于描述注解的生命周期，RUNTIME:在运行时有效（即运行时保留）
@Documented	//用于描述其它类型的annotation应该被作为被标注的程序成员的公共API，因此可以被例如javadoc此类的工具文档化
public @interface OperationLogger {

	/**
	 * 操作类型
	 */
	String operationType();
	
	/**
	 * 操作描述
	 */
	String operationDesc();
	
}
