package com.jokerchen.pinellia.common.dict.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

/**   
 * @description: 
 * @author Joker Chen 
 * @date 2019-03-14 09:04:50  
 */
@Data
@Entity
@Table(name="tc_sys_dict_category")
public class DictCategory {

    /** 类型编码 */
	@NotBlank
	@Size(max=32)
    @Id
    @Column(name="category")
    private String category;

    /**  */
	@NotBlank
	@Size(max=32)
    @Basic
    @Column(name="name")
    private String name;

}