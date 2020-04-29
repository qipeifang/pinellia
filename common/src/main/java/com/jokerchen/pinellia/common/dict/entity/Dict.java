package com.jokerchen.pinellia.common.dict.entity;

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
 * @date 2019-03-14 09:04:50  
 */
@Data
@Entity
@Table(name="tc_sys_dict")
public class Dict {

    /** 主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    /** 编码，相同的category下，code不能重复 */
    @NotBlank
    @Size(max=32)
    @Basic
    @Column(name="code")
    private String code;

    /** 名称 */
    @NotBlank
    @Size(max=32)
    @Basic
    @Column(name="name")
    private String name;

    /** 说明 */
    @Size(max=64)
    @Basic
    @Column(name="des")
    private String des;
    
    /** 类型编码，对应tc_sys_dict_category表 */
    @NotBlank
    @Size(max=32)
    @Basic
    @Column(name="category")
    private String category;

    /** 状态,0：正常，1：删除，2：停用 */
    @Basic
    @Column(name="state")
    private int state;
    
    /** 顺序 */
    @NotNull
    @Min(0)
    @Max(999)
    @Basic
    @Column(name="sequence")
    private Integer sequence;

}