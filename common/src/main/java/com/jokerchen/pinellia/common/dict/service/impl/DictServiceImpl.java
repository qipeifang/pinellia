package com.jokerchen.pinellia.common.dict.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jokerchen.pinellia.common.cache.CacheUtil;
import com.jokerchen.pinellia.common.constant.CacheKeyConstant;
import com.jokerchen.pinellia.common.constant.Constant;
import com.jokerchen.pinellia.common.exception.ServiceException;
import com.jokerchen.pinellia.common.model.Sort;
import com.jokerchen.pinellia.common.util.StringUtil;
import com.jokerchen.pinellia.common.dict.dao.DictCategoryDao;
import com.jokerchen.pinellia.common.dict.dao.DictDao;
import com.jokerchen.pinellia.common.dict.entity.Dict;
import com.jokerchen.pinellia.common.dict.entity.DictCategory;
import com.jokerchen.pinellia.common.dict.service.DictService;

/**   
 * @description: 数据字典逻辑层
 * @author Joker Chen 
 * @date 2019-03-21 16:48:16  
 */
@Service
public class DictServiceImpl implements DictService {
	
	@Autowired
	private DictDao dictDao;
	
	@Autowired
	private DictCategoryDao dictCategoryDao;
	

	@Override
	public List<DictCategory> findDictCategory(String category) {
		if(category != null && !category.isEmpty()) {
			return dictCategoryDao.findDictCategory(category);
		}else {
			return dictCategoryDao.findAll();
		}
	}

	@Override
	public List<Dict> findDictByCategory(String category) {
		List<Dict> list = dictDao.findByField("category", category, Sort.createSort("sequence"));
		return list;
	}

	@Override
	public List<Dict> findDict(String category) {
		if(category != null && !category.trim().isEmpty()) {
			String key = CacheKeyConstant.DICT_CATEGORY_PREFIX + category;
			List<Dict> list = CacheUtil.getList(key,Dict.class);
			if(list == null) {
				list = dictDao.findDict(category);
				if(list != null) {
					CacheUtil.set(key, list);
				}
			}
			return list;
		}
		return null;
	}

	@Override
	public void saveCategory(DictCategory dictCategory,String oldCategory) {
		//判断字典类型编码是否有修改
		if(oldCategory != null && !oldCategory.isEmpty() && !dictCategory.getCategory().equals(oldCategory)){
			DictCategory entity = dictCategoryDao.getOneByField("category",dictCategory.getCategory());
			if(entity != null) throw new ServiceException(dictCategory.getCategory()+"，字典类型编码已经存在");
			//有修改字典类型编码的话要更新下面的字典系想你
			List<Dict> list = dictDao.findByField("category",oldCategory);
			list.forEach(item->item.setCategory(dictCategory.getCategory()));
			dictDao.batchUpdate(list);
			entity = dictCategoryDao.getOneByField("category",oldCategory);
			dictCategoryDao.delete(entity);
			//删除缓存
			CacheUtil.delete(CacheKeyConstant.DICT_CATEGORY_PREFIX + oldCategory);
		}
		dictCategoryDao.saveOrUpdate(dictCategory);
	}
	
	@Override
	public void deleteCategory(String category) {
		//删除数据字典类型
		dictCategoryDao.delete(dictCategoryDao.getOneByField("category", category));
		//删除该类型下面的数据字典
		dictDao.batchDelete(dictDao.findByField("category", category));
		//删除缓存
		CacheUtil.delete(CacheKeyConstant.DICT_CATEGORY_PREFIX + category);
	}

	@Override
	public void saveDict(Dict dict) {
		boolean flag = dictDao.existCode(dict.getCategory(),dict.getCode(),dict.getId());
		if(flag) throw new ServiceException(dict.getCode()+"，字典编码已经存在");
		dictDao.saveOrUpdate(dict);
		//删除缓存
		CacheUtil.delete(CacheKeyConstant.DICT_CATEGORY_PREFIX + dict.getCategory());
	}
	
	@Override
	public void deleteDict(int id) {
		Dict dict = dictDao.getOne(id);
		//删除缓存
		CacheUtil.delete(CacheKeyConstant.DICT_CATEGORY_PREFIX + dict.getCategory());
		dictDao.delete(dict);
	}

	@Override
	public void disableDict(int id) {
		Dict dict = dictDao.getOne(id);
		//删除缓存
		CacheUtil.delete(CacheKeyConstant.DICT_CATEGORY_PREFIX + dict.getCategory());
		dict.setState(Constant.DATA_STATE_DISABLE);
		dictDao.update(dict);
	}

	@Override
	public void enableDict(int id) {
		Dict dict = dictDao.getOne(id);
		//删除缓存
		CacheUtil.delete(CacheKeyConstant.DICT_CATEGORY_PREFIX + dict.getCategory());
		dict.setState(Constant.DATA_STATE_NORMAL);
		dictDao.update(dict);
	}
	
	@Override
	public Dict findDictByCode(String category,String code) {
		if(StringUtil.isEmpty(category) || StringUtil.isEmpty(code)) return null;
		List<Dict> list = this.findDict(category);
		if(list != null && list.size()> 0) {
			for(Dict dict : list) {
				if(dict.getCode().equals(code)) {
					return dict;
				}
			}
		}
		return null;
	}
}
