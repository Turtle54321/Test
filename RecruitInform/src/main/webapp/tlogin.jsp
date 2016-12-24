<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>大连理工大学软件学院实习生就业服务系统登录页面</title>

<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/base.css" rel="stylesheet">
<link href="css/tlogin.css" rel="stylesheet">

<!--[if lt IE 9]>
	<script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	<script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->

<script>
		function checkForm(){
			var userName=$("#userName").val();
			var password=$("#password").val();
			if(userName==null||userName==""){
				$("#error").html("用户名不能为空！");
				return false;
			}
			if(password==null||password==""){
				$("#error").html("密码不能为空！");
				return false;
			}
			return true;
		}
	</script>
</head>
<body>
	<div class="a_top">
		<div class="a_1949_bg"></div>
		<div class="a_logo">
			<a href="#"><img src="./images/a_logo.png" width="381"
				height="67"></a>
		</div>
	</div>
	<div class="content">
		<div class="loginbox">
			<form class="form-horizontal" role="form"
				action="${pageContext.request.contextPath}/teacher/tlogin.do"
				method="post" onsubmit="return checkForm()">
				<div class="form-group">
					<label for="userName" class="col-sm-2 control-label">账号</label>
					<div class="col-sm-10">
						<input id="userName" name="teacherId" type="text"
							class="form-control" placeholder="请输入管理员号码">
					</div>
				</div>
				<div class="form-group">
					<label for="password" class="col-sm-2 control-label">密码</label>
					<div class="col-sm-10">
						<input id="password" name="password" type="password"
							class="form-control" placeholder="请输入密码">
					</div>
				</div>
				<div class="divider"></div>

				<div class="form-group">
					<p class="col-sm-offset-2 col-sm-5">实习生就业服务系统</p>
					<%String message = request.getParameter("message");
				if(message != null){
			%>
					<span><font color="red" id="error"><%=message %></font></span>
					<% }else{ %>
					<span><font color="red" id="error">${errorInfo }</font></span>
					<%} %>

					<div class="col-sm-offset-2 col-sm-3">
						<button type="submit" class="btn btn-default">登录</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<script src="js/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>