package com.jokerchen.pinellia.common.validation;

import com.jokerchen.pinellia.common.validation.constraints.DateString;

/**   
 * @description: 
 * @author Joker Chen 
 * @date 2019-04-24 14:40:28  
 */
public class DateStringValidator extends PatternValidator<DateString>{
	
	@Override
	String regex(DateString dateString) {
		return dateString.regex();
	}

}