package com.jokerchen.pinellia.console.org.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jokerchen.pinellia.common.dao.impl.BaseDaoImpl;
import com.jokerchen.pinellia.console.org.dao.OrgDao;
import com.jokerchen.pinellia.console.org.entity.Org;
import com.jokerchen.pinellia.console.org.vo.OrgVO;

/**
 * @author joker
 * 2019-03-18 21:25
 * @Description:
 */
@Repository
public class OrgDaoImpl extends BaseDaoImpl<Org> implements OrgDao {

    @SuppressWarnings("unchecked")
	@Override
    public List<OrgVO> findOrgByCode(String username) {
    	//先获取配置给当前用户的组织
    	String sql1 = "select o.code from tc_auth_org o "
				+ " LEFT JOIN tc_auth_org_user ou ON o.code = ou.orgCode "
				+ " where ou.username = ?0 ";
		List<String> codes = (List<String>) createSqlQuery(sql1, username).list();
    	if(codes == null || codes.size() == 0) return null;
    	
    	StringBuilder sql = new StringBuilder();
		sql.append(" select o.* from tc_auth_org o where ");
		Object[] array = new Object[codes.size()];
		for(int i=0; i<codes.size();i++) {
			sql.append(" o.code like ?"+i +" or");
			array[i] = codes.get(i)+"%";
		}
		String string = sql.substring(0,sql.length()-2) + " order by sequence asc ";
		return findBySql(string, OrgVO.class, array);
    }

    @Override
    public Org findMaxCodeOrg(String parentCode) {
        String hql = "from Org o where parentCode = ?0 order by o.code desc ";
        return this.createQuery(hql,parentCode).setMaxResults(1).uniqueResult();
    }

    @Override
    public Org getOrg(String code) {
        return this.getOneByField("code",code);
    }

    @Override
    public List<Org> findSubOrg(String code) {
        String hql = "from Org o where o.code like ?0 ";
        return this.findByHql(hql,code + "%");
    }


}
