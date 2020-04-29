package com.jokerchen.pinellia.console.menu.dao;

import java.util.List;

import com.jokerchen.pinellia.common.dao.BaseDao;
import com.jokerchen.pinellia.console.menu.entity.Menu;
import com.jokerchen.pinellia.console.menu.vo.MenuVO;

/**
 * @author joker
 * 2019-03-18 21:25
 * @Description:
 */
public interface MenuDao extends BaseDao<Menu> {
	
	/**
	 * 查询全部的菜单信息，用于构建菜单树
	 * @return
	 */
	List<MenuVO> findAllMenu();

    /**
     * 判断菜单编码是否已经存在
     * @param code
     * @param id
     */
    boolean existCode(String code, int id);
}
