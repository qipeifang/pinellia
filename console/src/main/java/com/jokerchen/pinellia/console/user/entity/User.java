package com.jokerchen.pinellia.console.user.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

/**   
 * @description: 
 * @author Joker Chen 
 * @date 2019-03-12 17:09:12  
 */
@Data
@Entity
@Table(name="tc_auth_user")
public class User {
	
	/** 主键 */
	@Id   
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	@Column(name="id")    
	private int id;
	
	/** 用户名 */
	@NotBlank
    @Size(max=64)
	@Basic
	@Column(name="username")
	private String username;
	
	/** 密码 */
	@Basic
	@Column(name="password")
	private String password;
	
	/** 状态,0：正常，1：删除，2：停用 */
	@Basic
	@Column(name="state")
	private int state;
	
	/** 身份证号码 */
	@Basic
	@Column(name="idNo")
	private String idNo;
	
	/** 用户类型 */
	@Basic
	@Column(name="type")
	private String type;
	
	/** 微信Id */
	@Basic
	@Column(name="wxId")
	private String wxId;
}
