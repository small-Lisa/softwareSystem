<%@page contentType="text/html;charset=utf-8"%>
<body background=yellow>
<jsp:useBean id="loginBean" class="mybean.data.Login" scope="session"/>
<br>
<ul align="left">
	<li>
	<a href="login.jsp"><font size=4><b>登录LOGIN账户</b></font></a>
		&nbsp<jsp:getProperty name="loginBean" property="phone"/>
	</li>
	<li><a href="exitServlet"><font size=4><b>退出&nbspEXIT&nbsp&nbsp账户</b></font></a></li>
</ul>
<br>
</body>