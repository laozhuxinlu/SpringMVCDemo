package com.springMVC.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.springMVC.service.UserService;
import com.base.entity.SysUser;
import com.base.page.Page;
import com.base.util.ApplicationException;
import com.base.util.MD5;
import com.base.util.StringUtil;
import com.google.common.base.Strings;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{

	private final Logger logger =Logger.getLogger(UserController.class);
	private Timer timer = null;
	
	@Autowired
	private UserService userService;	
	
	@RequestMapping("/getLogin")
	public ModelAndView getLogin(){
		return new ModelAndView("/tripMya/login");
	}

	
	@RequestMapping("/login")
	@ResponseBody
	public Map<String,Object> login(HttpServletRequest request){
        Map<String, Object> map=new HashMap<String, Object>();
		
		/****************获得前台用户登录信息******************/
		String userName = request.getParameter("userName");
		String passWordNoMd5 = request.getParameter("passWord");		
		
		String passWord = MD5.GetMD5Code(passWordNoMd5);
		
		try {
			if(StringUtil.isEmpty(userName)) {
				throw new ApplicationException("请输入用户名!");
			}
			userName = userName.trim();
			// 过滤特殊字符
			userName = StringUtil.filterSQLCharacter(userName);
			
			SysUser staff = (SysUser) userService.getUser(userName);
			 
			if(staff != null ) {
				if(!passWord.equals(staff.getPassWord())) { // password error
					throw new ApplicationException("密码错误!");
				}

				if(staff.getDisableFlag() != null && staff.getDisableFlag().intValue() == 1) { // disabled
					throw new ApplicationException("该账户已停用!");
				}

				
				
				/*TODO balabla, 执行用户登录后的逻辑  */
				
				
			} else {
				throw new ApplicationException("该用户不存在!");
			}
			
			map.put("success", true);
			map.put("message", "登录成功!");			
		}catch(Exception e){
			map.put("success", false);
			map.put("message", e.getMessage());
		}
		return map;
	}
	
	
	@RequestMapping("/getUser")
	@ResponseBody
	public Page getAllUser(HttpServletRequest request)throws Exception{
		logger.info("---------------enter the method getAllUser()   查询用户-------------------------");
		page = new Page();
		
		String pageIndex = request.getParameter("pageIndex");
		String pageSize = request.getParameter("pageSize");
		this.validateNullException(pageIndex, "参数pageIndex为空!");
		this.validateNullException(pageSize, "参数pageSize为空!");
		page.setStart(Integer.parseInt(pageSize)
				* Integer.parseInt(pageIndex));
		page.setLimit(Integer.parseInt(pageSize));
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("page", page);
		page =userService.getAllUser(map);
		page.setSuccess(true);
		return page;
	}
	

	@RequestMapping("/saveOrUpdateStaff")
	@ResponseBody
	public Map<String, Object> saveOrUpdateStaff(HttpServletRequest request) throws Exception{
		logger.info("---------------enter the method saveOrUpdateStaff()  添加用户 -------------------------");
		Map<String, Object>map=new HashMap<String, Object>();
		String id = request.getParameter("id");
		String trueName = request.getParameter("trueName");
		String passWordNoMd5 = request.getParameter("passWord");
		String passWord = MD5.GetMD5Code(passWordNoMd5);
		String userName = request.getParameter("userName");
		String disableFlag = request.getParameter("disableFlag");
		String telephoneNo = request.getParameter("telephoneNo");
		//String type=request.getParameter("type");
		
	//  不能为空的数据验证
		this.validateNullException(passWord, "密码不能为空!");
		this.validateNullException(userName, "用户名不能为空!");
		this.validateNullException(trueName, "用户名称不能为空!");
		//this.validateNullException(type, "用户名称不能为空!");
		
		// 过滤'及*等特殊字符
		userName = StringUtil.filterSQLCharacter(userName);
		
		SysUser user = null;
		if(Strings.isNullOrEmpty(id)){//id为空则新增
			user=new SysUser();
			//判断用户名是否存在
			SysUser isExis=userService.getUser(userName);
			if(isExis!=null){
				map.put("success", false);
				map.put("message", "该用户名已经存在!");
				return map;
//				throw new ApplicationException("该用户名已经存在!");
			}
		}else{//修改操作
			user=userService.getUserById(id);
		}
		user.setTrueName(trueName);
		user.setUserName(userName);
		user.setPassWord(passWord);
		user.setType("0");
		if(!StringUtil.isEmpty(telephoneNo)) {
			boolean flag = telephoneNo.trim().matches("^1\\d{10}");
			if(!flag) {
				map.put("success", false);
				map.put("message", "手机号必须为11位数字,首位无需加0!");
				return map;
			}
			user.setTelephoneNo(telephoneNo);
		} else {
			user.setTelephoneNo("");
		}
		user.setDisableFlag(Integer.valueOf(disableFlag));
		if(Strings.isNullOrEmpty(id)){
			try {
				userService.addUser(user);
				logger.info("添加用户成功");
				map.put("success", true);
				map.put("message", "添加用户成功");
			} catch (Exception e) {
				logger.error("添加用户失败,原因是:"+e);
				map.put("success", false);
				map.put("message", e.getMessage());
			}
		}else{
			try {
				userService.updateUser(user);
				logger.info("修改用户成功");
				map.put("success", true);
				map.put("message", "修改用户成功");
			} catch (Exception e) {
				logger.error("修改用户失败,原因是:"+e);
				map.put("success", false);
				map.put("message", e.getMessage());
			}
		}
		return map;
	}	
}
