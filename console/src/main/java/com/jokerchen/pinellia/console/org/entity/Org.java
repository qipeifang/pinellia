package com.jokerchen.pinellia.console.org.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * @date 2019-03-19 10:48:02  
 */
@Data
@Entity
@Table(name="tc_auth_org")
public class Org {

    /** 组织编码，每层编码在上层的编码下增加4位数字 */
    @Id
    @Column(name="code")
    private String code;

    /** 组织名称 */
    @NotBlank
    @Size(max=16)
    @Basic
    @Column(name="name")
    private String name;

    /** 上层组织编码 */
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

    /** 状态,0：正常，1：删除，2：停用 */
    @Basic
    @Column(name="state")
    private int state;

}