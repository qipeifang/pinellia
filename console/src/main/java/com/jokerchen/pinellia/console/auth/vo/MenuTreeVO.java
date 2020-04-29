package com.jokerchen.pinellia.console.auth.vo;


import com.jokerchen.pinellia.common.model.TreeNode;

/**
 * @description: 菜单信息，主要用于构建前端菜单功能使用
 * @author Joker Chen
 * @date 2019-03-15 09:11:45
 */
public class MenuTreeVO extends TreeNode {

    /**
     * 菜单对应的前端html路径
     */
    private String url;

    /**
     * 是否已经选中此节点
     */
    private Integer checked;

    /** 是否在菜单上展示，0：不展示为菜单（按钮或者纯后台api），1：展示为菜单 */
    private Integer type;

    /** 后台接口对应的路径，即springmvc的requestMapping */
    private String mapping;

    public MenuTreeVO() {}

    public MenuTreeVO(String code, String name, String parentCode, Integer level) {
        super(code, name, parentCode, level);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getChecked() {
        return checked;
    }

    public void setChecked(Integer checked) {
        this.checked = checked;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMapping() {
        return mapping;
    }

    public void setMapping(String mapping) {
        this.mapping = mapping;
    }
}
