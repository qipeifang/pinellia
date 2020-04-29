package com.jokerchen.pinellia.common.validation;

import com.jokerchen.pinellia.common.validation.constraints.OnlyEnNum;

/**   
 * @description: 
 * @author Joker Chen 
 * @date 2019-04-24 15:44:22  
 */
public class OnlyEnNumValidator extends PatternValidator<OnlyEnNum>{
	
	@Override
	String regex(OnlyEnNum r) {
		return r.regex();
	}
}
