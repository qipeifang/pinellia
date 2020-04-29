package com.jokerchen.pinellia.common.dict.dao;

import com.jokerchen.pinellia.common.dao.BaseDao;
import com.jokerchen.pinellia.common.dict.entity.Dict;

import java.util.List;

/**   
 * @description: 数据字典持久层接口
 * @author Joker Chen 
 * @date 2019-03-21 16:48:26  
 */
public interface DictDao extends BaseDao<Dict>{


    /**
     * 判断字典编码是否已经存在
     * @param category
     * @param code
     * @param id
     */
    boolean existCode(String category,String code, int id);

    /**
     * 根据字典类型获取字典信息
     * @param category
     * @return
     */
    List<Dict> findDict(String category);
}
