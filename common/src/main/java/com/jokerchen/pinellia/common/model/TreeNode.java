package com.jokerchen.pinellia.common.model;

import java.util.List;

/**   
 * @description: 用于在后台构建树形结构的数据，可用于菜单，组织结构等
 * @author Joker Chen 
 * @date 2019-03-15 09:25:42  
 */
public class TreeNode {
	
	protected String code;

	protected String name;

	protected String parentCode;

	protected Integer level;

	protected List<TreeNode> subNode;
	
	public TreeNode() { }

	public TreeNode(String code, String name, String parentCode, Integer level) {
		this.code = code;
		this.name = name;
		this.parentCode = parentCode;
		this.level = level;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public List<TreeNode> getSubNode() {
		return subNode;
	}

	public void setSubNode(List<TreeNode> subNode) {
		this.subNode = subNode;
	}
	
}
