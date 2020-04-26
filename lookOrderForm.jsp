<!DOCTYPE html>
<%@page contentType="text/html;charset=utf-8"%>
<%@ page import="mybean.data.Login" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.sql.*" %>
<html>
<head><%@include file="head0.jsp"%></head>
<head><%@include file="head.txt"%></head>
<head>
<style type="text/css">
	table.mt {
        width: auto;
        margin: 15px 20px 20px 20px;
        border-top: 1px solid grey;
        border-spacing: 0px;
        color: black;
    }
    table.mt thead tr td,table.mt tbody tr td {
        border-bottom: 1px solid grey;
        padding: 15px 20px;
		font-size:20px;
		font-family:楷体;
    }
    table.mt thead tr td{
        font-weight: bold;
		font-family:黑体;
		font-size:25px;
		background: rgba(171,192,228,0.65);
    }
</style>
</head>
<body>
<div align="center">
<%
	if(loginBean==null){
        response.sendRedirect("login.jsp");
    }
    else {
       boolean b=loginBean.getPhone()==null||loginBean.getPhone().length()==0;
       if(b)
         response.sendRedirect("login.jsp");
    }
	Connection con;
	Statement sql;
	ResultSet rs;
	try{
		Class.forName("com.mysql.jdbc.Driver");
	}catch(Exception e){}
	try{
		String uri="jdbc:mysql://127.0.0.1:3306/software";
		String user="root";
		String password="123";
		con=DriverManager.getConnection(uri,user,password);
		sql=con.createStatement();
		String cdn="select orderId,orderMess,orderSum from orderForm where phone='"+loginBean.getPhone()+"'";
		rs=sql.executeQuery(cdn);
		out.print("<table class='mt'>");
		out.print("<thead><tr><td>订单号</td><td>订单信息</td><td>价格</td><td>下载</td></tr></thead>");
		out.print("<tbody>");
		while(rs.next()){
			out.print("<tr>");
			out.print("<td>"+rs.getString(1)+"</td>"); 
            out.print("<td>"+rs.getString(2)+"</td>");
            out.print("<td>"+rs.getString(3)+"</td>");
			out.print("<td><form action='downloadServlet' method='post'>"+
				"<input type='hidden' name='sname' value='"+rs.getString(2)+"'>"+
				"<input type='submit' name='down' value='下载安装包'></form>"+"</td>");
			out.print("</tr>");
		}
		out.print("</tbody>");
		out.print("</thead>");
		con.close();
		
	}catch(SQLException e){
		out.print(e);
	}
%>
</div>
</body></html>