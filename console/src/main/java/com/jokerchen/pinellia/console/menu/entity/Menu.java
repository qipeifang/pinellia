package com.jokerchen.pinellia.console.menu.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

/**   
 * @description: 
 * @author Joker Chen 
 * @date 2019-03-14 09:03:25  
 */
@Data
@Entity
@Table(name="tc_auth_menu")
public class Menu {

    /** 主键 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    /** 菜单编码 */
	@Basic
	@Size(max=16)
    @Column(name="code")
    private String code;

    /** 菜单名称 */
	@NotBlank
	@Size(max=16)
    @Basic
    @Column(name="name")
    private String name;

    /** 前端功能，如果是菜单的话对应菜单路径，如果是按钮的话，对应按钮编码 */
    @Basic
    @Size(max=128)
    @Column(name="func")
    private String func;

    /** 后台接口对应的路径，即spring mvc的requestMapping */
    @Basic
    @Size(max=128)
    @Column(name="mapping")
    private String mapping;

    /** 上级菜单，顶层菜单的上级菜单为空 */
    @Basic
    @Column(name="parentCode")
    private String parentCode;

    /** 层级 */
    @Basic
    @Column(name="level")
    private Integer level;

    /** 顺序 */
    @NotNull
    @Min(0)
    @Max(999)
    @Basic
    @Column(name="sequence")
    private Integer sequence;

    /** 是否在菜单上展示，0：不展示为菜单（按钮或者纯后台api），1：展示为菜单 */
    @Basic
    @Column(name="type")
    private int type;

    /** 状态,0：正常，1：删除，2：停用 */
    @Basic
    @Column(name="state")
    private int state;
    
    /** 权限类型，0：无需权限，1：超级管理员，2：管理员，3：业务员 */
    @NotNull
    @Min(0)
    @Max(3)
    @Basic
    @Column(name="authType")
    private int authType;

}