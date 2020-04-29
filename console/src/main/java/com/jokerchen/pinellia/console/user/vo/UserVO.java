package com.jokerchen.pinellia.console.user.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

/**   
 * @description: 
 * @author Joker Chen 
 * @date 2019-04-09 16:55:04  
 */
@Data
public class UserVO {

	/** 主键 */
	private int id;
	
	/** 用户名 */
	@NotBlank
    @Size(max=64)
	private String username;
	
	/** 密码 */
	private String password;
	
	/** 状态,0：正常，1：删除，2：停用 */
	private int state;
	
	/** 身份证号码 */
	private String idNo;
	
	/** 用户类型 */
	private String type;
	
	/** 组织编码 */
	@NotBlank
	private String orgCode;
	
	/** 角色id */
	private String roleId;
}
