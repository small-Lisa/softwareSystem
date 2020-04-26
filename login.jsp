<%@page contentType="text/html;charset=utf-8"%>
<jsp:useBean id="loginBean" class="mybean.data.Login" scope="session"/>
<html>
<body background="image/back3.png"><font size=3>
<title>Lisasasa软件中心</title>
<div align="center">
<br><br><br>
<img src="image/welcome.png" width=1400 height=200></img>
<br>
<a href="index.jsp"><font size=4>< 返回首页</font></a>
<br><br><br>
<font size=4><b>请登录您的账号</b></font>
<br><br>
<table>
	<tr>
		<form action="loginServlet" method="post">
			<tr><td>账号：</td><td><input type=text name="phone" placeholder="手机号" required></td></tr>
			<tr><td>密码：</td><td><input type=password name="password" placeholder="数字/字母/下划线" required></td>
				<td><a href="resetpassword.jsp"><font size=2>?忘记密码</font></a></td>
			</tr>
	</tr>
</table>
<br>
<table>
	<tr>
		<td><input type=submit name="g" value="登录"></td>
		</form>
		<td>&nbsp&nbsp&nbsp&nbsp</td>
		<td><a href="register.jsp"><font size=2>未注册？</font><font size=3>注册</font></td>
	</tr>
</table>
<div align="center">
	登陆反馈信息：<jsp:getProperty name="loginBean" property="backNews"/>
	<br>账户：<jsp:getProperty name="loginBean" property="phone"/>;
</div>
</body>
</html>