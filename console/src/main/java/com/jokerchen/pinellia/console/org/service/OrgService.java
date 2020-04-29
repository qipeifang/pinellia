package com.jokerchen.pinellia.console.org.service;

import com.jokerchen.pinellia.common.model.TreeNode;
import com.jokerchen.pinellia.console.org.entity.Org;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**   
 * @description: 组织结构管理
 * @author Joker Chen 
 * @date 2019-03-13 16:35:13  
 */
public interface OrgService {

    /**
     * 获取当前登录用户有权限的组织信息
     * @return	树形结构的组织信息
     */
    List<TreeNode> findOrg();

    /**
     * 删除组织
     * @param code 要删除的组织id
     */
    @Transactional
    void deleteOrg(String code);

    /**
     * 停用组织
     * @param code 要停用的组织id
     */
    @Transactional
    void disableOrg(String code);

    /**
     * 启用组织
     * @param code 要停用的组织id
     */
    @Transactional
    void enableOrg(String code);

    /**
     * 保存组织
     * @param org
     */
    @Transactional
    void saveOrg(Org org);
    
    /**
     * 通过组织编码获取组织信息
     * @param code
     * @return
     */
    Org findOrgByCode(String code);
}
