package com.jokerchen.pinellia.common.sysParam.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

/**   
 * @description: 
 * @author Joker Chen 
 * @date 2019-03-28 16:17:27  
 */
@Data
@Entity
@Table(name="tc_sys_param")
public class SysParam {

    /** 系统参数代码 */
	@NotBlank
	@Size(max=32)
    @Id
    @Column(name="code")
    private String code;

    /** 参数名称 */
	@NotBlank
	@Size(max=64)
    @Basic
    @Column(name="name")
    private String name;

    /** 参数值 */
	@NotBlank
	@Size(max=2048)
    @Basic
    @Column(name="value")
    private String value;

}