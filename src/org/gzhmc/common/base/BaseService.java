package org.gzhmc.common.base;

import java.util.List;

/**
 * @author stshen
 *
 *         2017年11月8日
 */
public interface BaseService<T> {
	/**
	 * 插入数据
	 * 
	 * @param t
	 * @return
	 */
	public int add(T t);

	/**
	 * 删除数据
	 * 
	 * @param cId
	 * @return
	 */
	public int delete(int cId);

	/**
	 * 根据id更新将所有字段
	 * 
	 * @param t
	 * @return
	 */
	public int update(T t);

	/**
	 * 对不为空的字段进行更新
	 * 
	 * @param t
	 * @return
	 */
	public int updateSelective(T t);

	/**
	 * 根据id查找实体
	 * 
	 * @param cId
	 * @return
	 */
	public T getById(int cId);

	/**
	 * 获取所有实体
	 * 
	 * @return
	 */
	public List<T> getAll();
}
