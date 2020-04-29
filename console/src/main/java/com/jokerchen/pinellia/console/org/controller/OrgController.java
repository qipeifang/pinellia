package com.jokerchen.pinellia.console.org.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;

import com.jokerchen.pinellia.common.model.TreeNode;
import com.jokerchen.pinellia.console.org.entity.Org;

/**   
 * @description: 组织结构管理
 * @author Joker Chen 
 * @date 2019-03-13 16:35:13  
 */
@RequestMapping("/org")
public interface OrgController {

    /**
     * 获取当前登录用户有权限的组织信息
     * @return	树形结构的组织信息
     */
    @RequestMapping("/findOrg")
    List<TreeNode> findOrg();

    /**
     * 删除组织
     * @param code 要删除的组织id
     */
    @RequestMapping("/deleteOrg")
    void deleteOrg(String code);

    /**
     * 停用组织
     * @param code 要停用的组织id
     */
    @RequestMapping("/disableOrg")
    void disableOrg(String code);

    /**
     * 启用组织
     * @param code 要停用的组织id
     */
    @RequestMapping("/enableOrg")
    void enableOrg(String code);

    /**
     * 保存组织
     * @param org
     */
    @RequestMapping("/saveOrg")
    void saveOrg(@Valid Org org);
}
