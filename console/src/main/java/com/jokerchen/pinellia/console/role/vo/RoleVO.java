package com.jokerchen.pinellia.console.role.vo;

import lombok.Data;

/**   
 * @description: 
 * @author Joker Chen 
 * @date 2019-04-09 15:22:14  
 */
@Data
public class RoleVO {

	/** 角色Id */
    private int roleId;

    /** 角色名称 */
    private String roleName;
    
    /** 组织结构编码 */
    private String orgCode;
    
    /** 组织结构名称 */
    private String orgName;
    
    /** 权限类型，1：超级管理员，2：操作员，超管一开始系统数据初始化，后续添加的都为操作员 */
    private int authType;
    
    /** 配置给这个角色菜单编码 */
    private String menuCode;
    
    /** 配置给这个角色菜单名称 */
    private String menuName;
    
    /** 是否有操作的权限,默认没有 */
    private boolean editFlag;
}
