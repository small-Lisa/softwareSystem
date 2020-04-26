<!DOCTYPE HTML>
<%@page contentType="text/html;charset=utf-8"%>
<%@page import="java.sql.*"%>
<%@page import="mybean.data.DataByPage"%>
<%@ page import="com.sun.rowset.*" %>
<jsp:useBean id="dataBean" class="mybean.data.DataByPage" scope="session"/>
<html>
<head><%@include file="head0.jsp"%></head>
<head><%@include file="head.txt"%></head>
<head>
<script type="application/javascript">
	function open1(){
		window.open(,'smallwin','height=100;weight=400;top=30%;left=30%;toolbar=no;menubar=no;scrollbars=no;resizable=no;location=no;status=no');
		OpenWindow.document.write("<html><title>加入购物车<title>");
		OpenWindow.document.write("<body>");
		OpenWindow.document.write("<h1>Hello!</h1>");
		OpenWindow.document.write("New window opened!");
		OpenWindow.document.write("</body></html>");
		OpenWindow.document.close();
	}
	function open2(){
		window.showModalDialog('xxx.do',window, 'height=100, width=400, top=0,left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no');
	}
	function open3(){
		var flag=confirm("是否确定点击？");
		if(flag){
			window.l
		}
	}
	function open4(){
		if(confirm("Are you sure to delete it ？"))
			alert("delete successful");
	}
	function open(){
		if(confirm("点击确认还是退出")){
			alert("确认");
		}else{
			alert("取消");
		}
	}
	function duihua(){
		alert("这个窗口是对话框！");
	}
	function queren(){
		var se=confirm("请选择点击一个按钮!");
		if (se==true){
			alert("你按下的是【确认】");
		}else{
			alert("你按下的是【取消】");
		}
	}
	function tishi(){
		var t=prompt("请输入您的名字","KING视界");
		if (t!=null && t!=""){
			document.write("精彩MV就在，" + t + "！属于你的世界");
		}
	}
</script>
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
    table.mt thead tr td {
        font-weight: bold;
		font-family:黑体;
		font-size:25px;
    }
    table.mt tbody tr:nth-child(odd) {
        background: rgba(171,192,228,0.65);
    }
</style>
</head>
<body background="image/back3.png"><font size=3>
<div align="center">

<br>
<form action="searchServlet" method="post">
<font>排序：</font>
<input type=radio name="r" value="number" checked>按下载量
<font>&nbsp&nbsp</font>
<input type=radio name="r" value="grade">按评分
&nbsp&nbsp&nbsp&nbsp&nbsp
<input type=text name="searchMess">
<input type=submit name="g" value="搜索">
</form>
<jsp:setProperty name="dataBean" property="pageSize" param="pageSize"/>
<jsp:setProperty name="dataBean" property="currentPage" param="currentPage"/>
<%
	CachedRowSetImpl rowSet=dataBean.getRowSet();
	
	if(rowSet==null){
		out.print("没有任何查询消息，无法浏览！");
		return;
	}
	
	rowSet.last();
	int totalRecord=rowSet.getRow();
	out.println("全部记录数"+totalRecord);
	int pageSize=dataBean.getPageSize();
	int totalPages=dataBean.getTotalPages();
	if(totalRecord%pageSize==0)
		totalPages=totalRecord/pageSize;
	else
		totalPages=totalRecord/pageSize+1;
	dataBean.setPageSize(pageSize);
	dataBean.setTotalPages(totalPages);
	out.print("<table class='mt'>");
	out.print("<thead><tr><td>图标</td><td>名称</td><td>容量</td><td>评分</td><td>下载量</td><td>价格</td><td>详情</td><td>购买</td></tr></thead>");
	out.print("<tbody>");
	if(totalPages>=1){
		if(dataBean.getCurrentPage()<1)
			dataBean.setCurrentPage(dataBean.getTotalPages());
		if(dataBean.getCurrentPage()>dataBean.getTotalPages())
			dataBean.setCurrentPage(1);
		int index=(dataBean.getCurrentPage()-1)*pageSize+1;
		rowSet.absolute(index);
		boolean boo=true;
		for(int i=1;i<=pageSize&&boo;i++)
		{
			String sname=rowSet.getString("softname");
			String spic=rowSet.getString("softpic");
			float scap=rowSet.getFloat("capacity");
			float score=rowSet.getFloat("score");
			float price=rowSet.getFloat("price");
			int sdowns=rowSet.getInt("downloads"); 
			String goods ="("+sname+","+spic+","+scap+","+score+","+price+","+sdowns+")#"+price;
			String detail="<form action='showDetail.jsp' method='post'>"+
				"<input type='hidden' name='sname' value="+sname+">"+
				"<input type='submit' name='bd' value='查看细节'></form>";
			String button="<form action='putGoodsServlet' method='post'>"+
				"<input type='hidden' name='goods' value="+goods+">"+
				"<input type='submit' name='bp' value='加入购物车'></form>";
			
			out.print("<tr>");
			out.print("<td><img src='softpic"+spic+"' width=50 heigth=50></img></td>");
			out.print("<td>"+sname+"</td>");
			out.print("<td>"+scap+"MB</td>");
			out.print("<td>"+score+"分</td>");
			out.print("<td>"+sdowns+"次</td>");
			out.print("<td>￥ "+price+"</td>");
			out.print("<td>"+detail+"</td>");
			out.print("<td>"+button+"</td>");
			out.print("</tr>");
			
			boo=rowSet.next();
		}
	}
	out.print("</tbody></table>");
%>

<br>每页最多显示<jsp:getProperty name="dataBean" property="pageSize"/>条信息
<br>当前显示第<font color=blue>
     <jsp:getProperty name="dataBean" property="currentPage"/>
   </font>页,共有
   <font color=blue><jsp:getProperty name="dataBean" property="totalPages"/>
   </font>页。
<table>
   <tr><td><form action="" method=post>
           <input type=hidden name="currentPage" value="<%=dataBean.getCurrentPage()-1 %>">
           <input type=submit name="g" value="上一页"></FORM></td>
       <td><form action="" method=post>
           <input type=hidden name="currentPage" value="<%=dataBean.getCurrentPage()+1 %>">
           <input type=submit name="g" value="下一页"></FORM></td></tr>
   <tr><td> <form action="" method=post>
           每页显示<input type=text name="pageSize" value =1 size=3>
           条记录<input type=submit name="g" value="确定"></FORM></td>
       <td> <form action="" method=post>
           输入页码：<input type=text name="currentPage" size=2 >
           <input type=submit name="g" value="提交"></FORM></td></tr>
</table>

</div>
</body></html>