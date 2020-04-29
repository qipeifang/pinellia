package com.jokerchen.pinellia.common.dict.service;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.transaction.annotation.Transactional;

import com.jokerchen.pinellia.common.dict.entity.Dict;
import com.jokerchen.pinellia.common.dict.entity.DictCategory;

/**   
 * @description: 数据字典逻辑接口层 
 * @author Joker Chen 
 * @date 2019-03-21 16:48:08  
 */
public interface DictService {
	

	/**
	 * 根据字典类型编码或名称查找字典类型信息
	 * @param category
	 * @return
	 */
	List<DictCategory> findDictCategory(String category);
	
	/**
	 * 根据字典类型获取字典信息
	 * @param category
	 * @return
	 */
	List<Dict> findDictByCategory(String category);

	/**
	 * 根据字典类型获取字典信息
	 * @param category
	 * @return
	 */
	List<Dict> findDict(@NotNull String category);

    /**
     * 保存数据字典类型
     * @param dictCategory
     * @param oldCategory   原字典类型编码
     */
    @Transactional(rollbackFor=Exception.class)
    void saveCategory(DictCategory dictCategory,String oldCategory);
    
    /**
     * 删除数据字典类型
     * @param category	要删除的数据字典类型
     */
    @Transactional(rollbackFor=Exception.class)
    void deleteCategory(String category);
    
    /**
     * 保存数据字典
     * @param dict
     */
    @Transactional(rollbackFor=Exception.class)
    void saveDict(Dict dict);
    
    /**
     * 删除数据字典
     * @param id 要删除的字典id
     */
    @Transactional
    void deleteDict(int id);
    
    /**
     * 停用数据字典
     * @param id 要停用的字典id
     */
    void disableDict(int id);
    
    /**
     * 启用数据字典
     * @param id 要停用的字典id
     */
    void enableDict(int id);
    
    /**
     * 通过字典类型和字典编码获取字典信息，主要用于字典的转义
     * @param category
     * @param code
     */
    Dict findDictByCode(String category,String code);
}
