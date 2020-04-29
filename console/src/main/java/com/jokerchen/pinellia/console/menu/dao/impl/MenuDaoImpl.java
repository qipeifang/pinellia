package com.jokerchen.pinellia.console.menu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jokerchen.pinellia.common.dao.impl.BaseDaoImpl;
import com.jokerchen.pinellia.console.menu.dao.MenuDao;
import com.jokerchen.pinellia.console.menu.entity.Menu;
import com.jokerchen.pinellia.console.menu.vo.MenuVO;

/**
 * @author joker
 * 2019-03-18 21:25
 * @Description:
 */
@Repository
public class MenuDaoImpl extends BaseDaoImpl<Menu> implements MenuDao {

	@Override
	public List<MenuVO> findAllMenu() {
		List<MenuVO> res = findBySql("select * from tc_auth_menu order by sequence ", MenuVO.class);
		return res;
	}
	
    @Override
    public boolean existCode(String code, int id) {
        String hql = "from Menu where code = ?0 and id != ?1 ";
        List<Menu> list = this.findByHql(hql, code,id);
        return list !=null && list.size() > 0;
    }

}
