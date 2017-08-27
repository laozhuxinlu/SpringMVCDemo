<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>登录</title>
<link rel="stylesheet" href="${ctx}/backoffice/css/loginCss.css" />
<script type="text/javascript" src="${ctx}/res/js/scripts/jquery-1.6.2.min.js"></script>
</head>
<body>
	<div class="conter">
	  <div class="login">
	    <h1><font color="blue"><b>springMVC Demo</b></font></h1>
	    <h2>
	    	<font color="#8B008B">后台管理系统</font> 
	    </h2>
	    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table01">
		  <tr>
		    <th>用户名：</th>
		    <td>		    
		    	<input id="userName" style="line-height: 25px;" name="userName" type="text" />		    	
		    </td>		    
		  </tr>
		  <tr>
		    <th>密 码：</th>
		    <td><input id="passWord" style="line-height: 25px;" name="passWord" type="password" onkeydown="pwdKeydown()"/></td>
		  </tr>
		  <tr>
		    <td colspan="2">
		    	<div style=" width:130px; margin:5px auto;">
			    	<a id="loginBtn" href="#" onclick="login()" >登录</a>
			    	<a id="hidloginBtn" style="display:none;">登录</a>
			    	<a id="cancelBtn" href="#" onclick="clearForm()">取消</a>
		    	</div>
		    </td>
		    </tr>
		    <tr>
		    <th></th>
		    <td><span id="errorMsg" style="color:red;"></span></td>
		  </tr>
		</table>
    </div>
</div>
</body>
</html>
<script type="text/javascript">

var home_url = '${ctx}';


function login(){
	// 验证用户名
	if($.trim($("#userName").val()) == ''){
		$("#errorMsg").html("请输入用户名!");
		$("#userName").focus();
		return false;
	}
	// 验证密码
	if($.trim($("#passWord").val())==''){
		$("#errorMsg").html("请输入密码!");
		$("#passWord").focus();
		return false;
	}

	$("#errorMsg").html("正在登录,请稍候......");
	
	document.getElementById("loginBtn").style.display="none";
	document.getElementById("hidloginBtn").style.display="block";
	setTimeout(function(){
		$.ajax({
			url:home_url+'/user/login.do',
			type:'post',
			dataType:'json',
			cache:false,
			data:{
				userName:$.trim($("#userName").val()),
				passWord:$.trim($("#passWord").val())
			},
			success:function(data) {
				/* alert(data.success); */
				document.getElementById("loginBtn").style.display="block";
				document.getElementById("hidloginBtn").style.display="none";
				
				if(data.success) { // 登录成功
					//window.location.href="../index.jsp";
					$("#errorMsg").html("登录成功！");
				} else { // 报消息
					$("#errorMsg").html(data.message);
				}
			},
			error:function(data) {
				document.getElementById("loginBtn").style.display="block";
				document.getElementById("hidloginBtn").style.display="none";
				$("#errorMsg").html("程序异常,无法登录");
				$("#errorMsg").html(data.success);
			}
		});
	},200);
}

function clearForm() {
	$("#userName").val("");
	$("#passWord").val("");
	$("#loginCode").val("");
}

$(document).keydown(function (event) {
	if(window.event.keyCode=='13') {
		login();
	}
});
</script>