package com.jokerchen.pinellia.console.user.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.jokerchen.pinellia.common.dao.impl.BaseDaoImpl;
import com.jokerchen.pinellia.common.model.Page;
import com.jokerchen.pinellia.common.util.StringUtil;
import com.jokerchen.pinellia.console.user.dao.UserDao;
import com.jokerchen.pinellia.console.user.entity.User;
import com.jokerchen.pinellia.console.user.vo.UserVO;

/**
 * @author joker
 * 2019-03-18 20:04
 * @Description:
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public Page findUserPage(String username, Set<String> orgCode) {
		Map<String,Object> param = new HashMap<>();
        String hql = "select u.username, u.id, "
        		+ " group_concat(distinct ou.orgCode) as orgCode, "
        		+ " group_concat(distinct ur.roleId) as roleId "
        		+ " from tc_auth_user u  "
        		+ " LEFT JOIN tc_auth_org_user ou ON u.username = ou.username "
        		+ " LEFT JOIN tc_auth_user_role ur on u.username = ur.username "
        		+ " WHERE ou.orgCode in (:orgCode) " ;
        param.put("orgCode", orgCode);
        if(StringUtil.isNotEmpty(username)) {
        	hql += " and u.username like :username ";
        	param.put("username", "%"+username+"%");
        }
        hql += " group by u.username ";
        return this.pageBySql(hql,UserVO.class,param);
	}
	
    @Override
    public boolean existUsername(String username, int id) {
        String hql = "from User where username = ?0 and id != ?1 ";
        List<User> list = this.findByHql(hql, username,id);
        return list !=null && list.size() > 0;
    }
}
