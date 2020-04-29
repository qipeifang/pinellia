package com.jokerchen.pinellia.common.dict.dao.impl;

import com.jokerchen.pinellia.common.constant.Constant;
import com.jokerchen.pinellia.common.dao.impl.BaseDaoImpl;
import com.jokerchen.pinellia.common.dict.dao.DictDao;
import com.jokerchen.pinellia.common.dict.entity.Dict;
import org.springframework.stereotype.Repository;

import java.util.List;

/**   
 * @description:数据字典持久层实现 
 * @author Joker Chen 
 * @date 2019-03-21 16:48:26  
 */
@Repository
public class DictDaoImpl extends BaseDaoImpl<Dict> implements DictDao{


    @Override
    public boolean existCode(String category, String code, int id) {
        String hql = "from Dict where category = ?0 and code = ?1 and id != ?2 ";
        List<Dict> list = this.findByHql(hql, category,code,id);
        return list !=null && list.size() > 0;
    }

    @Override
    public List<Dict> findDict(String category) {
        String hql = "from Dict where category = ?0 and state = ?1 ";
        return this.findByHql(hql,category, Constant.DATA_STATE_NORMAL);
    }

}
