package com.jokerchen.pinellia.console.menu.vo;

import com.jokerchen.pinellia.common.model.TreeNode;

/**   
 * @description: 菜单信息，主要用于菜单维护时使用
 * @author Joker Chen 
 * @date 2019-03-15 09:11:45  
 */
public class MenuVO extends TreeNode{

	/** 主键 */
    private int id;

    /** 前端功能，如果是菜单的话对应菜单路径，如果是按钮的话，对应按钮编码 */
    private String func;
    
    /** 顺序 */
    private Integer sequence;

	/** 后台接口对应的路径，即spring mvc的requestMapping */
	private String mapping;

	/** 是否在菜单上展示，0：不展示为菜单（按钮或者纯后台api），1：展示为菜单 */
	private int type;

    /** 状态,0：正常，1：删除，2：停用 */
    private int state;
    
    /** 权限类型，0：超级管理员，1：管理员，2：业务员 */
    private int authType;
    
    public MenuVO() {}

    public MenuVO(String code, String name, String parentCode, Integer level) {
		super(code, name, parentCode, level);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFunc() {
		return func;
	}

	public void setFunc(String func) {
		this.func = func;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public String getMapping() {
		return mapping;
	}

	public void setMapping(String mapping) {
		this.mapping = mapping;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getAuthType() {
		return authType;
	}

	public void setAuthType(int authType) {
		this.authType = authType;
	}
	
}
