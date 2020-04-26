<%@page contentType="text/html;charset=utf-8"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@ page import = "com.baidu.aip.ocr.AipOcr" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="netscape.javascript.JSObject" %>
<%@ page import="org.json.JSONObject" %>
<jsp:useBean id="cardBean" class="mybean.data.Picfile" scope="request"/>
<html>
<body background="image/back3.png" height=auto><font size=3>
<title>Lisasasa软件中心</title>
<div align="center">
<br><br><br>
<img src="image/welcome.png" width=1400 height=200></img>
<br>
<a href="index.jsp"><font size=4>< 返回首页</font></a>
<br><br><br>
<font>------------请进行身份验证------------</font>
<br>
<font size=4 color=blue>示例：</font>
<img src="creditCard/shili.png" width=100 height=auto></img>
<br><br>
<form action="picfileServlet" method="post" ENCTYPE="multipart/form-data">
	正面照：</td><td><input type="file" name="cardL" required>
	<br><br>
	<input type=submit name="s" value="上传">
</form>
<font>---------------------------------------</font>
<p>上传反馈：<jsp:getProperty name="cardBean" property="mess"/>
<br>上传文件的名字：<jsp:getProperty name="cardBean" property="picname"/>
<%
	String picname=cardBean.getPicname();
	boolean boo=picname.endsWith(".jpg");
	boo=boo||picname.endsWith(".png");
	if(boo){
%>
<br><img src="creditCard/<%=picname%>" width=200 height=auto/>
<%
	}else{
%>	<%=picname%>
<%
	}
%>
<p>注册身份反馈：<jsp:getProperty name="cardBean" property="idmess"/>
<a href="login.jsp"><font size=5>去登陆</font></a>
<div align="center">
  <p>注册反馈：
  <table border="3">
	  <tr><td>身份证姓名：</td>
		  <td><jsp:getProperty name="cardBean" property="idname"/></td>
	  </tr>
  <tr><td>民族：</td>
	  <td><jsp:getProperty name="cardBean" property="idnation"/></td>
  </tr>
  <tr><td>住址：</td>
	  <td><jsp:getProperty name="cardBean" property="idaddress"/></td>
  </tr>
  <tr><td>性别：</td>
	  <td><jsp:getProperty name="cardBean" property="idsex"/></td>
  </tr>
  <tr><td>生日：</td>
	  <td><jsp:getProperty name="cardBean" property="idbirth"/></td>
  </tr>
  <tr><td>公民身份证号码：</td>
	  <td><jsp:getProperty name="cardBean" property="idnumber"/></td>
  </tr>
  </table>
</div>

</div>
</body></html>