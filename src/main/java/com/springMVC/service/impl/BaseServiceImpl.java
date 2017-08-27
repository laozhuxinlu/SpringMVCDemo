package com.springMVC.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springMVC.dao.BaseMapper;
import com.springMVC.service.BaseService;

@Service("baseService")
@Transactional
public class BaseServiceImpl implements BaseService{

	@Autowired
	private BaseMapper baseMapper;
	
	@Override
	public Integer getTotal(Map<String, Object> map) {
		return baseMapper.getTotal(map);
	}


}
