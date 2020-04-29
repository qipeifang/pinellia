package com.jokerchen.pinellia.common.validation;

import com.jokerchen.pinellia.common.validation.constraints.Tel;

/**   
 * @description: 正则表达式的校验
 * @author Joker Chen 
 * @date 2019-04-22 10:44:35  
 */
public class TelValidator extends PatternValidator<Tel>{
	
	@Override
	String regex(Tel tel) {
		return tel.regex();
	}
	
}
