package com.jokerchen.pinellia.console.role.entity;

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
 * @date 2019-03-19 10:46:50  
 */
@Data
@Entity
@Table(name="tc_auth_role")
public class Role {

    /** 角色Id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="roleId")
    private int roleId;

    /** 角色名称 */
    @NotBlank
    @Size(max=32)
    @Basic
    @Column(name="roleName")
    private String roleName;
    
    /** 组织结构编码 */
    @Basic
    @Column(name="orgCode")
    private String orgCode;
    
    /** 权限类型，1：超级管理员，2：操作员，超管一开始系统数据初始化，后续添加的都为操作员 */
    @Basic
    @Column(name="authType")
    private int authType;

}