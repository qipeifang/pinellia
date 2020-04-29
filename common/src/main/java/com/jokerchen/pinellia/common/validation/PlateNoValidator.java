package com.jokerchen.pinellia.common.validation;

import com.jokerchen.pinellia.common.validation.constraints.PlateNo;

/**   
 * @description: 
 * @author Joker Chen 
 * @date 2019-04-24 15:44:22  
 */
public class PlateNoValidator extends PatternValidator<PlateNo>{
	
	@Override
	String regex(PlateNo r) {
		return r.regex();
	}
}
