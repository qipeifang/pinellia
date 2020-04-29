package com.jokerchen.pinellia.console.org.dao;

import java.util.List;

import com.jokerchen.pinellia.common.dao.BaseDao;
import com.jokerchen.pinellia.console.org.entity.Org;
import com.jokerchen.pinellia.console.org.vo.OrgVO;

/**
 * @author joker
 * 2019-03-18 21:25
 * @Description:
 */
public interface OrgDao extends BaseDao<Org> {

    /**
     * 通过用户名获取该用户所配有的组织信息
     * @param username 当前登录的用户名
     * @return
     */
	List<OrgVO> findOrgByCode(String username);

    /**
     * 获取当前父节点下的最大编码值的组织机构
     * @param parentCode    父编码
     * @return
     */
    Org findMaxCodeOrg(String parentCode);

    /**
     * 获取组织结构信息
     * @param code
     * @return
     */
    Org getOrg(String code);

    /**
     * 获取当前节点和下级节点
     * @param code
     * @return
     */
    List<Org> findSubOrg(String code);
}
