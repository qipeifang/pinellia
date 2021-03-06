package com.jokerchen.pinellia.console.org.vo;

import com.jokerchen.pinellia.common.model.TreeNode;

/**   
 * @description: 
 * @author Joker Chen 
 * @date 2019-03-15 09:11:45  
 */
public class OrgVO extends TreeNode{

	/** 主键 */
    private int id;

    /** 顺序 */
    private Integer sequence;

    /** 状态,0：正常，1：删除，2：停用 */
    private int state;

    public OrgVO() {}

    public OrgVO(String code, String name, String parentCode, Integer level) {
		super(code, name, parentCode, level);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
}
