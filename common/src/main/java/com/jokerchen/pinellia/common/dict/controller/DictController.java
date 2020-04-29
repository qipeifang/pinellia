package com.jokerchen.pinellia.common.dict.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.RequestMapping;

import com.jokerchen.pinellia.common.dict.entity.Dict;
import com.jokerchen.pinellia.common.dict.entity.DictCategory;

/**   
 * @description: 数据字典维护
 * @author Joker Chen 
 * @date 2019-03-21 16:44:59  
 */
@RequestMapping("/dict")
public interface DictController {

	/**
	 * 根据字典类型编码或名称查找字典类型信息
	 * @param category
	 * @return
	 */
    @RequestMapping("/findDictCategory")
	List<DictCategory> findDictCategory(String category);
	
	/**
	 * 根据字典类型获取字典信息
	 * @param category
	 * @return
	 */
    @RequestMapping("/findDictByCategory")
	List<Dict> findDictByCategory(String category);

    /**
     * 根据字典类型获取字典信息，主要用于各个需要在页面展示数据字典的地方
     * @param category
     * @return
     */
    @RequestMapping("/findDict")
    List<Dict> findDict(@NotNull String category);
	
    /**
     * 保存数据字典类型
     * @param dictCategory
     * @param oldCategory   原字典类型编码
     */
    @RequestMapping("/saveCategory")
    void saveCategory(@Valid DictCategory dictCategory,String oldCategory);

    /**
     * 删除数据字典类型
     * @param category	要删除的数据字典类型
     */
    @RequestMapping("/deleteCategory")
    void deleteCategory(@NotNull String category);

    /**
     * 保存数据字典
     * @param dict
     */
    @RequestMapping("/saveDict")
    void saveDict(@Valid Dict dict);

    /**
     * 删除数据字典
     * @param id 要删除的字典id
     */
    @RequestMapping("/deleteDict")
    void deleteDict(int id);
    
    /**
     * 停用数据字典
     * @param id 要停用的字典id
     */
    @RequestMapping("/disableDict")
    void disableDict(int id);
    
    /**
     * 启用数据字典
     * @param id 要停用的字典id
     */
    @RequestMapping("/enableDict")
    void enableDict(int id);
}
