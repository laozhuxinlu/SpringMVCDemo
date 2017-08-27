package com.springMVC.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.base.entity.SysUser;

public interface SysUserMapper {	
	//根据用户名获取用户信息
	public SysUser getUser(@Param("userName")String userName);	
	
	//获取全部用户
	public List getUserAll(@Param("map")Map<String, Object>map);  
	
	//根据ID获取用户信息
	public SysUser getUserById(@Param("id")String id);

	//添加用户
	public void addUser(SysUser sysUser);
	
	//修改用户
	public void updateUser(SysUser sysUser);
	
	//添加用户
	public void deleteUser(SysUser sysUser);	
}
