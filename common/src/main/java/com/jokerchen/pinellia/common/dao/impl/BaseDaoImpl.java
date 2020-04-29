package com.jokerchen.pinellia.common.dao.impl;

import com.jokerchen.pinellia.common.dao.BaseDao;
import com.jokerchen.pinellia.common.model.Page;
import com.jokerchen.pinellia.common.model.Sort;
import com.jokerchen.pinellia.common.util.PageUtil;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManagerFactory;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**   
 * @description: 持久层通用查询方法实现
 * 			主要用于封装一些常用查询和操作。比如分页等
 * 			所有的dao层接口都需要继承
 * @author Joker Chen 
 * @date 2019-03-18 17:11:09  
 */
public abstract class BaseDaoImpl<T> implements BaseDao<T>{

    @Autowired
    private EntityManagerFactory entityManagerFactory;


    @Override
    public Session getSession() {
        return entityManagerFactory.unwrap(SessionFactory.class).getCurrentSession();
    }

    @Override
    public Serializable save(T entity) {
        return getSession().save(entity);
    }

    @Override
    public void update(T entity) {
        getSession().update(entity);
    }

    @Override
    public void saveOrUpdate(T entity) {

        getSession().saveOrUpdate(entity);
    }

    @Override
    public void delete(T entity) {
        getSession().delete(entity);
    }

    @Override
    public void merge(T entity){
        getSession().merge(entity);
    }

    @Override
    public T getOne(int id){
        return getSession().get(getEntityClass(), id);
    }

    @Override
    public List<T> findAll(){
        String hql = "FROM " + getEntityClass().getName();
        return this.findByHql(hql);
    }
    
    @Override
    public List<T> findAll(Sort sort){
    	String hql = "FROM " + getEntityClass().getName() + sort.orders();
        return this.findByHql(hql);
    }

    @Override
    public Page pageAll(){
        String hql = "FROM " + getEntityClass().getName();
        return this.pageByHql(hql);
    }
    
    @Override
    public Page pageAll(Sort sort){
    	String hql = "FROM " + getEntityClass().getName() + sort.orders();
    	return this.pageByHql(hql);
    }

    @Override
    public Page pageAll(Page page){
        String hql = "FROM " + getEntityClass().getName();
        return this.pageByHql(hql,page);
    }
    
    @Override
    public Page pageAll(Page page, Sort sort){
    	String hql = "FROM " + getEntityClass().getName() + sort.orders();
    	return this.pageByHql(hql,page);
    }

    @Override
    public T getOneByField(String field, Object value){
        String hql = " FROM " + getEntityClass().getName() + " WHERE " + field + " = ?0 ";
        return this.createQuery(hql,value).uniqueResult();
    }

    @Override
    public List<T> findByField(String field, Object value){
        if(field != null && !"".equals(field)){
            if(field.contains("%") || field.contains(" ") || field.toUpperCase().contains(" OR ")){
            }else{
                String hql = " FROM " + getEntityClass().getName() + " WHERE " + field + " = ?0 ";
                return this.findByHql(hql,value);
            }
        }
        return null;
    }
    
    @Override
    public List<T> findByField(String field, Object value,Sort sort){
    	if(field != null && !"".equals(field)){
    		if(field.contains("%") || field.contains(" ") || field.toUpperCase().contains(" OR ")){
    		}else{
    			String hql = " FROM " + getEntityClass().getName() + " WHERE " + field + " = ?0 " + sort.orders();
    			return this.findByHql(hql,value);
    		}
    	}
    	return null;
    }

    @Override
    public Query<T> createQuery(String hql, Object... params) {
		Query<T> query = getSession().createQuery(hql,getEntityClass());
		this.querySetParameter(query,hql,params);
        return query;
    }

    @Override
    public List<T> findByHql(String hql,Object ... params){
        Query<T> query = this.createQuery(hql, params);
        return query.list();
    }

    @Override
    public Query<T> createQuery(String hql, Map<String,Object> map){
        Query<T> query = getSession().createQuery(hql,getEntityClass());
        if(map != null && map.size() > 0){
            query.setProperties(map);
        }
        return query;
    }

    @Override
    public List<T> findByHql(String hql, Map<String,Object> map){
        Query<T> query = this.createQuery(hql, map);
        return query.list();
    }

