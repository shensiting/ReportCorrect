package org.gzhmc.common.base;

import java.util.List;

/**
*@Author : gcliang 
*@Date : 2016年6月8日
*/
//使用泛型
public interface BaseMapper <T>{
	//插入数据
	public int add(T t); 
	//删除数据
	public int delete(int cId);
	//根据id更新将所有字段
	public int update(T t);
	//对不为空的字段进行更新
	public int updateSelective(T t);
	//根据id查找实体
	public T getById(int cId);
	//获取所有实体
	public List<T> getAll();
	
}
