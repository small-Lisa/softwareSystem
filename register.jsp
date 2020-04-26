<%@page contentType="text/html;charset=utf-8"%>
<jsp:useBean id="userBean" class="mybean.data.Register" scope="request"/>
<html>
<body background="image/back3.png"><font size=3>
<title>Lisasasa软件中心</title>
<div align="center">
<br><br><br>
<img src="image/welcome.png" width=1400 height=200></img>
<br>
<a href="index.jsp"><font size=4>< 返回首页</font></a>
<br><br><br>
<FORM action="registerServlet" method="post" name=form>
<table>
	<tr><td>*&nbsp手机号：</td><td><Input type="text" name="phone" maxlength="11" oninput="value=value.replace(/[^\d]/g,'')" required></td>
		<td><font size=2 color=blue>（只能输入数字）一个手机号一个账户</font></td></tr>
	<tr><td>*&nbsp用户名称：</td><td><Input type="text" name="logname" maxlength="20" required></td></tr>
	<tr><td>*&nbsp真实姓名：</td><td><Input type="text" name="realname" required></td></tr>
	<tr><td>*&nbsp用户密码：</td><td><Input type="password" name="password" required></td>
		<td><font size=2 color=blue>（由字母、数字、下划线构成）</font></td></tr>
	<tr><td>*&nbsp重复密码：</td><td><Input type="password" name="again_password" required></td>
	</tr>
</table>
<br>
<input type="submit" name="g" value="注册"/>
</FORM>
</div>
<div align="center">
	<p>注册反馈：
		<jsp:getProperty property="backNews" name="userBean"/>
	<table border="3">
		<tr><td>手机号：</td>
			<td><jsp:getProperty property="phone" name="userBean"/></td>
		</tr>
		<tr><td>用户名称：</td>
			<td><jsp:getProperty property="logname" name="userBean"/></td>
		</tr>
		<tr><td>真是姓名：</td>
			<td><jsp:getProperty property="realname" name="userBean"/></td>
		</tr>
	</table>
</div>
</body>
</html>