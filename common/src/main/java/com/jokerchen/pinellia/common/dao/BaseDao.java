package com.jokerchen.pinellia.common.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;

import com.jokerchen.pinellia.common.model.Page;
import com.jokerchen.pinellia.common.model.Sort;

/**   
 * @description: 持久层通用查询方法接口定义
 * 			主要用于封装一些常用查询和操作。比如分页等
 * 			所有的dao层接口都需要继承
 * @author Joker Chen 
 * @date 2019-03-18 17:10:58  
 */
@Transactional(readOnly=true)
public interface BaseDao<T> {

    /**
     * 获取hibernate的会话对象
     * @return 返回会话对象
     */
    Session getSession();

    /**
     * 保存实体
     * @param entity 要保存的实体对象
     * @return	返回保存的实体主键
     */
    @Transactional(rollbackFor=Exception.class)
    Serializable save(T entity);

    /**
     * 修改实体
     * @param entity 要修改的实体对象
     */
    @Transactional(rollbackFor=Exception.class)
    void update(T entity);

    /**
     * 保存或修改实体
     * @param entity 要变更的实体对象
     */
    @Transactional(rollbackFor=Exception.class)
    void saveOrUpdate(T entity);

    /**
     * 删除对象
     * @param entity 要删除的实体对象
     */
    @Transactional(rollbackFor=Exception.class)
    void delete(T entity);

    /**
     * 保存修改对象
     * @param entity
     */
    @Transactional(rollbackFor=Exception.class)
    void merge(T entity);

    /**
     * 通过id查询实体信息
     * @param id		查询的数据主键
     * @return			返回要查询的实体对象
     */
    T getOne(int id);

    /**
     * 查询实体数据
     * @return 查询结果
     */
    List<T> findAll();
    
    /**
     * 查询实体数据
     * @param 排序信息
     * @return 查询结果
     */
    List<T> findAll(Sort sort);

    /**
     * 分页查询全部
     * @return
     */
    Page pageAll();
    
    /**
     * 分页查询全部
     * @param	排序信息
     * @return
     */
    Page pageAll(Sort sort);

    /**
     * 分页查询全部
     * @param page  分页信息
     * @return
     */
    Page pageAll(Page page);
    
    /**
     * 分页查询全部
     * @param page  分页信息
     * @param sort	排序信息
     * @return
     */
    Page pageAll(Page page, Sort sort);

    /**
     * 根据属性，查询实体数据，只获得一个实体
     * @param field 查询的字段
     * @param value 查询的值
     * @return
     */
    T getOneByField(String field, Object value);

    /**
     * 根据属性，查询实体数据
     * @param field 查询的字段
     * @param value 查询的值
     * @return  查询结果
     */
    List<T> findByField(String field, Object value);
    
    /**
     * 根据属性，查询实体数据
     * @param field 查询的字段
     * @param value 查询的值
     * @param sort	排序信息
     * @return  查询结果
     */
    List<T> findByField(String field, Object value,Sort sort);

    /**
     * 获取查询对象
     * @param hql	 查询的hql语句
     *          	示例： from dict where id = ?
     *            	params = 1
     * @param params 参数
     * @return	返回查询对象
     */
    Query<T> createQuery(String hql, Object... params);

    /**
     * 根据hql查询数据
     * @param hql	 查询的hql语句
     *          	示例： from dict where id = ?
     *            	params = 1
     * @param params 参数
     * @return	返回查询结果
     */
    List<T> findByHql(String hql,Object ... params);

    /**
     * 获取查询对象
     * @param hql	 查询的hql语句
     *               示例： from dict where id = :id
     *            	map = { id:1}
     * @param map 参数
     * @return	返回查询对象
     */
    Query<T> createQuery(String hql, Map<String,Object> map);

    /**
     * 根据hql查询数据
     * @param hql	 查询的hql语句
     *               示例： from dict where id = :id
     *            	map = { id:1}
     * @param map 参数
     * @return	返回查询结果
     */
    List<T> findByHql(String hql, Map<String,Object> map);

    /**
     * 获取查询对象
     * @param sql	 查询的sql语句
     *          	示例： select * from dict where id = ?
     *            	params = 1
     * @param params 参数
     * @return	返回查询对象
     */
    <K> NativeQuery<K> createSqlQuery(String sql, Class<K> clazz, Object... params);

