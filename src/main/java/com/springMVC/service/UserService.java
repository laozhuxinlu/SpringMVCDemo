package com.springMVC.service;

import java.util.List;
import java.util.Map;

import com.base.entity.SysUser;
import com.base.page.Page;

public interface UserService extends BaseService{

	public SysUser getUser(String userName);
	
	public	Page getAllUser(Map<String, Object>map);
	
	public SysUser getUserById(String userId);
	
	public void addUser(SysUser sysUser);
	
	public void updateUser(SysUser sysUser);
}
