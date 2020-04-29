package com.jokerchen.pinellia.common.dict.dao.impl;

import com.jokerchen.pinellia.common.dao.impl.BaseDaoImpl;
import com.jokerchen.pinellia.common.dict.dao.DictCategoryDao;
import com.jokerchen.pinellia.common.dict.entity.DictCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**   
 * @description: 数据字典类型持久层实现
 * @author Joker Chen 
 * @date 2019-03-21 16:48:49  
 */
@Repository
public class DictCategoryDaoImpl extends BaseDaoImpl<DictCategory> implements DictCategoryDao{

	@Override
	public List<DictCategory> findDictCategory(String category) {
		String hql = "from DictCategory where category like ?0 or name like ?1 ";
		return this.findByHql(hql, "%"+category+"%","%"+category+"%");
	}

	@Override
	public boolean existCategory(String category, int id) {
		String hql = "from DictCategory where category = ?0 and id != ?1 ";
		List<DictCategory> list = this.findByHql(hql, category,id);
		return list !=null && list.size() > 0;
	}

}
