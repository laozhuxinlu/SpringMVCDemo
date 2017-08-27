package com.springMVC.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface BaseMapper {

	//获取某张表数据总量
	public Integer getTotal(@Param("map")Map<String, Object>map);
	
	
}