    /**
     * 根据hql查询数据
     * @param sql	 查询的hql语句
     *          	示例： select * from dict where id = ?
     *            	params = 1
     * @param params 参数
     * @return	返回查询结果
     */
    <K> List<K> findBySql(String sql, Class<K> clazz, Object... params) ;

    /**
     * 获取查询对象
     * @param sql	 查询的sql语句
     *          	示例： select * from dict where id = :id
     *            	map = { id:1}
     * @param map 参数
     * @return	返回查询对象
     */
    <K> NativeQuery<K> createSqlQuery(String sql, Class<K> clazz, Map<String, Object> map);

    /**
     * 根据hql查询数据
     * @param sql	 查询的hql语句
     *          	示例： select * from dict where id = :id
     *            	map = { id:1}
     * @param map 参数
     * @return	返回查询结果
     */
    <K> List<K> findBySql(String sql, Class<K> clazz, Map<String, Object> map);


    /**
     * 获取操作对象对象
     * @param sql	 查询的sql语句
     *          	示例： delete from dict where id = ?
     *            	params = 1
     * @param params 参数
     * @return	返回查询对象
     */
    NativeQuery<?> createSqlQuery(String sql, Object... params) ;

    /**
     * 获取操作对象
     * @param sql	 查询的sql语句
     *          	示例： delete from dict where id = :id
     *            	map = { id:1}
     * @param map 参数
     * @return	返回查询对象
     */
    NativeQuery<?> createSqlQuery(String sql, Map<String, Object> map);

    /**
     * 通过sql语句执行数据库变更操作
     * @param sql	 查询的sql语句
     *          	示例： delete from dict where id = ?
     *            	params = 1
     * @param params 参数
     * @return	返回查询对象
     */
    void updateBySql(String sql, Object... params);

    /**
     * 通过sql语句执行数据库变更操作
     * @param sql	 查询的sql语句
     *          	示例： delete from dict where id = :id
     *            	map = { id:1}
     * @param map 参数
     * @return	返回查询对象
     */
    void updateBySql(String sql, Map<String, Object> map);

    /**
     * 分页查询数据，会从 PageContext 读取本次从前端传过来的分页参数信息
     * @param hql		查询的hql语句
     *          	示例： from dict where id = ?
     *            	params = 1
     * @param params	查询参数
     * @return 分页数据对象
     */
    Page pageByHql(String hql, Object... params);

    /**
     * 分页查询数据，会从 PageContext 读取本次从前端传过来的分页参数信息
     * @param hql		查询的hql语句
     *          	示例： from dict where id = :id
     *            	map = {id :1}
     * @param map	查询参数
     * @return 分页数据对象
     */
    Page pageByHql(String hql, Map<String,Object> map);

    /**
     * 分页查询数据，会从 PageContext 读取本次从前端传过来的分页参数信息
     * @param sql		查询的hql语句
     *          	示例： select * from dict where id = ?
     *            	params = 1
     * @param params	查询参数
     * @return 分页数据对象
     */
    Page pageBySql(String sql, Class<?> clazz, Object... params);

    /**
     * 分页查询数据，会从 PageContext 读取本次从前端传过来的分页参数信息
     * @param sql		查询的hql语句
     *          	示例： select * from dict where id = :id
     *            	map = {id :1}
     * @param map	查询参数
     * @return 分页数据对象
     */
    Page pageBySql(String sql, Class<?> clazz, Map<String,Object> map);

    /**
     * 分页查询
     * @param query 查询对象语句
     * @param page  分页信息
     * @return
     */
    Page pageQuery(Query<?> query, Page page);

    /**
     * 批量保存数据
     * @param list
     * @return
     */
    int batchSave(Collection<T> list) ;

    /**
     * 批量保存或更新数据
     * @param list
     * @return
     */
    int batchUpdate(Collection<T> list);

    /**
     * 批量保存或更新数据
     * @param list
     * @return
     */
    int batchSaveOrUpdate(Collection<T> list);

    /**
     * 批量删除数据
     * @param list
     */
    void batchDelete(Collection<T> list);


    /**
     * 将hibernate去持久化
     */
    void evict(Object entity);

    /**
     * 将session的缓存中的数据与数据库同步
     */
    void flush();

    /**
     * 清除session中的缓存数据
     */
    void clear();
}