    @Override
    public <K> NativeQuery<K> createSqlQuery(String sql, Class<K> clazz, Object... params) {
        @SuppressWarnings("unchecked")
		NativeQuery<K> query = getSession().createNativeQuery(sql);
        //NativeQuery<K> query = getSession().createNativeQuery(sql,clazz);
        //query.setResultTransformer(Transformers.aliasToBean(clazz));
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(clazz));
        this.querySetParameter(query,sql,params);
        return query;
    }

    @Override
    public <K> List<K> findBySql(String sql, Class<K> clazz, Object... params) {
        NativeQuery<K> query = this.createSqlQuery(sql,clazz, params);
        return query.list();
    }

    @Override
    public <K> NativeQuery<K> createSqlQuery(String sql, Class<K> clazz, Map<String, Object> map) {
        @SuppressWarnings("unchecked")
		NativeQuery<K> query = getSession().createNativeQuery(sql);
        //query.setResultTransformer(Transformers.aliasToBean(clazz));
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(clazz));
        if (map != null && map.size() > 0) {
            query.setProperties(map);
        }
        return query;
    }

    @Override
    public <K> List<K> findBySql(String sql, Class<K> clazz, Map<String, Object> map) {
        NativeQuery<K> query = this.createSqlQuery(sql,clazz, map);
        return query.list();
    }


    @Override
    public NativeQuery<?> createSqlQuery(String sql, Object... params) {
        NativeQuery<?> query = getSession().createNativeQuery(sql);
        this.querySetParameter(query,sql,params);
        return query;
    }

    /**
     * 参数使用 ?d （问号加数字）这种占位符方式时使用
     * @param query
     * @param hqlOrSql
     * @param params
     */
    private void querySetParameter(Query<?> query,String hqlOrSql,Object... params){
        if (params != null && params.length > 0) {
            //校正位，Sql查询，占位符默认从1开始，如果传入的占位符包含?0，则从0开始
            String placeholder = "?0";
            int correct = hqlOrSql.contains(placeholder) ?0:1;
            for (int i = 0; i < params.length; ++i) {
                query.setParameter(i+correct, params[i]);
            }
        }
    }

    @Override
    public NativeQuery<?> createSqlQuery(String sql, Map<String, Object> map){
        NativeQuery<?> query = getSession().createNativeQuery(sql);
        if (map != null && map.size() > 0) {
            query.setProperties(map);
        }
        return query;
    }

    @Override
    public void updateBySql(String sql, Object... params){
        NativeQuery<?> query = this.createSqlQuery(sql, params);
        query.executeUpdate();
    }

    @Override
    public void updateBySql(String sql, Map<String, Object> map){
        NativeQuery<?> query = this.createSqlQuery(sql, map);
        query.executeUpdate();
    }

    @Override
    public Page pageByHql(String hql, Object... params){
        Query<?> query = this.createQuery(hql,params);
        return this.pageQuery(query);
    }

    @Override
    public Page pageByHql(String hql, Map<String,Object> map){
        Query<?> query = this.createQuery(hql,map);
        return this.pageQuery(query);
    }

    @Override
    public Page pageBySql(String sql,Class<?> clazz, Object... params){
        Query<?> query = this.createSqlQuery(sql, clazz, params);
        return this.pageQuery(query);
    }

    @Override
    public Page pageBySql(String sql, Class<?> clazz, Map<String,Object> map){
        Query<?> query = this.createSqlQuery(sql, clazz, map);
        return this.pageQuery(query);
    }

    /**
     * 从上下文中读取request，然后从中读取分页信息，进行分页查询
     * @param query 查询对象语句
     * @return
     */
    private Page pageQuery(Query<?> query){
        Page page = PageUtil.getPageRequest();
        return this.pageQuery(query, page);
    }

    /**
     * 分页查询
     * @param query 查询对象语句
     * @param page  分页信息
     * @return
     */
    @Override
    public Page pageQuery(Query<?> query,Page page){
        ScrollableResults scrollableResults = query.scroll();
        scrollableResults.last();
        int count = scrollableResults.getRowNumber() + 1;
        page.setTotalElements(count);
        if(count > 0){
            //查询当前页码的数据
            query.setFirstResult((page.getPageNumber() - 1)* page.getPageSize());
            query.setMaxResults(page.getPageSize());
            page.setContent(query.list());
        }
        //调整pageVO中的数据
        PageUtil.adjustData(page);
        return page;
    }

    /**
     * 批量保存数据
     * @param list
     * @return
     */
    @Override
    public int batchSave(Collection<T> list) {
        int count = 0;
        for (T entity : list) {
            ++count;
            this.save(entity);
            if (count % 100 == 0) {
                this.getSession().flush();
                this.getSession().clear();
            }
        }
        return count;
    }

    /**
     * 批量保存或更新数据
     * @param list
     * @return
     */
    @Override
    public int batchUpdate(Collection<T> list) {
        int count = 0;
        for (T entity : list) {
            ++count;
            this.merge(entity);
            if (count % 100 == 0) {
                this.getSession().flush();
                this.getSession().clear();
            }
        }
        return count;
    }

    /**
     * 批量保存或更新数据
     * @param list
     * @return
     */
    @Override
    public int batchSaveOrUpdate(Collection<T> list) {
        int count = 0;
        for (T entity : list) {
            ++count;
            this.saveOrUpdate(entity);
            if (count % 100 == 0) {
                this.getSession().flush();
                this.getSession().clear();
            }
        }
        return count;
    }

    /**
     * 批量删除数据
     * @param list
     */
    @Override
    public void batchDelete(Collection<T> list) {
        int count = 0;
        for (T entity : list) {
            ++count;
            this.delete(entity);
            if (count % 100 == 0) {
                this.getSession().flush();
                this.getSession().clear();
            }
        }
    }

    @Override
    public void flush() {
        this.getSession().flush();
    }

    @Override
    public void clear() {
        this.getSession().clear();
    }

    /**
     * 将hibernate去持久化
     */
    @Override
    public void evict(Object entity) {
        this.getSession().evict(entity);
    }
    
    /**
     * 获取当前Entity对象的class
     * @return
     */
    private Class<T> getEntityClass() {
    	@SuppressWarnings("unchecked")
		Class<T> clazz = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    	return clazz;
    }
}
