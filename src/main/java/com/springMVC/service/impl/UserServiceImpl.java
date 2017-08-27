package com.springMVC.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springMVC.dao.SysUserMapper;
import com.springMVC.service.UserService;
import com.springMVC.dao.BaseMapper;
import com.base.entity.SysUser;
import com.base.page.Page;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private SysUserMapper userMapper;
	
	@Autowired
	private BaseMapper baseMapper;
	
	private final Logger logger=Logger.getLogger(UserServiceImpl.class);
	
	@Override
	public SysUser getUser(String userName) {
		return userMapper.getUser(userName);
	}

	@Override
	public Page getAllUser(Map<String, Object> map) {
		Page page=(Page) map.get("page");
		Map<String, Object>param=new HashMap<String, Object>();
		param.put("isFalg", page.getPageFlag());
		param.put("firstResult", page.getStart());
		param.put("maxResults", page.getLimit());
		List list=(List) userMapper.getUserAll(param);
		System.out.println(list.get(0));
		Map<String, Object> mapp=new  HashMap<String, Object>();
		mapp.put("cloumnName", "id");
		mapp.put("tableName", "sys_user");
		page.setTotal(this.getTotal(mapp));
		page.setData(list);
		return page;
	}

	@Override
	public SysUser getUserById(String userId) {
		return userMapper.getUserById(userId);
	}

	@Override
	public void addUser(SysUser sysUser) {
		userMapper.addUser(sysUser);
	}

	@Override
	public void updateUser(SysUser sysUser) {
		userMapper.updateUser(sysUser);		
	}

	@Override
	public Integer getTotal(Map<String, Object> map) {
		return baseMapper.getTotal(map);
	}
}
