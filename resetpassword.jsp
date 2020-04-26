<%@page contentType="text/html;charset=utf-8"%>
<jsp:useBean id="userBean" class="mybean.data.Register"/>
<html>
<body background="image/back3.png"><font size=3>
<title>Lisasasa软件中心</title>
<div align="center">
<br><br><br>
<img src="image/welcome.png" width=1400 height=200></img>
<br>
<a href="index.jsp"><font size=4>< 返回首页</font></a>
<br><br><br>

<form action=" " method="post">
<table>
	<tr><td>*&nbsp手机号：</td><td><Input type=text name="phone" required></td>
		<td><font size=2 color=blue>一个手机号一个账户</font></td></tr>
	<tr><td>*&nbsp新密码：</td><td><Input type=password name="newpassword" required></td>
		<td><font size=2 color=blue>由字母、数字、下划线构成</font></td></tr>
	<tr><td>*&nbsp重复新密码：</td><td><Input type=password name="again_newpassword" required></td></tr>
</table>
<br>
<table>
	<tr><td><input type=text placeholder="输入验证码" maxlength=4 name="code"></td>
		<td><input type=button value="发送验证码" name="b"></td></tr>

</table>
<br><br>
<input type=submit value="确认重置密码" name="s">
</form>
</div>
</body>
</html>