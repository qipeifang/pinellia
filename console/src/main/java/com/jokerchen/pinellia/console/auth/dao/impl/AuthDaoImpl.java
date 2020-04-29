package com.jokerchen.pinellia.console.auth.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.jokerchen.pinellia.common.constant.Constant;
import com.jokerchen.pinellia.common.dao.impl.BaseDaoImpl;
import com.jokerchen.pinellia.common.model.TreeNode;
import com.jokerchen.pinellia.console.auth.dao.AuthDao;
import com.jokerchen.pinellia.console.auth.vo.MenuTreeVO;
import com.jokerchen.pinellia.console.role.entity.Role;

/**   
 * @description: 权限查询相关实现
 * @author Joker Chen 
 * @date 2019-03-21 08:58:31  
 */
@Repository
public class AuthDaoImpl extends BaseDaoImpl<Object> implements AuthDao{

	@Override
	public List<MenuTreeVO> findMenuByUsername(String username) {
		String sql = "select DISTINCT m.code, m.name, m.func as url, m.parentCode, m.level, m.type, m.mapping " +
				" from tc_auth_menu m LEFT JOIN " +
				" tc_auth_role_menu rm on m.code = rm.menuCode LEFT JOIN " +
				" tc_auth_user_role ur on rm.roleId = ur.roleId " +
				" where (ur.username = ?0 or m.authType = ?1 ) and m.state = ?2  " +
				" order by m.sequence ";
		return this.findBySql(sql, MenuTreeVO.class, username,
				Constant.AUTH_TYPE_NO_AUTH, Constant.DATA_STATE_NORMAL);
	}

	@Override
	public List<Role> findRoleByUsername(String username) {
		String sql = "select r.* from tc_auth_role r LEFT JOIN " +
					" tc_auth_user_role ur on r.roleId = ur.roleId where ur.username = ?0 " ;
		return this.findBySql(sql, Role.class, username);
	}
	
	@Override
	public void deleteRoleMenu(int roleId) {
		String sql = "delete from tc_auth_role_menu where roleId = ?0 ";
		this.createSqlQuery(sql,roleId).executeUpdate();
	}
	
	@Override
	public void deleteRoleUser(int roleId) {
		String sql = "delete from tc_auth_user_role where roleId = ?0 ";
		this.createSqlQuery(sql,roleId).executeUpdate();
	}

	@Override
	public void saveRoleMenu(int roleId,String[] menuCodes) {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into tc_auth_role_menu(roleId, menuCode) values ");
		Object[] params = new Object[menuCodes.length * 2];
		for(int i=0;i<menuCodes.length;i++){
			sql.append("(?"+(2*i)+", ?"+(2*i+1)+"),");
			params[2*i] = roleId;
			params[2*i+1] = menuCodes[i];
		}
		String str = sql.substring(0,sql.length()-1);
		this.createSqlQuery(str,params).executeUpdate();
	}

	@Override
	public void deleteMenuRole(String menuCode) {
		String sql = "delete from tc_auth_role_menu where menuCode = ?0 ";
		this.createSqlQuery(sql,menuCode).executeUpdate();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<String> findOrgByUsername(String username) {
		String sql = "select o.code from tc_auth_org o "
				+ " LEFT JOIN tc_auth_org_user ou ON o.code = ou.orgCode "
				+ " where ou.username = ?0 ";
		return (List<String>) createSqlQuery(sql, username).list();
	}
	
	@Override
	public List<TreeNode> findOrgByCodes(List<String> codes){
		StringBuilder sql = new StringBuilder();
		sql.append(" select o.code, o.name, o.parentCode,o.level  from tc_auth_org o where o.state = ?0 and ");
		Object[] array = new Object[codes.size()+1];
		array[0] = Constant.DATA_STATE_NORMAL;
		for(int i=1; i<=codes.size();i++) {
			sql.append(" o.code like ?"+i +" or");
			array[i] = codes.get(i-1)+"%";
		}
		String string = sql.substring(0,sql.length()-2) + " order by sequence asc ";
		return findBySql(string, TreeNode.class, array);
	}
	
	@Override
	public List<Role> findAuthRole(Set<String> orgCode){
		Map<String,Object> param = new HashMap<>();
        String hql = "select * from tc_auth_role r where r.orgCode in (:orgCode) ";
        param.put("orgCode", orgCode);
        return this.findBySql(hql,Role.class,param);
		
	}

	@Override
	public void saveUserOrg(String username, String[] orgCodes) {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into tc_auth_org_user(username, orgCode) values ");
		String[] params = new String[orgCodes.length * 2];
		for(int i=0;i<orgCodes.length;i++){
			sql.append("(?"+(2*i)+", ?"+(2*i+1)+"),");
			params[2*i] = username;
			params[2*i+1] = orgCodes[i];
		}
		String str = sql.substring(0,sql.length()-1);
		this.createSqlQuery(str,params).executeUpdate();
	}

	@Override
	public void deleteUserOrg(String username) {
		String sql = "delete from tc_auth_org_user where username = ?0 ";
		this.createSqlQuery(sql,username).executeUpdate();
		
	}

	@Override
	public void saveUserRole(String username, Object[] roleIds) {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into tc_auth_user_role(username, roleId) values ");
		Object[] params = new Object[roleIds.length * 2];
		for(int i=0;i<roleIds.length;i++){
			sql.append("(?"+(2*i)+", ?"+(2*i+1)+"),");
			params[2*i] = username;
			params[2*i+1] = roleIds[i];
		}
		String str = sql.substring(0,sql.length()-1);
		this.createSqlQuery(str,params).executeUpdate();
	}

	@Override
	public void deleteUserRole(String username) {
		String sql = "delete from tc_auth_user_role where username = ?0 ";
		this.createSqlQuery(sql,username).executeUpdate();
	}
	
}

