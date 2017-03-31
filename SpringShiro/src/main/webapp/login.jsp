<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>login</title>
  </head>
  
  <body>
    <form action="${pageContext.request.contextPath }/user/login.do" method="post">
    	username:<input type="text" name="username"/><br>
    	password:<input type="password" name="password"/><br>
    	<input type="submit" value="登陆">${error }
    </form>
  </body>
</html>
