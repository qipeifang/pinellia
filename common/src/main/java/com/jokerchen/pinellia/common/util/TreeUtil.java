package com.jokerchen.pinellia.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.jokerchen.pinellia.common.model.TreeNode;

/**   
 * @description: 
 * @author Joker Chen 
 * @date 2019-03-15 09:36:25  
 */
public class TreeUtil {

	/**
	 * 将一组无序的树组装成树形结构的数据
	 * 
	 * @param treeList
	 * @return
	 */
	public static List<TreeNode> buildTree(List<? extends TreeNode> treeList) {
		if (treeList == null || treeList.size() == 0) {
			return null;
		}
		List<TreeNode> trees = getRootNode(treeList);
		trees.forEach(node -> node.setLevel(0));
		List<TreeNode> parentNodes = trees;
		List<TreeNode> subNodes;
		int level = 1;
		do {
			treeList.removeAll(parentNodes);
			subNodes = getRootNode(treeList);
			if (subNodes != null && subNodes.size() > 0) {
				for (TreeNode subNode : subNodes) {
					subNode.setLevel(level);
					for (TreeNode parentNode : parentNodes) {
						if (codeEquals(parentNode.getCode(), subNode.getParentCode())) {
							List<TreeNode> list = parentNode.getSubNode();
							if (list == null) {
								list = new ArrayList<>();
								parentNode.setSubNode(list);
							}
							list.add(subNode);
						}
					}
				}
			}
			parentNodes = subNodes;
			level++;
		} while (subNodes != null);
		return trees;
	}

	/**
	 * 获取treeList中的顶级节点
	 * 
	 * @param treeList
	 * @return
	 */
	public static List<TreeNode> getRootNode(List<? extends TreeNode> treeList) {
		if (treeList == null || treeList.size() == 0) {
			return null;
		}
		List<String> codes = treeList.stream().map(tree -> tree.getCode()).collect(Collectors.toList());
		List<TreeNode> trees = treeList.stream().filter(
				node -> !codes.contains(node.getParentCode()) || codeEquals(node.getCode(), node.getParentCode()))
				.collect(Collectors.toList());
		return trees;
	}

	/**
	 * 判断两个编码是否相同
	 * 
	 * @param code
	 * @param parentCode
	 * @return
	 */
	public static boolean codeEquals(String code, String parentCode) {
		if (code == null) {
			if (parentCode == null) {
				return true;
			}
		} else {
			if (parentCode != null && code.equals(parentCode)) {
				return true;
			}
		}
		return false;
	}
}
